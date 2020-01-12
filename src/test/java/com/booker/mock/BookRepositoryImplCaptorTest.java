package com.booker.mock;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.booker.impl.BookRepositoryImpl;
import com.booker.model.Book;
import com.booker.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookRepositoryImplCaptorTest {

	@Mock
	BookRepositoryImpl nBookRepositoryImpl;
	
	@Mock
	BookService nBookServiceImpl;

	@Before
	public void setUp() throws Exception
	{			
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void test_retrieveBookDetails_sanity() throws Exception{
		
		Assert.assertNotNull(nBookServiceImpl);
		Assert.assertNotNull(nBookRepositoryImpl);
	}
	
	@Test
	public void test_retrieveBookDetails() throws Exception{	
		
		Book nBookValue = new Book(Integer.valueOf(5), "ASP.NET Professional","July Market");
		
		Mockito.when(nBookServiceImpl.retrieveBookDetails(Matchers.anyInt())).thenReturn(nBookValue);
		
		nBookServiceImpl.retrieveBookDetails(nBookValue.getBookId());
		ArgumentCaptor<Integer> argumentInteger = ArgumentCaptor.forClass(Integer.class);

		Mockito.verify(nBookServiceImpl).retrieveBookDetails(argumentInteger.capture());		
		List<Integer> newBookIdValue = argumentInteger.getAllValues();

		System.out.print("[Captor Id: "+newBookIdValue.get(0)+"]\n"
				+ "[Book Id: "+nBookValue.getBookId()+" ]");

		Assert.assertNotNull(newBookIdValue.get(0));
		Assert.assertEquals(nBookValue.getBookId(), newBookIdValue.get(0));

	}
	
	@After
	public void tearDown() throws Exception{

		nBookRepositoryImpl = null;
		nBookServiceImpl = null;
		Assert.assertNull(nBookServiceImpl);
		Assert.assertNull(nBookRepositoryImpl);
	}
}