import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public class editFile{



public void edit(String read,String write){
        BufferedReader objReader = null;
        BufferedWriter objWriter = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(read));
            objWriter = new BufferedWriter(new FileWriter(write));

            while ((strCurrentLine = objReader.readLine()) != null) {
                try {

                    String data0 =strCurrentLine+" ";
                    String data1 = data0.replaceAll("\\s+", "\n").toLowerCase(Locale.ROOT);

                    //remove characters,digits,],[,",plural,
                    String dataFIN = data1.replaceAll("[{,.(’‘`'):\\[*©\\];`#£\"“\\d§%$–!?&}_-]", "")
                            .replaceAll("\uF733","").replaceAll("\uF731","");
                    // Writes the string to the file
                    objWriter.write(dataFIN);

                }catch (Exception e) {
                    e.getStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
                objWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

//.replaceAll("\\d","").replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"", "")