<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="alert alert-success">Witaj użytkowniku ${pageContext.request.userPrincipal.name}!  </div>

Co teraz?

<div class="btn-group" role="group" aria-label="...">
    <a href="<c:url value="/newbook"/>" class="btn btn-default">Dodaj książkę</a>
    <button type="button" class="btn btn-default">Przeglądaj</button>
    <button type="button" class="btn btn-default">Coś tam</button>
</div>