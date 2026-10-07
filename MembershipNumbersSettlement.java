
import java.util.Scanner;

public class MembershipNumbersSettlement {
    static class GymMember {
        private static int membersEnrolled = 2000;

        private final String membershipNumber;
        protected int monthlyFee;
        private int feesPaid;
        private String paymentMode;

        public GymMember(int monthlyFee) {
            if (monthlyFee <= 0)
                throw new IllegalArgumentException(
                        "Monthly fee must be positive");

            membersEnrolled++;
            membershipNumber = "GYM-" + membersEnrolled;
            this.monthlyFee = monthlyFee;
            feesPaid = 0;
            paymentMode = "Not specified";
        }

        public void payFee(int amount) {
            if (amount <= 0)
                throw new IllegalArgumentException(
                        "Payment must be positive");

            feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            paymentMode = mode;
            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }

        public String getMembershipNumber() {
            return membershipNumber;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled - 2000;
        }

        public static boolean isValidReferralCode(String code) {
            if (code == null || code.length() != 4)
                return false;

            return code.charAt(0) == 'G'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }
    }

    static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    public static String processWeeklyCheckIn(
            GymMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember)
                group++;
            else
                individual++;
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter monthly fee: ");
            int fee = Integer.parseInt(sc.nextLine());

            GymMember member = new GymMember(fee);

            System.out.println("Membership number: "
                    + member.getMembershipNumber());

            System.out.println("Members enrolled: "
                    + GymMember.getMembersEnrolled());

            System.out.print("Enter payment amount: ");
            int amount1 = Integer.parseInt(sc.nextLine());
            member.payFee(amount1);

            System.out.print("Enter second payment amount: ");
            int amount2 = Integer.parseInt(sc.nextLine());

            System.out.print("Enter payment mode: ");
            String mode = sc.nextLine();

            member.payFee(amount2, mode);

            System.out.println("Total fees paid: "
                    + member.getFeesPaid());

            System.out.println("Last payment mode: "
                    + member.getPaymentMode());

            System.out.print("Enter referral code: ");
            String code = sc.nextLine();

            System.out.println("Valid referral code: "
                    + GymMember.isValidReferralCode(code));

            System.out.print("\nEnter number of weekly entries: ");
            int n = Integer.parseInt(sc.nextLine());

            if (n < 0)
                throw new IllegalArgumentException(
                        "Invalid number of entries");

            GymMember[] members = new GymMember[n];

            for (int i = 0; i < n; i++) {
                System.out.println(
                        "\n1. Individual  2. Group Class  3. Null");
                System.out.print("Choose entry type: ");
                int type = Integer.parseInt(sc.nextLine());

                if (type == 1) {
                    System.out.print("Enter monthly fee: ");
                    int individualFee =
                            Integer.parseInt(sc.nextLine());

                    members[i] = new GymMember(individualFee);

                } else if (type == 2) {
                    System.out.print("Enter monthly fee: ");
                    int groupFee = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter class name: ");
                    String className = sc.nextLine();

                    members[i] =
                            new GroupClassMember(groupFee, className);

                } else if (type == 3) {
                    members[i] = null;

                } else {
                    throw new IllegalArgumentException(
                            "Invalid entry type");
                }
            }

            System.out.println("\nWeekly check-in result:");
            System.out.println(processWeeklyCheckIn(members));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}