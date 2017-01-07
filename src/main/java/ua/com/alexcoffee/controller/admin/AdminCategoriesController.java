package ua.com.alexcoffee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Photo;
import ua.com.alexcoffee.service.interfaces.CategoryService;
import ua.com.alexcoffee.service.interfaces.PhotoService;
import ua.com.alexcoffee.service.interfaces.UserService;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс-контроллер страниц управления
 * категориями. К даному контроллеру
 * и соответствующим страницам могут
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
 * @see Category
 * @see CategoryService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdminCategoriesController {
    /**
     * Объект сервиса для работы
     * с категориями товаров.
     */
    private final CategoryService categoryService;

    /**
     * Объект сервиса для работы
     * с изображениями категорий.
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
     * категорий.
     * Помечен аннотацией @Autowired,
     * которая позволит Spring автоматически
     * инициализировать объекты.
     *
     * @param categoryService Объект сервиса для работы
     *                        с категориями товаров.
     * @param photoService    Объект сервиса для работы
     *                        с изображениями категорий.
     * @param userService     Объект сервиса для работы
     *                        с пользователями.
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
     * Возвращает все категории товаров
     * на страницу "admin/category/all".
     * URL запроса "/admin/categories",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView viewAllCategories(final ModelAndView modelAndView) {
        modelAndView.addObject(
                "categories",
                this.categoryService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/category/all");
        return modelAndView;
    }

    /**
     * Возвращает категорию с уникальным
     * кодом id на страницу "admin/category/one".
     * URL запроса "/admin/view_category_{id}",
     * метод GET.
     *
     * @param id           Код категории,
     *                     которою нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/view_category_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView viewCategory(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "category",
                this.categoryService.get(id)
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/category/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/category/add"
     * для добавления новой категории.
     * URL запроса "/admin/add_category",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/add_category",
            method = RequestMethod.GET
    )
    public ModelAndView getAddCategoryPage(
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "photos",
                this.photoService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/category/add");
        return modelAndView;
    }

    /**
     * Сохраняет новую категорию по входящим
     * параметрам и перенаправляет по запросу
     * "/admin/categories".
     * URL запроса "/admin/save_category",
     * метод POST.
     *
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoTitle   Название изображения
     *                     категории.
     * @param photoFile    Файл-изображение для
     *                     сохранения в файловой
     *                     системе.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/save_category",
            method = RequestMethod.POST
    )
    public ModelAndView saveCategory(
            @RequestParam final String title,
            @RequestParam final String url,
            @RequestParam final String description,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile,
            final ModelAndView modelAndView
    ) {
        final Photo photo = new Photo(
                photoTitle,
                (photoFile != null)
                        ? photoFile.getOriginalFilename()
                        : null,
                null
        );
        final Category category = new Category(
                title, url, description, photo
        );
        this.categoryService.add(category);
        this.photoService.saveFile(photoFile);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException,
     * если обратится по запросу "/save_category"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/save_category",
            method = RequestMethod.GET
    )
    public void saveCategory()
            throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/save_category\" is not supported!"
        );
    }

    /**
     * Возвращает страницу "admin/category/edit"
     * для редактирование категории с уникальным кодом,
     * который совпадает с параметром id.
     * URL запроса "/admin/edit_category_{id}",
     * метод GET.
     *
     * @param id           Код категории,
     *                     которую нужно
     *                     отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/edit_category_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getEditCategoryPage(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "category",
                this.categoryService.get(id)
        );
        modelAndView.addObject(
                "photos",
                this.photoService.getAll()
        );
        modelAndView.addObject(
                "auth_user",
                this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/category/edit");
        return modelAndView;
    }

    /**
     * Обновляет категорию по входящим
     * параметрам и перенаправляет
     * по запросу "/admin/view_category_{id}".
     * URL запроса "/admin/update_category",
     * метод POST.
     *
     * @param id           Код категории для обновления.
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoId      Код изображения категории.
     * @param photoTitle   Название изображения.
     * @param photoFile    Файл-изображение для сохранения
     *                     в файловой системе.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/update_category",
            method = RequestMethod.POST
    )
    public ModelAndView updateCategory(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "photo_id") final long photoId,
            @RequestParam(value = "photo_title") final String photoTitle,
            @RequestParam(value = "photo") final MultipartFile photoFile,
            final ModelAndView modelAndView
    ) {
        final Photo photo = this.photoService.get(photoId);
        photo.setTitle(photoTitle);
        final String photoLinkShort = (
                photoFile == null
        ) || (
                isBlank(
                        photoFile.getOriginalFilename()
                )
        ) ? photo.getPhotoLinkShort()
                : photoFile.getOriginalFilename();
        photo.setPhotoLinkShort(photoLinkShort);
        final Category category = this.categoryService.get(id);
        category.initialize(
                title,
                url,
                description,
                photo
        );
        this.categoryService.update(category);
        this.photoService.saveFile(photoFile);
        modelAndView.setViewName("redirect:/admin/view_category_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException,
     * если обратится по запросу "/update_category"
     * методом GET.
     *
     * @throws WrongInformationException Бросает исключение,
     *                                   если обратится к
     *                                   этому методу GET.
     */
    @RequestMapping(
            value = "/update_category",
            method = RequestMethod.GET
    )
    public void updateCategory()
            throws WrongInformationException {
        throw new WrongInformationException(
                "GET method in \"/update_category\" is not supported!"
        );
    }

    /**
     * Удаляет категорию с уникальным кодом,
     * который совпадает с входящим
     * параметром id, и перенаправляет
     * по запросу "/admin/categories".
     * URL запроса "/delete_category_{id}",
     * метод GET.
     *
     * @param id           Код категории,
     *                     которою нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_category_{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteCategory(
            @PathVariable(value = "id") final long id,
            final ModelAndView modelAndView
    ) {
        this.categoryService.remove(id);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    /**
     * Удаляет все категории и перенаправляет
     * по запросу "/admin/categories".
     * URL запроса "/delete_all_categories",
     * метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(
            value = "/delete_all_categories",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllCategories(
            final ModelAndView modelAndView
    ) {
        this.categoryService.removeAll();
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }
}
