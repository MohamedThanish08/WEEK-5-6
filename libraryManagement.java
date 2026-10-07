import java.util.Arrays;
import java.util.Scanner;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory;
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException(
                "Borrow limit must be positive"
            );
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        fineHistory = new int[10];
        fineCount = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {

        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);

        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be empty");
        }

        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}

public class libraryManagement{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter member ID: ");
        String id = sc.nextLine();

        System.out.print("Enter borrow limit: ");
        int limit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        StudentMember student =
                new StudentMember(id, limit, course);

        System.out.print("Enter fine amount: ");
        int fine = sc.nextInt();

        student.chargeFine(fine);

        System.out.println(
            "Total Fine: " + student.getTotalFine()
        );

        System.out.println(
            "Fine History: "
            + Arrays.toString(student.getFineHistory())
        );

        int[] history = student.getFineHistory();

        if (history.length > 0) {
            history[0] = 999;
        }

        System.out.println(
            "Actual Fine History: "
            + Arrays.toString(student.getFineHistory())
        );

        sc.close();
    }
}