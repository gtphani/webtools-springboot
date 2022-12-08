package com.example.jobportal.dao;

import com.example.jobportal.pojo.CandidateProfile;
import com.example.jobportal.pojo.Company;
import com.example.jobportal.pojo.RecruiterProfile;
import com.example.jobportal.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

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
            throw new Exception("Could not save recruiter " + user.getFirstName() + e.getMessage());
        }
    }

    public void saveCandidate(User user, CandidateProfile candidateProfile) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.save(user);
            candidateProfile.setUser(user);
            session.save(candidateProfile);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save candidate " + user.getFirstName() + e.getMessage());
        }
    }

    public void updateCandidate(User user, CandidateProfile candidateProfile) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.update(user);
            session.update(candidateProfile);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not update candidate " + candidateProfile.getUser().getFirstName() + e.getMessage());
        }
    }

    public User getUserByEmail(String email) throws Exception {
        try {
            begin();
            Session session = getSession();
            User user = session.createQuery(String.format("FROM User U where U.email = '%s'", email), User.class).getSingleResult();
            commit();
            return user;
        } catch (Exception e) {
            throw new Exception("Could not retrieve user by email: " + e.getMessage());
        }
    }

    public User getUserByCredentials(String email, String password) throws Exception {
        try {
            begin();
            Session session = getSession();
            User user = session.createQuery(String.format("FROM User U where U.email = '%s' and U.password = '%s'", email, password), User.class).getSingleResult();
            commit();
            return user;
        } catch (NoResultException e) {
            System.out.println("User not found with the email/password combination");
            return null;
        }
    }

    public void delete(User user) throws Exception {
    	begin();
    	getSession().delete(user);
    	commit();
    }
}