<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Найденые компьютеры</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <jsp:useBean id="searchedComputers" class="beans.ComputerBean" scope="request"/>
        <div class="content">
            <h2 class="title">Найденые компьютеры</h2>
            <ul>
                <c:forEach items="#{searchedComputers.computers}" var="comp">
                    <li>
                        <c:out value="${comp.ip}@${comp.hostName}"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
