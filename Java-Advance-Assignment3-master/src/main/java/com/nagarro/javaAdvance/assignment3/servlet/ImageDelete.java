package com.nagarro.javaAdvance.assignment3.servlet;

import java.io.IOException;
import java.io.Serial;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.nagarro.javaAdvance.assignment3.hibernate.HibernateUtil;
import com.nagarro.javaAdvance.assignment3.model.Image;
import com.nagarro.javaAdvance.assignment3.model.User;

/**
 * Servlet implementation class ImageDelete
 */
public class ImageDelete extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Welcome");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("imageId");
        int id = Integer.parseInt(str);
        Session session = HibernateUtil.sf.openSession();
        session.beginTransaction();
        Image i = session.get(Image.class, id);
        User u = i.getUser();
        u.getImageList().remove(i);
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

}
