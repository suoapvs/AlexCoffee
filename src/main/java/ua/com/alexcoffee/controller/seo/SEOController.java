package ua.com.alexcoffee.controller.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.service.interfaces.CategoryService;
import ua.com.alexcoffee.service.interfaces.ProductService;

/**
 * Класс-контроллер для настройки поисковой оптимизации (SEO).
 * Аннотация @Controller служит для сообщения Spring'у о том, что
 * данный класс является bean'ом и его необходимо подгрузить при
 * старте приложения.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class SEOController {

    /**
     * Объект сервиса для работы с товарами.
     */
    private final ProductService productService;

    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Конструктор для инициализации основных переменных SEO контроллера.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param productService  Объект сервиса для работы с товарами.
     * @param categoryService Объект сервиса для работы с категориями товаров.
     */
    @Autowired
    public SEOController(
            final ProductService productService,
            final CategoryService categoryService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * Возвращает информацию для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода
     * в контроллере был выведен непосредственно в тело ответа на запрос,
     * а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о сайте для поисковых систем.
     */
    @ResponseBody
    @RequestMapping(
            value = {"/robots.txt", "/robots"},
            produces = "text/plain"
    )
    public ModelAndView getRobotsTxt() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seo/robots");
        return modelAndView;
    }

    /**
     * Возвращает файл sitemap.xml для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода в
     * контроллере был выведен непосредственно в тело ответа на запрос,
     * а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о ссылках на сайте для поисковых систем.
     */
    @ResponseBody
    @RequestMapping(
            value = {"/sitemap.xml", "/sitemap"},
            produces = "application/xml"
    )
    public ModelAndView getSiteMapXml() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", this.productService.getAll());
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.setViewName("seo/sitemap");
        return modelAndView;
    }
}
