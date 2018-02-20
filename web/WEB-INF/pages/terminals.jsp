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
                        <span class="col s12 center-align">Терминалы</span>
                    </div>
                    <form action="saveCcard" method="post" class="">
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="cardNumber" id="cardNumber"
                                           pattern="\d\d\d\d\d\d" placeholder="шесть цифр"
                                           required/>
                                    <label for="cardNumber">Номер Карты</label>
                                </div>
                            </div>
                        </div>

                       <%-- <div class="row">
                            <div class="col s12">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" name="operatorNameCardOut"
                                           id="operatorNameCardOut"/>
                                    <label for="operatorNameCardOut">Кто выдал карту</label>
                                </div>
                            </div>
                        </div>
                        --%>
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

</script>
<%@include file="footer.jsp" %>