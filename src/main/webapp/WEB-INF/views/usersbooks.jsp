<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA MOICH KSIĄŻEK <a href="<c:url value="/user/books/new" />" class="btn btn-success">nowa</a></h1>
<p class="bg-danger">${message}</p>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Tytuł</th>
        <th>Gatunek</th>
        <th>Funkcje</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.id}</td>
            <td><a href="<c:url value="/books/view/${b.id}" />">${b.title}</a></td>
            <td>${b.genre.name}</td>
            <td><a href="<c:url value="/user/books/edit/${b.id}" />" class="btn btn-info">edytuj</a> <a href="<c:url value="/user/books/delete/${b.id}" />" class="btn btn-danger">usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
