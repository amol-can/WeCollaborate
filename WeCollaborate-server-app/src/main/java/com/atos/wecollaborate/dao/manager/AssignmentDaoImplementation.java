package com.atos.wecollaborate.dao.manager;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

@Path("/assignment")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class AssignmentDaoImplementation implements AssignmentDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GET
    @Path("/name")
    @Produces({MediaType.TEXT_PLAIN})
    public String greeting() {
        Random random = new Random();
        return "Assignment " + random.nextInt(100);
    }

    @Override
    public void addAssignment(Assignment assignment) {
        String query = "insert into assignments (assignment_name,skills,domain,assignment_description) VALUES (?,?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, assignment.getAssignmentName());
            ps.setString(2, assignment.getSkils());
            ps.setString(3, assignment.getDomain());
            ps.setString(4, assignment.getAssignmentDescription());

            int out = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception while adding assignment " + e);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Exception while releasing resources: " + e);
            }
        }
    }

    @Path("/getAssignments")
    public Assignment getAllAssignment() {

        String query = "select * from assignments";
        Assignment assignment = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(query);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                assignment = new Assignment();
                assignment.setAssignmentName(resultSet.getString(""));
                assignment.setSkils(resultSet.getString(""));
                assignment.setDomain(resultSet.getString(""));
                assignment.setAssignmentDescription(resultSet.getString(""));
                System.out.println("Assignment: " + assignment);
            	}
            }
            catch(SQLException e){
                System.out.println("retrival time exception: " + e);
            }
    		finally{
    			try {

                    ps.close();
                    conn.close();
                	
    			}catch (SQLException e) {
					System.out.println("error in resource releasing: "+ e);
				}
    		}
        return assignment;
        }

    }
