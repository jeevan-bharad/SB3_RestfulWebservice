package com.example.main.services;

import java.util.List;

import com.example.main.entities.Book;

public interface BookService {

	public List<Book> getAllBooks();
	public Book getBookByid(int id);
	public Book addBook(Book b);
	public void delete(int id);
	public void updateBookByid(int id,Book b); 
	
}
