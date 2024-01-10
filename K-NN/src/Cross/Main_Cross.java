package Cross;

import KNN.FileToList;
import KNN.Instance;
import KNN.Prediction;

import java.io.IOException;
import java.util.*;

public class Main_Cross {
    public static void main(String[] args) throws IOException {
        //Chemin de corpus
        String chemin ="C:\\Users\\AMRAOUI\\Documents\\NetBeansProjects\\K-NN\\src\\KNN\\weather.nominal.arff";  // chemin du corpus
        FileToList fichier=new FileToList(chemin);
        List<Instance> listeInstances = fichier.lireFichier();
        List <Instance> listApprentissage= new ArrayList <Instance>();
        List <Instance> listTest= new ArrayList <Instance>();
        Scanner s= new Scanner(System.in);
        // Nombre de groupe dans lequel l'échantillon sera réparti
        int partition;
        System.out.println("Entrer le nombre de folds : ");
        partition = s.nextInt();
        System.out.println("\n Entrer la valeur de K : ");
        int KK = s.nextInt();

        int k,i ;
        listeInstances = fichier.lireFichier();
        Collections.shuffle(listeInstances);
        // On effectue d'abord une séparation de l'ensemble de données de façon aléatoire en K folds
        int size=listeInstances.size()/partition;

        for(i=0;i<partition;i++) {
            for(k=i*size;k<(i+1)*size;k++) {
                //System.out.println(" c'est l'iteration =>"+i +"de l'ensemble =>"+k);
                // Ajouter les éléments dans la liste de Test
                listTest.add(new Instance(listeInstances.get(k).attributs,listeInstances.get(k).valuesClass));
                listApprentissage=listeInstances;
                //Supprimer les éléments sélectionnées de la liste de Test à partir de la listeinstances pour avoir la liste d'apprentissage
                listApprentissage.remove(new Instance(listeInstances.get(k).attributs,listeInstances.get(k).valuesClass));
            }
            // calculer la matrice de confusion
            System.out.println("\n");
            int[][] mat= new int[2][2];
            mat =Prediction.Matrix(listApprentissage,listTest,KK);
            System.out.println();
            System.out.println("------------------------------------------------");
            System.out.println(" =======> La matrice de confusion <======");
            for(int j=0;j<2;j++) {
                for(int r=0;r<2;r++) {

                    System.out.print("\t"+mat[j][r]+"\t"+" | ");
                }
                System.out.println("\n");
            }
            // L'obtention de l'exactitude
            double x= Prediction.Exactitude(mat, listTest.size());
            System.out.println("L'exactitude est ===> "+ x);
            System.out.println("\n");
            //L'obtention de la Précision pour chaque classe
            double y=Prediction.Précision(mat,0);
            System.out.println("La precision de la classe 1 est ===> "+ y);
            double y1=Prediction.Précision(mat,1);
            System.out.println("La precision de la classe 2 est ===> "+ y1);
            System.out.println("\n");
            //L'obtention de le Rappel pour chaque classe
            double z=Prediction.Rappel(mat,0);
            System.out.println("Le rappel de la classe 1 est ===> "+ z);
            double z1=Prediction.Rappel(mat,1);
            System.out.println("Le rappel de la classe 2 est ===> "+ z1);
            System.out.println("\n");
            //L'obtention de F_mesure pour chaque classe
            double f= Prediction.F_mesure(y, z);
            System.out.println("La f mesure de la classe 1 est ===> "+ f);
            double f1= Prediction.F_mesure(y1, z1);
            System.out.println("La f mesure de la classe 2 est ===> "+ f1);
        }


}
}
