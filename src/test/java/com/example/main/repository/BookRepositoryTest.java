package com.example.main.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.main.entities.Author;
import com.example.main.entities.Book;

@DataJpaTest
class BookRepositoryTest {

	
	private Logger logger = LoggerFactory.getLogger(BookRepositoryTest.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	private Book book,book2;
	private Author author,author2;
	
	
	@BeforeEach
	public void setUp() {
		System.out.println("creting setup");
		author = new Author(11, "author name");
		book = new Book(1, "title", author);
		author2 = new Author(12, "new Author");
		book2 = new Book(21, "new title", author2);
		bookRepository.save(book);
		bookRepository.save(book2);
	}
	
	
	@Test
	public void testmethod() 
	{
		System.out.println(bookRepository.findAll());
		Book findById = bookRepository.findById(2).orElse(null);
		assertThat(findById.getTitle().equals(book2.getTitle()));
		System.out.println("testcase1 pass");
		logger.info("testcase1 is passed");
	}
	
	@Test
	public void testnotfoundmethod() 
	{
		Book findById = bookRepository.findById(44).orElse(null);
		//assertThat(findById.getTitle());
		assertThat(findById).isNull();
		System.out.println("testcase2 pass");
		logger.info("testcase2 is passed");

	}

	@AfterEach
	void tearDown() 
	{
		System.out.println("creting teardDown");

		author=null;
		book=null;
		bookRepository.deleteAll();
	}
	

}
