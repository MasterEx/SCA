// MIT License - Copyright (c) 2009 Periklis Ntanasis

/**
 * \package pntanasis.screenshot
 * This package contains all method needed to capture a screnshot.
 */
package pntanasis.screenshot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author periklis ntanasis 
 * pntanasis [at] gmail [dot] com
 *
 * \class Screenshot
 *
 * \brief Screenshot class contains all basic methods for screen capture.
 *
 *  It contains methods for a single or a multi-screencapture.
 */
public class Screenshot {

    /*
     * Basic private variables.
     */
    private char sep = File.separatorChar; /**< We get a file separator character unique for every os. */
    private String savepath = "screenshots"+sep; /**< This is the variable where the screenshot save path is stored. */
    private String imageprefix = ""; /**< In case of multicapturing we can set an image prefix. */
    private BufferedImage image = null; /**< This is where our image is stored before it's written in a file. This works as any normal buffer. */
    private Toolkit toolkit = Toolkit.getDefaultToolkit(); /**< toolkit give us access to basic java screen methods. */
    private Dimension screenSize = toolkit.getScreenSize(); /**< We are storing our screen size. */
    private Rectangle rectangle = new Rectangle(0, 0, screenSize.width, screenSize.height); /**< This is another variable that contains our main screen size. */
    private long time = 5000; /**< In case of multicapturing we may set the time between every capture in milliseconds. */
    private Robot robot; /**< A robot item captures our current screen. */

    /**
     * Constructor that creates our robot item.
     */
    Screenshot() throws AWTException
    {
        robot = new Robot();        
    }

    /**
     * Constructor that sets specific scrren area. The input is a Dimension Object.
     * Also it calls our empty constructor.
     */
    Screenshot(Dimension x) throws AWTException
    {
        this();
        screenSize = x;
    }

    /**
     * Constructor that stores a specific path where our pictures will be saved.
     * Also it calls our empty constructor.
     */
    Screenshot(String path) throws AWTException
    {
        this();
        savepath = path;
    }

    /**
     * Constructor that compines the role of the other two preivous constructors.
     * It stores a specific path where our pictures will be saved.
     * It sets specific scrren area.
     * Also it calls our empty constructor.
     */
    Screenshot(String path, Dimension x) throws AWTException
    {
        this();
        savepath = path;
        screenSize = x;
    }

    /**
     * If this method is called screenshots are taken periodicaly until the process ends.
     */
    public void multiCapture() throws IOException, InterruptedException
    {
        int count = 0; // Temp variable used for giving names to screenshots.
        while(true){
            Capture(""+(count++));
            Thread.sleep(time);
        }        
    }

    /**
     * As multicapture but the process ends when "counter" screenshots are taken.
     * \see multiCapture()
     */
    public void multiCapture(int counter) throws IOException, InterruptedException
    {
        int count = 0; // Temp variable used for giving names to screenshots.
        while(true){
            Capture(""+(count++));
            if(count == counter) System.exit(1);
            Thread.sleep(time);
        }
    }

    /**
     * As multicapture but the process ends when "millis" milliseconds have passed.
     * \see multiCapture()
     */
    public void multiCapture(long millis) throws IOException, InterruptedException
    {
        long now = java.util.Calendar.getInstance().getTimeInMillis(); // Current time in milliseconds.
        int count = 0; // Temp variable used for giving names to screenshots.
        while(true)
        {
            Capture(""+(count++));
            Thread.sleep(time);
            if(now+millis<java.util.Calendar.getInstance().getTimeInMillis()) break;
            
        }
    }

    /** 
     * \todo Make it work!
     * \brief Test method - shouldn't be used.
     * It should stop when user press a key.
     */
    public void MultiCapture() throws IOException, InterruptedException
    {        
        Scanner in ; //A scanner object for imput from the keyboard.
        int count = 0; //Temp variable used for giving names to screenshots.
        while(true){
            in = new Scanner(System.in);
            Capture(""+(count++));
            if(in.hasNext())
                System.exit(1);
            else
                in.close();
            Thread.sleep(time);
        }
    }    

    /**
     * This method captures a single screenshot with the given name. Multicapture calls that method
     * on a standar basis.
     */
    public void Capture(String imagename) throws IOException
    {        
        image = robot.createScreenCapture(rectangle);
        File file = new File(savepath+imageprefix+imagename+".png");
        ImageIO.write(image, "png", file);
    }

    /**
     * Method that sets the time between every screenshot.
     */
    public void setTime(long millisecs)
    {
        time = millisecs;
    }

    /**
     * Method that sets the pictures prefix is multicapture is on.
     */
    public void setPrefix(String num)
    {
        imageprefix = num;
    }

    /**
     * Method that sets the path where the pictures will be saved.
     */
    public void setPath(String path)
    {
        savepath = path+sep;
    }
    
           
}
