package com.sp.avalon.data;

import java.util.List; 

import com.sp.avalon.domain.Book;

public interface BookDao {

	public List<Book> allBooks();
	public Book findByIsbn(String isbn) throws BookNotFoundException;
	public void create (Book newBook);
	public void delete(Book redundantBook);
	public List<Book> findBooksByAuthor(String author);

}
