package me.sanderson.ea.data;

import me.sanderson.ea.Chromosome;
import me.sanderson.ea.Point;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Problem {

    private static List<String> readFile(String file) {
        List<String> list;
        try {
            list = Files.readAllLines(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static @NotNull Chromosome createChromosome(String file) {
        Chromosome c = new Chromosome();
        List<String> temp = readFile("./data/temperatures.txt");
        List<String> time = readFile("./data/times.txt");
        List<String> data = readFile("./data/" + file);

        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            String[] datum = s.split(",");
            for (int j = 0; j < datum.length; j++) {
                if (i == 0 && j == 0) continue;
                String str = datum[j];
                if (!"NA".equalsIgnoreCase(str)) {
                    c.addPoint(new Point(Double.parseDouble(temp.get(i)), Double.parseDouble(time.get(j)), Double.parseDouble(str)));
                }
            }
        }

        return c.shuffle();
    }

}
