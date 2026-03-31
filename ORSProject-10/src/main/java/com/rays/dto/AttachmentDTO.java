package com.rays.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code ST_ATTACHMENT}. Stores file metadata (name, type,
 * description) and binary content for user profile pictures or other uploaded
 * documents. No unique-key constraint is enforced.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "ST_ATTACHMENT")
public class AttachmentDTO extends BaseDTO {

	@Column(name = "NAME", length = 100)
	protected String name = null;

	@Column(name = "TYPE", length = 100)
	protected String type = null;

	@Column(name = "DESCRIPTION", length = 500)
	protected String description = null;

	/** Foreign key referencing the user this attachment belongs to. */
	@Column(name = "USER_ID")
	protected Long userId = null;

	/** Binary content of the uploaded file, stored as a LOB. */
	@Lob
	@Column(name = "DOC")
	private byte[] doc;

	/** Default no-arg constructor. */
	public AttachmentDTO() {
	}

	/**
	 * Convenience constructor that populates name, type, and binary content from a
	 * {@link MultipartFile}.
	 *
	 * @param file the uploaded multipart file
	 */
	public AttachmentDTO(MultipartFile file) {
		name = file.getOriginalFilename();
		type = file.getContentType();

		try {
			doc = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** @return {@code null} — attachments have no display value for dropdowns. */
	public String getValue() {
		return null;
	}

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	/** @return {@code null} — no unique key enforced for attachments. */
	@Override
	public String getUniqueKey() {
		return null;
	}

	/** @return {@code null} */
	@Override
	public String getUniqueValue() {
		return null;
	}

	/** @return {@code null} */
	@Override
	public String getLabel() {
		return null;
	}

	/** @return {@code null} */
	@Override
	public String getTableName() {
		return null;
	}
}