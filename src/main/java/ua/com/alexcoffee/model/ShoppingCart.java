package ua.com.alexcoffee.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс описывает корзину товаров.
 * Реализует интерфейс Serializable, может быть сериализован.
 * Помечен аннотациями @Component указывает,
 * что клас является компонентом фреймворка Spring;
 * и @Scope - область видимости бина "session"
 * (один экземпляр бина для каждой сессии).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see SalePosition
 */
@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ShoppingCart implements Serializable {
    /**
     * Номер версии класса необходимый
     * для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Список торговых позиций,
     * которые сделал клиент, но пока не оформил заказ.
     */
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Конструктр без параметров.
     */
    public ShoppingCart() {
        super();
    }

    /**
     * Конструктор для инициализации основных
     * переменных корзины.
     *
     * @param salePositions Торговые позиции,
     *                      сделаные клиентом.
     */
    public ShoppingCart(
            final List<SalePosition> salePositions
    ) {
        this();
        this.salePositions = salePositions;
    }

    /**
     * Возвращает описание корзины.
     * Переопределенный метод родительского
     * класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание корзины
     * (информация о торговых позициях, цена корзины).
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shopping Cart: ");
        if (this.salePositions != null && !this.salePositions.isEmpty()) {
            int count = 1;
            for (SalePosition salePosition : this.salePositions) {
                sb.append("\n")
                        .append(count++)
                        .append(") ").append(salePosition.getProduct().getTitle())
                        .append("\n№ ")
                        .append(salePosition.getProduct().getId())
                        .append(", ")
                        .append(salePosition.getPrice())
                        .append(" UAH");
            }
            sb.append("\nPrice: ")
                    .append(getPrice())
                    .append(" UAH");
        } else {
            sb.append("is empty!");
        }
        return sb.toString();
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция,
     *                     которая будет добавлена в корзину.
     */
    public void addSalePosition(final SalePosition salePosition) {
        if (salePosition != null) {
            if (!this.salePositions.contains(salePosition)) {
                this.salePositions.add(salePosition);
            } else {
                this.salePositions.get(
                        this.salePositions.indexOf(salePosition)
                ).numberIncrement();
            }
        }
    }

    /**
     * Добавляет список торговых позиций
     * в список корзины.
     *
     * @param salePositions Список торговых позиций,
     *                      которые будут добавлены в корзину.
     */
    public void addSalePositions(
            final List<SalePosition> salePositions
    ) {
        salePositions.forEach(this::addSalePosition);
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления
     *                     из корзины.
     */
    public void removeSalePosition(final SalePosition salePosition) {
        this.salePositions.remove(salePosition);
    }

    /**
     * Удаляет список торговых позицый из корзины.
     *
     * @param salePositions Торговые позиции для удаления
     *                      из корзины.
     */
    public void removeSalePositions(
            final List<SalePosition> salePositions
    ) {
        this.salePositions.removeAll(salePositions);
    }

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    public void clearSalePositions() {
        this.salePositions.clear();
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     * Метод конвертирует список торговых позиций
     * в корзине в список только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} -
     * список торговых позиций только для чтения или пустой список.
     */
    public List<SalePosition> getSalePositions() {
        return (
                this.salePositions == null
        ) || (
                this.salePositions.isEmpty()
        ) ? Collections.EMPTY_LIST
                : Collections.unmodifiableList(
                this.salePositions
        );
    }

    /**
     * Устанавливает список торговых позиций.
     *
     * @param salePositions Список торговых позиций .
     */
    public void setSalePositions(
            final List<SalePosition> salePositions
    ) {
        this.salePositions = salePositions;
    }

    /**
     * Возвращает цену корзины -
     * цена всех торговых позиций.
     *
     * @return Значение типа double -
     * цена корзины.
     */
    public double getPrice() {
        double sum = 0;
        for (SalePosition salePosition : this.salePositions) {
            sum += salePosition.getPrice();
        }
        return sum;
    }

    /**
     * Возвращает размер корзины -
     * количество товаров в корзине.
     *
     * @return Значение типа int -
     * количество товаров в корзине.
     */
    public int getSize() {
        int size = 0;
        for (SalePosition salePosition : this.salePositions) {
            size += salePosition.getNumber();
        }
        return size;
    }
}
