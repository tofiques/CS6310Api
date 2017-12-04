package com.dublabs.Controllers;


import com.dublabs.Domain.*;
import com.dublabs.Repository.*;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/initDb")
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
           List<Integer> preReq= preReqRepo.findByCourseIdforPreRec(c.getCourseId());
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

        return ResponseEntity.ok("dfsd");
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
        return ResponseEntity.ok(new ResponseMessage<String>("ok"));
    }
    
    @GetMapping(value = "/GetCourses", produces = {"application/json"})
     public List<CoursesEntity> getCourses() {

        List <CoursesEntity> courses= (List<CoursesEntity>) coursesEntityRepo.findAll();
        List <CoursesEntity> coursesInstrs =new ArrayList<CoursesEntity>();
        for (CoursesEntity course :courses){
            InstructorsEntity instructor = instructorsRepo.findByCourse_id(course.getCourseId());
            if (instructor!=null) {
                course.setInstructor(instructor);
            }
            coursesInstrs.add(course);

        }

        return coursesInstrs;
     	 
    }
    @GetMapping(value = "/GetInstructors", produces = {"application/json"})
     public List<InstructorsEntity> getInstructors() {
   List<InstructorsEntity> instructorsEntities=instructorsRepo.getAll();

     	return instructorsEntities;

    }

    /*
    export interface Assign {
        CourseId:number;
        InstructorId:number;
    }*/
    @PostMapping(value = "/AssignInstructor")
   public ResponseEntity assignInstructor(@RequestBody Assign assign) {
        Blackboard bb = new Blackboard();
        Integer courseId=assign.getCourseId();
        Integer instructorId=assign.getInstructorId();
        InstructorsEntity courseInstructor=null;

        // List<RequestsEntity> studentRequest=requests.getRequests();

        //get the course ID from json. check if the course is assigned to different instructor

        System.out.println("findby course Id...");

        String message="";

        boolean assigned=false;

        InstructorsEntity instructorToBeChanged =null;

        courseInstructor= instructorsRepo.findByCourse_id(courseId);

        if(courseInstructor==null){

            for (InstructorsEntity updateInstructor1 : instructorsRepo.findByInstructor_id(instructorId)) {

                System.out.println("Course: "+updateInstructor1.getName());

                // instructorToBeChanged=instructor;

                updateInstructor1.setCourseId(courseId);

                instructorsRepo.save(updateInstructor1);

                message= "Instructor "+updateInstructor1.getName()+" is Assigned to course "+courseId;

                assigned=true;

            }

        }





        if(courseInstructor!=null & !assigned)

            if(courseInstructor.getName().equals(instructorToBeChanged)){

                message="Already assigned to the Instructor "+courseInstructor.getName();

            }

            else if (courseInstructor.getCourseId()!=null)

            {

                //courseInstructor.setCourse_id(0); updated

                //instructorsRepo.save(courseInstructor);

                message="Couser "+courseInstructor.getCourseId()+" Already assigned to the Instructor "+courseInstructor.getName();

            }

            else if(courseInstructor.getCourseId()==null ||courseInstructor.getCourseId().equals(null)){

                for (InstructorsEntity updateInstructor : instructorsRepo.findByInstructor_id(instructorId)) {

                    System.out.println("Course: "+updateInstructor.getName());

                    // instructorToBeChanged=instructor;

                    updateInstructor.setCourseId(courseId);

                    instructorsRepo.save(updateInstructor);

                    message= "Instructor "+courseInstructor.getName()+" is Assigned to course "+courseId;

                }

            }




        return ResponseEntity.ok(new ResponseMessage<String>(message));
    }
    @GetMapping(value = "/GetCurrentTerm", produces = {"application/json"})
    public Term getTerm() {


        return new Term(getCount());
    }

    public Integer getCount() {
        return count;
    }
}
