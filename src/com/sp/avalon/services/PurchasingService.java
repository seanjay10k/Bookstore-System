package com.sp.avalon.services;

import com.sp.avalon.data.BookNotFoundException;

public interface PurchasingService {


	public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException;


}
