<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="bgroup.model.SiteUser" %>

<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <sec:authorize access="hasRole('ROLE_LOGIN')">
            <div class="row">
                <sec:authorize access="hasRole('ROLE_USERS_ADD')">
                    <form action="usersadd" method="POST" class="">
                        <div class="row">
                            <div class="input-field col s6 offset-s2">
                                <input type="text" class="validate" id="username" name="username" required
                                       autofocus/>
                                <label for="username">Логин пользователя</label>
                            </div>
                        </div>
                        <div class="row">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="col s6 offset-s2">
                                <button class="btn waves-effect waves-light">Добавить пользователя
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s6 offset-s2">
                                <p class="text">${errorAddUser}</p>
                            </div>
                        </div>
                    </form>
                </sec:authorize>

            </div>
            <sec:authorize access="hasRole('ROLE_USERS')">
                <div class="row">
                    <c:forEach items="${listUsers}" var="siteUser">
                        <div class="row">
                            <div class="col s6 offset-s2">
                                <sec:authorize access="hasRole('ROLE_USERS_RED')">
                                    <form action="usersred" method="post">
                                        <input type="hidden" id="user-find-label" value="1"
                                               name="user-find-label">
                                        <input value="${siteUser.getName()}" name="buttonuserred"
                                               id="buttonuserred"
                                               type="hidden">
                                        <c:if test="${siteUser.getIsEnable()}">

                                            <button class="btn waves-effect green " type="submit"
                                                    style="width: 15em;">${siteUser.getName()}
                                            </button>
                                            </span>

                                        </c:if>
                                        <c:if test="${!siteUser.getIsEnable()}">

                                            <button class="btn waves-effect grey " type="submit"
                                                    style="width: 15em;">${siteUser.getName()}
                                            </button>

                                        </c:if>

                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
                                    </form>
                                </sec:authorize>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </sec:authorize>
        </sec:authorize>
    </div>
</main>
<%@ include file="footer.jsp" %>
