
/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Lecture
 * 
 * This file contain a class that implement information about a course. It also
 * implement methods to implements/ alter informations of the course and
 * students within the course. 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class contain methods that is use to obtain information from the course
 * such as department, course's number, course's description, course, and
 * capacity. It also contain methods that initialized all the information of the
 * course; method to check if the student could be enroll/ unenroll from the
 * course, and if the course is full; methods that return course capacity; total
 * student enrolled; make a shallow copy; return the roster of students;
 * unenroll all student from the course; and print out a string of course
 * information.
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;
    int i;

    /**
     * This method Initialize the course’s information with an initial
     * enrollment of 0 students.
     * 
     * @param department
     * @param number
     * @param description
     * @param capacity
     * @throws IllegalArgumentException - if any input is null.
     */
    public Course(String department, String number, String description,
            int capacity) {
        // Check if any input is null
        if (department == null || number == null || description == null
                || capacity < 0) {
            throw new IllegalArgumentException();
        }
        // Initialize course information
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
    }

    /**
     * This method return the department name.
     * 
     * @return - the department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method return the number of the course.
     * 
     * @return - the course number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method return the description of the course.
     * 
     * @return - the course description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method return the capacity of the course.
     * 
     * @return - the course capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method check if the student can be enrolled in the course.
     * 
     * If student is a non-null student, the course is not full, and student is
     * not already in the course, then enroll the student into the course.
     * 
     * @param student - the student to be enroll.
     * @return - true if student is enrolled; false if the student can not be
     * enroll in the course.
     * @throws IllegalArgumentException - if student is null.
     */
    public boolean enroll(Student student) {
        // If student is null, throw an exception
        if (student == null) {
            throw new IllegalArgumentException();
        }
        // If the student is not full and the course does not contain that
        // specific student, enroll the student
        if (!this.isFull() && !enrolled.contains(student)) {
            enrolled.add(student);
            // Return true for a successful enrollment
            return true;
        }
        // Return false for an unsuccessful enrollment
        return false;
    }

    /**
     * This method check if the student can be unenroll in the course.
     * 
     * If student is a non-null student and the student is in the course, then
     * unenroll the student from the course.
     * 
     * @param student - the student to be unenroll.
     * @return - true if student is unenrolled; false if the student can not be
     * unenroll from the course.
     * @throws IllegalArgumentException - if student is null.
     */
    public boolean unenroll(Student student) {
        // If student is null, throw an exception
        if (student == null) {
            throw new IllegalArgumentException();
        }
        // If student is in the course, unenroll the student
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            // Return true for a successful unenrollment
            return true;
        }
        // Return false for an unsuccessful unenrollment
        return false;
    }

    /**
     * This method remove all student from the course.
     */
    public void cancel() {
        // A for each loop that go through the roster of student and uneroll
        // every students
        for (Student stu : this.getRoster()) {
            this.unenroll(stu);
        }
    }

    /**
     * This method check if the course is full or not.
     * 
     * @return true if the course is full; false if the course if not full.
     */
    public boolean isFull() {
        // Check if the total ammount of student enrolled is the same as class
        // capacity
        if (this.getEnrolledCount() == capacity) {
            return true;
        }
        return false;
    }

    /**
     * This method return the number of students that is enrolled in the course.
     * 
     * @return - the number of students in the course.
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * This method return the number of students that can still enroll in the
     * course (assuming everyone stays enrolled).
     * 
     * @return - avaliable seats in a course.
     */
    public int getAvailableSeats() {
        return capacity - this.getEnrolledCount();
    }

    /**
     * This method make a shallow copy of the students enrolled in the course.
     * 
     * @return - a copy of students in the course.
     */
    public HashSet<Student> getStudents() {
        // Create a new HashSet with type Student
        HashSet<Student> students = new HashSet<Student>();

        // Set the new HashSet to the clone HashSet of enrolled
        students = (HashSet<Student>) enrolled.clone();

        // Return the new HashSet clone
        return students;
    }

    /**
     * This method convert the collection of student into an arraylist.
     * 
     * @return - a sorted arraylist of student object.
     */
    public ArrayList<Student> getRoster() {
        // Create an iterator for students object
        Iterator students = enrolled.iterator();

        // Create an empty array list for the roster
        ArrayList studentRoster = new ArrayList<Student>();

        // While there is a student, that student will be added to the arraylist
        while (students.hasNext()) {
            studentRoster.add(students.next());
        }

        // This will sort all the student in the increasing order
        Collections.sort(studentRoster);

        // Return the roster sorted
        return studentRoster;
    }

    /**
     * This method return a string representation of course object in the format
     * of <department> <number> [<capacity>]\n<description>.
     * 
     * @return - a string of course object.
     */
    public String toString() {
        String result = String.format("%s %s [%s]\n%s", this.department,
                this.number, this.capacity, this.description);
        return result;
    }
}