package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code st_subject}. Course name is denormalized from the
 * course record by the DAO layer. Unique key: {@code name}. Used in dropdown
 * lists with {@code name} as the display value.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "st_subject")
public class SubjectDTO extends BaseDTO {

	/** Foreign key referencing the associated course. */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/** Denormalized course name populated by the DAO layer. */
	@Column(name = "course_name", length = 50)
	private String courseName;

	@Column(name = "name", length = 50)
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** @return {@code name} — used as dropdown display value. */
	@Override
	public String getValue() {
		return name;
	}

	/** @return {@code "name"} */
	@Override
	public String getUniqueKey() {
		return "name";
	}

	/** @return {@code name} */
	@Override
	public String getUniqueValue() {
		return name;
	}

	/** @return {@code "Subject Name"} */
	@Override
	public String getLabel() {
		return "Subject Name";
	}

	/** @return {@code "Subject"} */
	@Override
	public String getTableName() {
		return "Subject";
	}

}