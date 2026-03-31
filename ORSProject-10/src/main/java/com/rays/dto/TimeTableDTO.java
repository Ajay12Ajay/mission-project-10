package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code st_timetable}. Stores exam schedule details.
 * Course and subject names are denormalized by the DAO layer. Unique key:
 * {@code courseName}.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "st_timetable")
public class TimeTableDTO extends BaseDTO {

	/** Foreign key referencing the associated course. */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/** Denormalized course name populated by the DAO layer. */
	@Column(name = "course_name", length = 50)
	private String courseName;

	/** Foreign key referencing the associated subject. */
	@Column(name = "subject_id", length = 50)
	private long subjectId;

	/** Denormalized subject name populated by the DAO layer. */
	@Column(name = "subject_name", length = 50)
	private String subjectName;

	@Column(name = "exam_date")
	private Date examDate;

	@Column(name = "exam_time", length = 50)
	private String examTime;

	@Column(name = "semester", length = 30)
	private String semester;

	@Column(name = "description", length = 50)
	private String description;

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

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** @return {@code null} — timetable has no dropdown display value. */
	@Override
	public String getValue() {
		return null;
	}

	/** @return {@code "courseName"} */
	@Override
	public String getUniqueKey() {
		return "courseName";
	}

	/** @return {@code courseName} */
	@Override
	public String getUniqueValue() {
		return courseName;
	}

	/** @return {@code null} */
	@Override
	public String getLabel() {
		return null;
	}

	/** @return {@code "TimeTable"} */
	@Override
	public String getTableName() {
		return "TimeTable";
	}

}