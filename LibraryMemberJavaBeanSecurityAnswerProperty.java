import java.util.*;

public class LibraryMemberJavaBeanSecurityAnswerProperty {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    private boolean membershipIdSet = false;

    public LibraryMemberJavaBeanSecurityAnswerProperty() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (!membershipIdSet) {
            membershipId = id;
            membershipIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String answer) {

        securityAnswer =
            Integer.toHexString(answer.hashCode());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryMemberJavaBeanSecurityAnswerProperty member =
            new LibraryMemberJavaBeanSecurityAnswerProperty();

        System.out.print("Enter membership ID: ");
        String id = sc.nextLine();
        member.setMembershipId(id);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        member.setName(name);

        System.out.print("Is premium member? ");
        boolean premium = sc.nextBoolean();
        member.setPremiumMember(premium);

        sc.nextLine();

        System.out.print("Enter security answer: ");
        String answer = sc.nextLine();
        member.setSecurityAnswer(answer);

        System.out.println("Membership ID: " +
                           member.getMembershipId());

        System.out.println("Name: " +
                           member.getName());

        System.out.println("Premium Member: " +
                           member.isPremiumMember());

        System.out.println("Security Answer: " +
                           member.getSecurityAnswer());

        sc.close();
    }
}