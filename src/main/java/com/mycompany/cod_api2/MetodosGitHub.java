package com.mycompany.cod_api2;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author abrandarizdominguez
 */
public class MetodosGitHub {
    
    public void cloneRepository(){
        try {
            Git.cloneRepository()
                    .setURI("https://github.com/eclipse/jgit.git")
                    .setDirectory(new File("C:\\Users\\Hansen\\Documents\\NetBeansProjects\\COD_PRUEBA_API2"))
                    .call();
        } catch (GitAPIException ex) {
            Logger.getLogger(MetodosGitHub.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: "+ex);
        }
    }
}
