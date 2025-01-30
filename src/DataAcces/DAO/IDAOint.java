package DataAcces.DAO;

import java.util.List;

public interface IDAOint<T> {
    public boolean create(T entity) throws Exception;
    public List<T> readAll() throws Exception;
    public boolean update(T entity) throws Exception;
    public boolean delete(Integer id) throws Exception;
    public T readBy(Integer id) throws Exception;
    public Integer getMaxRow() throws Exception;
}
