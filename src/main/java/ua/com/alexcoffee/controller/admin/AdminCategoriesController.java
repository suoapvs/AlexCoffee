package ua.com.alexcoffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.model.category.CategoryBuilder;
import ua.com.alexcoffee.model.photo.Photo;
import ua.com.alexcoffee.model.photo.PhotoBuilder;
import ua.com.alexcoffee.service.interfaces.CategoryService;
import ua.com.alexcoffee.service.interfaces.PhotoService;
import ua.com.alexcoffee.service.interfaces.UserService;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс-контроллер страниц управления категориями. К даному контроллеру
 * и соответствующим страницам могут бращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс является
 * bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin/category") сообщаем, что данный контроллер
 * будет обрабатывать запросы, URI которых начинается с "/admin/category".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является типом
 * {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Category
 * @see CategoryService
 */
@Controller
@RequestMapping(value = "/admin/category")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public final class AdminCategoriesController {
    /**
     * Объект сервиса для работы с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Объект сервиса для работы с изображениями категорий.
     */
    private final PhotoService photoService;

    /**
     * Объект сервиса для работы с пользователями.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации основных переменных контроллера категорий.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param categoryService Объект сервиса для работы с категориями товаров.
     * @param photoService    Объект сервиса для работы с изображениями категорий.
     * @param userService     Объект сервиса для работы с пользователями.
     */
    @Autowired
    public AdminCategoriesController(
            final CategoryService categoryService,
            final PhotoService photoService,
            final UserService userService
    ) {
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }

    /**
     * Возвращает все категории товаров на страницу "admin/category/all".
     * URL запроса {"/admin/category", "/admin/category/", "/admin/category/all"},
     * метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView viewAllCategories() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/all");
        return modelAndView;
    }

    /**
     * Возвращает категорию с уникальным кодом id на страницу "admin/category/one".
     * URL запроса "/admin/category/view/{id}", метод GET.
     *
     * @param id           Код категории, которою нужно вернуть.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewCategory(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/category/add"
     * для добавления новой категории.
     * URL запроса "/admin/category/add",
     * метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public ModelAndView getAddCategoryPage() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/add");
        return modelAndView;
    }

    /**
     * Сохраняет новую категорию по входящим параметрам и перенаправляет по запросу
     * "/admin/category/all".
     * URL запроса "/admin/category/save", метод POST.
     *
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoTitle   Название изображения категории.
     * @param photoFile    Файл-изображение для сохранения в файловой системе.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    public String saveCategory(
            @RequestParam final String title,
            @RequestParam final String url,
            @RequestParam final String description,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile
    ) {
        final CategoryBuilder categoryBuilder = Category.getBuilder();
        categoryBuilder.addTitle(title).addUrl(url).addDescription(description);

        final PhotoBuilder photoBuilder = Photo.getBuilder();
        photoBuilder.addTitle(photoTitle);
        if (isNotNull(photoFile)) {
            photoBuilder.addSmallUrl(photoFile.getOriginalFilename());
        }
        final Photo photo = photoBuilder.build();
        categoryBuilder.addPhoto(photo);

        final Category category = categoryBuilder.build();
        this.categoryService.add(category);
        this.photoService.saveFile(photoFile);
        return "redirect:/admin/category/all";
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится по запросу
     * "/admin/category/save" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.GET
    )
    public void saveCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/category/save\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/category/edit" для редактирование категории
     * с уникальным кодом, который совпадает с параметром id.
     * URL запроса "/admin/category/edit/{id}", метод GET.
     *
     * @param id           Код категории, которую нужно отредактировать.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditCategoryPage(@PathVariable(value = "id") final long id) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("category/admin/edit");
        return modelAndView;
    }

    /**
     * Обновляет категорию по входящим параметрам и перенаправляет
     * по запросу "/admin/category/view/{id}".
     * URL запроса "/admin/category/update", метод POST.
     *
     * @param id           Код категории для обновления.
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoId      Код изображения категории.
     * @param photoTitle   Название изображения.
     * @param photoFile    Файл-изображение для сохранения в файловой системе.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateCategory(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "photo_id") final long photoId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile
    ) {
        final Photo photo = this.photoService.get(photoId);
        photo.setTitle(photoTitle);
        if (isNotNull(photoFile) && isNotEmpty(photoFile.getOriginalFilename())) {
            final String smallIUrl = photoFile.getOriginalFilename();
            photo.setSmallUrl(smallIUrl);
        }
        final Category category = this.categoryService.get(id);
        category.setTitle(title);
        category.setUrl(url);
        category.setDescription(description);
        category.setPhoto(photo);
        this.categoryService.update(category);
        this.photoService.saveFile(photoFile);
        return "redirect:/admin/view/" + id;
    }

    /**
     * Возвращает исключение IllegalMappingException, если обратится
     * по запросу "/admin/category/update" методом GET.
     *
     * @throws IllegalMappingException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/category/update\" is not supported!"
        );
    }

    /**
     * Удаляет категорию с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/category/all".
     * URL запроса "/admin/category/delete/{id}", метод GET.
     *
     * @param id           Код категории, которою нужно удалить.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteCategory(@PathVariable(value = "id") final long id) {
        this.categoryService.remove(id);
        return "redirect:/admin/category/all";
    }

    /**
     * Удаляет все категории и перенаправляет по запросу "/admin/category/all".
     * URL запроса "/admin/category/delete_all", метод GET.
     *
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_all",
            method = RequestMethod.GET
    )
    public String deleteAllCategories() {
        this.categoryService.removeAll();
        return "redirect:/admin/category/all";
    }
}
