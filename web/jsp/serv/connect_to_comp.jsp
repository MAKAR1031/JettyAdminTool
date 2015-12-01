<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поиск серверов - подключение к компьютеру</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h2 class="title">Подключение к компьютеру</h2>
            <form method="POST" action="<c:url value="/Controller"/>">
                <div class="fields">
                    <label for="">Имя пользователя</label>
                    <input type="text" id="user_name" name="user_name"/><br/>

                    <label for="">Пароль</label>
                    <input type="password" id="password" name="password"/><br/>

                    <input type="hidden" name="action" value="serv_search_post"/>
                    <input type="submit" value="Подключение"/>
                </div>
            </form>
            <div class="button_block">
                <form method="GET" action="<c:url value='/Controller'/>">
                    <input type="submit" name="action" value="to_serv"/>
                    <input type="submit" value="Вернуться к списку серверов"/>
                </form>
            </div>
        </div>
    </body>
</html>
