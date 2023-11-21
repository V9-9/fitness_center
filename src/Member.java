public class Member {
    private char memberType;
    private int memberID;
    private String name;
    private double fees;

    public Member(char pMemberType, int pMemberID, String pName, double pFees) {
        memberType = pMemberType;
        memberID = pMemberID;
        name = pName;
        fees = pFees;
    }

    public void setMemberType(char pMemberType) {
        memberType = pMemberType;
    }

    public void setMemberID(int pMemberID) {
        memberID = pMemberID;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void setFees(double pFees) {
        fees = pFees;
    }

    public char getMemberType() {
        return memberType;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public double getFees() {
        return fees;
    }

    @Override
    public String toString() {
        return String.format("%c, %d, %s, %.2f", memberType, memberID, name, fees);
    }
}