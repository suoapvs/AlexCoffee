package ua.com.alexcoffee.controller.manager;

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

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс-контроллер страниц, предназначеных для управления заказами менеджерами.
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
@RequestMapping(value = "/managers/order")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class ManagerOrdersController {
    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Объект сервиса для работы с заказами клиентов.
     */
    private final OrderService orderService;

    /**
     * Конструктор для инициализации основных переменных контроллера страниц для менеджеров.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     *
     * @param userService   Объект сервиса для работы с пользователями.
     * @param orderService  Объект сервиса для работы с заказами клиентов.
     */
    @Autowired
    public ManagerOrdersController(
            final UserService userService,
            final OrderService orderService
    ) {
        super();
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * Возвращает все заказы, сделаные клиентами, на страницу "manager/order/all".
     * URL запроса {"/managers/order", "/managers/order/", "/managers/order/all"},
     * метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllOrders() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", this.orderService.getAll());
        modelAndView.addObject("status_new", OrderStatus.NEW);
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("order/manager/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным кодом id на страницу "manager/order/one".
     * URL запроса "/managers/order/view/{id}",  метод GET.
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
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.addObject("manager_role", UserRole.MANAGER);
        modelAndView.addObject("admin_role", UserRole.ADMIN);
        modelAndView.setViewName("order/manager/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit" для редактирование заказа с уникальным
     * кодом,который совпадает с параметром id, или перенаправляет по запросу
     * "/managers/orders", если этот заказ уже обработал другой менеджер.
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
        if (isNotNull(order.getManager()) ||
                (order.getManager().equals(this.userService.getAuthenticatedUser()))) {
            modelAndView.addObject("order", order);
            modelAndView.addObject("sale_positions", order.getSalePositions());
            modelAndView.addObject("order_price", order.getPrice());
            modelAndView.addObject("statuses", OrderStatus.values());
            modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
            modelAndView.setViewName("order/manager/edit");
        } else {
            modelAndView.setViewName("redirect:/managers/order/all");
        }
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам и перенаправляет по запросу "/admin/order/view/{id}".
     * URL запроса "/admin/order/update", метод POST.
     *
     * @param id           Код заказа для обновления.
     * @param managerId    Код менеджера или администратора, который обработал заказ в последний раз.
     * @param number       Номер заказа.
     * @param statusName     Код статуса выполнения заказа.
     * @param name         Имя клиента, оформивший заказ.
     * @param email        Электронная почта клиента.
     * @param phone        Номер телефона клиента.
     * @param address      Адрес доставки заказа.
     * @param details      Детали доставки заказа.
     * @param description  Описание заказа.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateOrder(
            @RequestParam final long id,
            @RequestParam(value = "auth_user") final long managerId,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "status") final String statusName,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "shipping-address") final String address,
            @RequestParam(value = "shipping-details") final String details,
            @RequestParam final String description
    ) {
        final Order order = this.orderService.get(id);
        if (isNotNull(order.getManager()) || (order.getManager() == this.userService.getAuthenticatedUser())) {
            final User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);
            final OrderStatus status = OrderStatus.valueOf(statusName);
            User manager = null;
            if (!status.equals(OrderStatus.NEW)) {
                manager = this.userService.get(managerId);
            }
            order.setNumber(number);
            order.setDate(new Date());
            order.setShippingAddress(address);
            order.setShippingDetails(details);
            order.setDescription(description);
            order.setStatus(status);
            order.setClient(client);
            order.setManager(manager);
            this.orderService.update(order);
        }
        return "redirect:/manager/order/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/order/update" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/admin/order/update",
            method = RequestMethod.GET
    )
    public void updateOrder() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/order/update\" is not supported!"
        );
    }
}
