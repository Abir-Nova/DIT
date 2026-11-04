public class Ex06 {
    public static void sequence(int nbr) {
        // Negative input: print nothing and leave the function
        if (nbr < 0) {
            return;
        }

        // The sequence always starts with "1" (iteration 0)
        String current = "1";
        System.out.println(current + " ");

        // Compute and print 1 new term per iteration, from 1 up to nbr
        for (int i = 0; i < nbr; i++) {
            // Builds the next term (StringBuilder is faster than repeated String concatenation)
            StringBuilder next = new StringBuilder();

            // Number of consecutive identical digits seen so far in the current run
            int count = 1;

            // Walk through the current term 1 digit at a time
            // j goes up to current.length() (inclusive) so the last run is also flushed
            for (int j = 1; j <= current.length(); j++) {
                // Same digit as the previous one (and we're not past the end)?
                // Then the run continues: increase the counter
                if (j < current.length() && current.charAt(j) == current.charAt(j - 1))
                    count++;
                else {
                    // The run just ended: write "how many" then "which digit"
                    // Example: three 1s in a row -> "31"
                    next.append(count).append(current.charAt(j - 1));

                    // Start counting a new run
                    count = 1;
                }
            }

            // The freshly built term becomes the current one
            // so the next iteration describes it in turn
            current = next.toString();

            // Print this term on its own line
            System.out.println(current + " ");
        }
    }
}