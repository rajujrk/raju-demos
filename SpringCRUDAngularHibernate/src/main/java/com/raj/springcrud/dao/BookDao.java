package com.raj.springcrud.dao;

import java.util.List;

import com.raj.springcrud.models.Book;

public interface BookDao {

	
	public List<Book> list();
	
	public Book get(int id);
	
	public void saveOrUpdate(Book book);
	
	public void delete(int id);
}
