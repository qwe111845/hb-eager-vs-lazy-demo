package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new Instruct object...");

			Instructor tempInstructor = 
					new Instructor(
							"Lint",
							"Chent",
							"test12@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"https://www.youtube.com/watch?v=NCPH9JUFESA",
							"coding22");
			session.beginTransaction();
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			session.save(tempInstructor);
			
			System.out.println("Saving the Instruct...");
			
			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

}
