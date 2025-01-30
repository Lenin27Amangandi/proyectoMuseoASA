package DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAcces.SQLiteDataHelper;
import DataAcces.DTO.CategoriaDTO;

public class CategoriaDAO extends SQLiteDataHelper implements IDAOint<CategoriaDTO> {
    @Override
    public boolean create(CategoriaDTO entity) throws Exception {
        String query = "INSERT INTO Categoria (Nombre) VALUES(?)";
        try {
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<CategoriaDTO> readAll() throws Exception {
        // private Integer idCategoria;
        // private String Nombre;
        // private String Estado;
        // private String FechaCrea;
        // private String FechaModifica;

        List <CategoriaDTO> list = new ArrayList<>();
        String query = "SELECT idCategoria "
                    + " ,Nombre          "
                    + " ,Estado          "
                    + " ,FechaCrea       "
                    + " ,FechaModifica   "
                    + " From Categoria   "
                    + " WHERE Estado='A' ";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                CategoriaDTO s = new CategoriaDTO(rs.getInt(1)
                                                , rs.getString(2)
                                                , rs.getString(3)
                                                , rs.getString(4)
                                                , rs.getString(5));
                list.add(s);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean update(CategoriaDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Categoria SET Nombre = ? , FechaModifica = ? WHERE idCategoria = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdCategoria());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
        throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Categoria SET Estado = ? WHERE idCategoria = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*)"
                + " FROM    Categoria   "
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
    public CategoriaDTO readBy(Integer id) throws Exception {
        CategoriaDTO s = new CategoriaDTO();
        String query = "SELECT idCategoria "
                + " ,Nombre          "
                + " ,Estado          "
                + " ,FechaCrea       "
                + " ,FechaModifica   "
                + " WHERE Estado='A' "
                + " AND   idCategoria = " + id.toString();

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                s = new CategoriaDTO(rs.getInt(1)
                                    , rs.getString(2)
                                    , rs.getString(3)
                                    , rs.getString(4)
                                    , rs.getString(5));
            }
        } catch (SQLException e) {
            throw e;
        }
        return s;
    }
    
}
