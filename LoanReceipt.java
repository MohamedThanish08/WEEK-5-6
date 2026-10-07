import java.util.*;

class LoanReceipt {

    protected final String memberId;
    protected final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] copy = bookIds.clone();

        if (index >= 0 && index < copy.length) {
            copy[index] = newId;
        }

        return new LoanReceipt(memberId, copy);
    }
}


class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


class ImmutableLoanReceiptNightlyCirculationLedger {

    private static final String branchCode;

    static {
        branchCode = "PT-BRANCH-01";
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return "Branch " + branchCode + " | " +
               processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter member ID: ");
        String memberId = sc.nextLine();

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] bookIds = new String[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                "Enter book ID " + (i + 1) + ": "
            );

            bookIds[i] = sc.nextLine();
        }

        LoanReceipt receipt =
            new LoanReceipt(memberId, bookIds);

        System.out.println("Book IDs:");

        for (String id : receipt.getBookIds()) {
            System.out.println(id);
        }

        System.out.print("Enter correction index: ");
        int index = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new book ID: ");
        String newId = sc.nextLine();

        LoanReceipt corrected =
            receipt.withCorrectedBookId(index, newId);

        System.out.println("Original:");

        for (String id : receipt.getBookIds()) {
            System.out.print(id + " ");
        }

        System.out.println("\nCorrected:");

        for (String id : corrected.getBookIds()) {
            System.out.print(id + " ");
        }

        sc.close();
    }
}