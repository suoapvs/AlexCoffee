package ua.com.alexcoffee.model.order;

import ua.com.alexcoffee.model.model.Model;
import ua.com.alexcoffee.model.position.SalePosition;
import ua.com.alexcoffee.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.alexcoffee.util.validator.ObjectValidator.isNotNull;

/**
 * Класс описывает сущность "Заказы", наследует класс {@link Model}.
 * Заказ описывает торговые позиции, клиента, сделавшего заказ, и менеджера,
 * который обработал заказ. Аннотация @Entity говорит о том что объекты
 * этого класса будет обрабатываться hibernate. Аннотация @Table(name = "orders")
 * указывает на таблицу "orders", в которой будут храниться объекты.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see User
 * @see SalePosition
 */
@Entity
@Table(name = "orders")
public final class Order extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Номер заказа. Значение поля сохраняется
     * в колонке "number". Не может быть null.
     */
    @Column(
            name = "number",
            nullable = false
    )
    private String number = "";

    /**
     * Дата модификации заказа. Значение поля
     * сохраняется в колонке "date". Не может быть null.
     */
    @Column(
            name = "date",
            nullable = false
    )
    private Date date = new Date();

    /**
     * Адрес доставки заказа. Значение поля
     * сохраняется в колонке "shipping_address".
     */
    @Column(name = "shipping_address")
    private String shippingAddress = "";

    /**
     * Детали доставки заказа. Значение поля
     * сохраняется в колонке "shipping_details".
     */
    @Column(name = "shipping_details")
    private String shippingDetails = "";

    /**
     * Описание заказа. Значение поля с
     * охраняется в колонке "description".
     */
    @Column(name = "description")
    private String description = "";

    /**
     * Статус заказа.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    /**
     * Клиент, оформивший заказ.
     * Значение поля (id объекта client) сохраняется в колонке "client_id".
     * Между объектами классов {@link Order} и {@link User} связь
     * один-к-одному, а именно каждая запись в одной таблице напрямую связана
     * с отдельной записью в другой таблице. Выборка объекта client до первого
     * доступа нему, при первом доступе к текущему объекту. Сущности связаны
     * полностью каскадным обновлением записей в базе данных.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private User client;

    /**
     * Менеджер, обработавший заказ. Значение поля (id объекта manager)
     * сохраняется в колонке "manager_id". Между объектами классов {@link Order}
     * и {@link User} связь много-к-одному, а именно каждая запись в одной таблице
     * напрямую связана с отдельной записью в другой таблице. Выборка объекта manager
     * до первого доступа нему, при первом доступе к текущему объекту.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "id"
    )
    private User manager;

    /**
     * Список торговых позиция текущего заказу. К текущему заказу можно добраться через
     * поле "order" в объекте класса {@link SalePosition}. Выборка продаж при первом
     * доступе к текущему объекту. Сущности связаны полностью каскадным
     * обновлением записей в базе данных.
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private Collection<SalePosition> salePositions = new ArrayList<>();

    protected Order() {
    }

    /**
     * Возвращает описание заказа. Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} - строка описание заказа (номер, статус,
     * дата, информация о клиенте, информация о менеджере, адрес и детали доставкиб
     * описание, торговые позиции).
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.number).append(", ")
                .append(this.status.name())
                .append(",\n").append(this.date);
        if (isNotNull(this.client)) {
            sb.append("\n\nClient: ")
                    .append(this.client.getName())
                    .append("\ne-mail: ")
                    .append(this.client.getEmail())
                    .append("\nphone: ")
                    .append(this.client.getPhone())
                    .append("\n");
        }
        if (isNotNull(this.manager)) {
            sb.append("\n")
                    .append(this.manager.getRole().name())
                    .append(" ")
                    .append(this.manager.getName())
                    .append("\n");
        }
        if (isNotEmpty(this.shippingAddress)) {
            sb.append("\nShipping address: ")
                    .append(this.shippingAddress);
        }
        if (isNotEmpty(this.shippingDetails)) {
            sb.append("\nShipping details: ")
                    .append(this.shippingDetails);
        }
        if (isNotEmpty(this.description)) {
            sb.append("\nDescription: ")
                    .append(this.description);
        }
        if (isNotEmpty(this.salePositions)) {
            sb.append("\nSale Positions: ");
            int count = 1;
            for (SalePosition salePosition : this.salePositions) {
                sb.append("\n")
                        .append(count++)
                        .append(") ")
                        .append(salePosition.getProduct().getTitle())
                        .append(", № ")
                        .append(salePosition.getProduct().getId())
                        .append(",\n")
                        .append(salePosition.getNumber())
                        .append(" x ")
                        .append(salePosition.getProduct().getPrice())
                        .append(" = ")
                        .append(salePosition.getPrice())
                        .append(" UAH;");
            }
            sb.append("\n\nPRICE = ")
                    .append(getPrice())
                    .append(" UAH");
        }
        return sb.toString();
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
            final Order orderEntity = (Order) object;
            result = this.number.equals(orderEntity.number) &&
                    this.date.equals(orderEntity.date) &&
                    this.shippingAddress.equals(orderEntity.shippingAddress) &&
                    this.shippingDetails.equals(orderEntity.shippingDetails) &&
                    this.description.equals(orderEntity.description);
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
        int result = this.number.hashCode();
        result = 31 * result + this.date.hashCode();
        result = 31 * result + this.shippingAddress.hashCode();
        result = 31 * result + this.shippingDetails.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Добавляет торговую позицию в текущий заказа.
     *
     * @param position Торговая позиция, которая будет добавлена в заказ.
     */
    public void addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.add(position);
            if (position.getOrder() != this) {
                position.setOrder(this);
            }
        }
    }

    /**
     * Добавляет список торговых позиций в текущий заказ.
     *
     * @param positions Список торговых позиций,
     *                  которые будут дабавлены в заказ.
     */
    public void addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
    }

    /**
     * Удаляет торговую позицию из текущего заказа.
     *
     * @param position Торговая позиция, которая будет удалена из заказу.
     */
    public void removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
    }

    /**
     * Удаляет список торговых позиция из текущего заказа.
     *
     * @param positions Список торговых позиция, которые будут удалены из заказа.
     */
    public void removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            this.salePositions.removeAll(positions);
        }
    }

    /**
     * Очищает список торговых позиция текущего заказа.
     */
    public void clearSalePositions() {
        this.salePositions.clear();
    }

    /**
     * Конвертирует список торговых позиций текущего заказа в список
     * только для чтений и возвращает его.
     *
     * @return Объект типа {@link List} - список торговых позиция только
     * для чтения или пустой список.
     */
    public Collection<SalePosition> getSalePositions() {
        return new ArrayList<>(this.salePositions);
    }

    /**
     * Устанавливает список торговых позицияй текущему заказу.
     *
     * @param positions Список торговых позиция.
     */
    public void setSalePositions(final Collection<SalePosition> positions) {
        this.salePositions = positions;
    }

    /**
     * Возвращает номер заказа.
     *
     * @return Значение типа {@link String} - номер заказа.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Устанавливает номер заказа.
     *
     * @param number Номер заказа.
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Возвращает дату последней модификации заказа.
     *
     * @return Значение типа {@link String} - дата модификации заказа.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Устанавливает дату модификации заказа.
     *
     * @param date Дата модификации заказа.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Возвращает статус работы заказа.
     *
     * @return Объект класса {@link OrderStatus} - статус заказа.
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
     * Устанавливает статус заказа.
     *
     * @param status Статус заказа.
     */
    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    /**
     * Возвращает клиента, оформивший заказ.
     *
     * @return Объект класса {@link User} - клиент, оформивший заказ.
     */
    public User getClient() {
        return this.client;
    }

    /**
     * Устанавливает клиента, оформившего заказ.
     *
     * @param client Клиент, оформивший заказ.
     */
    public void setClient(final User client) {
        this.client = client;
    }

    /**
     * Возвращает менеджера, обработавший заказ.
     *
     * @return Объект класса {@link User} - менеджер, обработавший заказ.
     */
    public User getManager() {
        return this.manager;
    }

    /**
     * Устанавливает менеджера, обработавший заказ.
     *
     * @param manager Менеджер, обработавший заказ.
     */
    public void setManager(final User manager) {
        this.manager = manager;
    }

    /**
     * Возвращает адрес доставки заказа.
     *
     * @return Значение типа {@link String} - адресс доставки заказа.
     */
    public String getShippingAddress() {
        return this.shippingAddress;
    }

    /**
     * Устанавливает адрес доставки заказа.
     *
     * @param shippingAddress Адрес доставки заказа.
     */
    public void setShippingAddress(final String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Возвращает детали доставки заказа.
     *
     * @return Значение типа {@link String} - детали доставки заказа.
     */
    public String getShippingDetails() {
        return this.shippingDetails;
    }

    /**
     * Устанавливает детали доставки заказа.
     *
     * @param shippingDetails Детали доставки заказа.
     */
    public void setShippingDetails(final String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    /**
     * Возвращает описание заказа.
     *
     * @return Значение типа {@link String} - описание заказа.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Встанавливает описание заказа.
     *
     * @param description Описание заказа.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Возвращает цену заказа - общую стоимость всех торговых позиция.
     *
     * @return Значение типа double - цена заказа.
     */
    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : this.salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }

    public static OrderBuilder getBuilder() {
        return new OrderBuilder();
    }
}
