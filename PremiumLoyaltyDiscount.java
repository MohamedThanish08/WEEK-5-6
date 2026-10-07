
import java.util.Arrays;
import java.util.Scanner;

class GymMemberBase {
    protected String memberId;
    protected int monthlyFee;
    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;

    public GymMemberBase(String id, int fee) {
        if (id == null || id.trim().length() < 4)
            throw new IllegalArgumentException("Invalid member ID");
        if (fee <= 0)
            throw new IllegalArgumentException("Invalid monthly fee");

        memberId = id;
        monthlyFee = fee;
    }

    protected void chargeLateFee(int amount) {
        if (lateFeeCount >= lateFeeHistory.length)
            throw new IllegalStateException("Fee history is full");

        lateFeeHistory[lateFeeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++)
            total += lateFeeHistory[i];
        return total;
    }
}

public class PremiumLoyaltyDiscount {
    static class PremiumMember extends GymMemberBase {
        private String trainerName;

        public PremiumMember(String id, int fee, String trainer) {
            super(id, fee);
            if (trainer == null || trainer.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid trainer name");
            }
            trainerName = trainer.trim();
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter member ID: ");
            String id = sc.nextLine();

            System.out.print("Enter monthly fee: ");
            int fee = Integer.parseInt(sc.nextLine());

            System.out.print("Enter trainer name: ");
            String trainer = sc.nextLine();

            PremiumMember member =
                    new PremiumMember(id, fee, trainer);

            System.out.print("Enter number of late fees (0-10): ");
            int n = Integer.parseInt(sc.nextLine());

            if (n < 0 || n > 10) {
                System.out.println("Invalid number of late fees");
                return;
            }

            for (int i = 0; i < n; i++) {
                System.out.print("Enter late fee amount: ");
                int amount = Integer.parseInt(sc.nextLine());

                if (amount <= 0) {
                    System.out.println("Fee must be positive");
                    i--;
                    continue;
                }

                member.chargeLateFee(amount);
            }

            System.out.println("Trainer: " + member.getTrainerName());
            System.out.println("Total late fees: "
                    + member.getTotalLateFees());

            System.out.println("Fee history: "
                    + Arrays.toString(member.getLateFeeHistory()));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}