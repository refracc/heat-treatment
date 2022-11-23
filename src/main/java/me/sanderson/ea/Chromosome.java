package me.sanderson.ea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is the class that represents a {@link Chromosome} in the evolutionary algorithm.
 *
 * @author refracc
 * @version 0.0.1
 * @since 2022-11-22
 */
public class Chromosome {

    private final List<Point> points;
    private double fitness;

    /**
     * Default constructor creating an empty {@link Chromosome}.
     */
    public Chromosome() {
        this.points = new ArrayList<>();
        this.fitness = 0.0d;
    }

    /**
     * Constructor creating a new {@link Chromosome} with one singular point.
     * @param point The {@link Point} to add.
     */
    public Chromosome(Point point) {
        this();
        this.points.add(point);
    }

    /**
     * A constructor creating a new {@link Chromosome} with a {@link List} of {@link Point}s. 
     * @param points The {@link List} of {@link Point}s to add to the {@link Chromosome}.
     */
    public Chromosome(List<Point> points) {
        this();
        this.points.addAll(points);
    }

    /**
     * A constructor creating a new {@link Chromosome} with a {@link List} of {@link Point}s. 
     * @param points The primitive array of {@link Point}s to add to the {@link Chromosome}.
     */
    public Chromosome(Point... points) {
        this(Arrays.asList(points));
    }

    /**
     * Evaluate the {@link Chromosome} with respect to the fitness function (Euclidean Distance)
     * @return The {@link Chromosome}'s fitness value (after evaluating the {@link Chromosome}).
     */
    public double evaluate() {
        this.fitness = 0.0d;
        for (int i = 0; i < points.size() - 1; i++) {
            fitness += Math.sqrt(Math.pow(points.get(i).temp() - points.get(i + 1).temp(), 2) + Math.pow(points.get(i).time() - points.get(i + 1).time(), 2));
        }
        return fitness;
    }

    /**
     * Retrieve the fitness value of the {@link Chromosome}.
     * @return The fitness value of the {@link Chromosome}
     */
    public double getFitness() {
        return evaluate();
    }

    /**
     * Retrieve the {@link List} of {@link Point}s.
     * @return The {@link List} of {@link Point}s from the current {@link Chromosome}.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Add a {@link Point} to the {@link Chromosome}.
     * @param point the {@link Point} to add to the {@link Chromosome}.
     */
    public void addPoint(Point point) {
        this.points.add(point);
    }

    /**
     * Add a {@link List} of {@link Point}s to the {@link Chromosome}.
     * @param points The {@link List} of {@link Point}s to add to the {@link Chromosome}.
     */
    public void addPoints(List<Point> points) {
        this.points.addAll(points);
    }

    /**
     * Add a {@link List} of {@link Point}s to the {@link Chromosome}.
     * @param points The primitive array of {@link Point}s to add to the {@link Chromosome}.
     */
    public void addPoints(Point... points) {
        this.addPoints(Arrays.asList(points));
    }

    /**
     * Shuffle the {@link List} of {@link Point}s.
     */
    public Chromosome shuffle() {
        Collections.shuffle(this.getPoints());
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chromosome {\n");
        sb.append("\tPoints {\n");
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            sb.append("\t\t").append(i).append(" {\n");
            sb.append("\t\t\tTemperature: ").append(p.temp()).append("\n");
            sb.append("\t\t\tTime: ").append(p.time()).append("\n");
            sb.append("\t\t\tValue: ").append(p.value()).append("\n");
            sb.append("\t\t}\n");
        }
        sb.append("\t}\n");
        sb.append("Fitness: ").append(getFitness());
        return sb.toString();
    }
}
