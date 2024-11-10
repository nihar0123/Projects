package main.java.com.passwordmanager;

import main.java.com.passwordmanager.view.MainView;

import javax.swing.*;

public class PasswordManagerApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}
