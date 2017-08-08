<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="index,follow">
        <meta name="description" content="${product.description}"/>
        <meta name="keywords" content="${product.title}"/>
        <meta name="title" content="${product.title} || Alex Coffee">
        <title>${product.title} || Alex Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/client_navbar.jsp"/>
    <div class="container-fluid">
        <section id="one-product">
            <div class="row one-product">
                <div class="col-xs-7 col-xs-offset-1 col-sm-7 col-sm-offset-1 col-md-7 col-md-offset-1 col-lg-7 col-lg-offset-1">
                    <div class="col-xs-3 col-md-3 col-sm-3 col-lg-3">
                        <img src="<c:url value="/resources/img/${product.photo.smallUrl}"/>"
                             width="185px" height="185px" alt="<c:out value="${product.title}"/>">
                    </div>
                    <div class="col-xs-6 col-xs-offset-2 col-sm-6 col-sm-offset-2 col-md-6 col-md-offset-2 col-lg-6 col-lg-offset-2 col-xl-6 col-xl-offset-2">
                        <h3 class="text-shadow">
                            <b><c:out value="${product.title}"/></b>
                        </h3>
                        <h5>
                            Артикул: <c:out value="${product.article}"/>
                        </h5>
                        <h3>
                            <span class="price-product">
                                <fmt:formatNumber type="number" value="${product.price}"/> грн
                            </span>
                        </h3>
                        <form action="<c:url value="/cart/add"/>" method=post>
                            <input type=hidden name="id" value="<c:out value="${product.id}"/>">
                            <p class="text" title="Добавить <c:out value="${product.title}"/> в корзину">
                                <button class="btn btn-success" type="submit">Добавить в корзину</button>
                            </p>
                        </form>
                    </div>
                    <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                        <p><b>Характеристики товара:</b></p>
                        <p>${product.parameters}</p>
                        <c:if test="${not empty product.description}">
                            <br>
                            <p><b>Описание товара:</b></p>
                            <p><c:out value="${product.description}"/></p>
                        </c:if>
                        <c:if test="${not empty product.photo.longUrl}">
                            <p>
                                <img src="<c:url value="/resources/img/${product.photo.longUrl}"/>"
                                     class="hidden-xs hidden-sm" width="465px" height="465px"
                                     alt="<c:out value="${product.title}"/>">
                            </p>
                        </c:if>
                        <br>
                    </div>
                </div>
                <%@include file="/WEB-INF/views/product/featured.jsp" %>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/other/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/main.js"/>"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
