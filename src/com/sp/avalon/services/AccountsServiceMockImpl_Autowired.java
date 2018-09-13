package com.sp.avalon.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sp.avalon.domain.Book;

@Transactional(propagation=Propagation.SUPPORTS)
public class AccountsServiceMockImpl_Autowired implements AccountsService{

	@Override
	public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException{

		// basic implementation for testing
		System.out.println("Raised the invoice for " + requiredBook);
		//throw new CustomerCreditExceededException();

	}

}
