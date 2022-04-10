import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelOut {

    String keywordsFile = "output3.txt";
    String[] keywords;
    {
        try {
            keywords = FileToArrayReader.readLines(keywordsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Initialize a Workbook object
    public void ExcelOut() throws Exception {
        Workbook workbook = new Workbook();
        // Obtaining the reference of the worksheet
        Worksheet worksheet = workbook.getWorksheets().get(0);
        // Instantiating an ArrayList object
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(List.of(keywords));

        // Exporting the contents of ArrayList vertically at the first row and first column of the worksheet.
        worksheet.getCells().importArrayList(list, 0, 0, true);

        // Saving the Excel file
        workbook.save("Output.xlsx");
    }



}
