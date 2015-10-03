package test.net.modelbased.proasense.adapter.imm;

import java.util.Random;

/**
 * Created by Shahzad on 07.09.2015.
 */
public class GenQueries {

    public static void main(String[] args) {

        for (int i = 0; i < 102; i++) {
        GenQueries genQueries = new GenQueries();
        String refId = genQueries.getReferenceId(genQueries.randomGen());
        String names = genQueries.genNames(i%7);
        int nr = genQueries.randomGen();
        Double randDouble = genQueries.genDouble();



            System.out.println("insert into TNT_0000002015091021 VALUES ('20.08.2013 14:20:50', 0, '"+refId+"', '" + names + "', '1'," + nr + " , " + randDouble + ", ''); ");
        }
    }

    GenQueries(){

    }

    int randomGen(){
        Random gen = new Random();
        int i = gen.nextInt(100);
        return i;
    }

    double genDouble(){
        Random r = new Random();

        return  ((double)Math.round(r.nextDouble() * 1000000)) / 100000;
    }

    String getReferenceId(int i){

        String ref[] = {"6128649", "6128634", "6128630", "6154722", "6154721"};
        int k = i%ref.length;
        return ref[k];
    }

    String genNames(int i){

        String names[] = {"Cycle Time","Injection Time","Dosing Time",
                "Melt Cushion","Movement Differential","Cooling Time","Cavity Pressure"};

        int k = i%(names.length);

        return names[k];
    }
}
