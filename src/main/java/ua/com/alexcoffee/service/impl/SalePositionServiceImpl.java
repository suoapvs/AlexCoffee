package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.repository.SalePositionRepository;
import ua.com.alexcoffee.service.interfaces.SalePositionService;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link SalePosition}
 * в базе данных интерфейса {@link SalePositionService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainServiceImpl
 * @see SalePositionService
 * @see SalePosition
 * @see SalePositionRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class SalePositionServiceImpl extends MainServiceImpl<SalePosition> implements SalePositionService {

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link SalePositionRepository}
     *                   для работы торговых позиций с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SalePositionServiceImpl(final SalePositionRepository repository) {
        super(repository);
    }
}
