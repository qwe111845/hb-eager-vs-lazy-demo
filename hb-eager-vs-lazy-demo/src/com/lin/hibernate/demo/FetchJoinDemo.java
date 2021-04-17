package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			System.out.println("find Course object...");

			int theId = 1;
			
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i " 
							        + "JOIN FETCH i.courses "
							        + "where i.id=:theInstructorId", 
							Instructor.class);
			
			// set Parameter on query
			query.setParameter("theInstructorId", theId);
			
			// get Instructor
			Instructor tempInstructor = query.getSingleResult();
		
			System.out.println("Instructor: " + tempInstructor);
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
			session.close();
			System.out.println("\nThe session is now colsed!\n");
	
			// resolve lazy loading issue

			// option 2: Hibernate query with HQL
			System.out.println("Course: " + tempInstructor.getCourses());

			
		} 
		finally {
			factory.close();
		}
	}

}
