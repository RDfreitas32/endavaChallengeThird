import java.util.HashMap;
import java.util.Map;

public class VowelCounter {

    public static String countVowels(String fullName) {
        // Normalize the input to lowercase
        String normalized = fullName.toLowerCase();
        Map<Character, Integer> vowelCounts = new HashMap<>();

        // Count vowels recursively
        countVowelsRecursively(normalized, 0, vowelCounts);

        // Calculate total vowels
        int totalVowels = vowelCounts.values().stream().mapToInt(Integer::intValue).sum();

        // Build the repeated vowels output
        StringBuilder repeatedVowels = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : vowelCounts.entrySet()) {
            repeatedVowels.append(entry.getValue())
                    .append("(").append(entry.getKey()).append(")")
                    .append(", ");
        }

        // Format and return the result
        return String.format("O Total de vogais: %d (%s)\nVogais repetidas: %s",
                totalVowels,
                String.join(", ", vowelCounts.keySet().toString().replaceAll("[\\[\\]]", "")),
                repeatedVowels.toString().trim().replaceAll(", $", ""));
    }

    private static void countVowelsRecursively(String str, int index, Map<Character, Integer> vowelCounts) {
        if (index == str.length()) {
            return;
        }
        char ch = str.charAt(index);
        if ("aeiou".indexOf(ch) >= 0) {
            vowelCounts.put(ch, vowelCounts.getOrDefault(ch, 0) + 1);
        }
        countVowelsRecursively(str, index + 1, vowelCounts);
    }
}
