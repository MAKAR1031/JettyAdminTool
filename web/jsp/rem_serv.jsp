<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удалить сервер</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h1 class="title">Вы действительно хотите удалить этот сервер?</h1>
            <div class="button_block">
                <form method="POST" action="<c:url value='/Controller'/>">
                    <input type="submit" name="rem_serv_post" value="Удалить"/>
                </form>
                <form method="GET" action="<c:url value='/Controller'/>">
                    <input type="submit" name="to_serv" value="Вернуться к списку серверов"/>
                </form>
            </div>
        </div>
    </body>
</html>
