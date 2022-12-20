package com.example.jobportal.dao;

import com.example.jobportal.pojo.CandidateProfile;
import com.example.jobportal.pojo.JobPosting;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.NoResultException;
import java.util.ArrayList;
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new Exception("Could not save job posting " + e.getMessage());
        }
    }

    public JobPosting getJobPostingById(long jobPostingId){
        try {
            begin();
            Session session = getSession();
            String query = "from JobPosting JP where JP.id = :jobPostingId";
            JobPosting jobPosting = session.createQuery(
                    query, JobPosting.class).setParameter("jobPostingId", jobPostingId).getSingleResult();
            commit();
            return jobPosting;
        } catch (NoResultException e) {
            rollback();
            e.printStackTrace();
            System.out.println("Could not retrieve job posting " + e.getMessage());
            return null;
        }
    }

    public JobPosting getJobPostingByRecruiterId(long jobPostingId, long recruiterProfileId){
        try {
            begin();
            Session session = getSession();
            String query = "from JobPosting JP where JP.recruiter.id = :recruiterProfileId and JP.id = :jobPostingId order by JP.id desc ";
            JobPosting jobPosting = session.createQuery(query, JobPosting.class).setParameter("recruiterProfileId", recruiterProfileId).setParameter("jobPostingId", jobPostingId).getSingleResult();
            commit();
            return jobPosting;
        } catch (NoResultException e) {
            rollback();
            e.printStackTrace();
            System.out.println("Could not retrieve job posting " + e.getMessage());
            return null;
        }
    }

    public List<JobPosting> getAvailableJobPostings(CandidateProfile candidateProfile, String searchString) {
        try {
            begin();
            Session session = getSession();
            List<JobPosting> jobPostings = new ArrayList<>();
            String query = "from JobPosting JP where JP.id not in (select JA.job.id from JobApplication JA where JA.candidate.id = :candidateId) order by JP.id desc ";
            if (searchString == null) {
                jobPostings = session.createQuery(query, JobPosting.class).setParameter("candidateId", candidateProfile.getId()).list();
            } else {
                for (JobPosting job: session.createQuery(query, JobPosting.class).setParameter("candidateId", candidateProfile.getId()).list()) {
                    if (StringUtils.containsAnyIgnoreCase(job.getTitle(), searchString) ||
                            StringUtils.containsAnyIgnoreCase(job.getCompany().getName(), searchString) ||
                            StringUtils.containsAnyIgnoreCase(job.getSkills(), searchString)) {
                        jobPostings.add(job);
                    }
                }
            }
            commit();
            return jobPostings;
        } catch (NoResultException e) {
            rollback();
            e.printStackTrace();
            System.out.println("Could not retrieve job posting list: " + e.getMessage());
            return null;
        }
    }

    public List<JobPosting> getAppliedJobPostings(CandidateProfile candidateProfile) {
        try {
            begin();
            Session session = getSession();
            String query = "from JobPosting JP where JP.id in (select JA.job.id from JobApplication JA where JA.candidate.id = :candidateProfileId) order by JP.id desc ";
            List<JobPosting> jobPostings = session.createQuery(query, JobPosting.class).setParameter("candidateProfileId", candidateProfile.getId()).list();
            commit();
            return jobPostings;
        } catch (NoResultException e) {
            rollback();
            e.printStackTrace();
            System.out.println("Could not retrieve job posting list: " + e.getMessage());
            return null;
        }
    }

}
