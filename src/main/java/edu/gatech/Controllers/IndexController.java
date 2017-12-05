package edu.gatech.Controllers;


import edu.gatech.Domain.*;
import edu.gatech.Repository.*;
import edu.gatech.utils.CourseValidationRequestRepository;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private StudentsRepo studentsRepo;

    @Autowired
    private TermRepo termRepo;

    @Autowired
    private PreReqRepo preReqRepo;
    @Autowired
    private ListingsRepo listingsRepo;

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private AcademicRecordsEntityRepo academicRecordsEntityRepo;

    @Autowired
    private CourseValidationRequestRepository validate;

    @Autowired
    InstructorReassignmentRepo instructorReassignmentRepo;


    @GetMapping(value = "/initDb")
    public ResponseEntity ins() {
        coursesEntityRepo.deleteAll();
        instructorsRepo.deleteAll();
        studentsRepo.deleteAll();
        preReqRepo.deleteAll();
        listingsRepo.deleteAll();
        requestRepo.deleteAll();
        academicRecordsEntityRepo.deleteAll();
        termRepo.deleteAll();

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
            List<Integer> preReq = preReqRepo.findByCourseIdforPreRec(c.getCourseId());
            if (!preReq.isEmpty() && preReq != null) {
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


        degreePrograms = managementConsole.getDegreePrograms();
        for (ProgramsEntity degree : degreePrograms
                ) {
            programRepo.save(degree);
        }

        listingsEntities = managementConsole.getListingsEntities();
        for (ListingsEntity le : listingsEntities
                ) {
            listingsRepo.save(le);
        }

        Term term = new Term();
        term.setTermId(0);
        termRepo.save(term);

        return ResponseEntity.ok(new ResponseMessage<String>("ok"));
    }

    @PostMapping(value = "/UploadRequests")
    public ResponseEntity uploadRequests(@RequestBody Requests requests, HttpResponse httpResponse) {


        requestRepo.deleteAll();

        List<RequestsEntity> studentRequest = requests.getRequests();
        for (RequestsEntity re : studentRequest) {
            if (re.getStudentId() != null && re.getCourseId() != null) {
                requestRepo.save(re);
            }
        }
        // Start validating the request with business rules.
        Integer termCount = 0;
        Term requestTerm = termRepo.getMaxTerm();
        if (requestTerm == null) {
            Term term = new Term();
            term.setTermId(termCount);
            termRepo.save(term);
        } else {
            termCount = requestTerm.getTermId();
            Term term = new Term();
            term.setTermId(termCount + 1);
            termRepo.save(term);

        }

        List<String> messages = validate.processRequests(termCount);

        return ResponseEntity.ok(new ResponseMessage<List<String>>(messages));
    }

    @GetMapping(value = "/GetCourses", produces = {"application/json"})
    public List<CoursesEntity> getCourses() {

        List<CoursesEntity> courses = (List<CoursesEntity>) coursesEntityRepo.findAll();
        List<CoursesEntity> coursesInstrs = new ArrayList<CoursesEntity>();
        for (CoursesEntity course : courses) {
            InstructorsEntity instructor = instructorsRepo.findByCourse_id(course.getCourseId());
            if (instructor != null) {
                course.setInstructor(instructor);
            }
            coursesInstrs.add(course);

        }

        return coursesInstrs;

    }

    @GetMapping(value = "/GetInstructors", produces = {"application/json"})
    public List<InstructorsEntity> getInstructors() {
        List<InstructorsEntity> instructorsEntities = instructorsRepo.getAll();

        return instructorsEntities;

    }

    @GetMapping(value = "/RunWeka", produces = {"application/json"})
    public ResponseEntity getRunWeka() {


        Map<String, String[]> attrMap = new HashMap<String, String[]>();

        // TODO do as a for each and grab from DB
        attrMap.put("Course1", new String[]{"Taken", "None"});
        attrMap.put("Course2", new String[]{"Taken", "None"});
        attrMap.put("Course3", new String[]{"Taken", "None"});
        attrMap.put("Course4", new String[]{"Taken", "None"});
        attrMap.put("Course5", new String[]{"Taken", "None"});
        attrMap.put("Course6", new String[]{"Taken", "None"});
        attrMap.put("Course7", new String[]{"Taken", "None"});
        attrMap.put("Course8", new String[]{"Taken", "None"});

        AprioriEngine engine = new AprioriEngine("course-data", attrMap, null);
        String[] dataarray = engine.Run().split("\n");
        System.out.println(dataarray.toString());


        return ResponseEntity.ok(new ResponseMessage<String[]>(dataarray));
    }

    @PostMapping(value = "/AssignInstructor")
    public ResponseEntity assignInstructor(@RequestBody Assign assign) {

        Integer courseId = assign.getCourseId();
        Integer instructorId = assign.getInstructorId();
        InstructorsEntity courseInstructor = null;
        Integer term = termRepo.getMaxTerm().getTermId();
        // List<RequestsEntity> studentRequest=requests.getRequests();

        String message = "";


        boolean assigned = false;
        if (term == 0) {
            InstructorsEntity instructorToBeChanged = null;

            courseInstructor = instructorsRepo.findByCourse_id(courseId);

            if (courseInstructor == null) {

                InstructorsEntity updateInstructor1 = instructorsRepo.findByInstructor_id(instructorId);

                System.out.println("Course: " + updateInstructor1.getName());

                // instructorToBeChanged=instructor;

                updateInstructor1.setCourseId(courseId);

                instructorsRepo.save(updateInstructor1);

                message = "Instructor " + updateInstructor1.getName() + " is Assigned to course " + courseId;

                assigned = true;
            }


            if (courseInstructor != null & !assigned)

                if (courseInstructor.getName().equals(instructorToBeChanged)) {

                    message = "Already assigned to the Instructor " + courseInstructor.getName();

                } else if (courseInstructor.getCourseId() != null)

                {

                    //courseInstructor.setCourse_id(0); updated

                    //instructorsRepo.save(courseInstructor);

                    message = "Course " + courseInstructor.getCourseId() + " Already assigned to the Instructor " + courseInstructor.getName();

                } else if (courseInstructor.getCourseId() == null || courseInstructor.getCourseId().equals(null)) {

                    InstructorsEntity updateInstructor = instructorsRepo.findByInstructor_id(instructorId);

                    System.out.println("Course: " + updateInstructor.getName());

                    // instructorToBeChanged=instructor;

                    updateInstructor.setCourseId(courseId);

                    instructorsRepo.save(updateInstructor);

                    message = "Instructor " + courseInstructor.getName() + " is Assigned to course " + courseId;


                }

        } else {
            if (instructorReassignmentRepo.findByTerm(term) == null) {
                InstructorsEntity instructorOld = instructorsRepo.findByCourse_id(courseId);
                if (instructorOld != null) {
                    instructorOld.setCourseId(0);
                    instructorsRepo.save(instructorOld);
                }

                InstructorsEntity instructor = instructorsRepo.findByInstructor_id(instructorId);
                instructor.setCourseId(courseId);
                instructorsRepo.save(instructor);

                InstructorReassignmentEntity instructorReassignmentEntity = new InstructorReassignmentEntity();
                instructorReassignmentEntity.setTerm(term);
                instructorReassignmentRepo.save(instructorReassignmentEntity);

                courseInstructor = instructorsRepo.findByCourse_id(courseId);
                message = "Instructor " + courseInstructor.getName() + " is Assigned to course " + courseId;

            } else {
                message = "Reassignment already used";
            }
        }


        return ResponseEntity.ok(new ResponseMessage<String>(message));
    }

    @GetMapping(value = "/GetCurrentTerm", produces = {"application/json"})
    public Term getTerm() {
        Term term = termRepo.getMaxTerm();

        return term;
    }


}

