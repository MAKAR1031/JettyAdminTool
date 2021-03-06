<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Компьютеры</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <jsp:useBean id="computerBean" scope="request" class="beans.ComputerBean"/>
        <div class="content">
            <h1 class="title">Компьютеры</h1>
            <table class="data_table">
                <tr>
                    <th>Имя хоста</th>
                    <th>IP</th>
                    <th colspan="2">Действия</th>
                </tr>
                <c:forEach items="${computerBean.computers}" var="row">
                    <tr>
                        <td>
                            <c:out value="${row.name}"/>
                        </td>
                        <td>
                            <c:out value="${row.ip}"/>
                        </td>
                        <td>
                            <form method="GET" action="<c:url value="/Controller"/>">
                                <input type="hidden" name="id_comp" value="${row.id}"/>
                                <input type="hidden" name="action" value="show_serv"/>
                                <input class="button" type="submit" value="Сервера" />
                            </form>
                        </td>
                        <td>
                            <form method="GET" action="<c:url value="/Controller"/>">
                                <input type="hidden" name="id_comp" value="${row.id}"/>
                                <input type="hidden" name="action" value="rem_comp"/>
                                <input class="button" type="submit" value="Удалить" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="add_comp"/>
                    <input class="button" type="submit" value="Добавить компьютер"/>
                </form>
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="search_comp"/>
                    <input class="button" type="submit" value="Автоматический поиск" 
                           onclick="(function () {
                               alert('Поиск может занять некоторое время, пожалуйста подождите\nДля продолжения нажмите Ок');
                               return true;
                           })()"/>
                </form>
            </div>
        </div>
    </body>
</html>