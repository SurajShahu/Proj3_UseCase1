
<%@page import="com.rays.dto.UserDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>
	<jsp:useBean id="dto" class="com.rays.dto.UserDTO" scope="request"></jsp:useBean>
	<form action="UserListCtl" method="post">
		<h1 align="center">User List</h1>
		<%
			List list = (List) request.getAttribute("list");
			Iterator it = list.iterator();
			int index = 1;
			String msg = (String) request.getAttribute("msg");
			if (msg != null) {
		%>
		<h3 align="center" style="color: red"><%=msg%></h3>
		<%
			}
		%>
		&nbsp;&nbsp;&nbsp;
		<table align="center">

			<tr>
				<th>First Name :</th>
				<td><input type="text" name="firstName"></td>
				<th>DOB :</th>
				<td><input type="date" name="dob1"></td>
				<td><input type="submit" name="operation" value="search"></td>
			</tr>
			<!-- <td><input type="submit" name="operation" value="Search"></td> -->

		</table>
		&nbsp;
		<table align="center" width="100%" cellpadding=7px border="2">
			<tr align="center" bgcolor="#ccffff">
				<th>Select</th>
				<th>S.NO</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>LoginId</th>
				<th>Password</th>
				<th>Dob</th>
				<th>Address</th>
				<th>Edit</th>
			</tr>
			<%
				while (it.hasNext()) {
					dto = (UserDTO) it.next();
			%>
			<tr align="center">
				<td><input type="checkbox" name="ids" value="<%=dto.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=dto.getFirstName()%></td>
				<td><%=dto.getLastName()%></td>
				<td><%=dto.getLoginId()%></td>
				<td><%=dto.getPassword()%></td>
				<td><%=dto.getDob()%></td>
				<td><%=dto.getAddress()%></td>
				<td><a href="UserCtl?id=<%=dto.getId()%>">[edit]</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<table align="center">
			<tr>
				<th></th>
				<td><input type="submit" name="operation" value="delete"></td>
				<th></th>
				<!-- <td><input type="submit" name="operation" value="new"></td>
				<td><input type="reset"></td> -->
			</tr>
		</table>

	</form>
</body>
</html>