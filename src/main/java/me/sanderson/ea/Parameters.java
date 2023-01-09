package me.sanderson.ea;

import me.sanderson.ea.options.*;
import me.sanderson.ea.utility.ProblemType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Random;

public class Parameters {

    // The seed used for the random number generator.
    private static final long seed = System.currentTimeMillis();
    public static Random RANDOM = new Random(seed);

    /* Control how the evolutionary algorithm initialises the population.
     * Possible values:
     *  - AUGMENTED
     *  - RANDOM
     */
    public static Initialisation INITIALISATION = Initialisation.AUGMENTED;

    /* Control how the evolutionary algorithm controls the selection process.
     * Possible values:
     *  - RANDOM
     *  - ROULETTE
     *  - ROUTE_RANK
     *  - TOURNAMENT
     */
    public static Selection SELECTION = Selection.ROULETTE;

    /* Control how the evolutionary algorithm controls the crossover process.
     * Possible values:
     *  - ARITHMETIC
     *  - ONE_POINT
     *  - TWO_POINT
     *  - UNIFORM
     */
    public static Crossover CROSSOVER = Crossover.UNIFORM;

    /* Control how the evolutionary algorithm controls the mutation process.
     * Possible values:
     *  - CONSTRAINED
     *  - STANDARD
     */
    public static Mutation MUTATION = Mutation.CONSTRAINED;

    /* Control how the evolutionary algorithm controls the replacement process.
     * Possible values:
     *  - TOURNAMENT
     *  - WORST
     */
    public static Replacement REPLACEMENT = Replacement.WORST;

    /* Control how the evolutionary algorithm evaluates the chromosomes.
     * Possible values:
     *  - MINIMISATION
     *  - MAXIMISATION
     */
    public static ProblemType PROBLEM = ProblemType.MINIMISATION;

    // The tournament size for the selection and replacement process.
    public static int TOURNAMENT_SIZE = 10;

    // The file that is to be read in by the evolutionary algorithm.
    public static String FILE_NAME = "plastic-anisotropy.csv";

    // The default population size.
    public static int POPULATION_SIZE = 300;

    // The augmented population size when using augmented initialisation.
    public static int AUGMENTED_POPULATION_SIZE = 15750;

    // The maximum number of evaluations/generations.
    public static int MAXIMUM_EVALUATIONS = 20000;


    // Parameters for mutation
    // Rate = probability of changing a gene
    // Change = the maximum +/- adjustment to the gene value
    public static double MUTATION_RATE = 0.90; // mutation rate for mutation operator
    public static double MUTATION_CHANGE = 1.50; // delta change for mutation operator

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code asString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code asString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    public @NotNull static String asString() {
        StringBuilder str = new StringBuilder();
        for (Field field : Parameters.class.getDeclaredFields()) {
            String name = field.getName();
            Object val = null;

            try {
                val = field.get(null);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            str.append(name).append(" \t").append(val).append("\r\n");
        }
        return str.toString();
    }
}
