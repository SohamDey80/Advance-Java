package com.cdac;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query; // Keep this import

public class StudentDAO 
{
	
	private SessionFactory getSessionFactory() {
		Configuration cfg = new Configuration();
	    cfg.configure("hibernate.cfg.xml");
	    // The buildSessionFactory() method should typically be called once
	    // in a utility class, but for simplicity, we'll keep it here for now.
	    return cfg.buildSessionFactory();
	}
	
	public void writeStudent(Student s) throws Exception 
	{
		SessionFactory factory = getSessionFactory();
		       
	    Session session = factory.openSession();
	    Transaction tx = session.beginTransaction();

	    session.persist(s); // Uses persist which is correct for new entities

	    tx.commit();
		session.close(); // IMPORTANT: Always close the session
	}

	public List<Student> getAllStudents()
	
	{
		List<Student> list = null;
		Session session = null;
		
		try {
			SessionFactory factory = getSessionFactory();
			session = factory.openSession();
			
			// FIX: Changed HQL from "from students" to "from Student"
			// HQL references the Java class name (Student), not the SQL table name (students).
			list = session.createQuery("from Student", Student.class).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		// Removed Transaction since it's a read operation
	    return list;
	}
}