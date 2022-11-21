package cipds9.uf2.practica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileScanner {

    private static int FILE_MAX_SIZE=100000;
    private static String pathfile="C:\\apunts.txt";
    private static String pathtarget="C:\\target.bin";

    public static void main(String[] args) {
      //  int cores = Runtime.getRuntime().availableProcessors();

        ArrayByteSearcher abs=new ArrayByteSearcher(readFile(pathfile),readFile(pathtarget));
        System.out.println(Arrays.toString(abs.calculosComplejos()));

    }
    private static byte[] readFile(String path){
        File file = new File(path);
        try {
           return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}