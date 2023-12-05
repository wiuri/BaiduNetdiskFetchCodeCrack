package priv.light.baidu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicateLines {
    public static void main(String[] args) {
        String passwordFile = "password.txt";
        String passwordHasTestFile = "passwordHasTest.txt";

        // 读取passwordHasTest.txt中的内容
        Set<String> linesToRemove = readLinesFromFile(passwordHasTestFile);

        // 过滤password.txt中的重复行
        filterFile(passwordFile, linesToRemove);
    }

    private static Set<String> readLinesFromFile(String filename) {
        try {
            return Files.lines(Paths.get(filename))
                    .map(String::trim)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    private static void filterFile(String filename, Set<String> linesToRemove) {
        Path inputPath = Paths.get(filename);
        Path outputPath = Paths.get(filename + ".filtered");

        try {
            List<String> filteredLines = Files.lines(inputPath)
                    .map(String::trim)
                    .filter(line -> !linesToRemove.contains(line))
                    .collect(Collectors.toList());

            Files.write(outputPath, filteredLines);

            System.out.println("Filtering complete. Filtered content saved to " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}