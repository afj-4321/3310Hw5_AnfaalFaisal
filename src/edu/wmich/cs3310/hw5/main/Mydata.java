package edu.wmich.cs3310.hw5.main;

/**
 * Data holder
 */
public class Mydata {
    private String stuName;
    private int courseNumber;
    private char grade;

    /**
     * Simple constructor
     *
     * @param stuName      name inf specififc format
     * @param courseNumber number
     * @param grade        frade
     */
    public Mydata(String stuName, int courseNumber, char grade) {
        this.stuName = stuName;
        this.courseNumber = courseNumber;
        this.grade = grade;
    }

    /**
     * Getter of name
     *
     * @return student name
     */
    public String getStuName() {
        return stuName;
    }

    /**
     * Setter of name
     *
     * @param stuName student name
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    /**
     * Getter for course number
     * @return course number
     */
    public int getCourseNumber() {
        return courseNumber;
    }

    /**
     * Setter for course number
     * @param courseNumber course number
     */
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Getter for grade
     * @return grade
     */
    public char getGrade() {
        return grade;
    }

    /**
     * Settter for grade
     * @param grade grade
     */
    public void setGrade(char grade) {
        this.grade = grade;
    }

    /**
     * Overrided method
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "Name='" + stuName + '\'' + ", course number=" + courseNumber + ", grade=" + grade;
    }
}