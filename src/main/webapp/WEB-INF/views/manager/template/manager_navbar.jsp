<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="width">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span><span class="icon-bar"></span>
                </button>
                <div class="navbar-brand text-shadow">
                    <a href="<c:url value="/home"/>">
                        <span class="nav-text-label color-green">Alex</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                    </a>
                    <a href="<c:url value="/manager/"/>"><span class="nav-text-label color-green">M</span></a>
                </div>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu-product">
                    <ul class="nav navbar-nav">
                        <li id="nav-orders"><a href="<c:url value="/manager/order/all"/>">Заказы</a></li>
                        <li id="nav-persons"><a href="<c:url value="/manager/user/all"/>">Персонал</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li id="auth-user">
                            <a href="<c:url value="/manager/view_user_${auth_user.id}"/>">${auth_user.name}</a>
                        </li>
                        <li id="nav-logout">
                            <form class="form-signin" action="<c:url value="/logout"/>" method="post">
                                <input type="hidden" id="username" name="username">
                                <button class="btn-logout btn btn-danger" type="submit">Выйти</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
