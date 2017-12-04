package com.dublabs.Controllers;


import com.dublabs.Domain.*;
import com.dublabs.Repository.*;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by tofiques on 2/11/17.
 */

@RestController
@RequestMapping("/api")
public class IndexController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private CoursesEntityRepo coursesEntityRepo;
    @Autowired
    private InstructorsRepo instructorsRepo;

    @Autowired
    private  StudentsRepo studentsRepo;

    @Autowired
    private  PreReqRepo preReqRepo;
    @Autowired
    private ListingsRepo listingsRepo;

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private  AcademicRecordsEntityRepo academicRecordsEntityRepo;

    private Integer count=0;


    /*@Autowired
       private AcademicRecordsEntityRepo academicRecordsEntityRepo;*/
    @GetMapping(value = "/test", produces = {"application/json"})
    public Blackboard index() {
        count=0;
        ResultSet resultSet = null;
        Blackboard bb = new Blackboard();
        bb.setUrl("fsdfsd");
        String sql = "SELECT 1 from dual";


        List<String> names = jdbcTemplate.query("SELECT 1 from dual", new RowMapper() {
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
        System.out.println(names);


        return bb;
    }


    @GetMapping(value = "/ins", produces = {"application/json"})
    public ResponseEntity ins() {
        Blackboard bb = new Blackboard();
        ArrayList<CoursesEntity> coursesEntities;
        ArrayList<InstructorsEntity> instructorsEntities;
        ArrayList<StudentsEntity> students;
        ArrayList<PrereqsEntity> prereqsEntities;
        ArrayList<ProgramsEntity> degreePrograms;
        ArrayList<ListingsEntity> listingsEntities;

        Scratchpad managementConsole = new Scratchpad();

        String[] managementSystemFiles = {"courses.csv", "instructors.csv", "listings.csv", "prereqs.csv", "programs.csv", "students.csv"};
        for (String nextFileName : managementSystemFiles) {
            managementConsole.uploadFileContents(nextFileName);

        }
        prereqsEntities = managementConsole.getPrereqsEntities();
        for (PrereqsEntity prereqsEntity : prereqsEntities) {
            preReqRepo.save(prereqsEntity);

        }
        coursesEntities = managementConsole.getCourses();
        for (CoursesEntity c :
                coursesEntities) {
           List<Integer> preReq= preReqRepo.findByCourseId(c.getCourseId());
           if(!preReq.isEmpty() && preReq!=null) {
               c.setPrereq(preReq);
           }
            coursesEntityRepo.save(c);
        }
        instructorsEntities = managementConsole.getInstructors();
        for (InstructorsEntity i :
                instructorsEntities) {
            instructorsRepo.save(i);

        }
        students = managementConsole.getStudents();
       for (StudentsEntity stu : students
                ) {
            studentsRepo.save(stu);
        }




        degreePrograms=managementConsole.getDegreePrograms();
        for (ProgramsEntity degree: degreePrograms
                ) {
            programRepo.save(degree);
        }

        listingsEntities=managementConsole.getListingsEntities();
        for (ListingsEntity le:listingsEntities
                ) {
            listingsRepo.save(le);
        }
        requestRepo.deleteAll();
        academicRecordsEntityRepo.deleteAll();
        return ResponseEntity.ok("OK");
    }

    @PostMapping(value = "/UploadRequests")
    public ResponseEntity uploadRequests(@RequestBody Requests requests, HttpResponse httpResponse) {
        Blackboard bb = null;
        count++;
        ArrayList<String> str = new ArrayList<>();
        str.add("dsfsadf");
        str.add("sdfsad");
        List<RequestsEntity> studentRequest=requests.getRequests();
        for(RequestsEntity re: studentRequest){

            requestRepo.save(re);
        }
        return ResponseEntity.ok(str);
    }
    
    @GetMapping(value = "/GetCourses", produces = {"application/json"})
     public List<CoursesEntity> getCourses(@RequestBody Requests requests, HttpResponse httpResponse) {
   
    	// Get All courses 
     List <CoursesEntity> courses= (List<CoursesEntity>) coursesEntityRepo.findAll();
     List <CoursesEntity> coursesInstrs =new ArrayList<CoursesEntity>();
     for (CoursesEntity course :courses){
     InstructorsEntity instructor = instructorsRepo.findByCourse_id(course.getCourseId());
       if (instructor!=null)
    	   course.setInstructor(instructor);
       coursesInstrs.add(course);    
     
     }
     	 
     	return coursesInstrs;
     	 
    }

    /*
    export interface Assign {
        CourseId:number;
        InstructorId:number;
    }*/
    @GetMapping(value = "/AssignInstructor", produces = {"application/json"})
   public ResponseEntity assignInstructor(@RequestBody Integer instructorId,@RequestBody Integer courseId, HttpResponse httpResponse) {
   // public Blackboard assign(){
    	 Blackboard bb = new Blackboard();
        InstructorsEntity courseInstructor=null;
       // List<RequestsEntity> studentRequest=requests.getRequests();
        //get the course ID from json. check if the course is assigned to different instructor
        System.out.println("findby course Id...");
       String message="";
       InstructorsEntity instructorToBeChanged =null;
           
           courseInstructor= instructorsRepo.findByCourse_id(courseId);
           System.out.println("Course 11: "+courseInstructor.getInstr_name());
       if(courseInstructor!=null)
       if(courseInstructor.getInstructor_id().equals(instructorToBeChanged)){
    	   message="Already assigned to the Instructor "+courseInstructor.getInstr_name();
       }
       else if (courseInstructor.getCourse_id()!=null)
       {
    	   //courseInstructor.setCourse_id(0); updated
    	   //instructorsRepo.save(courseInstructor);
    	   message="Couser "+courseInstructor.getCourse_id()+" Already assigned to the Instructor "+courseInstructor.getInstr_name();
       }
       else if(courseInstructor.getCourse_id()==null ||courseInstructor.getCourse_id().equals(null)){
    	   for (InstructorsEntity updateInstructor : instructorsRepo.findByInstructor_id(instructorId)) {
               System.out.println("Course: "+updateInstructor.getInstr_name());
              // instructorToBeChanged=instructor;
               updateInstructor.setCourse_id(courseId);
        	   instructorsRepo.save(updateInstructor);
        	   message= "Instructor "+courseInstructor.getInstr_name()+" is Assigned to course "+courseId;
    	   } 
       }
          
        return ResponseEntity.ok(message);
    }
    @GetMapping(value = "/GetCurrentTerm", produces = {"application/json"})
    public Term getTerm() {


        return new Term(getCount());
    }

    public Integer getCount() {
        return count;
    }
}
