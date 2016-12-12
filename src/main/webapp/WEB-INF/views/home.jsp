<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<ul class="list-group">
	<li class="list-group-item"><a href="<c:url value="/login" />" >Panel logowania</a></li>
	<li class="list-group-item"><a href="<c:url value="/registration" />">Rejestracja</a></li>
	<li class="list-group-item"><a href="<c:url value="/forgotpassword" />">Przypomnienie hasła</a></li>
</ul>
