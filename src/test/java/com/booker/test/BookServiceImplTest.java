package com.booker.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import com.booker.impl.BookServiceImpl;
import com.booker.model.Book;
import com.booker.repository.BookRepository;

public class BookServiceImplTest {

	private BookRepository nBookRepository;
	private BookServiceImpl nBookServiceImpl;
	
	@Before
	public void setup() throws Exception
	{
		this.nBookRepository = Mockito.mock(BookRepository.class);
		this.nBookServiceImpl = Mockito.mock(BookServiceImpl.class);
		this.nBookServiceImpl.setBookRepository(this.nBookRepository);
	}
	
	@Test
	public void retrieveBookDetails() throws Exception
	{		
		final Book expectedBook = new Book(1, "Java Framework", "Peter Hayet"); 	
		
		Mockito.when(this.nBookServiceImpl.retrieveBookDetails(Matchers.anyInt())).thenReturn(expectedBook);
		final Book resultBook = this.nBookServiceImpl.retrieveBookDetails(expectedBook.getBookId());
		Mockito.verify(this.nBookServiceImpl).retrieveBookDetails(Matchers.anyInt());		
		assertEquals(expectedBook, resultBook);
		assertEquals(expectedBook.getBookId(), resultBook.getBookId());
		 
		final Book bookActual = new Book();
		
		bookActual.setBookId(100);
		bookActual.setTitle("Groovy");
		bookActual.setAuthor("Peter Hayet");		
		Mockito.when(this.nBookServiceImpl.retrieveBookDetails(Matchers.anyInt())).thenReturn(bookActual);		
		Mockito.verify(this.nBookServiceImpl).retrieveBookDetails(Matchers.anyInt());		

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void retrieveNullBookIdDetails() throws Exception
	{			
		Mockito.when(this.nBookServiceImpl.retrieveBookDetails(Matchers.anyInt())).thenReturn(null);
		final Book resultBook = this.nBookServiceImpl.retrieveBookDetails(new Integer(null));
		Mockito.verify(this.nBookServiceImpl).retrieveBookDetails(Matchers.anyInt());
		
		assertEquals(null, resultBook.getAuthor());
	}
	
	@After
	public void tearDown() throws Exception
	{
		this.nBookRepository = null;
		this.nBookServiceImpl = null;
		
		assertNull(this.nBookRepository);
		assertNull(this.nBookServiceImpl);
		
	}
}
