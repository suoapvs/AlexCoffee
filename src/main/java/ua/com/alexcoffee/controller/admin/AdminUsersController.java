package ua.com.alexcoffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserBuilder;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.service.interfaces.UserService;

/**
 * Класс-контроллер страниц управления персоналом сайта. К даному контроллеру
 * и соответствующим страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс является bean'ом
 * и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin/user") сообщаем, что данный контроллер будет
 * обрабатывать запросы, URI которых начинается с "/admin/user".
 * Методы класса работают с объектом, возвращенным handleRequest методом,
 * является типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see User
 * @see UserService
 */
@Controller
@RequestMapping(value = "/admin/user")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdminUsersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера пользователями.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param userService Объект сервиса для работы с пользователями.
     */
    @Autowired
    public AdminUsersController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает всех пользователей на страницу "admin/user/all".
     * URL запроса {"/admin/user", "/admin/user/", "/admin/user/all"},
     * метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView viewAllPersonnel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", this.userService.getPersonnel());
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("user/admin/all");
        return modelAndView;
    }

    /**
     * Возвращает пользователя с уникальным кодом id на страницу "admin/user/one".
     * URL запроса "/admin/user/view/{id}", метод GET.
     *
     * @param id           Код категории, которою нужно вернуть.
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
        modelAndView.setViewName("/user/admin/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/user/add" для добавления нового пользователе,
     * члена персонала (администратора или менеджера).
     * URL запроса "/admin/user/add", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public ModelAndView getAddUserPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("/user/admin/add");
        return modelAndView;
    }

    /**
     * Сохраняет нового пользователя по входящим параметрам и
     * перенаправляет по запросу "/admin/user/all".
     * URL запроса "/admin/user/save", метод POST.
     *
     * @param name         Имя нового пользователя.
     * @param roleName     Код роли пользователя.
     * @param username     Логин пользователя для входа в аккаунт на сайте.
     * @param password     Пароль пользователя для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц. сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц. сети "Facebook" пользователя.
     * @param skype        Логин пользователя в месенджере "Skype".
     * @param description  Описание пользователя.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    public String saveUser(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final String roleName,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description
    ) {
        final UserBuilder userBuilder = User.getBuilder();
        userBuilder.addName(name)
                .addUsername(username)
                .addPassword(password)
                .addEmail(email)
                .addPhone(phone)
                .addVkontakte(vkontakte)
                .addFacebook(facebook)
                .addSkype(skype)
                .addDescription(description);
        final UserRole role = UserRole.valueOf(roleName);
        userBuilder.addRole(role);
        final User user = userBuilder.build();
        this.userService.add(user);
        return "redirect:/admin/user/all";
    }

    /**
     * Возвращает исключение IllegalMappingException, если
     * обратится по запросу "/admin/user/save" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к
     *                                 этому методу GET.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.GET
    )
    public void saveUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/user/save\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/user/edit" для редактирование пользователя
     * с уникальным кодом, который совпадает с параметром id.
     * URL запроса "/admin/user/edit/{id}", метод GET.
     *
     * @param id           Код пользователя, информацию о котором
     *                     нужно отредактировать.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditUserPage(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.userService.get(id));
        modelAndView.addObject("roles", UserRole.values());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("/user/admin/edit");
        return modelAndView;
    }

    /**
     * Обновляет пользователя по входящим параметрам и перенаправляет
     * по запросу "/admin/user/view/{id}".
     * URL запроса "/admin/user/update", метод POST.
     *
     * @param id           Код пользователя для обновления.
     * @param name         Имя пользователя.
     * @param roleName     Код роли пользователя.
     * @param username     Логин пользователя для входа в аккаунт на сайте.
     * @param password     Пароль пользователя для входа в аккаунт на сайте.
     * @param email        Электронная почта пользователя.
     * @param phone        Номер телефона пользователя.
     * @param vkontakte    Ссылка на страничку в соц. сети "ВКонтакте" пользователя.
     * @param facebook     Ссылка на страничку в соц. сети "Facebook" пользователя.
     * @param skype        Логин пользователя в месенджере "Skype".
     * @param description  Описание пользователя.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "role") final String roleName,
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "description") final String description
    ) {
        final User user = this.userService.get(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setVkontakte(vkontakte);
        user.setFacebook(facebook);
        user.setSkype(skype);
        user.setDescription(description);
        final UserRole role = UserRole.valueOf(roleName);
        user.setRole(role);
        this.userService.update(user);
        return "redirect:/admin/user/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/user/update" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к
     *                                 этому методу GET.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/user/update\" is not supported!"
        );
    }

    /**
     * Удаляет пользователя с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/user/all".
     * URL запроса "/admin/user/delete/{id}", метод GET.
     *
     * @param id           Код пользвателя, которого нужно удалить.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteUser(@PathVariable(value = "id") final long id) {
        if (this.userService.getMainAdministrator().getId() != id) {
            this.userService.remove(id);
        }
        return "redirect:/admin/user/all";
    }

    /**
     * Удаляет всех пользователей и перенаправляет по запросу "/admin/user/all".
     * URL запроса "/admin/user/delete_all", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAll() {
        this.userService.removePersonnel();
        return "redirect:/admin/user/all";
    }
}
