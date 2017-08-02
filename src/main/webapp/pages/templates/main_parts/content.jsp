<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/content.css">
<link rel="stylesheet" href="/css/block.css">

<div class="content contentbg mrg-b15 fright border mrg-l10">
    <p class="content-title">${contentTitle}</p>
    <div class="table-div">
        <c:forEach items="${list}" var="elem">
            <div class="block">
                <p>${elem}</p>
            </div>
        </c:forEach>
    </div>

    <div class="conteiner">
        <sec:authorize access="isAuthenticated()">
            <h1>Welcome ${pageContext.request.userPrincipal.name}!!!</h1>
            <sec:authorize access="hasRole('ADMIN')">
                <h4>
                    <a href="/admin/adminPage">You can go to admin page...</a>
                </h4>
            </sec:authorize>
            <form action="/user/logout" method="post" id="logoutForm">
                <input type="submit" class="btn-link" value="logout...">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </sec:authorize>

    </div>

    <div class="page-select">
        <c:if test="${pageNum > 1}">
        <a href="/toFirstPage" title="На початок"><<&nbsp;&nbsp;</a>
        <a href="/prevPage" title="Назад"><-&nbsp;&nbsp;</a></c:if>
        &nbsp;&nbsp;${pageNum}&nbsp;&nbsp;
        <c:if test="${!lastPage}">
        <a href="/nextPage" title="Вперід">&nbsp;&nbsp;-></a>
        <a href="/toLastPage" title="В кінець">&nbsp;&nbsp;>></a></c:if>
    </div>
</div>