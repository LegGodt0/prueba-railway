<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Category!</title>
	</head>
	<body>
		<form:form action="/categories/new" method="POST" modelAttribute="categoria"> <!-- La acción se une con el controlador -->
			<h1>New Category</h1>
				<div style="margin-top: 10px;">
					<form:label path="name"> Name: </form:label> <!-- Debe coincidir el path del label y el input -->
					<form:input path="name" type="text" name="name"/> <!--  El path debe coincidir con el nombre de la variable global dentro del model -->
					<form:errors path="name" /> <!--  Con esta etiqueta se despliegan los mensajes de error si es que existen  -->
				</div>
				
				<button style="margin-top: 10px;">Create</button>
			</form:form>
	</body>
</html>