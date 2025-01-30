package BusinessLogic;

import java.util.List;
import DataAcces.DAO.SeccionDAO;
import DataAcces.DTO.SeccionDTO;

public class SeccionBL {

    private SeccionDTO seccion;
    private SeccionDAO seccionDAO = new SeccionDAO();

    public boolean add(SeccionDTO seccionDTO) throws Exception {
        return seccionDAO.create(seccionDTO);
    }
    public List<SeccionDTO> getAll() throws Exception {
        List<SeccionDTO> lst = seccionDAO.readAll();
        return lst;
    }
    public boolean update(SeccionDTO seccionDTO) throws Exception {
        return seccionDAO.update(seccionDTO);
    }
    public boolean delete(int id) throws Exception {
        return seccionDAO.delete(id);
    }
    public SeccionDTO getBy(int id) throws Exception {
        seccion = seccionDAO.readBy(id);
        return seccion;
    }
    public Integer getRowCount() throws Exception {
        return seccionDAO.getMaxRow();
    }

}
