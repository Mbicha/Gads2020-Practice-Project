package com.charles.gads2020leaderboard.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectSubmissionInfo {
    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;

    @SerializedName("githubLink")
    @Expose
    private String githubLink;

    public ProjectSubmissionInfo(String firstName, String lastName, String emailAddress, String githubLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.githubLink = githubLink;
    }

    public ProjectSubmissionInfo() {

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    @Override
    public String toString() {
        return "ProjectSubmissionInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", githubLink='" + githubLink + '\'' +
                '}';
    }
}
