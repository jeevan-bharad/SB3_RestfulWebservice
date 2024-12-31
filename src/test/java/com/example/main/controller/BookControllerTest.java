package com.example.main.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.main.entities.Author;
import com.example.main.entities.Book;
import com.example.main.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	MockMvc mockMvc;

	private BookController bookController;
	Book b1, b2;
	Author a1, a2;
	List<Book> books = new ArrayList<>();

	@MockBean
	private BookService bookservice;

	@BeforeEach
	void setUp() throws Exception {
		a1 = new Author(1, "mama");
		b1 = new Book(1, "nana", a1);
		a2 = new Author(2, "kaka");
		b2 = new Book(2, "mamai", a2);
		books.add(b1);
		books.add(b2);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllBook() throws Exception {
		System.out.println(
				"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4--------------------------------------------------------------------------------------------------------------");
		when(bookservice.getAllBooks()).thenReturn(books);

		this.mockMvc.perform(get("/book")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testGetABook() throws Exception {
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------");
		when(bookservice.getBookByid(1)).thenReturn(b1);

		this.mockMvc.perform(get("/book/{id}", 1)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testCreateBook() throws Exception {
		System.out.println(
				"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		 String requestjson = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(b1);
		
		
		
		when(bookservice.addBook(b1)).thenReturn(b1);
		
		this.mockMvc.perform(post("/book")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestjson))
		.andDo(print()).andExpect(status().isOk());
		
	}
	

	
	 @Test void testUpdateBook() throws Exception {
		 System.out.println(
					"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%-");
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			 String requestjson = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(b1);
			
			
			
			//when(bookservice.updateBookByid(1,b1)).thenReturn(b1);
			
			this.mockMvc.perform(put("/book/{id}",1)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestjson))
			.andDo(print()).andExpect(status().isOk());
		 }
	 
	 
	@Test
	void testDeleteBook() throws Exception {
		// when(bookservice.delete(1)).thenReturn("success");
		
		//this.mockMvc.perform(delete("",1)).andDo(print()).andExpect(status().isOk());

		 this.mockMvc.perform(delete("/book/{id}",1)).andDo(print()).andExpect(status().isOk());
	}

}
