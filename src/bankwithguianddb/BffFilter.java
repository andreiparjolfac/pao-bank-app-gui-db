/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankwithguianddb;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Andrei
 */
public class BffFilter extends FileFilter {

    public BffFilter() {
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()||f.getName().toLowerCase().endsWith(".bff")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Bank Format File";
    }
    
}
