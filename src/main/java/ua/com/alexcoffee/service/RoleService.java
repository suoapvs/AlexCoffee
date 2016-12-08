package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.service.impl.MainServiceImpl;

import java.util.List;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Role}. Расширяет интерфейс {@link MainService}.
 *
 * @author Yurii Salimov
 * @see Role
 * @see MainService
 * @see MainServiceImpl
 */
public interface RoleService extends MainService<Role> {
    /**
     * Добавляет роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title       Название роли для добавления.
     * @param description Описание роли.
     */
    void add(RoleEnum title, String description);

    /**
     * Возвращает роль по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли для возврата.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    Role get(RoleEnum title);

    /**
     * Возвращает роль администратора.
     *
     * @return Объект класса {@link Role} - роль администратора.
     */
    Role getAdministrator();

    /**
     * Возвращает роль менеджера.
     *
     * @return Объект класса {@link Role} - роль менеджера.
     */
    Role getManager();

    /**
     * Возвращает роль по-умолчанию.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     */
    Role getDefault();

    /**
     * Возвращает список ролей персонала сайта.
     *
     * @return Объект типа {@link List} - список ролей персонала.
     */
    List<Role> getPersonnel();

    /**
     * Удаляет роль по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли для удаления.
     */
    void remove(RoleEnum title);
}
