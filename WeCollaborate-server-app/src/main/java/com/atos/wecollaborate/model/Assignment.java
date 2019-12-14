package com.atos.wecollaborate.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Assignment {

    private String assignmentName;
    private String skils;
    private String domain;
    private String assignmentDescription;

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getSkils() {
        return skils;
    }

    public void setSkils(String skils) {
        this.skils = skils;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentName='" + assignmentName + '\'' +
                ", skils='" + skils + '\'' +
                ", domain='" + domain + '\'' +
                ", assignmentDescription='" + assignmentDescription + '\'' +
                '}';
    }
}
