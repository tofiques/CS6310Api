package com.dublabs.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dublabs.Domain.AcademicRecordsEntity;
import com.dublabs.Domain.CoursesEntity;
import com.dublabs.Domain.InstructorsEntity;
import com.dublabs.Domain.PrereqsEntity;
import com.dublabs.Domain.RequestsEntity;
import com.dublabs.Domain.StudentsEntity;
import com.dublabs.Repository.AcademicRecordsEntityRepo;
import com.dublabs.Repository.CoursesEntityRepo;
import com.dublabs.Repository.InstructorsRepo;
import com.dublabs.Repository.PreReqRepo;

import com.dublabs.Repository.RequestRepo;
import com.dublabs.Repository.StudentsRepo;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jineshk
 *
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
	    private  StudentsRepo studentsRepo;

	    @Autowired
	    private  PreReqRepo preReqRepo;
	   	    @Autowired
	    private RequestRepo requestRepo;
	    @Autowired
	    private  AcademicRecordsEntityRepo academicRecordsEntityRepo;
	

	public List<String> processRequests() {

		List<String> result = new ArrayList<String>();	
		List<RequestsEntity> grantedRequest = new ArrayList<RequestsEntity>();
		result.add("request processing");
		
		boolean hasPreqs;
		boolean courseOffered;

		for (RequestsEntity request : requestRepo.findAll()) {
			String message = String.format("%s, %s: ", request.getStudentId(), request.getCourseId());

			hasPreqs = hasPrerequisites(request);
			courseOffered = courseOffered(request);

			// meets requirements
			if (courseOffered && hasPreqs) {
				result.add(message + "granted");
				// add granted request to the list
				grantedRequest.add(request);
				continue;
			}

			// “denied: missing prerequisites” missing prerequisites and not being offered
			if ((!courseOffered && !hasPreqs) | !hasPreqs) {
				result.add( message + "denied: missing prerequisites");
				continue;
			}

			// “denied: not being offered” if there are no instructors teaching the course
			if (!courseOffered) {
				result.add(message + "denied: not being offered");
				continue;
			}
		}
		//split the array in to .35 "A",.45 "B",.15 "C" and .5"D".
	   saveAcademicRecordsByGrade(grantedRequest);
		

		return result;
	}
/**
 * 
 * @param grantedRequest
 */
	private void saveAcademicRecordsByGrade(List<RequestsEntity> grantedRequest) {
		// TODO Auto-generated method stub
		String[] grades={"A","B","C","D"};
		double[] percentage={35.0F,45.0F,15.0F,5.0F};
		int totalLength=grantedRequest.size();
		
		for(int j=0 ;j<grades.length;j++){
		int grade = (int) Math.round((double)(totalLength*(percentage[j]/100.0f)));
		System.out.println("Grade"+grades[j]+" === "+grade);
		int initalsize=0;
		int nextrange=initalsize+grade;
		List<RequestsEntity> sublist=grantedRequest.subList(initalsize, nextrange);
		System.out.println("Sublist Grade"+grades[j]+"-- "+sublist.size());
		createAcademicRecords(sublist,grades[j]);
		
		}
		
	}
/**
 * 
 * @param sublist
 * @param grade
 */
	private void createAcademicRecords(List<RequestsEntity> sublist,String grade) {
		// TODO Auto-generated method stub
		List<AcademicRecordsEntity> academicEntityRecs = new ArrayList<AcademicRecordsEntity>();
		AcademicRecordsEntity academic=null;
		System.out.println(" Saving Academics Recodrs saved for Grade"+grade);
		for(RequestsEntity courseRequest:sublist)
		{
			academic = new AcademicRecordsEntity();
			academic.setCouesTerm(3);
			academic.setCourseYear(2017);
			academic.setCourseId(courseRequest.getCourseId());
			academic.setStudentId(courseRequest.getStudentId());
			academic.setStudentGrade(grade);
			academicEntityRecs.add(academic);	
		}
		
		academicRecordsEntityRepo.save(academicEntityRecs);
		System.out.println("Completd Academics Recodrs saving for Grade"+grade);
		
	}

	@SuppressWarnings("null")
	public boolean hasPrerequisites(RequestsEntity courseRequest) {

		// get student
		StudentsEntity student = studentsRepo.findByStudentId(courseRequest.getStudentId());

		// get course
		CoursesEntity course = coursesEntityRepo.findByCourseId(courseRequest.getCourseId());

		// get passed courses
		List<CoursesEntity> passedCourses = getPassedCourses(student);

		// fail on first
		List<PrereqsEntity> preReqs = preReqRepo.findByCourseId(course.getCourseId());
		if(preReqs!=null ||preReqs.size()>0)
		for (PrereqsEntity preReq : preReqs) {
			if (!passedCourses.stream().anyMatch(x -> x.getCourseId() == preReq.getCourseId())) {
				return false;
			}
		}

		return true;
	}
	
	public List<CoursesEntity> getPassedCourses(StudentsEntity student) {

		List<CoursesEntity> passedCourses=new ArrayList<CoursesEntity>();
		List<AcademicRecordsEntity> academicEntity = academicRecordsEntityRepo.findByStudentId(student.getStudentId());

		if (!academicEntity.isEmpty()) {
			for (AcademicRecordsEntity record:academicEntity) {
				if (IsPassing(record.getStudentGrade())) {
					CoursesEntity course = coursesEntityRepo.findByCourseId(record.getCourseId());
					if(course!=null)
					passedCourses.add(course);
				}
			}
		}

		return passedCourses;
	}
	public boolean IsPassing(String inputStr) {
		String[] passingGrades = { "A", "B", "C","D" };
		return Arrays.stream(passingGrades).anyMatch(inputStr::contains);
	}
	public boolean courseOffered(RequestsEntity courseRequest) {

		// get course id
		CoursesEntity  course = coursesEntityRepo.findByCourseId(courseRequest.getCourseId());
//get List of Instructors
		 List<InstructorsEntity> instructors = (List<InstructorsEntity>) instructorsRepo.findAll()
;		// determine if class is being offered
		return instructors.stream().filter(x -> x.getCourseId() != 0)
				.anyMatch(x -> x.getCourseId() == course.getCourseId());
	}
}
