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
import DataAcces.DTO.AdministradorDTO;

public class AdministradorDAO extends SQLiteDataHelper implements IDAO<AdministradorDTO> {

    public Integer readTipo(String barcode) throws Exception {
        AdministradorDTO administradorDTO = null;
        String query = "SELECT Tipo FROM Administrador WHERE Estado = 'A' AND Codigo = " + barcode;
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                administradorDTO = new AdministradorDTO();
                administradorDTO.setTipo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw e;
        }
        return administradorDTO != null ? administradorDTO.getTipo() : null;
    }

    @Override
    public boolean create(AdministradorDTO entity) throws Exception {
        String query = " INSERT INTO Administrador (Codigo, Tipo) VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getCodigo());
            pstmt.setInt(2, entity.getTipo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    @Override
    public List<AdministradorDTO> readAll() throws Exception {
        // private Integer idAdministrador;
        // private String Codigo;
        // private Integer Tipo;
        // private String Estado;
        // private String FechaCrea;
        // private String FechaModifica;

        List<AdministradorDTO> list = new ArrayList<>();
        String query = "SELECT idAdministrador "
                        + " ,Codigo          "
                        + " ,Tipo            "
                        + " ,Estado          "
                        + " ,FechaCrea       "
                        + " ,FechaModifica   "
                        + " From Administrador "
                        + " WHERE Estado='A' ";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdministradorDTO adminDTO = new AdministradorDTO(rs.getInt(1)
                                            , rs.getString(2)
                                            , rs.getInt(3)
                                            ,rs.getString(4)
                                            , rs.getString(5)
                                            , rs.getString(6));
            list.add(adminDTO);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }
    @Override
    public boolean update(AdministradorDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Administrador SET Tipo = ?, FechaModifica = ? WHERE Codigo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, entity.getTipo());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    @Override
    public boolean delete(String barcode) throws Exception {
        // UPDATE Administrador
        // SET Estado = 'X', FechaModifica = datetime('now','localtime')
        // WHERE Codigo = '1234567890123';

        String query = "UPDATE Administrador SET Estado = ? WHERE Codigo = ? ";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setString(2, barcode);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }

        // try {
        // Connection conn = openConnection();
        // PreparedStatement pstmt = conn.prepareStatement(query);
        // // pstmt.setString(1, "X")-<<< previously 1;
        // pstmt.setString(1, barcode);
        // pstmt.executeUpdate();
        // return true;
        // } catch (SQLException e) {
        // throw e;
        // }
    }
    @Override
    public AdministradorDTO readBy(String barcode) throws Exception {
        AdministradorDTO administradorDTO = new AdministradorDTO();

        // SELECT idAdministrador
        // , Codigo
        // , Tipo
        // , Estado
        // , FechaCrea
        // , FechaModifica
        // FROM Administrador
        // WHERE Estado='A'
        // AND Codigo = "1753193828123";

        String query = "SELECT idAdministrador "
                + ", Codigo              "
                + ", Tipo                "
                + ", Estado              "
                + ", FechaCrea           "
                + ", FechaModifica       "
                + " FROM Administrador "
                + " WHERE Estado='A' "
                + "AND Codigo = '" + barcode + "'";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                administradorDTO = new AdministradorDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            throw e;
        }
        return administradorDTO;
    }
    @Override
    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*) TotalReg "
                + " FROM    Administrador    "
                + " WHERE   Estado ='A'      ";
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

}
