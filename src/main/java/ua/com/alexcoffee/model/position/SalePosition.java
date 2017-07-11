package ua.com.alexcoffee.model.position;

import ua.com.alexcoffee.model.basket.ShoppingCart;
import ua.com.alexcoffee.model.model.Model;
import ua.com.alexcoffee.model.order.Order;
import ua.com.alexcoffee.model.product.Product;

import javax.persistence.*;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNull;

/**
 * Класс описывает сущность "Торговая позиция", наследует класс {@link Model}.
 * Торговая позиция составляет товар и количество этого товара.
 * Аннотация @Entity говорит о том что объекты этого класса будет
 * обрабатываться hibernate.
 * Аннотация @Table(name = "sales") указывает на таблицу "sales",
 * в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see Product
 * @see Order
 * @see ShoppingCart
 */
@Entity
@Table(name = "sales")
public class SalePosition extends Model {

    /**
     * Товар текущей торговой позици.
     * Значение поля (id объекта photo) сохраняется в колонке "product_id".
     * Не может быть null.
     * Между объектами классов {@link Product} и {@link SalePosition}
     * связь один-к-одному, а именно каждая запись в одной таблице напрямую
     * связана с отдельной записью в другой таблице. Выборка объекта product
     * до первого доступа нему, при первом доступе к текущему объекту.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    /**
     * Количество товаров в текущей торговой позиции.
     * Значение поля сохраняется в колонке "description".
     * Не может быть null.
     */
    @Column(
            name = "number",
            nullable = false
    )
    private int number = 0;

    /**
     * Заказ, к которому относится текущая торговая позиция
     * Значение поля (id объекта order) сохраняется в колонке "order_id".
     * Не может быть null.
     * Между объектами классов {@link Order} и {@link SalePosition}
     * связь многие-к-одному, а именно каждая много заказов могут иметь
     * одинаковый статус выполнения. Выборка объекта order при первом доступе к нему.
     * Сущность order автоматически удаляется при удалении текущей.
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Order order;

    /**
     * Возвращает описание торговой позиции.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание торговой позиции
     * (уникальный код позиции, информация о товаре, количество тваров и общая
     * цена торговой позиции).
     */
    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId()
                + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number
                + "\nPrice = " + getPrice();
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @param object объект для сравнения с текущим объектом.
     * @return Значение типа boolean - результат сравнения текущего объекта
     * с переданным объектом.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final SalePosition position = (SalePosition) object;
            result = (this.number == position.number);
            if (isNotNull(this.product)) {
                result &= this.product.equals(position.product);
            } else {
                result &= isNull(position.product);
            }
        }
        return result;
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа int - уникальный номер объекта.
     */
    @Override
    public int hashCode() {
        int result = isNotNull(this.product) ? this.product.hashCode() : 0;
        result = 31 * result + this.number;
        return result;
    }

    /**
     * Возвращает общую стоимость торговой позиции (цена товара * количество).
     *
     * @return Значение типа double - цена торговой пзиции.
     */
    public double getPrice() {
        return this.number * this.product.getPrice();
    }

    /**
     * Увеличивает количество товаров в позиции на 1.
     */
    public void numberIncrement() {
        this.number++;
    }

    /**
     * Возвращает товар текущей торговой позиции.
     *
     * @return Объект класса {@link Product} - товар позиции.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Устанавливает товар для текущей торговой позиции.
     *
     * @param product Товар для позиции.
     */
    public void setProduct(final Product product) {
        this.product = product;
        this.number = isNotNull(product) ? 1 : 0;
    }

    /**
     * Возвращает номер торговой позиции.
     *
     * @return Значение типа {@link String} - номер торговой позиции.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Устанавливает номер торговой позиции.
     * Если входной параметр меньше 0, тогда значение номера будет 0.
     *
     * @param number Номер торговой позиции.
     */
    public void setNumber(final int number) {
        this.number = (number > 0) ? number : 0;
    }

    /**
     * Возвращает заказ, которому пренадлежит текущая торговая позиция.
     *
     * @return Объект класса {@link Order} - заказ торговой позиции.
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * Устанавливает заказ, которому пренадлежит текущая торговая позиция.
     *
     * @param order Заказ торговой позиции.
     */
    public void setOrder(final Order order) {
        this.order = order;
    }
}
