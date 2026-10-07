import java.util.*;

public class ReferenceDeskSubclassReach {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            if (classifyAccess(modifier, context)
                    .equals("DENIED")) {

                return modifier +
                       " via " +
                       context +
                       " (attempt #" +
                       (i + 1) +
                       ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter modifier: ");
            attempts[i][0] = sc.next();

            System.out.print("Enter accessor context: ");
            attempts[i][1] = sc.next();
        }

        System.out.println(
            firstDeniedAttempt(attempts)
        );

        sc.close();
    }
}