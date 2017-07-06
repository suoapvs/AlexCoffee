<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Редактирование заказа ${orderEntity.number} || Alex Coffee">
        <title>Редактирование заказа ${orderEntity.number} || Alex Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
    </head>
    <body>
        <%-- NAVBAR --%>
    <jsp:include page="/WEB-INF/views/other/manager_navbar.jsp"/>
        <%-- Edit orderEntity --%>
    <div class="container-fluid">
        <section id="orderEntity">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Редактирование заказа </span>
                            <span class="color-green">${orderEntity.number}</span>
                        </b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <form action="<c:url value="/managers/orderEntity/update"/>" method="post">
                        <input type="hidden" name="id" value="${orderEntity.id}">
                        <input type="hidden" name="auth_user" value="${auth_user.id}">
                        <table class="table">
                            <tr>
                                <th>Номер:</th>
                                <td>
                                    <input class="input-orderEntity" type="text" name="number" pattern="[A-Za-z0-9]{3,10}"
                                           placeholder=" Введите номер, формат (A-Z, a-z, 0-9)"
                                           value="${orderEntity.number}" minlength="3" maxlength="10" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Статус:</th>
                                <td>
                                    <select class="input-orderEntity" name="status" title="Статус заказа">
                                        <option value="${orderEntity.status.id}">${orderEntity.status.description}</option>
                                        <c:forEach items="${statuses}" var="status">
                                            <c:if test="${status.id ne orderEntity.status.id}">
                                                <option value="${status.id}">${status.description}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Дата:</th>
                                <td>${orderEntity.date}</td>
                            </tr>
                            <tr>
                                <th>Клиент:</th>
                                <td>
                                    <input class="input-orderEntity" type="text" name="user_name" minlength="2" maxlength="50"
                                           placeholder=" Введите имя клиента" value="${orderEntity.client.name}" required>
                                    <br><input class="input-orderEntity" name="user_email" value="${orderEntity.client.email}"
                                               placeholder=" Введите email клиента, формат (A-Z, a-z, 0-9, _, ., @)"
                                               pattern="[A-Za-z0-9_.@]{5,50}" minlength="5" maxlength="50" type="email">
                                    <br><input id="phone" class="input-orderEntity" type="text" name="user_phone" required
                                               placeholder=" Введите телефон клиента" value="${orderEntity.client.phone}">
                                </td>
                            </tr>
                            <tr>
                                <th>Адрес доставки:</th>
                                <td>
                                    <input class="input-orderEntity" type="text" name="shipping-address" maxlength="100"
                                           placeholder=" Введите адрес доставки" value="${orderEntity.shippingAddress}">
                                </td>
                            </tr>
                            <tr>
                                <th>Детали доставки:</th>
                                <td>
                                    <input class="input-orderEntity" type="text" name="shipping-details" maxlength="100"
                                           placeholder=" Введите детали даставки" value="${orderEntity.shippingDetails}">
                                </td>
                            </tr>
                            <tr>
                                <th>Коментарии:</th>
                                <td>
                                <textarea class="input-orderEntity textarea" name="description"
                                          placeholder=" Коментарий" maxlength="250">${orderEntity.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Товары:</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${fn:length(sale_positions) gt 0}">
                                            <c:forEach items="${sale_positions}" var="position">
                                                <a href="<c:url value="/product/${position.product.url}"/>"
                                                   title="Перейти к товару ${position.product.title}">
                                                        ${position.product.title}
                                                </a>, № ${position.product.id},
                                                <br>${position.number} x ${position.product.price} грн
                                                <br>--------------<br>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>Список товаров пуст!</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>Общая сумма:</th>
                                <td>${order_price} грн</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit"
                                            title="Обновить информацию о заказе">Сохранить</button>
                                    <button class="btn btn-info" type="reset"
                                            title="Сбросить введенные даные">Сброс</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
