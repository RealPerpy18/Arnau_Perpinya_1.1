package cipds9.uf2.practica;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import static java.util.Arrays.copyOfRange;

public class ArrayByteSearcher extends RecursiveAction {

    public static long[] arrayfinal = new long[0];

    byte[]Fitxer;
    byte[]Target;
    int ini;
    int fi;

    int llindar;

    public ArrayByteSearcher(byte[] fitxer, byte[] target,int ini,int fi) {
        this.Fitxer = fitxer;
        this.Target = target;
        this.ini =ini;
        this.fi =fi;
        this.llindar=fitxer.length/Runtime.getRuntime().availableProcessors();
    }
    public long[] getArray(){
        return arrayfinal;
    }



    protected void compute() {
        if ((fi-ini)<llindar){
                byte[]auxFitxer;
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
                System.out.println("Conta");
                int mid=(ini+fi+Target.length)/2;
                ArrayByteSearcher a1=new ArrayByteSearcher(Fitxer,Target,ini,mid);
                ArrayByteSearcher a2=new ArrayByteSearcher(Fitxer,Target,mid+1,fi);
                invokeAll(a1,a2);

          }
        }
        public long[] addArr(long[]a, long b){
        int n = a.length;
        long[] newArr = new long[n+1];
            System.arraycopy(a, 0, newArr, 0, n);
        newArr[n] = b;
        return newArr;
    }
}




