// Import the packages needed to store the information
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This class is used to create the ProjectData object that stores information that can be accessed or modified
 *
 * @author Chris Bagalwa
 * @version  24.0.3, 2022-08-09
 */
public class ProjectData {
    /** Create the arraylist object which stores all the existing projects
    and the arrayList object which stores completed projects **/
    ArrayList<Object> projectList = new ArrayList<>();
    ArrayList<Object> completeProjects = new ArrayList<>();


    /**
     * Array constructor <br>
     * Creates a two-arraylist object, the first which contains all existing
     * projects and the second for complete projects which are removed from the
     * first array once the program is terminated. <br>
     *
     * @throws IOException Exception thrown to notify user if the program can't access the projectData file
     */
    public ProjectData() throws IOException {
        accessFile();
    }
    /**
     * The accessFile method accesses the projects.txt file and
     * use the throws FileNotFoundException to notify the user if the projects.txt is not found
     *
     * @throws FileNotFoundException Exception thrown if file is not found or is misplaced.
     */
    public void accessFile() throws FileNotFoundException {
        try{
            Scanner projectsData = new Scanner(new File("projects.txt"));
            while (projectsData.hasNextLine()) {
                projectList.add(projectsData.nextLine());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
    }
    /**
     * The printData method Displays the projects that are stored in the projects data
    */
    public void printData() {
        int count = 0;
        System.out.println("Project List:\n");
        for (Object project : projectList) {
            count++;
            System.out.println(count + ". " + project);
        }
    }
    /**
     * The writeToFile method that add new project information on the projects.txt and
     * remove completed projects from the projects.txt
     *
     * @throws IOException that notifies the user if there is a problem while writing to file
     */
    public void writeToFile() throws IOException {
        projectList.removeAll(completeProjects);
        Formatter projectsData = new Formatter("projects.txt");
        for (Object project : projectList) {
            projectsData.format(project.toString());
            projectsData.format(System.lineSeparator());
        }
        projectsData.close();
    }

    /**
     * This method updates an existing project's contractor's contact information
     * and displays the updated details. <br>
     *
     * @param projectNumber  String contains name of project, so it can be found in the list.
     * @param telephoneNumber String contains contractor's new telephone number.
     * @param emailAddress       String contains contractor's new email address.
     */

    public void changeContractorDetails(String projectNumber, String telephoneNumber, String emailAddress) {
        Object newProjectObject;
        String[] objectDetails = new String[18];

        for (Object project : projectList) {
            // regionMatches() method enables string indexing of project string, to retrieve specified project ignoring character case
            if (projectNumber.regionMatches(true, 0, String.valueOf(project), 0, projectNumber.length())) {
                objectDetails = ((String.valueOf(project)).split(", "));
                StringBuilder newDetails = new StringBuilder();
                int pos = projectList.indexOf(project);

                objectDetails[16] = telephoneNumber;
                objectDetails[17] = emailAddress;

                for (String detail : objectDetails) {
                    newDetails.append(detail).append(", ");
                }
                newProjectObject = newDetails.toString();
                projectList.set(pos, newProjectObject);

            }
        }
        System.out.println("\nUPDATED CONTRACTOR DETAILS:\n" + objectDetails[19] + "\nName: " + objectDetails[15]
                + "\nTelephone Number: " + objectDetails[16] + "\nEmail address: " + objectDetails[17] + "\nPhysical Address: " + objectDetails[18]);

    }

    /**
     * The changeDeadline method updates the deadline of an existing project.
     */
    public void changeDeadline(String projectNumber, String deadline) {
        Object newProjectObject;
        String[] objectDetails;

        for (Object project : projectList) {
            // regionMatches() method enables string indexing of project string, to retrieve specified project
            // ignoring character case
            if (projectNumber.regionMatches(true, 0, String.valueOf(project), 0, projectNumber.length())) {
                objectDetails = ((String.valueOf(project)).split(", "));
                String newDetails = "";
                int pos = projectList.indexOf(project);
                objectDetails[4] = deadline;
                for (String detail : objectDetails) {
                    newDetails = detail + ", ";
                }
                newProjectObject = newDetails;
                projectList.set(pos, newProjectObject);
            }
        }
        System.out.println("\nDeadline changed: " + deadline);
    }
    /**
     * The changeAmountPaid method updates the amount a client has paid for their project fees.
     */
    public void changeAmountPaid(String projectNumber, Double paidAmount) {
        Object newProjectObject;
        String[] objectDetails = new String[18];
        for (Object project : projectList) {
            if (projectNumber.regionMatches(true, 0, String.valueOf(project), 0, projectNumber.length())) {
                objectDetails = ((String.valueOf(project)).split(", "));
                StringBuilder newDetails = new StringBuilder();
                int pos = projectList.indexOf(project);
                // Adds new paid amount to amount already paid
                objectDetails[6] = String.valueOf(Double.parseDouble(objectDetails[6]) + paidAmount);
                for (String detail : objectDetails) {
                    newDetails.append(detail).append(", ");
                }
                newProjectObject = newDetails.toString();
                projectList.set(pos, newProjectObject);
            }
        }
        System.out.println(
                "PROJECT FEES UPDATED!\nCurrent Amount Paid: R" + objectDetails[6] + "\nTotal Fee: R" + objectDetails[5]);
    }
    /**
     * The incompleteProjects method returns a list of all incomplete projects in the projects Data
     */
    public void incompleteProjects() {
        int count = 0;
        System.out.println("\nIncomplete Projects List:\n");
        for (Object project : projectList) {
            count++;
            if (String.valueOf(project).endsWith("not finalized, ")) {
                System.out.println(count + ". " + project);
            }
        }
    }
    /** The finalizeProject method that finalizes projects and returns an invoice if the user hasn't fully paid for the project.
     * If they have fully paid no invoice is provided and the object is moved to a completed projects file.
     *
     * @throws IOException Exception thrown if an error occurs when writing to the file.
     */
    public void finalizeProject(String projectNumber) throws IOException {
        ArrayList<Object> finalizedProjects = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Object newProjectObject;
        String[] objectDetails;
        Date currentDate = new Date();

        for (Object project : projectList) {
            if (projectNumber.regionMatches(true, 0, String.valueOf(project), 0, projectNumber.length())) {
                objectDetails = ((String.valueOf(project)).split(", "));
                StringBuilder newDetails = new StringBuilder();

                objectDetails[18] = "finalized";

                for (String detail : objectDetails) {
                    newDetails.append(detail).append(", ");
                }
                // Append date on which project is finalized
                newDetails.append(sdf.format(currentDate));
                newProjectObject = newDetails.toString();
                completeProjects.add(project);
                finalizedProjects.add(newProjectObject);

                // write finalized projects to new file.
                FileWriter newFile;
                newFile = new FileWriter("Completed project.txt", true);
                for (Object finalizedProject : finalizedProjects) {
                    newFile.write(finalizedProject.toString());
                    newFile.write(System.lineSeparator());
                }
                newFile.close();

                // Display the invoice
                double balanceOutstanding = Double.parseDouble(objectDetails[5]) - Double.parseDouble(objectDetails[6]);
                if (balanceOutstanding == 0) {
                    System.out.print("\nProject Finalized!\nNo invoice available, balance fully paid.\n");
                } else if (balanceOutstanding > 0) {
                    System.out.print("\nProject Finalized!\nINVOICE\nProject " + objectDetails[0] + "\nClient name: "
                            + objectDetails[7] + "\nTotal Project Fees: R" + objectDetails[5]
                            + "\nTotal Paid: R" + objectDetails[6] + "\nBalance Outstanding: R" + balanceOutstanding
                            + "\n");
                }
            }
        }
    }

    /**
     * The overdueProjects method returns a list of overdue projects in the project's dataset.
     * */
    public void overdueProjects() throws ParseException {
        ArrayList<Object> dueProjectsList = new ArrayList<>();
        String[] objectDetails;
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        // indexes deadline and compares it to current date
        for (Object project : projectList) {
            objectDetails = ((String.valueOf(project)).split(", "));
            String dateStr = objectDetails[4];
            Date dueDate = sdf.parse(dateStr);
            if (currentDate.after(dueDate)) {
                dueProjectsList.add(project);
            }
        }
        System.out.println("\nOverDue Projects List:\n");
        for (Object dueProject : dueProjectsList) {
            count++;
            System.out.println(count + ". " + dueProject);
        }
    }
    /** Define the showProject method that displays an existing project's properly formatted information
     * when user chooses to edit the project.
     */
    public void showProject(String projectNumber) {
        String[] objectDetails = new String[18];
        for (Object project : projectList) {
            if (projectNumber.regionMatches(true, 0, String.valueOf(project), 0, projectNumber.length())) {
                objectDetails = ((String.valueOf(project)).split(", "));
            }
        }
        System.out.println("\nProject number: " + objectDetails[0] +
                "\nProject name: " + objectDetails[1] +
                "\nProject type: " + objectDetails[2] +
                "\nERF number: " + objectDetails[3] +
                "\nProject deadline: " + objectDetails[4] +
                "\nTotal Project fee: R" + objectDetails[5] +
                "\nTotal amount paid to date: R" + objectDetails[6]);

        System.out.println("\nClient name: " + objectDetails[7] +
                "\nTelephone number: " + objectDetails[8] +
                "\nEmail address: " + objectDetails[9] +
                "\nPhysical address: " + objectDetails[10]);

        System.out.println("\nArchitect name: " + objectDetails[11] +
                "\nTelephone number: " + objectDetails[12] +
                "\nEmail address: " + objectDetails[13] +
                "\nPhysical Address: " + objectDetails[14]);

        System.out.println("\nContractor name: " + objectDetails[15] +
                "\nTelephone number: " + objectDetails[16] +
                "\nEmail address: " + objectDetails[17] +
                "\nPhysical Address: " + objectDetails[18]);

    }

    /**
     * The addNewProject method that adds a new project to list of existing projects.
     */
    public void addNewProject(Project newProject, Client client, Architect architect, Contractor contractor) {

        String projectDetails = "";
        projectDetails += newProject.projectNumber + ", ";
        projectDetails += newProject.projectName + ", ";
        projectDetails += newProject.projectType + ", ";
        projectDetails += newProject.ERFNumber + ", ";
        projectDetails += newProject.deadline + ", ";
        projectDetails += newProject.totalFee + ", ";
        projectDetails += newProject.totalPaid + ", ";

        projectDetails += client.name + ", ";
        projectDetails += client.telephoneNumber + ", ";
        projectDetails += client.emailAddress + ", ";
        projectDetails += client.physicalAddress + ", ";

        projectDetails += architect.name + ", ";
        projectDetails += architect.telephoneNumber + ", ";
        projectDetails += architect.emailAddress + ", ";
        projectDetails += architect.physicalAddress + ", ";

        projectDetails += contractor.name + ", ";
        projectDetails += contractor.telephoneNumber + ", ";
        projectDetails += contractor.emailAddress + ", ";
        projectDetails += contractor.physicalAddress + ", ";

        // Project completion indicator
        projectDetails += "not finalized, ";
        Object newProjectObject = projectDetails;
        projectList.add(newProjectObject);
    }
}
