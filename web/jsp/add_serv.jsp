<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить сервер</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h1 class="title">Добавить сервер</h1>
            <form method="POST" action="<c:url value="/Controller"/>">
                <div class="fields">
                    <label for="directory">Директория</label>
                    <input type="text" name="directory" id="directory" required="true"/><br/>

                    <label for="port">Порт</label>
                    <input type="text" name="port" id="port" required="true"/><br/>

                    <label for="comment">Комментарий</label>
                    <input type="text" name="comment" id="comment"/><br/>

                    <input type="submit" name="add_serv_post" value="Добавить"/>
                </div>
            </form>            
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">    
                    <input type="submit" name="to_serv" value="К списку серверов"/>
                </form>
            </div>
        </div>
    </body>
</html>
