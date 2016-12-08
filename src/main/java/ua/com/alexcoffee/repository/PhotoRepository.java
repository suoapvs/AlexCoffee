package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.Photo;

/**
 * Репозиторий для объектов класса {@link Photo}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov
 * @see MainRepository
 * @see Photo
 */
public interface PhotoRepository extends MainRepository<Photo, Long> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объект-изображение.
     */
    Photo findByTitle(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаление.
     */
    void deleteByTitle(String title);
}
