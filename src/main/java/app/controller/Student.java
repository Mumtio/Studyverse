package app.controller;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private String email;
    private List<Course> enrolledcourses ;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getEnrolledcourses() {
        return enrolledcourses;
    }

    public Student(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        enrolledcourses = new ArrayList<>();
    }
}


