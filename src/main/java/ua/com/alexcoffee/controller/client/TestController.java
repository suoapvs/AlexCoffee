package ua.com.alexcoffee.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.service.interfaces.ShoppingCartService;

/**
 * Класс-контроллер тестовых страниц. К даному контроллеру и соответствующим
 * страницам могут обращатсья все пользователи, независимо от ихних ролей.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Методы класса работают с объектом, возвращенным handleRequest методом,
 * является типом {@link ModelAndView}, который агрегирует все параметры модели и
 * имя отображения. Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class TestController {

    /**
     * Объект сервиса для работы с торговой корзиной.
     */
    private final ShoppingCartService shoppingCartService;

    /**
     * Конструктор для инициализации основных переменных контроллера главных страниц сайта.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать
     * объекты.
     *
     * @param shoppingCartService Объект сервиса для работы с торговой корзиной.
     */
    @Autowired
    public TestController(final ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Возвращает страцницу "client/test" с тестовым описанием сайта.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET
    )
    public ModelAndView getTestPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cart_size", this.shoppingCartService.getSize());
        modelAndView.setViewName("home/test");
        return modelAndView;
    }
}
