package com.atos.wecollaborate.dao.manager;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.wecollaborate.model.Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Path("/assignment")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class AssignmentDaoImplementation implements AssignmentDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) throws SQLException {
    	
    	this.dataSource = dataSource;
    }

    @GET
    @Path("/name")
    @Produces({MediaType.APPLICATION_JSON})
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
            
            System.out.println("Data successfully inserted");
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Assignment> getAllAssignment() {
    	
    	List<Assignment> assignments = new LinkedList<Assignment>();

        String query = "select * from assignments";
        Assignment assignment = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                assignment = new Assignment();
                assignment.setAssignmentName(resultSet.getString("assignment_name"));
                assignment.setSkils(resultSet.getString("skills"));
                assignment.setDomain(resultSet.getString("domain"));
                assignment.setAssignmentDescription(resultSet.getString("assignment_description"));
                assignments.add(assignment);
            }
            
            }
            catch(SQLException e){
                System.out.println("retrival time exception: " + e);
            }
    		finally{
    			/*try {

                    ps.close();
                    conn.close();
                	
    			}catch (SQLException e) {
					System.out.println("error in resource releasing: "+ e);
				}*/
    		}
        return assignments;
        }

    }
