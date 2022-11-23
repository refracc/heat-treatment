package me.sanderson.ea;

import me.sanderson.ea.data.Problem;

/**
 * A simple {@link HillClimber} algorithm that works by finding the minimum value.
 *
 * @author refracc
 * @version 0.0.1
 * @since 2022-11-23
 */
public class HillClimber {

    public static void main(String[] args) {
        Chromosome c = Problem.createChromosome("plastic-anisotropy.csv");
        double best = Double.MAX_VALUE;

        while (true) {
            if (c.getFitness() < best) {
                best = c.getFitness();
                System.out.println("New best fitness found! [" + best + "]");
            }
            c.shuffle();
        }
    }
}
