package com.rays.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Abstract base implementation of {@link BaseDAOInt} providing common JPA-based
 * data access operations for all DAO classes in the application.
 * <p>
 * Concrete DAO implementations must extend this class and provide the specific
 * DTO class type and the WHERE clause predicates for custom search queries.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO} that this DAO manages
 *
 * @author Ajay Pratap Kerketta
 */
public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	/**
	 * The JPA {@link EntityManager} used for all database interactions.
	 * Injected by the persistence context.
	 */
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Returns the {@link Class} object of the DTO type managed by this DAO.
	 * <p>
	 * Must be implemented by each concrete subclass to enable generic JPA queries.
	 * </p>
	 *
	 * @return the {@link Class} of the DTO type {@code T}
	 */
	public abstract Class<T> getDTOClass();

	/**
	 * Builds the list of JPA {@link Predicate} objects representing the WHERE clause
	 * for search queries specific to the concrete DTO type.
	 *
	 * @param dto     the DTO instance containing filter criteria values
	 * @param builder the {@link CriteriaBuilder} used to construct predicates
	 * @param qRoot   the {@link Root} of the criteria query representing the entity
	 * @return a {@link List} of {@link Predicate} objects to be applied as the WHERE clause
	 */
	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	/**
	 * Hook method to populate additional fields on the DTO before persisting or merging.
	 * <p>
	 * Default implementation is empty. Subclasses may override this to set
	 * entity-specific derived or computed fields.
	 * </p>
	 *
	 * @param dto         the DTO to be populated
	 * @param userContext the current user's context
	 */
	protected void populate(T dto, UserContext userContext) {

	}

	/**
	 * Persists a new DTO entity to the database.
	 * <p>
	 * Sets the {@code createdBy}, {@code createdDatetime}, {@code modifiedBy}, and
	 * {@code modifiedDatetime} audit fields before persisting. Also calls {@link #populate}
	 * for any subclass-specific field population.
	 * </p>
	 *
	 * @param dto         the DTO entity to be added; must not already exist in the database
	 * @param userContext the current user's context used to set audit fields
	 * @return the generated primary key ({@code id}) of the newly persisted entity
	 */
	@Override
	public long add(T dto, UserContext userContext) {

		dto.setCreatedBy(userContext.getLoginId());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.persist(dto);
		return dto.getId();
	}

	/**
	 * Merges (updates) an existing DTO entity in the database.
	 * <p>
	 * Updates the {@code modifiedBy} and {@code modifiedDatetime} audit fields
	 * before merging. Also calls {@link #populate} for subclass-specific field population.
	 * </p>
	 *
	 * @param dto         the DTO entity containing updated values; must have a valid ID
	 * @param userContext the current user's context used to set audit fields
	 */
	@Override
	public void update(T dto, UserContext userContext) {

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.merge(dto);
	}

	/**
	 * Removes a DTO entity from the database.
	 *
	 * @param dto         the managed DTO entity to be deleted
	 * @param userContext the current user's context (not directly used in deletion but passed for consistency)
	 */
	@Override
	public void delete(T dto, UserContext userContext) {

		entityManager.remove(dto);

	}

	/**
	 * Finds and returns a single entity by its primary key.
	 *
	 * @param id          the primary key of the entity to look up
	 * @param userContext the current user's context
	 * @return the DTO entity matching the given primary key, or {@code null} if not found
	 */
	@Override
	public T findByPk(long id, UserContext userContext) {
		T dto = entityManager.find(getDTOClass(), id);
		return dto;
	}

	/**
	 * Finds and returns a single entity matching a specific attribute-value pair (unique key lookup).
	 * <p>
	 * Uses JPA Criteria API to construct a query with an equality condition on the given attribute.
	 * Returns the first result if multiple matches exist.
	 * </p>
	 *
	 * @param attribute   the entity field name to match against (e.g., {@code "email"})
	 * @param value       the value the attribute must equal
	 * @param userContext the current user's context
	 * @return the first matching DTO entity, or {@code null} if no match is found
	 */
	@Override
	public T findByUniqueKey(String attribute, Object value, UserContext userContext) {

		Class<T> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(dtoClass);

		Root<T> qRoot = cq.from(dtoClass);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<T> query = entityManager.createQuery(cq);

		List<T> list = query.getResultList();

		T dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}

		return dto;
	}

	/**
	 * Builds and returns a {@link TypedQuery} using the criteria defined in
	 * {@link #getWhereClause(BaseDTO, CriteriaBuilder, Root)}.
	 * <p>
	 * This is a shared utility used by both paginated and non-paginated search methods.
	 * </p>
	 *
	 * @param dto         the DTO containing search filter values
	 * @param userContext the current user's context
	 * @return a {@link TypedQuery} constructed from the entity's WHERE clause predicates
	 */
	protected TypedQuery<T> createCriteria(T dto, UserContext userContext) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		Root<T> qRoot = cq.from(getDTOClass());

		cq.select(qRoot);

		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));

		TypedQuery<T> query = entityManager.createQuery(cq);

		return query;

	}

	/**
	 * Searches for entities matching the filter criteria in the given DTO, with pagination.
	 * <p>
	 * If {@code pageSize} is 0 or less, all matching records are returned without pagination.
	 * Otherwise, the result is limited to {@code pageSize} records starting from
	 * {@code pageNo * pageSize}.
	 * </p>
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param pageNo      the zero-based page index
	 * @param pageSize    the maximum number of records to return; if &le; 0, returns all records
	 * @param userContext the current user's context
	 * @return a {@link List} of matching DTO entities for the specified page
	 */
	@Override
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		TypedQuery<T> query = createCriteria(dto, userContext);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		List list = query.getResultList();

		return list;
	}

	/**
	 * Searches for all entities matching the filter criteria in the given DTO, without pagination.
	 * <p>
	 * Delegates to {@link #search(BaseDTO, int, int, UserContext)} with {@code pageNo=0}
	 * and {@code pageSize=0} to retrieve all matching records.
	 * </p>
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param userContext the current user's context
	 * @return a {@link List} of all matching DTO entities
	 */
	@Override
	public List search(T dto, UserContext userContext) {

		return search(dto, 0, 0, userContext);
	}

	/**
	 * Executes a raw HQL query and returns the top 10 results.
	 * <p>
	 * Intended for specialized use cases such as merit list generation.
	 * The result is always limited to the first 10 records.
	 * </p>
	 *
	 * @param hql         the HQL query string to execute
	 * @param userContext the current user's context
	 * @return a {@link List} of up to 10 query result objects
	 */
	public List marksheetMeritList(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(10);
		List l = q.getResultList();
		return l;
	}

	/**
	 * Checks whether a {@link String} value is null or blank.
	 *
	 * @param val the string value to check
	 * @return {@code true} if the value is {@code null} or contains only whitespace; {@code false} otherwise
	 */
	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	/**
	 * Checks whether a {@link Double} value is null or zero.
	 *
	 * @param val the Double value to check
	 * @return {@code true} if the value is {@code null} or equals {@code 0}; {@code false} otherwise
	 */
	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	/**
	 * Checks whether a {@link Long} value is null or zero.
	 *
	 * @param val the Long value to check
	 * @return {@code true} if the value is {@code null} or equals {@code 0}; {@code false} otherwise
	 */
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	/**
	 * Checks whether an {@link Integer} value is null or zero.
	 *
	 * @param val the Integer value to check
	 * @return {@code true} if the value is {@code null} or equals {@code 0}; {@code false} otherwise
	 */
	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	/**
	 * Checks whether an {@link Object} value is not null.
	 *
	 * @param val the object to check
	 * @return {@code true} if the value is not {@code null}; {@code false} otherwise
	 */
	protected boolean isNotNull(Object val) {
		return val != null;
	}
}