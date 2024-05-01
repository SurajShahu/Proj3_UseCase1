package com.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.dto.UserDTO;
import com.rays.model.UserModel;

@WebServlet("/UserCtl")
public class UserCtl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		UserDTO dto = new UserDTO();
		UserModel model = new UserModel();
		if (id != null) {
			dto = model.findByPk(Integer.parseInt(id));
			req.setAttribute("dto", dto);
		}
		RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		String id = req.getParameter("id");
		String op = req.getParameter("operation");
		UserModel model = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		dto.setFirstName(req.getParameter("firstName"));
		dto.setLastName(req.getParameter("lastName"));
		dto.setLoginId(req.getParameter("loginId"));
		dto.setPassword(req.getParameter("password"));
		String date = req.getParameter("dob");

		if (date != null) {
			try {
				dto.setDob(sdf.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		dto.setAddress(req.getParameter("address"));

		if (op.equalsIgnoreCase("update")) {
			dto.setId(Integer.parseInt(id));
			model.update(dto);
			req.setAttribute("dto", dto);
			req.setAttribute("msg", "User record updated successfully");
		}

		if (op.equalsIgnoreCase("save")) {
			model.add(dto);
			req.setAttribute("msg", "User record added successfully");

		}
		
		if (op.equalsIgnoreCase("reset")) {
			RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");
			rd.forward(req, resp);
		}
		
		if (op.equalsIgnoreCase("cancel")) {
			RequestDispatcher rd = req.getRequestDispatcher("UserListCtl");
			rd.forward(req, resp);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");
		rd.forward(req, resp);
	}
}
