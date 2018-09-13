package com.sp.avalon.client;

import org.junit.runner.Computer; 
import org.junit.runner.JUnitCore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sp.avalon.data.BookNotFoundException;
import com.sp.avalon.domain.Book;
import com.sp.avalon.integrationtests.ManagingBooksIntegrationTest;
import com.sp.avalon.services.BookService;
import com.sp.avalon.services.CustomerCreditExceededException;
import com.sp.avalon.services.PurchasingService;


public class Client 
{
	public static void main(String[] args)
	{
		//CLIENT MODE
		ClassPathXmlApplicationContext container= new ClassPathXmlApplicationContext("application_AnnotationAutowired.xml");
		try {
			PurchasingService purchasingService=container.getBean(PurchasingService.class);
			BookService bookService= (BookService) container.getBean("bookService");
			//begin
			bookService.registerNewBook(new Book("12234567","War&Peace", "Leo Tolstoy", 9.99));
			//commit

			try {
				//begin
				purchasingService.buyBook("12234567");
			}catch(BookNotFoundException e) {
				System.out.println("Sorry book not found with that isbn");
			}catch(CustomerCreditExceededException e) {
				System.out.println("Sorry you dont have enough credit");
			}

		}finally {
			container.close();
		}
		
		// INTEGRATION TESTING MODE
				
		Computer computer = new Computer();
		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.run(computer, ManagingBooksIntegrationTest.class);
	}
}
