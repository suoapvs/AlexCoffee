package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.SalePosition;

/**
 * Репозиторий для объектов класса {@link SalePosition}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov
 * @see MainRepository
 * @see SalePosition
 */
public interface SalePositionRepository extends MainRepository<SalePosition, Long> {

}
