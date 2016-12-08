package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Status;
import ua.com.alexcoffee.enums.StatusEnum;

/**
 * Репозиторий для объектов класса {@link Status}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov
 * @see MainRepository
 * @see Status
 */
public interface StatusRepository extends MainRepository<Status, Long> {
    /**
     * Возвращает статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     * @return Объект класса {@link Status} - статус с уникальным названием.
     */
    Status findByTitle(StatusEnum title);

    /**
     * Удаляет статус из базы даных по названию, которое может принимать
     * одно из значений перечисления {@link StatusEnum}.
     *
     * @param title Название статуса.
     */
    void deleteByTitle(StatusEnum title);
}
