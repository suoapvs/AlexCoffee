<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="description" content="Корзина - выбраные товары."/>
        <meta name="keywords" content="Корзина, выбраные товары, оформить заказ"/>
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Корзина || Alex Coffee">
        <title>Корзина || Alex Coffee</title>
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
    <jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>
        <%-- CART --%>
    <div class="container-fluid width">
        <section id="cart">
            <div class="row cart">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow color-brown">
                        <b>
                            <span class="color-brown">Корзина</span>
                                <%-- If cert is empty --%>
                            <c:if test="${fn:length(sale_positions) eq 0}">
                                <span class="color-green"> - список пуст!</span>
                            </c:if>
                        </b>
                    </div>
                </div>
                    <%-- If Cart is not empty --%>
                <c:if test="${fn:length(sale_positions) gt 0}">
                    <%-- PRODUCTS IN THE CART --%>
                    <jsp:include page="/WEB-INF/views/client/template/products_in_the_cart.jsp"/>
                    <%-- Order registration --%>
                    <div class="row">
                        <form action="checkout" method="post">
                            <div class="col-xs-12 col-sm-2 col-sm-offset-2 col-md-2 col-md-offset-2 col-lg-2 col-lg-offset-2 col-xl-2 col-xl-offset-2 input-padding text-center">
                                <input class="input" type="text" name="user_name" placeholder=" Введите имя"
                                       minlength="2" maxlength="50" required autofocus>
                            </div>
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                                <input class="phone input" type="text" name="user_phone" placeholder=" Введите телефон"
                                       required>
                            </div>
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                                <input class="input" type="email" name="user_email" placeholder=" Введите Email"
                                       minlength="5" maxlength="50">
                            </div>
                            <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2 col-xl-2 input-padding text-center">
                                <input type="submit" class="btn btn-success" value="Оформить заказ" width="80px">
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 text-center">
                            <a href="<c:url value="/all_products"/>">
                                <button class="btn btn-success">Продолжить покупки</button>
                            </a>
                            <a href="<c:url value="/cart_clear"/>">
                                <button class="btn btn-success">Очистить корзину</button>
                            </a>
                        </div>
                    </div>
                </c:if>
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
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
