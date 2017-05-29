<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="navbar-brand text-shadow">
                    <a href="<c:url value="/home"/>">
                        <span class="nav-text-label color-green">Alex</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                        <img src="<c:url value="/resources/img/favicon.png"/>" class="nav-label" alt="AlexCoffee">
                    </a>
                </div>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu">
                    <ul class="nav navbar-nav">
                        <li id="nav-main"><a href="#main">Главная</a></li>
                        <li id="nav-categories"><a href="#categories">Категории</a></li>
                        <li id="nav-products"><a href="#products">Товары</a></li>
                        <li id="nav-delivery" class="hidden-sm"><a href="#delivery">Доставка</a></li>
                        <li id="nav-payments" class="hidden-sm"><a href="#payments">Оплата</a></li>
                        <li id="nav-contacts" class="hidden-sm"><a href="#contacts">Контакты</a></li>
                    </ul>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li id="nav-cart">
                        <a href="<c:url value="/cart"/>">
                            <c:choose>
                                <c:when test="${cart_size gt 0}">
                                    <span class="glyphicon glyphicon-shopping-cart color-green"
                                          aria-hidden="true"></span>
                                    Корзина (<span class="color-green"><c:out value="${cart_size}"/></span>)
                                </c:when>
                                <c:otherwise>
                                    <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                                    Корзина (<c:out value="${cart_size}"/>)
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
