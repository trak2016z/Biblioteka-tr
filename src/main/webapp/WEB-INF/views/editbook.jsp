<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">EDYCJA KSIĄŻKI</h1>
<p class="bg-danger">${message}</p>
<form:form modelAttribute="book" commandName="book" method="POST" enctype="utf8" target="_self">
    <div class="form-group">
        <label for="title">Tytuł:</label>
        <form:input type="text" value="" path="title" name="title" id="title" placeholder="Tytuł" required="required" cssClass="form-control" />
        <form:errors path="title" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="date">Data publikacji:</label>
        <form:input type="text" value="" path="date" name="date" id="date" placeholder="" required="required" cssClass="form-control" />
        <form:errors path="date" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="pages">Liczba stron:</label>
        <form:input type="number" value="" path="pages" name="pages" id="pages" placeholder="" required="required" cssClass="form-control" />
        <form:errors path="pages" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="ISBN">Numer ISBN:</label>
        <form:input type="number" value="" path="ISBN" name="ISBN" id="ISBN" placeholder="" required="required" cssClass="form-control" />
        <form:errors path="ISBN" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="publisher">Wydawnictwo:</label>
        <form:input type="text" value="" path="publisher" name="publisher" id="publisher" placeholder="Wydawnictwo" required="required" cssClass="form-control" />
        <form:errors path="publisher" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="author">Autor:</label>
        <form:input type="text" value="" path="author" name="author" id="author" placeholder="Autor" required="required" cssClass="form-control" />
        <form:input type="hidden" value="" path="user.id" name="user.id" id="user.id" />
        <form:errors path="author" element="div" cssClass="error" />
    </div>
    <div class="form-group">
        <label for="genre.id">Gatunek:</label>
        <form:select  path="genre.id" class="form-control">
            <c:forEach items="${genres}" var="g">
                <c:choose>
                    <c:when test="${g.id==book.genre.id}">
                        <option value="${g.id}"selected>${g.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${g.id}">${g.name}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>

    </div>

    <input type="submit" id="newbook" value="Edytuj książkę" class="btn btn-default" />
</form:form>
</body>
</html>
