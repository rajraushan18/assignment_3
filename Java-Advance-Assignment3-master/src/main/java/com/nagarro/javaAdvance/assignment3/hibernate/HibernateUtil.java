package com.nagarro.javaAdvance.assignment3.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();
}
