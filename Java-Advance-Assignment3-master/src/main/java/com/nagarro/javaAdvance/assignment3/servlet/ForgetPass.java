package com.nagarro.javaAdvance.assignment3.servlet;

import java.io.IOException;
import java.io.Serial;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.nagarro.javaAdvance.assignment3.hibernate.HibernateUtil;
import com.nagarro.javaAdvance.assignment3.model.User;

/**
 * Servlet implementation class ForgetPass
 */
public class ForgetPass extends HttpServlet {
	@Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String email = request.getParameter("email");

        Session session = HibernateUtil.sf.openSession();
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = session.createQuery("from User where username=:username");
        query.setParameter("username", username);
        User user = query.getSingleResult();

        if (user == null)
            response.getWriter().print("No such User Exists");
        else if (user.getEmail().equalsIgnoreCase(email))
            response.getWriter().print("Your Password is : " + user.getPass());
        else
            response.getWriter().print("Sorry, Email and username doesn't match");

        session.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            resp.sendRedirect("Welcome");
            return;
        }
        for (Cookie c : cookies) {
            if (c.getName().equals("username"))
                resp.sendRedirect("ImageUtility");
        }
    }
}
