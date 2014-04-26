package com.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.data.Book;
import com.springtest.data.BookRepository;

@Service
public class BookServiceInternalImpl implements BookServiceInternal {

	@Autowired
	private BookRepository books;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createAnotherBook(Book book) {
		books.create(book);
	}

}
