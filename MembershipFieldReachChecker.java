import java.util.*;

public class MembershipFieldReachChecker {

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
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private",
            "default",
            "protected",
            "public"
        };

        StringBuilder result = new StringBuilder();

        for (String modifier : modifiers) {

            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {

                if (attempt[0].equals(modifier)) {

                    String answer = classifyAccess(
                        attempt[0],
                        attempt[1]
                    );

                    if (answer.equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (result.length() > 0) {
                result.append(" | ");
            }

            result.append(modifier)
                  .append(": ")
                  .append(allowed)
                  .append(" allowed / ")
                  .append(denied)
                  .append(" denied");
        }

        return result.toString();
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
            summarizeByModifier(attempts)
        );

        sc.close();
    }
}