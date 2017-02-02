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
        <meta name="description" content="Весь ассортимент товаров интернет магазина кофе Alex Coffee"/>
        <meta name="keywords" content="Ассортимент кофе магазина Alex Coffee"/>
        <meta name="robots" content="index,follow">
        <meta name="title" content="Весь ассортимент кофе || Alex Coffee">
        <title>Весь ассортимент кофе || Alex Coffee</title>
            <%-- Favicon --%>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
            <%-- Styles --%>
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
    </head>
    <body>
        <%-- NAVBAR --%>
    <jsp:include page="/WEB-INF/views/client/template/navbar.jsp"/>
        <%-- All PRODUCTS --%>
    <jsp:include page="/WEB-INF/views/client/template/some_products.jsp"/>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/template/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/main.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>" type="text/javascript"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
