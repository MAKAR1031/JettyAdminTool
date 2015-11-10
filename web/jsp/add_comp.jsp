<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить компьютер</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h1 class="title">Добавить компьютер</h1>
            <form method="POST" action="<c:url value="/Controller"/>">
                <div class="fields">
                    <label for="ip">IP</label>
                    <input type="text" name="ip" id="ip" required="true"/><br/>

                    <label for="host_name">Имя хоста</label>
                    <input type="text" name="host_name" id="host_name" required="true"/><br/>

                    <input type="submit" name="add_comp_post" value="Добавить"/>
                </div>
            </form>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="submit" name="to_comp" value="К списку компьютеров"/>
                </form>
            </div>
        </div>
    </body>
</html>
