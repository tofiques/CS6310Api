package com.dublabs.Domain;

import com.dublabs.Repository.AcademicRecordsEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Scratchpad {



    private ArrayList<StudentsEntity> students = new ArrayList<StudentsEntity>();
    private ArrayList<CoursesEntity> courses = new ArrayList<CoursesEntity>();
    private ArrayList<InstructorsEntity> instructors = new ArrayList<InstructorsEntity>();
    private ArrayList<PrereqsEntity> prereqsEntities = new ArrayList<PrereqsEntity>();
    private ArrayList<ProgramsEntity> degreePrograms = new ArrayList<ProgramsEntity>();
    private ArrayList<ListingsEntity> listingsEntities = new ArrayList<ListingsEntity>();
    public Scratchpad() {
    }

    private void processFileContents(String inputFileName, String[] tokens) {

        switch (inputFileName) {
            case "courses.csv":
                CoursesEntity course = new CoursesEntity(Integer.valueOf(tokens[0]), tokens[1], Double.valueOf(tokens[2]));
               courses.add(course);
                break;
            case "instructors.csv":
                InstructorsEntity instructor = new InstructorsEntity(Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[4]));
                instructors.add(instructor);
                break;
            case "listings.csv":
               // listings.computeIfAbsent(Integer.valueOf(tokens[0]), k -> new ArrayList<>()).add(Integer.valueOf(tokens[1]));
                break;
            case "prereqs.csv":
                //prereqs.computeIfAbsent(Integer.valueOf(tokens[0]), k -> new ArrayList<>()).add(Integer.valueOf(tokens[1]));
                break;
            case "programs.csv":
               // DegreeProgram degreeProgram = new DegreeProgram(Integer.valueOf(tokens[0]), tokens[1]);
                //degreePrograms.add(degreeProgram);
                break;
            case "records.csv":
               // Record record = new Record(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), tokens[2], Integer.valueOf(tokens[3]), Integer.valueOf(tokens[4]));
               // records.add(record);
                break;
            case "requests.csv":
                //Request request = new Request(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]));
                //requests.add(request);
                break;
            case "students.csv":
              //  Student stu = new Student(Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[4]));
               // students.add(stu);
                break;
            default:
                System.out.println("# error: unknown input file name");
                break;
        }
    }

    public void uploadFileContents(String inputFileName) {


        // Input file which needs to be parsed
        String fileToParse = inputFileName;
        BufferedReader fileReader = null;

        // Delimiter used in CSV file
        final String DELIMITER = ",";
        try {
            String line = "";
            // Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));

            // Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                processFileContents(inputFileName, tokens);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<CoursesEntity> getCourses() {
        return courses;
    }

    public ArrayList<InstructorsEntity> getInstructors() {
        return instructors;
    }
}
