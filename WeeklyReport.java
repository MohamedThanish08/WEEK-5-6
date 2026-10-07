import java.util.Scanner;

public class WeeklyReport {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            if (borrowLimit <= 0) {
                throw new IllegalArgumentException("Borrow limit must be positive");
            }

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public String displayInfo() {
            return "General | Books: " + booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {

        private final String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
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
        public String displayInfo() {
            return "Student | Course: " + course + " | Books: " + booksBorrowed;
        }
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            if (member == null) {
                continue;
            }

            report.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append(" [Course via downcast: ").append(student.getCourse()).append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter general member ID: ");
        String generalId = sc.nextLine();

        System.out.print("Enter general borrow limit: ");
        int generalLimit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter student member ID: ");
        String studentId = sc.nextLine();

        System.out.print("Enter student borrow limit: ");
        int studentLimit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter student course: ");
        String course = sc.nextLine();

        LibraryMember general = new LibraryMember(generalId, generalLimit);
        StudentMember student = new StudentMember(studentId, studentLimit, course);

        general.borrowBook();
        student.borrowBook();

        LibraryMember[] members = { general, student };

        System.out.println(batchPrint(members));

        sc.close();
    }
}