package com.coderscampus;

//Class to hold student data
public class Student {
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

@Override
public String toString() {
	return studentId + "," + studentName + "," + courseName + "," + studentGrade;
}

}