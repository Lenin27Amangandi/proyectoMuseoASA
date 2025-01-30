package BusinessLogic;

import java.text.DecimalFormat;
import java.util.List;

import DataAcces.DAO.PiezaDeArteDAO;
import DataAcces.DTO.PiezaDeArteDTO;

public class PiezaDeArteBL {

    private PiezaDeArteDTO piezaDeArte;
    private PiezaDeArteDAO piezaDeArteDAO = new PiezaDeArteDAO();

    public PiezaDeArteBL() {
    }

    public String getPrecioBy(String barcode) throws Exception {
        Double precio = piezaDeArteDAO.readPrecioBy(barcode);
        precio = precio * 1.15; // suma el 15%
        DecimalFormat df = new DecimalFormat("#.00"); // redondea a dos decimales
        return "$" + df.format(precio); // retorna el precio en tipo string y formato $x.xx
    }

    public String getNombreBy(String barcode) throws Exception {
        String nombre = piezaDeArteDAO.readNombreBy(barcode);
        return nombre;
    }
    public String getAutorBy(String barcode) throws Exception {
        String nombreAutor = piezaDeArteDAO.readAutorBy(barcode);
        return nombreAutor;
    }
    public String getDescripcionBy(String barcode) throws Exception {
        String descripcionPieza = piezaDeArteDAO.readDescripcionBy(barcode);
        return descripcionPieza;
    }
    public boolean add(PiezaDeArteDTO piezaDeArteDTO) throws Exception {
        return piezaDeArteDAO.create(piezaDeArteDTO);
    }
    public List<PiezaDeArteDTO> getAll() throws Exception {
        List<PiezaDeArteDTO> lst = piezaDeArteDAO.readAll();
        return lst;
    }
    public boolean update(PiezaDeArteDTO piezaDeArteDTO) throws Exception {
        return piezaDeArteDAO.update(piezaDeArteDTO);
    }
    public boolean delete(String barcode) throws Exception {
        return piezaDeArteDAO.delete(barcode);
    }
    public PiezaDeArteDTO getBy(String barcode) throws Exception {
        piezaDeArte = piezaDeArteDAO.readBy(barcode);
        return piezaDeArte;
    }
    public Integer getRowCount() throws Exception {
        return piezaDeArteDAO.getMaxRow();
    }

}
