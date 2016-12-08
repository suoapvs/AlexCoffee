package ua.com.alexcoffee.dao;

import ua.com.alexcoffee.enums.RoleEnum;
import ua.com.alexcoffee.model.Role;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link Role} с базой данных.
 * Расширяет интерфейс {@link DataDAO}.
 *
 * @author Yurii Salimov
 * @see DataDAO
 * @see ua.com.alexcoffee.dao.impl.RoleDAOImpl
 * @see Role
 * @see RoleEnum
 * @see ua.com.alexcoffee.model.User
 */
public interface RoleDAO extends DataDAO<Role> {

    /**
     * Добавляет роль в базу даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title       Название роли.
     * @param description Описание роли.
     */
    void add(RoleEnum title, String description);

    /**
     * Возвращает роль из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     * @return Объект класса {@link Role} - роль с уникальным названием.
     */
    Role get(RoleEnum title);

    /**
     * Возвращает из базы даных роль по-умолчанию.
     *
     * @return Объект класса {@link Role} - роль по-умолчание.
     */
    Role getDefault();

    /**
     * Удаляет роль из базы даных по названию, которое может принимать одно
     * из значений перечисления {@link RoleEnum}.
     *
     * @param title Название роли.
     */
    void remove(RoleEnum title);
}
