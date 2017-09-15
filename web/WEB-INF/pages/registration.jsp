<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col s12">
                <%@ include file="menu_cols.jsp" %>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <div class="row">
                        <span class="col s12 center-align">Карточка пользователя</span>
                    </div>
                    <form:form method="POST" modelAttribute="user" id="regForm">
                        <form:input type="hidden" path="id" id="id"/>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="text" class="validate" path="lastName" id="lastName"/>
                                    <label for="lastName">Фамилия</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="text" class="validate" path="firstName" id="firstName"/>
                                    <label for="firstName">Имя</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="text" class="validate" path="patronymicName" id="patronymicName"/>
                                    <label for="patronymicName">Отчество</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="text" class="validate" path="phone" id="phone"
                                                pattern="7\d\d\d\d\d\d\d\d\d\d" placeholder="79223334444"/>
                                    <label for="phone">Телефон</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <form:input type="text" class="validate" path="userName" id="userName"
                                                        disabled="true"/>
                                            <label for="patronymicName">Логин</label>
                                            <form:input type="hidden" class="validate" path="password" id="password"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input type="text" class="validate" path="userName" id="userName"/>
                                            <form:input type="hidden" class="validate" path="password" id="password" value="111"/>
                                            <label for="patronymicName">Логин</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="email" class="validate" path="email" id="email"/>
                                    <label for="email">Электронный адрес</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <form:input type="number" class="validate" path="azs" id="azs"/>
                                    <label for="azs">Номер АЗС</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <div class="select-wrapper">
                                        <form:select path="userProfiles" items="${roles}" itemValue="id"
                                                     itemLabel="type" class="initialized" multiple="true"/>
                                    </div>
                                    <label>Роли</label>
                                </div>
                            </div>
                        </div>
                        <c:if test="${edit}">
                            <div class="row">
                                <div class="col s12">
                                    <label class="col s12" for="blocked">Блокировка</label>
                                    <div class="switch col s12">
                                        <label>
                                            Off
                                            <c:if test="${user.getBlocked()}">
                                                <input type="checkbox" path="blocked" checked id="blocked"
                                                       name="blocked" class="validate"/>
                                            </c:if>
                                            <c:if test="${!user.getBlocked()}">
                                                <input type="checkbox" path="blocked" id="blocked" name="blocked"
                                                       class="validate"/>
                                            </c:if>
                                            <span class="lever"></span>
                                            On
                                        </label>
                                    </div>

                                </div>
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col s12">
                                <c:choose>
                                    <c:when test="${edit}">
                                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                            href="<c:url value='/userslist' />">Cancel</a>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                                            href="<c:url value='/userslist' />">Cancel</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <input type="hidden" name="" value="${_csrf.token}" id="token"/>
                    </form:form>
                    <div class="row">
                        <div class="col s12">
                            <button class="btn waves-effect waves-light red" type="" name="action" onclick="sendFormChangePassword()">Поменять пароль
                                <i class="material-icons right">replay</i>
                            </button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <span id="results">${success}</span>
                        </div>
                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</main>
<script>
    $(document).ready(function () {
        $('select').material_select();
    });
    <c:if test="${edit}">
    function sendFormChangePassword2() {
        var msg = $('#regForm').serialize();
        $.ajax({
            type: 'POST',
            url: 'updatePassword',
            data: msg,
            success: function (data) {
                if (data == 1)
                    document.getElementById("results").innerHTML = "Пароль выслан";
                else document.getElementById("results").innerHTML = "Ошибка смены пароля";
            },
            error: function (xhr, str) {
                //$('.results').html('Возникла ошибка: ' + xhr.responseCode);
                document.getElementById("results").innerHTML = "Возникла ошибка: " + xhr.responseCode;
            }
        });

    }
    function sendFormChangePassword() {
        var id = document.getElementById("id");
        var xmlhttp;
        var idSpan = "results";
        document.getElementById(idSpan).innerHTML = "loading...";
        //document.getElementById(idSpan).value = orgDogId.value;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById(idSpan).innerHTML = xmlhttp.responseText;
            }
            else if (xmlhttp.status != 200 && xmlhttp.status != 0){
                document.getElementById(idSpan).innerHTML = "Ошибка какая-то возникла: " + xmlhttp.status;
            }
        }
        var token = document.getElementById("token");
        xmlhttp.open("POST", "updatePassword", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=" + id.value + "&_csrf=" + token.value);
    }

    </c:if>
</script>
<%@include file="footer.jsp" %>