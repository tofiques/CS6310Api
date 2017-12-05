package edu.gatech.utils;

import edu.gatech.Domain.*;
import edu.gatech.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jineshk
 */
@Component
public class CourseValidationRequestRepository {

	/*private CourseManagerContex _contex;
    private StudentRepository studentRepository;
	private CourseRepository courseRepository;*/


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private CoursesEntityRepo coursesEntityRepo;
    @Autowired
    private InstructorsRepo instructorsRepo;

    @Autowired
    private StudentsRepo studentsRepo;

    @Autowired
    private PreReqRepo preReqRepo;
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private AcademicRecordsEntityRepo academicRecordsEntityRepo;

    ArrayList<String> preReqCourseId = null;
    ArrayList<String> courseNotOffered = null;

    public List<String> processRequests(Integer termCount) {

        List<String> result = new ArrayList<String>();
        List<RequestsEntity> grantedRequest = new ArrayList<RequestsEntity>();
        result.add("request processing");

        boolean hasPreqs;
        boolean courseOffered;

        for (RequestsEntity request : requestRepo.findAll()) {
            String message = String.format("%s, %s: ", request.getStudentId(), request.getCourseId());
            courseNotOffered = new ArrayList<String>();

            hasPreqs = hasPrerequisites(request);
            courseOffered = courseOffered(request);
            if (!courseOffered)
                courseNotOffered.add(request.getCourseId().toString());

            // meets requirements
            if (courseOffered && hasPreqs) {
                result.add(message + "granted");
                // add granted request to the list
                grantedRequest.add(request);
                continue;
            }


            // “denied: missing prerequisites” missing prerequisites and not being offered
            if ((!courseOffered && !hasPreqs) | !hasPreqs) {
                result.add(message + "denied: missing prerequisites " + String.join("," , preReqCourseId));
                continue;
            }

            // “denied: not being offered” if there are no instructors teaching the course
            if (!courseOffered) {
                result.add(message + "denied: not being offered " + String.join(",", courseNotOffered));
                continue;
            }
        }
        //split the array in to .35 "A",.45 "B",.15 "C" and .5"D".
        saveAcademicRecordsByGrade(grantedRequest, termCount);


        return result;
    }

    /**
     * @param grantedRequest
     * @param termCount
     */
    private void saveAcademicRecordsByGrade(List<RequestsEntity> grantedRequest, Integer termCount) {
        // TODO Auto-generated method stub
        String[] grades = {"A", "B", "C", "D", "F"};
        double[] percentage = {35.0F, 45.0F, 10.0F, 5.0F, 5.0F};
        int totalLength = grantedRequest.size();

        for (int j = 0; j < grades.length; j++) {
            int grade = (int) Math.round((double) (totalLength * (percentage[j] / 100.0f)));
            System.out.println("Grade" + grades[j] + " === " + grade);
            int initalsize = 0;
            int nextrange = initalsize + grade;
            List<RequestsEntity> sublist = grantedRequest.subList(initalsize, nextrange);
            System.out.println("Sublist Grade" + grades[j] + "-- " + sublist.size());
            createAcademicRecords(sublist, grades[j], termCount);

        }

    }

    /**
     * @param sublist
     * @param grade
     * @param termCount
     */
    private void createAcademicRecords(List<RequestsEntity> sublist, String grade, Integer termCount) {
        // TODO Auto-generated method stub
        // List<AcademicRecordsEntity> academicEntityRecs = new ArrayList<AcademicRecordsEntity>();

        System.out.println(" Saving Academics Recodrs saved for Grade" + grade);
        for (RequestsEntity courseRequest : sublist) {
            AcademicRecordsEntity academic = new AcademicRecordsEntity();
            academic.setCouesTerm(termCount);
            //academic.setCourseYear(2017);
            academic.setCourseId(courseRequest.getCourseId());
            academic.setStudentId(courseRequest.getStudentId());
            academic.setStudentGrade(grade);
            academicRecordsEntityRepo.save(academic);
            //academicEntityRecs.add(academic);
        }

        //academicRecordsEntityRepo.save(academicEntityRecs);
        System.out.println("Completd Academics Recodrs saving for Grade" + grade);

    }

    @SuppressWarnings("null")
    public boolean hasPrerequisites(RequestsEntity courseRequest) {

        // get student
        StudentsEntity student = studentsRepo.findByStudentId(courseRequest.getStudentId());
        preReqCourseId = new ArrayList<String>();
        // get course
        CoursesEntity course = coursesEntityRepo.findByCourseId(courseRequest.getCourseId());

        // get passed courses
        List<CoursesEntity> passedCourses = getPassedCourses(student);

        List<PrereqsEntity> preReqs = (List<PrereqsEntity>) preReqRepo.findByCourseId(course.getCourseId());
        if (preReqs != null || preReqs.size() > 0) {
            for (PrereqsEntity preReq : preReqs) {
                if (!passedCourses.stream().anyMatch(x -> x.getCourseId() == preReq.getPrereqCourseId())) {
                    preReqCourseId.add(preReq.getPrereqCourseId().toString());
                }
            }
            return preReqCourseId.size() == 0;
        }
        return true;
    }

    public List<CoursesEntity> getPassedCourses(StudentsEntity student) {

        List<CoursesEntity> passedCourses = new ArrayList<CoursesEntity>();
        List<AcademicRecordsEntity> academicEntity = academicRecordsEntityRepo.findByStudentId(student.getStudentId());

        if (!academicEntity.isEmpty()) {
            for (AcademicRecordsEntity record : academicEntity) {
                if (IsPassing(record.getStudentGrade())) {
                    CoursesEntity course = coursesEntityRepo.findByCourseId(record.getCourseId());
                    if (course != null)
                        passedCourses.add(course);
                }
            }
        }

        return passedCourses;
    }

    public boolean IsPassing(String inputStr) {
        String[] passingGrades = {"A", "B", "C", "D"};
        return Arrays.stream(passingGrades).anyMatch(inputStr::contains);
    }

    public boolean courseOffered(RequestsEntity courseRequest) {

        // get course id
        CoursesEntity course = coursesEntityRepo.findByCourseId(courseRequest.getCourseId());
//get List of Instructors
        List<InstructorsEntity> instructors = (List<InstructorsEntity>) instructorsRepo.findAll();        // determine if class is being offered
        return instructors.stream().filter(x -> x.getCourseId() != 0)
                .anyMatch(x -> x.getCourseId() == course.getCourseId());
    }
}
