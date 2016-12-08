package ua.com.alexcoffee.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.service.SenderService;
import ua.com.alexcoffee.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Класс сервисного слоя реализует методы интерфейса {@link SenderService}
 * для работы с электронной почтой. Также реализует интерфейс {@link Runnable},
 * то есть можно отправлять сообщения в отдельном потоке.
 *
 * @author Yurii Salimov
 * @see SenderService
 * @see User
 * @see Order
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class SenderServiceImpl implements SenderService, Runnable {

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
    private List<User> managers;

    /**
     * Заказ, информация о котором будет приходить на почту менеджерам.
     */
    private Order order;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     *
     * @param userService Реализация интерфейса для работы з пользователями.
     */
    @Autowired
    public SenderServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Отсылает информацию о заказе менеджерам на электронную почту.
     * Запускает отдельный поток.
     *
     * @param order Заказ для отправке менеджерам.
     */
    @Override
    public void send(Order order) {
        this.order = order;
        new Thread(this).start();
    }

    /**
     * Запускает текущий поток. Переопределенный метод класса {@link Thread}.
     */
    @Override
    public void run() {
        if (this.order != null) {
            this.admin = this.userService.getMainAdministrator();
            this.managers = this.userService.getManagers();
            Collections.shuffle(this.managers);

            if (this.admin != null && !this.managers.isEmpty()) {
                choosePropertiesAndSend();
            }
        }
    }

    private void choosePropertiesAndSend() {
        Properties properties;
        String subject = "AlexCoffee || New Order " + this.order.getNumber();
        String text = this.order.toString();
        try {
            for (User manager : this.managers) {
                Thread.sleep(10000);
                try {
                    try {
                        properties = getTLSProperties();
                        sendMessage(properties, manager.getEmail(), subject, text);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                        LOGGER.error(ex.getMessage(), ex);

                        properties = getSSLProperties();
                        sendMessage(properties, manager.getEmail(), subject, text);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    LOGGER.error(ex.getMessage(), ex);
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Возвращает настройки протокола TLS (Transport Layer Security) для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - TLS настройки.
     */
    @Override
    public Properties getTLSProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    /**
     * Возвращает настройки протокола SSL (Secure Sockets Layer) для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - SSL настройки.
     */
    @Override
    public Properties getSSLProperties() {
        Properties properties = new Properties();
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
    public void sendMessage(Properties properties, String toEmail, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(admin.getEmail(), admin.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("support@alexcoffee.com.ua"));
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
    public Order getOrder() {
        return this.order;
    }

    /**
     * Устанавливает заказ для отправки менеджерам.
     *
     * @param order Заказ для обработки.
     */
    public void setOrder(Order order) {
        this.order = order;
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
    public void setAdmin(User admin) {
        this.admin = admin;
    }

    /**
     * Возвращает список менеджеров, которым будет отправлятся сообщения.
     *
     * @return Объект класса {@link List} - список менеджеров.
     */
    public List<User> getManagers() {
        return this.managers;
    }

    /**
     * Устанавливает список менеджеров, которым будет отправлятся сообщения.
     *
     * @param managers список менеджеров.
     */
    public void setManagers(List<User> managers) {
        this.managers = managers;
    }
}
