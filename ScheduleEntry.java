/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseschedulerjakebyrnep1;
import java.util.Calendar;
import java.sql.Timestamp;
/**
 *
 * @author jakebyrne
 */
//Holds information about a schedule through a ScheduleEntry object
public class ScheduleEntry {
    
    private String semester;
    private String CourseCode;
    private String StudentID;
    private String status;
    private Timestamp Timestamp;

    public ScheduleEntry(String semester, String CourseCode, String StudentID, String status, Timestamp Timestamp) {
        this.semester = semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.status = status;
        this.Timestamp = Timestamp;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return Timestamp;
    }
    
    
    
}
