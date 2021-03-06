<%@ page import="bgroup.model.Org" %>
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
                <sec:authorize access="hasRole('LOGIN') or hasRole('ADMIN')">
                    <div class="row">
                        <span class="col s12 center-align">Терминалы</span>
                    </div>
                    <form action="#" method="post" class="" id="terminalForm">
                        <div class="row">
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <input type="text" class="datepicker" name="date" id="date" required
                                           onchange="clearResults()">
                                    <label for="date">Дата измерения</label>
                                </div>
                            </div>
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <input type="number" class="text" name="smena" id="smena" required>
                                    <label for="smena">Смена</label>
                                </div>
                            </div>
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <select name="org" id="org" required>
                                        <option value="" disabled selected></option>
                                        <c:forEach items="${orgList}" var="org">
                                            <option value="${org.getId()}">${org.getOrgName()}</option>
                                        </c:forEach>
                                    </select>
                                    <label>Предприятие</label>
                                </div>
                            </div>
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <select name="oil" id="oil" required>
                                        <option value="" disabled selected></option>
                                        <c:forEach items="${oilList}" var="oil">
                                            <option value="${oil.getId()}">${oil.getOil()}</option>
                                        </c:forEach>
                                    </select>
                                    <label>Топливо</label>
                                </div>
                            </div>
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <input type="number" class="text" name="vco" id="vco" required>
                                    <label for="vco">Объем СО</label>
                                </div>
                            </div>
                            <div class="col s2">
                                <div class="input-field col s12">
                                    <input type="number" class="text" name="vterm" id="vterm" required>
                                    <label for="vterm">Объем Терм</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <textarea class="materialize-textarea" name="prim" id="prim"></textarea>
                                    <label for="prim">Примечание</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <input type="button" value="Зарегистрировать" class="btn btn-primary btn-sm"
                                       onclick="createTerminal()"/>
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
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 3, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        format: 'dd.mm.yyyy',
        closeOnSelect: false // Close upon selecting a date,
    });
    $(document).ready(function () {
        $('select').material_select();
    });

    function clearResults() {
        document.getElementById("results").innerHTML = "";
    }

    function createTerminal() {
        var msg = $('#terminalForm').serialize();
        $.ajax({
            type: 'POST',
            url: 'createTerminal',
            data: msg,
            success: function (data) {
                if (data == 0) {
                    $('#terminalForm')[0].reset();
                    document.getElementById("results").innerHTML = "Показания записаны";
                }
                else if (data < 0)
                    document.getElementById("results").innerHTML = "Ошибка: " + data;
            },
            error: function (xhr, str) {
                //$('.results').html('Возникла ошибка: ' + xhr.responseCode);
                document.getElementById("results").innerHTML = "Возникла ошибка: " + xhr.responseCode;
            }
        });
    }
</script>
<%@include file="footer.jsp" %>