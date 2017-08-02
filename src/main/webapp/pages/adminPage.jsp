<%@include file="templates/main_parts/head.jsp" %>

<div class="container">
    <h1>Welcome to Admin Page</h1>
    <form action="/user/logout" method="post" id="logoutForm">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <a href="javascript:formSubmit()">Close admin session</a>
</div>
<hr>
<br>
<div class="container">

</div>

<%--<c:if test="${showBuildedForm}">
    <%@include file="templates/forms/autoForms/f_entityAddData.jsp" %>
</c:if>--%>

</body>
</html>
