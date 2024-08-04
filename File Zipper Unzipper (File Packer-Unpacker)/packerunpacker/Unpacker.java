import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Unpacker

{
    FileOutputStream outstream = null;

    public Unpacker(String src) throws Exception
    {
        unpack(src);
    }
    
    public void unpack(String filePath) throws Exception
    {
        try
        {
            FileInputStream instream = new FileInputStream(filePath);
            
            byte header[] = new byte[100];
            int length = 0;
            
            byte Magic[] = new byte[12];
            instream.read(Magic, 0, Magic.length);
            
            String Magicstr = new String(Magic);

            if (!Magicstr.equals("Packer11"))
            {
                throw new InvalidFileException("Invalid packed file format");
            } 
            while ((length = instream.read(header, 0, 100)) > 0)
            {
                String str = new String(header);
                
                String ext = str.substring(str.lastIndexOf("/"));
                ext = ext.substring(1);

                String[] words = ext.split("\\s");

                String filename = words[0];

                int size = Integer.parseInt(words[1]);
                
                byte arr[] = new byte[size];

                instream.read(arr, 0, size);

                FileOutputStream fout = new FileOutputStream(filename);
                fout.write(arr, 0, size);
                fout.close();
            }
        }
        catch (InvalidFileException obj)
        {
            throw new InvalidFileException("Invalid packed file format");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
