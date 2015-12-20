<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
    </head>
    <body>
        <div class="header">
            <div class="text_logo">
                Jetty Admin Tool
            </div>
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <form method="POST" class="login_form" action="<c:url value="/LoginController"/>">
                        <input type="hidden" name="action" value="login" />
                        <input class="button" type="submit" value="Войти"/>
                    </form>
                </c:when>
                <c:otherwise>
                    <form method="POST" class="login_form" action="<c:url value="/LoginController"/>">
                        <input type="hidden" name="action" value="logoff" />
                        <input class="button" type="submit" value="Выйти"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
