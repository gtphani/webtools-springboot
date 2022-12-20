package com.example.jobportal.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Entity(name = "JobApplication")
public class JobApplication {

    public enum Status {
        APPLIED,
        SHORTLISTED,
        INTERVIEWING,
        OFFERED,
        REJECTED,
        ARCHIVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private JobPosting job;

    @ManyToOne
    @JoinColumn
    private CandidateProfile candidate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] resume;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date applieddAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "application")
    private List<ApplicationMessage> messages;

    public long getId() {
        return id;
    }


    public JobPosting getJob() {
        return job;
    }

    public void setJob(JobPosting job) {
        this.job = job;
    }

    public CandidateProfile getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateProfile candidate) {
        this.candidate = candidate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getApplieddAt() {
        return new SimpleDateFormat("k:mma, d MMM, y").format(applieddAt);
    }

    public String getUpdatedAt() {
        return new SimpleDateFormat("k:mma, d MMM, y").format(updatedAt);
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public List<ApplicationMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ApplicationMessage> messages) {
        this.messages = messages;
    }
}
