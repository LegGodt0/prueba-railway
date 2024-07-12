<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>        
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body style="width: 970px; text-align:center;">
    <h1>${category.name}</h1>

    <div style="display: grid; grid-template-columns: 1fr 1fr;">
        <div>
            <h3>Productos:</h3>
            <table style="text-align: center;">
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Segundo div -->
        <div>
            <h2>Agregar Producto</h2>
            <form:form action="${pageContext.request.contextPath}/categories/${category.id}" method="post" modelAttribute="formProductos">
                <label for="product">Seleccionar Producto:</label>
                <form:select path="productId">
                    <form:option value="" label="Seleccione un producto" />
                    <form:options items="${unassignedProducts}" itemValue="id" itemLabel="name"/>
                </form:select>
                <button type="submit">Agregar</button>
            </form:form>
        </div>
    </div>
</body>
</html>