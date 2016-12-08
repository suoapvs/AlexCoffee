<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="width">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span><span class="icon-bar"></span>
                </button>
                <div class="navbar-brand text-shadow">
                    <a href="../home">
                        <span class="nav-text-label color-green">Alex</span>
                        <span class="nav-text-label color-brown">Coffee</span>
                    </a>
                    <a href="../manager">
                        <span class="nav-text-label color-green">M</span>
                    </a>
                </div>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <div id="menu-product">
                    <ul class="nav navbar-nav">
                        <li id="nav-orders"><a href="orders">Заказы</a></li>
                        <li id="nav-persons"><a href="users">Персонал</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li id="auth-user"><a href="view_user_${auth_user.id}">${auth_user.name}</a></li>
                        <li id="nav-logout">
                            <form class="form-signin" action="../logout" method="post">
                                <input type="hidden" id="username" name="username">
                                <button class="btn-logout btn btn-danger" type="submit">Выйти</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>
