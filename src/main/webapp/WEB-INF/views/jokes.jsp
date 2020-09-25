<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var="pageTitle" value="Jokes" />

<jsp:useBean scope="request" id="jokes" type="io.openaffect.flow.application.joke.JokesDTO"/>

<%@include file="fragments/header.jsp" %>

    <div class="columns">
      <div class="authoring">
        <div class="title">Submit your jokes</div>
        <form id="newJoke" method="POST" action="submitJoke.do">
          <textarea id="tfText" name="text" form="newJoke" rows="4"></textarea>
          <button id="bSubmitJoke" type="submit">Submit Joke</button>
        </form>
      </div>
      <div class="jokes">
        <div class="title">Jokes</div>
        <c:forEach var="joke" items="${jokes.jokes}">
          <div class="joke">${joke.text}</div>
        </c:forEach>
      </div>
    </div>

<%@include file="fragments/footer.jsp" %>
