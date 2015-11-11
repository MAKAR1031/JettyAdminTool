<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Сервера</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <jsp:useBean id="serverBean" scope="session" class="beans.ServerBean"/>
        <jsp:setProperty name="serverBean" property="idComputer" value="${sessionScope.idComputer}"/>
        <div class="content">
            <h1 class="title">Сервера: ${sessionScope.idComputer}</h1>
            <table class="data_table">
                <tr>
                    <th>Директория</th>
                    <th>Порт</th>
                    <th>Комментарий</th>
                    <th colspan="2">
                        Действия
                    </th>
                    <c:forEach items="${serverBean.servers}" var="row">
                    <tr>
                        <td>
                            ${row.directory}
                        </td>
                        <td>
                            ${row.port}
                        </td>
                        <th>
                            ${row.comment}
                        </th>
                        <td>
                            <form method="GET" action="<c:url value="/Controller"/>">
                                <input type="hidden" name="id_serv" value="${row.id}"/>
                                <input type="hidden" name="action" value="show_apps"/>
                                <input type="submit" value="Приложения"/>
                            </form>
                        </td>
                        <td>
                            <form method="GET" action="<c:url value="/Controller"/>">
                                <input type="hidden" name="id_serv" value="${row.id}"/>
                                <input type="hidden" name="action" value="rem_serv"/>
                                <input type="submit" value="Удалить"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tr>
            </table>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="rem_serv"/>
                    <input type="submit" value="Добавить сервер" name="add_serv"/>
                </form>
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="auto_serv_search"/>
                    <input type="submit" value="Автоматический поиск серверов"
                           onclick="alert('В разработке...');
                                   return false" />
                </form>
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_comp"/>
                    <input type="submit" value="Вернуться к компьютерам"/>
                </form>
            </div>
        </div>
    </body>
</html>