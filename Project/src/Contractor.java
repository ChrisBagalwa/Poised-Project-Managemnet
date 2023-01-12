/**
 * This class is used to create the Contractor object that stores information about the Architect
 *
 * @author Chris Bagalwa
 * @version  24.0.3, 2022-08-09
 */
public class Contractor {
    // Attributes
    String name;
    String telephoneNumber;
    String emailAddress;
    String physicalAddress;

    /** Contractor constructor with four argument
     *
     * @param name              String contains the name of the Contractor
     * @param telephoneNumber   String contains the telephone number of the Contractor
     * @param emailAddress      String contains the email address of the Contractor
     * @param physicalAddress   String contains the physical address of the Contractor
     */
    public Contractor(String name, String telephoneNumber, String emailAddress, String physicalAddress)
    {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    // Define the method that will return the argument name as a string
    public String getName()
    {
        return name;
    }
    // Define the method that will return the argument telephone number as a string
    public String getTelephone()
    {
        return telephoneNumber;
    }
    // Define the method that will return the argument email address as a string
    public String getEmailAddress()
    {
        return emailAddress;
    }
    // Define the method that will return the argument physical address as a string
    public String getPhysiclAddress()
    {
        return physicalAddress;
    }


    /** @return All the arguments in the other methods
     */
    // Define the toString method that will return all the arguments in other methods defined above
    public String toString()
    {
        String output = "Contractor Name: " + name + "\n";
        output += "Telephone number: " + telephoneNumber + "\n";
        output += "Email address: " + emailAddress + "\n";
        output += "Physical address: " + physicalAddress + "\n";
        return output;
    }
}

