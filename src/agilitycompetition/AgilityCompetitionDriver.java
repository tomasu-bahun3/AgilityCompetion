/**
 * Driver for the Agility Competition showing the use of inheritance and
 * implementation of an interface.
 * The driver performs the input from a file to the AgilityContest object
 * of the competitor objects, finds the winners, prints the sorted list of
 * all competitors and prints a very small press release.
 */
package agilitycompetition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aapplin
 */
public class AgilityCompetitionDriver {

    // create an Contest object as a property
    private Contest contest = new Contest("South Portland",
            "Blue Bison", new Date(5, 12, 2015));

    /**
     * Standard method to read a file.
     * @param fileName the filename from the command line arguments.
     */
    public void readCompetitorFile(String fileName) {

        // now try to connect the sybolic name to the physical file
        try {
            // if the physical file doesn't exist it throws an exception
            Scanner inFile = new Scanner(new FileReader(fileName));
            String name, breed, owner;
            double weight;
            ElapsedTime time;
            Date dob;
            int int1, int2, int3, int4;// use 1 - 3 for dob and 1 - 4 for time
            Competitor competitor; // declare, do not create
            while (inFile.hasNext()) {
                name = inFile.next();
                int1 = inFile.nextInt();
                int2 = inFile.nextInt();
                int3 = inFile.nextInt();
                dob = new Date(int1, int2, int3);
                breed = inFile.nextLine();
                weight = inFile.nextDouble();
                owner = inFile.next();
                int1 = inFile.nextInt();
                int2 = inFile.nextInt();
                int3 = inFile.nextInt();
                int4 = inFile.nextInt();
                time = new ElapsedTime(int1, int2, int3, int4);
                // now create a new object to add to the competition
                competitor = 
                        new Competitor(dob, breed, weight, name, owner, time);
                //System.out.println(competitor); // for debugging
                contest.addContestant(competitor);
            }
            inFile.close();
            contest.determineWinners();
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File data.txt not found");
            // and exit in a controlled manner
            System.exit(1);
        }

    }

    /**
     * For debugging purposes allows us to print the entire 
     * ArrayList 
     *
     */
    public void printCompetitors() {
        double age; // since this changes based on the current date
        // we don't store it, we calcualate it on the fly
        Date contestDate = contest.getContestDate(); // call the method once
        Dog current; // local storage so we don't call get.() but once
        for (int i = 0; i < contest.getNumberOfContestants(); i++) {
            current = contest.getContestantAt(i);
            age = (contestDate.difference(current.getBirthDate())) / 364.5;
            System.out.printf("%4d   %s%6.2f\n", i, current, age);
        }
    }

    /**
     * Outputs a pleasant little blurb suitable for  
     * publication in a newspaper.
     */
    public void printPressRelease() {
        Competitor winner = contest.getWinner();
        double age = (contest.getContestDate().difference
                        (winner.getBirthDate())) / 364.5;
        
        String timeStr = winner.getTime().getTimeInWords();

        System.out.printf("\n\n"
                + "SoPo Pup Takes Top Honors\n"
                + "    It was a beautiful day here at the park in %s\n"
                + "where the 3rd annual York county agility competition \n"
                + "sponsored by %s was held. \n\n"
                + "    Top honors go to %s,"  
                + " a %6.2f lb %.0f year old %s\n"
                + "who ran the course in %s. %s\n"
                + "is owned by %s, a South Portland resident and SMCC\n"
                + "computer science major.\n\n",
                 contest.getContestLocation(), contest.getContestSponsor(), 
                 winner.getName(), winner.getWeight(), 
                 age ,winner.getBreed(), timeStr, winner.getName(),
                 winner.getOwner());
    }

    /**
     * The driver for the program.
     * @param fileName filename from the command line arguments
     */
    public void run(String fileName) {
        // enroll the competitors
        readCompetitorFile(fileName);
        // print them
        printCompetitors();
        // print a press release
        printPressRelease();
    }

    /**
     * main method for the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: progname inputFIle");
            System.exit(1);
        }
        AgilityCompetitionDriver driver = new AgilityCompetitionDriver();
        driver.run(args[0]);
    }
}
