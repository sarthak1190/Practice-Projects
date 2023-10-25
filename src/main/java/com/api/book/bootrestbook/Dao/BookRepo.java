package com.api.book.bootrestbook.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.bootrestbook.Entities.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
    public Book findById(int id);

}
