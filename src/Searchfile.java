import java.io.File;

public class Searchfile {


    static boolean fileFound;
    static String fileName = "tumblr.txt";


    public static void searchFile(File f)     //File f is "C:\\"
    {
        try
        {
            if(f.isDirectory())
            {
                File [] fi = f.listFiles();
                for(int i=0;i<fi.length;i++)
                {
                    if(fileFound==true)
                    {
                        break;
                    }
                    System.out.println(fi[i].getName());
                    searchFile(fi[i]);
                }
            }
            else
            {
                String fname = f.getName();
                if(fname.equalsIgnoreCase(fileName) ||  fname.toLowerCase().startsWith(fileName.toLowerCase())||
                        fname.toLowerCase().endsWith(fileName.toLowerCase() ))
                {
                    System.out.print("file found " + f.getAbsolutePath());
                    fileFound=true;
                }
            }
        }
        catch(Exception e)
        {
        }
    }
}
