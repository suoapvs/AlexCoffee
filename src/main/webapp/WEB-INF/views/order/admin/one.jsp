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
        <meta name="title" content="Заказ ${order.number} || Alex Coffee">
        <title>Заказ ${order.number} || Alex Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div class="container-fluid">
        <section id="order">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b><span class="color-brown">Заказ </span><span class="color-green">${order.number}</span></b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Номер:</th>
                            <td>${order.number}</td>
                        </tr>
                        <tr>
                            <th>Статус:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status eq status_new}">
                                        <span class="color-green"><b>${order.status.description}</b></span>
                                    </c:when>
                                    <c:otherwise>${order.status.description}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Дата:</th>
                            <td>${order.date}</td>
                        </tr>
                        <tr>
                            <th>Заказ обработал:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${order.manager ne null}">
                                        <c:choose>
                                            <c:when test="${order.manager.role eq admin_role}">
                                                <span class="color-red">${order.manager.role.description}</span>
                                            </c:when>
                                            <c:when test="${order.manager.role eq manager_role}">
                                                <span class="color-green">${order.manager.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${order.manager.role.description}</c:otherwise>
                                        </c:choose>
                                        <a href="<c:url value="/admin/user/view/${order.manager.id}"/>">
                                                ${order.manager.name}
                                        </a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Клиент:</th>
                            <td>
                                Имя: <b>${order.client.name}</b>
                                <br>Email: <b>${order.client.email}</b>
                                <br>Телефон: <b>${order.client.phone}</b>
                            </td>
                        </tr>
                        <tr>
                            <th>Адрес доставки:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.shippingAddress}">
                                        ${order.shippingAddress}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Детали доставки:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.shippingDetails}">
                                        ${order.shippingDetails}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Коментарии:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.description}">
                                        ${order.description}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
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
                            <td><b>${order_price}</b> грн</td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/order/edit/${order.id}"/>"
                                   title="Редактировать заказ ${order.number}">
                                    <button class="btn btn-success" type="submit">Редактировать</button>
                                </a>
                                <a href="<c:url value="/admin/order/delete/${order.id}"/>"
                                   title="Удалить заказ ${order.number}">
                                    <button class="btn btn-danger" type="submit">Удалить</button>
                                </a>
                                <a href="<c:url value="/admin/order/all"/>" title="Вернуться к списку заказов">
                                    <button class="btn btn-info" type="submit">Назад</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
