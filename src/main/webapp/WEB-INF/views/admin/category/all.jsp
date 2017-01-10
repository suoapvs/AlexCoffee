<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
            <%-- HEAD --%>
        <jsp:include page="/WEB-INF/views/admin/template/head.jsp"/>
        <meta name="title" content="Категории кофе || Alex Coffee">
        <title>Категории кофе || Alex Coffee</title>
    </head>
    <body>
        <%-- NAVBAR --%>
    <jsp:include page="/WEB-INF/views/admin/template/admin_navbar.jsp"/>
        <%-- All categories --%>
    <div class="container-fluid width">
        <section id="categories">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Категории</span>
                            <c:if test="${fn:length(categories) eq 0}">
                                <span class="color-red"> - список пуст!</span><br>
                                <a href="<c:url value="/admin/category/add"/>" title="Добавить новую категорию">
                                    <button class="btn btn-success" type="submit">Добавить</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${fn:length(categories) gt 0}">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                        <table class="table">
                            <tr>
                                <th>Название</th>
                                <td class="hidden-xs"><b>URL</b></td>
                                <th>
                                    Действие
                                    <a href="<c:url value="/admin/category/add"/>" title="Добавить новую категорию">
                                        <button class="btn btn-success" type="submit">Добавить</button>
                                    </a>
                                    <a href="<c:url value="/admin/category/delete_all"/>"
                                       title="Удалить все категории">
                                        <button class="btn btn-danger" type="submit">Удалить ВСЕ</button>
                                    </a>
                                </th>
                            </tr>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td>
                                        <a href="<c:url value="/category/${category.url}"/>"
                                           title="Перейти к категории ${category.title}">
                                                ${category.title}
                                        </a>
                                    </td>
                                    <td class="hidden-xs">${category.url}</td>
                                    <td>
                                        <a href="<c:url value="/admin/category/view/${category.id}"/>"
                                           title="Смотреть категорию ${category.title}">
                                            <button class="btn btn-info" type="submit">Смотреть</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/edit/${category.id}"/>"
                                           title="Редактировать категорию ${category.title}">
                                            <button class="btn btn-success" type="submit">Редактировать</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/delete/${category.id}"/>"
                                           title="Удалить категорию ${category.title}">
                                            <button class="btn btn-danger" type="submit">Удалить</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
