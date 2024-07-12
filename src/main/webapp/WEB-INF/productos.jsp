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
		<h1>${product.name}</h1>
	
		<div style="display: grid; grid-template-columns: 1fr 1fr;">
			<div>
				<h3>Categories:</h3>
				<table style="text-align: center;">
				    <c:forEach var="category" items="${categories}">
				        <tr>
				            <td>${category.id}</td>
				            <td>${category.name}</td>
				        </tr>
				    </c:forEach>
				</table>
			</div>
			
			<!-- Segundo div -->
	        <div>
	            <h2>Agregar Categoría</h2>
	            <form:form action="${pageContext.request.contextPath}/products/${product.id}" method="post" modelAttribute="formCategorias">
	                <label for="category">Seleccionar Categoría:</label>
	                <form:select path="categoryId">
	                    <form:option value="" label="Seleccione una categoría" />
	                    <form:options items="${unassignedCategories}" itemValue="id" itemLabel="name"/>
	                </form:select>
	                <button type="submit">Agregar</button>
	            </form:form>
	        </div>
		</div>
	</body>
</html>