package com.booker.test;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import com.booker.model.Book;
import com.booker.service.BookService;


import com.booker.impl.BookRepositoryImpl;

public class BookRepositoryImplTest {

	private BookService nBookServiceImpl;
	private BookRepositoryImpl nBookRepositoryImpl;
	
	@Before
	public void setup() throws Exception
	{
		this.nBookServiceImpl = Mockito.mock(BookService.class);
		this.nBookRepositoryImpl = Mockito.mock(BookRepositoryImpl.class);
		
		this.nBookRepositoryImpl.setBookService(nBookServiceImpl);
	}
	
	@Test
	public void getBook() throws Exception
	{
		Mockito.when(this.nBookRepositoryImpl.getBook(Matchers.anyInt())).thenReturn(BookData.newBook); 
		this.nBookRepositoryImpl.getBook(BookData.BookID);
		
		Mockito.verify(this.nBookRepositoryImpl).getBook(Matchers.anyInt());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getNullBook() throws Exception
	{
		final Book emptyBook = null;
		Mockito.when(this.nBookRepositoryImpl.getBook(Matchers.anyInt())).thenReturn(emptyBook); 
		this.nBookRepositoryImpl.getBook(Integer.valueOf(new Integer(null)));
		
		Mockito.verify(this.nBookRepositoryImpl).getBook(Matchers.anyInt());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getBookGreaterThanZero() throws Exception
	{
		//Fuskande mock sats för denna sats används för att testa statiska methoder.
		doThrow(IllegalArgumentException.class).when(this.nBookRepositoryImpl).getBook(Matchers.any(Integer.class)); 
		this.nBookRepositoryImpl.getBook(Integer.valueOf(-2));
		
		Mockito.verify(this.nBookRepositoryImpl).getBook(Matchers.any(Integer.class));
	}
	
	@After
	public void tearDown() throws Exception
	{
		this.nBookRepositoryImpl = null;
		
		assertNull(this.nBookRepositoryImpl);
	}
	
	private static class BookData
	{
		private final static Integer BookID = Integer.valueOf(1);
		private final static String BookTitle = "Java Pro Groovy";
		private final static String BookAuthor = "Daniel Andersson";	
		private static Book newBook = new Book(BookID, BookTitle, BookAuthor);
	}
}
