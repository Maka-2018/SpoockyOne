package com.booker.repository;

import com.booker.model.Book;

public interface BookRepository 
{
	public Book getBook(Integer bookId);
}
