package BusinessLogic;

import java.util.List;
import DataAcces.DAO.Administrador2DAO;
import DataAcces.DTO.Administrador2DTO;

/**
 * La clase Administrador2BL proporciona la lógica de negocio para gestionar
 * administradores tipo 2.
 * Utiliza Administrador2DAO para interactuar con la base de datos y realizar
 * operaciones CRUD.
 */
public class Administrador2BL {
    private Administrador2DTO administrador2;
    private Administrador2DAO administrador2DAO = new Administrador2DAO();

    /**
     * Determines if the administrator type is of a specific kind based on the
     * barcode.
     * 
     * @param barcode the barcode of the administrator to check
     * @return true if the administrator type is 1, false otherwise
     * @throws Exception if there is an error reading the administrator type from
     *                   the database
     */
    public boolean tipoAdmin2(String barcode) throws Exception {
        int tipo = administrador2DAO.readTipo(barcode);
        return tipo == 1; // Si el tipo es 1, es un administrador y retornamos true
    }

    public boolean tipoAdminByUsuarioYContrasenia(String usuario, String contrasenia) throws Exception {
        // Llamar al método del DAO que devuelve el tipo según usuario y contraseña
        Integer tipo = administrador2DAO.readByUsuarioYContrasenia(usuario, contrasenia);
        // Verificar si el tipo es 1 (administrador), si es así, devolver true
        return tipo != null && tipo == 1;
    }

    /**
     * Agrega un nuevo administrador tipo 2 a la base de datos.
     * 
     * @param administrador2DTO el administrador a agregar
     * @return true si se agregó correctamente, false en caso contrario
     * @throws Exception si ocurre un error al agregar el administrador
     */
    public boolean add(Administrador2DTO administrador2DTO) throws Exception {
        return administrador2DAO.create(administrador2DTO);
    }

    /**
     * Obtiene todos los administradores tipo 2.
     * 
     * @return una lista con todos los administradores tipo 2
     * @throws Exception si ocurre un error al obtener los administradores
     */
    public List<Administrador2DTO> getAll() throws Exception {
        return administrador2DAO.readAll();
    }

    /**
     * Actualiza la información de un administrador tipo 2.
     * 
     * @param administrador2DTO el administrador a actualizar
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws Exception si ocurre un error al actualizar el administrador
     */
    public boolean update(Administrador2DTO administrador2DTO) throws Exception {
        return administrador2DAO.update(administrador2DTO);
    }

    /**
     * Elimina un administrador tipo 2 de la base de datos.
     * 
     * @param barcode el código del administrador a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     * @throws Exception si ocurre un error al eliminar el administrador
     */
    public boolean delete(String barcode) throws Exception {
        return administrador2DAO.delete(barcode);
    }

    /**
     * Obtiene un administrador tipo 2 por su código.
     * 
     * @param barcode el código del administrador
     * @return el administrador encontrado
     * @throws Exception si ocurre un error al obtener el administrador
     */
    public Administrador2DTO getBy(String barcode) throws Exception {
        administrador2 = administrador2DAO.readBy(barcode);
        return administrador2;
    }

    /**
     * Obtiene el número total de administradores tipo 2 registrados.
     * 
     * @return el número total de administradores tipo 2
     * @throws Exception si ocurre un error al obtener el total de registros
     */
    public Integer getRowCount() throws Exception {
        return administrador2DAO.getMaxRow();
    }
}
