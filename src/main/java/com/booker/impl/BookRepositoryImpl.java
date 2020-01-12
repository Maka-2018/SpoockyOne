package com.booker.impl;

import com.booker.model.Book;
import com.booker.repository.BookRepository;
import com.booker.service.BookService;

public class BookRepositoryImpl implements BookRepository{

	private BookService bookService;

	public BookService getBookService() 
	{
		return this.bookService;
	}
	
	public void setBookService(BookService newBookService) 
	{
		this.bookService = newBookService;
	}

	@Override
	public Book getBook(Integer bookId) 
	{
		if (bookId == null || bookId <= 0) 
		{
			throw new IllegalArgumentException("Book ID should not be null/zero");	
		}
		return this.bookService.retrieveBookDetails(bookId);
	}

}
