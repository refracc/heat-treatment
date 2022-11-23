package me.sanderson.ea;

import me.sanderson.ea.data.Problem;
Added bas import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    @Contract(pure = true)
    private static @NotNull List<Chromosome> augmented() {
        List<Chromosome> pop = new ArrayList<>();
        for (int i = 0; i < Parameters.POPULATION_SIZE + 7500; i++) {
            pop.add(Problem.createChromosome("plastic-anisotropy.csv"));
        }
        evaluate(pop);
        return pop.stream().sorted().limit(Parameters.POPULATION_SIZE).collect(Collectors.toList());
    }

    private static void evaluate(@NotNull List<Chromosome> population) {
        for (Chromosome c : population) {
            c.getFitness();
        }
    }
}
