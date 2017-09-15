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
            <form action="getContext" method="post" class="">
                <c:if test="${param.error != null}">
                    <div class="col s6 offset-s2">
                        <p>Не прошла атворизация.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="col s6 offset-s2">
                        <p>Вы успешно вышли.</p>
                    </div>
                </c:if>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input type="text" id="fio" name="fio"
                               class="autocomplete"
                               required autofocus/>
                        <label for="fio">ФИО</label>
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
<script type="text/javascript">
    (function ($) {
        $(function () {

            $('input.autocomplete').autocomplete({
                data: {
                    <c:forEach items="${usersList}" var="user">
                    "${user.getFio()}": null,
                    </c:forEach>
                    "": null
                },
                limit: 20, // The max amount of results that can be shown at once. Default: Infinity.
                onAutocomplete: function (val) {
                    // Callback function when value is autcompleted.
                },
                minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
            });
        })
    })(jQuery);
</script>
<%@ include file="footer.jsp" %>