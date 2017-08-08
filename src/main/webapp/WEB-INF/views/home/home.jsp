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
        <meta name="robots" content="index,follow">
        <meta name="google-site-verification" content="qiZQeYKdNTO5NVZQisl_gpnbTUCB89tSrwzSo99-fNo"/>
        <meta name="description"
              content="Alex Coffee - магазин вкусного и ароматного кофе для Вас и Вашех друзей. Кофе - любимый напиток цивилизованного мира."/>
        <meta name="keywords"
              content="alexcoffee, alex coffee, интернет, магазин, вкусный, аромтный, кофе, купить, куплю, в Киеве, в Украине, Киев, Украина"/>
        <meta name="title" content="Alex Coffee || Лучший магазин кофе">
        <title>Alex Coffee || Лучший магазин кофе</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
    <%@include file="/WEB-INF/views/home/navbar.jsp" %>
    <div class="container-fluid">
        <section id="main">
            <div class="row main text-shadow">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <span class="main-text-label color-green">Alex</span>
                    <span class="main-text-label color-brown">Coffee</span>
                    <h1>

                        <a href="<c:url value="/test"/>" title="Что это значит?">
                            <img src="<c:url value="/resources/img/main_big_icon_test.png"/>"
                                 id="label-main" class="main-label-test" alt="Alex Coffee">
                        </a>
                            <%--
                        <img src="<c:url value="/resources/img/main_big_icon.png"/>"
                             id="label-main" class="main-label " alt="Alex Coffee">
                             --%>
                    </h1>
                    <span class="main-text-label color-green">Лучший магазин </span>
                    <span class="main-text-label color-brown"> кофе</span>
                </div>
            </div>
        </section>
    </div>
    <div class="container-fluid">
        <section id="categories">
            <div class="row categories">
                <c:set var="categories_length" value="${fn:length(categories)}"/>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h3 class="intro-text text-shadow">
                        <img src="<c:url value="/resources/img/section_icon_1.png"/>"
                             id="label-category" width="90px" height="90px" alt="Alex Coffee">
                        <br>
                        <span class="home-block-name color-green">Категории</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                        <c:if test="${categories_length eq 0}">
                            <span class="color-red"> - список пуст!</span>
                        </c:if>
                    </h3>
                </div>
                <c:if test="${categories_length gt 0}">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <c:forEach items="${categories}" var="category">
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                                <div class="category">
                                    <a href="<c:url value="/category/${category.url}"/>"
                                       title="Перейти к категории <c:out value="${category.title}"/>">
                                        <img src="<c:url value="/resources/img/${category.photo.smallUrl}"/>"
                                             class="img-thumbnail blink" width="150px" height="150px"
                                             alt="<c:out value="${category.title}"/>">
                                        <div class="text-shadow">
                                            <c:out value="${category.title}"/>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                            <h4 class="text-all-products text-shadow">
                                <a href="<c:url value="/product/all"/>" title="Перейти ко всем товарам">
                                    Весь ассортимент кофе
                                </a>
                            </h4>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/product/some_list.jsp"/>
    <div class="container-fluid">
        <section id="delivery">
            <div class="row delivery">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h3 class="intro-text text-shadow">
                        <img src="<c:url value="/resources/img/section_icon_3.png"/>"
                             id="label-delivery" width="90px" height="90px" alt="Alex Coffee">
                        <br>
                        <span class="home-block-name color-green">Доставка</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </h3>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-car fa-5x color-green" id="icon1"></i>
                    <p class="icon-text">
                        Курьер по адресу<br>
                        Киев
                    </p>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-truck fa-5x color-green" id="icon2"></i>
                    <p class="icon-text">
                        Новая Почта<br>
                        Украина
                    </p>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-shopping-cart fa-5x color-green" id="icon3"></i>
                    <p class="icon-text">
                        Самовывоз<br>
                        Киев
                    </p>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h4 class="text-delivery">
                        Стоимость курьевской доставки по Киеву и отправка Новой Почтой - 35 грн<br>
                        Мы находимся по адресу г.Киев, ул. Михаила Ломоносова 55
                    </h4>
                </div>
            </div>
        </section>
    </div>
    <div class="container-fluid">
        <section id="payments">
            <div class="row payments">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h3 class="intro-text text-shadow">
                        <img src="<c:url value="/resources/img/section_icon_4.png"/>"
                             id="label-payments" width="90px" height="90px" alt="Alex Coffee">
                        <br>
                        <span class="home-block-name color-green">Оплата</span>
                        <span class="home-block-name color-brown"> Кофе</span>
                    </h3>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-money fa-5x color-green" id="icon4"></i>
                    <p class="icon-text">Оплата курьеру</p>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-truck fa-5x color-green" id="icon5"></i>
                    <p class="icon-text">
                        При получении на <br>
                        Новой Почте
                    </p>
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 icon-block-payment-delivery">
                    <i class="fa fa-cc-visa fa-5x color-green" id="icon6"></i>
                    <p class="icon-text">
                        Карта ПриватБанка
                    </p>
                </div>
                <div class="col-xs-8 col-xs-offset-2 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
                    <h4 class="text-payments">
                        Возврат приобретенных товаров осуществляется в случаях и согласно условиям,
                        регламентированным «Законом Украины о защите прав потребителей»
                    </h4>
                </div>
            </div>
        </section>
    </div>
    <div class="container-fluid">
        <section id="contacts">
            <div class="row contacts intro-text">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h3 class="text-shadow">
                        <img src="<c:url value="/resources/img/section_icon_5.png"/>"
                             id="label-cantacts" width="90px" height="90px" alt="Alex Coffee">
                        <br>
                        <span class="home-block-name color-green">Контакты</span>
                    </h3>
                    <br>
                    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-5 col-xl-5">
                        <div class="row text-shadow text-center">
                            <br>
                            <p>
                                <span class="glyphicon glyphicon-map-marker social color-green"
                                      aria-hidden="true"></span>
                                &nbsp;Украина, г. Киев, ул. Михаила Ломоносова, 55<br>
                                (5 мин ходьбы от метро Выставочный центр)
                            </p>
                            <br>
                            <p>
                                <a href="tel:+380637399290" title="Позвонить на телефон">
                                    <span class="glyphicon glyphicon-phone social color-green"
                                          aria-hidden="true"></span>
                                    &nbsp;+38(063)73-99-290
                                </a>
                            </p><br>
                            <p>
                                <a href="mailto:info@alexcoffee.com.ua" title="Написать письмо" target="_blank">
                                    <span class="glyphicon glyphicon-envelope social color-green"
                                          aria-hidden="true"></span>
                                    &nbsp;info@alexcoffee.com.ua
                                </a>
                            </p><br>
                            <p>
                                <a href="https://www.facebook.com/yurii.alex.salimov" title="Facebook" target="_blank">
                                    <i class="fa fa-facebook-official fa-2x social color-green"></i>
                                </a>
                                <a href="https://vk.com/yurii.alex.salimov" title="ВКонтакте" target="_blank">
                                    <i class="fa fa-vk fa-2x social color-brown"></i>
                                </a>
                                <a href="skype:yurii.salimov?call" title="Skype" target="_blank">
                                    <i class="fa fa-skype fa-2x social color-green"></i>
                                </a>
                            </p><br>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-7 col-xl-7">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254"
                                width="700" height="400" frameborder="0" style="border:0" allowfullscreen></iframe>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/other/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/main.js"/>"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
