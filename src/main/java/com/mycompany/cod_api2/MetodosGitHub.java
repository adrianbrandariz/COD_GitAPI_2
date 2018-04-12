package com.mycompany.cod_api2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 *
 * @author abrandarizdominguez
 */
public class MetodosGitHub {

    /**
     * Método que permite crear un repositorio de forma remota.
     *
     * @param nombre Nombre que recibirá el nuevo repositorio.
     */
    public void createRepository(String nombre) {
        try {
            GitHub github = GitHub.connect();
            GHCreateRepositoryBuilder builder;
            builder = github.createRepository(nombre);
            builder.create();
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        }
    }

    /**
     * Método que permite clonar un repositorio.
     *
     * @param uri Dirección url del repositorio remoto que se quiere clonar.
     * @param repositoryName Nombre que se le dará al nuevo repositorio.
     */
    public void cloneRepository(String uri, String repositoryName) {
        try {
            Git.cloneRepository()
                    .setURI(uri)
                    .setDirectory(new File("C:\\Users\\Hansen\\Documents\\NetBeansProjects\\" + repositoryName))
                    .call();
        } catch (GitAPIException ex) {
            Logger.getLogger(MetodosGitHub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método con el que se puede realizar commits en los repositorios.
     *
     * @param localPath Ruta del directorio local en la carpeta
     * NetBeansProyects.
     * @param commitMessage Mensaje que aparece en el commit.
     */
    public void commit(String localPath, String commitMessage) {
        try {
            FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
            Repository repository = repositoryBuilder.setGitDir(new File(localPath + "\\.git"))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git = new Git(repository);
            AddCommand add = git.add();
            add.addFilepattern(localPath).call();
            CommitCommand commit = git.commit();
            commit.setMessage(commitMessage).call();
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        } catch (GitAPIException ex) {
            System.out.println("Error:" + ex);
        }
    }

    /**
     * Metodo que permite realizar push de los commits realizados.
     *
     * @param uri Dirección url del directorio remoto sobre el cual se quiere
     * hacer el push.
     * @param repositoryName Ruta local del repositorio en la carpeta
     * NetBeansProyects.
     * @param user Credencial del usuario de GitHub.
     * @param password Credencial de la contraseña del usuario.
     */
    public void push(String uri, String repositoryName, String user, String password) {
        try {
            FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
            Repository repository = repositoryBuilder.setGitDir(new File(repositoryName + "\\.git"))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            Git git = new Git(repository);
            RemoteAddCommand remoteAddCommand = git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(uri));
            remoteAddCommand.call();
            PushCommand pushCommand = git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(user, password));
            pushCommand.call();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (URISyntaxException ex2) {
            System.out.println("Error: " + ex2);
        } catch (GitAPIException ex3) {
            System.out.println("Error: " + ex3);
        }
    }
}
