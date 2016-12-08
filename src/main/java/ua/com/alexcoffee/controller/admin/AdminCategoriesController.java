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
import ua.com.alexcoffee.service.CategoryService;
import ua.com.alexcoffee.service.PhotoService;
import ua.com.alexcoffee.service.UserService;

/**
 * Класс-контроллер страниц управления категориями. К даному контроллеру и соответствующим
 * страницам могут обращатсья пользователи, имеющие роль-админстратор.
 * Аннотация @Controller служит для сообщения Spring'у о том, что данный класс
 * является bean'ом и его необходимо подгрузить при старте приложения.
 * Аннотацией @RequestMapping(value = "/admin") сообщаем, что данный контроллер
 * будет обрабатывать запрос, URI которого "/admin".
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 *
 * @author Yurii Salimov
 * @see Category
 * @see CategoryService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.alexcoffee.service")
public class AdminCategoriesController {
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
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     *
     * @param categoryService Объект сервиса для работы с категориями товаров.
     * @param photoService    Объект сервиса для работы с изображениями категорий.
     * @param userService     Объект сервиса для работы с пользователями.
     */
    @Autowired
    public AdminCategoriesController(CategoryService categoryService, PhotoService photoService, UserService userService) {
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }

    /**
     * Возвращает все категории товаров на страницу "admin/category/all".
     * URL запроса "/admin/categories", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView viewAllCategories(ModelAndView modelAndView) {
        modelAndView.addObject("categories", this.categoryService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser()); // Авторизированый пользователь.
        modelAndView.setViewName("admin/category/all");
        return modelAndView;
    }

    /**
     * Возвращает категорию с уникальным кодом id на страницу "admin/category/one".
     * URL запроса "/admin/view_category_{id}", метод GET.
     *
     * @param id           Код категории, которою нужно вернуть.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/view_category_{id}", method = RequestMethod.GET)
    public ModelAndView viewCategory(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/category/one");
        return modelAndView;
    }

    /**
     * Возвращает страницу "admin/category/add" для добавления новой категории.
     * URL запроса "/admin/add_category", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/add_category", method = RequestMethod.GET)
    public ModelAndView getAddCategoryPage(ModelAndView modelAndView) {
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/category/add");
        return modelAndView;
    }

    /**
     * Сохраняет новую категорию по входящим параметрам и перенаправляет по запросу "/admin/categories".
     * URL запроса "/admin/save_category", метод POST.
     *
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoTitle   Название изображения категории.
     * @param photoFile    Файл-изображение для сохранения в файловой системе.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/save_category", method = RequestMethod.POST)
    public ModelAndView saveCategory(@RequestParam String title,
                                     @RequestParam String url,
                                     @RequestParam String description,
                                     @RequestParam(value = "photo_title") String photoTitle,
                                     @RequestParam(value = "photo") MultipartFile photoFile,
                                     ModelAndView modelAndView) {
        Photo photo = new Photo(photoTitle, photoFile == null ? null : photoFile.getOriginalFilename(), null);
        Category category = new Category(title, url, description, photo);
        this.categoryService.add(category);
        this.photoService.saveFile(photoFile);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/save_category" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/save_category", method = RequestMethod.GET)
    public void saveCategory() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/save_category\" is not supported!");
    }

    /**
     * Возвращает страницу "admin/category/edit" для редактирование категории с уникальным кодом,
     * который совпадает с параметром id. URL запроса "/admin/edit_category_{id}", метод GET.
     *
     * @param id           Код категории, которую нужно отредактировать.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/edit_category_{id}", method = RequestMethod.GET)
    public ModelAndView getEditCategoryPage(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        modelAndView.addObject("category", this.categoryService.get(id));
        modelAndView.addObject("photos", this.photoService.getAll());
        modelAndView.addObject("auth_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/category/edit");
        return modelAndView;
    }

    /**
     * Обновляет категорию по входящим параметрам и перенаправляет по запросу "/admin/view_category_{id}".
     * URL запроса "/admin/update_category", метод POST.
     *
     * @param id           Код категории для обновления.
     * @param title        Название категории.
     * @param url          URL категории.
     * @param description  Описание категории.
     * @param photoId      Код изображения категории.
     * @param photoTitle   Название изображения.
     * @param photoFile    Файл-изображение для сохранения в файловой системе.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/update_category", method = RequestMethod.POST)
    public ModelAndView updateCategory(@RequestParam long id,
                                       @RequestParam String title,
                                       @RequestParam String url,
                                       @RequestParam String description,
                                       @RequestParam(value = "photo_id") long photoId,
                                       @RequestParam(value = "photo_title") String photoTitle,
                                       @RequestParam(value = "photo") MultipartFile photoFile,
                                       ModelAndView modelAndView) {
        Photo photo = this.photoService.get(photoId);
        photo.setTitle(photoTitle);
        String photoLinkShort = photoFile == null || photoFile.getOriginalFilename().isEmpty() ? photo.getPhotoLinkShort()
                : photoFile.getOriginalFilename();
        photo.setPhotoLinkShort(photoLinkShort);

        Category category = this.categoryService.get(id);
        category.initialize(title, url, description, photo);
        this.categoryService.update(category);

        this.photoService.saveFile(photoFile);

        modelAndView.setViewName("redirect:/admin/view_category_" + id);
        return modelAndView;
    }

    /**
     * Возвращает исключение WrongInformationException, если обратится по запросу "/update_category" методом GET.
     *
     * @throws WrongInformationException Бросает исключение, если обратится к этому методу GET.
     */
    @RequestMapping(value = "/update_category", method = RequestMethod.GET)
    public void updateCategory() throws WrongInformationException {
        throw new WrongInformationException("GET method in \"/update_category\" is not supported!");
    }

    /**
     * Удаляет категорию с уникальным кодом, который совпадает с входящим параметром id,
     * и перенаправляет по запросу "/admin/categories".
     * URL запроса "/delete_category_{id}", метод GET.
     *
     * @param id           Код категории, которою нужно удалить.
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_category_{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
        this.categoryService.remove(id);
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }

    /**
     * Удаляет все категории и перенаправляет по запросу "/admin/categories".
     * URL запроса "/delete_all_categories", метод GET.
     *
     * @param modelAndView Объект класса {@link ModelAndView}.
     * @return Объект класса {@link ModelAndView}.
     */
    @RequestMapping(value = "/delete_all_categories", method = RequestMethod.GET)
    public ModelAndView deleteAllCategories(ModelAndView modelAndView) {
        this.categoryService.removeAll();
        modelAndView.setViewName("redirect:/admin/categories");
        return modelAndView;
    }
}
