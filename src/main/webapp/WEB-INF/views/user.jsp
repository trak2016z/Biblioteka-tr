<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="alert alert-success">Witaj użytkowniku ${pageContext.request.userPrincipal.name}!  </div>