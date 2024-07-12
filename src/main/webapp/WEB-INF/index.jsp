<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>        
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Products and Categories!</title>
		<!-- Integración de Bootstrap -->
    	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    	<style>
        /* Estilos adicionales */
        body {
            width: 970px; /* Ancho del cuerpo */
            margin: 0 auto; /* Centrado horizontal */
            padding: 20px; /* Espacio interior */
        }
        table {
            border: 2px solid black; /* Borde negro para las tablas */
            width: 100%; /* Ancho completo de la tabla */
            margin-bottom: 20px; /* Espacio inferior entre tablas */
        }
        table th, table td {
            padding: 10px; /* Espaciado interno de celdas */
        }
        h1 {
            text-align: center; /* Centrado del título */
            margin-bottom: 30px; /* Espacio inferior del título */
        }
    	</style>
	</head>
	<body>
		<div class="container">
			<div>
				<h1>Products</h1>
				<table class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Price</th>
							<th>Category Count</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productos}" var="producto">
						<tr>
							<td> <c:out value="${producto.name}"/> </td>
							<td> <c:out value="${producto.description}"/> </td>
							<td> <c:out value="${producto.price}"/> </td>
							<td> <c:out value="${producto.categories.size()}"/> categories </td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	
			
			<div>
				<h1>Categories</h1>
				<table class="table">
					<thead>
						<tr>
							<th>Category Name</th>
							<th>Product Count</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categorias}" var="categoria">
						<tr>
							<td> <c:out value="${categoria.name}"/> </td>
							<td> <c:out value="${categoria.products.size()}"/> products </td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>