package ua.com.alexcoffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.OrderService;
import ua.com.alexcoffee.service.RoleService;
import ua.com.alexcoffee.service.StatusService;
import ua.com.alexcoffee.service.UserService;

import java.util.Date;

/**
 * Класс-контроллер страниц управления заказами клиентов. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/admin".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov
 * @see Order
 * @see OrderService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdminOrdersController {
    /**
     * Объект сервиса для работы с заказами клиентов.
     */
    private final OrderService orderService;

    /**
     * Объект сервиса для работы с статусами выполнения заказов.
     */
    private final StatusService statusService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final UserService userService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final RoleService roleService;

    /**
     * Конструктор для инициализации основных переменных контроллера заказов.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     *
     * @param orderService  Объект сервиса для работы с заказами клиентов.
     * @param statusService Объект сервиса для работы с статусами выполнения заказов.
     * @param userService   Объект сервиса для работы с категориями товаров.
     * @param roleService   Объект сервиса для работы с категориями товаров.
     */
    @Autowired
    public AdminOrdersController(OrderService orderService, StatusService statusService,
                                 UserService userService, RoleService roleService) {
        this.orderService = orderService;
        this.statusService = statusService;
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "admin/order/all".
     * URL запроса {"/admin", "/admin/orders"}, метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView viewAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", this.orderService.getAll());
        modelAndView.addObject("status_new", this.statusService.getDefault());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/all");
        return modelAndView;
    }

    /**
     * Перенаправляет по по запросу "/admin/orders".
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView redirectToOrders(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "admin/order/one".
     * URL запроса "/admin/view_order_{id}", метод GET.
     *
     * @param id           Код заказа, который нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/view_order_{id}", method = RequestMethod.GET)
    public ModelAndView viewOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = this.orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", this.statusService.getDefault());
        modelAndView.addObject("admin_role", this.roleService.getAdministrator());
        modelAndView.addObject("manager_role", this.roleService.getManager());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа с уникальным кодом,
     * который совпадает с параметром id. URL запроса "/admin/edit_order_{id}", метод GET.
     *
     * @param id           Код заказа, которую нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/edit_order_{id}", method = RequestMethod.GET)
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        Order order = this.orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("statuses", this.statusService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/order/edit");
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/view_order_{id}".
     * URL запроса "/admin/update_category", метод POST.
     *
     * @param id              Код заказа для обновления.
     * @param managerId       Код менеджера или администратора, который обработал заказ в последний раз.
     * @param number          Номер заказа.
     * @param statusId        Код статуса выполнения заказа.
     * @param name            Имя клиента, оформивший заказ.
     * @param email           Электронная почта клиента.
     * @param phone           Номер телефона клиента.
     * @param shippingAddress Адрес доставки товаров заказа.
     * @param shippingDetails Детали доставки заказа.
     * @param description     Описание заказа.
     * @param modelAndView    Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.POST)
    public ModelAndView updateOrder(@RequestParam long id,
                                    @RequestParam(value = "auth_user") long managerId,
                                    @RequestParam String number,
                                    @RequestParam(value = "status") long statusId,
                                    @RequestParam(value = "user_name") String name,
                                    @RequestParam(value = "user_email") String email,
                                    @RequestParam(value = "user_phone") String phone,
                                    @RequestParam(value = "shipping-address") String shippingAddress,
                                    @RequestParam(value = "shipping-details") String shippingDetails,
                                    @RequestParam String description,
                                    ModelAndView modelAndView) {
        Order order = this.orderService.get(id);

        User client = order.getClient();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);

        Status status = this.statusService.get(statusId);
        User manager = this.userService.get(managerId);

        order.initialize(number, new Date(), shippingAddress, shippingDetails, description, status, client, manager);
        this.orderService.update(order);

        modelAndView.setViewName("redirect:/admin/view_order_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_order" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/update_order", method = RequestMethod.GET)
    public void updateOrder() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_order\" is not supported!");
    }

    /**
     * Удаляет заказ с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/orders".
     * URL запроса "/delete_orders_{id}", метод GET.
     *
     * @param id           Код заказа, которою нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_order_{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        this.orderService.remove(id);
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }

    /**
     * Удаляет все заказы и перенаправляет по запросу "/admin/orders".
     * URL запроса "/delete_all_orders", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_all_orders", method = RequestMethod.GET)
    public ModelAndView deleteAllOrders(ModelAndView modelAndView) {
        this.orderService.removeAll();
        modelAndView.setViewName("redirect:/admin/orders");
        return modelAndView;
    }
}
