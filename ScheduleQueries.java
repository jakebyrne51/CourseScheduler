/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseschedulerjakebyrnep1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jakebyrne
 */
public class ScheduleQueries {
    private static Connection connection;

    private static PreparedStatement addScheduleEntry;

    private static PreparedStatement getScheduleByStudent;
    private static ResultSet scheduleByStudentRslt;

    private static PreparedStatement getScheduledStudentCount;
    private static ResultSet scheduledStudentCountRslt;

    public static int getScheduledStudentCount(String currentSemester, String courseCode) {
        int count = 0;
        
        try {
           connection = DBConnection.getConnection();
           getScheduledStudentCount = connection.prepareStatement("SELECT count(studentid) FROM app.schedule WHERE semester = ? OR coursecode = ?");
           getScheduledStudentCount.setString(1, currentSemester);
           getScheduledStudentCount.setString(2, courseCode);
           scheduledStudentCountRslt = getScheduledStudentCount.executeQuery();
           scheduledStudentCountRslt.next();
           count = scheduledStudentCountRslt.getInt(1);
           
           
        } catch(SQLException sqlException)
            {
            sqlException.printStackTrace();
        }
        System.out.println("studentNum" + count);
        return count;
        }

    public static void addScheduleEntry(ScheduleEntry entry) {
        try {
            connection = DBConnection.getConnection();
            addScheduleEntry = connection.prepareStatement(
                    "INSERT INTO app.schedule(semester, studentid, coursecode, status, timestamp) VALUES (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(3, entry.getCourseCode());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
        ArrayList<ScheduleEntry> studentSchedule = new ArrayList<>();
        
        try {
           connection = DBConnection.getConnection();
           getScheduleByStudent = connection.prepareStatement("SELECT semester, coursecode, studentid, status, timestamp FROM app.schedule WHERE semester = ? OR studentid = ?");
           
           getScheduleByStudent.setString(1, semester);
           getScheduleByStudent.setString(2, studentID);
           
    
           scheduleByStudentRslt = getScheduleByStudent.executeQuery();
          
        
        while(scheduleByStudentRslt.next()) {
           ScheduleEntry studentScheduleObj = new ScheduleEntry(scheduleByStudentRslt.getString(1), scheduleByStudentRslt.getString(2), scheduleByStudentRslt.getString(3), scheduleByStudentRslt.getString(4), scheduleByStudentRslt.getTimestamp(5));
           studentSchedule.add(studentScheduleObj);
        }
        
        } catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        //CONFIRMED ISSUE IS HERE NOTHING IS COMING OUT FROM 
        return studentSchedule;
        }

}
