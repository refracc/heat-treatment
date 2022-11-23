package me.sanderson.ea;

import me.sanderson.ea.data.Problem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Output should be with parallel coordinates :)
 */
public class Algorithm {

    public static void main(String[] args) {
        List<Chromosome> population;

        population = switch (Parameters.INITIALISATION) {
            case AUGMENTED -> augmented();
            case OPPOSITION -> null;
            case RANDOM -> null;
        };

        population.forEach(System.out::println);
    }

    /*
        d888888b d8b   db d888888b d888888b d888888b  .d8b.  db      d888888b .d8888.  .d8b.  d888888b d888888b  .d88b.  d8b   db
          `88'   888o  88   `88'   `~~88~~'   `88'   d8' `8b 88        `88'   88'  YP d8' `8b `~~88~~'   `88'   .8P  Y8. 888o  88
           88    88V8o 88    88       88       88    88ooo88 88         88    `8bo.   88ooo88    88       88    88    88 88V8o 88
           88    88 V8o88    88       88       88    88~~~88 88         88      `Y8b. 88~~~88    88       88    88    88 88 V8o88
          .88.   88  V888   .88.      88      .88.   88   88 88booo.   .88.   db   8D 88   88    88      .88.   `8b  d8' 88  V888
        Y888888P VP   V8P Y888888P    YP    Y888888P YP   YP Y88888P Y888888P `8888Y' YP   YP    YP    Y888888P  `Y88P'  VP   V8P
     */

    /**
     * Augmented initialisation.
     * Initialise a significantly larger population of {@link Chromosome}s and
     * take the top {@code n} candidates from this population to pre-seed the
     * population with good results.
     * @return A population pre-seeded with good(ish) results.
     */
    @Contract(pure = true)
    private static @NotNull List<Chromosome> augmented() {
        List<Chromosome> pop = new ArrayList<>();
        for (int i = 0; i < Parameters.POPULATION_SIZE + 7500; i++) {
            pop.add(Problem.createChromosome("plastic-anisotropy.csv"));
        }
        evaluate(pop);
        return pop.stream().sorted().limit(Parameters.POPULATION_SIZE).collect(Collectors.toList());
    }

    /**
     * The procedure for evaluating a {@link List} of {@link Chromosome}s.
     * @param population The population of {@link Chromosome}s.
     */
    private static void evaluate(@NotNull List<Chromosome> population) {
        for (Chromosome c : population) {
            c.getFitness();
        }
    }

    /**
     * The function to retrieve the best {@link Chromosome} from the population.
     * @param population The population of {@link Chromosome}s.
     * @return The best {@link Chromosome} from the population based on fitness.
     */
    private static Chromosome getBestChromosome(@NotNull List<Chromosome> population) {
        Chromosome best = null;

        for (Chromosome c : population) {
            if (best == null || c.getFitness() < best.getFitness()) {
                best = c.copy();
            }
        }
        return best;
    }

    /**
     * Activation function selection.
     * Select the activation function for the evolutionary algorithm.
     * @param v The value to be passed in to the activation function.
     * @return The result of the activation function.
     */
    private static double activation(double v) {
        return switch (Parameters.ACTIVATION) {
            case LEAKY_RELU -> (v > 0) ? v : v / 100;
            case RELU -> (v > 0.0) ? v : -1.0;
            case STEP -> (v <= 0) ? -1.0 : 1.0;
            case SWISH -> v * (1 / (1 + Math.exp(-v)));
            case TANH -> (v < -20.0) ? -1.0 : (v > 20.0) ? 1.0 : Math.tanh(v);
            case ELU -> (v > 0) ? v : (Math.exp(v) - 1) / 10;
        };
    }
}
