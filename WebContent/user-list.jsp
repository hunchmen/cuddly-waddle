<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1" >
	<title>User Management Application</title>
	<link rel="stylesheet" href="<C:URL VALUE="./WEB-INF/VENDOR/CSS/BOOTSTRAP.MIN.CSS" />" />
	<script type="text/javascript" src="<c:url value="./WEB-INF/vendor/js/jquery-3.6.0.min.js" />" ></script>
</head>

<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark">
			<div>
				<a class="navbar-brand">User Management Application</a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath() %>/list" class="nav-link">Users List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.country}" /></td>
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
	
</body>

</html>