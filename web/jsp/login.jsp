<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h2 class="title">Авторизация</h2>
            <form method="POST" action="<c:url value="/LoginController"/>">
                <div class="fields">
                    <label for="username">Имя пользователя</label>
                    <input id="username" type="text" name="username" required="true"/><br/>

                    <label for="password">Пароль</label>
                    <input id="password" type="password" name="password" required="true"/><br/>

                    <input type="hidden" name="action" value="login_post"/>
                    <div class="button_block">
                        <input class="button" type="submit" value="Вход"/>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
