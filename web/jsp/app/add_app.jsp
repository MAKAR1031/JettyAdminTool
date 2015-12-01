<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <div class="content">
            <h1 class="title">Добавить приложение</h1>
            <form method="POST" action="<c:url value="/Controller"/>">
                <div class="fields">
                    <label for="war">War</label>
                    <input type="text" name="war" required="true"/><br/>

                    <label for="context_root">Context root</label>
                    <input type="text" name="context_root" required="true"/><br/>

                    <label for="deployer_name">Deployer name</label>
                    <input type="text" name="deployer_name" required="true"/><br/>

                    <label for="deploy_date">Deploy date</label>
                    <input type="date" name="deploy_date" required="true"/><br/>

                    <label for="comment">Комментарий</label>
                    <input type="text" name="comment"/><br/>

                    <input type="hidden" name="action" value="add_app_post"/>
                    <input type="submit" value="Добавить"/>
                </div>
            </form>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_app"/>
                    <input type="submit" value="К списку приложений"/>
                </form>
            </div>
        </div>
    </body>
</html>
