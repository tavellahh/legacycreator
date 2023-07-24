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

//        Main app = new Main();
//        String fileName = "MuleTemplate\\hht.txt";
//        File file = app.getFileFromResource(fileName);
//        printFile(file);
//
//        // Buscar y reemplazar texto en el archivo
//        String searchText = "hola";
//        String replaceText = "Replacement";
//        String destinationPath = "C:\\Users\\p046456\\Downloads\\destination.txt"; // Ruta de destino del archivo modificado
//
//        app.searchAndReplaceInFile(file, searchText, replaceText, destinationPath);
    	Main app = new Main();
    	
    	String nombreLegacy = "et02"; 
    	String folderLegacy = "C:\\MIGRACION\\legacy-"+nombreLegacy;
        copyDirectory("C:\\MIGRACION\\legacy-LLLL", folderLegacy);
        
        
        //POM
    	String pomPath = folderLegacy + "\\pom.xml";
        File pomFile = new File(pomPath);
        String pomSearch = "PPPP";
        String pomReplace = nombreLegacy;
        searchAndReplaceInFile(pomFile, pomSearch, pomReplace, pomPath);
        
        
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

    private static void printFisle(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchAndReplaceInFile(File file, String searchText, String replaceText, String destinationPath) {
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
    
    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) 
    		  throws IOException {
    		    Files.walk(Paths.get(sourceDirectoryLocation))
    		      .forEach(source -> {
    		          Path destination = Paths.get(destinationDirectoryLocation, source.toString()
    		            .substring(sourceDirectoryLocation.length()));
    		          try {
    		              Files.copy(source, destination);
    		          } catch (IOException e) {
    		              e.printStackTrace();
    		          }
    		      });
    		}

}
