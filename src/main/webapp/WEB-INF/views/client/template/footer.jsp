<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${auth_user ne null}">
    <c:set var="reqmap" value="../"/>
</c:if>
<div class="container-fluid width">
    <footer>
        <div class="row footer text-shadow text-center">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <div class="row footer text-shadow text-center">
                    <h3>
                        <span class="footer-text-label color-green">Alex</span>
                        <span class="footer-text-label color-brown">Coffee</span>
                    </h3>
                    <h3><img src="${reqmap}resources/img/main_icon.png" alt="Alex Coffee"></h3>
                    <h3>
                        <span class="footer-text-label color-green">Лучший магазин </span>
                        <span class="footer-text-label color-brown">кофе</span>
                    </h3>
                    <h5>
                        <a href="http://alexcoffee.com.ua"
                           title="Alex Coffee - Лучший магазин кофе">alexcoffee.com.ua</a>&nbsp;&copy;&nbsp;2016&nbsp;
                        <a href="https://www.linkedin.com/in/yurii-salimov"
                           title="Linkedin" target="_blank">
                            Yurii Salimov&nbsp;<img src="${reqmap}resources/img/mr_alex.png" alt="Mr. Alex"
                                                    title="Mr. Alex">
                        </a>
                    </h5>
                </div>
            </div>
        </div>
    </footer>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
