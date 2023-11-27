create database swp391_g3_flm;

USE  swp391_g3_flm;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` char(10) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `job_title` varchar(20) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `setting_type` char(25) DEFAULT NULL,
  `setting_name` varchar(50) DEFAULT NULL,
  `setting_value` varchar(50) DEFAULT NULL,
  `display_order` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `role_id` int NOT NULL,
  `user_id` int NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `setting` (`setting_id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `decision` (
  `decision_id` int NOT NULL AUTO_INCREMENT,
  `decision_no` char(50) DEFAULT NULL,
  `decision_name` varchar(255) DEFAULT NULL,
  `decision_ApprovedDate` date DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `decision_CreateDate` date DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  PRIMARY KEY (`decision_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `decision_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `curriculum` (
  `curriculum_id` int NOT NULL AUTO_INCREMENT,
  `code` char(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `decision_id` int DEFAULT NULL,
  `total_credit` int DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`curriculum_id`),
  KEY `decision_id` (`decision_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `curriculum_ibfk_1` FOREIGN KEY (`decision_id`) REFERENCES `decision` (`decision_id`),
  CONSTRAINT `curriculum_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `group` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `combo_subject` char(50) DEFAULT NULL,
  `curriculum_id` int DEFAULT NULL,
  `display_order` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plo` (
  `plo_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` mediumtext,
  `curriculum_id` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`plo_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `plo_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `code` char(25) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `type` char(50) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `description` mediumtext,
  `parent_id` int DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `syllabus` (
  `syllabus_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `no_of_credit` tinyint DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_approved` bit(1) DEFAULT NULL,
  `degree_level` varchar(30) DEFAULT NULL,
  `decision_id` int DEFAULT NULL,
  `designer_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `time_allocation` mediumtext,
  `stu_task` mediumtext,
  `tool` mediumtext,
  `scoring_scale` int DEFAULT NULL,
  `note` mediumtext,
  `minAvgMarkToPass` int DEFAULT NULL,
  `creator_id` int DEFAULT NULL,
  PRIMARY KEY (`syllabus_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `syllabus_ibfk_4` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `curriculum_subject` (
  `curriculum_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `syllabus_id` int DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  `semester` varchar(50) DEFAULT NULL,
  `no_credit` tinyint DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`curriculum_id`,`subject_id`),
  KEY `subject_id` (`subject_id`),
  KEY `group_id` (`group_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `curriculum_subject_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`),
  CONSTRAINT `curriculum_subject_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `curriculum_subject_ibfk_3` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`),
  CONSTRAINT `curriculum_subject_ibfk_4` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `prerequisite` (
  `prerequisite_id` int NOT NULL,
  `curriculum_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `description` mediumtext,
  PRIMARY KEY (`prerequisite_id`,`curriculum_id`,`subject_id`),
  KEY `curriculum_id` (`curriculum_id`,`subject_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `prerequisite_ibfk_1` FOREIGN KEY (`curriculum_id`, `subject_id`) REFERENCES `curriculum_subject` (`curriculum_id`, `subject_id`),
  CONSTRAINT `prerequisite_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plo_subject` (
  `plo_id` int NOT NULL,
  `curriculum_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`plo_id`,`curriculum_id`,`subject_id`),
  KEY `curriculum_id` (`curriculum_id`,`subject_id`),
  CONSTRAINT `plo_subject_ibfk_1` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`),
  CONSTRAINT `plo_subject_ibfk_2` FOREIGN KEY (`curriculum_id`, `subject_id`) REFERENCES `curriculum_subject` (`curriculum_id`, `subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `po` (
  `po_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` mediumtext,
  `curriculum_id` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`po_id`),
  KEY `curriculum_id` (`curriculum_id`),
  CONSTRAINT `po_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`curriculum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `po_plo` (
  `po_id` int NOT NULL,
  `plo_id` int NOT NULL,
  PRIMARY KEY (`po_id`,`plo_id`),
  KEY `plo_id` (`plo_id`),
  CONSTRAINT `po_plo_ibfk_1` FOREIGN KEY (`po_id`) REFERENCES `po` (`po_id`),
  CONSTRAINT `po_plo_ibfk_2` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clo` (
  `clo_id` int NOT NULL AUTO_INCREMENT,
  `clo_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `clo_description` text,
  `syllabus_id` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`clo_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `clo_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clo_plo` (
  `clo_id` int NOT NULL,
  `plo_id` int NOT NULL,
  PRIMARY KEY (`clo_id`,`plo_id`),
  KEY `plo_id` (`plo_id`),
  CONSTRAINT `clo_plo_ibfk_1` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`),
  CONSTRAINT `clo_plo_ibfk_2` FOREIGN KEY (`plo_id`) REFERENCES `plo` (`plo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `session` (
  `session_id` int NOT NULL AUTO_INCREMENT,
  `syllabus_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `details` mediumtext,
  `learning_type` varchar(55) DEFAULT NULL,
  `is_introduce` bit(1) DEFAULT NULL,
  `is_teach` bit(1) DEFAULT NULL,
  `is_utilize` bit(1) DEFAULT NULL,
  `student_materials` varchar(255) DEFAULT NULL,
  `teacher_materials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `syllabus_id` (`syllabus_id`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`syllabus_id`) REFERENCES `syllabus` (`syllabus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `session_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `details` mediumtext,
  PRIMARY KEY (`question_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `session` (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `session_clo` (
  `session_id` int NOT NULL,
  `clo_id` int NOT NULL,
  PRIMARY KEY (`session_id`,`clo_id`),
  KEY `clo_id` (`clo_id`),
  CONSTRAINT `session_clo_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `session` (`session_id`),
  CONSTRAINT `session_clo_ibfk_2` FOREIGN KEY (`clo_id`) REFERENCES `clo` (`clo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('1', 'User Role', 'Guest', '');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('2', 'User Role', 'Teacher', '2');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('3', 'User Role', 'Student', '1');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('4', 'User Role', 'Admin', '3');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `setting_value`, `display_order`) VALUES ('5', 'user/list', 'User Managerment', 'System Page', '5');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`, `job_title`, `company`, `status`) VALUES ('1', 'admin', 'admin@gmail.com', 'admin', '123456', '3.png', 'Manager System', 'FPT ', b'1');

-- --Test---- 
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('2', 'quan', 'quannt@gmail.com', 'quannt', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('3', 'trung', 'trungnv@gmail.com', 'trungnv', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('4', 'quan', 'quantn@gmail.com', 'quantn', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('5', 'tien', 'tienlv@gmail.com', 'tienlv', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('6', 'khoa', 'khoant@gmail.com', 'khoant', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('7', 'hiep', 'hieptd@gmail.com', 'hieptd', '123456', 'avatar.jpg');
INSERT INTO `swp391_g3_flm`.`user` (`user_id`, `full_name`, `email`, `user_name`, `password`, `avatar`) VALUES ('8', 'thuong', 'thuongvv@gmail.com', 'thuongvv', '123456', 'avatar.jpg');

INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('6', 'User Role', 'Head', '6');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('7', 'User Role', 'Staff', '7');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('8', 'User Role', 'Reviewer', '8');
INSERT INTO `swp391_g3_flm`.`setting` (`setting_id`, `setting_type`, `setting_name`, `display_order`) VALUES ('9', 'User Role', 'Designer', '9');

INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('1', '8', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('3', '2', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('4', '1', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('6', '4', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('7', '5', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('8', '6', b'1');
INSERT INTO `swp391_g3_flm`.`user_role` (`role_id`, `user_id`, `is_active`) VALUES ('9', '7', b'1');

INSERT INTO `swp391_g3_flm`.`decision` (`decision_id`, `decision_no`, `decision_CreateDate`) VALUES ('1', '495/QĐ-ĐHFPT', '2023-05-16');

INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('1', 'BBA_MKT_K16D17A', 'Bachelor Program of Business Adminstration, Digital Marketing Major', 'The objective of the Bachelor of Business Administration – Digital Marketing program of FPT University is to train students into specialists in marketing management, managers, and entrepreneurs. Students will be equipped with all essential knowledge and skills to work in the field of marketing and in an international working environment, or to continue into the next higher level of education.

The program consists of four main modules:
• General knowledge and skills (13 subjects – 35 credits): Provide the general knowledge of political, cultural and social issues, and all essential skills to study and work in an active and changing environment.
• Major knowledge and skills (16 subjects – 55 credits): Provide the basic knowledge of the business administration major; and all essential skills and attitudes to become specialists in the business administration field.
• Specialized knowledge and skills (10 subjects – 37 credits): Provide the general knowledge of marketing, including behavior, services, integrated marketing, branding, and marketing in the internet era. Equip students with all the tools for marketing, selling, brand developing activities, and independent research in marketing field.
• Elective combo (4 subjects – 12 credits for each combo): Provide in-depth knowledge and skills in two minors: Digital marketing tools and Brand and event management.

Upon graduation, students can build their career in the fields of digital marketing, market research, advertising and public relations, event organizing, sales, marketing management, and start-up.', '1', '5', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('2', 'BBA_MKT_K17B', 'Bachelor Program of Business Adminstration, Digital Marketing Major', 'The objective of the Bachelor of Business Administration – Digital Marketing program of FPT University is to train students into specialists in digital marketing management, managers, and entrepreneurs. Students will be equipped with all essential knowledge and skills to work in the field of marketing and in an international working environment, or to continue into the next higher level of education.

The program consists of four main modules:
• General knowledge and skills (13 subjects – 35 credits): Provide the general knowledge of political, cultural and social issues, and all essential skills to study and work in an active and changing environment.
• Major knowledge and skills (16 subjects – 55 credits): Provide the basic knowledge of the business administration major; and all essential skills and attitudes to become specialists in the business administration field.
• Specialized knowledge and skills (10 subjects – 37 credits): Provide the general knowledge of marketing, including behavior, services, integrated marketing, branding, and marketing in the internet era. Equip students with all the tools for marketing, selling, brand developing activities, and independent research in marketing field.
• Elective combo (4 subjects – 12 credits for each combo): Provide in-depth knowledge and skills in two minors: Digital marketing tools and Brand and event management.

Upon graduation, students can build their career in the fields of digital marketing, market research, advertising and public relations, event organizing, sales, marketing management, and start-up.

Mục tiêu tổng thể của chương trình cử nhân Quản trị Kinh doanh (QTKD) - Chuyên ngành Marketing Số, Trường Đại học FPT là đào tạo người học thành các nhà chuyên môn trong các lĩnh vực marketing số, nhà quản lý, doanh nhân tiềm năng năng động và sáng tạo làm việc được trong môi trường quốc tế, tạo tiền đề cho việc học tập, nghiên cứu ở bậc học cao hơn.


Chương trình bao gồm bốn khối kiến thức lớn:
• Kiến thức kỹ năng chung (13 môn – 35 tín chỉ): Cung cấp cho người học các kiến thức chung về chính trị, văn hóa, xã hội; và các kỹ năng cần thiết để học tập và làm việc trong môi trường năng động luôn thay đổi.
• Kiến thức kỹ năng ngành (16 môn – 55 tín chỉ): Cung cấp các kiến thức cơ sở ngành quản trị kinh doanh; các kỹ năng và thái độ cần thiết để trở thành các nhà chuyên môn trong lĩnh vực quản trị kinh doanh.
• Kiến thức kỹ năng chuyên ngành (10 môn – 37 tín chỉ): Cung cấp các kiến thức chung về các lĩnh vực marketing gồm hành vi, dịch vụ, marketing tích hợp, thương hiệu và marketing thời đại internet. Trang bị cho người học các công cụ phục vụ hoạt động marketing, bán hàng, phát triển thương hiệu, và nghiên cứu độc lập trong lĩnh vực marketing.
• Lựa chọn (4 môn – 12 tín chỉ cho mỗi lựa chọn): Cung cấp các kiến thức và kỹ năng chuyên sâu về hai lĩnh vực: Công cụ marketing số và quản trị thương hiệu và sự kiện.

Sau khi tốt nghiệp, sinh viên có thể làm việc trong các lĩnh vực về marketing số, nghiên cứu thị trường, quảng cáo và quan hệ công chúng, tổ chức sự kiện, bán hàng, các vị trí quản trị về marketing, và khởi nghiệp.', '1', '5', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('3', 'BIT_SE_K16D_K17A', 'Bachelor Program of Information Technology, Software Engineering Major', '1. Training Objectives
1.1 General objective:
Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implement and promote the creativity in jobs related to the trained specialty as well as pursue further education and research.
The training program aims to:
a) To equip students with fundamental knowledge of mathematics and the IT industry together with fundamental and specialized methodologies, technologies related to the trained specialty ;
b) Train students the necessary virtues and skills in the professional working environment, know how to apply specialized knowledge of SE specialty into practical work
c) Provide students with a strong foundation in foreign languages, science, culture and society, promoting their autonomy and creativity in study, work and life
1.2 Specific objectives:
Graduates of the IT training program/SE specialty must demonstrate the following:
PO1. Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.
PO2. Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.
PO3. Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society);
PO4. Can use English well in study and work and a second foreign language in normal communication.
PO5. Honesty, high discipline in study and work, know how to work effectively in groups; know how to behave culturally at work and in society; dynamic, creative and willing to learn constantly. Demonstrate professional attitude and behavior with the ability to conceive of ideas, design, implement and operate systems in the context of corporation and society.

1.3. Job positions after graduation:
Graduates of Software Engineering can choose for themselves the following jobs:
✔ Application development programmers
✔ Business analysts
✔ Software quality assurance engineers
✔ Software process engineers
✔ Software project administrators

2. Program Learning Outcomes

3. Volume of learning of the course: 145 credits, excluding Preparation English, Military Training, compulsory and elective training activities.

4. Enrollment object
✔ In accordance with regulations on formal university enrollment; college enrollment of the Ministry of Education and Training.
✔ In accordance with regulations on enrollment of FPT university.

5. Training process, graduating conditions
✔ In accordance with regulations on formal university and college training of the Ministry of Education and Training.
✔ In accordance with regulations on training of FPT University.

6. Evaluation method
✔ In accordance with regulations on examination and assessment in the training regulations of FPT University.

1. Mục tiêu đào tạo
1.1 Mục tiêu chung:
Đào tạo kỹ sư ngành Công nghệ thông tin (CNTT)/chuyên ngành Kỹ thuật phần mềm (KTPM) có nhân cách và năng lực đáp ứng nhu cầu thực tế của xã hội, nắm vững kiến thức chuyên môn và thực hành, có khả năng tổ chức, thực hiện và phát huy sáng tạo trong các công việc liên quan đến các chuyên ngành được đào tạo.
Chương trình đào tạo nhằm:
a) Trang bị cho sinh viên kiến thức cơ bản của ngành CNTT cùng các phương pháp luận, công nghệ nền tảng và chuyên sâu của chuyên ngành;
b) Rèn luyện cho sinh viên những đức tính, kỹ năng cần thiết qua môi trường làm việc chuyên nghiệp, biết vận dụng các kiến thức ngành CNTT và các kiến thức chuyên ngành vào công việc thực tế;
c) Cung cấp cho sinh viên một nền tảng vững chắc về ngoại ngữ, khoa học, văn hóa, xã hội, phát huy tính chủ động, sáng tạo trong học tập, công việc và cuộc sống.
1.2 Mục tiêu cụ thể:
Sinh viên tốt nghiệp chương trình đào tạo phải thể hiện được những điều sau đây:
PO1. Có kiến thức cơ bản về khoa học xã hội, chính trị pháp luật, an ninh quốc phòng, kiến thức nền tảng của ngành CNTT & kiến thức chuyên sâu của chuyên ngành được đào tạo: kỹ thuật, phương pháp, công nghệ, các lĩnh vực ứng dụng chuyên sâu; xu hướng phát triển trên thế giới; đồng thời hiểu biết tổng thể thị trường, bối cảnh, chức năng, nhiệm vụ của các ngành nghề thuộc chuyên ngành được đào tạo.
PO2. Có thể làm việc được như một thành viên chính thức trong nhóm chuyên môn thuộc chuyên ngành được đào tạo: tham gia thiết kế, lựa chọn kỹ thuật và công nghệ phù hợp với xu hướng phát triển, giải quyết các vấn đề kỹ thuật; nắm được xu hướng công nghệ và yêu cầu người dùng; có thể làm kế hoạch phát triển hoàn thiện giải pháp; quản lý thực hiện và quản lý thay đổi trong phần công việc của mình; hiểu được các chính sách nhà nước về lĩnh vực chuyên ngành.
PO3. Thành thạo được các kỹ năng nghề nghiệp và các kỹ năng mềm của công dân thế kỷ 21 (kỹ năng tư duy, kỹ năng làm việc, kỹ năng sử dụng các công cụ làm việc, kỹ năng sống trong xã hội toàn cầu);
PO4. Sử dụng được tốt tiếng Anh trong học tập và công việc và một ngoại ngữ thứ hai trong giao tiếp thông thường.
PO5. Trung thực, kỷ luật cao trong học tập và công việc, biết làm việc nhóm một cách hiệu quả; biết ứng xử văn hóa trong công việc và xã hội; năng động, sáng tạo và có ý chí học tập không ngừng. Thể hiện thái độ và hành vi chuyên nghiệp với năng lực hình thành ý tưởng, thiết kế, thực hiện và vận hành hệ thống trong bối cảnh doanh nghiệp và xã hội.

1.3. Vị trí việc làm sau khi tốt nghiệp:
Sinh viên tốt nghiệp chuyên ngành Kỹ thuật phần mềm có thể lựa chọn cho mình những công việc như:
✔ Lập trình viên phát triển ứng dụng
✔ Chuyên viên phân tích nghiệp vụ
✔ Kỹ sư đảm bảo chất lượng phần mềm
✔ Kỹ sư quy trình sản xuất phần mềm
✔ Quản trị viên dự án phần mềm

2. Chuẩn đầu ra

3. Khối lượng kiến thức toàn khóa

4. Đối tượng tuyển sinh
✔ Theo quy chế tuyển sinh đại học, cao đẳng hệ chính quy của Bộ Giáo dục và Đào tạo.
✔ Theo quy chế tuyển sinh của trường Đại học FPT.

5. Quy trình đào tạo, điều kiện tốt nghiệp
✔ Thực hiện theo quy chế đào tạo đại học, cao đẳng hệ chính quy của Bộ Giáo dục và Đào tạo.
✔ Theo quy chế đào tạo của trường Đại học FPT.

6. Cách thức đánh giá
✔ Theo quy định về kiểm tra và đánh giá học phần trong quy chế đào tạo của trường Đại học FPT.', '1', '5', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('4', 'BIT_SE_K17B', 'Bachelor Program of Information Technology, Software Engineering Major', '1. Training Objectives
1.1 General objective:
Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implement and promote the creativity in jobs related to the trained specialty as well as pursue further education and research.
The training program aims to:
a) To equip students with fundamental knowledge of mathematics and the IT industry together with fundamental and specialized methodologies, technologies related to the trained specialty ;
b) Train students the necessary virtues and skills in the professional working environment, know how to apply specialized knowledge of SE specialty into practical work
c) Provide students with a strong foundation in foreign languages, science, culture and society, promoting their autonomy and creativity in study, work and life
1.2 Specific objectives:
Graduates of the IT training program/SE specialty must demonstrate the following:
PO1. Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.
PO2. Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.
PO3. Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society);
PO4. Can use English well in study and work and a second foreign language in normal communication.
PO5. Honesty, high discipline in study and work, know how to work effectively in groups; know how to behave culturally at work and in society; dynamic, creative and willing to learn constantly. Demonstrate professional attitude and behavior with the ability to conceive of ideas, design, implement and operate systems in the context of corporation and society.

1.3. Job positions after graduation:
Graduates of Software Engineering can choose for themselves the following jobs:
✔ Application development programmers
✔ Business analysts
✔ Software quality assurance engineers
✔ Software process engineers
✔ Software project administrators

2. Program Learning Outcomes

3. Volume of learning of the course: 145 credits, excluding Preparation English, Military Training, compulsory and elective training activities.

4. Enrollment object
✔ In accordance with regulations on formal university enrollment; college enrollment of the Ministry of Education and Training.
✔ In accordance with regulations on enrollment of FPT university.

5. Training process, graduating conditions
✔ In accordance with regulations on formal university and college training of the Ministry of Education and Training.
✔ In accordance with regulations on training of FPT University.

6. Evaluation method
✔ In accordance with regulations on examination and assessment in the training regulations of FPT University.', '1', '5', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('5', 'BIT_SE_K17C', 'Bachelor Program of Information Technology, Software Engineering Major', '1. Training Objectives
1.1 General objective:
Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implement and promote the creativity in jobs related to the trained specialty as well as pursue further education and research.
The training program aims to:
a) To equip students with fundamental knowledge of mathematics and the IT industry together with fundamental and specialized methodologies, technologies related to the trained specialty ;
b) Train students the necessary virtues and skills in the professional working environment, know how to apply specialized knowledge of SE specialty into practical work
c) Provide students with a strong foundation in foreign languages, science, culture and society, promoting their autonomy and creativity in study, work and life
1.2 Specific objectives:
Graduates of the IT training program/SE specialty must demonstrate the following:
PO1. Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.
PO2. Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.
PO3. Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society);
PO4. Can use English well in study and work and a second foreign language in normal communication.
PO5. Honesty, high discipline in study and work, know how to work effectively in groups; know how to behave culturally at work and in society; dynamic, creative and willing to learn constantly. Demonstrate professional attitude and behavior with the ability to conceive of ideas, design, implement and operate systems in the context of corporation and society.

1.3. Job positions after graduation:
Graduates of Software Engineering can choose for themselves the following jobs:
✔ Application development programmers
✔ Business analysts
✔ Software quality assurance engineers
✔ Software process engineers
✔ Software project administrators

2. Program Learning Outcomes

3. Volume of learning of the course: 145 credits, excluding Preparation English, Military Training, compulsory and elective training activities.

4. Enrollment object
✔ In accordance with regulations on formal university enrollment; college enrollment of the Ministry of Education and Training.
✔ In accordance with regulations on enrollment of FPT university.

5. Training process, graduating conditions
✔ In accordance with regulations on formal university and college training of the Ministry of Education and Training.
✔ In accordance with regulations on training of FPT University.

6. Evaluation method
✔ In accordance with regulations on examination and assessment in the training regulations of FPT University.', '1', '5', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `is_active`) VALUES ('6', 'BIT_SE_K16C', 'Bachelor Program of Information Technology, Software Engineering Major', '1. Training Objectives
1.1 General objective:
Training Information Technology (IT)/Software Engineering (SE) specialty engineers with personality and capacity to meet the needs of society, mastering professional knowledge and practice, being able to organize, implement and promote the creativity in jobs related to the trained specialty as well as pursue further education and research.
The training program aims to:
a) To equip students with fundamental knowledge of mathematics and the IT industry together with fundamental and specialized methodologies, technologies related to the trained specialty ;
b) Train students the necessary virtues and skills in the professional working environment, know how to apply specialized knowledge of SE specialty into practical work
c) Provide students with a strong foundation in foreign languages, science, culture and society, promoting their autonomy and creativity in study, work and life
1.2 Specific objectives:
Graduates of the IT training program/SE specialty must demonstrate the following:
PO1. Having basic knowledge of social sciences, politics and law, security and defense, foundational knowledge of the IT industry & in-depth knowledge of the specialized training: techniques, methods, technologies, in-depth application areas; development trends in the world; at the same time understand the overall market, context, functions and tasks of the professions in the specialized training.
PO2. Be able to work as a full member of a professional team in the field of training: participate in designing, selecting techniques and technologies in line with development trends, solving technical problems; understand technology trends and user requirements; can do the complete solution development plan; performance management and change management in his or her part of the job; understand state policies in specialized fields.
PO3. Mastering professional skills and soft skills of 21st century citizens (thinking skills, work skills, skills in using work tools, life skills in a global society);
PO4. Can use English well in study and work and a second foreign language in normal communication.
PO5. Honesty, high discipline in study and work, know how to work effectively in groups; know how to behave culturally at work and in society; dynamic, creative and willing to learn constantly. Demonstrate professional attitude and behavior with the ability to conceive of ideas, design, implement and operate systems in the context of corporation and society.

1.3. Job positions after graduation:
Graduates of Software Engineering can choose for themselves the following jobs:
✔ Application development programmers
✔ Business analysts
✔ Software quality assurance engineers
✔ Software process engineers
✔ Software project administrators

2. Program Learning Outcomes

3. Volume of learning of the course: 145 credits, excluding Preparation English, Military Training, compulsory and elective training activities.

4. Enrollment object
✔ In accordance with regulations on formal university enrollment; college enrollment of the Ministry of Education and Training.
✔ In accordance with regulations on enrollment of FPT university.

5. Training process, graduating conditions
✔ In accordance with regulations on formal university and college training of the Ministry of Education and Training.
✔ In accordance with regulations on training of FPT University.

6. Evaluation method
✔ In accordance with regulations on examination and assessment in the training regulations of FPT University.', '1', '5', b'1');

INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`) VALUES ('1', 'PHE_COM1: Vovinam BIT_GD_K16D,K17A');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`) VALUES ('2', 'PHE_COM2: Cờ vua BIT_SE_K15A');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`) VALUES ('3', 'SE_COM3: Topic on .NET Programming_Chủ đề lập trình .NET BIT_SE_K15A');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`) VALUES ('4', '	SE_COM5.2: Topic on Japanese Bridge Engineer BIT_SE_K15A');

INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('1', 'OTP101', 'Orientation and General Training Program_Định hướng và Rèn luyện tập trung', 'Default', b'1', 'Orientation and general training program includes 4 modules :
* Module 1: Orientation
Main activities of this module are:
- Organizing the opening ceremony for students.
- Organizing health check amd making students cards.
- Arranging classes for students and organizing class meeting.
- Introducing to students about FPT corporation, FPT university, functional departments, training regulations and how to use information systems to support students learning.
- Sharing study skills for university students.
- Sharing about topics related to community activities. ( For example: activities towards sustainable development, volunteering activities...)
* Module 2: Military training the program prescribed by the Ministry of Education and Training.
Implementing the program prescribed by the Ministry of Education and Training.
* Module 3: Experience program
Main activities of this module are:
- Organizing research and review memoirs.
- Organizing seminars
- Organizing experiential activities for students (Towards sustainable development and volunteering for the community)
* Module 4: Vovinam
Follow the outline VOV114.
Objectives of orientation and training program are:
1) Instruct students to complete procedures before a new semester.
2) Provide students with knowledge about FPT corporation, FPT university and functional departments which support students during the period of study at the university.
3) Introduce to students about Curriculum, FU training model and regulations as well as how to use information systems to enable students to adapt new learning environment.
4) Educate students the fundamentals of military and national security, build and enrich patriotism, national pride through history lessons, seminars, documentaries, field trips to military bases and memoirs about two prolonged resistance wars of Viet Nam.
5) Train the willpower and improve physical strengths, fitness and sense of responsibilities through physical education lessons and combat practice in the field.
6) Train team spirit, disciplines, shape good attitude and behaviors towards friends, teachers and educational environment.
7) Enhance student experiences with extra-curricular activities. Strengthen the sense of community through community and volunteering activities and the ones towards the sustainable development.');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('2', '	PHE_COM*1', 'Physical Education 1_Giáo dục thể chất 1', 'Combo', b'1', ' ');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `group_id`) VALUES ('3', 'VOV114', 'Vovinam 1', 'subCombo', b'1', 'Tấn pháp: Trung bình tấn; cung tiễn tấn; kiềm dương mã tấn; đinh tấn; tam giác tấn.
Stances: Middle stance, bow stance, horse stance, front stance, triangle stance.
Kỹ thuật tay:
Hand techniques:
* 4 lối chém cạnh tay;
4 cutting edge techniques;
* 7 lối đấm;
7 punch techniques;
* 4 lối gạt cạnh tay;
4 hand parry techniques;
* 4 lối chỏ;
4 elbow techniques;
Kỹ thuật chân: Đá thẳng; đá cạnh; đá tạt; đá đạp;
Leg techniques: Straight kick; crescent kick; roundhouse kick; side kick;
Phản đòn: Phản đòn đấm thẳng phải; phản đòn đấm lao phải; phản đòn đấm múc phải.
Counter attack techniques: counter attack straight right punch; counter attack long right punch; counter attack right uppercut punch.
Thể lực chung.
Physical strength.', '1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `group_id`) VALUES ('4', 'COV111', 'Cờ Vua 1_Chess 1', 'subCombo', b'1', '* Tập trung vào các nội dung: Tri thức cơ bản trong Cờ Vua.
Focus on: basic knowledge of Chess
* Tập trung vào các nội dung: các kỹ thuật cơ bản; đấu tập..
Focus on: basic techniques; competition-style practice.', '2');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('5', 'CEA201', 'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính', 'Default', b'1', 'This course in an introduction to computer architecture and organization. It will cover topics in both the physical design of the computer (organization) and the logical design of the computer (architecture). The main contents include the organization of a simple stored-program computer: CPU, busses and memory; Instruction sets, machine code, and assembly language; Conventions for assembly language generated by compilers; Floating-point number representation; Hardware organization of simple processors; Address translation and virtual memory; Very introductory examples of input/output devices, interrupt handling and multi-tasking systems.
Chapter covered: Computer Evolution and Performance; A Top-Level View of Computer Function and Interconnection; Cache Memory; Internal Memory; External Memory; Input/Output; Operating System Support; Instruction Sets: Characteristics and Functions; Processor Structure and Function; Reduced Instruction Set Computers; Instruction-Level Parallelism and Superscalar Processors; Parallel Processing; Multicore Computers.');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('6', 'PRF192', 'Programming Fundamentals_Cơ sở lập trình', 'Default', b'1', 'Understand basics of information theory, computer system and methods of software development, focus on function-oriented programming design, coding, testing and discipline in programming.
Explain basic concepts of programming, function-oriented programming design, modularity, understand and coding programs using C
Upon completing the course, student should have:
1. Knowledge: (ABET e)
- Explain the way to solve a real problem using computer .
- Understand the basic concepts of information theory, computer system, and software development.
- Understand the basic concepts of programming, focus on procedure programming, testing and debugging, unit testing
2. Skills in programming: (ABET k)
- Read and understand the simple C programs;
- Solve real problems using C
3. Apply learning methods effectively: (ABET i)
- academic reading
- individual and team work behaviors');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('7', 'MAE101', 'Mathematics for Engineering_Toán cho ngành kỹ thuật', 'Default', b'1', ' ');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('8', 'SSG103', 'Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác', 'Default', b'1', 'Assessment structure:
* On-going Assessment:
- Activity: 10%
- Quiz: 15%
- Group Assignment : 15%
- Group Project : 30%
* Final Exam: 30%
* Completion Criteria: Every on-going assessment component > 0, Final Exam >=4, Final Result >=5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`) VALUES ('9', 'SSG104', 'Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác', 'Default', b'1', 'This course covers both working in groups and communication skills. The course covers theories of communication, working in group, and activities for students to practice applying the theories in academic and working contexts.', '8');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`) VALUES ('10', 'LAB211', 'OOP with Java Lab_Thực hành OOP với Java', 'Default', b'1', 'This course focuses on basic problems related to Java programming skills. Students are required to implement all assignments by him/her self in lab rooms.
Each assignment must be completed continuosly in the defined time. ');


INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '5', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '6', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '7', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '8', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '9', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('2', '10', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '5', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '6', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '7', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '8', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '9', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '10', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '5', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '6', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '7', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '8', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '9', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('4', '10', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '5', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '6', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '7', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '8', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '9', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('5', '10', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '3', '1', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `group_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '4', '2', '1', '0', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '5', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '6', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '7', '2', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '8', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '9', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('6', '10', '3', '3', b'1');

INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`, `combo_subject`, `curriculum_id`) VALUES ('5', 'BIT_GD_K16D,K17A TMI_ELE', 'elective', '1');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`, `combo_subject`, `curriculum_id`) VALUES ('6', 'SE_Entrepreneurship 2 and Combos', 'elective', '1');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`, `combo_subject`, `curriculum_id`) VALUES ('7', 'Entrepreneurship Group 1_ Nhóm môn Khởi nghiệp 1', 'elective', '1');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`, `combo_subject`, `curriculum_id`) VALUES ('8', 'Học phần 4 của combo SE', 'elective', '1');


INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('11', 'TRG102', 'Traditional musical instrument_Nhạc cụ truyền thống-Trống dân tộc', 'Default', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('12', 'ĐTR102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Tranh', 'Default', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('13', 'ĐTB102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Tỳ bà', 'Default', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('14', 'ĐSA102', 'Traditional musical instrument_Nhạc cụ truyền thống-Sáo trúc', 'Default', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('15', 'ĐNH102', 'Traditional musical instrument_Nhạc cụ truyền thống-Đàn Nhị', 'Default', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('16', 'TMI_ELE', 'Traditional musical instrument_Nhạc cụ truyền thống', 'Elective', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('17', 'EXE201', 'Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2', 'Default', b'1', '6');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('18', 'PRU211m', 'C# Programming and Unity_Lập trình C# và Unity', 'Default', b'1', '6');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('19', 'PRN231', 'Building Cross-Platform Back-End Application With .NET_Xây dựng ứng dụng back-end với .NET', 'Default', b'1', '6');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('20', 'MMA301', 'Multiplatform Mobile App Development_Phát triển ứng dụng di động đa nền tảng', 'Default', b'1', '6');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('21', 'WDP301', 'Web Development Project_Dự án phát triển web', 'Default', b'1', '6');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `group_id`) VALUES ('22', 'SE_EXE_ELE', 'SE_Entrepreneurship 2 and Combos_ Nhóm môn Khởi nghiệp 2 và Combos', 'Elective', b'1', null);

INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('23', 'CSD201', 'Data Structures and Algorithms_Cấu trúc dữ liệu và giải thuật', 'default', b'1', 'Upon finishing the course, students can:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('24', 'CSD203', 'Data Structures and Algorithm with Python_Cấu trúc dữ liệu và giải thuật với Python', 'default', b'1', 'This course introduces the basic concepts of data structures and algorithms in data structures with the use of Python. Topics introduced in the course include the basics of algorithm analysis, basic data structures (including stack, queue, linked list, hashtable, tree), recursion and some important applications of these data structures and algorithms.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('25', 'CSD301', 'Advanced Algorithms_Giải thuật nâng cao', 'default', b'1', 'This course cover the following topics:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('26', 'CSI102', 'Introduction to Informatics', 'default', b'1', 'This course covers following topics History of computing; Architecture; Database; Operating System; Networks; Internet; and Microsoft Office', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('27', 'CSI104', 'Introduction to Computer_Nhập môn khoa học máy tính', 'default', b'1', '	This course provides an overview of computer Fundamentals. Topics cover all areas of computer science in breadth such as computer organization, network, operating system, data structure , file structure, social and ethical issues.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('28', 'CSI105', 'Introduction to Computer Science_Nhập môn khoa học máy tính', 'default', b'1', 'This course provides an overview of the fundamentals of computer science and Artificial Intelligence (AI). In the first part, students will learn about all areas of computer science such as computer organization, networks, operating systems, data structures, file structures, social and professional ethics issues. In the second part, students will learn about the concept of AI, exploring the use and applications of AI. Students will be exposed to various issues related to AI such as scientific ethics, biases, employment and receive advice from experts on learning and starting a career in AI.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('29', 'PRO001', 'Programming with Alice', 'default', b'1', 'Alice is an innovative 3D programming environment that makes it easy to create an animation for telling a story, playing an interactive game, or a video to share on the web. Alice is a freely available teaching tool designed to be a student\'s first exposure to object-oriented programming. It allows students to learn fundamental programming concepts in the context of creating animated movies and simple video games. In Alice, 3-D objects (e.g., people, animals, and vehicles) populate a virtual world and students create a program to animate the objects', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('30', '	PRO191', 'Object-Oriented Paradigm (C++)', 'default', b'1', 'Specific topic coverage includes:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('31', 'PRO201', 'Front-end Web Development', 'default', b'1', '	This course introduces:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('32', 'PRO192', 'Object-Oriented Programming_Lập trình hướng đối tượng', 'default', b'1', '-This subject introduces the student to object-oriented programming. The student learns to build reusable objects, encapsulate data and logic within a class, inherit one class from another and implement polymorphism.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('33', 'MAD111', 'Discrete Mathematics 1', 'default', b'1', NULL, NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('34', 'MAD101', 'Discrete mathematics_Toán rời rạc', 'default', b'1', 'Upon finishing the course, students must acquire:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('35', 'OSG202', 'Operating Systems_Hệ điều hành', 'default', b'1', NULL, NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('36', 'SSG101', 'Working in Group Skills', 'default', b'1', 'This course will cover both classic and current theories of group communication that focus on \"how groups work\" and include practical information on group communication strategies and skills that emphasize \"how to work in groups\". Topics included: group development, group membership, group diversity, group leadership, group motivation, conflict and cohesion in groups, planning and conducting meetings and making presentations in groups. Learning in the class will be facilitated through the use of vehicles such as textbook readings, class discussion, exercises/in-class activities, cases and lectures.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('37', 'SSG103', 'Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác', 'default', b'1', 'This course will cover both working in groups and communication skills.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('38', 'SSG104', 'Communication and In-Group Working Skills_Kỹ năng giao tiếp và cộng tác', 'default', b'1', 'This course provides an overview of computer Fundamentals. Topics cover all areas of computer science in breadth such as computer organization, network, operating system, data structure , file structure, social and ethical issues.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('39', 'JPD111', '	Elementary Japanese 1.1_Tiếng Nhật sơ cấp 1.1', 'default', b'1', 'Nội dung học bao gồm:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('40', 'JPD112', 'Elementary Japanese 1_Tiếng Nhật sơ cấp 1', 'default', b'1', 'Nội dung học bao gồm:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('41', 'JPD113', 'Elementary Japanese 1- A1.1_Tiếng Nhật sơ cấp 1-A1.1', 'default', b'1', 'I. Yêu cầu định hướng triển khai của môn học: Môn học cung cấp kiến thức, kỹ năng cơ bản của tiếng Nhật ở trình độ sơ cấp 1 (tương đương với trình độ A1) cho đối tượng sinh viên học tiếng Nhật là ngoại ngữ 2 tại trường Đại học.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('42', 'JPD116', 'Elementary Japanese 1-A1/A2_Tiếng Nhật sơ cấp 1-A1/A2', 'default', b'1', 'I. Yêu cầu định hướng triển khai của môn học: Môn học giới thiệu các kiến thức, kĩ năng cơ bản của nửa đầu phần sơ cấp tiếng Nhật để chuẩn bị cho môn học tiếp theo.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('43', 'PRF192', 'Programming Fundamentals_Cơ sở lập trình', 'default', b'1', 'By the end of this course Students will be able to:a) Knowledge: (what will students know?)', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('44', 'PRJ301', 'Java Web Application Development_Phát triển ứng dụng Java web', 'default', b'1', 'By the end of this course Students will be able to:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('45', 'DBI202', 'Database Systems_Các hệ cơ sở dữ liệu', 'default', b'1', '- Knowledge about database systems has become an essential part of an education in computer science because database management has evolved from a specialized computer application to a central component of a modern computing environment.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('46', 'JPD123', 'Elementary Japanese 1-A1.2_Tiếng Nhật sơ cấp 1-A1.2', 'default', b'1', 'I. Yêu cầu định hướng triển khai của môn học: Môn học cung cấp kiến thức, kỹ năng cơ bản của tiếng Nhật ở trình độ sơ cấp 1 (tương đương với trình độ A1.2) cho đối tượng sinh viên học tiếng Nhật là ngoại ngữ 2 tại trường Đại học. Môn học là học phần tiếp theo môn JPD113.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('47', 'MAE101', 'Mathematics for Engineering_Toán cho ngành kỹ thuật', 'default', b'1', NULL, NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('48', 'MAS291', 'Statistics & Probability_Xác suất thống kê', 'default', b'1', 'Upon finishing the course, students must acquire:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('49', 'SWE201c', '	Introduction to Software Engineering_Nhập môn kĩ thuật phần mềm', 'default', b'1', 'SWE201c is for people who are new to software engineering. It\'s also for those who have already developed software, but wish to gain a deeper understanding of the underlying context and theory of software development practices.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('50', 'SWP391', 'Software development project_Dự án phát triển phần mềm', 'default', b'1', '\"This course focuses on designing, developing, and integrating the basic Web-based system/application using Java Web or .NET technologies (with the system requirements, technical framework & DBMS as asigned/agreed by the teacher)', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('51', 'SWR302', 'Software Requirement_Yêu cầu phần mềm', 'default', b'1', 'This course is a model-based introduction to RE, providing the conceptual background and terminology on RE, addressing a variety of techniques for requirements development including Analysis and Requirements Elicitation; Requirements Evaluation; Requirements Specification and Documentation; Requirements Quality Assurance. To implement these frameworks, student will be learnt how to find appropriate customer representatives, elicit requirements from them, and document user requirements, business rules, functional requirements, data requirements, and nonfunctional requirements.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('52', 'SWT301', 'Software Testing_Kiểm thử phần mềm', 'default', b'1', '• General concepts about software testing', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('53', 'PRM392', 'Mobile Programming_Lập trình di động', 'default', b'1', 'Upon completion of this course students should:', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('54', 'SWD392', 'Software Architecture and Design_Kiến trúc và thiết kế phần mềm', 'default', b'1', 'This is a course in concepts and methods for the architectural design of software systems of', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('55', 'EXE101', 'Experiential Entrepreneurship 1_Trải nghiệm khởi nghiệp 1', 'default', b'1', '- This course will provide students with essential knowledge and tips on starting a start-up efficiently and effectively.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('56', 'EXE201', 'Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2', 'default', b'1', '- In Experimental Experiment 2, groups of students will develop products for their start-up ideas prepared from Experimental Experiment 1 and deploy sales and find real customers for their group\'s products/services.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('57', 'MLN111', 'Philosophy of Marxism – Leninism_Triết học Mác - Lê-nin', 'default', b'1', 'Giới thiệu môn học: Triết học Mác - Lênin nghiên cứu những quan điểm duy vật biện chứng về tự nhiên, xã hội, tư duy và nhờ đó thế giới quan duy vật biện chứng trở thành toàn diện và triệt để. Áp dụng và mở rộng quan điểm duy vật biện chứng vào nghiên cứu xã hội, Mác đã đưa ra được quan niệm duy vật về lịch sử, chỉ ra con đường nghiên cứu những quy luật của sự phát triển xã hội, cũng như sự phát triển của tự nhiên, không phải do ý muốn chủ quan mà do những quy luật khách quan quyết định. Sự ra đời của Triết học Mác - Lênin đã đặt cơ sở cho việc nghiên cứu lịch sử và đời sống xã hội thực sự có tính chất khoa học.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('58', 'MLN122', 'Philosophy of Marxism – Leninism_Triết học Mác - Lê-nin', 'default', b'1', 'Giới thiệu môn học: Kinh tế chính trị Mác - Lênin hay kinh tế chính trị học Mác - Lênin là một lý thuyết kinh tế và là môn khoa học về kinh tế chính trị do C. Mác , Ăngghen và sau này là Lênin phát triển trong giai đoạn mới, có đối tượng nghiên cứu là phương thức sản xuất tư bản chủ nghĩa và những quan hệ sản xuất và trao đổi thích ứng với phương thức sản xuất tư bản chủ nghĩa. Qua đó vạch rõ bản chất, hiện tượng của các quá trình kinh tế để có cơ sở giải quyết các mối quan hệ liên quan đến học thuyết của chủ nghĩa Mác - Lênin. Cốt lõi của kinh tế chính trị Mác - Lênin là học thuyết giá trị thặng dư của Các Mác.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('59', 'HCM202', '	HCM Ideology_Tư tưởng Hồ Chí Minh', 'default', b'1', 'Giới thiệu môn học: Tư tưởng Hồ Chí Minh là kết tinh của truyền thống hàng nghìn năm dựng nước và giữ nước của dân tộc Việt Nam. Trên nền tảng đó, tư tưởng Hồ Chí Minh đã gạn lọc các hạt giống trí tuệ của phương Đông, phương Tây, vận dụng sáng tạo và phát triển chủ nghĩa Mác - Lênin vào điều kiện cụ thể của nước ta. Dưới góc độ triết học, tư tưởng Hồ Chí Minh là hệ thống các quan điểm toàn diện, sâu sắc về những vấn đề cơ bản của cách mạng Việt Nam. Mục tiêu của tư tưởng Hồ Chí Minh là hướng tới giải phóng giai cấp, giải phóng dân tộc và giải phóng con người. Do đó, tư tưởng Hồ Chí Minh đã trở thành tài sản tinh thần vô giá và là ngọn cờ dẫn dắt cách mạng Việt Nam đi từ thắng lợi này đến thắng lợi khác.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('60', 'MLN131', 'Scientific socialism_Chủ nghĩa xã hội khoa học', 'default', b'1', 'Giới thiệu môn học: Chủ nghĩa xã hội khoa học là một trong ba bộ phận của chủ nghĩa Marx-Lenin. Chủ nghĩa xã hội khoa học đã dựa trên phương pháp luận triết học duy vật biện chứng và duy vật lịch sử, đồng thời cũng dựa trên những cơ sở lý luận khoa học về các quy luật kinh tế, quan hệ kinh tế để luận giải một cách khoa học về quá trình nảy sinh cách mạng xã hội chủ nghĩa, hình thành và phát triển hình thái kinh tế - xã hội cộng sản chủ nghĩa, gắn liền với sứ mệnh lịch sử của giai cấp công nhân, nhằm giải phóng con người, giải phóng xã hội.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('61', 'VNR202', 'History of CPV - Lịch sử Đảng Cộng sản Việt Nam', 'default', b'1', '	Giới thiệu môn học: Lịch sử Đảng Cộng sản Việt Nam là một chuyên ngành, một bộ phận của khoa học lịch sử. Chủ tịch Hồ Chí Minh đã khẳng định Lịch sử Đảng Cộng sản Việt Nam là cả một pho lịch sử bằng vàng. Đó chính là tính khoa học, cách mạng, giá trị thực tiễn sâu sắc trong Cương lĩnh, đường lối của Đảng; là sự lãnh đạo đúng đắn, đáp ứng kịp thời những yêu cầu, nhiệm vụ do lịch sử đặt ra; những kinh nghiệm, bài học có tính quy luật, lý luận của cách mạng Việt Nam và những truyền thống vẻ vang của Đảng.', NULL, NULL);
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`, `description`, `parent_id`, `group_id`) VALUES ('62', 'LAB211', 'OOP with Java Lab_Thực hành OOP với Java', 'default', b'1', NULL, NULL, NULL);

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '11', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '12', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '13', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '14', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '15', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '16', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '17', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '18', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '19', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '20', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '21', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `no_credit`, `is_active`) VALUES ('1', '22', '3', b'1');

INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('1', 'Định hướng và Rèn luyện tập trung(Orientaiton and General Training Program)', '0', b'1', b'1', 'Bachelor', '1', '7', '1');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('2', 'Vovinam 1', '2', b'1', b'1', 'Bachelor', '1', '7', '3');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('3', 'Cờ Vua 1_Chess 1', '2', b'1', b'1', 'Bachelor', '1', '7', '4');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('4', 'Computer Organization and Architecture_Tổ chức và Kiến trúc máy tính', '3', b'1', b'1', 'Bachelor', '1', '7', '5');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('5', 'Programming Fundamentals_Cơ sở lập trình', '3', b'1', b'1', 'Bachelor', '1', '7', '6');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('6', 'Mathematics for Engineering_Toán cho ngành kỹ thuật', '3', b'1', b'1', 'Bachelor', '1', '7', '7');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('7', 'Communication and In-group working skills', '3', b'1', b'1', 'Bachelor', '1', '7', '8');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('8', 'OOP with Java Lab_Thực hành OOP với Java', '3', b'1', b'1', 'Bachelor', '1', '7', '10');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('9', 'C# Programming and Unity _Lập trình C# và Unity', '3', b'1', b'1', 'Bachelor', '1', '7', '18');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('10', 'Building Cross-Platform Back-End Application With .NET_Xây dựng ứng dụng back-end với With .NET', '3', b'1', b'1', 'Bachelor', '1', '7', '19');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('11', 'Multiplatform Mobile App Development_Phát triển ứng dụng di động đa nền tảng', '3', b'1', b'1', 'Bachelor', '1', '7', '20');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('12', 'Web Development Project_Dự án phát triển Web', '3', b'1', b'1', 'Bachelor', '1', '7', '21');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('13', 'Nhạc cụ truyền thống -Trống dân tộc Traditional Musical Instrument', '3', b'1', b'1', 'Bachelor', '1', '7', '11');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('14', 'Nhạc cụ truyền thống-Đàn Tranh Traditional musical instrument - Dan Tranh', '3', b'1', b'1', 'Bachelor', '1', '7', '12');
INSERT INTO `swp391_g3_flm`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `decision_id`, `designer_id`, `subject_id`) VALUES ('15', 'Nhạc cụ truyền thống- Sáo trúc Traditional musical instrument', '3', b'1', b'1', 'Bachelor', '1', '7', '14');

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '23', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '32', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '41', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '43', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '44', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '45', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '46', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '47', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '48', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '49', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '50', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '51', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '52', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '53', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '54', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '55', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '56', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '57', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '58', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '59', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '60', '3', '3', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`) VALUES ('3', '61', '3', '3', b'1');

INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('32', '3', '23', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('43', '3', '32', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('32', '3', '44', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('45', '3', '44', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('41', '3', '46', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('47', '3', '48', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('32', '3', '49', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('44', '3', '50', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('49', '3', '50', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('49', '3', '51', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('49', '3', '52', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('32', '3', '53', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('32', '3', '54', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('49', '3', '54', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('55', '3', '56', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('57', '3', '59', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('58', '3', '59', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('57', '3', '60', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('58', '3', '60', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('57', '3', '61', NULL);
INSERT INTO `swp391_g3_flm`.`prerequisite` (`prerequisite_id`, `curriculum_id`, `subject_id`, `description`) VALUES ('58', '3', '61', NULL);

INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('1', 'PO1', 'Upon graduation, students can build their career in the fields of digital marketing, market research, advertising and public relations, event organizing, sales, marketing management, and start-up.', '3', b'1');
INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('2', 'PO2', 'Mục tiêu tổng thể của chương trình cử nhân Quản trị Kinh doanh (QTKD) Trường Đại học FPT nhằm đào tạo người học thành các nhà chuyên môn trong các lĩnh vực quản trị kinh doanh, nhà quản lý, doanh nh...', '3', b'1');
INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('3', 'PO3', 'Sau khi tốt nghiệp, sinh viên có thể làm việc trong các lĩnh vực về marketing số, nghiên cứu thị trường, quảng cáo và quan hệ công chúng, tổ chức sự kiện, bán hàng, các vị trí quản trị về marketing, ...', '3', b'1');
INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('4', 'PO4', 'Have basic knowledge of social science, law and politics, national security and defense, contribute to form a worldview and scientific methodology.', '3', b'1');
INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('5', 'PO5', 'Good at English to communicate and study; can simply communicate in a second foreign language', '3', b'1');
INSERT INTO `swp391_g3_flm`.`po` (`po_id`, `name`, `description`, `curriculum_id`, `is_active`) VALUES ('6', 'PO6', 'Be professional, ethical, have social responsibility, have a spirit of service to the community', '3', b'1');

INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('1', 'CLO1', 'Describe about a program, and the way it works on computer.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('2', 'CLO2', 'Demonstrate about variables, expressions and basic operations and illustrate them by program examples.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('3', 'CLO3', 'Explain the meaning and use of Logic constructs in C Language. Describe about programming styles.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('4', 'CLO4', 'Describe about Modularity and Functions in C programs and their use.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('5', 'CLO5', 'Discuss about C Libraries and their use.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('6', 'CLO6', 'Discuss about Arrays and their use in C programs.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('7', 'CLO7', 'Discuss about Strings and their use in C programs.', '5', b'1');
INSERT INTO `swp391_g3_flm`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES ('8', 'CLO8', 'Discuss about Files and their use in C programs.', '5', b'1');

INSERT INTO `swp391_g3_flm`.`curriculum` (`curriculum_id`, `code`, `name`, `description`, `decision_id`, `owner_id`, `creator_id`, `is_active`) VALUES ('7', 'Curriculum BIT_SE', 'Bachelor Program of Information Technology, Software Engineering Major', '1. Training Objectives', '1', '5', '4', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('7', 'PLO1', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('8', 'PLO2', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('9', 'PLO3', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('10', 'PLO4', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('11', 'PLO5', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('12', 'PLO6', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('13', 'PLO7', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('14', 'PLO8', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('15', 'PLO9', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('16', 'PLO10', '7', b'1');
INSERT INTO `swp391_g3_flm`.`plo` (`plo_id`, `name`, `curriculum_id`, `is_active`) VALUES ('17', 'PLO11', '7', b'1');

INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('63', 'VOV124', 'Vovinam 2', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('64', 'VOV134', 'Vovinam 3', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('65', 'PHE_COM2', 'Cờ vua BIT_SE_K15A', 'Combo', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('66', 'COV121', 'Cờ Vua 2_Chess 2', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('67', 'COV131', 'Cờ Vua 3_Chess 3', 'Default', b'1');

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('1', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('1', '64', '3', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('2', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('2', '64', '3', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '64', '3', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('4', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('4', '64', '3', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('5', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('5', '64', '3', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('6', '63', '2', '3', b'1', '1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('6', '64', '3', '3', b'1', '1');

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('1', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('1', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('1', '67', '3', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('2', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('2', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('2', '67', '3', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '67', '3', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('4', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('4', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('4', '67', '3', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('5', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('5', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('5', '67', '3', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('6', '65', '1', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('6', '66', '2', '3', b'1', '2');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('6', '67', '3', '3', b'1', '2');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '6') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '6') and (`subject_id` = '3');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '5') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '5') and (`subject_id` = '3');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '4') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '4') and (`subject_id` = '3');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '3') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '3') and (`subject_id` = '3');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '2') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `group_id` = Null WHERE (`curriculum_id` = '2') and (`subject_id` = '3');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = '2' WHERE (`curriculum_id` = '2') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = '2' WHERE (`curriculum_id` = '3') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = '2' WHERE (`curriculum_id` = '4') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = '2' WHERE (`curriculum_id` = '5') and (`subject_id` = '4');
UPDATE `swp391_g3_flm`.`curriculum_subject` SET `type_id` = '2' WHERE (`curriculum_id` = '6') and (`subject_id` = '4');

INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('16','Data Structures and Algorithms_Cấu trúc dữ liệu và giải thuật', '3', b'1', b'1', 'Bachelor', '1', '5', '23');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('17','Object-Oriented Programming_Lập trình hướng đối tượng', '3', b'1', b'1', 'Bachelor', '1', '5', '32');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('18','Elementary Japanese 1- A1.1_Tiếng Nhật sơ cấp 1-A1.1', '3', b'1', b'1', 'Bachelor', '1', '5', '41');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('19','Programming Fundamentals_Cơ sở lập trình', '3', b'1', b'1', 'Bachelor', '1', '5', '43');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('20','Java Web Application Development_Phát triển ứng dụng Java web', '3', b'1', b'1', 'Bachelor', '1', '5', '44');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('21','Introduction to Databases_Các hệ cơ sở dữ liệu', '3', b'1', b'1', 'Bachelor', '1', '5', '45');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('22','Elementary Japanese 1-A1.2_Tiếng Nhật sơ cấp 1-A1.2', '3', b'1', b'1', 'Bachelor', '1', '5', '46');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('23','Statistics & Probability_Xác suất thống kê', '3', b'1', b'1', 'Bachelor', '1', '5', '48');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('24','	Introduction to Software Engineering_Nhập môn kĩ thuật phần mềm', '3', b'1', b'1', 'Bachelor', '1', '5', '49');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('25','Software development project_Dự án phát triển phần mềm', '3', b'1', b'1', 'Bachelor', '1', '5', '50');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('26','Software Requirement_Yêu cầu phần mềm', '3', b'1', b'1', 'Bachelor', '1', '5', '51');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('27','	Software Testing_Kiểm thử phần mềm', '3', b'1', b'1', 'Bachelor', '1', '5', '52');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('28','Mobile Programming_Lập trình di động', '3', b'1', b'1', 'Bachelor', '1', '5', '53');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('29','	Software Architecture and Design_Kiến trúc và thiết kế phần mềm', '3', b'1', b'1', 'Bachelor', '1', '5', '54');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('30','Experiential Entrepreneurship 1_Trải nghiệm khởi nghiệp', '3', b'1', b'1', 'Bachelor', '1', '5', '55');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('31','Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2', '3', b'1', b'1', 'Bachelor', '1', '5', '56');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('32','Philosophy of Marxism – Leninism_Triết học Mác - Lê-nin', '3', b'1', b'1', 'Bachelor', '1', '5', '57');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('33','Political economics of Marxism – Leninism_Kinh tế chính trị Mác - Lê-nin', '3', b'1', b'1', 'Bachelor', '1', '5', '58');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('34','HCM Ideology_Tư tưởng Hồ Chí Minh', '3', b'1', b'1', 'Bachelor', '1', '5', '59');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('35','Scientific socialism_Chủ nghĩa xã hội khoa học', '3', b'1', b'1', 'Bachelor', '1', '5', '60');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('36','History of CPV_Lịch sử Đảng Cộng sản Việt Nam', '3', b'1', b'1', 'Bachelor', '1', '5', '61');
INSERT INTO swp391_g3_flm.`syllabus` (syllabus_id, name, no_of_credit, is_active, is_approved, degree_level, decision_id, designer_id, subject_id) VALUES ('37','OOP with Java Lab_Thực hành OOP với Java', '3', b'1', b'1', 'Bachelor', '1', '5', '62');

INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('68', 'SE_COM3', 'Topic on .NET Programming_Chủ đề lập trình .NET BIT_SE_K15A', 'Combo', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('69', 'PRN211', 'Basic Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng cơ bản với .NET', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('70', 'PRN221', 'Advanced Cross-Platform Application Programming With .NET_Lập trình ứng dụng đa nền tảng nâng cao với .NET', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('71', 'PRU211m', 'C# Programming and Unity_Lập trình C# và Unity', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('72', 'PRU221m', 'Object-Oriented Programming for Unity Games_Lập trình hướng đối tượng cho Unity Games', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('73', 'PRN231', 'Building Cross-Platform Back-End Application With .NET_Xây dựng ứng dụng back-end với .NET', 'Default', b'1');

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '68', '5', '3', b'1', '3');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '69', '5', '3', b'1', '3');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '70', '7', '3', b'1', '3');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '71', '7', '3', b'1', '3');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '72', '8', '3', b'1', '3');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '73', '8', '3', b'1', '3');

INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '11', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '12', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '13', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '14', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '15', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '16', '1', '3', b'1', '5');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('74', 'SE_EXE_ELE', 'SE_Entrepreneurship 2 and Combos_ Nhóm môn Khởi nghiệp 2 và Combos', 'Elective', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('75', 'EXE201', 'Experiential Entrepreneurship 2_Trải nghiệm khởi nghiệp 2', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('76', 'DMT301', 'Data Mining with Tensorflow_Khai phá dữ liệu với Tensorflow', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('78', 'AIT301', 'Artificial Intelligence with TensorFlow_TTNT với TensorFlow', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('79', 'NMA301', 'Multiplatform Mobile App Development_Phát triển ứng dụng di động đa nền tảng', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`subject` (`subject_id`, `code`, `name`, `type`, `is_active`) VALUES ('80', 'WDP301', 'Web Development Project_Dự án phát triển web', 'Default', b'1');
INSERT INTO `swp391_g3_flm`.`group` (`group_id`, `name`, `is_active`) VALUES ('20', 'SE_Entrepreneurship 2 and Combos', b'1');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '74', '8', '3', b'1', '20');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '75', '8', '3', b'1', '20');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '76', '8', '3', b'1', '20');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '78', '8', '3', b'1', '20');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '79', '8', '3', b'1', '20');
INSERT INTO `swp391_g3_flm`.`curriculum_subject` (`curriculum_id`, `subject_id`, `semester`, `no_credit`, `is_active`, `type_id`) VALUES ('3', '80', '8', '3', b'1', '20');
