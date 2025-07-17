package app.util;

import java.util.List;

public class TestClient {
    public static void main(String[] args) {
        System.out.println("Fetching course list from server...");

        List<String[]> courses = Client.getAvailableCourses();

        for (String[] course : courses) {
            System.out.println("Title: " + course[0]);
            System.out.println("Description: " + course[1]);
            System.out.println("Course ID: " + course[2]);
            System.out.println("-----");
        }

        System.out.println("Now enrolling student 1 into course 2...");
        Client.enrollCourse(1, 2);  // test enroll

        System.out.println("✅ Test complete.");
    }
}
