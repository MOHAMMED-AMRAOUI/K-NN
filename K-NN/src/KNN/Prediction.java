package KNN;

import java.util.ArrayList;
import java.util.List;


public class Prediction {
    //*** méthode pour calculer l'exactitude***//
    public static double Exactitude(int [][] T , int n) {
        double r = 0;
        for(int i=0;i<T.length;i++) {
            r +=T[i][i];
        }
        r=r/n;
        return r;
    }


    //*** méthode pour calculer la précision pour chaque classe***//
    public static double Précision (int [][] T,int n){

        double pred=0;
        for(int i=0;i<2;i++){
            pred+= T[i][n];
        }
        pred=T[n][n]/pred;
        return pred;

    }

    //*** méthode pour calculer le rappel pour chaque classe***//
    public static double Rappel (int [][] T,int n){
        double rap=0;
        for(int i=0;i<2;i++){
            rap+= T[n][i];
        }
        rap=T[n][n]/rap;
        return rap;

    }

    //*** méthode pour calculer le F_mesure pour chaque classe***//
    public static double F_mesure (double précision, double rappel){

        return 2*(précision*rappel)/(précision+rappel);

    }

    //*** méthode pour calculer la matrice de confusion***//
    public static int[][] Matrix (List<Instance> actual,List<Instance> predicted,int k){
        Distances distances=new Distances();
        int[][] Tab=new int [2][2];
        int i,j;

        for( i=0; i< predicted.size(); i++){
            List <Distances> listResultat=distances.calculeDistance(actual,predicted.get(i).attributs);
            String res=distances.classeInstance( listResultat,k);
            if(predicted.get(i).valuesClass.equals("yes")){
                if(res.equals("yes")){
                    Tab[0][0]++;
                }else if(res.equals("no"))
                    Tab[0][1]++;
            }else if(predicted.get(i).valuesClass.equals("no")){
                if(res.equals("yes")){
                    Tab[1][0]++;
                }else if(res.equals("no"))
                    Tab[1][1]++;
            }


        }
        return Tab;
    }

    static double Precision(int[][] mat, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

