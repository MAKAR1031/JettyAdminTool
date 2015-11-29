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
        <div class="content">
            <h2 class="title">Произошла ошибка!</h2>
            <c:choose>
                <c:when test="${pageContext.errorData.throwable != null}">
                    <h2>${pageContext.errorData.throwable.message}</h2>
                    <p>
                        <c:forEach items="${pageContext.errorData.throwable.stackTrace}" var="row">
                            ${row}
                        </c:forEach>
                    </p>
                </c:when>
                <c:otherwise>
                    <c:out value="${requestScope.errorMessage}"/>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
