package com.sp.avalon.services;

import java.util.List;

import com.sp.avalon.data.BookNotFoundException;
import com.sp.avalon.domain.Book;

public class BookServiceTimingProxy implements BookService {

	private BookService originalBookService;



	public void setOriginalBookService(BookService originalBookService) {
		this.originalBookService = originalBookService;
	}

	@Override
	public List<Book> getAllBooksByAuthor(String author) {
		long nanoSecondsInMillisecond=1000000;

		//start the clock
		long timeThen= System.nanoTime();
		//work to me measured
		List<Book> allBooks= originalBookService.getAllBooksByAuthor(author);
		//stop clock
		long timeNow= System.nanoTime();
		//calculate		
		long timeTaken=timeNow-timeThen;
		System.out.println(" getAllBooksByAuthor(author) method took"+timeTaken/nanoSecondsInMillisecond +" milliseconds");

		return allBooks;
	}

	@Override
	public List<Book> getAllRecommendedBooks(String userId) {
		long nanoSecondsInMillisecond=1000000;

		//start the clock
		long timeThen= System.nanoTime();
		//work to me measured
		List<Book> allBooks= originalBookService.getAllRecommendedBooks(userId);
		//stop clock
		long timeNow= System.nanoTime();
		//calculate		
		long timeTaken=timeNow-timeThen;
		System.out.println(" getAllRecommendedBooks(userId) method took"+timeTaken/nanoSecondsInMillisecond +" milliseconds");

		return allBooks;
	}

	@Override
	public Book getBookByIsbn(String isbn) throws BookNotFoundException {

		long nanoSecondsInMillisecond=1000000;
		//start the clock
		long timeThen= System.nanoTime();
		//work to me measured
		try {
			Book book= originalBookService.getBookByIsbn(isbn);
			return book;
		}finally {


			//stop clock
			long timeNow= System.nanoTime();
			//calculate		
			long timeTaken=timeNow-timeThen;
			System.out.println(" getBookByIsbn(isbn) method took"+timeTaken/nanoSecondsInMillisecond +" milliseconds");
		}

	}

	@Override
	public List<Book> getEntireCatalogue() {
		long nanoSecondsInMillisecond=1000000;

		//start the clock
		long timeThen= System.nanoTime();
		//work to me measured
		List<Book> allBooks= originalBookService.getEntireCatalogue();
		//stop clock
		long timeNow= System.nanoTime();
		//calculate		
		long timeTaken=timeNow-timeThen;
		System.out.println(" getEntireCatalogue() method took"+timeTaken/nanoSecondsInMillisecond +" milliseconds");

		return allBooks;
	}

	@Override
	public void registerNewBook(Book newBook) {

		long nanoSecondsInMillisecond=1000000;

		//start the clock
		long timeThen= System.nanoTime();
		//work to me measured
		originalBookService.registerNewBook(newBook) ;
		//stop clock
		long timeNow= System.nanoTime();
		//calculate		
		long timeTaken=timeNow-timeThen;
		System.out.println(" registerNewBook(Book newBook) method took"+timeTaken/nanoSecondsInMillisecond +" milliseconds");



	}

	@Override
	public void deleteFromStock(Book oldBook) {
		// TODO Auto-generated method stub

	}

}
