package com.booker.impl;

import com.booker.model.Book;
import com.booker.repository.BookRepository;
import com.booker.service.BookService;

public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	public void setBookRepository(BookRepository newBookRepository)
	{
		this.bookRepository = newBookRepository;
	}
	
	@Override
	public Book retrieveBookDetails(Integer bookId) 
	{
		if (bookId == null || bookId <= 0) 
		{
			throw new IllegalArgumentException("Book ID should not be null/zero");	
		}		
		return this.bookRepository.getBook(bookId);
	}

}
