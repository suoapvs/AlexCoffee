<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html>
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="index,follow">
        <meta name="description" content="${product.description}"/>
        <meta name="keywords" content="${product.title}"/>
        <meta name="title" content="${product.title} || Alex Coffee">
        <title>${product.title} || Alex Coffee</title>
        <!-- Favicon -->
        <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="resources/img/favicon.ico" type="image/x-icon">
        <!-- Styles -->
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="resources/bootstrap/css/animate.css" rel="stylesheet" type="text/css">
        <link href="resources/bootstrap/css/style.css" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
    </head>
    <body>

    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>

    <!-- Product -->
    <div class="container-fluid width">
        <section id="one-product">
            <div class="row one-product">
                <div class="col-xs-7 col-xs-offset-1 col-sm-7 col-sm-offset-1 col-md-7 col-md-offset-1 col-lg-7 col-lg-offset-1">
                    <div class="col-xs-3 col-md-3 col-sm-3 col-lg-3">
                        <img width="185px" height="185px" alt="${product.title}"
                             src="resources/img/${product.photo.photoLinkShort}">
                    </div>

                    <div class="col-xs-6 col-xs-offset-2 col-sm-6 col-sm-offset-2 col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2 col-xl-6 col-xl-offset-2">
                        <h3 class="text-shadow"><b>${product.title}</b></h3>
                        <h5>Артикул: ${product.article}</h5>
                        <h3>
                            <p class="price-product">
                                <fmt:formatNumber type="number" value="${product.price}"/> грн
                            </p>
                        </h3>
                        <form action="cart_add" method=post>
                            <input type=hidden name="id" value="${product.id}">
                            <p class="text" title="Добавить ${product.title} в корзину">
                                <button class="btn btn-success" type="submit">Добавить в корзину</button>
                            </p>
                        </form>
                    </div>

                    <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                        <p><b>Характеристики товара:</b></p>
                        <p>${product.parameters}</p>

                        <c:if test="${(product.description ne null) and (product.description ne '')}">
                            <br>
                            <p><b>Описание товара:</b></p>
                            <p>${product.description}</p>
                        </c:if>

                        <c:if test="${(product.photo.photoLinkLong ne null) and (product.photo.photoLinkLong ne '')}">
                            <p><img class="hidden-xs hidden-sm" width="465px" height="465px" alt="${product.title}"
                                    src="resources/img/${product.photo.photoLinkLong}"></p>
                        </c:if>
                        <br>
                    </div>
                </div>

                <!-- Featured_products -->
                <c:if test="${fn:length(featured_products) gt 0}">
                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 col-xl-4 featured-products text-center">
                        <c:forEach items="${featured_products}" var="featured_product">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <div class="product">
                                    <a href="product_${featured_product.url}"
                                       title="Перейти к ${featured_product.title}">
                                        <img class="img-thumbnail blink" width="185px" height="185px"
                                             alt="${featured_product.title}"
                                             src="resources/img/${featured_product.photo.photoLinkShort}">
                                        <div class="text-shadow">
                                                ${featured_product.title}
                                        </div>
                                        <p class="price-top">
                                            <fmt:formatNumber type="number" value="${featured_product.price}"/> грн
                                        </p>
                                    </a>

                                    <form action="cart_add_quickly" method=post>
                                        <input type="hidden" name="id" value="${featured_product.id}">
                                        <input type="hidden" name="url" value="/product_${product.url}">
                                        <p class="text" title="Добавить ${featured_product.title} в корзину">
                                            <button class="btn btn-success" type="submit">Добавить в корзину</button>
                                        </p>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </section>
    </div>

    <!-- FOOTER -->
    <jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>

    <!-- Scripts -->
    <script src="resources/bootstrap/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/jquery.appear.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/main.js" type="text/javascript"></script>
    </body>
    </html>
</compress:html>
