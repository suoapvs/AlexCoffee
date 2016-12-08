package ua.com.alexcoffee.model;

import javax.persistence.*;

/**
 * Класс описывает сущность "Торговая позиция", наследует класс {@link Model}.
 * Торговая позиция составляет товар и количество этого товара.
 * Аннотация @Entity говорит о том что объекты этого класса будет обрабатываться hibernate.
 * Аннотация @Table(name = "sales") указывает на таблицу "sales", в которой будут храниться объекты.
 *
 * @author Yurii Salimov
 * @see Product
 * @see Order
 * @see ShoppingCart
 */
@Entity
@Table(name = "sales")
public class SalePosition extends Model {

    /**
     * Товар текущей торговой позици.
     * Значение поля (id объекта photo) сохраняется в колонке "product_id". Не может быть null.
     * Между объектами классов {@link Product}
     * и {@link SalePosition} связь один-к-одному, а именно каждая
     * запись в одной таблице напрямую связана с отдельной записью в другой таблице.
     * Выборка объекта product до первого доступа нему, при первом доступе к текущему объекту.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    /**
     * Количество товаров в текущей торговой позиции.
     * Значение поля сохраняется в колонке "description". Не может быть null.
     */
    @Column(name = "number", nullable = false)
    private int number;

    /**
     * Заказ, к которому относится текущая торговая позиция
     * Значение поля (id объекта order) сохраняется в колонке "order_id". Не может быть null.
     * Между объектами классов {@link Order} и
     * {@link SalePosition} связь многие-к-одному, а именно каждая
     * много заказов могут иметь одинаковый статус выполнения.
     * Выборка объекта order при первом доступе к нему.
     * Сущность order автоматически удаляется при удалении текущей.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    /**
     * Конструктр без параметров.
     */
    public SalePosition() {
        this(null, 0);
    }

    /**
     * Конструктор для инициализации основных переменных категории.
     *
     * @param product Товар текущей торговой позици.
     * @param number  Количество товаров в текущей торговой позиции.
     */
    public SalePosition(Product product, int number) {
        super();
        this.product = product;
        this.number = number;
    }

    /**
     * Возвращает описание торговой позиции.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание торговой позиции
     * (уникальный код позиции, информация о товаре, количество тваров и общая цена торговой позиции).
     */
    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId() + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number + "\nPrice = " + getPrice();
    }

    /**
     * Генерирует строку для конечного сравнения торговых позиций в методе equals() родительского класса.
     * Переопределенный метод родительского класса {@link Model}.
     *
     * @return Значение типа {@link String} - результат работы метода сравнения входящего товара toEquals.
     */
    @Override
    public String toEquals() {
        String line = this.product.toEquals();
        if (getId() != null) {
            line += getId();
        }
        return line;
    }

    /**
     * Возвращает общую стоимость торговой позиции (цена товара * количество).
     *
     * @return Значение типа double - цена торговой пзиции.
     */
    public double getPrice() {
        return this.product.getPrice() * this.number;
    }

    /**
     * Увеличивает количество товаров в позиции на 1.
     */
    public void numberIncr() {
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
    public void setProduct(Product product) {
        this.product = product;
        this.number = product != null ? 1 : 0;
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
    public void setNumber(int number) {
        this.number = number > 0 ? number : 0;
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
    public void setOrder(Order order) {
        this.order = order;
    }
}
