package cipds9.uf2.practica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class FileScanner {

    private static int FILE_MAX_SIZE=100000;
    private static String pathfile="C:\\apunts.txt";
    private static String pathtarget="C:\\target.txt";

    public static void main(String[] args) {
      //  int cores = Runtime.getRuntime().availableProcessors();

        long t1=System.currentTimeMillis();
        ForkJoinPool pool=new ForkJoinPool(2);
        ArrayByteSearcher abs=new ArrayByteSearcher(readFile(pathfile),readFile(pathtarget));
        long[]a= (long[]) pool.invoke(abs);
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(Arrays.toString(a));

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