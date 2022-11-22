package cipds9.uf2.practica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class FileScanner {

    private static int FILE_MAX_SIZE=50000000;


    public static void main(String[] args) {
      //

        if(readFile(args[0]).length<=FILE_MAX_SIZE) {

            ForkJoinPool pool = new ForkJoinPool();
            long temp1=System.currentTimeMillis();
            ArrayByteSearcher abs = new ArrayByteSearcher(readFile(args[0]), readFile(args[1]));
            long temp2=System.currentTimeMillis();

            ArrayList<Long> al=pool.invoke(abs);
            if (al.size() > 0) {

                Collections.sort(al);

                long[] ala = new long[al.size()];
                for(int i=0; i<al.size();i++){
                    ala[i]=al.get(i);
                }
                System.out.println(Arrays.toString(ala));
                if(args.length==3) {
                    System.out.print("Temps d'execució: ");
                    System.out.println((((temp2 - temp1)/1000)+","+(temp2 - temp1)%1000)+" Segons");
                }
            } else {
                if(args.length==3) {
                    System.out.print("Temps d'execució: ");
                    System.out.println((((temp2 - temp1)/1000)+","+(temp2 - temp1)%1000)+" Segons");
                }
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