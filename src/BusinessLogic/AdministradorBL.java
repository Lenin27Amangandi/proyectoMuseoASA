package BusinessLogic;

import java.util.List;
import DataAcces.DAO.AdministradorDAO;
import DataAcces.DTO.AdministradorDTO;

/**
 * La clase AdministradorBL proporciona la lógica de negocio para gestionar administradores.
 * Utiliza AdministradorDAO para interactuar con la base de datos y realizar operaciones CRUD.
 */
public class AdministradorBL {
    private AdministradorDTO administrador;
    private AdministradorDAO administradorDAO= new AdministradorDAO();

    /**
     * Determines if the administrator type is of a specific kind based on the barcode.
     * @param barcode the barcode of the administrator to check
     * @return true if the administrator type is 1, false otherwise
     * @throws Exception if there is an error reading the administrator type from the database
     */

    public boolean tipoAdmin(String barcode) throws Exception {
        int tipo = administradorDAO.readTipo(barcode);
        if (tipo == 1) //Si es administrador osea tipo 1 retorna true
            return true;
        else
            return false; //Si es osea tipo 2 retorna false
    }

    public boolean add(AdministradorDTO administradorDTO) throws Exception {
        return administradorDAO.create(administradorDTO);
    }
    public List<AdministradorDTO> getAll() throws Exception {
        List<AdministradorDTO> lst = administradorDAO.readAll();
        return lst;
    }
    public boolean update(AdministradorDTO administradorDTO) throws Exception {
        return administradorDAO.update(administradorDTO);
    }
    public boolean delete(String barcode) throws Exception {
        return administradorDAO.delete(barcode);
    }
    public AdministradorDTO getBy(String barcode) throws Exception {
        administrador = administradorDAO.readBy(barcode);
        return administrador;
    }
    public Integer getRowCount() throws Exception {
        return administradorDAO.getMaxRow();
    }

}
