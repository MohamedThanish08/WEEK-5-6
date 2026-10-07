
import java.util.Scanner;

public class ThreeTiersOfGymMembership {
    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().length() < 4)
                throw new IllegalArgumentException("Invalid member ID");
            if (monthlyFee <= 0)
                throw new IllegalArgumentException("Invalid fee");

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            sessionsAttended = 0;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public void displayInfo() {
            System.out.println("Standard Member | Sessions: "
                    + sessionsAttended);
        }
    }

    static class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(String id, int fee, String trainer) {
            super(id, fee);
            trainerName = trainer;
        }

        @Override
        public void displayInfo() {
            System.out.println("Premium Member | Trainer: "
                    + trainerName + " | Sessions: "
                    + getSessionsAttended());
        }
    }

    static class EliteMember extends PremiumMember {
        private String lockerNumber;

        public EliteMember(String id, int fee, String trainer,
                           String locker) {
            super(id, fee, trainer);
            lockerNumber = locker;
        }

        @Override
        public void displayInfo() {
            System.out.println("Elite Member | Trainer: "
                    + trainerName + " | Locker: " + lockerNumber
                    + " | Sessions: " + getSessionsAttended());
        }
    }

    static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(String id, int fee, String name) {
            super(id, fee);
            className = name;
        }

        @Override
        public void displayInfo() {
            System.out.println("Group Class Member | Class: "
                    + className + " | Sessions: "
                    + getSessionsAttended());
        }
    }

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember)
            return "Multilevel descendant (3 generations deep)";
        if (member instanceof GroupClassMember)
            return "Hierarchical sibling (independent branch)";
        return "Standard/Premium member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members)
            total += member.getSessionsAttended();
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of members: ");
        int n = sc.nextInt();
        sc.nextLine();

        GymMember[] members = new GymMember[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n1.Standard  2.Premium"
                    + "  3.Elite  4.Group Class");
            System.out.print("Choose membership type: ");
            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter member ID: ");
            String id = sc.nextLine();

            System.out.print("Enter monthly fee: ");
            int fee = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter sessions attended: ");
            int sessions = sc.nextInt();
            sc.nextLine();

            try {
                switch (type) {
                    case 1:
                        members[i] = new GymMember(id, fee);
                        break;
                    case 2:
                        System.out.print("Enter trainer name: ");
                        String trainer = sc.nextLine();
                        members[i] = new PremiumMember(id, fee, trainer);
                        break;
                    case 3:
                        System.out.print("Enter trainer name: ");
                        trainer = sc.nextLine();
                        System.out.print("Enter locker number: ");
                        String locker = sc.nextLine();
                        members[i] = new EliteMember(id, fee, trainer, locker);
                        break;
                    case 4:
                        System.out.print("Enter class name: ");
                        String name = sc.nextLine();
                        members[i] = new GroupClassMember(id, fee, name);
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Invalid membership type");
                }

                for (int j = 0; j < sessions; j++)
                    members[i].attendSession();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                members[i] = new GymMember("TEMP", 1);
            }
        }

        System.out.println("\n--- Membership Details ---");
        for (GymMember member : members) {
            member.displayInfo();
            System.out.println(classifyGeneration(member));
        }

        System.out.println("Total sessions attended: "
                + getTotalSessionsAttended(members));

        sc.close();
    }
}