package ua.com.alexcoffee.repository;

import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.basket.ShoppingCart;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link ShoppingCart}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see ShoppingCartRepositoryImpl
 * @see SalePosition
 */
public interface ShoppingCartRepository {
    /**
     * Возвращает список всех торговых позиций в корзине.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    Collection<SalePosition> getSalePositions();

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в корзину.
     */
    void addSalePosition(SalePosition salePosition);

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления из корзины.
     */
    void removeSalePosition(SalePosition salePosition);

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    void clearSalePositions();

    /**
     * Возвращает объект-корзину целиком.
     *
     * @return Объект класса {@link ShoppingCart} - корзина.
     */
    ShoppingCart get();

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    int getSize();

    /**
     * Возвращает цену корзины - цена всех торговых позиций.
     *
     * @return Значение типа double - цена корзины.
     */
    double getPrice();
}
