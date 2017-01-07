package ua.com.alexcoffee.controller.admin;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Photo;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.service.interfaces.CategoryService;
import ua.com.alexcoffee.service.interfaces.PhotoService;
import ua.com.alexcoffee.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.service.interfaces.UserService;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс-контроллер страниц управления
 * товарами. К даному контроллеру и
 * соответствующим страницам могут
 * обращатсья пользователи, имеющие
 * роль-админстратор.
 * Аннотация @Controller служит для
 * сообщения Spring'у о том, что данный
 * класс является bean'ом и его необходимо
 * подгрузить при старте приложения.
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
 * @see Product
 * @see ProductService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdminProductsController {
    /**
     * Объект сервиса для работы
     * с товаров.
     */
    private final ProductService productService;

    /**
     * Объект сервиса для работы
     * с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Объект сервиса для работы
     * с изображенями товаров.
     */
    private final PhotoService photoService;

    /**
     * Объект сервиса для работы
     * с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации
     * основных переменных контроллера
     * товаров.
     * Помечен аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param productService  Объект сервисадля работы
     *                        с товаров.
     * @param categoryService Объект сервиса для работы
     *                        с категориями товаров.
     * @param photoService    Объект сервиса для работы
     *                        с изображенями товаров.
     * @param userService     Объект сервиса для работы
     *                        с пользователями.
     */
    @Autowired
    public AdminProductsController(
            final ProductService productService,
            final CategoryService categoryService,
            final PhotoService photoService,
            final UserService userService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }

    /**
     * Возвращает все товары
     * на страницу "admin/product/all".
     * URL запроса "/admin/products",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/products",
            method = RequestMethod.GET
    )
    public ModelAndView viewAllProducts(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "products",
                this.productService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/product/all");
        return modelAndView;
    }

    /**
     * Возвращает товар с уникальным
     * кодом id на страницу "admin/product/one".
     * URL запроса "/admin/view_product_{id}",
     * метод GET.
     *
     * @param id           Код товара,
     *                     который нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view_product_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewProduct(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "product",
                this.productService.get(id)
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/product/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/product/add"
     * для добавления нового товара.
     * URL запроса "/admin/add_product",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/add_product",
            method = RequestMethod.GET
    )
    public ModelAndView getAddProductPage(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "categories",
                this.categoryService.getAll()
        );
        modelAndView.addObject(
                "photos",
                this.photoService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/product/add");
        return modelAndView;
    }

    /**
     * Сохраняет новый товар по входящим
     * параметрам и перенаправляет
     * по запросу "/admin/products".
     * URL запроса "/admin/save_product",
     * метод POST.
     *
     * @param title          Название товара
     * @param url            URL товара.
     * @param parameters     Параметры товара.
     * @param description    Описание товара.
     * @param categoryId     Код категории,
     *                       к которой пренадлежит товар.
     * @param photoTitle     Название изображения товара.
     * @param smallPhotoFile Файл меленького изображения
     *                       для сохранения в файловой системе.
     * @param bigPhotoFile   Файл большго изображения
     *                       для сохранения в файловой системе.
     * @param price          Цена товара.
     * @param modelAndView   Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/save_product",
            method = RequestMethod.POST
    )
    public ModelAndView saveProduct(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "parameters") final String parameters,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "category") final long categoryId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "small_photo") final MultipartFile smallPhotoFile,
            @RequestParam(value = "big_photo") final MultipartFile bigPhotoFile,
            @RequestParam(value = "price") final double price,
            final ModelAndView modelAndView
    ) {
        final Category category = this.categoryService.get(categoryId);
        final Photo photo = new Photo(
                photoTitle,
                (smallPhotoFile != null)
                        ? smallPhotoFile.getOriginalFilename() : null,
                (bigPhotoFile != null)
                        ? bigPhotoFile.getOriginalFilename() : null);
        final Product product = new Product();
        product.initialize(
                title,
                url,
                parameters,
                description,
                category,
                photo,
                price
        );
        this.productService.add(product);
        this.photoService.saveFile(smallPhotoFile);
        this.photoService.saveFile(bigPhotoFile);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException,
     * если обратится по запросу "/save_product"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/save_product",
            method = RequestMethod.GET
    )
    public void saveProduct() throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/save_product\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/product/edit"
     * для редактирование товара с уникальным
     * кодом, который совпадает с параметром id.
     * URL запроса "/admin/edit_product_{id}",
     * метод GET.
     *
     * @param id           Код товара, который
     *                     нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit_product_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditProductPage(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "product",
                this.productService.get(id)
        );
        modelAndView.addObject(
                "categories",
                this.categoryService.getAll()
        );
        modelAndView.addObject(
                "photos",
                this.photoService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/product/edit");
        return modelAndView;
    }

    /**
     * Обновляет товар по входящим параметрам
     * и перенаправляет по запросу
     * "/admin/view_product_{id}".
     * URL запроса "/admin/update_product",
     * метод POST.
     *
     * @param id             Код товара для обновления.
     * @param title          Название товара.
     * @param url            URL товара.
     * @param parameters     Параметры товара.
     * @param description    Описание товара.
     * @param categoryId     Код категории,
     *                       к которой пренадлежит товар.
     * @param photoId        Код изображения товара.
     * @param photoTitle     Название изображения товара.
     * @param smallPhotoFile Файл меленького изображения
     *                       для сохранения в файловой
     *                       системе.
     * @param bigPhotoFile   Файл большго изображения
     *                       для сохранения в файловой
     *                       системе.
     * @param price          Цена товара.
     * @param modelAndView   Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update_product",
            method = RequestMethod.POST
    )
    public ModelAndView updateProduct(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "parameters") final String parameters,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "category") final long categoryId,
            @RequestParam(value = "photo_id") final long photoId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "small_photo") final MultipartFile smallPhotoFile,
            @RequestParam(value = "big_photo") final MultipartFile bigPhotoFile,
            @RequestParam(value = "price") final double price,
            final ModelAndView modelAndView
    ) {
        final Product product = this.productService.get(id);
        final Category category = this.categoryService.get(categoryId);
        final Photo photo = this.photoService.get(photoId);
        final String photoLinkShort = (
                smallPhotoFile == null
        ) || (
                isBlank(
                        smallPhotoFile.getOriginalFilename()
                )
        ) ? photo.getPhotoLinkShort()
                : smallPhotoFile.getOriginalFilename();
        final String photoLinkLong = (
                bigPhotoFile == null
        ) || (
                isBlank(
                        bigPhotoFile.getOriginalFilename()
                )
        ) ? photo.getPhotoLinkLong()
                : bigPhotoFile.getOriginalFilename();
        photo.initialize(
                photoTitle,
                photoLinkShort,
                photoLinkLong
        );
        product.initialize(
                title,
                url,
                parameters,
                description,
                category,
                photo,
                price
        );
        this.productService.update(product);
        this.photoService.saveFile(smallPhotoFile);
        this.photoService.saveFile(bigPhotoFile);
        modelAndView.setViewName("redirect:/admin/view_product_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException,
     * если обратится по запросу "/update_product"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится
     *                                   к этому методу GET.
     */
    @RequestMapping(
            value = "/update_product",
            method = RequestMethod.GET
    )
    public void updateProduct() throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/update_product\" is not supported!"
        );
    }

    /**
     * Удаляет товар с уникальным кодом,
     * который совпадает с входящим
     * параметром id, и перенаправляет
     * по запросу "/admin/products".
     * URL запроса "/admin/delete_product_{id}",
     * метод GET.
     *
     * @param id           Код товара,
     *                     который нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_product_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteProduct(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        this.productService.remove(id);
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }

    /**
     * Удаляет все товары и перенаправляет
     * по запросу "/admin/products".
     * URL запроса "/admin/delete_all_products",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_all_products",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllProducts(
            final ModelAndView modelAndView
    ) {
        this.productService.removeAll();
        modelAndView.setViewName("redirect:/admin/products");
        return modelAndView;
    }
}
