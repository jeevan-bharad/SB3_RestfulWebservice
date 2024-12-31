package com.example.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
