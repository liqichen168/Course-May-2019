<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--<%--%>
<%--	String path = request.getContextPath();--%>
<%--	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>



<html>
<head>
<%--	<base href="<%=basePath%>">--%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to Clinic Login page</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="icon" href="css/favicon.ico">

	<!-- Bootstrap core CSS -->
	<link href="dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
</head>
<body id="body_index">
	<div id="div1">
		<h1>Welcome to Clinic! Please login or register!</h1>
		<c:if test="${not empty success}">
			<p style="color: green"><c:out value="${success}"></c:out></p>
			<%
				session.invalidate();
			%>
		</c:if>
		<c:if test="${not empty error}">
			<p style="color: red"><c:out value="${error}"></c:out></p>
			<%
				session.invalidate();
			%>
		</c:if>
	</div>
	<div id="lista">
    	<ul>
        	<li><a href="addUserPatient.html">Registration for Patient</a></li>
            <li><a href="addUserMedic.jsp">Registration for Medical Staff</a></li>
            <li><a href="adminLogin.jsp">Administration</a></li>
            <li><a href="login.html">Login</a></li>
            <li><a href="Logout">Logout</a></li>
        </ul>
    </div>
		<br>
		<br>
	<div>
		<form id="form1" action="Login" method="post">
			<table>
				<tr>
					<td style="color: white">Name:</td>
					<td><input type="text" class="form-control" name="name" required="required" /></td>
				</tr>
				<tr>
					<td style="color: white">Password:</td>
					<td><input type="password" class="form-control" name="password" required="required" /></td>
				</tr>
				<tr>
				<td></td>
						<td><input type="submit"class="btn btn-primary" value="login" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
