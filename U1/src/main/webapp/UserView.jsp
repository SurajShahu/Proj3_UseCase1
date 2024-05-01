<%@page import="com.rays.dto.UserDTO"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="UserListCtl">User List</a>
	<%
		UserDTO dto = (UserDTO) request.getAttribute("dto");
		String msg = (String) request.getAttribute("msg");
	%>
	<%
		if (dto != null) {
	%>
	<h1 align="center">Update User</h1>
	<%
		} else {
	%>
	<h1 align="center">Add User</h1>
	<%
		}
	%>
	<%
		if (msg != null) {
	%>
	<h3 align="center">
		<font color="green"><%=msg%></font>
	</h3>
	<%
		}
	%>
	<form action="UserCtl" method="post">
		<table align="center">
			<input type="hidden" name="id"
				value="<%=(dto != null) ? dto.getId() : ""%>">
			<tr>
				<th>FirstName :</th>
				<td><input type="text" name="firstName"
					value="<%=(dto != null) ? dto.getFirstName() : ""%>"></td>
			</tr>
			&nbsp;
			<tr>
				<th>LastName :</th>
				<td><input type="text" name="lastName"
					value="<%=(dto != null) ? dto.getLastName() : ""%>"></td>
			</tr>
			&nbsp;
			<tr>
				<th>LoginId :</th>
				<td><input type="text" name="loginId"
					value="<%=(dto != null) ? dto.getLoginId() : ""%>"></td>
			</tr>

			&nbsp;

			<tr>
				<th>Password :</th>
				<td><input type="password" name="password"
					value="<%=(dto != null) ? dto.getPassword() : ""%>"></td>
			</tr>
			&nbsp;

			<%
			Date dobDate = (dto != null) ? dto.getDob() : null;
			// Define output format (desired format: yyyy-MM-dd)
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Convert the Date object to string in yyyy-MM-dd format
			String formattedDob = (dobDate != null) ? outputFormat.format(dobDate) : "";
			%>
			<tr>
				<th>Dob :</th>
				<td><input type="date" name="dob"
					value="<%=(dto != null) ? formattedDob : ""%>"></td>
			</tr>
			&nbsp;

			<tr>
				<th>Address :</th>
				<td><input type="text" name="address"
					value="<%=(dto != null) ? dto.getAddress() : ""%>"></td>
			</tr>
			&nbsp;

			<tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=(dto != null) ? "Update" : "Save"%>"> <input
					type="submit" name="operation"
					value="<%=(dto != null) ? "Cancel" : "Reset"%>"></td>
			</tr>
		</table>
	</form>
</body>
</html>