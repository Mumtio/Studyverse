package app.util;

import app.controller.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static final String URL = "jdbc:postgresql://ep-nameless-sun-a81zzeu9-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_QUe05HJPEIma&ssl=true&sslmode=require";

    private static final String USER = "Rincodesuwu";
    private static final String PASSWORD = "npg_QUe05HJPEIma";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static String getStudentName(String studentId) {
        String name = "";
        String query = "SELECT name FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(studentId)); // Fix: use setInt
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }


    public static List<Course> getEnrolledCourses(String studentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.id, c.title, c.description FROM courses c " +
                "JOIN student_courses sc ON c.id = sc.course_id " +
                "WHERE sc.student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(studentId)); // Fix: cast to int
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }




    public static List<Course> getNotEnrolledCourses(String studentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT id, title, description FROM courses " +
                "WHERE id NOT IN (SELECT course_id FROM student_courses WHERE student_id = ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(studentId)); // Fix: cast to int
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }


    public static void enrollStudentInCourse(int studentId, int courseId) {
        String query = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            System.out.println("Enrolling student " + studentId + " in course " + courseId);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            System.out.println("✔️ Enrollment successful!");

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Enrollment failed");
            e.printStackTrace();
        }
    }




}
