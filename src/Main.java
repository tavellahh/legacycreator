import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Main app = new Main();

        String fileName = "hht.txt";

        System.out.println("\ngetResource : " + fileName);
        File file = app.getFileFromResource(fileName);
        printFile(file);

        // Buscar y reemplazar texto en el archivo
        String searchText = "hola";
        String replaceText = "Replacement";
        String destinationPath = "C:\\Users\\Hernan\\destination.txt"; // Ruta de destino del archivo modificado

        app.searchAndReplaceInFile(file, searchText, replaceText, destinationPath);
    }

 
    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static void printInputStream(InputStream is) {
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFile(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchAndReplaceInFile(File file, String searchText, String replaceText, String destinationPath) {
        try {
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(searchText)) {
                    line = line.replace(searchText, replaceText);
                    lines.set(i, line);
                }
            }

            Path destinationFilePath = Paths.get(destinationPath);
            Files.write(destinationFilePath, lines, StandardCharsets.UTF_8);
            System.out.println("File saved to: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
