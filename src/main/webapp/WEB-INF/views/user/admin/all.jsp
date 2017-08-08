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
        <meta name="title" content="Персонал || Alex Coffee">
        <title>Персонал || Alex Coffee</title>
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
        <section id="persons">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Персонал</span>
                            <c:if test="${fn:length(users) eq 0}">
                                <span class="color-red"> - список пуст!</span>
                                <br>
                                <a href="<c:url value="/admin/user/add"/>" title="Добавить нового работника">
                                    <button class="btn btn-success" type="submit">Добавить</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${fn:length(users) gt 0}">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                        <table class="table">
                            <tr>
                                <th>Роль</th>
                                <th>Имя</th>
                                <td class="hidden-xs"><b>Телефон</b>
                                <td>
                                    <b>Действие</b>
                                    <a href="<c:url value="/admin/user/add"/>" title="Добавить нового работника">
                                        <button class="btn btn-success" type="submit">Добавить</button>
                                    </a>
                                    <a href="<c:url value="/admin/user/delete_all"/>" title="Удалить всех менеджеров">
                                        <button class="btn btn-danger" type="submit">Удалить всех</button>
                                    </a>
                                </td>
                            </tr>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.role eq admin_role}">
                                                <b><span class="color-red">${user.role.description}</span></b>
                                            </c:when>
                                            <c:when test="${user.role eq manager_role}">
                                                <span class="color-green">${user.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${user.role.description}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.name}</td>
                                    <td class="hidden-xs">${user.phone}</td>
                                    <td>
                                        <a href="<c:url value="/admin/user/view/${user.id}"/>"
                                           title="Смотреть информацию ${user.name}">
                                            <button class="btn btn-info btn-mg" type="submit">Смотреть</button>
                                        </a>
                                        <a href="<c:url value="/admin/user/edit/${user.id}"/>"
                                           title="Редактировать информацию о ${user.name}">
                                            <button class="btn btn-success btn-mg" type="submit">Редактировать</button>
                                        </a>
                                        <a href="<c:url value="/admin/user/delete/${user.id}"/>"
                                           title="Удалить информацию о ${user.name}">
                                            <button class="btn btn-danger btn-mg" type="submit">Удалить</button>
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
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
