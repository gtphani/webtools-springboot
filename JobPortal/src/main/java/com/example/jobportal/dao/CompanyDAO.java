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
            e.printStackTrace();
            throw new Exception("Could not save company " + company.getName(), e);
        }
    }

    public List<Company> getCompanies() throws Exception {
        try {
            begin();
            Session session = getSession();
            String query = "from Company";
            List<Company> companies = session.createQuery(query, Company.class).list();
            commit();
            return companies;
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Error while retrieving list of companies " + e.getMessage());
        }
    }

    public Company getCompanyById(long companyId) throws Exception {
        try {
            begin();
            Session session = getSession();
            String query = "from Company where id = :companyId";
            Company company = session.createQuery(query, Company.class).setParameter("companyId", companyId).getSingleResult();
            commit();
            return company;
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Error while retrieving list of companies " + e.getMessage());
        }
    }
}
