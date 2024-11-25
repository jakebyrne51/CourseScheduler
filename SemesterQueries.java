package courseschedulerjakebyrnep1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv
 */
public class SemesterQueries {
    private static Connection connection;

    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;

    private static ResultSet resultSet;

    public static void addSemester(String name) {
        try {
            connection = DBConnection.getConnection();
            addSemester = connection.prepareStatement("INSERT INTO app.semester(name) VALUES (?)");
            addSemester.setString(1, name);
            addSemester.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<String> getSemesterList() {
        ArrayList<String> semester = new ArrayList<String>();
        try {
            connection = DBConnection.getConnection();
            getSemesterList = connection.prepareStatement("SELECT name FROM app.semester ORDER by name");
            resultSet = getSemesterList.executeQuery();

            while (resultSet.next()) {
                semester.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return semester;

    }

}
