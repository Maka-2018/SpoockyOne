package com.booker.test

import com.booker.impl.BookRepositoryImpl
import com.booker.impl.BookServiceImpl
import com.booker.model.Book
import com.booker.repository.BookRepository
import com.booker.service.BookService
import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class BookRepositorySpecification extends Specification{

	private BookService bookService;
	private BookRepository bookRepository;
	
	def setupSep(){
		log.debug("setupSep() - Runs once Specification");
	}
	def setup(){
		log.debug ("setup() - Runs before every method");
		bookRepository = new BookRepositoryImpl();
		bookService = Stub(BookService);
		bookRepository.setBookService(bookService)
		
		bookService.retrieveBookDetails(_) >> { int id ->
			if (id == 1)
			{
				Book b = new Book(1, 'Robert Chevalier', 'Java 5 & 6');
				log.debug(b.toString());
				return b;
			}
			else if (id == 2)
			{
				Book b = new Book(2, 'Eugene Hayet', 'JUnit and Spock');
				log.debug(b.toString());
				return b;
			}
			else if (id == 3)
			{
				log.debug("Book ID does not exist");
				return null;
			}
			else if (id <= 0) 
			{
				throw new IllegalArgumentException("Invalid BookID");				
			}			 
			
		}
	
	}		
	def "retrieved if book is not null"(){
		log.debug("Method 1 - retrieved book object is not null- start");
		expect :
			bookRepository.getBook(id) != null
		where :
			id << [1, 2]
    }	
	def "retrieved if book is null"(){
		log.debug("Method - 2 retrieved book object is null - start");
		expect :
		  bookRepository.getBook(id) == null
		where :
		  id << 3
	}
	def "retrieve bookId must be greater than 0"(){
		log.debug("Method 3 - book id must be greater than 0 - start");
		given :
		   		//NA			
		when:
		   bookRepository.getBook(-3)
		then:
		   thrown(IllegalArgumentException)
	}
	def cleanup(){
		log.debug ("Cleanup a method - Runs after every method.");		
	}
	def cleanupSpec(){
		log.debug ("cleanupSpec() - Runs only once specification");
	}
		
}
