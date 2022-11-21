package cipds9.uf2.practica;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

public class ArrayByteSearcher {

    byte[]Fitxer;
    byte[]Target;

    public ArrayByteSearcher(byte[] fitxer, byte[] target) {
        Fitxer = fitxer;
        Target = target;
    }

    public byte[] getFitxer() {

        return Fitxer;
    }

    public byte[] getTarget() {

        return Target;
    }
    public long[] calculosComplejos(){
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




