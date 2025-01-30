package DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.SQLiteDataHelper;
import DataAcces.DTO.AdministradorTipoDTO;
import DataAcces.DTO.CategoriaDTO;

public class AdministradorTipoDAO extends SQLiteDataHelper implements IDAOint<AdministradorTipoDTO> {

    @Override
    public boolean create(AdministradorTipoDTO entity) throws Exception {
        String query = "INSERT INTO AdministradorTipo (Nombre) VALUES(?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return false;
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*)"
                + " FROM    AdministradorTipo   "
                + " WHERE   Estado ='A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }

    @Override
    public List<AdministradorTipoDTO> readAll() throws Exception {
        // private int idAdministradorTipo;
        // private String Nombre;
        // private String Estado;
        // private String FechaCrea;
        // private String FechaModifica;
        List<AdministradorTipoDTO> list = new ArrayList<>();
        String query = "SELECT idAdministradorTipo "
                + " ,Nombre          "
                + " ,Estado          "
                + " ,FechaCrea       "
                + " ,FechaModifica   "
                + " From AdministradorTipo   "
                + " WHERE Estado='A' ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            AdministradorTipoDTO adminTipo = new AdministradorTipoDTO(rs.getInt(1)
                                        , rs.getString(2)
                                        , rs.getString(3)
                                        , rs.getString(4)
                                        , rs.getString(5));
                list.add(adminTipo);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    @Override
    public AdministradorTipoDTO readBy(Integer id) throws Exception {
        // idAdministradorTipo  INTEGER PRIMARY KEY AUTOINCREMENT
        // ,Nombre              TEXT NOT NULL
    
        // ,Estado              VARCHAR(1) NOT NULL DEFAULT('A')
        // ,FechaCrea           DATETIME DEFAULT(datetime('now','localtime'))
        // ,FechaModifica       DATETIME

        AdministradorTipoDTO adminTipoDTO = new AdministradorTipoDTO();
        String query = "SELECT idAdministradorTipo "
                + " ,Nombre          "
                + " ,Estado          "
                + " ,FechaCrea       "
                + " ,FechaModifica   "
                + " From AdministradorTipo   "
                + " WHERE Estado='A' "
                + " AND   idAdministradorTipo = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                adminTipoDTO = new AdministradorTipoDTO(rs.getInt(1)
                                    , rs.getString(2)
                                    , rs.getString(3)
                                    , rs.getString(4)
                                    , rs.getString(5));
            }
        } catch (SQLException e) {
            throw e;
        }
        return adminTipoDTO;
        }

    @Override
    public boolean update(AdministradorTipoDTO entity) throws Exception {
        return false;
    }

}
