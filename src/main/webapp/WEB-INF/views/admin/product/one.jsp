<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
            <%-- HEAD --%>
        <jsp:include page="/WEB-INF/views/admin/template/head.jsp"/>
        <meta name="title" content="${product.title} || Alex Coffee">
        <title>${product.title} || Alex Coffee</title>
    </head>
    <body>
        <%-- NAVBAR --%>
    <jsp:include page="/WEB-INF/views/admin/template/admin_navbar.jsp"/>
        <%-- Product --%>
    <div class="container-fluid width">
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
                                <br><img src="<c:url value="/resources/img/${product.photo.photoLinkShort}"/>"
                                         width="75px" height="75px" alt="${product.title}">
                                <c:if test="${product.photo.photoLinkLong ne null}">
                                    <img src="<c:url value="/resources/img/${product.photo.photoLinkLong}"/>"
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
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
