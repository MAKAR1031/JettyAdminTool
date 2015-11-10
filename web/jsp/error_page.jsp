<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <jsp:include page="/jsp/header.jsp"/>
        <h2>${pageContext.errorData.throwable.message}</h2>
        <p>
            <c:forEach items="${pageContext.errorData.throwable.stackTrace}" var="row">
                ${row}
            </c:forEach>
        </p>
    </body>
</html>
