<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">LISTA KSIĄŻEK NA PÓŁCE ${bookshelf.name}</h1>
<p class="bg-danger">${message}</p>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Nazwa</th>
        <th>FUNKCJE</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookshelf.books}" var="b">
        <tr>
            <td>${b.id}</td>
            <td><a href="<c:url value="/books/view/${b.id}" />">${b.title}</a></td>
            <td><a href="<c:url value="/user/bookshelves/book-delete/${b.id}/${bookshelf.id}" />" class="btn btn-danger">usuń</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
