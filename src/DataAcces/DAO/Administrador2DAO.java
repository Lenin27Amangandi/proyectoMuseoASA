package DataAcces.DAO;

import DataAcces.SQLiteDataHelper;
import DataAcces.DTO.Administrador2DTO;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Administrador2DAO extends SQLiteDataHelper implements IDAO<Administrador2DTO> {

    public Integer readTipo(String barcode) throws Exception {
        Administrador2DTO administrador2DTO = null;
        String query = "SELECT Tipo FROM Administrador2 WHERE Estado = 'A' AND Codigo = " + barcode;
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                administrador2DTO = new Administrador2DTO();
                administrador2DTO.setTipo(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw e;
        }
        return administrador2DTO != null ? administrador2DTO.getTipo() : null;
    }

    public Integer readByUsuarioYContrasenia(String usuario, String contrasenia) throws Exception {
        Administrador2DTO administrador2DTO = null;
        String query = "SELECT Tipo FROM Administrador2 WHERE Estado = 'A' AND Usuario = ? AND Contrasenia = ?";
        try {
            // Establecer la conexión
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usuario);  // Se reemplaza ? con el valor de usuario
            pstmt.setString(2, contrasenia);  // Se reemplaza ? con el valor de contrasenia
            ResultSet rs = pstmt.executeQuery();
            
            // Si se encuentra el usuario y la contraseña, obtener el tipo
            if (rs.next()) {
                administrador2DTO = new Administrador2DTO();
                administrador2DTO.setTipo(rs.getInt("Tipo"));
            }
        } catch (SQLException e) {
            throw e;
        }
        
        // Si se encuentra el DTO, devolver el tipo. Si no, devolver null.
        return administrador2DTO != null ? administrador2DTO.getTipo() : null;
    }

    @Override
    public boolean create(Administrador2DTO entity) throws Exception {
        String query = " INSERT INTO Administrador2 (Codigo, Usuario, Contrasenia, Tipo) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getCodigo());
            pstmt.setString(2, entity.getUsuario());
            pstmt.setString(3, entity.getContrasenia());
            pstmt.setInt(4, entity.getTipo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<Administrador2DTO> readAll() throws Exception {
        List<Administrador2DTO> list = new ArrayList<>();
        String query = "SELECT idAdministrador2, Codigo, Usuario, Contrasenia, Tipo, Estado, FechaCrea, FechaModifica " +
                       "FROM Administrador2 WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Administrador2DTO admin2DTO = new Administrador2DTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                list.add(admin2DTO);
            }
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

    @Override
    public boolean update(Administrador2DTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Administrador2 SET Tipo = ?, FechaModifica = ? WHERE Codigo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getTipo());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setString(3, entity.getCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(String barcode) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String query = "UPDATE Administrador2 SET Estado = ?, FechaModifica = ? WHERE Codigo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setString(2, dtf.format(now));
            pstmt.setString(3, barcode);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Administrador2DTO readBy(String barcode) throws Exception {
        Administrador2DTO administrador2DTO = null;
        String query = "SELECT idAdministrador2, Codigo, Usuario, Contrasenia, Tipo, Estado, FechaCrea, FechaModifica " +
                       "FROM Administrador2 WHERE Estado = 'A' AND Codigo = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                administrador2DTO = new Administrador2DTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                );
            }
        } catch (SQLException e) {
            throw e;
        }
        return administrador2DTO;
    }

    @Override
    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM Administrador2 WHERE Estado = 'A'";
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
