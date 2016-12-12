<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1>ZMIEŃ HASŁO</h1>
<p class="bg-danger">${message}</p>

				<form:form commandName="user" method="POST" enctype="utf8" target="_self">
					<div class="form-group">
						<label for="password">Hasło:</label>
						<input type="password" value="" name="password" id="password" placeholder="Tajne hasło" required="required" class="form-control" />
					</div>
					<div class="form-group">
						<label for="matchingPassword">Powtórz hasło:</label>
						<input type="password" value="" name="matchingPassword" id="matchingPassword" placeholder="Tajne hasło" required="required" class="form-control" />
					</div>
					<input type="submit" id="login" value="przypomnij" class="btn btn-default" />
				</form:form>
