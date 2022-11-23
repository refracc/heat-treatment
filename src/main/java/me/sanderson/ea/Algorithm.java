package me.sanderson.ea;

import me.sanderson.ea.data.Problem;

public class Algorithm {

    public static void main(String[] args) {
        Chromosome c = Problem.createChromosome("plastic-anisotropy.csv");

        System.out.println(c);
    }
}
