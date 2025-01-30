package BusinessLogic;

import java.util.List;
import DataAcces.DAO.CategoriaDAO;
import DataAcces.DTO.CategoriaDTO;

public class CategoriaBL {
    private CategoriaDTO categoria;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    public CategoriaBL() {}

    public boolean add(CategoriaDTO categoriaDTO) throws Exception {
        return categoriaDAO.create(categoriaDTO);
    }
    public List<CategoriaDTO> getAll() throws Exception {
        List<CategoriaDTO> lst = categoriaDAO.readAll();
        return lst;
    }
    public boolean update(CategoriaDTO categoriaDTO) throws Exception {
        return categoriaDAO.update(categoriaDTO);
    }
    public boolean delete(int id) throws Exception {
        return categoriaDAO.delete(id);
    }
    public CategoriaDTO getBy(int id) throws Exception {
        categoria = categoriaDAO.readBy(id);
        return categoria;
    }
    public Integer getRowCount() throws Exception {
        return categoriaDAO.getMaxRow();
    }

}
