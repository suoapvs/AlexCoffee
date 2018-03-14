<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="noindex,nofollow">
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
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div class="container-fluid">
        <section id="product">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b><span class="color-brown">Товар </span><span
                                class="color-green">"${product.title}"</span></b>
                    </div>
                </div>

                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Название:</th>
                            <td>
                                <a href="<c:url value="/product/${product.url}"/>"
                                   title="Перейти к товару ${product.title}">
                                        ${product.title}
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>Категория:</th>
                            <td>
                                <a href="<c:url value="/admin/category/view/${product.id}"/>"
                                   title="Смотреть категорию ${product.category.title}">
                                        ${product.category.title}
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>Параметры:</th>
                            <td>${product.parameters}</td>
                        </tr>
                        <tr>
                            <th>Описание:</th>
                            <td>${product.description}</td>
                        </tr>
                        <tr>
                            <th>Изображение:</th>
                            <td>
                                    ${product.photo.title}
                                <br><img src="<c:url value="/resources/img/${product.photo.smallUrl}"/>"
                                         width="75px" height="75px" alt="${product.title}">
                                <c:if test="${product.photo.smallUrl ne null}">
                                    <img src="<c:url value="/resources/img/${product.photo.longUrl}"/>"
                                         width="100px" height="100px" alt="${product.title}">
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>Цена:</th>
                            <td>${product.price}</td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/product/edit/${product.id}"/>"
                                   title="Редактировать товар ${product.title}">
                                    <button class="btn btn-success" type="submit">Редактировать</button>
                                </a>
                                <a href="<c:url value="/admin/product/delete/${product.id}"/>"
                                   title="Удалить товар ${product.title}">
                                    <button class="btn btn-danger" type="submit">Удалить</button>
                                </a>
                                <a href="<c:url value="/admin/product/all"/>" title="Вернуться к списку товаров">
                                    <button class="btn btn-info" type="submit">Назад</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
