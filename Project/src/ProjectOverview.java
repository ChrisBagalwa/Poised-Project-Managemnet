/** This program manages construction projects by storing pending and
 * complete project details, as well as maintaining updates about the project.
 * @author Chris Bagalwa
 * @version  24.0.3, 2022-08-09
 */

// Import the packages needed to display project overview
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

// ProjectOverview class declaration
public class ProjectOverview
{
     /** Use the main method to access the existing projects, add updates,
      * finalize them, or add new projects at runtime. <br>
      *
      * @param args The command line arguments
      * @throws IOException if error occurs
      */
    public static void main(String [] args) throws IOException
    {
        // Define variables used for project and project personnel details.
        String name;
        String telephoneNumber, emailAddress, physicalAddress;
        String projectNumber, projectName, projectType, ERFNumber, deadline;
        double totalFee, totalPaid, balance = 0;
        ProjectData projectList = new ProjectData();
        // Prompt the user to choose from the menu list above
        while (true) {
            System.out.println("""
                    \nProgram menu:
                    1 - Display all the projects
                    2 - Add new a project
                    3 - Edit an existing project
                    4 - Display incomplete projects
                    5 - Display overdue projects
                    0 - exit
                    Enter option:""");

            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            // If user chooses option 1, then display the list of all the projects
            if (option == 1) {
                projectList.printData();
            // Else if the user chooses option 2, then prompt the user to input project details
            } else if (option == 2) {
                Project newProject;
                input.nextLine();
                System.out.println("\nEnter Project number:");
                projectNumber = input.nextLine();
                System.out.println("Enter Project name:");
                projectName = input.nextLine();
                System.out.println("Enter Project type:");
                projectType = input.nextLine();
                System.out.println("Enter building ERF number:");
                ERFNumber = input.nextLine();
                System.out.println("Enter project deadline(yyyy-MM-dd):");
                deadline = input.nextLine();
                while (validDateFormat(deadline) == false) {
                    System.out.println("\nInvalid date format. Try again.");
                    System.out.println("Enter project deadline(yyyy-MM-dd):");
                    deadline = input.nextLine();
                }
                while (true) {
                    try {
                        System.out.println("Enter overall Project Fee:");
                        totalFee = input.nextDouble();
                        System.out.println("Enter total paid to date:");
                        totalPaid = input.nextDouble();
                        // New project Object
                        newProject = new Project(projectNumber,projectName,projectType,ERFNumber,deadline,totalFee,totalPaid);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input!\nPlease enter valid amount.");
                        input.nextLine();
                    }
                }
                // created client, architect and contractor objects, they're accessible out of the loops
                Client client = null;
                Architect architect = null;
                Contractor contractor;

                // loop used to add details of project client and architect
                System.out.println("\nClient Details".toUpperCase());
                input.nextLine();
                int loop = 0;
                while (true) {
                    loop++;
                    System.out.println("\nEnter name:");
                    name = input.nextLine();
                    System.out.println("Enter telephone number:");
                    telephoneNumber = input.nextLine();
                    System.out.println("Enter email address:");
                    emailAddress = input.nextLine();
                    System.out.println("Enter physical address:");
                    physicalAddress = input.nextLine();

                    if (loop == 1) {
                        client = new Client(name, telephoneNumber, emailAddress, physicalAddress);
                        if (projectName.isBlank()) {
                            // If project name not provided Project type & client name used in place of.
                            newProject.projectName = newProject.projectType + " " + name;
                        }
                        System.out.println("\nProject Architect Details".toUpperCase());
                    }
                    if (loop == 2) {
                        architect = new Architect(name, telephoneNumber, emailAddress, physicalAddress);
                        System.out.println("\nProject Contractor Details".toUpperCase());
                    }
                    if (loop == 3) {
                        contractor = new Contractor(name, telephoneNumber, emailAddress, physicalAddress);
                        break;
                    }
                }
                // Assert used to declare null values as false.
                System.out.println("\nInformation Successfully Saved!\n");
                System.out.println(newProject.projectDetails() + "\n");
                for (String s : Arrays.asList(client.toString(), architect.toString(), contractor.toString())) {
                    System.out.println(s + "\n");
                }

                projectList.addNewProject(newProject, client, architect, contractor);

            // Else if the user chooses option 3, then prompt the user to input specifies project to edit
            } else if (option == 3) {
                input.nextLine();
                String projectToEdit;
                System.out.println("\nEnter project number:");
                projectToEdit = input.nextLine();
                projectList.showProject(projectToEdit);
                while (true) {
                    System.out.println("""
                            \n
                            1 - Edit Contractor's details
                            2 - Edit project deadline
                            3 - Edit Amount paid to date
                            4 - Finalize project
                            0 - Return to main menu
                            """);
                    int choice = input.nextInt();
                    if (choice == 1) {
                        input.nextLine();
                        System.out.println("\nEnter contractor's new telephone number:");
                        telephoneNumber = input.nextLine();
                        System.out.println("Enter contractor's new email Address: ");
                        emailAddress = input.nextLine();
                        projectList.changeContractorDetails(projectToEdit, telephoneNumber, emailAddress);
                    }
                    else if (choice == 2) {
                        input.nextLine();
                        System.out.println("\nEnter New deadline(yyyy-MM-dd):");
                        deadline = input.nextLine();
                        while ((validDateFormat(deadline)) == false) {
                            System.out.println("\nInvalid date format. Try again.");
                            System.out.println(validDateFormat(deadline));
                        }
                        projectList.changeDeadline(projectToEdit, deadline);

                    } else if (choice == 3) {
                        while (true) {
                            try {
                                System.out.println("\nEnter amount paid:");
                                Double paidAmount = input.nextDouble();
                                projectList.changeAmountPaid(projectToEdit, paidAmount);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid Input!\nPlease enter valid amount.");
                                input.nextLine();
                            }
                        }
                    } else if (choice == 4) {
                        projectList.finalizeProject(projectToEdit);
                    } else if (choice == 0) {
                        break;
                    } else {
                        System.out.println("Invalid option!");
                    }
                }
            // Else if the user chooses option 4, then Display incomplete projects
            } else if (option == 4) {
                projectList.incompleteProjects();
            // Else if the user chooses option 5, then Display overdue projects
            } else if (option == 5) {
                try {
                    projectList.overdueProjects();
                } catch (ParseException e) {
                    System.out.println("Parse Exception occurred!");
                }
            // Else if the user chooses option 0, Logout
            } else if (option == 0) {
                System.out.println("\nLogout Successful!");
                break;
            }
        }
        // Writes updated project object list, to projects txt file
        projectList.writeToFile();
    }
    /** Define the validDateFormat method that validates if user inputs
     * the correct date format for the project deadlines
     *
     * @param deadline formats the string to date
     * @return boolean value if date is valid
     */
    public static boolean validDateFormat(String deadline) {
        boolean valid;
        try {
            String dateFormat = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
                    .withResolverStyle(ResolverStyle.LENIENT);
            LocalDate date = LocalDate.parse(deadline, dateFormatter);
            valid = true;
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }
}