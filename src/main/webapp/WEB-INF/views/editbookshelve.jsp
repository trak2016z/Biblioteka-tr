<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">Edycja półki na ksiażki</h1>
<p class="bg-danger">${message}</p>
<form:form modelAttribute="bookshelve" commandName="bookshelve" method="POST" enctype="utf8" target="_self">
    <div class="form-group">
        <label for="name">Nazwa:</label>
        <form:input type="text" value="" path="name" name="name" id="name" placeholder="Tytuł" required="required" cssClass="form-control" />
        <form:errors path="name" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="description">Opis:</label>
        <form:input type="text" value="" path="description" name="description" id="description" required="required" cssClass="form-control" />
        <form:input type="hidden" value="" path="user.id" name="user.id" id="user.id" />
        <form:errors path="description" element="div" cssClass="error" />
    </div>
    <input type="submit" id="newbook" value="Edytuj półkę" class="btn btn-default" />
</form:form>