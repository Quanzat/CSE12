
/**
 * Name: Quan Tran
 * ID: A16191839
 * Email: qutran@ucsd.edu
 * Sources used: Lecture
 * 
 * The purpose of this file is to test the methods written in Student.java,
 * Course.java, and Sanctuary.java. These tests consider test cases that did not 
 * get covered in the public tester.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class is a custom tester class that test methods written in other
 * classes to check its functionalities.
 */
public class CustomTester {

    // ----------------Student class----------------
    /**
     * Test the equals method when Student is null and when student is different
     */
    @Test
    public void testEquals() {
        // Testing when student2 is different from student 1
        Student student1 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        Student student2 = new Student(new String("Selena"), new String("Tran"),
                new String("A12345678"));
        assertFalse(student1.equals(student2));

        // Testing when student is null
        try {
            Student studentNull = new Student(null, null, null);
        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }

        // Testing when student2 is different first name
        Student student3 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        Student student4 = new Student(new String("Selena"), new String("Tran"),
                new String("A16191839"));
        assertFalse(student3.equals(student4));

        // Testing when student2 is different PID
        Student student5 = new Student(new String("Selena"), new String("Tran"),
                new String("A16191839"));
        Student student6 = new Student(new String("Selena"), new String("Tran"),
                new String("A12345678"));
        assertFalse(student5.equals(student6));

        // Testing when student2 is different last name
        Student student7 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        Student student8 = new Student(new String("Quan"), new String("Le"),
                new String("A16191839"));
        assertFalse(student7.equals(student8));
    }

    /**
     * Test the compareTo method when: Two student are the same / Two student
     * with the same first name and PID / Two student are the same first and
     * last name.
     * 
     * For all test, also test the orders as well.
     */
    @Test
    public void testCompareTo() {
        // Test students with the same for everything
        Student student1 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        Student student2 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));

        // Student2 is behind
        assertEquals(0, student1.compareTo(student2));

        // Student1 is behind
        assertEquals(0, student2.compareTo(student1));

        // Test students with same first name and PID but different last name
        Student student3 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        Student student4 = new Student(new String("Selena"), new String("Tran"),
                new String("A16191839"));

        // Student4 is behind
        for (int i = 0; i > 0; i--)
            assertEquals(i, student3.compareTo(student4));

        // Student3 is behind
        for (int i = 0; i < 0; i++)
            assertEquals(i, student4.compareTo(student3));

        // Test students with same first and last name but different PID
        Student student5 = new Student(new String("Selena"), new String("Tran"),
                new String("A16191839"));
        Student student6 = new Student(new String("Selena"), new String("Tran"),
                new String("A12345678"));

        // Student6 is behind
        for (int i = 0; i > 0; i--)
            assertEquals(i, student5.compareTo(student6));
        // Student5 is behind
        for (int i = 0; i < 0; i++)
            assertEquals(i, student6.compareTo(student5));

    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when student object is null, and when the course
     * is already full.
     */
    @Test
    public void testEnroll() {
        Course course = new Course("CSE", "12", "Data Structure", 3);
        course.enrolled = new HashSet<>();
        Student student1 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));
        // Test when enrolling a null student object
        try {
            course.enroll(null);

        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }

        // Test when enrolling a student in a class that is already full
        for (int i = 0; i < 3; i++) {
            course.enrolled.add(new Student("A" + i, "B", "C"));
        }
        assertTrue(course.isFull());
        assertFalse(course.enroll(student1));
    }

    /**
     * Test the unenroll method when student object is null and when student is
     * not in the class
     */
    @Test
    public void testUnenroll() {
        Course course = new Course("CSE", "12", "Data Structure", 3);
        course.enrolled = new HashSet<>();
        Student student1 = new Student(new String("Quan"), new String("Tran"),
                new String("A16191839"));

        // Test when unenrolling a null student object
        try {
            course.unenroll(null);

        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }

        // Test unenrolling a student that is not in the class
        assertFalse(course.unenroll(student1));

    }

    /**
     * Test the getRoster method when calling it on a course with 3 students and
     * check if it get sorted in an increasing order
     */
    @Test
    public void testGetRoster() {
        Course course = new Course("CSE", "12", "Data Structure", 3);
        course.enrolled = new HashSet<>();

        Student stu1 = new Student("Whales", "Ocean", "A123");
        Student stu2 = new Student("Selena", "Tran", "A135");
        Student stu3 = new Student("Quan", "Tran", "A789");

        // Test if the student is enrolled
        assertTrue(course.enroll(stu1));
        assertTrue(course.enroll(stu2));
        assertTrue(course.enroll(stu3));

        // Create a roster by calling the getRoster method
        ArrayList<Student> roster = course.getRoster();

        // Check if the roster is in order
        assertEquals(stu1, roster.get(0));
        assertEquals(stu3, roster.get(1));
        assertEquals(stu2, roster.get(2));
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when input is negative
     */
    @Test
    public void testSanctuaryConstructor() {
        try {
            Sanctuary sact = new Sanctuary(-1, -1);
        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }
    }

    /**
     * Test the rescue method when number of animals is negative and species is
     * null
     */
    @Test
    public void testRescueTestOne() {
        Sanctuary sanct = new Sanctuary(500, 50);

        try {
            sanct.rescue("Shiba", -1);
            sanct.rescue(null, 10);
        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }
    }

    /**
     * Test the rescue method when adding animals would reach over maximun
     * allowed animals in the sanctuary
     */
    @Test
    public void testRescueTestTwo() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Yoda", 20);
        sanct.sanctuary.put("Shiba", 20);

        assertEquals(10, sanct.rescue("Shiba", 20));
        assertEquals(10, sanct.rescue("Cat", 10));
    }

    /**
     * Test the release method when num is less 0; num is bigger than the
     * avaliable animals in the sanctuary; species is null; species does not
     * exist in the sanctuary
     */
    @Test
    public void testReleaseTestOne() {
        Sanctuary sanct = new Sanctuary(500, 50);
        try {
            sanct.rescue("Yoda", -1);
            sanct.rescue("Shiba", 550);
            sanct.rescue(null, 10);
            sanct.rescue("Cat", 10);
        } catch (IllegalArgumentException e) {
            // Exception catched. Test passed!
        }
    }

    /**
     * Test the release method when the species is completely removed
     */
    @Test
    public void testReleaseTestTwo() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Yoda", 20);
        sanct.sanctuary.put("Shiba", 20);

        sanct.release("Shiba", 20);
        assertEquals(1, sanct.sanctuary.size());
    }
}
