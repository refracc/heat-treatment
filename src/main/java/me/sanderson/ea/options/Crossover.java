package me.sanderson.ea.options;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Parameters;
import me.sanderson.ea.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public enum Crossover {
    ARITHMETIC, ONE_POINT, TWO_POINT, UNIFORM;

    /**
     * Use uniform crossover to create a new collection of children from the population.
     *
     * @param parent1 A {@link Chromosome} from the population.
     * @param parent2 A {@link Chromosome} from the population.
     * @return A collection of children ({@link Chromosome}s) from the population.
     */
    public static @NotNull @Unmodifiable List<Chromosome> uniform(@NotNull Chromosome parent1, @NotNull Chromosome parent2) {
        Chromosome child1 = new Chromosome();
        Chromosome child2 = new Chromosome();
        int i = 0;
        if (i < parent1.getPoints().size()) {
            do {
                if (Parameters.RANDOM.nextBoolean()) {
                    child1.getPoints().set(i, parent1.getPoints().get(i));
                    child2.getPoints().set(i, parent2.getPoints().get(i));
                } else {
                    child1.getPoints().set(i, parent2.getPoints().get(i));
                    child2.getPoints().set(i, parent1.getPoints().get(i));
                }
                i++;
            } while (i < parent1.getPoints().size());
        }
        return List.of(child1, child2);
    }

    /**
     * Use One-Point crossover to create a new collection of children from the population.
     *
     * @param parent1 A {@link Chromosome} from the population.
     * @param parent2 A {@link Chromosome} from the population.
     * @return A collection of children ({@link Chromosome}s) from the population.
     */
    public static @NotNull @Unmodifiable List<Chromosome> onePoint(@NotNull Chromosome parent1, @NotNull Chromosome parent2) {
        Chromosome child1 = new Chromosome();
        Chromosome child2 = new Chromosome();

        int cut = Parameters.RANDOM.nextInt(parent1.getPoints().size());

        for (int i = 0; i < parent1.getPoints().size(); i++) {
            if (i < cut) {
                child1.getPoints().set(i, parent1.getPoints().get(i));
                child2.getPoints().set(i, parent2.getPoints().get(i));
            } else {
                child1.getPoints().set(i, parent2.getPoints().get(i));
                child2.getPoints().set(i, parent1.getPoints().get(i));
            }
        }
        return List.of(child1, child2);
    }


    /**
     * Use Two-Point crossover to create a new collection of children from the population.
     *
     * @param parent1 A {@link Chromosome} from the population.
     * @param parent2 A {@link Chromosome} from the population.
     * @return A collection of children ({@link Chromosome}s) from the population.
     */
    public static @NotNull @Unmodifiable List<Chromosome> twoPoint(@NotNull Chromosome parent1, @NotNull Chromosome parent2) {
        Chromosome child1 = new Chromosome();
        Chromosome child2 = new Chromosome();
        int cut1 = Parameters.RANDOM.nextInt(parent1.getPoints().size());
        int cut2 = Parameters.RANDOM.nextInt(parent1.getPoints().size() - cut1 + 1) + cut1;

        for (int i = 0; i < parent1.getPoints().size(); i++) {
            if (i < cut1 || i >= cut2) {
                child1.getPoints().set(i, parent1.getPoints().get(i));
                child2.getPoints().set(i, parent2.getPoints().get(i));
            } else {
                child1.getPoints().set(i, parent2.getPoints().get(i));
                child2.getPoints().set(i, parent1.getPoints().get(i));
            }
        }
        return List.of(child1, child2);
    }

    /**
     * Use Arithmetic crossover to create a new collection of children from the population.
     *
     * @param parent1 A {@link Chromosome} from the population.
     * @param parent2 A {@link Chromosome} from the population.
     * @return A collection of children ({@link Chromosome}s) from the population.
     */
    public static @NotNull @Unmodifiable List<Chromosome> arithmetic(@NotNull Chromosome parent1, @NotNull Chromosome parent2) {
        Chromosome child = new Chromosome();

        for (int i = 0; i < parent1.getPoints().size(); i++) {
            double valueAverage = (parent1.getPoints().get(i).value() + parent2.getPoints().get(i).value()) / 2;
            double tempAverage = (parent1.getPoints().get(i).temp() + parent2.getPoints().get(i).temp()) / 2;
            double timeAverage = (parent1.getPoints().get(i).time() + parent2.getPoints().get(i).time()) / 2;

            child.getPoints().set(i, new Point(tempAverage, timeAverage, valueAverage));
        }

        return List.of(child);
    }
}
