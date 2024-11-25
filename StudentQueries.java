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
public class StudentQueries {

    private static Connection connection;
    private static PreparedStatement getAllStudents;
    private static ResultSet allStudentsRslt;
    private static PreparedStatement addStudent;

    public static void addStudent(StudentEntry student) {
        try {
            connection = DBConnection.getConnection();
            addStudent = connection
                    .prepareStatement("INSERT INTO app.student(studentid, firstname, lastname) VALUES (?, ?, ?)");
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.setString(1, student.getStudentID());
            addStudent.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<StudentEntry> getAllStudents() {
        ArrayList<StudentEntry> studentList = new ArrayList<StudentEntry>();
        try {
            connection = DBConnection.getConnection();
            //Proper SQL statement that we need
            getAllStudents = connection.prepareStatement("SELECT studentid, firstname, lastname FROM app.student");
            
            //Executes the statement above
            allStudentsRslt = getAllStudents.executeQuery();

            //Adds each student into the ArrayList with a while loop using next() on the result set
            while (allStudentsRslt.next()) {
                studentList.add(new StudentEntry(allStudentsRslt.getString(1), allStudentsRslt.getString(2), allStudentsRslt.getString(3)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return studentList;

    }

}