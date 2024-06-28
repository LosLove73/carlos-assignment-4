package com.coderscampus;

//Class to hold student data
public class Student implements Comparable<Student> {

	int studentId;
	String studentName;
	String courseName;
	int studentGrade;
	
	
public Student (int studentId, String studentName, String courseName, int studentGrade) {
	this.studentId = studentId;
	this.studentName = studentName;
	this.courseName = courseName;
	this.studentGrade = studentGrade;
		
	}

//Getter for studentGrade
public int getStudentGrade() {
	return studentGrade;
}

@Override
public int compareTo(Student o) {
	// TODO Auto-generated method stub
	return 0;
}

//compareTo method for sorting by studentGrade descending


	
}


