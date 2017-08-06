package ua.com.alexcoffee.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.service.interfaces.OrderService;
import ua.com.alexcoffee.service.interfaces.UserService;

/**
 * Класс-контроллер страниц, предназначеных для управления пользователей менеджерами.
 * К даному контроллеру и соответствующим страницам могут обращатсья пользователи,
 * имеющие роль-админстратор и -менеджер.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс является
 * bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/manager") сообщаем, что данный контроллер будет
 * обрабатывать запрос, URI которого "/manager". Методы класса работают с объектом,
 * возвращенным handleRequest методом, является типом {@link ModelAndView},
 * который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see User
 * @see Order
 * @see UserService
 * @see OrderService
 */
@Controller
@RequestMapping(value = "/managers/user")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class ManagerUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     *
     * @param userService Объект сервиса для работы с пользователями.
     */
    @Autowired
    public ManagerUsersController(final UserService userService) {
        super();
        this.userService = userService;
    }

    /**
     * Возвращает всех пользователей на страницу "manager/user/all".
     * URL запроса {"/managers/user", "/managers/user/", "/managers/user/all"},
     * метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "manager/user/one".
     * URL запроса "/managers/user/view/{id}", метод GET.
     *
     * @param id           Код пользователя, которого нужно вернуть.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewUser(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/manager/one");
        return modelAndView;
    }
}
