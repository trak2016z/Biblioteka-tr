<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA MOICH PÓŁEK <a href="<c:url value="/user/bookshelves/new" />" class="btn btn-success">nowa</a></h1>
<p class="bg-danger">${message}</p>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Funkcje</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookshelves}" var="b">
        <tr>
            <td>${b.id}</td>
            <td><a href="<c:url value="/user/bookshelves/view/${b.id}" />">${b.name}</a></td>
            <td>${b.description}</td>
            <td><a href="<c:url value="/user/bookshelves/edit/${b.id}" />" class="btn btn-info">edytuj</a> <a href="<c:url value="/user/bookshelves/delete/${b.id}" />" class="btn btn-danger">usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
