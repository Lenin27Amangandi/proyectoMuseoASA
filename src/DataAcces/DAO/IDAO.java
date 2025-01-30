package DataAcces.DAO;

import java.util.List;

public interface IDAO<T> {
    public boolean create(T entity) throws Exception;

    public List<T> readAll() throws Exception;

    public boolean update(T entity) throws Exception;

    public boolean delete(String barcode) throws Exception;

    public T readBy(String barcode) throws Exception;

    public Integer getMaxRow() throws Exception;
}
