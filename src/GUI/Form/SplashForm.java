package GUI.Form;





import java.awt.Color;
import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import GUI.Styles;

public class SplashForm extends JFrame {

    public SplashForm() {

        setUndecorated(true);
        setSize(240, 240);
        JProgressBar loadingSplash = new JProgressBar(0, 100);
        loadingSplash.setStringPainted(true);
        loadingSplash.setBackground(Styles.COLOR_BACKGROUND);
        loadingSplash.setOpaque(true);
        loadingSplash.setBorderPainted(false);
        loadingSplash.setForeground(Styles.COLOR_FOREGROUND);
        loadingSplash.setFont(Styles.FONT_SMALL);
        loadingSplash.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionBackground() {
                return Styles.COLOR_FOREGROUND; // Establece el color de la barra de progreso
            }
        });

        ImageIcon icon = new ImageIcon(Styles.URL_LOGO);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(scaledIcon);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);
        add(logo, BorderLayout.CENTER);
        add(loadingSplash, BorderLayout.SOUTH);
        setLocationRelativeTo(null);

        setVisible(true);
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(50); // Espera por 50 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadingSplash.setValue(i);
        }
        setVisible(false);

    }

}
