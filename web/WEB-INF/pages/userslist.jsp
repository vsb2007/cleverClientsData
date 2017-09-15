<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col s12">
                <%@ include file="menu_cols.jsp" %>
                <div class="row">
                    <span class="col s12 center-align">Список пользователей </span>
                </div>
                <div class="row">
                    <div class="col s3 m2 l2">
                        <h6>Фамилия</h6>
                    </div>
                    <div class="col s3 m2 l2">
                        <h6>Имя</h6>
                    </div>
                    <div class="col l2 hide-on-med-and-down">
                        <h6>Телефон</h6>
                    </div>
                    <div class="col l2 hide-on-med-and-down">
                        <h6>Логин</h6>
                    </div>

                </div>
                <c:forEach items="${users}" var="user">
                    <div class="row ">
                        <div class="col s3 m2 l2">
                            <p class="collection-item">${user.lastName}</p>
                        </div>
                        <div class="col s3 m2 l2">
                            <p class="collection-item">${user.firstName}</p>
                        </div>
                        <div class="col l2 hide-on-med-and-down">
                            <p class="collection-item">${user.phone}</p>
                        </div>
                        <div class="col l2 hide-on-med-and-down">
                            <p class="collection-item">${user.userName}</p>
                        </div>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <div class="col s2 m1 l1">
                                <a href="<c:url value='/edit-user-${user.userName}' />"
                                   class="btn-floating green darken-4 ">
                                    <i class="large material-icons tiny">mode_edit</i>
                                </a>
                            </div>
                            <div class="col s2 m1 l1">
                                <a href="<c:url value='/delete-user-${user.userName}' />"
                                   class="btn-floating red darken-4">
                                    <i class="material-icons small">delete</i>
                                </a>
                            </div>
                        </sec:authorize>
                    </div>
                    <div class="divider"></div>
                </c:forEach>
            </div>
            <sec:authorize access="hasRole('ADMIN')">
                <div class="well">
                    <a href="<c:url value='/newuser' />" class="btn-floating red darken-4">
                        <i class="material-icons small left">add</i>Добавить пользователя</a>
                </div>
            </sec:authorize>
            <div class="row">
                <div class="col s12">
                    <div class="alert alert-success lead">
                        ${success}
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>