<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark text-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">ADLISTER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/ads">Index</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Profile
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <c:choose>
                            <c:when test="${user == null}">
                                <li><a class="dropdown-item" href="/login">Login</a></li>
                                <li><a class="dropdown-item" href="/register">Register</a></li>
                            </c:when>
                            <c:otherwise>

                                <li><a class="dropdown-item" href="/profile">Profile</a></li>
                                <li><a class="dropdown-item" href="/profile/update">Update</a></li>
                                <li><a class="dropdown-item" href="/logout">Logout</a></li>
                            </c:otherwise>

                        </c:choose>

                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Ads
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:choose>
                            <c:when test="${user == null}">
                                <li><a class="dropdown-item" href="/sort">Sort by User</a></li>
                                <li><a class="dropdown-item" href="/sortcat">Sort by Category</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="dropdown-item" href="/sort">Sort by User</a></li>
                                <li><a class="dropdown-item" href="/sortcat">Sort by Category</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="/ads/create">Create AD</a></li>
                                <li><a class="dropdown-item" href="/update-ads">Update Ad</a></li>
                                <li><a class="dropdown-item" href="/delete">Delete AD</a></li>
                            </c:otherwise>

                        </c:choose>




                    </ul>
                </li>
            </ul>
            <form class="d-flex" action="/ads/search" method="post">
                <input class="form-control me-2" type="search" name="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light text-nowrap" type="submit">Search ads</button>
            </form>
        </div>
    </div>
</nav>