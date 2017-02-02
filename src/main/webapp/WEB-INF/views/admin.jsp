<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="alert alert-success">Witaj administratorze ${pageContext.request.userPrincipal.name}!</div>
	<ul class="list-group">
		<li class="list-group-item"><a href="<c:url value="/admin/newgenre"/>">Dodaj kategorie</a></li>

	</ul>
