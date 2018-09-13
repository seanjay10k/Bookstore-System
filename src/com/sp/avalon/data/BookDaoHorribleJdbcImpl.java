package com.sp.avalon.data;

import java.util.List;

import com.sp.avalon.domain.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookDaoHorribleJdbcImpl implements BookDao
{
	// For HSQLDB, username is "sa" (System Administrator) with no password
	private static final String PASSWORD = "";
	private static final String USERNAME = "sa";

	// Driver and Database URL
	private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
	private static final String DATABASE_URL = "jdbc:hsqldb:file:database.dat;shutdown=true";

	// SQL
	private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
	private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
	private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";



	/**
	 * Instantiates the DAO and if necessary creates the table in the database.
	 */
	public BookDaoHorribleJdbcImpl()
	{
		try 
		{
			Class.forName(DRIVER_NAME);	
			createTables();

		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * We have added this method purely to make the sample code self contained. You can 
	 * just run our Java without worrying about setting up databases and tables
	 */
	private static void createTables()
	{
		try
		{
			Connection con = null;
			try
			{
				con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

				System.out.println(con);

				Statement stmt = con.createStatement();
				stmt.executeUpdate(CREATE_TABLE_SQL);
				System.out.println("Created new table BOOK");
			}
			finally
			{
				if (con!= null) con.close();
			}
		}
		catch (SQLException e)
		{
			if (e.getErrorCode() == 134){
				System.out.println("BOOK table has already been created...");
			}
			else
			{
				// raise another error
			}
		}

	}

	public void create(Book newBook) 
	{
		try 
		{
			Connection con = null;
			PreparedStatement insertBook = null;

			try
			{
				con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				insertBook = con.prepareStatement(INSERT_BOOK_SQL);
				insertBook.setString(1, newBook.getIsbn());
				insertBook.setString(2, newBook.getTitle());
				insertBook.setString(3, newBook.getAuthor());
				insertBook.setDouble(4, newBook.getPrice());
				insertBook.executeUpdate();
			}
			finally
			{
				if (insertBook != null)
					insertBook.close();
				if (con != null) {
					con.close();
				}
			}
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}

	public void delete(Book redundantBook) 
	{

		throw new java.lang.UnsupportedOperationException();
	}

	public List findBooksByAuthor(String author) 
	{
		throw new java.lang.UnsupportedOperationException();
	}

	public Book findByIsbn(String isbn) 
	{
		throw new java.lang.UnsupportedOperationException();
	}

	public List allBooks() 
	{
		try 
		{
			Connection con = null;
			PreparedStatement getBooks = null;
			List results = new ArrayList();
			ResultSet rs = null;

			try
			{
				con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				getBooks = con.prepareStatement(GET_ALL_BOOKS_SQL);
				rs = getBooks.executeQuery();
				while(rs.next())
				{
					String isbn = rs.getString(1);
					String title = rs.getString(2);
					String author = rs.getString(3);
					double price = rs.getDouble(4);
					Book nextBook = new Book(isbn, title, author, price);
					results.add(nextBook);
				}
				return results;
			}
			finally
			{
				if (rs != null)
					rs.close();
				if (getBooks != null)
					getBooks.close();
				if (con != null) {
					con.close();
				}
			}
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}

}
