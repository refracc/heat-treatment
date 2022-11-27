package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Replacement {
    TOURNAMENT, WORST;

    /**
     * Replace the worst performing {@link Chromosome} in the population with a new random {@link Chromosome}
     * @param population A collection of {@link Chromosome}s from the population.
     */
    public static void worst(List<Chromosome> population) {
        int index = getWorstIndex(population);
        population.set(index, new Chromosome());
    }

    /**
     * Use tournament as a means for replacement in the evolutionary process.
     * @param population The population of {@link Chromosome}s.
     */
    @Contract(pure = true)
    public static void tournament(@NotNull List<Chromosome> population) {
        population.forEach(c -> {
            Collections.shuffle(population);
            Chromosome worst = population.stream().limit(Parameters.TOURNAMENT_SIZE).max(Comparator.naturalOrder()).orElse(null);
            population.remove(worst);
            population.add(new Chromosome());
        });
    }

    /**
     * Retrieve the worst performing {@link Chromosome} from the population.
     * @param population The population of {@link Chromosome}s.
     * @return The index of the worst performing {@link Chromosome}.
     */
    private static int getWorstIndex(@NotNull List<Chromosome> population) {
        Chromosome worst = null;
        int index = -1;

        for (int i = 0; i < population.size(); i++) {
            if (worst == null || population.get(i).getFitness() > worst.getFitness()) {
                worst = population.get(i);
                index = i;
            }
        }
        return index;
    }
}
