public class LineReader {

    private String fileName;

    public LineReader(String filePath) {
        this.fileName = fileName;
    }

    public String readLine() {
        if (fileName == null) {
            System.err.println("Incorrect path");
            return null;
        }

        return "";
    }
}
