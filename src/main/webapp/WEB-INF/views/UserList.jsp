<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>Name</td>
			<td>Email</td>
			<td>Address</td>
			<td>Phone</td>
		</tr>
		<c:forEach var="type" items="${users}">
			<tr>
				<td>${type.name}</td>
				<td><c:out value="${type.email}" /></td>
				

				<td><a href="editEmp/${user.id}">Edit</a></td>
				<td><a>Delete</a></td>/
				<td><a href="add-document/4">doc</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>