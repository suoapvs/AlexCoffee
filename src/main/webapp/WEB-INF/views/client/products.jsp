<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html>
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
        <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="resources/img/favicon.ico" type="image/x-icon">
            <%-- Styles --%>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="resources/bootstrap/css/animate.css" rel="stylesheet" type="text/css">
        <link href="resources/bootstrap/css/style.css" rel="stylesheet" type="text/css">
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
    <script src="resources/bootstrap/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/jquery.appear.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/main.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/jquery.maskedinput.min.js" type="text/javascript"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
