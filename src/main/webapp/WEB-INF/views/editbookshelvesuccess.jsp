<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<div class="alert alert-success">Półka na książki została edytowana</div>
<a href="<c:url value="/user/bookshelves"/>" class="btn btn-default">Wróć</a>
