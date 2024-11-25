/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseschedulerjakebyrnep1;

/**
 *
 * @author jakebyrne
 */
//Holds information about a course through a CourseEntry object
public class CourseEntry {

    private String semester;
    private String courseDescription;
    private String courseCode;
    private int seats;

    public CourseEntry(String semester, String courseDescription, String courseCode, int seats) {
        this.semester = semester;
        this.courseDescription = courseDescription;
        this.courseCode = courseCode;
        this.seats = seats;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getSeats() {
        return seats;
    }

}
