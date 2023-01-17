/**
 * This class is used to create the client object that stores information about the client
 *
 * @author Chris Bagalwa
 * @version  24.0.3, 2022-08-09
 */
public class Client {
    // Attributes
    String name;
    String telephoneNumber;
    String emailAddress;
    String physicalAddress;

    /** Client constructor with four arguments
     *
     * @param name              String contains the name of the client
     * @param telephoneNumber   String contains the telephone number of the client
     * @param emailAddress      String contains the email address of the client
     * @param physicalAddress   String contains the physical address of the client
     */
    public Client(String name, String telephoneNumber, String emailAddress, String physicalAddress)
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
    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }
    // Define the method that will return the argument email as a string
    public String getEmailAddress()
    {
        return emailAddress;
    }
    // Define the method that will return the argument physical address as a string
    public String getPhysiclAddress() {return physicalAddress;}

    /** @return All the arguments in the other methods
     */
    // Define the toString method that will return all the arguments in other methods defined above
    public String toString()
    {
        // Create format to Display the client's details
        String output = "Client name: " + name + "\n";
        output += "Telephone number: " + telephoneNumber + "\n";
        output += "Email address: " + emailAddress + "\n";
        output += "Physical address: " + physicalAddress + "\n";
        return output;
    }
}
