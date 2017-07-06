package ua.com.alexcoffee.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.basket.ShoppingCart;

import java.util.Collection;
import java.util.List;

/**
 * Класс реализует методы интерфейса {@link ShoppingCartRepository} для работы с корзиной.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see ShoppingCartRepository
 * @see ShoppingCart
 */
@Repository
@ComponentScan(basePackages = "ua.com.alexcoffee.model")
public final class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    /**
     * Объект корзина, в которой
     * хранятся торговые позиции клиента.
     */
    private final ShoppingCart shoppingCart;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param shoppingCart Объект класса {@link ShoppingCart} для работы с товарной
     *                     корзиной.
     */
    @Autowired
    public ShoppingCartRepositoryImpl(final ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    @Override
    public Collection<SalePosition> getSalePositions() {
        return this.shoppingCart.getSalePositions();
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция, которая будет добавлена
     *                     в корзину.
     */
    @Override
    public void addSalePosition(final SalePosition salePosition) {
        this.shoppingCart.addSalePosition(salePosition);
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления из корзины.
     */
    @Override
    public void removeSalePosition(final SalePosition salePosition) {
        this.shoppingCart.removeSalePosition(salePosition);
    }

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    @Override
    public void clearSalePositions() {
        this.shoppingCart.clearSalePositions();
    }

    /**
     * Возвращает объект-корзину целиком.
     *
     * @return Объект класса {@link ShoppingCart} - корзина.
     */
    @Override
    public ShoppingCart get() {
        return this.shoppingCart;
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    @Override
    public int getSize() {
        return this.shoppingCart.getSize();
    }

    /**
     * Возвращает цену корзины - цена всех продаж.
     *
     * @return Значение типа double -  цена корзины.
     */
    @Override
    public double getPrice() {
        return this.shoppingCart.getPrice();
    }
}
