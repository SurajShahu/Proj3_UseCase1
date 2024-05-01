package com.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.dto.UserDTO;
import com.rays.model.UserModel;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserDTO dto = new UserDTO();
		UserModel model = new UserModel();
		List list = model.search(dto);
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		String[] ids = req.getParameterValues("ids");
		UserDTO dto = new UserDTO();
		UserModel model = new UserModel();
		if (op.equalsIgnoreCase("delete")) {
			if (ids != null) {
				for (String id : ids) {
					UserDTO deletedto = new UserDTO();
					deletedto.setId(Integer.parseInt(id));
					model.delete(deletedto);
					req.setAttribute("msg", "Record delete sucessfully");
				}
			} else {
				req.setAttribute("msg", "Select Atleast one record");
			}
		}
		
		if (op.equalsIgnoreCase("new")) {
			resp.sendRedirect("UserCtl");
			return;
		}
		
		if (op.equals("search")) {
			
			if(req.getParameter("firstName")!=null && req.getParameter("firstName").length()>0) {
				dto.setFirstName(req.getParameter("firstName"));
			}
			
			
			String date=req.getParameter("dob1");
			System.out.println("IpDate"+date);
			if(date!=null && date.length()>0)
			{
				
				String inputDateString = date;
		        String inputDateFormat = "yyyy-MM-dd";

		        try {
		            // Parse the input date string into a Date object
		            Date inputDate = new SimpleDateFormat(inputDateFormat).parse(inputDateString);

		            // Format the Date object into the desired output format
		            String outputDateFormat = "yyyy-MM-dd HH:mm:ss.S";
		            String formattedDateString = new SimpleDateFormat(outputDateFormat).format(inputDate);

		            Date formattedDate = new SimpleDateFormat(outputDateFormat).parse(formattedDateString);
		            
		            // Print the formatted date
		            System.out.println("Formatted Date: " + formattedDate);
		            dto.setDob(formattedDate);
		        } catch (ParseException e) {
		            System.out.println("Error parsing date: " + e.getMessage());
		        }
				
			}
			
		}

		List list = model.search(dto);
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
		rd.forward(req, resp);
	}
}
