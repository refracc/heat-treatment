package me.sanderson.ea;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String file = "plastic-anisotropy.csv";
        List<String> nums = readProblem("./data/" + file);

        StringBuilder sb = new StringBuilder();
        for (String s : nums) {
            sb.append(s).append(",");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("./data/output/" + file));
        bw.write(sb.substring(0, sb.length() - 1));
        bw.close();
    }

    private static @NotNull List<String> readProblem(String problem) {
        List<String> nums = new ArrayList<>();
        try {
            List<String> file = Files.readAllLines(Path.of(problem));
            for (String s : file) {
                String[] data = s.split(",");
                for (String str : data) {
                    if (!str.matches("[^0-9]."))
                        nums.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nums.subList(1, nums.size() - 1);
    }
}
