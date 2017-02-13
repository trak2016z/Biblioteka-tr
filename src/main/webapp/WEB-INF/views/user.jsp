<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="alert alert-success">Witaj użytkowniku ${pageContext.request.userPrincipal.name}!  </div>

<div class="btn-group" role="group" aria-label="...">
    <a href="<c:url value="/user/books"/>" class="btn btn-default">Dodaj książkę</a>
    <a href="<c:url value="/user/bookshelves"/>" class="btn btn-default">Moje półki</a>
</div>