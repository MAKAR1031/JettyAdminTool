<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Приложения</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <jsp:useBean id="applicationBean" scope="session" class="beans.ApplicationBean"/>
        <jsp:setProperty name="applicationBean" property="idServer" value="${sessionScope.idServer}"/>
        <div class="content">
            <h1 class="title">Приложения: ${sessionScope.idServer}</h1>
            <table class="data_table">
                <tr>
                    <th>War</th>
                    <th>Context root</th>
                    <th>Deployer name</th>
                    <th>Deploy date</th>
                    <th>Комментарий</th>
                    <th>Удалить</th>
                </tr>
                <c:forEach items="${applicationBean.applications}" var="row">
                    <tr>
                        <td>
                            ${row.war}
                        </td>
                        <td>
                            ${row.contextRoot}
                        </td>
                        <td>
                            ${row.deployerName}
                        </td>
                        <td>
                            ${row.deployDate}
                        </td>
                        <td>
                            ${row.comment}
                        </td>
                        <td>
                            <form method="GET" action="<c:url value="/Controller"/>">
                                <input type="hidden" name="id_app" value="${row.id}"/>
                                <input type="hidden" name="action" value="rem_app"/>
                                <input type="submit" value="Удалить"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="add_app"/>
                    <input type="submit" value="Добавить приложение"/>
                </form>
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_serv"/>
                    <input type="submit" value="К списку серверов"/>
                </form>
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_comp"/>
                    <input type="submit" value="К списку компьютеров"/>
                </form>
            </div>
        </div>
    </body>
</html>
