package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class Styles {

    public static final Color COLOR_FONT = new Color(0x292929);
    public static final Color COLOR_FONT_LIGHT = new Color(0xd1d1d1);
    public static final Color COLOR_FONT_BG = new Color(0xd9d9d9);
    public static final Color COLOR_CURSOR = Color.black;
    public static final Color COLOR_BORDER_BUTTON= new Color(0x292929);

    // public static final Color COLOR_BACKGROUND= new Color(0x014f01); // Este a cambiar
    public static final Color COLOR_BACKGROUND= new Color(0x4754a1); // Algo Morado Azul
    public static final Color COLOR_BACKGROUND2= new Color(0xF38FF4); // Algo Morado Azul
    // public static final Color COLOR_FOREGROUND = new Color(0xf7c207); // Este a cambiar
    // public static final Color COLOR_FOREGROUND = new Color(0x996d74); // Algo Rosa
    public static final Color COLOR_FOREGROUND = new Color(0xaa9beb); // Algo Rosa
    public static final Color COLOR_FOREGROUND_PRESSED = new Color(0xBD9402);


    public static final Font FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Inconsolata", Font.BOLD | Font.PLAIN, 14);
    public static final Font FONT_BOLD_20 = new Font("Arial", Font.BOLD| Font.PLAIN, 24);
    public static final Font FONT_SMALL = new Font("Inconsolata", Font.PLAIN | Font.PLAIN, 10);

    public static final int ALIGNMENT_LEFT = SwingConstants.LEFT;
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT;
    public static final int ALIGNMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    public static final URL URL_FONDO = Styles.class.getResource("/GUI/Resource/Imagenes/Fondo1.jpg");
    // public static final URL URL_FONDO = Styles.class.getResource("/GUI/Resource/Imagenes/InicioImage.jpg");
    public static final URL URL_FONDO_GENERAL= Styles.class.getResource("/GUI/Resource/Imagenes/InicioAdmin.png");
    public static final URL URL_SPLASH = Styles.class.getResource("/UserInterface/Resources/Img/LogoEA.jpg");


    public static final URL URL_BOTON = Styles.class.getResource("/UserInterface/Resources/Img/Button.png");
    public static final URL URL_MAIN = Styles.class.getResource("/UserInterface/Resources/Img/EATypo.png");
    public static final URL URL_LOGO = Styles.class.getResource("/GUI/Resource/Imagenes/logoSplash.jpg");//Listo



    public static final CompoundBorder createBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray),
                new EmptyBorder(5, 5, 5, 5));
    }

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "🐶 PRODUCTOS", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "🦁 PRODUCTOS", JOptionPane.OK_CANCEL_OPTION);
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "🤡 PRODUCTOS",
                JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION);
    }
}
