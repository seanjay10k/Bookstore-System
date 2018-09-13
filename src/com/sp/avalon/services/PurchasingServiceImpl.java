package com.sp.avalon.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sp.avalon.data.BookNotFoundException;
import com.sp.avalon.domain.Book;

@Transactional
public class PurchasingServiceImpl implements PurchasingService {

	private AccountsService accounts;
	private BookService books;

	public PurchasingServiceImpl() {

	}
	//rollback if any of these "checked" exception occurs
	//timeout at 10 sec, but if this method takes more than 10 seconds we get TransactionTimedOutException and RolledBack happens
	@Transactional(rollbackFor= {BookNotFoundException.class,CustomerCreditExceededException.class},
			timeout=10)
	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException{
		//get the correct book
		Book requiredBook= books.getBookByIsbn(isbn);
		//delete book from stock
		books.deleteFromStock(requiredBook);
		//raise the invoice
		accounts.raiseInvoice(requiredBook);
		/*	This is one way to force rollback on checked exception	
		 * try {
		//raise the invoice
		accounts.raiseInvoice(requiredBook);
		}catch(CustomerCreditExceededException e) {
			//tell spring to rollback because by default if checked exception happens spring wont automatically isssue a rollback at the end of transaction

			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//this doesnt automaticaaly issue rollback but commands spring to issue rollback when it is ready to issue commit
			//throw the exception down the line
			throw e;
		}
		 */
	}


	public void setAccountsService(AccountsService accounts) {

		this.accounts=accounts;

	}


	public void setBookService(BookService books) {

		this.books=books;


	}

}
