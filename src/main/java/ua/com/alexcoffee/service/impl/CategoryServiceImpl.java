package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.model.category.Category;
import ua.com.alexcoffee.repository.CategoryRepository;
import ua.com.alexcoffee.service.interfaces.CategoryService;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNull;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Category}
 * в базе данных интерфейса {@link CategoryService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainServiceImpl
 * @see CategoryService
 * @see Category
 * @see CategoryRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class CategoryServiceImpl extends MainServiceImpl<Category> implements CategoryService {
    /**
     * Реализация интерфейса {@link CategoryRepository}
     * для работы категорий с базой данных.
     */
    private final CategoryRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link CategoryRepository}
     *                   для работы категорий с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(final CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     * Режим только для чтения.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     * @throws IllegalArgumentException Бросает исключение, если пустой входной
     *                                  параметр url.
     * @throws NullPointerException     Бросает исключение, если не найдена категория
     *                                  с входящим параметром url.
     */
    @Override
    @Transactional(readOnly = true)
    public Category get(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No category URL!");
        }
        final Category category = this.repository.findByUrl(url);
        if (isNull(category)) {
            throw new NullPointerException("Can't find category by url " + url + "!");
        }
        return category;
    }

    /**
     * Удаляет категрию из базы даных, у которого совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    @Override
    @Transactional
    public void remove(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }
}
