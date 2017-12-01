CREATE TABLE `academic_records` (
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `student_grade` varchar(1) DEFAULT NULL,
  `course_year` int(11) DEFAULT NULL,
  `coues_term` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_academic_student` (`student_id`),
  KEY `FK_academic_course` (`course_id`),
  CONSTRAINT `FK_academic_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FK_academic_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL,
  `course_title` varchar(255) DEFAULT NULL,
  `course_cost` double DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `terminx` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `instructors` (
  `instructor_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `instr_name` varchar(255) DEFAULT NULL,
  `office_hours` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`instructor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=big5;


CREATE TABLE `listings` (
  `program_id` int(11) DEFAULT NULL,
  `ourse_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

CREATE TABLE `prereqs` (
  `course_id` int(11) DEFAULT NULL,
  `prereq_course_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

CREATE TABLE `programs` (
  `program_id` int(11) NOT NULL,
  `program_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`program_id`)
) ENGINE=MyISAM DEFAULT CHARSET=big5;


CREATE TABLE `requests` (
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8;


CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_addr` varchar(4000) DEFAULT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `program_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `terminx` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;