package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ds.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, String> {

}
