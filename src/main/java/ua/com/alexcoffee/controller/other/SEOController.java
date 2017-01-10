package ua.com.alexcoffee.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.service.interfaces.CategoryService;
import ua.com.alexcoffee.service.interfaces.ProductService;

import java.util.List;

/**
 * Класс-контроллер для настройки
 * поисковой оптимизации (SEO).
 * Аннотация @Controller служит для
 * сообщения Spring'у о том, что
 * данный класс является bean'ом и
 * его необходимо подгрузить при
 * старте приложения.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 */
@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class SEOController {

    /**
     * Информация для поисковых систем.
     */
    private final static String ROBOTS_TXT
            = "User-agent: Yandex\n"
            + "Disallow: /admin\n"
            + "Disallow: /manager\n"
            + "Disallow: /login\n"
            + "Disallow: /resources\n"
            + "Host: alexcoffee.com.ua\n\n"
            + "User-agent: Googlebot\n"
            + "Disallow: /admin\n"
            + "Disallow: /manager\n"
            + "Disallow: /login\n"
            + "Disallow: /resources\n\n"
            + "User-agent: *\n"
            + "Crawl-delay: 30\n"
            + "Disallow: /admin\n"
            + "Disallow: /manager\n"
            + "Disallow: /login\n"
            + "Disallow: /resources\n\n"
            + "Sitemap: http://alexcoffee.com.ua/sitemap.xml";

    /**
     * Объект сервиса для работы
     * с товарами.
     */
    private final ProductService productService;

    /**
     * Объект сервиса для работы
     * с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Конструктор для инициализации
     * основных переменных SEO контроллера.
     * Помечен аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param productService  Объект сервиса для работы
     *                        с товарами.
     * @param categoryService Объект сервиса для работы
     *                        с категориями товаров.
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
     * Возвращает информацию для поисковых
     * систем.
     * Аннотация @ResponseBody указывает
     * на то, что результат работы метода
     * в контроллере был выведен
     * непосредственно в тело ответа на
     * запрос, а не послужил адресом
     * перехода и не был помещён как
     * параметр в модель.
     *
     * @return Значение типа String -
     * информация о сайте для поисковых
     * систем.
     */
    @RequestMapping(
            value = {
                    "/robots.txt",
                    "/robots"
            },
            produces = "text/plain"
    )
    @ResponseBody
    public String getRobotsTxt() {
        return ROBOTS_TXT;
    }

    /**
     * Возвращает файл sitemap.xml для поисковых
     * систем.
     * Аннотация @ResponseBody указывает на то,
     * что результат работы метода в
     * контроллере был выведен
     * непосредственно в тело ответа на
     * запрос, а не послужил адресом
     * перехода и не был помещён как
     * параметр в модель.
     *
     * @return Значение типа String - информация
     * о ссылках на сайте для поисковых систем.
     */
    @RequestMapping(
            value = {
                    "/sitemap.xml",
                    "/sitemap"
            },
            produces = "application/xml"
    )
    @ResponseBody
    public String getSiteMapXml() {
        return "<urlset\n"
                + "xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n"
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "xsi:schemaLocation=\""
                + "http://www.sitemaps.org/schemas/sitemap/0.9\n"
                + "http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n"
                + "<url>\n<loc>http://alexcoffee.com.ua/</loc>\n</url>\n"
                + getCategoriesUrls()
                + getProductsUrls()
                + "</urlset>";
    }

    /**
     * Возвращает строку с URL
     * всех категорий.
     *
     * @return Строку с URL
     * всех категорий.
     */
    private String getCategoriesUrls() {
        final StringBuilder sb = new StringBuilder();
        final List<Category> categories = this.categoryService.getAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                sb.append("<url>\n<loc>http://alexcoffee.com.ua/category_")
                        .append(category.getUrl())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }

    /**
     * Возвращает строку с URL
     * всех товаров.
     *
     * @return Строку с URL
     * всех товаров.
     */
    private String getProductsUrls() {
        final StringBuilder sb = new StringBuilder();
        final List<Product> products = this.productService.getAll();
        if (!products.isEmpty()) {
            sb.append("<url>\n<loc>")
                    .append("http://alexcoffee.com.ua/all_products")
                    .append("</loc>\n</url>\n");
            for (Product product : products) {
                sb.append("<url>\n<loc>http://alexcoffee.com.ua/product_")
                        .append(product.getUrl())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }
}
