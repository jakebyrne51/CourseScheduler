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
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<CourseEntry> courses = new ArrayList<>();
    private static ArrayList<String> coursecodes = new ArrayList<String>();
    private static PreparedStatement getAllCourses;
    private static ResultSet coursesrslt;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet coursecodesrslt;
    private static PreparedStatement getCourseSeats;
    private static ResultSet courseseatsrslt;

    public static ArrayList<CourseEntry> getAllCourses(String semester) {

        try {
            connection = DBConnection.getConnection();
            courses.clear();
            getAllCourses = connection.prepareStatement(
                    "SELECT semester, coursecode, description,seats FROM app.course WHERE semester = ?");
            getAllCourses.setString(1, semester);
            coursesrslt = getAllCourses.executeQuery();
            while (coursesrslt.next()) {
                courses.add(new CourseEntry(coursesrslt.getString(1), coursesrslt.getString(2), coursesrslt.getString(3), coursesrslt.getInt(4)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return courses;
    }

    public static void addCourse(CourseEntry course) {

        try {
            connection = DBConnection.getConnection();

            addCourse = connection.prepareStatement(
                    "INSERT INTO app.course(semester, coursecode, description, seats) values(? ,?, ?, ?)");

            addCourse.setString(1, course.getSemester());
            
            addCourse.setString(3, course.getCourseCode());
            addCourse.setString(2, course.getCourseDescription());
            addCourse.setInt(4, course.getSeats());

            addCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes(String semester) {

        try {
            connection = DBConnection.getConnection();
            
            coursecodes.clear();
            getAllCourseCodes = connection.prepareStatement("SELECT coursecode FROM app.course WHERE semester = ?");
            
            getAllCourseCodes.setString(1, semester);
            
            coursecodesrslt = getAllCourseCodes.executeQuery();

            while (coursecodesrslt.next()) {
                coursecodes.add(coursecodesrslt.getString(1));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return coursecodes;

    }

    public static int getCourseSeats(String semester, String CourseCode) {

       
        int studentSeats = 0;
        try {
            connection = DBConnection.getConnection();
            getCourseSeats = connection
                    .prepareStatement("SELECT seats FROM app.course WHERE semester = ? OR coursecode = ?");
            
            getCourseSeats.setString(2, CourseCode);

            getCourseSeats.setString(1, semester);            
            
            courseseatsrslt = getCourseSeats.executeQuery();
      
            while (courseseatsrslt.next()) {
                studentSeats = courseseatsrslt.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return studentSeats;

    }

}
