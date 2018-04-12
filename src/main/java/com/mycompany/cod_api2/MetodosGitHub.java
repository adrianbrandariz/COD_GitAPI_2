package com.mycompany.cod_api2;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 *
 * @author abrandarizdominguez
 */
public class MetodosGitHub {

    private String localPath, remotePath, repositoryName, commitmensaje;
    private Repository localRepo;
    private Git git;
    private CredentialsProvider cp;
    private String name = "username";
    private String password = "password";

    /**
     * Constructor por parámetros.
     *
     * @param localPath Direccion del directorio local.
     * @param remotePath Direccion del directorio remoto.
     */
    public MetodosGitHub(String localPath, String remotePath) {
        try {
            this.localPath = localPath;
            this.remotePath = remotePath;
            this.localRepo = new FileRepository(localPath + "/.git");
            cp = new UsernamePasswordCredentialsProvider(name, password);
            git = new Git(localRepo);
        } catch (IOException ex) {
            Logger.getLogger(MetodosGitHub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor por defecto.
     */
    public MetodosGitHub() {

    }

    /**
     * Método que permite crear un repositorio de forma remota.
     */
    public void createRepository() {
        try {
            repositoryName = JOptionPane.showInputDialog("Introduce nombre del nuevo repositorio");
            GitHub github = GitHub.connect();
            GHCreateRepositoryBuilder builder;
            builder = github.createRepository(repositoryName);
            builder.create();
        } catch (IOException ex) {
            Logger.getLogger(MetodosGitHub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que permite clonar un repositorio.
     *
     * @throws IOException Excepcion que puede ser lanzada.
     * @throws org.eclipse.jgit.api.errors.GitAPIException
     */
    public void cloneRepository() throws IOException, GitAPIException {
        // Se pide por texto el nombre del repositorio y la dirección del remoto.
        repositoryName = JOptionPane.showInputDialog("Introduce nombre del nuevo repositorio");
        remotePath = JOptionPane.showInputDialog("Introduce dirección del repositorio en github");
        try {
            Git.cloneRepository()
                    .setURI(remotePath)
                    .setDirectory(new File(localPath + "/.git"))
                    .setCloneAllBranches(true)
                    .call();
        } catch (GitAPIException ex) {
            Logger.getLogger(MetodosGitHub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
