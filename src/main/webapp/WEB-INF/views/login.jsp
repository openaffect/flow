<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var="pageTitle" value="Login" />

<%@include file="fragments/header.jsp" %>

<div class="columns">
  <div class="login-card">
    <div class="title">Login</div>
    <form id="login" method="POST" action="login.do">
      <input id="tfUsername" name="username" placeholder="Username">
      <input id="tfPassword" type="password" name="password" placeholder="Password">
      <button id="bLogin" type="submit">Login</button>
    </form>

  </div>
  <div class="register-card">
    <div class="title">Register</div>
    <form id="register" method="POST" action="register.do">
      <input id="tfUsername" name="username" placeholder="Username">
      <input id="tfFirstName" name="firstName" placeholder="First name">
      <input id="tfLastName" name="lastName" placeholder="Last name">
      <input id="tfEmail" name="email" placeholder="Email">
      <input id="tfPassword" type="password" name="password" placeholder="Password">
      <button id="bRegister" type="submit">Register</button>
    </form>
  </div>
</div>

<div class="messages">
  <c:forEach var="error" items="${errors}">
    <div class="error">${error}</div>
  </c:forEach>
</div>

<%@include file="fragments/footer.jsp" %>
