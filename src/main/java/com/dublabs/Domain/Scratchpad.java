package com.dublabs.Domain;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.springframework.core.io.ClassPathResource;

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
                ListingsEntity listingsEntity=new ListingsEntity(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]));
                listingsEntities.add(listingsEntity);

                break;
            case "prereqs.csv":
               PrereqsEntity prereqsEntity= new PrereqsEntity(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]));
               prereqsEntities.add(prereqsEntity);
                break;
            case "programs.csv":
                ProgramsEntity degreeProgram = new ProgramsEntity(Integer.valueOf(tokens[0]), tokens[1]);
                degreePrograms.add(degreeProgram);
                break;


            case "students.csv":
               StudentsEntity stu = new StudentsEntity(Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], Integer.valueOf(tokens[4]));
               students.add(stu);
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
            // Tofique local reader uncomment the below line 
          // fileReader = new BufferedReader(new FileReader(fileToParse));
           // Toufiq local work reader comment the below line 
           ClassPathResource cpr = new ClassPathResource("./"+fileToParse);
           InputStream is = cpr.getInputStream();
           fileReader = new BufferedReader(new InputStreamReader(is));
           /// comment till here
           
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

    public ArrayList<StudentsEntity> getStudents() {
        return students;
    }

    public ArrayList<PrereqsEntity> getPrereqsEntities() {
        return prereqsEntities;
    }

    public ArrayList<ProgramsEntity> getDegreePrograms() {
        return degreePrograms;
    }

    public ArrayList<ListingsEntity> getListingsEntities() {
        return listingsEntities;
    }
}
