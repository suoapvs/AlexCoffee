package ua.com.alexcoffee.dao.impl;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.dao.StatusDAO;
import ua.com.alexcoffee.repository.StatusRepository;
import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов класса {@link Status}
 * в базе данных интерфейса {@link StatusDAO}, наследует родительский
 * абстрактній класс {@link DataDAOImpl}, в котором реализованы
 * основные методы. Для работы методы используют объект-репозиторий
 * интерфейса {@link StatusRepository}.
 * Класс помечена аннотацией @Repository (наследник Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически зарегестрировать компонент в своём контексте
 * для последующей инъекции.
 *
 * @author Yurii Salimov
 * @see DataDAOImpl
 * @see StatusDAO
 * @see Status
 * @see StatusRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public class StatusDAOImpl extends DataDAOImpl<Status> implements StatusDAO {
    /**
     * Реализация репозитория {@link StatusRepository} для работы статусов заказов с базой данных.
     */
    private final StatusRepository repository;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация репозитория {@link StatusRepository}
     *                   для работы статусов заказов с базой данных.
     */
    @Autowired
    public StatusDAOImpl(StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Добавляет статус в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    @Override
    public void add(StatusEnum title, String description) {
        this.repository.save(new Status(title, description));
    }

    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Status} - статус с уникальным названием.
     */
    @Override
    public Status get(StatusEnum title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Возвращает из базы даных статус по-умолчанию.
     *
     * @return Объект класса {@link Status} - статус по-умолчание.
     */
    @Override
    public Status getDefault() {
        return this.repository.findOne((long) 1);
    }

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    @Override
    public void remove(StatusEnum title) {
        this.repository.deleteByTitle(title);
    }
}
