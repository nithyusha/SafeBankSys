package com.ss.service;

import org.hibernate.HibernateException;

import com.ss.entity.SBSUsers;
import com.ss.entity.User;
import com.ss.entity.UserRoles;
import com.ss.exception.ApplicationException;

public interface UserManager {
	public void registerUser(SBSUsers user) throws HibernateException, ApplicationException;
	public String getRole(String userName) throws HibernateException, ApplicationException;
	public int getIdByname(String username) throws HibernateException, ApplicationException;
	public void editUserDetails(SBSUsers user) throws HibernateException, ApplicationException;
	public SBSUsers getUserByname(String username) throws HibernateException, ApplicationException;
	public SBSUsers getUserBynameNEmail(String username,String emailId) throws HibernateException, ApplicationException ;
	public void persistUser(User user) throws HibernateException, ApplicationException ;
	public void persistUserRoles(UserRoles user) throws HibernateException, ApplicationException ;
	public User getUser(int userId) throws HibernateException, ApplicationException;
	public void editUser(User user) throws HibernateException, ApplicationException;
}

