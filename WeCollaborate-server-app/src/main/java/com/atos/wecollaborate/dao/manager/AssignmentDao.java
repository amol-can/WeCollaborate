package com.atos.wecollaborate.dao.manager;

import java.util.List;

import com.atos.wecollaborate.model.Assignment;

public interface AssignmentDao {

    public void addAssignment(Assignment assignment);
    public List<Assignment> getAllAssignment();

}
