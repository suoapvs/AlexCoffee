package ua.com.alexcoffee.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.service.interfaces.SenderService;
import ua.com.alexcoffee.service.interfaces.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс сервисного слоя реализует методы интерфейса {@link SenderService}
 * для работы с электронной почтой. Также реализует интерфейс {@link Runnable},
 * то есть можно отправлять сообщения в отдельном потоке.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see SenderService
 * @see User
 * @see Order
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class SenderServiceImpl implements SenderService, Runnable {

    /**
     * Объект для логирования информации.
     */
    private static final Logger LOGGER = Logger.getLogger(SenderServiceImpl.class);

    /**
     * Стандартная кодировка сообщений.
     */
    private static final String CHARSET = "UTF-8";

    /**
     * Стандартная кодировка сообщений.
     */
    private static final String ENCODING = "Q";

    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Администратор сайта, от имени которого будут отправлятся
     * сообщения менеджерам.
     */
    private User admin;

    /**
     * Список менеджеров, которым будет приходить сообщение о заказе.
     */
    private Collection<User> managers;

    /**
     * Заказ, информация о котором будет приходить на почту менеджерам.
     */
    private Order orderEntity;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param userService Реализация интерфейса для работы з пользователями.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SenderServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Отсылает информацию о заказе менеджерам на электронную почту.
     * Запускает отдельный поток.
     *
     * @param orderEntity Заказ для отправке менеджерам.
     */
    @Override
    public void send(final Order orderEntity) {
        this.orderEntity = orderEntity;
        new Thread(this).start();
    }

    /**
     * Запускает текущий поток.
     * Переопределенный метод класса {@link Thread}.
     */
    @Override
    public void run() {
        if (this.orderEntity != null) {
            this.admin = this.userService.getMainAdministrator();
            this.managers = this.userService.getManagers();
            if (isNotNull(this.admin) && isNotEmpty(this.managers)) {
                choosePropertiesAndSend();
            }
        }
    }

    /**
     * Выбирает подходящие настройки и отправляет сообщение.
     */
    private void choosePropertiesAndSend() {
        Properties properties;
        final String subject = "AlexCoffee || New Order " + this.orderEntity.getNumber();
        final String text = this.orderEntity.toString();
        for (User manager : this.managers) {
            try {
                try {
                    properties = getTLSProperties();
                    sendMessage(
                            properties,
                            manager.getEmail(),
                            subject, text
                    );
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage(), ex);
                    properties = getSSLProperties();
                    sendMessage(
                            properties,
                            manager.getEmail(),
                            subject, text
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Возвращает настройки протокола TLS
     * (Transport Layer Security) для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - TLS настройки.
     */
    @Override
    public Properties getTLSProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    /**
     * Возвращает настройки протокола SSL (Secure Sockets Layer)
     * для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - SSL настройки.
     */
    @Override
    public Properties getSSLProperties() {
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    /**
     * Отправляет сообщение по заданым параметрам.
     *
     * @param properties Настройки протокола для сессии.
     * @param toEmail    Адрес электронной почты, на который будет отправлено сообщение.
     * @param subject    Тема сообщения.
     * @param text       Текст сообщения.
     * @throws MessagingException           Исключение класса InternetAddress.
     * @throws UnsupportedEncodingException Исключение кодировки метдом MimeUtility.encodeText().
     */
    @Override
    public void sendMessage(
            final Properties properties,
            final String toEmail,
            final String subject,
            final String text
    ) throws MessagingException, UnsupportedEncodingException {
        final Session session = Session.getDefaultInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                admin.getEmail(), admin.getPassword()
                        );
                    }
                }
        );
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("info@alexcoffee.com.ua"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(MimeUtility.encodeText(subject, CHARSET, ENCODING));
        message.setContent(text, "text/plain;charset=" + CHARSET);
        message.setSentDate(new Date());
        Transport.send(message);
    }

    /**
     * Возвращает заказ для отправки менеджерам.
     *
     * @return Объект класса {@link Order} - заказ для обработки.
     */
    public Order getOrderEntity() {
        return this.orderEntity;
    }

    /**
     * Устанавливает заказ для отправки менеджерам.
     *
     * @param orderEntity Заказ для обработки.
     */
    public void setOrderEntity(final Order orderEntity) {
        this.orderEntity = orderEntity;
    }

    /**
     * Возвращает администратора, от имени которого отсылаются сообщения.
     *
     * @return Объект класса {@link User} - администратор сайта.
     */
    public User getAdmin() {
        return this.admin;
    }

    /**
     * Устанавливает администратора, от имени которого отсылаются сообщения.
     *
     * @param admin Администратор сайта.
     */
    public void setAdmin(final User admin) {
        this.admin = admin;
    }

    /**
     * Возвращает список менеджеров, которым будет отправлятся сообщения.
     *
     * @return Объект класса {@link List} - список менеджеров.
     */
    public Collection<User> getManagers() {
        return this.managers;
    }

    /**
     * Устанавливает список менеджеров, которым будет отправлятся сообщения.
     *
     * @param managers список менеджеров.
     */
    public void setManagers(final Collection<User> managers) {
        this.managers = managers;
    }
}
