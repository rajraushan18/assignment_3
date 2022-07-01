package com.nagarro.javaAdvance.assignment3.servlet;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.nagarro.javaAdvance.assignment3.hibernate.HibernateUtil;
import com.nagarro.javaAdvance.assignment3.model.User;

/**
 * Servlet implementation class RegisterUser
 */
public class RegisterUser extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name  = request.getParameter("name");
		String userName  = request.getParameter("userName");
		String email  = request.getParameter("email");
		String pass  = request.getParameter("pass");
		
		User user = new User();
		user.setEmail(email);
		user.setFullName(name);
		user.setPass(pass);
		user.setUserName(userName);
		user.setImageList(new ArrayList<>());
		
		Session session = HibernateUtil.sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			response.getWriter().println("User could not be saved !! Try using unique Username");
			session.getTransaction().rollback();
			session.close() ;
			return ;
		}
		session.close() ;
		response.getWriter().print("Registered successfully");
		response.addCookie(new Cookie("username", userName));
		response.addCookie(new Cookie("pass", pass));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect("Welcome");
	}
}
