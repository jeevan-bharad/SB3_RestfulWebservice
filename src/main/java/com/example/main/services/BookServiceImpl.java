package com.example.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.entities.Book;
import com.example.main.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repo;

	
	public BookServiceImpl(BookRepository repo) {
		this.repo = repo;
		// TODO Auto-generated constructor stub
	}
	// private static List<Book> list = new ArrayList<>();

	/*
	 * static {
	 * 
	 * list.add(new Book(1, "Java By Jeevan", "JB1")); list.add(new Book(2,
	 * "C by Ashu", "JB2")); list.add(new Book(3, "C** by JJ", "JB3")); list.add(new
	 * Book(4, "Python", "Durgesh")); list.add(new Book(5, "asdf", "kaka"));
	 * 
	 * }
	 */

	public List<Book> getAllBooks() {
		return this.repo.findAll();

	}

	public Book getBookByid(int id) {
		try {
			Book findById = this.repo.findById(id).orElse(null);
			//Book book2 = list.stream().filter(book -> book.getId() == id).findFirst().get();
			return findById;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateBookByid(int id, Book b) {

		Book oldBook = this.repo.findById(id).orElse(null);
		if(oldBook != null)
		{
		this.repo.save(b);
		}
		else 
		{
			this.repo.save(b);
		}
		
		/*
		 * list = list.stream().map(book -> { if (book.getId() == id) { book.setId(id);
		 * book.setTitle(b.getTitle()); book.setAuthor(b.getAuthor());
		 * 
		 * } return book; }).collect(Collectors.toList());
		 */
	}

	@Override
	public Book addBook(Book b) {

		Book save = repo.save(b);
		return save;
	}



	@Override
	public void delete(int id) {
		this.repo.deleteById(id);

	}

}
