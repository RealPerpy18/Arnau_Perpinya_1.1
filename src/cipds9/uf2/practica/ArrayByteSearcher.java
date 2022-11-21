package cipds9.uf2.practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.copyOfRange;

public class ArrayByteSearcher extends RecursiveTask {

private static final int THRESHOLD=25;
    byte[]Fitxer;
    byte[]Target;

    public ArrayByteSearcher(byte[] fitxer, byte[] target) {
        Fitxer = fitxer;
        Target = target;
    }

    public long[] addArr(long[]a, long b){
        int n = a.length;
        long newArr[] = new long[n+1];

        for(int i = 0; i<n; i++) {
            newArr[i] = a[i];
        }
        newArr[n] = b;
      return newArr;
    }

    @Override
    protected long[] compute() {

        if (Fitxer.length<THRESHOLD){
           return task();
        }

         else{

            List<ArrayByteSearcher> subtasks =
                    new ArrayList<ArrayByteSearcher>();
            subtasks.addAll(createSubtasks());

            for(ArrayByteSearcher subtask : subtasks){
                subtask.fork();
            }
            for(ArrayByteSearcher subtask : subtasks) {
               subtask.join();
            }

            return task();
        }


    }


    private List<ArrayByteSearcher> createSubtasks() {
        List<ArrayByteSearcher> subtasks =
                new ArrayList<ArrayByteSearcher>();

        ArrayByteSearcher subtask1 = new ArrayByteSearcher(copyOfRange(Fitxer,0,Fitxer.length/2),Target);
        ArrayByteSearcher subtask2 = new ArrayByteSearcher(copyOfRange(Fitxer,Fitxer.length/2,Fitxer.length),Target);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
    private long[] task(){
        byte[]auxFitxer = new byte[0];
        long[]ret=new long[0];
        for(int i=0;i<Fitxer.length;i++) {
            if(Fitxer[i]==Target[0]) {
                auxFitxer=copyOfRange(Fitxer,  i,  i+Target.length);
                if(Arrays.equals(auxFitxer,Target)){
                    ret= addArr(ret,i+1);
                    i=i+(Target.length-1);
                }
            }
        }
        return ret;
    }

}




