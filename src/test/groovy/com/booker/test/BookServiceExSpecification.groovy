package com.booker.test

import spock.lang.Specification

import com.booker.impl.BookServiceImpl;
import com.booker.model.Book
import com.booker.service.BookService;
import com.booker.repository.BookRepository;

import spock.lang.*
import groovy.util.logging.Slf4j

@Slf4j
class BookServiceExSpecification extends Specification {
	
	BookServiceImpl bookService
	BookRepository bookRepository
	def setupSpec(){
		log.debug("setupSpec() - Runs once per Specification");
	}
	
	def setup(){
		log.debug ("setup() - Runs before every feature method");
		bookService = new BookServiceImpl();
		bookRepository = Stub(BookRepository);
		bookService.setBookRepository(bookRepository)
		
		bookRepository.getBook(_) >> { int id ->
			if (id == 1)
			{
				 Book b = new Book(1, 'Srujana', 'Spock Tut');
				 log.debug(b.toString());
				 return b;
			}
			 else if (id == 2)
			{
				Book b = new Book(2, 'Eugen', 'JUnit Tut');
				 log.debug(b.toString());
				return b;
			}
			else if (id == 3)
			{
				log.debug("Book with this ID does not exist");
				return null;
			}
		}
	}
	
	def "retrieved book object is not null"(){
		log.debug ("Feature method 1 - retrieved book object is not null- start");
		expect :
			bookService.retrieveBookDetails(id) != null
		where :
		id << [1, 2]
	}
	
	def "retrieved book object is null"(){
		log.debug ("Feature method - 2 retrieved book object is null - start");
		expect :
			bookService.retrieveBookDetails(id) == null
		where :
		id << 3
	}
	def cleanup(){
		log.debug ("Cleanup method - Runs  after every feature method.");
	}
	
	def cleanupSpec(){
		log.debug ("cleanupSpec() - Runs only once per specification");
	}
}