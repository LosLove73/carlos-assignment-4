package com.coderscampus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentParser {

	public static void main(String[] args) {
		String csvFile = "student-master-list.csv";
		String line = " ";
		String csvSplitBy = ",";

		// Create Lists to hold students based on courseName
		List<Student> compSciStudents = new ArrayList<>();
		List<Student> statisticsStudents = new ArrayList<>();
		List<Student> apMathStudents = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			// Skips the header line
			br.readLine();

			// Read each line
			while ((line = br.readLine()) != null) {
				// split student data by commas
				String[] studentData = line.split(csvSplitBy);

				// Validate and Parse student data...
				int studentId;
				try {
					studentId = Integer.parseInt(studentData[0]);
				} catch (NumberFormatException e) {
					System.err.println("Invalid studentId format for line: " + line);
					continue; // Skip this line and proceed to the next one
				}

				String studentName = studentData[1];
				String courseName = studentData[2];

				int studentGrade;
				try {
					studentGrade = Integer.parseInt(studentData[3]);
				} catch (NumberFormatException e) {
					System.err.println("Invalid studentGrade format for line: " + line);
					continue; // Skip this line and proceed to the next one
				}

				// Constructor to create a new student object...
				Student student = new Student(studentId, studentName, courseName, studentGrade);

				// Separate students by List (courseName)
				if (courseName.contains("COMPSCI")) {
					compSciStudents.add(student);
				} else if (courseName.contains("STAT")) {
					statisticsStudents.add(student);
				} else if (courseName.contains("APMTH")) {
					apMathStudents.add(student);
				} else {
					System.out.println("Unknown class: " + courseName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// No need to close BufferedReader here, it's handled by try-with-resources
		}

		// Remove null entries before sorting
		compSciStudents = removeNullEntries(compSciStudents);
		statisticsStudents = removeNullEntries(statisticsStudents);
		apMathStudents = removeNullEntries(apMathStudents);

		// Sort each list of students by studentGrade in descending order using an anonymous class
		
		// Sorting compSciStudents
		Collections.sort(compSciStudents, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s2.getStudentGrade() - s1.getStudentGrade(); // Descending order
			}
		});

		// Sorting statisticsStudents
		Collections.sort(statisticsStudents, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s2.getStudentGrade() - s1.getStudentGrade(); // Descending order
			}
		});

		// Sorting apMathStudents
		Collections.sort(apMathStudents, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s2.getStudentGrade() - s1.getStudentGrade(); // Descending order
			}
		});

		// Write each list to its own CSV file
		writeListToCsv(compSciStudents, "compsci_students.csv");
		writeListToCsv(statisticsStudents, "statistics_students.csv");
		writeListToCsv(apMathStudents, "ap_math_students.csv");
	}

	// Method to write a list of students to a CSV file
	private static void writeListToCsv(List<Student> students, String fileName) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			// Write the Header
			bw.write("StudentId,StudentName,CourseName,CourseGrade");
			bw.newLine();

			// Write each student's data
			for (Student student : students) {
				bw.write(student.studentId + "," + student.studentName + "," + student.courseName + ","
						+ student.studentGrade);
				bw.newLine();
			}
			System.out.println("Successfully written to: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to create a new list of null entries.
	private static List<Student> removeNullEntries(List<Student> students) {
		List<Student> nonNullStudents = new ArrayList<>();
		for (Student student : students) {
			if (student != null) {
				nonNullStudents.add(student);
			}
		}
		return nonNullStudents;
	}

}
