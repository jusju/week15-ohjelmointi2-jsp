<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta charset="utf-8">
	<title>Shoppinglist</title>
	<link rel="stylesheet" href="/styles/demo.css">
</head>

<body>
<h1>List of items to shop</h1>

	<ul>
		<c:forEach items="${ shoppingitems }" var="shoppingListItem">
			<li id="product-${ shoppingListItem.getId() }">
			<c:out value="${ shoppingListItem.getOstos() }" />
				<a href="list?id=${ shoppingListItem.getId() }">Remove</a>
			</li>
		</c:forEach>
	</ul>
	
</body>
</html>