package cipds9.uf2.practica;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ArrayByteSearcher extends RecursiveAction {
    private static final int THRESHOLD=25;

    public static long[] arrayfinal = new long[0];

    byte[]Fitxer;
    byte[]Target;
    int ini;

    int fi;

    public ArrayByteSearcher(byte[] fitxer, byte[] target,int ini,int fi) {
        this.Fitxer = fitxer;
        this.Target = target;
        this.ini =ini;
        this.fi =fi;

    }
    public long[] getArray(){
        return arrayfinal;
    }



    protected void compute() {
        if ((fi-ini)<THRESHOLD){
            byte[]auxFitxer ;
            for(int i=this.ini;i<this.fi;i++) {
                if(Fitxer[i]==Target[0]) {
                    auxFitxer=copyOfRange(Fitxer,  i,  i+Target.length);
                    if(Arrays.equals(auxFitxer,Target)){
                        arrayfinal= addArr(arrayfinal,i+1);
                        i=i+(Target.length-1);
                    }
                }
            }
        }
else{
            int mid=(ini+fi+Target.length)/2;
            ArrayByteSearcher a1=new ArrayByteSearcher(Fitxer,Target,ini,mid);
            ArrayByteSearcher a2=new ArrayByteSearcher(Fitxer,Target,mid+1,fi);
            invokeAll(a1,a2);
        }
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
}




