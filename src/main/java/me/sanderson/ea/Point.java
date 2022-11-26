package me.sanderson.ea;

/**
 * The class used for storing data about a {@link Point}.
 *
 * @author refracc
 * @version 0.0.1
 * @since 2022-11-22
 */
public record Point(double temp, double time, double value) {

    /**
     * Constructor for creating a {@link Point}.
     *
     * @param temp  The temperature at this data {@link Point}.
     * @param time  The time at this data {@link Point}.
     * @param value The value for the data {@link Point}.
     */
    public Point {
    }

    /**
     * Retrieve the temperature of the {@link Point}.
     *
     * @return THe temperature of the {@link Point}.
     */
    @Override
    public double temp() {
        return temp;
    }

    /**
     * Retrieve the time of the {@link Point}.
     *
     * @return The time of the {@link Point}.
     */
    @Override
    public double time() {
        return time;
    }

    /**
     * Retrieve the normalised value of the {@link Point}.
     *
     * @return The data value of the {@link Point}.
     */
    @Override
    public double value() {
        return value;
    }
}
