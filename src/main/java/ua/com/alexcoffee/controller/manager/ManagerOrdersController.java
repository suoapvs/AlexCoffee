package ua.com.alexcoffee.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Order;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.service.interfaces.OrderService;
import ua.com.alexcoffee.service.interfaces.RoleService;
import ua.com.alexcoffee.service.interfaces.StatusService;
import ua.com.alexcoffee.service.interfaces.UserService;

import java.util.Date;

/**
 * Класс-контроллер страниц, предназначеных
 * для управления заказами менеджерами.
 * К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи,
 * имеющие роль-админстратор и -менеджер.
 * Аннотация @Controller служит для сообщения
 * Spring'у о том, что данный класс является
 * bean'ом и его необходимо подгрузить при
 * старте приложения.
 * Аннотацией @RequestMapping(value = "/manager")
 * сообщаем, что данный контроллер будет
 * обрабатывать запрос, URI которого "/manager".
 * Методы класса работают с объектом,
 * возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует
 * все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see User
 * @see Order
 * @see UserService
 * @see OrderService
 */
@Controller
@RequestMapping(value = "/managers/order")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class ManagerOrdersController {
    /**
     * Объект сервиса для работы
     * с пользователями.
     */
    private final UserService userService;

    /**
     * Объект сервиса для работы
     * с заказами клиентов.
     */
    private final OrderService orderService;

    /**
     * Объект сервиса для работы
     * с статусами виполнения заказов.
     */
    private final StatusService statusService;

    /**
     * Объект сервиса для работы
     * с ролями пользователей.
     */
    private final RoleService roleService;

    /**
     * Конструктор для инициализации
     * основных переменных контроллера
     * страниц для менеджеров.
     * Помечен аннотацией @Autowired,
     * которая позволит Spring
     * автоматически инициализировать
     * объекты.
     *
     * @param userService   Объект сервиса для работы
     *                      с пользователями.
     * @param orderService  Объект сервиса для работы
     *                      с заказами клиентов.
     * @param statusService Объект сервиса для работы
     *                      с статусами виполнения
     *                      заказов.
     * @param roleService   Объект сервиса для работы
     *                      с ролями пользователей.
     */
    @Autowired
    public ManagerOrdersController(
            final UserService userService,
            final OrderService orderService,
            final StatusService statusService,
            final RoleService roleService
    ) {
        super();
        this.userService = userService;
        this.orderService = orderService;
        this.statusService = statusService;
        this.roleService = roleService;
    }

    /**
     * Возвращает все заказы,
     * сделаные клиентами,
     * на страницу "manager/order/all".
     * URL запроса {"/managers/order",
     * "/managers/order/", "/managers/order/all"},
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView viewAllOrders(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "orders",
                this.orderService.getAll()
        );
        modelAndView.addObject(
                "status_new",
                this.statusService.getDefault()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("manager/order/all");
        return modelAndView;
    }

    /**
     * Возвращает заказ с уникальным
     * кодом id на страницу
     * "manager/order/one".
     * URL запроса "/managers/order/view/{id}",
     * метод GET.
     *
     * @param id           Код заказа, который
     *                     нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewOrder(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        final Order order = this.orderService.get(id);
        modelAndView.addObject("order", order);
        modelAndView.addObject(
                "sale_positions",
                order.getSalePositions()
        );
        modelAndView.addObject(
                "order_price",
                order.getPrice()
        );
        modelAndView.addObject(
                "status_new",
                this.statusService.getDefault()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.addObject(
                "manager_role",
                this.roleService.getManager()
        );
        modelAndView.addObject(
                "admin_role",
                this.roleService.getAdministrator()
        );
        modelAndView.setViewName("manager/order/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/order/edit"
     * для редактирование заказа с уникальным
     * кодом,который совпадает с параметром
     * id, или перенаправляет по запросу
     * "/managers/orders", если этот заказ
     * уже обработал другой менеджер.
     * URL запроса "/admin/order/edit/{id}",
     * метод GET.
     *
     * @param id           Код заказа, который
     *                     нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditOrderPage(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        final Order order = this.orderService.get(id);
        if ((
                order.getManager() == null
        ) || (
                order.getManager()
                        .equals(
                                this.userService.getAuthenticatedUser()
                        )
        )) {
            modelAndView.addObject(
                    "order", order);
            modelAndView.addObject(
                    "sale_positions",
                    order.getSalePositions()
            );
            modelAndView.addObject(
                    "order_price",
                    order.getPrice()
            );
            modelAndView.addObject(
                    "statuses",
                    this.statusService.getAll()
            );
            modelAndView.addObject(
                    "auth_user",
                    this.userService.getAuthenticatedUser()
            );
            modelAndView.setViewName("manager/order/edit");
        } else {
            modelAndView.setViewName("redirect:/managers/order/all");
        }
        return modelAndView;
    }

    /**
     * Обновляет заказ по входящим параметрам
     * и перенаправляет по запросу
     * "/admin/order/view/{id}".
     * URL запроса "/admin/order/update",
     * метод POST.
     *
     * @param id           Код заказа для обновления.
     * @param managerId    Код менеджера
     *                     или администратора,
     *                     который обработал
     *                     заказ в последний раз.
     * @param number       Номер заказа.
     * @param statusId     Код статуса выполнения
     *                     заказа.
     * @param name         Имя клиента,
     *                     оформивший заказ.
     * @param email        Электронная почта клиента.
     * @param phone        Номер телефона клиента.
     * @param address      Адрес доставки заказа.
     * @param details      Детали доставки заказа.
     * @param description  Описание заказа.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updateOrder(
            @RequestParam final long id,
            @RequestParam(value = "auth_user") final long managerId,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "status") final long statusId,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "shipping-address") final String address,
            @RequestParam(value = "shipping-details") final String details,
            @RequestParam final String description,
            final ModelAndView modelAndView
    ) {
        final Order order = this.orderService.get(id);
        if ((
                order.getManager() == null
        ) || (
                order.getManager() == this.userService
                        .getAuthenticatedUser()
        )) {
            final User client = order.getClient();
            client.setName(name);
            client.setEmail(email);
            client.setPhone(phone);
            final Status status = this.statusService.get(statusId);
            User manager = null;
            if (
                    !status.equals(
                            this.statusService.getDefault()
                    )
                    ) {
                manager = this.userService.get(managerId);
            }
            order.initialize(
                    number,
                    new Date(),
                    address,
                    details,
                    description,
                    status,
                    client,
                    manager
            );
            this.orderService.update(order);
        }
        modelAndView.setViewName("redirect:/managers/order/view/" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение
     * WrongInformationException, если
     * обратится по запросу
     * "/admin/order/update" методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/admin/order/update",
            method = RequestMethod.GET
    )
    public void updateOrder()
            throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/admin/order/update\" is not supported!"
        );
    }
}
