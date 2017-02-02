<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<h1 class="pomarancz">NOWA</h1>
<p class="bg-danger">${message}</p>

    <div class="form-group">
        <label for="genre.id">Gatunek:</label>
        <form:select  path="genre.id" class="form-control">
            <c:forEach items="${genres}" var="g">
                <option value="${g.id}">${g.name}</option>
            </c:forEach>
        </form:select>

    </div>

    <input type="submit" id="newbook" value="Dodaj książkę" class="btn btn-default" />
</form:form>