import java.util.Scanner;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Member ID");
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Monthly fee must be positive");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: " + sessionsAttended
        );
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee,
                         String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Premium Member | Trainer: " + trainerName +
            " | Sessions: " + getSessionsAttended()
        );
    }
}

public class GymMembershipFoundation {

    public static String signUpBatch(
            String[] memberIds, int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String memberId : memberIds) {

            try {
                new GymMember(memberId, monthlyFee);
                signedUp++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp +
               " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine();

        System.out.print("Enter monthly fee: ");
        int monthlyFee = sc.nextInt();
        sc.nextLine();

        try {
            GymMember member =
                new GymMember(memberId, monthlyFee);

            System.out.println("Member created successfully.");

            System.out.print("Enter number of sessions attended: ");
            int sessions = sc.nextInt();

            for (int i = 0; i < sessions; i++) {
                member.attendSession();
            }

            System.out.println(
                "Sessions Attended: " +
                member.getSessionsAttended()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        System.out.print("Enter number of members for batch signup: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] memberIds = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter member ID " + (i + 1) + ": ");
            memberIds[i] = sc.nextLine();
        }

        System.out.print("Enter monthly fee for batch: ");
        int batchFee = sc.nextInt();

        System.out.println(
            signUpBatch(memberIds, batchFee)
        );

        sc.close();
    }
}