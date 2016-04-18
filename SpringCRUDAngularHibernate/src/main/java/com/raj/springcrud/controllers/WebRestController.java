package com.raj.springcrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.raj.springcrud.models.Book;
import com.raj.springcrud.services.BookService;



/**
 * @author rajkumarj
 *
 */
@RestController
public class WebRestController {

	@Autowired
	BookService bookService;

	// -------------------Retrieve All
	// Books--------------------------------------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);// You
																			// many
																			// decide
																			// to
																			// return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Book--------------------------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		System.out.println("Fetching Book with id " + id);
		Book book = bookService.findById(id);
		if (book == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	// -------------------Create a
	// Book--------------------------------------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Book " + book.getBookname());

		if (bookService.isBookExist(book)) {
			System.out.println("A Book with name " + book.getBookname() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		bookService.saveOrUpdateBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getBookid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Book
	// --------------------------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
		System.out.println("Updating Book " + id);

		Book currentBook = bookService.findById(id);

		if (currentBook == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		currentBook.setBookname(book.getBookname());
		currentBook.setAuthor(book.getAuthor());
		currentBook.setCategory(book.getCategory());
		currentBook.setPrice(book.getPrice());
		currentBook.setPublishdate(book.getPublishdate());
		currentBook.setPublisher(book.getPublisher());
		bookService.saveOrUpdateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}

	// ------------------- Delete a Book
	// --------------------------------------------------------

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting Book with id " + id);

		Book book = bookService.findById(id);
		if (book == null) {
			System.out.println("Unable to delete. Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Books
	// --------------------------------------------------------

	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks() {
		System.out.println("Deleting All Books");

		bookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}
