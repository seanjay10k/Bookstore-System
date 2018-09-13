package com.sp.avalon.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sp.avalon.domain.Book;


public class BookDaoCleanerJdbcImpl_Autowired implements BookDao {


	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
	private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
	private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";

	public BookDaoCleanerJdbcImpl_Autowired(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;

	}

	public void createTable() {
		try {
			jdbcTemplate.update(CREATE_TABLE_SQL);
			//TEMPLATE WILL CATCH THE ERROR/EXCEPTION, CHECKS AGAINST ITS LIST FOR A DATABASE AND THROWS MEANINGFUL UNCHECKED EXCEPTION
			//SINCE UNCHECKED NO COMPILE TIME ERROR, YOU WONT BE FORCED TO CATCH IT	
		}
		catch(BadSqlGrammarException e) {
			// If table already exist spring will throw BadSqlGrammerException from within
			System.out.println("Table already exists");
		}
	}

	@Override
	public List<Book> allBooks() {



		return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
	}

	@Override
	public Book findByIsbn(String isbn) throws BookNotFoundException{
		try {
			return jdbcTemplate.queryForObject("select * from book where isbn=?", new BookMapper(), isbn);
		}catch(EmptyResultDataAccessException erde) {
			throw new BookNotFoundException();
		}

	}

	@Override
	public void create(Book newBook) {

		jdbcTemplate.update(INSERT_BOOK_SQL, new Object[] {newBook.getIsbn(),newBook.getTitle(),newBook.getAuthor(),newBook.getPrice()});

	}

	@Override
	public void delete(Book redundantBook) {

		jdbcTemplate.update("delete from book where isbn=?",redundantBook.getIsbn());

	}

	@Override
	public List<Book> findBooksByAuthor(String author) {

		return jdbcTemplate.query("select * from book where author=?", new BookMapper(),author);
	}

}
class BMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {

		String isbn= rs.getString("ISBN");
		String title = rs.getString("TITLE");
		String author= rs.getString("AUTHOR");
		double price= rs.getDouble("PRICE");

		Book nextBook = new Book(isbn, title, author, price);
		return nextBook;
	}

}	




