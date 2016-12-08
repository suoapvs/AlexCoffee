package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Order;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Интерфейс сервисного слоя для работы с электронной почтой.
 * Представляет методы для отправки сообщений на электронную почту.
 *
 * @author Yurii Salimov
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.SenderServiceImpl
 */
public interface SenderService {
    /**
     * Отсылает информацию о заказе менеджерам на электронную почту.
     *
     * @param order Заказ для отправке менеджерам.
     */
    void send(Order order);

    /**
     * Возвращает настройки протокола TLS (Transport Layer Security) для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - TLS настройки.
     */
    Properties getTLSProperties();

    /**
     * Возвращает настройки протокола SSL для отправки сообщения.
     *
     * @return Объект класса {@link Properties} - SSL настройки.
     */
    Properties getSSLProperties();

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
    void sendMessage(Properties properties, String toEmail, String subject, String text) throws MessagingException, UnsupportedEncodingException;
}
