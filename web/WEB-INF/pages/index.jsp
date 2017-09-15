<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <sec:authorize access="!hasRole('ROLE_LOGIN')">
            <div class="row">
                <form action="login" method="POST" class="">
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
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
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
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_LOGIN')">
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <h4>Здравствуйте
                        <c:if test="${loggedinuser != null && loggedinuser.getFio()!=null}">
                            <div class="col s6 offset-s2">
                                    ${loggedinuser.getFio()}
                            </div>
                        </c:if>
                    </h4>


                </div>
            </div>
        </sec:authorize>
    </div>
</main>
<%@ include file="footer.jsp" %>
