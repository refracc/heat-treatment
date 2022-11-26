package me.sanderson.ea.utility;

import me.sanderson.ea.Parameters;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Helpers {

    // Helpers from smile (https://github.com/haifengl/smile)
    @Contract(pure = true)
    public static double norm(double @NotNull [] x) {
        double norm = 0.0;

        for (double n : x) {
            norm += Math.abs(n);
        }

        return norm;
    }

    public static void unitise(double[] x) {
        double n = norm(x);
        for (int i = 0; i < x.length; i++) {
            x[i] /= n;
        }
    }

    public static int random(double[] prob) {
        int[] ans = random(prob, 1);
        return ans[0];
    }

    public static int @NotNull [] random(double @NotNull [] prob, int n) {
        // set up alias table
        double[] q = new double[prob.length];
        for (int i = 0; i < prob.length; i++) {
            q[i] = prob[i] * prob.length;
        }

        // initialize a with indices
        int[] a = new int[prob.length];
        for (int i = 0; i < prob.length; i++) {
            a[i] = i;
        }

        // set up H and L
        int[] HL = new int[prob.length];
        int head = 0;
        int tail = prob.length - 1;
        for (int i = 0; i < prob.length; i++) {
            if (q[i] >= 1.0) {
                HL[head++] = i;
            } else {
                HL[tail--] = i;
            }
        }

        while (head != 0 && tail != prob.length - 1) {
            int j = HL[tail + 1];
            int k = HL[head - 1];
            a[j] = k;
            q[k] += q[j] - 1;
            tail++;                                  // remove j from L
            if (q[k] < 1.0) {
                HL[tail--] = k;                      // add k to L
                head--;                              // remove k
            }
        }

        // generate sample
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            double rU = Parameters.RANDOM.nextDouble() * prob.length;

            int k = (int) (rU);
            rU -= k;  /* rU becomes rU-[rU] */

            if (rU < q[k]) {
                ans[i] = k;
            } else {
                ans[i] = a[k];
            }
        }

        return ans;
    }
}
