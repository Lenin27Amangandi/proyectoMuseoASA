package GUI.Form;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import GUI.Form.PanelesGUI.MenuPanel;

public class MainForm extends JFrame {

    MenuPanel pnlMenu;
    
    public MainForm(String titleApp, boolean registroSuperAdmin) {
        pnlMenu = new MenuPanel();
        if (registroSuperAdmin) {
            customizeComponent(titleApp);
        }
    }

    private void customizeComponent(String titleApp) {
        setTitle(titleApp);
        setSize(720, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(pnlMenu, BorderLayout.CENTER);
        setVisible(true);
    }

}
