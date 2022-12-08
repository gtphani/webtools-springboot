package com.example.jobportal.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "RecruiterProfile")
public class RecruiterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private Company company;

    @OneToMany(mappedBy = "recruiter")
    private List<JobPosting> jobsPosted;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<JobPosting> getJobsPosted() {
        ArrayList<JobPosting> l = new ArrayList<>();
        for (JobPosting jobPosting: jobsPosted) {
            jobPosting.getCompany().setBase64logoFile();
            l.add(jobPosting);
        }
        return l;
    }

    public void setJobsPosted(List<JobPosting> jobsPosted) {
        this.jobsPosted = jobsPosted;
    }
}
