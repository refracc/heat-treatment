package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import me.sanderson.ea.data.Problem;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Initialisation {
    AUGMENTED, RANDOM;

    /**
     * Augmented initialisation.
     * Initialise a significantly larger population of {@link Chromosome}s and
     * take the top {@code n} candidates from this population to pre-seed the
     * population with good results.
     *
     * @return A population pre-seeded with good(ish) results.
     */
    @Contract(pure = true)
    public static @NotNull List<Chromosome> augmented() {
        List<Chromosome> pop = new ArrayList<>();
        for (int i = 0; i < Parameters.POPULATION_SIZE + 7500; i++) {
            pop.add(Problem.createChromosome(Parameters.FILE_NAME));
        }
        pop.forEach(Chromosome::evaluate);
        return pop.stream().sorted().limit(Parameters.POPULATION_SIZE).collect(Collectors.toList());
    }

    /**
     * Initialise a population with random {@link Chromosome}s. The values within these {@link Chromosome}s have been
     * randomised by using the {@link Chromosome#shuffle()} method.
     *
     * @return A random population of {@link Chromosome}s.
     */
    public static @NotNull List<Chromosome> random() {
        List<Chromosome> pop = new ArrayList<>();
        for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
            Chromosome c = Problem.createChromosome(Parameters.FILE_NAME);
            c.shuffle();
            pop.add(c);
        }
        return pop;
    }
}
