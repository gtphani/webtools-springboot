package com.example.jobportal.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Entity(name = "JobPosting")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private RecruiterProfile recruiter;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String skills;

    private String location;

    private String experience;

    @OneToMany(mappedBy = "job")
    private List<JobApplication> applicantList;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Transient
    private boolean isApplied;

    public long getId() {
        return id;
    }

    public RecruiterProfile getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(RecruiterProfile recruiter) {
        this.recruiter = recruiter;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCreatedAt() {
        return new SimpleDateFormat("d MMM, y").format(createdAt);
    }


    public String getUpdatedAt() {
        return new SimpleDateFormat("k:mma, d MMM, y").format(updatedAt);
    }

    public List<JobApplication> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(List<JobApplication> applicantList) {
        this.applicantList = applicantList;
    }


    public boolean getIsApplied() {
        return isApplied;
    }

    public void setIsApplied(boolean applied) {
        isApplied = applied;
    }
}
