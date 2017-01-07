package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.dao.interfaces.ShoppingCartDAO;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.model.ShoppingCart;
import ua.com.alexcoffee.service.interfaces.ShoppingCartService;

import java.util.List;

/**
 * Класс сервисного слоя для работы с торговой корзиной.
 * Реализует методы интерфейса {@link ShoppingCartService}.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.2
 * @see ShoppingCart
 * @see ShoppingCartService
 * @see ShoppingCartDAO
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.dao")
public final class ShoppingCartServiceImpl
        implements ShoppingCartService {
    /**
     * Реализация интерфейса для работы з торговой корзиной.
     */
    private final ShoppingCartDAO shoppingCartDAO;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired,
     * которая позволит Spring автоматически инициализировать объект.
     *
     * @param shoppingCartDAO Реализация интерфейса для работы з торговой корзиной.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ShoppingCartServiceImpl(final ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Возвращает объект корзину.
     * Режим только для чтения.
     *
     * @return Объект класса {@link ShoppingCart} -
     * торговая корзина.
     * @throws BadRequestException Бросает исключение,
     *                             если корзина отсутствует.
     */
    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws BadRequestException {
        final ShoppingCart shoppingCart = this.shoppingCartDAO.get();
        if (shoppingCart == null) {
            throw new BadRequestException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция,
     *                     которая будет добавлена в корзину.
     */
    @Override
    @Transactional
    public void add(final SalePosition salePosition) {
        if (salePosition != null) {
            this.shoppingCartDAO.addSalePosition(salePosition);
        }
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} -
     * список торговых позиций.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SalePosition> getSalePositions() {
        return this.shoppingCartDAO.getSalePositions();
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для
     *                     удаления из корзины.
     */
    @Override
    @Transactional
    public void remove(final SalePosition salePosition) {
        if (salePosition != null) {
            this.shoppingCartDAO.removeSalePosition(salePosition);
        }
    }

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    @Override
    @Transactional
    public void clear() {
        this.shoppingCartDAO.clearSalePositions();
    }

    /**
     * Возвращает цену корзины -
     * цена всех продаж.
     * Режим только для чтения.
     *
     * @return Значение типа double -
     * цена корзины.
     */
    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return this.shoppingCartDAO.getPrice();
    }

    /**
     * Возвращает размер корзины,
     * то есть количество товаров в корзине.
     * Режим только для чтения.
     *
     * @return Значение типа int -
     * количество товаров в корзине.
     */
    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return this.shoppingCartDAO.getSize();
    }
}
