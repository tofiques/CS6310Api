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


    /*@Autowired
       private AcademicRecordsEntityRepo academicRecordsEntityRepo;*/
    @GetMapping(value = "/test", produces = {"application/json"})
    public Blackboard index() {

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
    public Blackboard ins() {
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
        coursesEntities = managementConsole.getCourses();
        for (CoursesEntity c :
                coursesEntities) {
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

        prereqsEntities = managementConsole.getPrereqsEntities();
        for (PrereqsEntity prereqsEntity : prereqsEntities) {
            preReqRepo.save(prereqsEntity);

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

        return bb;
    }

    @PostMapping(value = "/uploadRequests")
    public ResponseEntity uploadRequests(@RequestBody Requests requests, HttpResponse httpResponse) {
        Blackboard bb = null;
        ArrayList<String> str = new ArrayList<>();
        str.add("dsfsadf");
        str.add("sdfsad");
        List<RequestsEntity> studentRequest=requests.getRequests();
        for(RequestsEntity re: studentRequest){

            requestRepo.save(re);
        }
        return ResponseEntity.ok(str.size());
    }


}
