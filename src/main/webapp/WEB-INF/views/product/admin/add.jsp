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
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Новый продукт || Alex Coffee">
        <title>Новый продукт || Alex Coffee</title>
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
                        <b>
                            <span class="color-green">Новый </span>
                            <span class="color-brown">товар</span>
                        </b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <form action="<c:url value="/admin/product/save"/>" enctype="multipart/form-data" method="post">
                        <table class="table">
                            <tr>
                                <th>Название:</th>
                                <td>
                                    <input class="input-order" type="text" name="title" minlength="5"
                                           maxlength="100" placeholder="Введите название товара" required>
                                </td>
                            </tr>
                            <tr>
                                <th>URL:</th>
                                <td>
                                    <input class="input-order" type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                           placeholder=" Введите URL, формат (a-z, 0-9, _)"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Категория:</th>
                                <td>
                                    <select class="input-order" name="category" title="Категория товара">
                                        <c:forEach items="${categories}" var="category">
                                            <option value="${category.id}">${category.title}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Параметры:</th>
                                <td>
                                    <textarea class="input-order textarea" name="parameters" maxlength="500"
                                              placeholder="Введите параметры товара" required></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Описание:</th>
                                <td>
                                    <textarea class="input-order textarea" name="description" maxlength="500"
                                              placeholder="Введите описание товара"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Изображение:</th>
                                <td>
                                    <input class="input-order" type="text" name="photo_title" required
                                           placeholder="Введите название фото" minlength="5" maxlength="100">
                                    <br>
                                    Малое: <input type="file" name="small_photo" accept="image/*">
                                    <br>
                                    Большое: <input type="file" name="big_photo" accept="image/*">
                                </td>
                            </tr>
                            <tr>
                                <th>Цена:</th>
                                <td>
                                    <input class="input-order" type="number" name="price" required
                                           placeholder="Введите цену товара" min="0" max="99999" step="0.01">
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit"
                                            title="Добавить новый товар">
                                        Добавить товар
                                    </button>
                                    <button class="btn btn-info" type="reset"
                                            title="Сбросить введенные даные">
                                        Сброс
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </form>
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
