<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="description" content="${category.title} - ${category.description}"/>
        <meta name="keywords" content="${category.title}"/>
        <meta name="robots" content="index,follow">
        <meta name="title" content="${category.title} || Alex Coffee">
        <title>${category.title} || Alex Coffee</title>
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
        <%-- Coffee --%>
    <div class="container-fluid width">
        <section id="products_${category.url}">
            <div class="row products">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <h3 class="intro-text label-categories">
                        <img id="label-category" width="150px" height="150px" alt="${category.title}"
                             src="<c:url value="/resources/img/${category.photo.photoLinkShort}"/>">
                        <div class="text-shadow">
                            <span class="home-block-name color-green">${category.title}</span>
                            <c:if test="${fn:length(products) eq 0}">
                                <span class="home-block-name color-red"> - список пуст!</span>
                            </c:if>
                        </div>
                    </h3>
                </div>
                    <%-- PRODUCTS IN CATEGORY --%>
                <jsp:include page="/WEB-INF/views/client/template/products_list.jsp"/>
                <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <h4 class="text-all-products text-shadow">
                        <a href="<c:url value="/product/all"/>"
                           title="Перейти ко всем товарам">Весь ассортимент кофе</a>
                    </h4>
                </div>
            </div>
        </section>
    </div>
        <%-- Category description --%>
    <c:if test="${category.description ne ''}">
        <div class="container-fluid width">
            <section id="category-description">
                <div class="row category-description color-black">
                    <div class="col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                        <p><b>${category.title}</b></p>
                        <p>${category.description}</p>
                    </div>
                </div>
            </section>
        </div>
    </c:if>
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

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
