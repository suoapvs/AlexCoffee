<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="navbar-brand text-shadow">
                    <a href="<c:url value="/home"/>">
                        <span class="nav-text-label color-green">Alex</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                        <img src="<c:url value="/resources/img/favicon.png"/>" class="nav-label" alt="Alex Coffee">
                    </a>
                </div>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu-product">
                    <ul class="nav navbar-nav">
                        <li id="nav-main">
                            <a href="<c:url value="/home"/>">Главная</a>
                        </li>
                        <li id="nav-categories">
                            <a href="<c:url value="/home#categories"/>">Категории</a>
                        </li>
                        <li id="nav-all-products">
                            <a href="<c:url value="/home#all-products"/>">Товары</a>
                        </li>
                        <li id="nav-delivery" class="hidden-sm">
                            <a href="<c:url value="/home#delivery"/>">Доставка</a>
                        </li>
                        <li id="nav-payments" class="hidden-sm">
                            <a href="<c:url value="/home#payments"/>">Оплата</a>
                        </li>
                        <li id="nav-contacts" class="hidden-sm">
                            <a href="<c:url value="/home#contacts"/>">Контакты</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li id="nav-cart">
                            <a href="<c:url value="/cart"/>">
                                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                                Корзина (<c:out value="${cart_size}"/>)
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
