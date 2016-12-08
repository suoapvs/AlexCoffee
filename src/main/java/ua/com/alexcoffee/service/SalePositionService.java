package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.SalePosition;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link SalePosition}. Наследует интерфейс {@link MainService}.
 *
 * @author Yurii Salimov
 * @see SalePosition
 * @see MainService
 * @see ua.com.alexcoffee.service.impl.SalePositionServiceImpl
 */
public interface SalePositionService extends MainService<SalePosition> {

}
