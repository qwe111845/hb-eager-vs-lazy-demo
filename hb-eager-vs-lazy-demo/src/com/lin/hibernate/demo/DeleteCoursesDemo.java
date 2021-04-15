package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Course;
import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;

public class DeleteCoursesDemo {

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
			System.out.println("Creating new Instruct object...");

			session.beginTransaction();
			
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			Course tempCourse1 = new Course("Air Guitar");
			Course tempCourse2 = new Course("Data structure");

			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);

			session.save(tempCourse1);
			session.save(tempCourse2);

			System.out.println("Saving the Course...");
			
			session.getTransaction().commit();
			System.out.println("Done!");

			
		} 
		finally {
			factory.close();
		}
	}

}
