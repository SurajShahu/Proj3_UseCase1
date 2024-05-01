package com.rays.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.rays.dto.UserDTO;

public class UserModel {

	public void add(UserDTO dto) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();
	}

	public void update(UserDTO dto) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(dto);
		tx.commit();
		session.close();
	}

	public void delete(UserDTO dto) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(dto);
		tx.commit();
		session.close();
	}

	public List search(UserDTO dto) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(UserDTO.class);
		if (dto != null) {
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				c.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			
			if (dto.getDob() != null && dto.getDob().getTime() > 0) {
				
				System.out.println("model date "+dto.getDob());
				c.add(Restrictions.eq("dob", dto.getDob()));
			}
		}	
		List list = c.list();
		tx.commit();
		return list;
	}

	public UserDTO findByPk(int id) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		UserDTO dto =  (UserDTO) session.get(UserDTO.class, id);
		return dto;
	}

}
