package com.fpe.hibernate.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.fpe.hibernate.entity.Instructor;
import com.fpe.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession(); 
		
		try {
			// cretae the objects
			Instructor tempInstructor = 
					new Instructor("Raul", "Montesinos", "raulmon@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://www.youtube.com/raulmon", "I like football!!");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save instructor
			// this will ALSO save the details objects
			// because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println(">>> Done!! <<<");
			
		}finally {
			factory.close();
		}

	}

}
