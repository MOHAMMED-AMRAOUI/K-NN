package K_Optimale;

import KNN.Distances;
import KNN.FileToList;
import KNN.Instance;
import KNN.Prediction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class K_Optimale {

    public static void main(String[] args) {
        String chemin ="C:\\Users\\AMRAOUI\\Documents\\NetBeansProjects\\K-NN\\src\\KNN\\weather.nominal.arff";  // chemin du corpus
        FileToList fichier=new FileToList(chemin);
        Distances distances=new Distances();
        List<Instance> listeInstances = fichier.lireFichier();
        Collections.shuffle(listeInstances);
        List<Instance> listApprentissage =new ArrayList<Instance>();
        List<Instance> listTest =new ArrayList<Instance>();
        double pourcentage=0.8;
        int nombreErreur=0,i,k;
        int trainSize=(int) Math.round(listeInstances.size()*pourcentage);
        int TestSize=listeInstances.size()-trainSize;
        System.out.println("Affichage des instances d'Apprentissage :");
        for(i=0;i<trainSize;i++){
            listApprentissage.add(new Instance(listeInstances.get(i).attributs,listeInstances.get(i).valuesClass));
            System.out.println(Arrays.asList(listApprentissage.get(i).attributs)+" "+listApprentissage.get(i).valuesClass);
        }

        for(i=trainSize;i<listeInstances.size();i++){
            listTest.add(new Instance(listeInstances.get(i).attributs,listeInstances.get(i).valuesClass));
        }
        System.out.println("\n Affichage des instances de Test :");
        for(int j=0;j<listTest.size();j++){
            System.out.println(Arrays.asList(listTest.get(j).attributs)+" "+listTest.get(j).valuesClass);
        }

        //*** Appliquer KNN pour toutes les instances du test pour k egal à racine de n ***
        for(i=0;i<listTest.size();i++){
            List<Distances> listeResultats=distances.calculeDistance(listApprentissage, listTest.get(i).attributs);
            String resultat=distances.classeInstance(listeResultats,(int) Math.sqrt(listApprentissage.size()));
            if(!resultat.equals(listTest.get(i).valuesClass)){
                // System.out.println("la calculation des distances n'est pas optimal");
                nombreErreur++;
            }else continue;
        }


        // calculer La matrice de confusion
        System.out.println("\n");
        int[][] mat= new int[2][2];
        mat = Prediction.Matrix(listApprentissage,listTest,(int) Math.sqrt(listApprentissage.size()));
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println(" La matrice de confusion :");
        for(int j=0;j<2;j++) {
            for(int r=0;r<2;r++) {
                System.out.print("\t"+mat[j][r]+"\t"+"|");
            }
            System.out.println("\n");
        }

        // L'obtention de l'exactitude
        double x= Prediction.Exactitude(mat, listTest.size());
        System.out.println("l'exactitude est : "+ x);
        System.out.println("\n");
        double y=Prediction.Précision(mat,0);
        //L'obtention de la Précision pour chaque classe
        System.out.println("la precision de la classe 1 est : "+ y);
        double y1=Prediction.Précision(mat,1);
        System.out.println("la precision de la classe 2 est : "+ y1);
        System.out.println("\n");
        //L'obtention de le Rappel pour chaque classe
        double z=Prediction.Rappel(mat,0);
        System.out.println("le rappel de la classe 1 est : "+ z);
        double z1=Prediction.Rappel(mat,1);
        System.out.println("le rappel de la classe 2 est : "+ z1);
        System.out.println("\n");
        //L'obtention de F_mesure  pour chaque classe
        double f= Prediction.F_mesure(y, z);
        System.out.println("la f mesure de la classe 1 est : "+ f);
        double f1= Prediction.F_mesure(y1, z1);
        System.out.println("la f mesure de la classe 2 est : "+ f1);
    }
}

