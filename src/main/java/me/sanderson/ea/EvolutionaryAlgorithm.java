package me.sanderson.ea;

import me.sanderson.ea.options.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * Output should be with parallel coordinates :)
 */
public class EvolutionaryAlgorithm {

    private static Chromosome globalBest = null;

    public static void main(String[] args) {
        Chromosome best = null;
        List<Chromosome> population;

        population = switch (Parameters.INITIALISATION) {
            case AUGMENTED -> Initialisation.augmented();
            case RANDOM -> Initialisation.random();
        };

        for (int i = 0; i < Parameters.MAXIMUM_EVALUATIONS; i++) {
            best = getBestChromosome(population, i);
            Chromosome parent1 = new Chromosome();
            Chromosome parent2 = new Chromosome();

            switch (Parameters.SELECTION) {
                case RANDOM -> {
                    parent1 = Selection.random(population);
                    parent2 = Selection.random(population);
                }
                case ROULETTE -> {
                    parent1 = Selection.roulette(population);
                    parent2 = Selection.roulette(population);
                }
                case ROUTE_RANK -> {
                    parent1 = Selection.routeRank(population);
                    parent2 = Selection.routeRank(population);
                }
                case TOURNAMENT -> {
                    parent1 = Selection.tournament(population);
                    parent2 = Selection.tournament(population);
                }
            }

            List<Chromosome> children = switch (Parameters.CROSSOVER) {
                case ARITHMETIC -> Crossover.arithmetic(parent1, parent2);
                case ONE_POINT -> Crossover.onePoint(parent1, parent2);
                case TWO_POINT -> Crossover.twoPoint(parent1, parent2);
                case UNIFORM -> Crossover.uniform(parent1, parent2);
            };

            switch (Parameters.MUTATION) {
                case CONSTRAINED -> Mutation.constrained(children);
                case STANDARD -> Mutation.standard(children);
            }

            children.forEach(Chromosome::evaluate);

            switch (Parameters.REPLACEMENT) {
                case TOURNAMENT -> Replacement.tournament(children);
                case WORST -> Replacement.worst(children);
            }
        }
        System.out.println(best);
        System.out.println(globalBest);
        System.out.println(Parameters.asString());
    }

    /**
     * The function to retrieve the best {@link Chromosome} from the population.
     *
     * @param population The population of {@link Chromosome}s.
     * @param i The counter value from the loop.
     * @return The best {@link Chromosome} from the population based on fitness.
     */
    private static @NotNull Chromosome getBestChromosome(@NotNull List<Chromosome> population, int i) {
        Chromosome best = null;

        for (Chromosome c : population) {
            if (best == null || c.getFitness() < best.getFitness()) {
                best = c.copy();
            }
        }
        assert best != null;
        System.out.println("Best fitness from generation " + i + ": " + best.getFitness());

        if (globalBest == null || best.getFitness() < globalBest.getFitness()) {
            globalBest = best.copy();
            System.out.println("**NEW GLOBAL BEST FITNESS FOUND!**");
        }
        return best;
    }
}
