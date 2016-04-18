package com.raj.springcrud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raj.springcrud.dao.BookDao;
import com.raj.springcrud.models.Book;

/**
 * @author rajkumarj
 *
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDaoImpl;
	
	public Book findById(int id) {
		// TODO Auto-generated method stub
		Book book= bookDaoImpl.get(id);
		
		return book;
	}

	public Book findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdateBook(Book book) {
		// TODO Auto-generated method stub

		bookDaoImpl.saveOrUpdate(book);
		
	}

	public void deleteBookById(int id) {
		// TODO Auto-generated method stub
		bookDaoImpl.delete(id);
	}

	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub	
		return bookDaoImpl.list();
	}

	public void deleteAllBooks() {
		// TODO Auto-generated method stub

	}

	public boolean isBookExist(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

}
