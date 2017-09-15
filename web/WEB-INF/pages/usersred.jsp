<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="bgroup.model.SiteUser" %>

<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <sec:authorize access="hasRole('ROLE_USERS_RED')">
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <h4>Пользователь ${userRed.getName()}</h4>
                </div>
            </div>
            <form action="usersred" method="post" accept-charset="utf-8">
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="text" class="text-input border-green-500"
                               value="${userRed.getName()}"
                               id="user-name-label" name="user-name-label"
                        >
                        <label for="user-name-label">Имя пользователя</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="password" class="text-input border-green-500"
                               value=""
                               id="user-password-label" name="user-password-label"
                        >
                        <label for="user-password-label">Пароль</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="text" class="text-input border-green-500"
                               value="${userRed.getFio()}"
                               id="user-fio-label" name="user-fio-label"
                        >
                        <label for="user-fio-label">ФИО</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="tel" class="text-input border-green-500"
                               value="${userRed.getPhone()}"
                               id="user-phone-label" name="user-phone-label"
                        >
                        <label for="user-phone-label">Телефон</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="email" class="text-input border-green-500"
                               value="${userRed.getEmail()}"
                               id="user-email-label" name="user-email-label"
                        >
                        <label for="user-email-label">E-Mail</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <select class="dropdown-menu" id="postId" name="postId" onchange="checkCompany(this)">
                            <option value="-1">Сущность</option>
                            <c:forEach items="${postList}" var="post">
                                <c:if test="${post.getIdPost() == userRed.getPost().getIdPost()}">
                                    <option value="${post.getIdPost()}" selected>${post.getPostName()}</option>
                                </c:if>
                                <c:if test="${post.getIdPost() != userRed.getPost().getIdPost()}">
                                    <option value="${post.getIdPost()}">${post.getPostName()}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <label for="postId" class="label">Сущность</label>
                    </div>
                </div>

                <br>
                <span class="item-text">
    <div class="switch">
        <c:if test="${userRed.getIsEnable()}">
            <input type="checkbox" id="user-active-flag" name="user-active-flag" value="0" checked/>
        </c:if>
        <c:if test="${!userRed.getIsEnable()}">

            <input type="checkbox" id="user-active-flag" name="user-active-flag" value="0"/>
        </c:if>
        <label for="user-active-flag">&nbsp;</label>
        <span class="secondary-text">
        <label for="user-active-flag" class="label">Активен</label>
        </span>
    </div>

    </span>
                </li>
                </ul>
                <br>
                <input type="hidden" id="red_form" value="1" name="red_form">
                <input type="hidden" id="user-red-id-label" name="user-red-id-label" value="${userRed.getId()}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="token"/>
                <button class="button raised color-white bg-blue-500" type="submit" id="updateButton"
                        name="updateButton">
                    Сохранить
                </button>
            </form>
            <span class="secondary-text">
    <label for="updateButton" class="label color-green-500">${userRed.getError()}
    </label>
    </span>


            <!-- <div class="section">
            <form action="/usersred" method="post">
            <input type="hidden" id="delete_form" value="1" name="delete_form">
            <input type="hidden" id="user-delete-id-label" name="user-delete-id-label" value="${userRed.getId()}">
            <button class="button raised color-white bg-red-500" type="submit">Удалить</button>
            </form>
            </div>
            -->

        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USERS_RED')">
            <div class="section">
                <c:forEach
                        items="${roleList}"
                        var="role">
                    <div class="switch">
                        <c:if test="${role.getHasRole()==1}">
                            <input type="checkbox" id="r-${role.getRoleName()}"
                                   name="r-${role.getRoleName()}" value="1"
                                   onchange="changeRoleForUser(this,'${role.getRoleName()}')" checked/>
                        </c:if>
                        <c:if test="${role.getHasRole()==-1}">
                            <input type="checkbox" id="r-${role.getRoleName()}"
                                   name="r-${role.getRoleName()}" value="1"
                                   onchange="changeRoleForUser(this,'${role.getRoleName()}')"/>
                        </c:if>
                        <label for="r-${role.getRoleName()}">&nbsp;</label>
                        <span class="secondary-text">
                    <label for="r-${role.getRoleName()}" class="label">${role.getRoleName()} </label>
                    <span id="div${role.getRoleName()}"></span>
                </span>
                    </div>
                    <br>
                </c:forEach>
            </div>
            <script src="js/usersRed.js"></script>
        </sec:authorize>
    </div>
</main>
<%@ include file="footer.jsp" %>
