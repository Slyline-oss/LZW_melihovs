import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class WriteFile {

    private String resultFile;
    private ArrayList<String> stringToWrite;

    public WriteFile(String resultFile, ArrayList<String> stringToWrite) {
        this.resultFile = resultFile;
        this.stringToWrite = stringToWrite;
    }

    public void writeIntoFile() throws IOException {
        File file = new File(resultFile);
        if (file.createNewFile()) System.out.println("file created: " + file.getCanonicalPath());
        else System.out.println("file already exists at location: " + file.getCanonicalPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        String s;
        for (int i = 0; i < stringToWrite.size(); i++) {
            s = stringToWrite.get(i);
            bw.write(s);
            if (i != stringToWrite.size()-1) bw.newLine();
        }
        bw.close();
     }


}
