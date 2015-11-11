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
            <form method="GET" class="login_form" action="<c:url value="/Controller"/>">
                <input type="hidden" name="action" value="login" />
                <input type="submit" value="Войти"/>
            </form>
        </div>
    </body>
</html>
