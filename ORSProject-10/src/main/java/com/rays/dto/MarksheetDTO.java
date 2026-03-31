package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code st_marksheet}. Stores a student's exam marks for
 * physics, chemistry, and maths. Unique key: {@code rollNo}. Student name is
 * denormalized from the student record.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "st_marksheet")
public class MarksheetDTO extends BaseDTO {

	@Column(name = "roll_no", length = 20)
	protected String rollNo = null;

	/** Foreign key referencing the associated student. */
	@Column(name = "student_id")
	protected Long studentId;

	/** Denormalized student full name populated by the DAO layer. */
	@Column(name = "name", length = 50)
	protected String name = null;

	@Column(name = "physics")
	protected Integer physics;

	@Column(name = "chemistry")
	protected Integer chemistry;

	@Column(name = "maths")
	protected Integer maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	/** @return {@code null} — marksheet has no dropdown display value. */
	@Override
	public String getValue() {
		return null;
	}

	/** @return {@code "rollNo"} */
	@Override
	public String getUniqueKey() {
		return "rollNo";
	}

	/** @return {@code rollNo} */
	@Override
	public String getUniqueValue() {
		return rollNo;
	}

	/** @return {@code "Roll No"} */
	@Override
	public String getLabel() {
		return "Roll No";
	}

	/** @return {@code "Marksheet"} */
	@Override
	public String getTableName() {
		return "Marksheet";
	}

}