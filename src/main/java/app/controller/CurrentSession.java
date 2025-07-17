package app.controller;

import java.util.List;

public class CurrentSession {
    private static int studentId;
    private static String name;
    private static List<Course> enrolledCourses;
    private static List<Course> notEnrolledCourses;

    public static void initSession(int id, String nameVal, List<Course> enrolled, List<Course> notEnrolled) {
        studentId = id;
        name = nameVal;
        enrolledCourses = enrolled;
        notEnrolledCourses = notEnrolled;
    }

    public static int getStudentId() { return studentId; }
    public static String getName() { return name; }
    public static List<Course> getEnrolledCourses() { return enrolledCourses; }
    public static List<Course> getNotEnrolledCourses() { return notEnrolledCourses; }

    public static void enrollCourse(Course course) {
        enrolledCourses.add(course);
        notEnrolledCourses.remove(course);
    }

    public static void logout() {
        studentId = -1;
        name = null;
        enrolledCourses = null;
        notEnrolledCourses = null;
    }
}
