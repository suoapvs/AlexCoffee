package ua.com.alexcoffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.interfaces.RoleService;
import ua.com.alexcoffee.service.interfaces.UserService;

/**
 * Класс-контроллер страниц управления
 * персоналом сайта. К даному контроллеру
 * и соответствующим страницам могут
 * обращатсья пользователи, имеющие
 * роль-админстратор. Аннотация @Controller
 * служит для сообщения Spring'у о том,
 * что данный класс является bean'ом и
 * его необходимо подгрузить при старте
 * приложения.
 * Аннотацией @RequestMapping(value = "/admin")
 * сообщаем, что данный контроллер будет
 * обрабатывать запрос, URI которого "/admin".
 * Методы класса работают с объектом,
 * возвращенным handleRequest методом,
 * является типом {@link ModelAndView},
 * который агрегирует все параметры модели
 * и имя отображения.
 * Этот тип представляет Model и View
 * в MVC шаблоне.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see User
 * @see UserService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdminUsersController {
    /**
     * Объект сервиса для работы
     * с пользователями.
     */
    private final UserService userService;

    /**
     * Объект сервиса для работы
     * с ролями пользователей.
     */
    private final RoleService roleService;

    /**
     * Конструктор для инициализации
     * основных переменных контроллера
     * пользователями.
     * Помечен аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param userService Объект сервиса для работы
     *                    с пользователями.
     * @param roleService Объект сервиса для работы
     *                    с ролями пользователей.
     */
    @Autowired
    public AdminUsersController(
            final UserService userService,
            final RoleService roleService
    ) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает всех пользователей
     * на страницу "admin/user/all".
     * URL запроса "/admin/users",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "users", this.userService.getPersonnel());
        modelAndView.addObject(
                "admin_role", this.roleService.getAdministrator()
        );
        modelAndView.addObject(
                "manager_role", this.roleService.getManager()
        );
        modelAndView.addObject(
                "auth_user", this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/user/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя
     * с уникальным кодом id
     * на страницу "admin/user/one".
     * URL запроса "/admin/view_user_{id}",
     * метод GET.
     *
     * @param id           Код категории,
     *                     которою нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view_user_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewUser(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "user",
                this.userService.get(id));
        modelAndView.addObject(
                "admin_role",
                this.roleService.getAdministrator()
        );
        modelAndView.addObject(
                "manager_role",
                this.roleService.getManager()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("/admin/user/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/user/add"
     * для добавления нового пользователе,
     * члена персонала (администратора
     * или менеджера).
     * URL запроса "/admin/add_user", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/add_user",
            method = RequestMethod.GET
    )
    public ModelAndView getAddUserPage(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "roles",
                this.roleService.getPersonnel()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("/admin/user/add");
        return modelAndView;
    }

    /**
     * Сохраняет нового пользователя
     * по входящим параметрам и
     * перенаправляет по запросу "/admin/users".
     * URL запроса "/admin/save_user",
     * метод POST.
     *
     * @param name         Имя нового пользователя.
     * @param roleId       Код роли пользователя.
     * @param username     Логин пользователя
     *                     для входа в аккаунт на сайте.
     * @param password     Пароль пользователя
     *                     для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц.
     *                     сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц.
     *                     сети "Facebook" пользователя.
     * @param skype        Логин пользователя
     *                     в месенджере "Skype".
     * @param description  Описание пользователя.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/save_user",
            method = RequestMethod.POST
    )
    public ModelAndView saveUser(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final long roleId,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description,
            final ModelAndView modelAndView
    ) {
        final User user = new User();
        final Role role = this.roleService.get(roleId);
        user.initialize(
                name,
                username,
                password,
                email,
                phone,
                vkontakte,
                facebook,
                skype,
                description,
                role
        );
        this.userService.add(user);
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }

    /**
     * Возвращает исключение
     * WrongInformationException, если
     * обратится по запросу "/save_user"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/save_user",
            method = RequestMethod.GET
    )
    public void saveUser()
            throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/save_user\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/user/edit"
     * для редактирование пользователя
     * с уникальным кодом, который совпадает
     * с параметром id.
     * URL запроса "/admin/edit_user_{id}",
     * метод GET.
     *
     * @param id           Код пользователя,
     *                     информацию о котором
     *                     нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit_user_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditUserPage(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "user",
                this.userService.get(id)
        );
        modelAndView.addObject(
                "roles",
                this.roleService.getPersonnel()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("/admin/user/edit");
        return modelAndView;
    }

    /**
     * Обновляет пользователя по входящим
     * параметрам и перенаправляет
     * по запросу "/admin/view_user_{id}".
     * URL запроса "/admin/update_user",
     * метод POST.
     *
     * @param id           Код пользователя
     *                     для обновления.
     * @param name         Имя пользователя.
     * @param roleId       Код роли пользователя.
     * @param username     Логин пользователя
     *                     для входа в аккаунт на сайте.
     * @param password     Пароль пользователя
     *                     для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц.
     *                     сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц.
     *                     сети "Facebook" пользователя.
     * @param skype        Логин пользователя
     *                     в месенджере "Skype".
     * @param description  Описание пользователя.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update_user",
            method = RequestMethod.POST
    )
    public ModelAndView updateUser(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final long roleId,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description,
            final ModelAndView modelAndView
    ) {
        final User user = this.userService.get(id);
        final Role role = this.roleService.get(roleId);
        user.initialize(
                name,
                username,
                password,
                email,
                phone,
                vkontakte,
                facebook,
                skype,
                description,
                role
        );
        this.userService.update(user);
        modelAndView.setViewName("redirect:/admin/view_user_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException,
     * если обратится по запросу "/update_user"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/update_user",
            method = RequestMethod.GET
    )
    public void updateUser() throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/update_user\" is not supported!"
        );
    }

    /**
     * Удаляет пользователя с уникальным
     * кодом, который совпадает с
     * входящим параметром id, и
     * перенаправляет по запросу
     * "/admin/users".
     * URL запроса "/delete_user_{id}",
     * метод GET.
     *
     * @param id           Код пользвателя,
     *                     которого нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_user_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteUser(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        if (this.userService.getMainAdministrator().getId() != id) {
            this.userService.remove(id);
        }
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }

    /**
     * Удаляет всех пользователей и
     * перенаправляет по запросу "/admin/users".
     * URL запроса "/delete_all_users",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping
            (value = "/delete_all_users",
                    method = RequestMethod.GET
            )
    public ModelAndView deleteAll(
            final ModelAndView modelAndView
    ) {
        this.userService.removePersonnel();
        modelAndView.setViewName("redirect:/admin/users");
        return modelAndView;
    }
}
