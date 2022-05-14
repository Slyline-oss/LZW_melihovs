import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LZW {

    private String sourceFile;
    private String resultFile;

    public LZW(String sourceFile, String resultFile) {
        this.sourceFile = sourceFile;
        this.resultFile = resultFile;
    }

    public void comp() throws IOException {
        LineReader lr = new LineReader(sourceFile);
        ArrayList<String> stringArr = lr.readLine();

        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }
        ArrayList<String> result = new ArrayList<>();
        String text = "";
        for (int i = 0; i < stringArr.size(); i++) {
            text = stringArr.get(i);
            String foundChars = "";
            for (char character : text.toCharArray()) {
                String charsToAdd = foundChars + character;
                if (dictionary.containsKey(charsToAdd)) {
                    foundChars = charsToAdd;
                } else {
                    result.add(String.valueOf(dictionary.get(foundChars)));
                    dictionary.put(charsToAdd, dictSize++);
                    foundChars = String.valueOf(character);
                }
            }
            if (!foundChars.isEmpty()) {
                result.add(String.valueOf(dictionary.get(foundChars)));
            }
        }

        WriteFile wf = new WriteFile(resultFile, result);
        wf.writeIntoFile();
   }

    public void decode() {

        LineReader lr = new LineReader(sourceFile);
        ArrayList<String> stringArr = lr.readLine();
        ArrayList<Integer> encodedText = new ArrayList<>();
        for(String stringValue : stringArr) {
            try {
                encodedText.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
        }
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
                dictionary.put(i, String.valueOf((char) i));
        }

        String characters = String.valueOf((char) encodedText.remove(0).intValue());
        StringBuilder result = new StringBuilder(characters);
        for (int code : encodedText) {
            String entry = dictionary.containsKey(code)
                    ? dictionary.get(code)
                    : characters + characters.charAt(0);
            result.append(entry);
            dictionary.put(dictSize++, characters + entry.charAt(0));
            characters = entry;
        }

        System.out.println(result);

    }
}
