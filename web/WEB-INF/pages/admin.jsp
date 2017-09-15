<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<main>
    <div class="container">
        <%-- <sec:authorize access="!hasRole('ROLE_LOGIN')"> --%>
        <div class="row">
            <c:url var="loginUrl" value="/admin"/>
            <form action="${loginUrl}" method="post" class="">
                <c:if test="${param.error != null}">
                    <div class="col s6 offset-s2">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="col s6 offset-s2">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="text" class="validate" id="username" name="userName" required autofocus/>
                        <label for="username">Login</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="password" class="validate" id="password" name="password" required/>
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="col s6 offset-s2">
                        <button class="btn waves-effect waves-light">Вход
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <%-- </sec:authorize> --%>
    </div>
</main>
<%@ include file="footer.jsp" %>