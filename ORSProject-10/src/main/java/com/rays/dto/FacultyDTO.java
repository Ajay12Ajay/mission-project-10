package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code st_faculty}. Stores faculty personal details
 * along with denormalized college, course, and subject names. Unique key:
 * {@code email}.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "st_faculty")
public class FacultyDTO extends BaseDTO {

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "phone_no", length = 15)
	private String phoneNo;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "qualification", length = 15)
	private String qualification;

	/** Foreign key to the associated college. */
	@Column(name = "college_id", length = 50)
	private long collegeId;

	/** Denormalized college name populated by the DAO layer. */
	@Column(name = "college_name", length = 50)
	private String collegeName;

	/** Foreign key to the associated course. */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/** Denormalized course name populated by the DAO layer. */
	@Column(name = "course_name", length = 50)
	private String courseName;

	/** Foreign key to the associated subject. */
	@Column(name = "subject_id", length = 50)
	private long subjectId;

	/** Denormalized subject name populated by the DAO layer. */
	@Column(name = "subject_name", length = 50)
	private String subjectName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/** @return {@code null} — faculty has no dropdown display value. */
	@Override
	public String getValue() {
		return null;
	}

	/** @return {@code "email"} */
	@Override
	public String getUniqueKey() {
		return "email";
	}

	/** @return {@code email} */
	@Override
	public String getUniqueValue() {
		return email;
	}

	/** @return {@code "Email"} */
	@Override
	public String getLabel() {
		return "Email";
	}

	/** @return {@code "Faculty"} */
	@Override
	public String getTableName() {
		return "Faculty";
	}

}