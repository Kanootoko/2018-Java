package DAL.DAO;

import java.sql.SQLException;

import DAL.SearchRequest.SearchRequest;
import DAL.DTO.DTO;

public interface DAO<T extends DTO> {
	void update(T oldDTO, T newDTO) throws SQLException;
	void insert(T dto) throws SQLException;
	void delete(T dto) throws SQLException;
	T[] get(SearchRequest<T> sr) throws SQLException;
	T getOne(SearchRequest<T> sr) throws SQLException;
}
