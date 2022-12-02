package com.example.jobportal.dao;

import com.example.jobportal.pojo.Company;
import com.example.jobportal.pojo.RecruiterProfile;
import com.example.jobportal.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends DAO {

    public UserDAO() {
    }

    public void saveRecruiter(User user, RecruiterProfile recruiterProfile) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.save(user);
            recruiterProfile.setUser(user);
            session.save(recruiterProfile);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save recruiter " + user.getFirstName(), e);
        }
    }

    public void saveCandidate(User user) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.save(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save candidate " + user.getFirstName(), e);
        }
    }

    public User getUserByEmail(String email) throws Exception {
        try {
            begin();
            Session session = getSession();
            User user = (User) session.createQuery("FROM User U where U.email = " + email).getSingleResult();
            commit();
            return user;
        } catch (Exception e) {
            throw new Exception("Could not retrieve user by email");
        }
    }

    public void delete(User user) throws Exception {
    	begin();
    	getSession().delete(user);
    	commit();
    }
}