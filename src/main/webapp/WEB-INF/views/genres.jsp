<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">Lista gatunków <a href="<c:url value="/admin/newgenre" />" class="btn btn-success">nowa</a></h1>

<c:if test="${not empty message}">
    <div class="alert alert-danger">${message}</div>
</c:if>

<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>NAZWA</th>
        <th>FUNKCJE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${genres}" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.name}</td>
            <td><a href="<c:url value="/admin/editgenre/${g.id}" />" class="btn btn-info">edytuj</a> <a href="<c:url value="/admin/deletegenre/${g.id}" />" class="btn btn-danger">usuń</a></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
