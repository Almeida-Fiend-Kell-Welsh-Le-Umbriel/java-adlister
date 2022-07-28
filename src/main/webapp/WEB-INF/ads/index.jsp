<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <div class="row">
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6 card mx-2 my-2 border border-dark rounded border-3" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">Title: <c:out value="${ad.title}"/></h5>
                    <h6 class="card-subtitle mb-2 text-muted">Post ID: <c:out value="${ad.id}"/></h6>
                    <h6 class="card-subtitle mb-2 text-muted">Posted by User#<c:out value="${ad.userId}"/></h6>
                    <p class="card-text"><c:out value="${ad.description}"/></p>

                    <p class="card-text">Categories:
                        <c:forEach var="cat" items="${ad.categories}">
                            <c:out value="[${cat}] "/>
                        </c:forEach>
                    </p>
                    <form action="/ad" method="POST">
                        <button class="btn btn-info" name="id" value="${ad.id}">view details</button>
                    </form>

<%--                    individual ad link would redirect to single ad servlet --%>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>
