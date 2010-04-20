// MIT License - Copyright (c) 2009 Periklis Ntanasis

package screenshot;

import java.awt.Dimension;
import java.awt.Toolkit;
import pntanasis.screenshot.Arguments;
import pntanasis.screenshot.MainFrame;

/**
 * \mainpage Screenshot Capture Application description
 *
 * SCA is a simple screen capture application. It's most
 * powerfull feature is the screen capture command line tool.
 *
 * @author periklis
 */
public class main {
    public static void main(String args[]) {      
        Arguments arg ;
        if(args.length>0)
        {
           arg = new Arguments(args);
        }
        else
        {                            
            MainFrame mf = new MainFrame();
            mf.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - mf.getWidth()) / 2;
            int y = (screenSize.height - mf.getHeight()) / 2;
            mf.setLocation(x, y);
               
        }
    }
}
