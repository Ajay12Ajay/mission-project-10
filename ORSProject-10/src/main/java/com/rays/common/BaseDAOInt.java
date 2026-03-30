package com.rays.common;

import java.util.List;

/**
 * Base DAO interface defining the standard data access contract for all entities.
 * <p>
 * All DAO interfaces in the application should extend this interface and supply
 * the appropriate DTO generic type. Implementations are provided by {@link BaseDAOImpl}.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO} that this DAO manages
 *
 * @author Ajay Pratap Kerketta
 */
public interface BaseDAOInt<T extends BaseDTO> {

	/**
	 * Persists a new DTO entity to the database.
	 *
	 * @param dto         the DTO entity to be added
	 * @param userContext the current user's context used for audit fields
	 * @return the generated primary key ({@code id}) of the newly added entity
	 */
	public long add(T dto, UserContext userContext);

	/**
	 * Updates an existing DTO entity in the database.
	 *
	 * @param dto         the DTO entity with updated values; must have a valid ID
	 * @param userContext the current user's context used for audit fields
	 */
	public void update(T dto, UserContext userContext);

	/**
	 * Deletes a DTO entity from the database.
	 *
	 * @param dto         the managed DTO entity to be removed
	 * @param userContext the current user's context
	 */
	public void delete(T dto, UserContext userContext);

	/**
	 * Finds and returns a single entity by its primary key.
	 *
	 * @param id          the primary key of the entity to retrieve
	 * @param userContext the current user's context
	 * @return the DTO entity matching the given primary key, or {@code null} if not found
	 */
	public T findByPk(long id, UserContext userContext);

	/**
	 * Finds and returns a single entity matching a given attribute-value pair.
	 *
	 * @param attribute   the entity field name to match against (e.g., {@code "email"})
	 * @param value       the value the specified attribute must equal
	 * @param userContext the current user's context
	 * @return the first matching DTO entity, or {@code null} if no match is found
	 */
	public T findByUniqueKey(String attribute, Object value, UserContext userContext);

	/**
	 * Searches for entities matching the filter criteria in the given DTO, with pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param pageNo      the zero-based page index
	 * @param pageSize    the maximum number of records per page; if &le; 0, returns all matching records
	 * @param userContext the current user's context
	 * @return a {@link List} of matching DTO entities for the given page
	 */
	public List search(T dto, int pageNo, int pageSize, UserContext userContext);

	/**
	 * Searches for all entities matching the filter criteria in the given DTO, without pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param userContext the current user's context
	 * @return a {@link List} of all matching DTO entities
	 */
	public List search(T dto, UserContext userContext);

}