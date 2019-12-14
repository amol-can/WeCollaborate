package com.atos.wecollaborate.dao.manager;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WeCollaborateApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("wecollaborate.xml");

        AssignmentDao assignmentDao = context.getBean("assignmentDaoImp",AssignmentDao.class);

        /*Assignment assignment = new Assignment();
        assignment.setAssignmentName("POC on Highchart");
        assignment.setSkils("java,blockchain,hyperledger");
        assignment.setDomain("blockchain");
        assignment.setAssignmentDescription("Need to implement PIC on smart contract");

        assignmentDao.addAssignment(assignment);*/
    	
    	//AssignmentDaoImplementation as = new AssignmentDaoImplementation();

        
        System.out.println(assignmentDao.getAllAssignment());
        
    }
}
