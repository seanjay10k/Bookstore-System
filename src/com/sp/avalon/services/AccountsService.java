package com.sp.avalon.services;

import com.sp.avalon.domain.Book;

public interface AccountsService {

	public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException;
}
