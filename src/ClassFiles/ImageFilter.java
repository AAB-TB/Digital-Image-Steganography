/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassFiles;

/**
 *
 * @author Jhamela
 */
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

class ImageFilter extends  FileFilter {
    
    // Accept all directories and all gif, jpg, or tiff files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
	if (extension != null) {
            if (extension.equals(Utils.tiff)||
                extension.equals(Utils.tif) ||
                extension.equals(Utils.gif) ||
                extension.equals(Utils.jpeg)||
				extension.equals(Utils.png) ||
                extension.equals(Utils.jpg)) {
                    return true;
            } 
			else 
			{
                return false;
            }
    	}

        return false;
    }
    
    // The description of this filter
    public String getDescription() 
	{
        return "Image Files";
    }
}
