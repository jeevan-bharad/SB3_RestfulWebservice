package com.example.main.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.main.entities.Author;
import com.example.main.entities.Book;
import com.example.main.repository.BookRepository;

class BookServiceImplTest {

	private Logger logger = LoggerFactory.getLogger(BookServiceImplTest.class);
	
	@Mock
	private BookRepository bookRepository;
	//@InjectMocks
	private BookService bookService;//= new BookServiceImpl(bookRepository);;
	private AutoCloseable autoCloseable;
	private Book book;
	private Author author;
	
	
	@BeforeEach
	void setUp() throws Exception {
		logger.info("setup method started");
		autoCloseable = MockitoAnnotations.openMocks(this);
		bookService = new BookServiceImpl(bookRepository);
		author = new Author(1,"nana");
		book = new Book(1, "first", author);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.info("teardown method started");
		autoCloseable.close();
	}

	@Test
	void testGetAllBooks() {
		when(bookRepository.findAll()).thenReturn(new ArrayList<Book>(Collections.singleton(book)));
		assertThat(bookService.getAllBooks().get(0).getTitle()).isEqualTo(book.getTitle());
	}

	@Test
	void testGetBookByid() {
		
		when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));
		assertThat(bookService.getBookByid(1).getTitle()).isEqualTo(book.getTitle());
	}

	@Test
	void testUpdateBookByid() {
		when(bookRepository.save(book)).thenReturn(book);
		System.out.println("book1=>"+book);
		System.out.println("servicebook1=>"+bookService.addBook(book));

		assertThat(bookService.addBook(book)).isEqualTo(book);
	}

	@Test
	void testAddBook() {
		//mock(Book.class);
		//mock(BookRepository.class);
		
		when(bookRepository.save(book)).thenReturn(book);
		System.out.println("book1=>"+book);
		System.out.println("servicebook1=>"+bookService.addBook(book));

		assertThat(bookService.addBook(book)).isEqualTo(book);
	}

	@Test
	void testDelete() {
		mock(Book.class);
		mock(BookRepository.class,Mockito.CALLS_REAL_METHODS);
		
		doAnswer(Answers.CALLS_REAL_METHODS).when(bookRepository).deleteById(any());
		//assertThat(bookService.delete(1)).ise
		bookService.delete(1);
		 assertThat(bookRepository.findById(1)).isEmpty();
	}

}
