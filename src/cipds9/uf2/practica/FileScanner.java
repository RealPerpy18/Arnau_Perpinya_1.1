package cipds9.uf2.practica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class FileScanner {

    private static int FILE_MAX_SIZE=100000;


    public static void main(String[] args) {
      //  int cores = Runtime.getRuntime().availableProcessors();

        int llarg=readFile(args[0]).length;
        if(llarg<=FILE_MAX_SIZE) {

            ForkJoinPool pool = new ForkJoinPool();
            ArrayByteSearcher abs = new ArrayByteSearcher(readFile(args[0]), readFile(args[1]), 0, llarg);
            pool.invoke(abs);
            if (abs.getArray().length > 0) {
                Arrays.sort(abs.getArray());
                System.out.println(Arrays.toString(abs.getArray()));
            } else {
                System.out.println(0);
            }
        }
        else{
            System.out.println("El fitxer es massa granm");
        }

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