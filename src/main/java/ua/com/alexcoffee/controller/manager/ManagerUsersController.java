package ua.com.alexcoffee.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.service.interfaces.OrderService;
import ua.com.alexcoffee.service.interfaces.RoleService;
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
public class ManagerUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Объект сервиса для работы с ролями пользователей.
     */
    private final RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     *
     * @param userService Объект сервиса для работы с пользователями.
     * @param roleService Объект сервиса для работы с ролями пользователей.
     */
    @Autowired
    public ManagerUsersController(
            final UserService userService,
            final RoleService roleService
    ) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает всех пользователей на страницу "manager/user/all".
     * URL запроса {"/managers/user", "/managers/user/", "/managers/user/all"},
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", this.roleService.getAdministrator());
        modelAndView.addObject("manager_role", this.roleService.getManager());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("manager/user/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "manager/user/one".
     * URL запроса "/managers/user/view/{id}", метод GET.
     *
     * @param id           Код пользователя, которого нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewUser(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("admin_role", this.roleService.getAdministrator());
        modelAndView.addObject("manager_role", this.roleService.getManager());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("manager/user/one");
        return modelAndView;
    }
}
