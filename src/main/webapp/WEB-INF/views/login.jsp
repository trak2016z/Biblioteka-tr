<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1>PANEL LOGOWANIA</h1>
<c:if test="${not empty msg}">
	<div class="alert alert-danger">${msg}</div>
</c:if>
	<form:form method="POST" target="_self">
		<div class="form-group">
			<label for="username">Adres email:</label>
			<input type="email" name="username" value="" id="username" placeholder="adres@email.pl" class="form-control" required />
		</div>
		<div class="form-group">
			<label for="password">Hasło:</label>
			<input type="password" name="password" id="password" placeholder="Tajne hasło" class="form-control" required />
		</div>
		<input type="submit" id="login" value="zaloguj się" class="btn btn-default" />
	</form:form>