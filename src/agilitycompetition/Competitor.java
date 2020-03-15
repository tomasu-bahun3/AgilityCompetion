/*
 *              Revision History
 * ***************************************************************
 * 
 */
package agilitycompetition;

/**
 *
 * @author aapplin
 */
public class Competitor extends Pet {

    private final ElapsedTime time;
    
    /**
     * Parameterized Constructor for Competitor
     * @param birthDate the dog's date of birth (Date)
     * @param breed the dog's breed
     * @param weight the dog's weight
     * @param name the pet's name
     * @param owner the pet's owner's name
     * @param time the time that the competitor ran the course in
     */
    public Competitor(Date birthDate, String breed, double weight,
                      String name, String owner, ElapsedTime time) {
        super(birthDate, breed, weight, name, owner); // call Pet's constructor  
        this.time = time;
    }

    /**
     * Accessor for the course time
     *
     * @return the value of course time
     */
    public ElapsedTime getTime() {
        return time;
    }

    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file.
     *
     * @return a formatted string representing the values of the attributes for
     * a dog object.
     */
    @Override
    public String toString() {
        return String.format("%s%-12s", super.toString(), time.toString());
    }

    /**
     * compareTo (abstract method of the Comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the competitors belonging to
     * some Java collection.
     *
     * @param that is the Dog object we are comparing this one to
     * @return a negative integer, zero, or a positive integer if this object
     * comes before, is equal to, or comes after the specified object.
     */
    @Override
    public int compareTo(Dog that) {
        // some constants for clarity
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        // shouldn't be any null objects, but if there are
        // put them at the end
        if (that == null) {
            return AFTER;
        }
        //this optimization is usually worthwhile, and can
        //always be added - if the addresses are the same... they are equal
        if (this == that) {
            return EQUAL;
        }
        // we sort by time. There is a compareTo() 
        // in the ElapsedTime class that sorts on the 4 fields.
        return this.time.compareTo(((Competitor) that).getTime());
    }

    /**
     * Unit Test for the Competitor class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // tests Dog class only. to run, right-click 
        // and choose Run File
        Date date = new Date(7, 26, 2006);
        Competitor dog = new Competitor(date, "Toy Poodle", 10.2, "Eudora",
                "Anne", new ElapsedTime(0, 2, 35, 40));
        System.out.println(dog);
    }
}
