<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<meta charset="utf-8">
	<title>Shoppinglist</title>
	<script src="/js/app.js"></script>
	<link rel="stylesheet" href="/styles/demo.css">
</head>

<body>
<h1>List of items to shop</h1>

	<ul>
		<c:forEach items="${ shoppingitems }" var="shoppingListItem">
			<li id="product-${ shoppingListItem.getId() }"><c:out value="${ shoppingListItem.getOstos() }" />
				<button onclick="removeProduct(${ shoppingListItem.getId() })">Remove</button>
			</li>
		</c:forEach>
	</ul>
	
</body>
</html>