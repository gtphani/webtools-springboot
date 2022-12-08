package com.example.jobportal.dao;

import com.example.jobportal.pojo.CandidateProfile;
import com.example.jobportal.pojo.JobPosting;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class JobPostingDAO extends DAO{

    public JobPostingDAO() {
    }

    public void saveJobPosting(JobPosting jobPosting) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.save(jobPosting);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save job posting " + e.getMessage());
        }
    }

    public void updateJobPosting(JobPosting jobPosting) throws Exception {
        try {
            begin();
            Session session = getSession();
            session.update(jobPosting);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save job posting " + e.getMessage());
        }
    }

    public JobPosting getJobPostingById(int jobPostingId){
        try {
            begin();
            Session session = getSession();
            JobPosting jobPosting = session.createQuery(
                    "from JobPosting JP where JP.id = " + jobPostingId, JobPosting.class).getSingleResult();
            commit();
            return jobPosting;
        } catch (NoResultException e) {
            e.printStackTrace();
            System.out.println("Could not retrieve job posting " + e.getMessage());
            return null;
        }
    }

    public JobPosting getJobPostingByRecruiterId(int jobPostingId, long recruiterProfileId){
        try {
            begin();
            Session session = getSession();
            JobPosting jobPosting = session.createQuery(String.format(
                    "from JobPosting JP where JP.recruiter.id = '%d' and JP.id = '%d'",
                    recruiterProfileId, jobPostingId), JobPosting.class).getSingleResult();
            commit();
            return jobPosting;
        } catch (NoResultException e) {
            e.printStackTrace();
            System.out.println("Could not retrieve job posting " + e.getMessage());
            return null;
        }
    }

    public List<JobPosting> getAvailableJobPostings(CandidateProfile candidateProfile) {
        try {
            begin();
            Session session = getSession();
            List<JobPosting> jobPostings = session.createQuery(String.format("from JobPosting JP where JP.id not in (select JA.job.id from JobApplication JA where JA.candidate.id = '%d')", candidateProfile.getId()), JobPosting.class).list();
            for (JobPosting jobPosting: jobPostings) {
                jobPosting.getCompany().setBase64logoFile();
            }
            commit();
            return jobPostings;
        } catch (NoResultException e) {
            System.out.println("Could not retrieve job posting list: " + e.getMessage());
            return null;
        }
    }

    public List<JobPosting> getAppliedJobPostings(CandidateProfile candidateProfile) {
        try {
            begin();
            Session session = getSession();
            List<JobPosting> jobPostings = session.createQuery(String.format("from JobPosting JP where JP.id in (select JA.job.id from JobApplication JA where JA.candidate.id = '%d')", candidateProfile.getId()), JobPosting.class).list();
            for (JobPosting jobPosting: jobPostings) {
                jobPosting.getCompany().setBase64logoFile();
            }
            commit();
            return jobPostings;
        } catch (NoResultException e) {
            System.out.println("Could not retrieve job posting list: " + e.getMessage());
            return null;
        }
    }

}
