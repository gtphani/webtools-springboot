package com.example.jobportal.dao;

import com.example.jobportal.pojo.JobApplication;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationDAO extends DAO{

    public JobApplicationDAO() {
    }

    public void saveApplication(JobApplication jobApplication) throws Exception{
        try {
            begin();
            Session session = getSession();
            session.save(jobApplication);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Could not save application for candidate " + jobApplication.getCandidate(), e);
        }
    }

    public JobApplication getApplicationById(long jobApplicationId) throws Exception{
        try {
            begin();
            Session session = getSession();
            String query = "from JobApplication JA where JA.id = :jobApplicationId";
            JobApplication jobApplication = session.createQuery(
                    query, JobApplication.class).setParameter("jobApplicationId", jobApplicationId).getSingleResult();
            commit();
            return jobApplication;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Could not retrieve application " + jobApplicationId, e);
        }
    }

    public void updateApplication(JobApplication jobApplication) throws Exception{
        try {
            begin();
            Session session = getSession();
            session.update(jobApplication);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Could not update application for candidate " + jobApplication.getCandidate(), e);
        }
    }


}
