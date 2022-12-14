package com.example.jobportal.pojo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PostRemove;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Component
@Entity(name = "User")
public class User {

    public enum UserType {
        ADMIN,
        CANDIDATE,
        RECRUITER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 40)
    private String firstName;

    @Column(length = 40)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;

    @OneToOne(mappedBy = "user")
    private CandidateProfile candidateProfile;

    @OneToOne(mappedBy = "user")
    private RecruiterProfile recruiterProfile;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] avatar;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Transient
    private String base64AvatarFile;

    @Transient
    private boolean isRecruiter;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResumeName() {
        return this.firstName + "_" + this.firstName + "_resume";
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public CandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public void setCandidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public RecruiterProfile getRecruiterProfile() {
        return recruiterProfile;
    }

    public void setRecruiterProfile(RecruiterProfile recruiterProfile) {
        this.recruiterProfile = recruiterProfile;
    }

    public String getBase64AvatarFile() {
        return new Base64().encodeToString(this.avatar);
    }

    public void setBase64AvatarFile() {
        this.base64AvatarFile = new Base64().encodeToString(this.avatar);
    }

    public boolean getIsRecruiter() {
        return this.recruiterProfile != null;
    }

    public void setIsRecruiter(boolean recruiter) {
        isRecruiter = recruiter;
    }
}
