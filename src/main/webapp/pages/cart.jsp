<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/head.jsp"%>
<%@include file="templates/main_parts/header.jsp" %>
<%@include file="templates/main_parts/menu.jsp" %>

<div class="container fleft">

    <div class="col-sm-offset-1">
        <h3 style="margin-top: 11px">Корзина</h3>
    </div>

    <div class="container">
        <table class="table">
            <%--<thead>
            <tr>
                <th>#</th>
                <th>Image</th>
                <th>Article</th>
                <th>Name</th>
                <th>ProductGroup</th>
                <th>MeasUnits</th>
                <th>Price</th>
                <th>Description</th>
                <th>Change</th>
                <th>Remove</th>
            </tr>
            </thead>--%>
            <tbody>
            <c:set var="indexPG" value="1" scope="page" />
            <c:forEach items="${listProd}" var="product">
                <tr>
                    <td>${indexPG}</td>
                    <td><img src="${product.mainPicture}" alt="" style="width: 100px"></td>
                    <td>${product.article}</td>
                    <td>${product.name}</td>
                    <td>${product.measurementUnits.uaName}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                        <%--<td><a href="/admin/modifyProduct${product.id}">Modify</a></td>
                        <td><a href="/admin/removeProduct${product.id}">Delete</a></td>--%>
                </tr>
                <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>

<%@include file="templates/main_parts/footer.jsp" %>

</body>
</html>