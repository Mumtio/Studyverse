package app.util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static List<String[]> getAvailableCourses() {
        List<String[]> list = new ArrayList<>();

        try {
            Socket socket = new Socket("localhost", 9999);  // Connect to server

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            out.println("GET_COURSES");  // Ask for course list

            String line;
            while (!(line = in.readLine()).equals("END")) {
                String[] data = line.split("\\|");  // Split "Title|Desc|ID"
                list.add(data);  // Add to list
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;  // List of [title, description, id] arrays
    }

    public static void enrollCourse(int studentId, int courseId) {
        try {
            Socket socket = new Socket("localhost", 9999);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // Send enroll command
            out.println("ENROLL:" + studentId + ":" + courseId);
            String response = in.readLine();  // "SUCCESS"
            System.out.println("Server response: " + response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
