package com.springtest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.data.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Override
	public void createBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBookWithoutTx(Book book) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Book lookupBookById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
