package ua.com.alexcoffee.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.service.ProductService;

import java.util.List;

/**
 * Класс-контроллер для настройки поисковой оптимизации (SEO).
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 *
 * @author Yurii Salimov
 */
@Controller
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class SEOController {

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
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     *
     * @param productService  Объект сервиса для работы с товарами.
     * @param categoryService Объект сервиса для работы с категориями товаров.
     */
    @Autowired
    public SEOController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * Возвращает информацию для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода в контроллере был выведен
     * непосредственно в тело ответа на запрос, а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о сайте для поисковых систем.
     */
    @RequestMapping(value = "/robots.txt", produces = {"text/plain"})
    @ResponseBody
    public String getRobotsTxt() {
        return "User-agent: Yandex\n" +
                "Disallow: /admin\n" +
                "Disallow: /manager\n" +
                "Disallow: /login\n" +
                "Disallow: /resources\n" +
                "Host: alexcoffee.com.ua\n" +
                "\n" +
                "User-agent: Googlebot\n" +
                "Disallow: /admin\n" +
                "Disallow: /manager\n" +
                "Disallow: /login\n" +
                "Disallow: /resources\n" +
                "\n" +
                "User-agent: *\n" +
                "Crawl-delay: 30\n" +
                "Disallow: /admin\n" +
                "Disallow: /manager\n" +
                "Disallow: /login\n" +
                "Disallow: /resources\n" +
                "\n" +
                "Sitemap: http://alexcoffee.com.ua/sitemap.xml";
    }

    /**
     * Возвращает файл sitemap.xml для поисковых систем.
     * Аннотация @ResponseBody указывает на то, что результат работы метода в контроллере был выведен
     * непосредственно в тело ответа на запрос, а не послужил адресом перехода и не был помещён как параметр в модель.
     *
     * @return Значение типа String - информация о ссылках на сайте для поисковых систем.
     */
    @RequestMapping(value = "/sitemap.xml", produces = {"application/xml"})
    @ResponseBody
    public String getSiteMapXml() {
        StringBuilder sitemap = new StringBuilder();
        sitemap.append("<urlset\n")
                .append("      xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n")
                .append("      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n")
                .append("      xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n")
                .append("            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">\n")
                .append("<url>\n")
                .append("  <loc>http://alexcoffee.com.ua/</loc>\n")
                .append("</url>\n");

        // Ссылки на категории товаров.
        List<Category> categories = this.categoryService.getAll();
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://alexcoffee.com.ua/category_").append(category.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        // Ссылки на товары.
        List<Product> products = this.productService.getAll();
        if (!products.isEmpty()) {
            sitemap.append("<url>\n")
                    .append("  <loc>http://alexcoffee.com.ua/all_products</loc>\n")
                    .append("</url>\n");

            for (Product product : products) {
                sitemap.append("<url>\n")
                        .append("  <loc>http://alexcoffee.com.ua/product_").append(product.getUrl()).append("</loc>\n")
                        .append("</url>\n");
            }
        }

        sitemap.append("</urlset>");

        return sitemap.toString();
    }
}
