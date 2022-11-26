package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import me.sanderson.ea.utility.Helpers;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public enum Selection {
    RANDOM, ROULETTE, ROUTE_RANK, TOURNAMENT;

    /**
     * Select a {@link Chromosome} at random from the population.
     *
     * @param population The population of {@link Chromosome}s to select from.
     * @return A random {@link Chromosome}.
     */
    public static Chromosome random(@NotNull List<Chromosome> population) {
        return population.get(Parameters.RANDOM.nextInt(Parameters.POPULATION_SIZE));
    }

    /**
     * Use tournament selection to get a {@link Chromosome} from the population.
     * This function also makes use of Elitism, by copying the best
     * chromosomes from the current population to the new population.
     * (1). Select {@code k} solutions at random from the population.
     * (2). Select the best of these {@code k} solutions to be parents.
     *
     * @return A {@link Chromosome} from the population.
     */
    public static Chromosome tournament(@NotNull List<Chromosome> population) {
        population.forEach(Chromosome::shuffle);
        return population.stream().limit(Parameters.TOURNAMENT_SIZE).min(Comparator.naturalOrder()).orElse(null);
    }

    /**
     * Use fitness-proportionate selection to obtain {@link Chromosome} from the population.
     *
     * @return A {@link Chromosome} from the population.
     */
    public static Chromosome roulette(@NotNull List<Chromosome> population) {
        double weightedSum = 0.0d;
        for (Chromosome c : population) {
            double v = 1 - c.getFitness();
            weightedSum += v;
        }

        double rand = weightedSum * Parameters.RANDOM.nextDouble();
        for (Chromosome c : population) {
            rand -= 1 - c.getFitness();
            if (rand < 0) return c;
        }
        // When rounding errors occur, return the last item
        return population.get(population.size() - 1);
    }

    /**
     * Rank the routes and select the {@link Chromosome} that meets the selection criteria.
     *
     * @return A {@link Chromosome} from the population.
     */
    public static Chromosome routeRank(@NotNull List<Chromosome> population) {
        double[] fitness = new double[Parameters.POPULATION_SIZE];
        for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
            fitness[i] = (i + 1);
        }
        Helpers.unitise(fitness);
        return population.get(Helpers.random(fitness));
    }
}
