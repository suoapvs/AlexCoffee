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
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.order.OrderStatus;
import ua.com.alexcoffee.model.user.User;
import ua.com.alexcoffee.model.user.UserRole;
import ua.com.alexcoffee.service.interfaces.OrderService;
import ua.com.alexcoffee.service.interfaces.UserService;

import java.util.Date;

/**
 * Класс-контроллер страниц управления заказами клиентов. К даному контроллеру
 * и соответствующим страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс является
 * bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin/order") сообщаем, что данный контроллер будет
 * обрабатывать запросы, URI которых начинается с "/admin/order".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Order
 * @see OrderService
 */
@Controller
@RequestMapping(value = "/admin/order")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdminOrdersController {
    /**
     * Объект сервиса для работы с заказами клиентов.
     */
    private final OrderService orderService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера заказов.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param orderService Объект сервиса для работы с заказами клиентов.
     * @param userService  Объект сервиса для работы с категориями товаров.
     */
    @Autowired
    public AdminOrdersController(
            final OrderService orderService,
            final UserService userService
    ) {
        this.orderService = orderService;
        this.userService = userService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "admin/order/all".
     * URL запроса {"/admin/order", "/admin/order/", "/admin/order/all", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET)
    public ModelAndView viewAllOrders() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", this.orderService.getAll());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/admin/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "admin/order/one".
     * URL запроса "/admin/order/view/{id}", метод GET.
     *
     * @param id           Код заказа, который нужно вернуть.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewOrder(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/admin/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа
     * с уникальным кодом, который совпадает с параметром id.
     * URL запроса "/admin/order/edit/{id}", метод GET.
     *
     * @param id           Код заказа, который нужно отредактировать.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditOrderPage(@PathVariable(value = "id") final long id) {
        final Order order = this.orderService.get(id);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order", order);
        modelAndView.addObject("sale_positions", order.getSalePositions());
        modelAndView.addObject("order_price", order.getPrice());
        modelAndView.addObject("statuses", OrderStatus.values());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/admin/edit");
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/order/view/{id}".
     * URL запроса "/admin/order/update", метод POST.
     *
     * @param id           Код заказа для обновления.
     * @param managerId    Код менеджера или администратора, который обработал заказ в последний раз.
     * @param number       Номер заказа.
     * @param statusName   Код статуса выполнения заказа.
     * @param name         Имя клиента, оформивший заказ.
     * @param email        Электронная почта клиента.
     * @param phone        Номер телефона клиента.
     * @param address      Адрес доставки товаров заказа.
     * @param details      Детали доставки заказа.
     * @param description  Описание заказа.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateOrder(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "auth_user") final long managerId,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "status") final String statusName,
            @RequestParam(value = "user_name") final String name,
            @RequestParam(value = "user_email") final String email,
            @RequestParam(value = "user_phone") final String phone,
            @RequestParam(value = "shipping-address") final String address,
            @RequestParam(value = "shipping-details") final String details,
            @RequestParam(value = "description") final String description
    ) {
        final Order order = this.orderService.get(id);
        order.setNumber(number);
        order.setShippingAddress(address);
        order.setShippingDetails(details);
        order.setDescription(description);
        order.setDate(new Date());

        final User client = order.getClient();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        order.setClient(client);

        final OrderStatus status = OrderStatus.valueOf(statusName);
        order.setStatus(status);

        final User manager = this.userService.get(managerId);
        order.setManager(manager);

        this.orderService.update(order);
        return "redirect:/admin/order/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/order/update" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится
     *                                 к этому методу GET.
     */
    @RequestMapping(
            value = "/update_order",
            method = RequestMethod.GET
    )
    public void updateOrder() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/order/update\" is not supported!"
        );
    }

    /**
     * Удаляет заказ с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/order/all".
     * URL запроса "/admin/order/delete/{id}", метод GET.
     *
     * @param id           Код заказа, которою нужно удалить.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteOrder(@PathVariable(value = "id") final long id) {
        this.orderService.remove(id);
        return "redirect:/admin/order/all";
    }

    /**
     * Удаляет все заказы и перенаправляет по запросу "/admin/order/all".
     * URL запроса "/admin/order/delete_all", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAllOrders() {
        this.orderService.removeAll();
        return "redirect:/admin/order/all";
    }
}
