<html>
  <head>
    <title>Open Affect Flow - ${pageTitle}</title>
    <link rel="icon" href="/assets/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="assets/css/flow.css">
  </head>
  <body>
    <div class="navbar">
      <c:choose>
        <c:when test="${currentUser != null}">
          <div>
              ${currentUser.firstName} ${currentUser.lastName}
          </div>
          <form id="logoutForm" method="POST" action="logout.do">
            <button class="as-link" type="submit">Logout</button>
          </form>
        </c:when>
        <c:otherwise>
          <div></div>
        </c:otherwise>
      </c:choose>
    </div>
