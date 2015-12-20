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
                    <p>
                        <c:out value="${requestScope.errorMessage}"/>
                    </p>
                </c:otherwise>
            </c:choose>
            <div class="button_block">
                <form method="GET" action="<c:url value="/Controller"/>">
                    <input type="hidden" name="action" value="to_comp"/>
                    <input class="button" type="submit" value="К списку компьютеров"/>
                </form>
            </div>
        </div>
    </body>
</html>
