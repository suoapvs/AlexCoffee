package ua.com.alexcoffee.controller.other;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.ForbiddenException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.interfaces.ShoppingCartService;
import ua.com.alexcoffee.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс для перехвата исключений. Он будет перехватывать исключения, которые не указаны
 * в контроллере. Методы класса работают с объектом, возвращенным handleRequest методом,
 * является типом {@link ModelAndView}, который агрегирует все параметры модели
 * и имя отображения. Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see BadRequestException
 * @see DuplicateException
 * @see ForbiddenException
 * @see WrongInformationException
 */
@ControllerAdvice
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdviceController {

    /**
     * Объект для логирования информации.
     */
    private static final Logger LOGGER = Logger.getLogger(AdviceController.class);

    /**
     * Сообщение исключения NoHandlerFoundException.
     */
    private final static String NO_HANDLER_FOUND_EXCEPTION_MESSAGE
            = "Ошибка 404. Не найдено!";

    /**
     * Сообщение исключения BadRequestException.
     */
    private final static String BAD_REQUEST_EXCEPTION_MESSAGE
            = "Ошибка в запросе!";

    /**
     * Сообщение исключения WrongInformationException.
     */
    private final static String WRONG_INFORMATION_EXCEPTION_MESSAGE
            = "Ошибка в запросе!";

    /**
     * Сообщение исключения ForbriddenException.
     */
    private final static String FORBIDDEN_EXCEPTION_MESSAGE
            = "У Вас нет достаточных прав для доступа к этой странице.";

    /**
     * Сообщение исключения DuplicateException.
     */
    private final static String DUPLICATE_EXCEPTION_MESSAGE
            = "Такой объект уже существует!";

    /**
     * Сообщение все других исключений.
     */
    private final static String OTHER_EXCEPTION_MESSAGE
            = "Временные неполадки с сервером... Приносим свои извинения!";

    /**
     * Объект сервиса для работы с корзиной.
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных перехватчика исключений.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param shoppingCartService Объект сервиса для работы с корзиной.
     * @param userService         Объект сервиса для работы с пользователями.
     */
    @Autowired
    public AdviceController(
            final ShoppingCartService shoppingCartService,
            final UserService userService
    ) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    /**
     * Перехват NoHandlerFoundException исключения (http статус 404).
     *
     * @param exception Объект исключения NoHandlerFoundException.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(
            final NoHandlerFoundException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, NO_HANDLER_FOUND_EXCEPTION_MESSAGE);
    }

    /**
     * Перехват BadRequestException исключения (http статус 400).
     *
     * @param exception Объект исключения BadRequestException.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView badRequestException(
            final BadRequestException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, BAD_REQUEST_EXCEPTION_MESSAGE);
    }

    /**
     * Перехват WrongInformationException исключения (http статус 400).
     *
     * @param exception Объект исключения WrongInformationException.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView wrongInformationException(
            final WrongInformationException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, WRONG_INFORMATION_EXCEPTION_MESSAGE);
    }

    /**
     * Перехват ForbiddenException исключения (http статус 403).
     *
     * @param exception Объект исключения ForbiddenException.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenException(
            final ForbiddenException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, FORBIDDEN_EXCEPTION_MESSAGE);
    }

    /**
     * Перехват DuplicateException исключения (http статус 409).
     *
     * @param exception Объект исключения DuplicateException.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(
            final DuplicateException exception,
            final HttpServletRequest request
    ) {
        return handleException(exception, request, DUPLICATE_EXCEPTION_MESSAGE);
    }

    /**
     * Перехват всех остальных исключения (http статус 500).
     *
     * @param exception Объект исключения Exception.
     * @param request   Объект интерфейса HttpServletRequest.
     * @return Объект класса {@link ModelAndView}.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(
            final Exception exception,
            final HttpServletRequest request) {
        return handleException(exception, request, OTHER_EXCEPTION_MESSAGE);
    }

    /**
     * Обработака всех входящих исключений: логирование, печать стека,
     * возврат модели с информациею.
     *
     * @param ex        Объект исключения наследника Exception.
     * @param request   Объект интерфейса HttpServletRequest.
     * @param textError Текст исключения, который нужно вывести на страницу.
     * @return Объект класса {@link ModelAndView}.
     */
    private ModelAndView handleException(
            final Exception ex,
            final HttpServletRequest request,
            final String textError
    ) {
        logError(ex, request);
        final ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
            addAuthenticatedUser(request, modelAndView);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        modelAndView.addObject("text_error", textError);
        modelAndView.addObject(
                "message_error",
                ex.getClass().getSimpleName() + " : " + ex.getMessage()
        );
        modelAndView.setViewName("client/error");
        return modelAndView;
    }

    /**
     * огирует информацию об ошибке.
     *
     * @param ex      Объект исключения наследника Exception.
     * @param request Объект интерфейса HttpServletRequest.
     */
    private void logError(
            final Exception ex,
            final HttpServletRequest request
    ) {
        LOGGER.error(request.getRemoteAddr() + " : " + request.getRequestURL());
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    /**
     * Добавляет авторизированого пользователя.
     *
     * @param request      Объект интерфейса HttpServletRequest.
     * @param modelAndView Объект класса {@link ModelAndView}.
     */
    private void addAuthenticatedUser(
            final HttpServletRequest request,
            final ModelAndView modelAndView
    ) {
        final String servletPath = request.getServletPath();
        if ((servletPath.contains("admin")) ||
                (servletPath.contains("manager"))) {
            modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        }
    }
}
