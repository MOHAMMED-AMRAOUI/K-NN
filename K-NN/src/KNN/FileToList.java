package KNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mostafa
 */
public class FileToList {
    // chemin de corpus
    String chemin;

    // transfère un fichier vers liste
    public FileToList(String chemin){
        this.chemin=chemin;
    }
    public List<Instance> lireFichier(){
        List<Instance> listeInstances=new ArrayList<Instance>();
        String ligne=null;
        try{
            // Lire et traiter toutes les lignes du fichier de caractères donné par Path "chemin"
            BufferedReader bf=new BufferedReader(new FileReader(chemin)); //Stocker un nouveau BufferedReader pour chemin dans bf
            while((ligne=bf.readLine())!=null){ // lire le fichier ligne par ligne tantque la ligne n'est pas vide
                if(! ligne.contains("@") && ! ligne.contains("%") && ! ligne.equals("")){

                    String attributs=ligne.substring(0, ligne.lastIndexOf(","));
                    String classe=ligne.substring(ligne.lastIndexOf(",")+1,ligne.length());
                    String [] tableau=attributs.split(",");
                    listeInstances.add(new Instance(tableau, classe));
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listeInstances;
    }

}

