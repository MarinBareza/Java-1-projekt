/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Bareza
 */
public class FileUtils {
        
    private static final String UPLOAD = "Upload";
    
    public static void downloadFromUrl(String source, String destination) throws MalformedURLException, IOException {
        
        createDirectory(destination);
        
        try (InputStream in = new URL(source).openStream()) {
            Files.copy(in, Paths.get(destination));
        }
    }
        
    private static void createDirectory(String destination) throws IOException {
        
        String dir = destination.substring(0, destination.lastIndexOf(File.separator));
        
        if (!Files.exists(Paths.get(dir))) {
            Files.createDirectory(Paths.get(dir));
        }
    }

    public static void copy(String source, String destination) throws FileNotFoundException, IOException {
        createDirectory(destination);
        Files.copy(Paths.get(source), new FileOutputStream(destination));
    }

    public static File uploadFile(String description, String...extensions) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        chooser.setDialogTitle(UPLOAD);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
            return Arrays.asList(extensions).contains(extension.toLowerCase()) ? selectedFile : null;            
        }
        return null;
    }
}
