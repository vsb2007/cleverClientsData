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
              <%--  <%@ include file="menu_cols.jsp" %> --%>
                <sec:authorize access="hasRole('LOGIN') or hasRole('ADMIN')">
                    <div class="row">
                        <span class="col s12 center-align">Карточка пользователя</span>
                    </div>
                    <form action="saveCcard" method="post" class="">
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="cardNumber" id="cardNumber" required/>
                                    <label for="cardNumber">Номер Карты</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="f" id="f" required/>
                                    <label for="f">Фамилия</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="i" id="i" required/>
                                    <label for="i">Имя</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="o" id="o" />
                                    <label for="o">Отчество</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="tel" class="validate" name="phoneNumber" id="phoneNumber" required
                                           pattern="\d\d\d\d\d\d\d\d\d\d" placeholder="9223334444"
                                           onkeyup="validatePhone(this)" onchange="validatePhone(this)"/>
                                    <label for="phoneNumber">Телефон</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="email" class="validate" name="email" id="email" />
                                    <label for="email">Электронный адрес</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="dateBirth" id="dateBirth" required
                                           pattern="[0-9]{2}.[0-9]{2}.[0-9]{4}" placeholder="01.01.1970"
                                           onkeyup="validateBirth(this,event.keyCode)"
                                           onchange="validateBirth(this,event.keyCode)"/>
                                    <label for="dateBirth">Дата Рождения</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <p>
                                    <input name="sex" type="radio" id="sex1" value="0" required/>
                                    <label for="sex1">Мужской пол</label>
                                </p>
                                <p>
                                    <input name="sex" type="radio" id="sex2" value="1" required/>
                                    <label for="sex2">Женский пол</label>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="vendorAuto" id="vendorAuto"/>
                                    <label for="vendorAuto">Марка Авто</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="operatorNameCardOut"
                                           id="operatorNameCardOut"/>
                                    <label for="operatorNameCardOut">Кто выдал карту</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <input type="submit" value="Зарегистрировать" class="btn btn-primary btn-sm"/>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="token"/>
                    </form>
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
<script type="text/javascript">
    function validateBirth(inp, keyCode) {
        str = inp.value;
        if (keyCode == 8 && (str.length == 2 || str.length == 5)) {
            inp.value = str.substring(0, str.length - 1);
            return;
        }
        str = str.replace(/[^.0-9]/gim, '');
        if (str[0] == ".") {
            inp.value = "";
            return;
        }
        if (str.charAt(1) == '.') {
            inp.value = str[0];
            return;
        }
        if (str.charAt(3) == '.') {
            inp.value = str.substring(0, 3);
            return;
        }
        if (str.charAt(4) == '.') {
            inp.value = str.substring(0, 4);
            return;
        }
        if (str.charAt(6) == '.') {
            inp.value = str.substring(0, 6);
            return;
        }
        if (str.charAt(7) == '.') {
            inp.value = str.substring(0, 7);
            return;
        }
        if (str.charAt(8) == '.') {
            inp.value = str.substring(0, 8);
            return;
        }
        if (str.charAt(9) == '.') {
            inp.value = str.substring(0, 9);
            return;
        }
        if (str.length == 2) str = str + ".";
        if (str.length == 5) str = str + ".";
        if (str.length > 10) str = str.substring(0, 10);
        inp.value = str;
    }

    function validatePhone(inp) {
        str = inp.value;
        str = str.replace(/[^0-9]/gim, '');
        if (str.length > 10) str = str.substring(0, 10);
        inp.value = str;
    }
</script>
<%@include file="footer.jsp" %>