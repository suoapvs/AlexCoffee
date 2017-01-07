package ua.com.alexcoffee.dao.impl;

import org.springframework.context.annotation.ComponentScan;
import ua.com.alexcoffee.dao.interfaces.RoleDAO;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Класс реализует методы доступа объектов
 * класса {@link Role} в базе данных интерфейса
 * {@link RoleDAO}, наследует родительский
 * абстрактній класс {@link DataDAOImpl}, в
 * котором реализованы основные методы.
 * Для работы методы используют
 * объект-репозиторий интерфейса
 * {@link RoleRepository}. Класс помечена
 * аннотацией @Repository (наследник
 * Spring'овой аннотации @Component).
 * Это позволяет Spring автоматически
 * зарегестрировать компонент в своём
 * контексте для последующей инъекции.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAOImpl
 * @see RoleDAO
 * @see Role
 * @see RoleRepository
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class RoleDAOImpl
        extends DataDAOImpl<Role>
        implements RoleDAO {
    /**
     * Реализация репозитория {@link RoleRepository}
     * для работы ролей пользователей
     * с базой данных.
     */
    private final RoleRepository repository;

    /**
     * Конструктор для инициализации основных
     * переменных. Помечаный
     * аннотацией @Autowired, которая
     * позволит Spring автоматически
     * инициализировать объект.
     *
     * @param repository Реализация репозитория
     *                   {@link RoleRepository}
     *                   для работы ролей
     *                   пользователей
     *                   с базой данных.
     */
    @Autowired
    public RoleDAOImpl(final RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Добавляет роль в базу даных
     * по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    @Override
    public void add(
            final RoleEnum title,
            final String description
    ) {
        this.repository.save(
                new Role(
                        title,
                        description
                )
        );
    }

    /**
     * Возвращает роль из базы даных
     * по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} -
     * роль с уникальным названием.
     */
    @Override
    public Role get(final RoleEnum title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Возвращает из базы даных роль
     * по-умолчанию.
     *
     * @return Объект класса {@link Role} -
     * роль по-умолчание.
     */
    @Override
    public Role getDefault() {
        return this.repository.findOne((long) 1);
    }

    /**
     * Удаляет роль из базы даных по названию,
     * которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    @Override
    public void remove(final RoleEnum title) {
        this.repository.deleteByTitle(title);
    }
}
