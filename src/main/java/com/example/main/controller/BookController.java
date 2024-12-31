package com.example.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.entities.Book;
import com.example.main.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBook() {
		List<Book> allBooks = this.service.getAllBooks();
		if (allBooks.size() == 0) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.of(Optional.of(allBooks));
		}

	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getABook(@PathVariable("id") int id) {
		Book bookByid = this.service.getBookByid(id);
		if (bookByid != null) {
			return ResponseEntity.ok(bookByid);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
		Book addBook = this.service.addBook(book);
		return ResponseEntity.ok(addBook);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/book/{id}")
	public Book updateBook(@PathVariable("id") int id, @RequestBody Book b) {
		this.service.updateBookByid(id, b);
		return b;
	}

	@DeleteMapping("/book/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		this.service.delete(id);

	}

}
