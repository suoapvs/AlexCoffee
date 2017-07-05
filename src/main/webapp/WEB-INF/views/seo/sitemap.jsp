<%@ page contentType="application/xml;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:xml removeIntertagSpaces="true">
    <?xml version="1.0" encoding="UTF-8"?>
    <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
        <url>
            <loc>http://alexcoffee.com.ua/</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <url>
            <loc>http://alexcoffee.com.ua/index</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <url>
            <loc>http://alexcoffee.com.ua/home</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <c:if test="${fn:length(categories) gt 0}">
            <url>
                <loc>http://alexcoffee.com.ua/category/all</loc>
                <changefreq>weekly</changefreq>
                <priority>0.6</priority>
            </url>
            <c:forEach items="${categories}" var="category">
                <url>
                    <loc>http://alexcoffee.com.ua/category/${category.url}</loc>
                    <changefreq>weekly</changefreq>
                    <priority>0.7</priority>
                </url>
            </c:forEach>
        </c:if>
        <c:if test="${fn:length(products) gt 0}">
            <url>
                <loc>http://alexcoffee.com.ua/product/all</loc>
                <changefreq>weekly</changefreq>
                <priority>0.7</priority>
            </url>
            <c:forEach items="${products}" var="product">
                <url>
                    <loc>http://alexcoffee.com.ua/product/${product.url}</loc>
                    <changefreq>weekly</changefreq>
                    <priority>0.8</priority>
                </url>
            </c:forEach>
        </c:if>
        <url>
            <loc>https://yuriisalimov.github.io</loc>
            <changefreq>weekly</changefreq>
            <priority>0.5</priority>
        </url>
    </urlset>
</compress:xml>

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
