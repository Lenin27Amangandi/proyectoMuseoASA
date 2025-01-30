import java.nio.channels.Pipe.SourceChannel;

import BusinessLogic.AdministradorBL;
import BusinessLogic.CategoriaBL;
import BusinessLogic.PiezaDeArteBL;
import BusinessLogic.SeccionBL;
import DataAcces.SQLiteDataHelper;
import DataAcces.DAO.AdministradorDAO;
import DataAcces.DAO.AdministradorTipoDAO;
import DataAcces.DAO.CategoriaDAO;
import DataAcces.DAO.PiezaDeArteDAO;
import DataAcces.DAO.SeccionDAO;
import DataAcces.DTO.AdministradorDTO;
import DataAcces.DTO.AdministradorTipoDTO;
import DataAcces.DTO.CategoriaDTO;
import DataAcces.DTO.PiezaDeArteDTO;
import DataAcces.DTO.SeccionDTO;
import GUI.Form.MainForm;
import GUI.Form.SplashForm;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // new SplashForm();
            new MainForm("Acces Scand Art", true);

            // // Testeo de conexión a la base de datos SQLite para verificar que la
            // conexión es exitosa
            // SQLiteDataHelper.testConnection();

            // // Testeo de Dao Categoria
            // System.out.println();
            // System.out.println("- ".repeat(25));
            // CategoriaDAO dao = new CategoriaDAO();

            // Testeo de create Categoria desde el dao
            // // dao.create(new CategoriaDTO("Cine")); // Funciona

            // CategoriaDTO categoria = dao.readBy(4);

            // for (CategoriaDTO cate : dao.readAll()) {
            // System.out.println(cate.toString());
            // }
            // // Testeo para el getMaxRow Categoria
            // System.out.println("- ".repeat(25));
            // Integer maxRow1 = dao.getMaxRow();
            // System.out.println("Número máximo de filas: " + maxRow1);

            // // Testeo de Dao AdministradorTipo
            // System.out.println("- ".repeat(25));
            // AdministradorTipoDAO dao2 = new AdministradorTipoDAO();
            // for (AdministradorTipoDTO adminTip : dao2.readAll()) {
            // System.out.println(adminTip.toString());
            // }
            // // //Testeo de create AdministradorTipo desde el dao exitoso
            // System.out.println(dao2.readBy(1));
            // System.out.println(dao2.readBy(2));
            // //Testeo para el getMaxRow AdministradorTipo
            // System.out.println("- ".repeat(25));
            // Integer maxRow2 = dao2.getMaxRow();
            // System.out.println("Número máximo de filas: " + maxRow2);

            // //Testeo de Dao Seccion
            // System.out.println();
            // System.out.println("- ".repeat(25));
            // SeccionDAO dao3= new SeccionDAO();
            // //Testeo de create Seccion desde el dao exitoso
            // //dao3.create(new SeccionDTO("Moderno"));
            // for (SeccionDTO seccion : dao3.readAll()) {
            // System.out.println(seccion.toString());
            // }
            // // testeo para el readBy de Seccion Exitoso
            // System.out.println(dao3.readBy(3));

            // // Testeo para el getMaxRow AdministradorTipo
            // System.out.println("- ".repeat(25));
            // Integer maxRow3 = dao3.getMaxRow();
            // System.out.println("Número máximo de filas: " + maxRow3);

            // Testeo de Dao PiezaDeArte
            // System.out.println();
            // System.out.println("- ".repeat(25));
            // PiezaDeArteDAO dao4 = new PiezaDeArteDAO();
            // System.out.println(dao4.readAutorBy("0234567890125"));
            // //Testeo de create PiezaDeArte desde el dao
            // // ,('0234567890127' , 'El Grito' , 'Edvard Munch' , 'Pintura al oleo',
            // 10.90, 1, 3)
            // // dao4.create(new PiezaDeArteDTO("0234567890127", "ElGrito", "Edvard Munch",
            // "Pintura que replresenta el estado mental", 10.90, 1, 3));// Funciona
            // //Testeo de readAll PiezaDeArte
            // for (PiezaDeArteDTO pieza : dao4.readAll()) {
            // System.out.println(pieza.toString());
            // }
            // Testeo para el read By de Pieza Exitoso
            // // System.out.println(dao4.readBy("0234567890126")); Funciona el read by
            // // Testeo para el getMaxRow PiezaDeArte
            // System.out.println("- ".repeat(25));
            // Integer maxRow4 = dao4.getMaxRow();
            // System.out.println("Número máximo de filas: " + maxRow4);

            // //Testeo de Dao Administrador
            // System.out.println();
            // System.out.println("- ".repeat(25));
            // AdministradorDAO dao5 = new AdministradorDAO();
            // // //Testeo de create Administrador desde el dao
            // // dao5.create(new AdministradorDTO("1753193828123", 1)); Funciona
            // for (AdministradorDTO admin : dao5.readAll()) {
            // System.out.println(admin.toString());
            // }
            // dao5.delete("1234567890123");
            // Testeo para el read by Exitoso
            // System.out.println("- ".repeat(25));
            // System.out.println(dao5.readBy("1753193828123"));

            // // Testeo para el getMaxRow AdministradorTipo
            // System.out.println("- ".repeat(25));
            // Integer maxRow5 = dao5.getMaxRow();
            // System.out.println("Número máximo de filas: " + maxRow5);

            // // Testing BL Categoria
            // CategoriaBL categoriaBL = new CategoriaBL();
            // System.out.println("- ".repeat(25));
            // for (CategoriaDTO categoriDTO : categoriaBL.getAll()) {
            // System.out.println(categoriDTO.toString());
            // }

            // // Testing BL Seccion
            // SeccionBL seccionBL = new SeccionBL();
            // System.out.println("- ".repeat(25));
            // for (SeccionDTO seccionDTO : seccionBL.getAll()) {
            // System.out.println(seccionDTO.toString());
            // }

            // Testing Pieza de arteBL
            // PiezaDeArteBL piezaBL= new PiezaDeArteBL();
            // piezaBL.getNombreBy("0234567890124");
            // System.out.println(piezaBL.getNombreBy("0234567890124"));
            // System.out.println(piezaBL.getPrecioBy("0234567890124"));
            // System.out.println(piezaBL.getAutorBy("0234567890124"));

            // Testing BL Admin
            // AdministradorBL adminBL = new AdministradorBL();
            // System.out.println("- ".repeat(25));
            // // // adminBL.add(new AdministradorDTO("1234657891212", 2));
            // System.out.println(adminBL.getAll());

            //
        } catch (Exception e) {
            throw e;
        }
    }
}
