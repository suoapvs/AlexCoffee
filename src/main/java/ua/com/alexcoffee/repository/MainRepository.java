package ua.com.alexcoffee.repository;

import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alexcoffee.model.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для объектов классов наследников класса {@link Model}.
 * Наследует интерфейс {@link JpaRepository} - это интерфейс
 * фреймворка Spring Data предоставляющий набор стандартных методов JPA
 * для работы с БД. Первый Generic T должен быть тип (класс) объекта нашей
 * сущности для которой мы создали Repository, это указывает на то, что
 * Spring Data должен предоставить реализацию методов для работы с этой
 * сущностью (обязательно). Второй Generic E должен быть оберточным типом
 * того типа которым есть id нашей сущности (обязательно).
 *
 * @param <T> Тип (класс) сущности.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see CategoryRepository
 * @see OrderRepository
 * @see PhotoRepository
 * @see ProductRepository
 * @see RoleRepository
 * @see SalePositionRepository
 * @see StatusRepository
 * @see UserRepository
 * @see Model
 */
@NoRepositoryBean
public interface MainRepository<T extends Model> extends JpaRepository<T, Long> {
}
