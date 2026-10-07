
import java.util.Scanner;

public class MonthlyAttendanceAnnouncer {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String id, int fee, int sessions) {
            if (id == null || id.trim().length() < 4)
                throw new IllegalArgumentException("Invalid member ID");
            if (fee <= 0 || sessions < 0)
                throw new IllegalArgumentException("Invalid input");

            memberId = id;
            monthlyFee = fee;
            sessionsAttended = sessions;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String id, int fee, int sessions,
                             String trainer) {
            super(id, fee, sessions);
            trainerName = trainer;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName
                    + " | Sessions: " + sessionsAttended;
        }
    }

    static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {
            result.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;

                result.append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of members: ");
            int n = Integer.parseInt(sc.nextLine());

            if (n < 0) {
                System.out.println("Invalid number of members");
                return;
            }

            GymMember[] members = new GymMember[n];

            for (int i = 0; i < n; i++) {
                System.out.println("\n1. Standard  2. Premium");
                System.out.print("Choose membership type: ");
                int type = Integer.parseInt(sc.nextLine());

                System.out.print("Enter member ID: ");
                String id = sc.nextLine();

                System.out.print("Enter monthly fee: ");
                int fee = Integer.parseInt(sc.nextLine());

                System.out.print("Enter sessions attended: ");
                int sessions = Integer.parseInt(sc.nextLine());

                if (type == 1) {
                    members[i] = new GymMember(id, fee, sessions);
                } else if (type == 2) {
                    System.out.print("Enter trainer name: ");
                    String trainer = sc.nextLine();

                    members[i] = new PremiumMember(
                            id, fee, sessions, trainer);
                } else {
                    throw new IllegalArgumentException(
                            "Invalid membership type");
                }
            }

            System.out.println("\nAnnouncement:");
            System.out.println(batchPrint(members));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}