package com.ss.service;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ss.db.UserManagementDao;
import com.ss.entity.SBSUsers;
import com.ss.entity.User;
import com.ss.entity.UserRoles;
import com.ss.exception.ApplicationException;

@Service
public class UserManagerImpl implements UserManager{

	@Override
	public void registerUser(SBSUsers user) throws HibernateException, ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		userDao.registerUser(user);
	}

	@Override
	public String getRole(String userName) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		return userDao.getRole(userName);
	
	}

	@Override
	public int getIdByname(String username) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		return userDao.getIdByname(username);
	}

	@Override
	public void editUserDetails(SBSUsers user) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		userDao.editUserDetails(user);
	}

	@Override
	public SBSUsers getUserByname(String username) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		return userDao.getUserByname(username);
	}

	@Override
	public SBSUsers getUserBynameNEmail(String username, String emailId)
			throws HibernateException, ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		return userDao.getUserBynameNEmail(username, emailId);
		
	}

	@Override
	public void persistUser(User user) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		userDao.persistUser(user);
	}

	@Override
	public void persistUserRoles(UserRoles user) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		userDao.persistUserRoles(user);
		
	}

	@Override
	public User getUser(int userId) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();
		return userDao.getUser(userId);
	}

	@Override
	public void editUser(User user) throws HibernateException,
			ApplicationException {
		UserManagementDao userDao = new UserManagementDao();	
		userDao.editUser(user);
	}

	
}
