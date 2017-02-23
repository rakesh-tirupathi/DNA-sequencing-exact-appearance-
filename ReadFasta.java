/**
 * This class reads the input i.e., DNA Sequences from the fasta file and
 * stores it in an ArrayList and displays them
 */

package KunthMorrisPratt;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by rakes_000 on 2/17/2017.
 */
public class ReadFasta {

    //Creating an Array list to store each line into an array
    ArrayList<String> lines = new ArrayList<String>();

    public ArrayList<String> getLines() {
        return lines;
    }

    public String getSequence(int i){
        return lines.get(i-1);

    }

    /* method for reading the Fasta file input and
            store the string sequences in an Array List */
    public void ReadFastaInput() {

        String fileName = "src/fasta.txt";

        // This will reference one line at a time
        String line = null;

        try {
            /* FileReader reads text files in the default encoding.
             Always wrap FileReader in BufferedReader.*/
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            // reading the input from the fasta file and storing it in an Array List
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(">") || line.isEmpty()) {
                    //do nothing
                } else {
                    lines.add(line);
                }
            }

            //printing the DNA sequences(strings) read from the file
            System.out.println("The number of sequences read from the file is : " + lines.size());
            System.out.println("\nThe Sequences are: ");
            for(int i=0; i<lines.size(); i++){
                System.out.println(lines.get(i));
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}
