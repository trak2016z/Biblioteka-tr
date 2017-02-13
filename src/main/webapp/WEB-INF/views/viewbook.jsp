<%--
  Created by IntelliJ IDEA.
  User: Ilona
  Date: 2017-02-03
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">NOWA</h1>
<p class="bg-danger">${message}</p>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">${book.title}</h3>
    </div>
    <div class="panel-body"></div>
    <ul class="list-group">
        <li class="list-group-item"><b>Autor:</b> ${book.author}</li>
        <li class="list-group-item"><b>Data wydania:</b> ${book.date}</li>
        <li class="list-group-item"><b>ISBN:</b> ${book.ISBN}</li>
        <li class="list-group-item"><b>Stron:</b> ${book.pages}</li>
        <li class="list-group-item"><b>Gatunek:</b> ${book.genre.name}</li>
        <li class="list-group-item"><b>Wydawca:</b> ${book.publisher}</li>
    </ul>
</div>

<c:if test="${pageContext.request.userPrincipal.name != null}">

    <c:url value="/books/addtoshelf" var="post" />

    <h2>Dodaj do swojej półki</h2>
    <c:if test="${bookshelves.size() > 0}">
        <form action="${post}" method="POST" enctype="utf8">
            <div class="form-group">
                <label for="id">Nazwa:</label>
                <select name="shelvesid" id="id" class="form-control">
                    <c:forEach items="${bookshelves}" var="b">
                        <option value="${b.id}">${b.name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" value="${book.id}" name="id" id="id" />
            </div>
            <input type="submit" id="newbook" value="Dodaj na półkę" class="btn btn-success" />
        </form>
    </c:if>
    <c:if test="${bookshelves.size() == 0}">
        Brak zdefiniowanych półek...
    </c:if>
</c:if>

<h2>Recenzje</h2>
<c:forEach items="${book.reviews}" var="r">
    <div class="panel panel-warning">
        <div class="panel-heading"><b>Autor:</b> ${r.user.name} ${r.user.surname} (<small><b>ocena: ${r.mark}</b></small>)</div>
        <div class="panel-body">
                ${r.opinion}
        </div>
    </div>
</c:forEach>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Dodaj recenzję</h3>
        </div>
        <div class="panel-body">
            <form:form modelAttribute="review" commandName="review" method="POST" enctype="utf8" target="_self">
                <div class="form-group">
                    <label for="opinion">Opinia:</label>
                    <form:textarea value="" path="opinion" name="opinion" id="opinion" required="required" cssClass="form-control" />
                    <form:errors path="opinion" element="div" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="mark">Ocena:</label>
                    <form:select path="mark" id="mark" class="form-control" >
                        <option value="1"selected>1</option>
                        <option value="2"selected>2</option>
                        <option value="3"selected>3</option>
                        <option value="4"selected>4</option>
                        <option value="5"selected>5</option>
                    </form:select>
                    <form:errors path="opinion" element="div" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="spoiler">Czy spoiler?:</label>
                    <div class="radio">
                        <label><form:radiobutton path="spoiler" value="false" checked="checked"/>Nie</label>
                    </div>
                    <div class="radio">
                        <label><form:radiobutton path="spoiler" value="true"/>Tak</label>
                    </div>
                    <form:errors path="spoiler" element="div" cssClass="error" />
                </div>
                <input type="submit" id="dodaj" value="dodaj recenzję" class="btn btn-success" />
            </form:form>
        </div>
    </div>
</c:if>
