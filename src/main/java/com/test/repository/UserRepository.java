package com.test.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.entity.Login;
import com.test.entity.User;
import com.test.entity.UserDocument;

@Repository
public class UserRepository {

	Query query;
	@Autowired 
	private SessionFactory sf;

	public void addUser(User user) {
	 sf.openSession().save(user);
	}

	public Integer loginUser(String email,String password) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		try {
			
	   query=session.createQuery("from User where email=? and password=?");
	  query.setParameter(0, email);
	  query.setParameter(1, password);
	  List list=query.list();
	  System.err.println("hh" +list);
	  if ((list != null) && (list.size() > 0)) {
		  System.err.println("ghg"+list);
				return ((User) list.get(0)).getId();
	  }
	}catch (Exception e) {
		return 0;
	}
		session.close();
		
		return 0;
}

	public List<User> list() {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		query=session.createQuery("from User");
		List<User> user=query.list();
		session.close();
		return user;
	}

	public User findUserId(int id) {
		Session session =sf.openSession();
	User user=(User) session.get(User.class,id);
	session.close();
	return user;
	}

	public void updateUser(User user) {
		Session session=sf.openSession();
		session.update(user);
		session.close();
		
	}

	public List<UserDocument> findAllByUserId(int userId) {
		Session session=sf.openSession();
		query=session.createQuery("from USER_DOCUMENT where USER_ID=?");
		query.setParameter(0, userId);
		List<UserDocument> user=query.list();
		session.close();
		return user;
	}

	public void saveDocument(UserDocument document) {
		 sf.openSession().save(document);
		
	}
}
