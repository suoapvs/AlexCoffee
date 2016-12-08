package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.CategoryDAO;
import ua.com.alexcoffee.dao.ProductDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Category;
import ua.com.alexcoffee.model.Product;
import ua.com.alexcoffee.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Product}
 * в базе данных интерфейса {@link ProductService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov
 * @see MainServiceImpl
 * @see ProductService
 * @see ProductDAO
 * @see Product
 * @see Category
 * @see CategoryDAO
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.dao")
public class ProductServiceImpl extends MainServiceImpl<Product> implements ProductService {
    /**
     * Реализация интерфейса {@link ProductDAO} для работы с товаров базой данных.
     */
    private final ProductDAO productDAO;

    /**
     * Реализация интерфейса {@link CategoryDAO} для работы с категорий базой данных.
     */
    private final CategoryDAO categoryDAO;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param productDAO  Реализация интерфейса {@link ProductDAO} для работы с товаров базой данных.
     * @param categoryDAO Реализация интерфейса {@link CategoryDAO} для работы с категорий базой данных.
     */
    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        super(productDAO);
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    /**
     * Возвращает товар, у которого совпадает параметр url. Режим только для чтения.
     *
     * @param url URL товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным url полем.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     * @throws BadRequestException       Бросает исключение, если не найден товар с входящим параметром url.
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }
        Product product = this.productDAO.getByUrl(url);
        if (product == null) {
            throw new BadRequestException("Can't find product by url " + url + "!");
        }
        return product;
    }

    /**
     * Возвращает товар, у которого совпадает уникальный
     * артикль с значением входящего параметра. Режим только для чтения.
     *
     * @param article Артикль товара для возврата.
     * @return Объект класса {@link Product} - товара с уникальным артиклем.
     * @throws BadRequestException Бросает исключение, если не найден
     *                             товар с входящим параметром article.
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByArticle(int article) throws BadRequestException {
        Product product = this.productDAO.getByArticle(article);
        if (product == null) {
            throw new BadRequestException("Can't find product by article " + article + "!");
        }
        return product;
    }

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным URL - входным параметром. Режим только для чтения.
     *
     * @param url URL категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     * @throws BadRequestException       Бросает исключение, если не найдена категория с входящим параметром url.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        Category category = this.categoryDAO.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        return this.productDAO.getListByCategoryId(category.getId());
    }

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром. Режим только для чтения.
     *
     * @param id Код категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр id.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getByCategoryId(Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No category id!");
        }
        return this.productDAO.getListByCategoryId(id);
    }

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     *
     * @param size Количество товаров в списке.
     * @param id   Код категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long id) {
        return getRandomByCategoryId(size, id, -1L);
    }

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром. Режим только для чтения.
     *
     * @param size               Количество товаров в списке.
     * @param categoryId         Код категории, товары которой будут возвращены.
     * @param differentProductId Код товара, который точно не будет включен в список.
     * @return Объект типа {@link List} - список товаров.
     * @throws WrongInformationException Бросает исключение, если несли пустой хотя бы одис с параметров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(int size, Long categoryId, Long differentProductId) throws WrongInformationException {
        if (categoryId == null || differentProductId == null) {
            throw new WrongInformationException("No category or product id!");
        }
        List<Product> products = this.productDAO.getListByCategoryId(categoryId);
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        products.remove(this.productDAO.get(differentProductId));
        return getShuffleSubList(products, 0, size);
    }

    /**
     * Возвращает список рандомных товаров. Режим только для чтения.
     *
     * @param size Количество товаров в списке.
     * @return Объект типа {@link List} - список товаров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandom(int size) {
        List<Product> products = this.productDAO.getAll();
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return getShuffleSubList(products, 0, size);
    }

    /**
     * Удаляет товар, у которого совпадает параметр url.
     *
     * @param url URL товара для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     */
    @Override
    @Transactional
    public void removeByUrl(String url) throws WrongInformationException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No product URL!");
        }
        this.productDAO.removeByUrl(url);
    }

    /**
     * Удаляет товар, у которого совпадает параметр article.
     *
     * @param article артикль товара для удаления.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     */
    @Override
    @Transactional
    public void removeByArticle(int article) {
        this.productDAO.removeByArticle(article);
    }

    /**
     * Удаляет товары, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     *
     * @param url URL категории, товары которой будут удалены.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр url.
     * @throws BadRequestException       Бросает исключение, если не найдена категория с входящим параметром url.
     */
    @Override
    @Transactional
    public void removeByCategoryUrl(String url) throws WrongInformationException, BadRequestException {
        if (url == null || url.isEmpty()) {
            throw new WrongInformationException("No category URL!");
        }
        Category category = this.categoryDAO.get(url);
        if (category == null) {
            throw new BadRequestException("Can't find category by url " + url + "!");
        }
        this.productDAO.removeByCategoryId(category.getId());
    }

    /**
     * Удаляет товары, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     *
     * @param id Код категории, товары котрой будут удалены.
     * @throws WrongInformationException Бросает исключение, если пустой входной параметр id.
     * @throws BadRequestException       Бросает исключение, если не найдена категория с входящим параметром id.
     */
    @Override
    @Transactional
    public void removeByCategoryId(Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        if (this.categoryDAO.get(id) == null) {
            throw new BadRequestException("Can't find category by id " + id + "!");
        }
        this.productDAO.removeByCategoryId(id);
    }

    /**
     * Возвращает список перемешаных товаров начиная с позиции start и заканчиваю позицеей end.
     *
     * @param products Список товаров для обработки.
     * @param start    Начальная позиция выборки товаров из списка.
     * @param end      Конечная позиция выборки товаров из списка.
     * @return Объект типа {@link List} - список перемешаных товаров или пустой лист.
     */
    private static List<Product> getShuffleSubList(List<Product> products, int start, int end) {
        if (products == null || products.isEmpty() || start > products.size() || start > end || start < 0 || end < 0) {
            return new ArrayList<>();
        }
        if (end > products.size()) {
            end = products.size();
        }
        Collections.shuffle(products);
        return products.subList(start, end);
    }
}
