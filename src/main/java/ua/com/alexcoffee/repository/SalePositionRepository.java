package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.position.SalePosition;

/**
 * Репозиторий для объектов класса {@link SalePosition}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see SalePosition
 */
public interface SalePositionRepository extends MainRepository<SalePosition> {
}
