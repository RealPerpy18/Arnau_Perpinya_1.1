package cipds9.uf2.practica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.copyOfRange;

public class ArrayByteSearcher extends RecursiveTask<ArrayList<Long>> {


    private byte[]Fitxer;
    private byte[]Target;
    private int ini;
    private int cores;

    public ArrayByteSearcher(byte[] fitxer, byte[] target) {
        this.Fitxer = fitxer;
        this.Target = target;
        this.cores=Runtime.getRuntime().availableProcessors();
        this.ini=1;
    }
    public ArrayByteSearcher(byte[] fitxer, byte[] target,int cores, int ini) {
        this.Fitxer = fitxer;
        this.Target = target;
        this.cores=cores;
        this.ini=ini;
    }

    /**
     * Executa únicament la funció getPositions depenent de si cal dividir la tasca.
     * Si cal dividir-la, es crida a ella mateixa dividint el fitxer.
     * @return Retorna un ArrayList de Long amb les posicions on es troba el Target dins del Fitxer.
     */
    @Override
    protected ArrayList<Long> compute() {
       if (cores==1||Fitxer.length<50.000) {
            System.out.println("conta");

        return getPositions(Fitxer,Target,ini);

     } else {
            byte [] fpp1 = new byte[((Fitxer.length/2))+(Target.length-1)];
            System.arraycopy(Fitxer,0,fpp1,0,fpp1.length);

            byte [] fp2 = new byte[(Fitxer.length/2)+(Fitxer.length%2)];
            System.arraycopy(Fitxer,Fitxer.length/2,fp2,0,fp2.length);


            ArrayByteSearcher a1 = new ArrayByteSearcher(fpp1, Target,cores / 2, this.ini);
            a1.fork();
            ArrayByteSearcher a2 = new ArrayByteSearcher(fp2, Target, cores / 2,this.ini+ Fitxer.length/2 );
            a2.fork();
            ArrayList<Long>fusio=new ArrayList<>();
            fusio.addAll(a1.join());
            fusio.addAll(a2.join());
            return fusio;
        }

    }

    /**
     *
     * @param Fitxer Array Byte[] del fitxer
     * @param Target Array Byte[] del Target
     * @param ini Posicio on comença a llegir l'Array Fitxer. Serveix especialment en la recursivitat on la posicio 0 no és la primera.
     * @return ArrayList long de posicions del Target dividida en cas de recursivitat.
     */
    private ArrayList<Long> getPositions(byte[]Fitxer,byte[]Target,long ini){
        ArrayList<Long>posicions = new ArrayList<>();
        for (int i=0;i<=Fitxer.length-Target.length;i++){
            if (Fitxer[i]==Target[0]){
                boolean coincideix=true;
                int k = 0;
                while (coincideix && k<Target.length){
                    if (!(Fitxer[i+k]==Target[k])){
                        coincideix=false;
                    }
                    else k++;
                }
                if (coincideix){
                    posicions.add(Long.parseLong(i+"")+ini);
                    i+=k-1;
                }
            }
        }
        return posicions;
    }

}




