import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LineReader {

    private String fileName;

    public LineReader(String filePath) {
        this.fileName = filePath;
    }

    public ArrayList<String> readLine() {
        ArrayList<String> stringArr = new ArrayList<String>();
        FileReader dataBase;
        if (fileName == null) {
            System.err.println("Incorrect path");
            return null;
        } else {
            dataBase = null;
            try {
                dataBase = new FileReader(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scanner sc = new Scanner(dataBase);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                stringArr.add(str);
            }
            return stringArr;
        }
    }
}
