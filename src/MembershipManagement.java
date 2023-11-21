import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        while (!reader.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            reader.next();
        }
        return reader.nextInt();
    }

    private void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoice() {
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("================================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit):");

        return getIntInput();
    }

    public String addMembers(LinkedList<Member> m) {
        System.out.println("Enter member name:");
        String name = reader.next();

        System.out.println("Select club:");
        printClubOptions();
        int club = getIntInput();

        int memberID = m.isEmpty() ? 1 : m.getLast().getMemberID() + 1;

        System.out.println("Enter fees:");
        double fees = (club >= 1 && club <= 3) ? calculateFees(club) : -1;

        Member mbr;
        if (fees != -1) {
            if (club >= 1 && club <= 3) {
                SingleClubMember singleClubMember = new SingleClubMember('S', memberID, name, fees, club);
                m.add(singleClubMember);
                mbr = singleClubMember;
            } else if (club == 4) {
                Calculator<Integer> cal = (clubID) -> (clubID == 4) ? 1200 : -1;
                int membershipPoints = (int) calculateFees(4);
                MultiClubMember multiClubMember = new MultiClubMember('M', memberID, name, fees, membershipPoints);
                m.add(multiClubMember);
                mbr = multiClubMember;
            } else {
                return "Invalid club selection. Member not added.";
            }

            String mem = mbr.toString();
            return "Member added successfully:\n" + mem;
        } else {
            return "Invalid club selection. Member not added.";
        }
    }

    private double calculateFees(int club) {
        return (club == 1) ? 900 : ((club == 2) ? 950 : ((club == 3) ? 1000 : -1));
    }

    public void removeMember(LinkedList<Member> m) {
        System.out.println("Enter member ID to remove:");
        int memberID = getIntInput();

        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                m.remove(member);
                System.out.println("Member removed successfully.");
                return;
            }
        }

        System.out.println("Member with ID " + memberID + " not found.");
    }

    public void printMemberInfo(LinkedList<Member> m) {
        System.out.println("Enter member ID to display information:");
        int memberID = getIntInput();

        for (Member member : m) {
            if (member.getMemberID() == memberID) {
                System.out.println(member);
                return;
            }
        }

        System.out.println("Member with ID " + memberID + " not found.");
    }
}