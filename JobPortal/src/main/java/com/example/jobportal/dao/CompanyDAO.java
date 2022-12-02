package com.example.jobportal.dao;

import com.example.jobportal.pojo.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDAO extends DAO{

    public CompanyDAO() {
    }

    public void saveCompany(Company company) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.save(company);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save company " + company.getName(), e);
        }
    }

    public List<Company> getCompanies() throws Exception {
        try {
            begin();
            Session session = getSession();
            List<Company> companies = session.createQuery("from Company", Company.class).list();
            commit();
            return companies;
        } catch (HibernateException e) {
            throw new Exception("Error while retrieving list of companies");
        }
    }
}
