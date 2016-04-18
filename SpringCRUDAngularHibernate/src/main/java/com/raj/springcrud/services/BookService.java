package com.raj.springcrud.services;

import java.util.List;

import com.raj.springcrud.models.Book;

/**
 * @author rajkumarj
 *
 */
public interface BookService {
	Book findById(int id);

	Book findByName(String name);

	void saveOrUpdateBook(Book book);

	void deleteBookById(int id);

	List<Book> findAllBooks();

	void deleteAllBooks();

	public boolean isBookExist(Book book);
}
