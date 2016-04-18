package com.raj.springcrud.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raj.springcrud.models.Book;

/**
 * @author rajkumarj
 *
 */
@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public BookDaoImpl(){
		
	}
	
	public BookDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Book> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Book> listBook = (List<Book>) sessionFactory.getCurrentSession()
				.createCriteria(Book.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listBook;
	}

	@Transactional
	public Book get(int id) {
		// TODO Auto-generated method stub
		String hql = "from Book where bookid=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Book> listBook = (List<Book>) query.list();
		
		if (listBook != null && !listBook.isEmpty()) {
			return listBook.get(0);
		}
		
		return null;
	}

	@Transactional
	public void saveOrUpdate(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(book);
	}

	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		Book BookToDelete = new Book();
		BookToDelete.setBookid(id);
		sessionFactory.getCurrentSession().delete(BookToDelete);
	}

}
