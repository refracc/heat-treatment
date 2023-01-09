package me.sanderson.ea;

import org.jetbrains.annotations.NotNull;

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
public class Chromosome implements Comparable<Chromosome> {

    private List<Point> points;
    private double fitness;

    /**
     * Default constructor creating an empty {@link Chromosome}.
     */
    public Chromosome() {
        this.points = new ArrayList<>();
        this.fitness = 0.0d;
    }

    private Chromosome(List<Point> points, double fitness) {
        this.points = points;
        this.fitness = fitness;
    }

    /**
     * Evaluate the {@link Chromosome} with respect to the fitness function (Euclidean Distance) expressed in 3 dimensions.
     *
     * @return The {@link Chromosome}'s fitness value (after evaluating the {@link Chromosome}).
     */
    public double evaluate() {
        this.fitness = 0.0d;
        for (int i = 0; i < points.size() - 1; i++) {
            fitness += Math.sqrt(Math.pow(points.get(i).temp() - points.get(i + 1).temp(), 2)
                    + Math.pow(points.get(i).time() - points.get(i + 1).time(), 2));
        }
        return fitness;
    }

    /**
     * Retrieve the fitness value of the {@link Chromosome}.
     *
     * @return The fitness value of the {@link Chromosome}
     */
    public double getFitness() {
        return evaluate();
    }

    /**
     * Retrieve the {@link List} of {@link Point}s.
     *
     * @return The {@link List} of {@link Point}s from the current {@link Chromosome}.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Add a {@link Point} to the {@link Chromosome}.
     *
     * @param point the {@link Point} to add to the {@link Chromosome}.
     */
    public void addPoint(Point point) {
        this.points.add(point);
    }

    /**
     * Add a {@link List} of {@link Point}s to the {@link Chromosome}.
     *
     * @param points The {@link List} of {@link Point}s to add to the {@link Chromosome}.
     */
    public void addPoints(List<Point> points) {
        this.points.addAll(points);
    }

    /**
     * Add a primitive array of {@link Point}s to the {@link Chromosome}.
     *
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

    public Chromosome copy() {
        return new Chromosome(this.points, this.fitness);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param c the {@link Chromosome} to be compared with.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(@NotNull Chromosome c) {
        return Double.compare(this.getFitness(), c.getFitness());
    }
}
