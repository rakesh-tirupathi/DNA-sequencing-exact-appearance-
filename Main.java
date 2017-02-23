/**
 * This is a program that will find a common pattern (motif) from a sequence of strings (DNA patterns)
 *
 * Input  :  Input will be read from the fasta files
 *
 */
package KunthMorrisPratt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rakes_000 on 2/17/2017.
 */
public class Main {

    static String motif_found;
    static boolean foundMotif;
    static boolean print;

    public static void main(String[] args) throws IOException {

        /* Getting the length of the motif pattern from the user*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the length of the motif pattern that you want to find in the sequence: ");
        int input = scanner.nextInt();
        System.out.println("The length of the motif pattern entered is: " + input);

        /*Calling the ReadFasta method to read and display the DNA sequences from the file*/
        ReadFasta read = new ReadFasta();
        read.ReadFastaInput();

         /*creating an array list to store all the subsequences that are matched*/
        ArrayList<String> test_motif = new ArrayList<String>();

        /*Block for comparing two sequences to find the common pattern(i.e., Motif) between them*/
       for(int sequenceNo = 1; sequenceNo < read.getLines().size();sequenceNo++){
           int startPt =0,endPt=input;
           boolean endOfSequence = false;
           foundMotif = false;
           print = false;
           //System.out.println("\ncomparing Sequences: " + sequenceNo + " and " + (sequenceNo+1));

           while(!endOfSequence){
               String testMotif = read.getSequence(sequenceNo+1).substring(startPt,endPt);
               KnuthMorrisPratt kmp = new KnuthMorrisPratt(read.getSequence(sequenceNo), testMotif);
               if(kmp.getPrintPos()>0){
                   String motif = read.getSequence(sequenceNo).substring(kmp.getPrintPos(),(kmp.getPrintPos()+input));
                   //System.out.println("The subsequence matched is: " + motif );

                   /* This if else block stores all the subsequences in an array list compared between 1st and the 2nd
                    * sequence and then any other sequences generated after that will be compared with this array list
                    * and if the subsequence is found within this array list it will output it*/
                   if(sequenceNo == 1){
                       test_motif.add(motif);
                   }
                   else{
                       if(test_motif.contains(motif)){
                           //System.out.println("Motif found for the sequence: " + sequenceNo);
                           motif_found = motif;
                           foundMotif = true;
                       }
                   }
               }
               //System.out.println(testMotif);
               startPt+=1;
               endPt+=1;
               if(endPt>read.getSequence(sequenceNo+1).length()){
                   endOfSequence = true;
               }
           }
           if(sequenceNo > 1 && foundMotif == false){
               System.out.println("\nA similar subsequence was not found in the sequence no: " + sequenceNo);
               print = true;
               break;
           }
       }

        //System.out.println("\nThe number of subsequences found while comparing 1st and 2nd sequence is: " + test_motif.size());
        /*for(String mo : motif_found){
            System.out.println("The sequences found between 1 and 2 are: " + mo);
        }*/

        if(print == true){
            System.out.println(" No motif found in the sequences");
        }
        else{
            System.out.println("\n The motif found in the sequences is: " + motif_found );
        }
    }
}
