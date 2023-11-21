import com.opencsv.exceptions.CsvValidationException;

import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) throws CsvValidationException {
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();
        int choice;

        do {
            System.out.println("WELCOME TO OZONE FITNESS CENTER");
            System.out.println("================================");
            System.out.println("1) Add Member");
            System.out.println("2) Remove Member");
            System.out.println("3) Display Member Information");
            System.out.println("Please select an option (or Enter -1 to quit):");

            choice = mm.getChoice();

            switch (choice) {
                case 1:
                    mem = mm.addMembers(members);
                    fh.appendFile(mem);
                    break;
                case 2:
                    mm.removeMember(members);
                    fh.overwriteFile(members);
                    break;
                case 3:
                    mm.printMemberInfo(members);
                    break;
                case -1:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != -1);
    }
}