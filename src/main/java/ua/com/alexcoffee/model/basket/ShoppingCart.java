package ua.com.alexcoffee.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ua.com.alexcoffee.model.position.SalePosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс описывает корзину товаров.
 * Реализует интерфейс Serializable, может быть сериализован.
 * Помечен аннотациями @Component указывает, что клас является
 * компонентом фреймворка Spring;
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
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Список торговых позиций,
     * которые сделал клиент, но пока не оформил заказ.
     */
    private List<SalePosition> salePositions = new ArrayList<>();

    /**
     * Возвращает описание корзины.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание корзины
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
     * @param position Торговая позиция, которая будет добавлена в корзину.
     */
    public void addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            if (!this.salePositions.contains(position)) {
                this.salePositions.add(position);
            } else {
                final int index = this.salePositions.indexOf(position);
                this.salePositions.get(index).numberIncrement();
            }
        }
    }

    /**
     * Добавляет список торговых позиций в список корзины.
     *
     * @param positions Список торговых позиций,
     *                  которые будут добавлены в корзину.
     */
    public void addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param position Торговая позиция для удаления из корзины.
     */
    public void removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
    }

    /**
     * Удаляет список торговых позицый из корзины.
     *
     * @param positions Торговые позиции для удаления из корзины.
     */
    public void removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            this.salePositions.removeAll(positions);
        }
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
     * @return Объект типа {@link List} - список торговых позиций только
     * для чтения или пустой список.
     */
    public Collection<SalePosition> getSalePositions() {
        final Collection<SalePosition> positions;
        if (isNotEmpty(this.salePositions)) {
            positions = new ArrayList<>(this.salePositions);
        } else {
            positions = new ArrayList<>();
        }
        return positions;
    }

    /**
     * Устанавливает список торговых позиций.
     *
     * @param positions Список торговых позиций .
     */
    public void setSalePositions(final Collection<SalePosition> positions) {
        clearSalePositions();
        addSalePositions(positions);
    }

    /**
     * Возвращает цену корзины - цена всех торговых позиций.
     *
     * @return Значение типа double - цена корзины.
     */
    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : this.salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }

    /**
     * Возвращает размер корзины - количество товаров в корзине.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    public int getSize() {
        int size = 0;
        for (SalePosition salePosition : this.salePositions) {
            size += salePosition.getNumber();
        }
        return size;
    }
}
