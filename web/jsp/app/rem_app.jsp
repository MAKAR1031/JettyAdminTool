<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Удалить приложение</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h2 class="title">Вы действительно хотите удалить это приложение?</h2>
            <div class="button_block">
                <form method="POST" action="<c:url value='/Controller'/>">
                    <input type="hidden" name="action" value="rem_app_post"/>
                    <input class="button" type="submit" value="Удалить"/>
                </form>
                <form method="GET" action="<c:url value='/Controller'/>">
                    <input type="hidden" name="action" value="to_app"/>
                    <input class="button" type="submit" value="К списку приложений"/>
                </form>
            </div>
        </div>
    </body>
</html>
