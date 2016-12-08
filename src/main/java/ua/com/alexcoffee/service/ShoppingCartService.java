package ua.com.alexcoffee.service;

import ua.com.alexcoffee.model.SalePosition;
import ua.com.alexcoffee.model.ShoppingCart;

import java.util.List;

/**
 * Интерфейс сервисного слоя для работы с торговой корзиной.
 * Представляет методы с торговой корзиной и торговыми позициями,
 * которые оформляет клиент.
 *
 * @author Yurii Salimov
 * @see MainService
 * @see ShoppingCart
 * @see ua.com.alexcoffee.service.impl.SenderServiceImpl
 */
public interface ShoppingCartService {

    /**
     * Возвращает объект корзину.
     * @return Объект класса {@link ShoppingCart} - торговая корзина.
     */
    ShoppingCart getShoppingCart();

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в корзину.
     */
    void add(SalePosition salePosition);

    /**
     * Возвращает список всех торговых позиций в корзине.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    List<SalePosition> getSalePositions();

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления из корзины.
     */
    void remove(SalePosition salePosition);

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    void clear();

    /**
     * Возвращает цену корзины - цена всех продаж.
     *
     * @return Значение типа double - цена корзины.
     */
    double getPrice();

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    int getSize();
}
