package ua.com.alexcoffee.service.interfaces;

import ua.com.alexcoffee.model.position.SalePosition;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link SalePosition}.
 * Наследует интерфейс {@link MainService}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see SalePosition
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.SalePositionServiceImpl
 */
public interface SalePositionService extends MainService<SalePosition> {
}
