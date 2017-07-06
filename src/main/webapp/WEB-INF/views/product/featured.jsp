<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(featured_products) gt 0}">
    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 featured-products text-center">
        <c:forEach items="${featured_products}" var="featured_product">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <div class="product">
                    <a href="<c:url value="/product/${featured_product.url}"/>"
                       title="Перейти к <c:out value="${featured_product.title}"/>">
                        <img class=" img-thumbnail blink" width="185px" height="185px"
                             alt="<c:out value="${featured_product.title}"/>"
                             src="<c:url value="/resources/img/${featured_product.photo.smallUrl}"/>">
                        <div class="text-shadow">
                            <c:out value="${featured_product.title}"/>
                        </div>
                        <p class="price-top">
                            <fmt:formatNumber type="number" value="${featured_product.price}"/> грн
                        </p>
                    </a>
                    <form action="<c:url value="/cart/add_quickly"/>" method=post>
                        <input type="hidden" name="id" value="<c:out value="${featured_product.id}"/>">
                        <input type="hidden" name="url"
                               value="<c:url value="/product/${product.url}"/>">
                        <p class="text"
                           title="Добавить <c:out value="${featured_product.title}"/> в корзину">
                            <button class="btn btn-success" type="submit">Добавить в корзину</button>
                        </p>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
