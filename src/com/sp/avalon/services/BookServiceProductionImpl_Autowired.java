package com.sp.avalon.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sp.avalon.data.BookDao;
import com.sp.avalon.data.BookNotFoundException;
import com.sp.avalon.domain.Book;


@Transactional(propagation=Propagation.REQUIRED)
public class BookServiceProductionImpl_Autowired implements BookService {

	private BookDao dao;

	public BookServiceProductionImpl_Autowired(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Book> getAllBooksByAuthor(String author) {

		return dao.findBooksByAuthor(author);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Book> getAllRecommendedBooks(String userId) {
		throw new UnsupportedOperationException();
	}

	//@Transactional(readOnly=true)
	//override the transactional declared at class level and if this method is directly called from client, it tells connection that we are running on readonly
	// but if this or chain of this method tries to modify the db(insert, delete..), We get an error
	public Book getBookByIsbn(String isbn) throws BookNotFoundException{
		return dao.findByIsbn(isbn);
	}

	@Override
	public List<Book> getEntireCatalogue() {
		return dao.allBooks();
	}

	@Override
	public void registerNewBook(Book newBook) {
		// put book to database
		dao.create(newBook);
		//throw new NullPointerException();
	}

	@Override
	public void deleteFromStock(Book oldBook) {
		dao.delete(oldBook);

	}

}
