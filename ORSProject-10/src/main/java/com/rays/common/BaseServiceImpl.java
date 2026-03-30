package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract base service implementation providing common business logic
 * for add, update, save, delete, find, and search operations.
 * <p>
 * All concrete service implementations should extend this class and supply
 * the appropriate DTO and DAO generic type parameters. The DAO is wired
 * automatically by Spring.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 * @param <D> the DAO type implementing {@link BaseDAOInt} for the given DTO
 *
 * @author Ajay Pratap Kerketta
 */
public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	/** The DAO layer instance auto-wired by Spring for data access operations. */
	@Autowired
	protected D dao;

	/**
	 * Persists a new DTO entity by delegating to the DAO's add method.
	 *
	 * @param dto         the DTO entity to add; must not already exist in the database
	 * @param userContext the current user's context used for setting audit fields
	 * @return the generated primary key ({@code id}) of the newly added entity
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto, UserContext userContext) {
		long id = dao.add(dto, userContext);
		return id;
	}

	/**
	 * Updates an existing DTO entity in the database.
	 * <p>
	 * Before updating, retrieves the existing record to preserve the original
	 * {@code createdBy} and {@code createdDatetime} audit fields.
	 * </p>
	 *
	 * @param dto         the DTO entity with updated values; must have a valid existing ID
	 * @param userContext the current user's context used for setting audit fields
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) {

		T oldDto = dao.findByPk(dto.getId(), userContext);

		if (oldDto != null) {
			dto.setCreatedBy(oldDto.getCreatedBy());
			dto.setCreatedDatetime(oldDto.getCreatedDatetime());
		}

		dao.update(dto, userContext);

	}

	/**
	 * Saves a DTO entity by determining whether to add or update based on the presence of an ID.
	 * <p>
	 * If the DTO has a non-null ID greater than 0, it delegates to {@link #update}.
	 * Otherwise, it delegates to {@link #add}.
	 * </p>
	 *
	 * @param dto         the DTO entity to save
	 * @param userContext the current user's context
	 * @return the primary key of the saved entity — the existing ID for updates,
	 *         or the newly generated ID for additions
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext) {
		Long id = dto.getId();

		if (id != null && id > 0) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	/**
	 * Deletes an entity by its primary key.
	 * <p>
	 * First retrieves the entity by ID. If it does not exist, throws a
	 * {@link RuntimeException} with the message "Record not found".
	 * Otherwise, delegates to the DAO to perform the deletion.
	 * </p>
	 *
	 * @param id          the primary key of the entity to delete
	 * @param userContext the current user's context
	 * @return the deleted DTO entity
	 * @throws RuntimeException if no entity with the given ID exists
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id, UserContext userContext) {
		T dto = findById(id, userContext);

		if (dto == null) {
			throw new RuntimeException("Record not found");
		}

		dao.delete(dto, userContext);
		return dto;
	}

	/**
	 * Finds and returns a single entity by its primary key.
	 *
	 * @param id          the primary key of the entity to retrieve
	 * @param userContext the current user's context
	 * @return the DTO entity matching the given ID, or {@code null} if not found
	 */
	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		T dto = dao.findByPk(id, userContext);
		return dto;
	}

	/**
	 * Finds and returns a single entity matching a given attribute-value pair.
	 *
	 * @param attribute   the entity field name to match against (e.g., {@code "email"})
	 * @param val         the string value the specified attribute must equal
	 * @param userContext the current user's context
	 * @return the first matching DTO entity, or {@code null} if no match is found
	 */
	@Transactional(readOnly = true)
	public T findByUniqueKey(String attribute, String val, UserContext userContext) {

		T dto = dao.findByUniqueKey(attribute, val, userContext);

		return dto;
	}

	/**
	 * Searches for entities matching the filter criteria in the given DTO, with pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param pageNo      the zero-based page index
	 * @param pageSize    the maximum number of records per page
	 * @param userContext the current user's context
	 * @return a {@link List} of matching DTO entities for the specified page
	 */
	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		return dao.search(dto, pageNo, pageSize, userContext);

	}

	/**
	 * Searches for all entities matching the filter criteria in the given DTO, without pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param userContext the current user's context
	 * @return a {@link List} of all matching DTO entities
	 */
	@Transactional(readOnly = true)
	public List search(T dto, UserContext userContext) {

		return dao.search(dto, userContext);
	}

}