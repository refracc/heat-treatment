package me.sanderson.ea;

import me.sanderson.ea.options.Initialisation;
import me.sanderson.ea.options.Selection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Output should be with parallel coordinates :)
 */
public class EvolutionaryAlgorithm {

    public static void main(String[] args) {
        Chromosome best = null;
        List<Chromosome> population;

        population = switch (Parameters.INITIALISATION) {
            case AUGMENTED -> Initialisation.augmented();
            case RANDOM -> Initialisation.random();
        };

        best = getBestChromosome(population);

        for (int i = 0; i < Parameters.MAXIMUM_EVALUATIONS; i++) {
            Chromosome parent1;
            Chromosome parent2;

            switch (Parameters.SELECTION) {
                case RANDOM -> {
                    parent1 = Selection.random(population);
                    parent2 = Selection.random(population);
                }
                case ROULETTE -> {
                }
                case ROUTE_RANK -> {
                }
                case TOURNAMENT -> {
                    parent1 = Selection.tournament(population);
                    parent2 = Selection.tournament(population);
                }
            }
        }
    }

    /*
     * ADDITIONAL FUNCTIONS
     */

    /**
     * The procedure for evaluating a {@link List} of {@link Chromosome}s.
     *
     * @param population The population of {@link Chromosome}s.
     */
    private static void evaluate(@NotNull List<Chromosome> population) {
        for (Chromosome c : population) {
            c.getFitness();
        }
    }

    /**
     * The function to retrieve the best {@link Chromosome} from the population.
     *
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
}
