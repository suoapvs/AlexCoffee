package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.service.interfaces.RoleService;

import java.util.Collection;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.*;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link Role}
 * в базе данных интерфейса {@link RoleService}, наследует родительский
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
 * @see RoleService
 * @see Role
 * @see RoleRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class RoleServiceImpl extends MainServiceImpl<Role> implements RoleService {
    /**
     * Реализация интерфейса {@link RoleRepository}
     * для работы ролей пользователей с базой данных.
     */
    private final RoleRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link RoleRepository}
     *                   для работы ролей пользователей с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public RoleServiceImpl(final RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Добавляет роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли для добавления.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр title.
     * @throws DuplicateException       Бросает исключение,
     *                                  если в БД уже есть такой объект.
     */
    @Override
    @Transactional
    public void add(
            final RoleEnum title,
            final String description
    ) throws IllegalArgumentException, DuplicateException {
        if (isNull(title)) {
            throw new IllegalArgumentException("No role enum (title)!");
        }
        final Role savingRole = this.repository.findByTitle(title);
        if (isNotNull(savingRole)) {
            throw new DuplicateException("Duplicate role with title  " + title + "!");
        }
        final Role role = new Role(title, description);
        add(role);
    }

    /**
     * Возвращает роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     * Режим только для чтения.
     *
     * @param title Название роли для возврата.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр title.
     * @throws NullPointerException     Бросает исключение,
     *                                  если не найденая рось с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Role get(final RoleEnum title) throws IllegalArgumentException, NullPointerException {
        if (isNull(title)) {
            throw new IllegalArgumentException("No role enum (title)!");
        }
        final Role role = this.repository.findByTitle(title);
        if (isNull(role)) {
            throw new NullPointerException("Can't find role by title " + title + "!");
        }
        return role;
    }

    /**
     * Возвращает роль администратора.
     * Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль администратора.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getAdministrator() {
        return get(RoleEnum.ADMIN);
    }

    /**
     * Возвращает роль менеджера.
     * Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль менеджера.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getManager() {
        return get(RoleEnum.MANAGER);
    }

    /**
     * Возвращает роль по-умолчанию.
     * Режим только для чтения.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     * @throws NullPointerException Бросает исключение,
     *                              если не найдена роль по-умолчание.
     */
    @Override
    @Transactional(readOnly = true)
    public Role getDefault() throws NullPointerException {
        final Role role = this.repository.findOne((long) 1);
        if (isNull(role)) {
            throw new NullPointerException("Can't find default role!");
        }
        return role;
    }

    /**
     * Возвращает список ролей персонала сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} -
     * список ролей персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Role> getPersonnel() {
        final Collection<Role> roles = this.repository.findAll();
        if (isNotEmpty(roles)) {
            final Role defaultRole = getDefault();
            roles.remove(defaultRole);
        }
        return roles;
    }

    /**
     * Удаляет роль по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    @Override
    @Transactional
    public void remove(final RoleEnum title) {
        if (isNotNull(title)) {
            this.repository.deleteByTitle(title);
        }
    }
}
