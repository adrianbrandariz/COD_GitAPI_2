package com.mycompany.cod_api2;

import javax.swing.JOptionPane;

/**
 *
 * @author abrandarizdominguez
 */
public class Main {

    public static void main(String[] args) {

        MetodosGitHub met = new MetodosGitHub();

        String repositoryName, uri, commitMessage, user, password;

        int op = Integer.parseInt(JOptionPane.showInputDialog("***MENU***\n"
                + "1) Crear Repositorio\n"
                + "2) Clonar Repositorio\n"
                + "3) Inicializar Repositorio\n"
                + "4) Commit\n"
                + "3. Salir"));
        do {
            switch (op) {
                case 1:
                    repositoryName = JOptionPane.showInputDialog("Nombre del repositorio nuevo:");
                    met.createRepository(repositoryName);
                    break;
                case 2:
                    repositoryName = JOptionPane.showInputDialog("Nombre del repositorio nuevo:");
                    uri = JOptionPane.showInputDialog("URL del repositorio remoto:");
                    met.cloneRepository(uri, repositoryName);
                    break;
                case 3:
                    repositoryName = JOptionPane.showInputDialog("Nombre del repositorio nuevo:");
                    commitMessage = JOptionPane.showInputDialog("Mensaje para el commit:");
                    met.commit(repositoryName, commitMessage);
                    break;
                case 4:
                    repositoryName = JOptionPane.showInputDialog("Inserte la ruta del proyecto:");
                    uri = JOptionPane.showInputDialog("Inserte la url del repositorio remoto");
                    user = JOptionPane.showInputDialog("Inserte usuario:");
                    password = JOptionPane.showInputDialog("Inserte contraseña:");
                    met.push(uri, repositoryName, user, password);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } while (op < 5);
    }

}
