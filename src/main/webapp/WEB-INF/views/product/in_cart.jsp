<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
        <table class="table cart-table">
            <tr>
                <th>Название</th>
                <th>Кол.</th>
                <th>Фото</th>
                <th>Категория</th>
                <th>Стоимость</th>
            </tr>
            <c:forEach items="${sale_positions}" var="position">
                <tr>
                    <td>
                        <a href="<c:url value="/product/${position.product.url}"/>"
                           title="Перейти к <c:out value="${position.product.title}"/>">
                            <c:out value="${position.product.title}"/>
                        </a>
                    </td>
                    <td>${position.number}</td>
                    <td>
                        <img src="<c:url value="/resources/img/${position.product.photo.smallUrl}"/>"
                             width="50px" height="50px" alt="<c:out value="${position.product.title}"/>">
                    </td>
                    <td>
                        <a href="<c:url value="/category/${position.product.category.url}"/>"
                           title="Перейти к категории <c:out value="${position.product.category.title}"/>">
                            <c:out value="${position.product.category.title}"/>
                        </a>
                    </td>
                    <td>
                        <fmt:formatNumber type="number" value="${position.product.price}"/> грн
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td style="text-align: right;">
                    <strong>Итого:</strong>
                </td>
                <td>
                    <b><fmt:formatNumber type="number" value="${price_of_cart}"/> грн</b>
                </td>
            </tr>
        </table>
    </div>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
