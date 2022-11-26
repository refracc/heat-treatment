package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public enum Selection {
    RANDOM, ROULETTE, ROUTE_RANK, TOURNAMENT;

    /**
     * Select a {@link Chromosome} at random from the population.
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
     * @return A {@link Chromosome} from the population.
     */
    public static Chromosome tournament(@NotNull List<Chromosome> population) {
        population.forEach(Chromosome::shuffle);
        return population.stream().limit(Parameters.TOURNAMENT_SIZE).min(Comparator.naturalOrder()).orElse(null);
    }
}
