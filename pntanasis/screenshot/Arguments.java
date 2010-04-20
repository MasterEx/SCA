// MIT License - Copyright (c) 2009 Periklis Ntanasis

package pntanasis.screenshot;
/*
 * /package pntanasis.screenshot
 * Package pntanasis.screenshot contains the main methods needed to capture a
 * screenshot.
 */

import java.awt.AWTException;
import java.io.IOException;
import pntanasis.exceptions.*;

/**
 *
 * @author periklis
 *
 * \exception WrongNumberOfArgumentsException
 * \exception WrongSyntaxException
 *
 * \brief This class receives the program arguments.
 * 
 * This class is only needed if the progam is used from the command line.
 * We pass the arguments array to that
 * class and the expected actions take place.
 */
public class Arguments {  

    /**
     * This is Arguments class' constructor. The constructor receives the arguments
     * array and executes them. The arguments order is not important.
     */
    public Arguments(String args[])
    {
        /**
         * If the arguments number is less than 2 then a WrongNumberOfArgumentsEsception occurs. Also
         * arguments number should be even.
         */
         try {
            if(!(args.length%2==0))
                if(!(args[0].equals("-h") || args[0].equals("--help")))
                    throw new pntanasis.exceptions.WrongNumberOfArgumentsException("The number of arguments should be even");
        } catch (WrongNumberOfArgumentsException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }

         /*
          * Friendly variables.
          */
         String autostop = "";  /** autostop keeps the input time value that indicates when the application will stop. */
         String name = "";  /** name is the name of the screenshot. The screenshot will be saved in .png format. */
         Screenshot screenObject = null; /** scrrenObject is an Object of our wrapper class Screenshot. */
         Boolean multicapture = false; /** multicaprure is false by default, if true then screenshots will be taken until proccess end. */
         int counter = -5; /** If multicapture is on then we may set it stop after a specific amount of screenshots */
         boolean incounter = false; /** When counter option is selected incounter is true. */

         /*
          * If the syntax of the arguments is wrong then a WrongSyntaxException is caused.
          */
        try {
            screenObject = new Screenshot();
            for(int i=0;i<args.length;i++)
         {
             if(args[i].equals("--saveas"))
             {
                 screenObject.setPath(args[++i]);
             }
             else if(args[i].equals("--setpostfix"))
             {
                 screenObject.setPrefix(args[++i]);
             }
             else if(args[i].equals("--settime"))
             {
                 screenObject.setTime(Long.valueOf(args[++i]));
             }
             else if(args[i].equals("--setautostop"))
             {
                 autostop = args[++i];
             }
             else if(args[i].equals("--capture"))
             {
                 name = args[++i];
             }
             else if(args[i].equals("--setcounter"))
             {
                 counter = Integer.valueOf(args[++i]);
                 incounter = true;
             }
             else if(args[i].equals("--multicapture"))
             {
                 if(args[++i].equalsIgnoreCase("on"))
                     multicapture = true;
             }
             else if(args[i].equals("-h") || args[i].equals("--help"))
             {
                 System.out.print("Screen Capture Application v1.0 -- by Ntanasis Periklis [pntanasis@gmail.com]\n" +
                         "Commands\n" +
                         "--saveas\t\tSet relative path\n" +
                         "--setprefix\t\tSet the image name postfix\n" +
                         "--setcounter\t\tSet captures number - works only with multicapture mode\n"+
                         "--settime\t\tSet how much time in milliseconds the loop will wait in MultiCapture mode\n" +
                         "--setautostop\t\tSet after how many milliseconds the process will stop\n" +
                         "--capture\t\tGive image name for uni-capture mode - don't forget to set save path\n" +
                         "--multicapture\t\tSet on or off - doesn't apply with capture\n");
                 System.exit(1);
             }
             else
             {
                 throw new pntanasis.exceptions.WrongSyntaxException("Syntax error\nGive -h--help for information");
             }
         }
        } catch (AWTException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        } 
         catch (WrongSyntaxException e){
             System.out.println(e.getMessage());
             System.exit(1);
         }

         if(multicapture && !name.equals(""))
         {
             System.out.println("multicapture doesn't works with capture");
             System.exit(1);
         }

         if(multicapture && counter<=0 && autostop.equals("") && incounter)
         {
             System.out.println("counter should be greater of zero");
             System.exit(1);
         }

         if(!name.equals("") && incounter)
         {
             System.out.println("counter works only in multicapture mode");
             System.exit(1);
         }

         try{
             if(!autostop.equals("") && !name.equals(""))
                 throw new pntanasis.exceptions.WrongSyntaxException("Syntax error");
             if(multicapture && !(autostop.equals("")))
                 screenObject.multiCapture(Long.valueOf(autostop));
             else if(!name.equals(""))
                 screenObject.Capture(name);
             else if(multicapture && counter>0)
             {
                 screenObject.multiCapture(counter);
             }
             else if(multicapture)
                 screenObject.multiCapture();
             
         }
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
         catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
         catch (WrongSyntaxException e){
             System.out.println(e.getMessage());
             System.exit(1);
         }
         
    }
}
