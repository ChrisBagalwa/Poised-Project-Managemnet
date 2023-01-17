/**
 * This class is used to create the project object that stores information about the project
 *
 * @author Chris Bagalwa
 * @version  24.0.3, 2022-08-09
 */
public class Project {
    // Attributes
    String projectNumber;
    String projectName;
    String projectType;
    String ERFNumber;
    String deadline;
    double totalFee;
    double totalPaid;

    // Project constructor with six arguments
    /**
     * Project constructor. <br>
     *
     * @param projectNumber String contains the project number
     * @param ERFNumber     String contains ERF number for the building
     * @param projectName   String contains the project name
     * @param projectType   String determines what type of project it is i.e. Apartment, house etc.
     */
    public Project(String projectNumber,String projectName, String projectType, String ERFNumber, String deadline, double totalFee, double totalPaid)
    {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.projectType = projectType;
        this.ERFNumber = ERFNumber;
        this.deadline = deadline;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
    }

    // Define the method that will return the argument project number as a string
    public String getProjectNumber()
    {
        return projectNumber;
    }
    // Define the method that will return the argument project name as a string
    public String getProjectName()
    {
        return projectName;
    }
    // Define the method that will return the argument project type as a string
    public String getProjectType()
    {
        return projectType;
    }
    // Define the method that will return the argument ERF number as a string
    public String getERFNumber()
    {
        return ERFNumber;
    }
    // Define the method that will return the argument client as a string
    // Define the method that will return the argument deadline as a string
    public String getDeadline()
    {
        return deadline;
    }

    /** @return All the arguments in the other methods
     */
    // Define the method toString that will return all the arguments defined above
    public String projectDetails()
    {
        String output = "Project number: " + projectNumber + "\n";
        output += "Project name: " + projectName + "\n";
        output += "Project type: " + projectType + "\n";
        output += "ERF number: " + ERFNumber + "\n";
        output += "Project deadline: " + deadline + "\n";
        output += "Total Project fee: R" + totalFee + "\n";
        output += "Total amount paid to date: R" + totalPaid + "\n";
        return output;
    }
}
