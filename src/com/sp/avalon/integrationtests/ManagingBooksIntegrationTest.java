package com.sp.avalon.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sp.avalon.data.BookNotFoundException;
import com.sp.avalon.domain.Book;
import com.sp.avalon.services.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/application_AnnotationAutowired.xml"})
@Transactional //begin new Transaction at begin of every test method and rollback at their end. 
public class ManagingBooksIntegrationTest {
	@Autowired
	private BookService books;

	/* 
	@Before
	public void setup() {
		System.out.println("Running setup()");
		ClassPathXmlApplicationContext container=new ClassPathXmlApplicationContext("application_AnnotationAutowiredForJpa.xml");
		books= container.getBean(BookService.class);

	}
	Note:	
	@Before is from junit. This makes this code inside setup() run "everytime" "individual" test runs(slower).
	 Even if we put this code in Constructor, surprisingly same thing happens.
	 For Every @Test method, junit creates that many  instances of this class .
	 USE @RunWith(SpringJUnit4ClassRunner.class) at class level which enhances this class with extra features.
	 THEn, you can apply @ContextConfiguration({"application.xml"}), which creates the spring container and will create only one instance.
	 Hence, we dont create spring container every time a test runs but only once(faster). Note: inject dependency with @Autowired)
	 */

	@Test
	public void testFindingByIsbn() {
		//arrange

		String isbn="1933988134";
		Book testBook=new Book(isbn, "Spring in Action", "Craig Walls",23.99);
		books.registerNewBook(testBook);
		//act
		Book foundBook=null;

		try {
			foundBook=books.getBookByIsbn(isbn);
			assertEquals("The book returned is wrong one!", testBook,foundBook);//the string will output if test fails(not same book)
		} catch (BookNotFoundException e) {
			fail("No book was found when one should have been");//exception 
		}


	}

	@Test
	public void testAddingBooks() {
		//arrange

		//act
		books.registerNewBook(new Book("1933988134", "Spring in Action", "Craig Walls",23.99));
		books.registerNewBook(new Book("0764543857","Expert in J2EE","Rod Johnson",28.49));
		//assert
		int booksInDB= books.getEntireCatalogue().size();
		assertEquals("There Should Have Been 2 Books in DB",2,booksInDB);

	}
	@Test(expected=BookNotFoundException.class)//This test is trying to check if service properly throws exception. if does, pass.
	public void testFindingNonExistentBook() throws BookNotFoundException {
		//arrange

		//act
		Book foundBook=books.getBookByIsbn("xxxxxxxx");//if BookNotFoundException is not thrown, we get fail


	}


}




