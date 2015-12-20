<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Найденные компьютеры</title>
    </head>
    <body>
        <c:import url="/jsp/header.jsp"/>
        <div class="content">
            <h2 class="title">Следующие компьютеры были найдены в сети</h2>
            <form method="POST" action="<c:url value="/Controller"/>">
                <table class="data_table">
                    <tr>
                        <th>Выбрать</th>
                        <th>IP</th>
                        <th>Имя</th>
                    </tr>
                    <c:set var="i" value="0"/>
                    <c:forEach items="${requestScope.searchedComputers}" var="comp">
                        <tr>
                            <td>
                                <input name="select_${i}" type="checkbox"/>
                            </td>
                            <td>
                                <c:out value="${comp.ip}"/>
                                <input type="hidden" name="ip_${i}" value="${comp.ip}"/>
                            </td>
                            <td>
                                <c:out value="${comp.name}"/>
                                <input type="hidden" name="name_${i}" value="${comp.name}"/>
                            </td>
                        </tr>
                        <c:set var="i" value="${i+1}"/>
                    </c:forEach>
                </table>
                <input type="hidden" name="total" value="${i}"/>
                <input type="hidden" name="action" value="search_comp_post"/>
                <div class="button_block">
                    <input class="button" type="submit" value="Добавить выбранные"/>
                </div>
            </form>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_comp"/>
                    <input class="button" type="submit" value="К списку компьютеров"/>
                </form>
            </div>
        </div>
    </body>
</html>
