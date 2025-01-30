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
import DataAcces.DTO.SeccionDTO;

public class SeccionDAO extends SQLiteDataHelper implements IDAOint<SeccionDTO> {

    @Override
    public boolean create(SeccionDTO entity) throws Exception {
        String query = "INSERT INTO Seccion (Nombre) VALUES(?)";
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
    public List<SeccionDTO> readAll() throws Exception {
        // private Integer idSeccion;
        // private String Nombre;
        // private String Estado;
        // private String FechaCrea;
        // private String FechaModifica;

        List<SeccionDTO> list = new ArrayList<>();
        String query = "SELECT idSeccion "
                + " ,Nombre          "
                + " ,Estado          "
                + " ,FechaCrea       "
                + " ,FechaModifica   "
                + " From Seccion   "
                + " WHERE Estado='A' ";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SeccionDTO s = new SeccionDTO(rs.getInt(1)
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
    public boolean update(SeccionDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Seccion SET nombre = ? , FechaModifica = ? WHERE idSeccion = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdSeccion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
        throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Seccion SET Estado = ? WHERE idSeccion = ?";
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
                + " FROM    Seccion   "
                + " WHERE   Estado ='A'";
        try {
            Connection  conn = openConnection();
            Statement   stmt = conn.createStatement();
            ResultSet   rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }

    @Override
    public SeccionDTO readBy(Integer id) throws Exception {
        SeccionDTO s = new SeccionDTO();
        String query = "SELECT idSeccion "
                + " ,Nombre          "
                + " ,Estado          "
                + " ,FechaCrea       "
                + " ,FechaModifica   "
                + " From Seccion     "
                + " WHERE Estado='A' "
                + " AND   idSeccion = " + id.toString();
                // + " AND   idSeccion = " + id;

        try {
            Connection  conn = openConnection();
            Statement   stmt = conn.createStatement();
            ResultSet   rs = stmt.executeQuery(query);

            while (rs.next()) {
                s = new SeccionDTO(rs.getInt(1)
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
