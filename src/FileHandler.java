import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileHandler {
    private static final String FILE_PATH = "members.csv";
    private static final String TEMP_FILE_PATH = "members.temp";

    public LinkedList<Member> readFile() throws CsvValidationException {
        LinkedList<Member> members = new LinkedList<>();

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] record;
            while ((record = reader.readNext()) != null) {
                char memberType = record[0].charAt(0);
                int memberID = Integer.parseInt(record[1]);
                String name = record[2];
                double fees = Double.parseDouble(record[3]);

                if (memberType == 'S') {
                    String clubAsString = record[4];
                    int club = Integer.parseInt(clubAsString);
                    SingleClubMember singleClubMember = new SingleClubMember(memberType, memberID, name, fees, club);
                    members.add(singleClubMember);
                } else if (memberType == 'M') {
                    MultiClubMember multiClubMember = new MultiClubMember(memberType, memberID, name, fees, 0);
                    int membershipPoints = Integer.parseInt(record[4]);
                    multiClubMember.setMembershipPoints(membershipPoints);
                    members.add(multiClubMember);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return members;
    }

    public void appendFile(String mem) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true);
             CSVWriter writer = new CSVWriter(fileWriter)) {
            String[] record = mem.split(",");
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteFile(LinkedList<Member> m) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(TEMP_FILE_PATH))) {
            for (Member member : m) {
                String[] record;
                if (member.getMemberType() == 'S') {
                    SingleClubMember singleClubMember = (SingleClubMember) member;
                    record = new String[]{String.valueOf(member.getMemberType()), String.valueOf(member.getMemberID()), member.getName(), String.valueOf(member.getFees()), String.valueOf(singleClubMember.getClub())};
                } else {
                    MultiClubMember multiClubMember = (MultiClubMember) member;
                    record = new String[]{String.valueOf(member.getMemberType()), String.valueOf(member.getMemberID()), member.getName(), String.valueOf(member.getFees()), String.valueOf(multiClubMember.getMembershipPoints())};
                }
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File originalFile = new File(FILE_PATH);
        File tempFile = new File(TEMP_FILE_PATH);

        if (tempFile.renameTo(originalFile)) {
            System.out.println("The file has been successfully overwritten.");
        } else {
            System.out.println("Failed to overwrite the file.");
        }
    }
}