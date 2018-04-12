package com.mycompany.cod_api2;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;

/**
 *
 * @author abrandarizdominguez
 */
public class Main {

    public static void main(String[] args) throws IOException, JGitInternalException, GitAPIException, URISyntaxException {

        MetodosGitHub met = new MetodosGitHub();
        int op = Integer.parseInt(JOptionPane.showInputDialog("***MENU***\n"
                + "1) Crear Repositorio\n"
                + "2) Clonar Repositorio\n"
                + "3) Inicializar Repositorio\n"
                + "4) Commit\n"
                + "3. Salir"));
        do {
            switch (op) {
                case 1:
                    met.createRepository();
                    break;
                case 2:
                    met.cloneRepository();
                    break;
                case 3:
                    met.iniRepo();
                    break;
                case 4:
                    met.commit();
                    break;
                case 5:
                    met.push();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        } while (op < 6);
    }

}
