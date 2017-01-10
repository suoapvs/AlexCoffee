<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Ошибка || Alex Coffee">
        <title>Ошибка || Alex Coffee</title>
            <%-- Favicon --%>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
            <%-- Styles --%>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- NAVBAR --%>
    <div class="width">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                    <div class="navbar-brand text-shadow">
                        <a href="<c:url value="/home"/>">
                            <span class="nav-text-label color-green">Alex</span>
                            <span class="nav-text-label color-brown">Coffee</span>
                            <img src="<c:url value="/resources/img/main_icon.png"/>"
                                 class="nav-label" alt="Alex Coffee">
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
                                    <span class="glyphicon glyphicon-shopping-cart"
                                          aria-hidden="true"></span> Корзина (${cart_size})
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
        <%-- Error information --%>
    <div class="container-fluid width">
        <section id="error">
            <div class="row error text-shadow color-red">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        ${text_error}<br><br><span class="color-brown" style="font-size: 15px">${message_error}</span>
                </div>
            </div>
        </section>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/main.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
