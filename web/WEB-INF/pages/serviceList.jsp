<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <sec:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_SERVICELIST')">
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <%@ include file="menu_cols.jsp" %>
                    <div class="row">
                        <span class="col s12 center-align">Список услуг </span>
                    </div>
                    <div class="row">
                        <div class="col s3 m2 l2">
                            <h6>Дата</h6>
                        </div>
                        <div class="col s3 m2 l2">
                            <h6>Код</h6>
                        </div>
                        <div class="col l3 hide-on-med-and-down">
                            <h6>Наименование</h6>
                        </div>
                        <div class="col l2 m2 s2">
                            <h6>Сумма</h6>
                        </div>
                        <div class="col l2 m2 s2">
                            <h6>Партнер</h6>
                        </div>
                        <div class="col l1 m1 s1">
                            <h6>ID партнера</h6>
                        </div>
                    </div>
                    <c:forEach items="${serviceList}" var="service">
                        <div class="row ">
                            <div class="col s3 m2 l2">
                                <p class="collection-item">${service.dateDoneStr}</p>
                            </div>
                            <div class="col s3 m2 l2">
                                <p class="collection-item">${service.serviceCode}</p>
                            </div>
                            <div class="col l3 hide-on-med-and-down">
                                <p class="collection-item">${service.serviceName}</p>
                            </div>
                            <div class="col l2 m2 s2">
                                <p class="collection-item">${service.sumPayClient}</p>
                            </div>
                            <div class="col l2 m2 s2">
                                <p class="collection-item">${service.partnerFio}</p>
                            </div>
                            <div class="col l1 m1 s1">
                                <p class="collection-item">${service.partnerId}</p>
                            </div>
                                <%-- <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <div class="col s2 m1 l1">
                                        <a href="<c:url value='/delete-user-${user.userName}' />"
                                           class="btn-floating red darken-4">
                                            <i class="material-icons small">delete</i>
                                        </a>
                                    </div>
                                </sec:authorize>
                                --%>
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
    </sec:authorize>
</main>
<%@ include file="footer.jsp" %>