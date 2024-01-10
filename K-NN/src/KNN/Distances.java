package KNN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Distances{
    public double distance;
    public String classe;

    public Distances(double distance, String classe) {
        this.distance = distance;
        this.classe = classe;
    }

    public Distances() {

    }
    /*
    * Calculez la distance entre la liste de test et chaque rangée de liste d’apprentissage
    * en utilisant la méthode Euclidienne
     */
    public List<Distances> calculeDistance(List<Instance> listApprentissage,String[] instanceTest){
        List<Distances> listResultats =new ArrayList<Distances>();
        for(int i=0; i<listApprentissage.size();i++){
            double dist=0.0;
            String [] corpusApprAttributs=listApprentissage.get(i).attributs;
            String classValue=listApprentissage.get(i).valuesClass;
            for(int j=0; j<corpusApprAttributs.length;j++){
                dist+=Math.pow(this.Levenshtein(instanceTest[j],corpusApprAttributs[j]),2);
            }
            distance= Math.sqrt(dist);
            listResultats.add(new Distances(distance,classValue));
        }

        System.out.println("\n\n\n");

        System.out.println("liste de distances avant le tri ☺☺☺☺→: ");
        for(int i=0;i<listResultats.size();i++){
            System.out.println(listResultats.get(i).distance +" "+ listResultats.get(i).classe);
        }
        // En fonction de la valeur de distance, triez-les par ordre croissant.
        Collections.sort(listResultats, new DistanceComparator());
        System.out.println("\n\n#========================================================================#\n\n");
        System.out.println("liste de distances après le tri ☻☻☻☻→: ");
        for(int i=0;i<listResultats.size();i++){
            System.out.println(listResultats.get(i).distance +" "+ listResultats.get(i).classe);
        }
        return listResultats;
    }

    /*
    *       The Levenshtein distance is a measure of dissimilarity between two Strings.
    *       Mathematically, given two Strings x and y,
    *       the distance measures the minimum number of character edits required to transform x into y.
    *
    *       Example: If x = ‘shot' and y = ‘spot', the edit distance between the two is 1 because ‘shot'
    *       can be converted to ‘spot' by substituting ‘h‘ to ‘p‘.
    * */


    // Calcule et renvoie la distance Levenshtein entre deux chaînes de longueurs arbitraires.
    public int Levenshtein(String str1, String str2) {

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {

              /*    When one of the Strings is empty, then the edit distance between them is the length
                of the other String   */
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    // we search the minimum with 3 operations : Insertion , Deletion ,Substitution



                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // Delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
        // Définir la classe d’un nouvelle instance
   public String classeInstance(List<Distances> listeResultats, int k){
       int compteur = 0;
       for(int i=0;i<k;i++){
           if (listeResultats.get(i).classe.equals("yes"))
               compteur++;
       }
       if (compteur>k-compteur)
           return "yes";
       else
           return "no";
   }
    }

