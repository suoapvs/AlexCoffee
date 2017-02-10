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
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="${user.name} | ${user.role.description} || Alex Coffee">
        <title>${user.name} | ${user.role.description} || Alex Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/manager/template/manager_navbar.jsp"/>
    <div class="container-fluid">
        <section id="product">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b><span class="color-brown">${user.name}</span></b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Имя:</th>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <th>Роль:</th>
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
                        </tr>
                        <tr>
                            <th>Логин:</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><a href="mailto:${user.email}" title="Email" target="_blank">${user.email}</a></td>
                        </tr>
                        <tr>
                            <th>Телефон:</th>
                            <td>${user.phone}</td>
                        </tr>
                        <c:if test="${(user.vkontakte ne null) and (user.vkontakte ne '')}">
                            <tr>
                                <th>ВКонтакте:</th>
                                <td>
                                    <a href="https://${user.vkontakte}" title="ВКонтакте"
                                       target="_blank">${user.vkontakte}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${(user.facebook ne null) and (user.facebook ne '')}">
                            <tr>
                                <th>Facebook:</th>
                                <td>
                                    <a href="https://${user.facebook}" title="Facebook"
                                       target="_blank">${user.facebook}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${(user.skype ne null) and (user.skype ne '')}">
                            <tr>
                                <th>Skype:</th>
                                <td>
                                    <a href="skype:${user.skype}?call" title="Skype"
                                       target="_blank">${user.skype}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${(user.description ne null) and (user.description ne '')}">
                            <tr>
                                <th>Описание:</th>
                                <td>${user.description}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/managers/user/all"/>" title="Вернуться к списку пользователей">
                                    <button class="btn btn-info" type="submit">Назад</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
