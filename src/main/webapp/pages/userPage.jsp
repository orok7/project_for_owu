<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/head.jsp"%>
<%@include file="templates/main_parts/header.jsp" %>
<%--<%@include file="templates/main_parts/menu.jsp" %>--%>

<div class="container-fluid fright">

    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header col-sm-3">
                <a class="navbar-brand" href="/user/userPage/main">${loggedUser.name} ${loggedUser.surname}</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="${userPageInvoice}"><a href="/user/userPage/invoice">Замовлення</a></li>
                <li class="${userPagePersonal}"><a href="/user/userPage/personal">Особисті дані</a></li>
                <li class="${userPageReview}"><a href="/user/userPage/review">Відгуки та оцінки</a></li>
                <li class="${userPageRating}"><a href="/user/userPage/rating">Змінити пароль</a></li>
            </ul>
        </div>
    </nav>

    <div class="container" style="display: ${upPersonalShow};">
        <h1>Personal data:</h1>
        <%@include file="templates/forms/f_userEdit.jsp"%>
    </div>

    <div class="container" style="display: ${upInvoiceShow};">
        <h1>Your orders:</h1>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Number</th>
                <th>Date</th>
                <th>Sum</th>
                <th>Payment Type</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="indexPG" value="1" scope="page" />
            <c:forEach items="${listInvoice}" var="invoice">
            <tr>
                <td>${indexPG}</td>
                <td>${invoice.id}</td>
                <td>${invoice.date}</td>
                <td>${invoice.sum}</td>
                <td>${invoice.paymentType.uaName}</td>
                <td>${invoice.status.uaName}</td>
                <td><a href="/user/userPage/detailsInvoice${invoice.id}">Details</a></td>
            </tr>
                <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="container" style="display: ${upReviewShow};">
        <h1>Your reviews:</h1>
        <div id="reviewsDiv" <%--class="" style="display: none;"--%>>
            <div class="">
                <c:forEach items="${reviews}" var="review">
                    <div class="review-div">
                        <div><h4 style="margin-bottom: 0"><span style="font-size: 0.75em;color: grey">
                                ${review.date}
                        </span></h4>
                            <div class="rating-div-sm" title="Оцінка ${review.rating}">
                                <div style="width: ${review.rating*20}%"></div>
                            </div>
                            <p>${review.reviews}</p></div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <br>

    <div class="container" style="display: ${upRatingShow};">
        <h1>Your can't change password now, please try again later</h1>
    </div>

</div>

<%@include file="templates/main_parts/footer.jsp" %>

</body>
</html>