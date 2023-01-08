package me.sanderson.ea;

import me.sanderson.ea.options.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Random;

public class Parameters {

    //Random number generator used throughout the application
    private static final long seed = System.currentTimeMillis();
    public static Initialisation INITIALISATION = Initialisation.AUGMENTED;
    public static Selection SELECTION = Selection.TOURNAMENT;
    public static Crossover CROSSOVER = Crossover.TWO_POINT;
    public static Mutation MUTATION = Mutation.STANDARD;
    public static Replacement REPLACEMENT = Replacement.WORST;
    public static int TOURNAMENT_SIZE = 10;
    public static String FILE_NAME = "plastic-anisotropy.csv";
    // specifies minimum and maximum weight values
    public static int POPULATION_SIZE = 300;
    public static int AUGMENTED_POPULATION_SIZE = 15750;
    public static int MAXIMUM_EVALUATIONS = 50000;
    // Parameters for mutation
    // Rate = probability of changing a gene
    // Change = the maximum +/- adjustment to the gene value
    public static double MUTATION_RATE = 0.90; // mutation rate for mutation operator
    public static double MUTATION_CHANGE = 1.50; // delta change for mutation operator
    public static Random RANDOM = new Random(seed);

    @Override
    public @NotNull String toString() {
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
