import java.io.IOException;
import java.util.*;


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
        for (int i = 32; i < dictSize; i++) {
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

    public void decode() throws IOException {

        LineReader lr = new LineReader(sourceFile);
        ArrayList<String> stringArr = lr.readLine();
        ArrayList<Integer> encodedText = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 32; i < dictSize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }
        String [] numbers;
        for(int i = 0; i < stringArr.size(); i++) {
            String stringValue = stringArr.get(i);
            if (Objects.equals(stringValue, "")) continue;;
            numbers = stringValue.split(" ");
            for (int j = 0; j < numbers.length; j++) {
                try {
                    encodedText.add(Integer.parseInt(numbers[j]));
                } catch(NumberFormatException nfe) {
                    System.out.println(nfe.getMessage());
                }
            }
            String characters = dictionary.get(encodedText.get(0));
            encodedText.remove(0);
            StringBuilder result = new StringBuilder(characters);
            for (int code : encodedText) {
                String entry = dictionary.containsKey(code)
                        ? dictionary.get(code)
                        : characters + characters.charAt(0);
                result.append(entry);
                dictionary.put(dictSize++, characters + entry.charAt(0));
                characters = entry;
            }

            output.add(String.valueOf(result));
            output.add("");
            result.delete(0,result.length());
            Arrays.fill(numbers,null);
            encodedText.clear();
        }

        WriteFile wf = new WriteFile(resultFile, output);
        wf.writeIntoFile();

    }
}
