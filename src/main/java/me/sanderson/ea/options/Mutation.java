package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import me.sanderson.ea.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public enum Mutation {
    CONSTRAINED, STANDARD;

    /**
     * Mutate the population based on the constrained mutation technique.
     * @param children The child {@link Chromosome}s to be mutated.
     */
    public static void constrained(@NotNull List<Chromosome> children) {
        for (Chromosome c : children) {
            for (int i = 0; i < c.getPoints().size(); i++) {
                if (Parameters.RANDOM.nextDouble() < Parameters.MUTATION_RATE) {
                    double priorFitness = c.getFitness();
                    double temp = c.getPoints().get(i).temp();
                    double time = c.getPoints().get(i).time();
                    double value = c.getPoints().get(i).value();

                    if (Parameters.RANDOM.nextBoolean()) {
                        c.getPoints().set(i, new Point(temp + Parameters.MUTATION_CHANGE, time + Parameters.MUTATION_CHANGE, value + Parameters.MUTATION_CHANGE));
                    } else {
                        c.getPoints().set(i, new Point(temp - Parameters.MUTATION_CHANGE, time - Parameters.MUTATION_CHANGE, value - Parameters.MUTATION_CHANGE));
                    }
                    c.evaluate();
                    if (c.getFitness() > priorFitness) c.getPoints().set(i, new Point(temp, time, value));
                    c.evaluate();
                }
            }
        }
    }

    /**
     * Mutate a population based on the standard probabilistic mutation technique.
     * @param children The child {@link Chromosome}s to be mutated.
     */
    public static void standard(@NotNull List<Chromosome> children) {
        for (Chromosome c : children) {
            for (int i = 0; i < c.getPoints().size(); i++) {
                double temp = c.getPoints().get(i).temp();
                double time = c.getPoints().get(i).time();
                double value = c.getPoints().get(i).value();

                if (Parameters.RANDOM.nextDouble() < Parameters.MUTATION_RATE) {
                    c.getPoints().set(i, new Point(temp + Parameters.MUTATION_CHANGE, time + Parameters.MUTATION_CHANGE, value + Parameters.MUTATION_CHANGE));
                } else {
                    c.getPoints().set(i, new Point(temp - Parameters.MUTATION_CHANGE, time - Parameters.MUTATION_CHANGE, value - Parameters.MUTATION_CHANGE));
                }
            }
        }
    }
}
