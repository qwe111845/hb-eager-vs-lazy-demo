package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .addAnnotatedClass(Course.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			System.out.println("find Course object with eager loading...");

			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("Instructor: " + tempInstructor);

			// option 1: call getter method while session is open
			System.out.println("Course: " + tempInstructor.getCourses());
						
			session.getTransaction().commit();
			System.out.println("Done!");
			    
			
			session.close();
			System.out.println("\nThe session is now colsed!\n");
	
			// resolve lazy loading issue

			// option 1: call getter method while session is open
			System.out.println("Course: " + tempInstructor.getCourses());

			
		} 
		finally {
			factory.close();
		}
	}

}
