package ua.com.alexcoffee.dao.interfaces;

import ua.com.alexcoffee.model.SalePosition;

/**
 * Интерфейс описывает набор методов для работы объектов класса
 * {@link SalePosition} с базой данных. Наследует интерфейс {@link DataDAO},
 * своих дополнительных методов не имеет.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see DataDAO
 * @see ua.com.alexcoffee.dao.impl.SalePositionDAOImpl
 * @see SalePosition
 * @see ua.com.alexcoffee.model.Order
 * @see ua.com.alexcoffee.model.ShoppingCart
 */
public interface SalePositionDAO extends DataDAO<SalePosition> {
}
