# VowelCounter - Using .stream()

## About the Project
The `VowelCounter` is a Java application that analyzes a given string to count:
- The total number of vowels in the string.
- Which vowels were repeated and how many times.

The output is descriptive, showing the total number of vowels and their occurrences in the format:
```plaintext
The Total number of vowels: 11 (a, e, i, o)
Repeated vowels: 2(a), 2(e), 4(i), 3(o)
```

---

## Code Explanation (Line-by-Line)

### 1. VowelCounter Class
This class contains the core logic to process the input string and count vowels.

```java
public class VowelCounter {
```
#### Explanation:
Defines the main class `VowelCounter` where the methods for vowel counting and recursion are implemented.

---

### 2. countVowels Method

```java
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
    return String.format("The Total number of vowels: %d (%s)\nRepeated vowels: %s",
            totalVowels,
            String.join(", ", vowelCounts.keySet().toString().replaceAll("[\[\]]", "")),
            repeatedVowels.toString().trim().replaceAll(", $", ""));
}
```
#### Explanation:
1. **`fullName.toLowerCase()`**:
   Converts the input string to lowercase to ensure consistent processing of vowels.
2. **`Map<Character, Integer> vowelCounts = new HashMap<>();`**:
   Initializes a map to store each vowel as a key and its count as the value.
3. **`countVowelsRecursively()`**:
   Calls the recursive method to process the string and populate the `vowelCounts` map.
4. **Calculate Total Vowels**:
   - **`vowelCounts.values().stream().mapToInt(Integer::intValue).sum()`**:
     Sums all the values in the map to compute the total number of vowels.
5. **Build the Repeated Vowels Output**:
   - Iterates through the map and constructs a string in the format `count(vowel)` for each vowel.
6. **Format and Return Result**:
   - Formats the output string showing the total vowels and repeated vowels in the specified format.

---

### 3. Recursive Method: countVowelsRecursively

```java
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
```
#### Explanation:
1. **Base Case**:
   - **`if (index == str.length())`**:
     Stops the recursion when all characters in the string are processed.
2. **Process Current Character**:
   - **`str.charAt(index)`**:
     Retrieves the character at the current index.
   - **`"aeiou".indexOf(ch) >= 0`**:
     Checks if the character is a vowel.
   - **`vowelCounts.put()`**:
     Updates the map with the count of the vowel.
3. **Recursive Call**:
   - Proceeds to the next character in the string by calling itself with `index + 1`.

---

### 4. Main Method

```java
public class Main {
    public static void main(String[] args) {
        String input = "Rodrigo de Freitas Marcilio";
        String result = VowelCounter.countVowels(input);
        System.out.println(result);
    }
}
```
#### Explanation:
1. **Input String**:
   - Defines the input string `"Rodrigo de Freitas Marcilio"`.
2. **Calling `countVowels`**:
   - Calls the `countVowels` method from the `VowelCounter` class to process the input string.
3. **Print Result**:
   - Outputs the formatted result to the console.

---

## Expected Output
With the input `"Rodrigo de Freitas Marcilio"`, the output will be:
```plaintext
The Total number of vowels: 11 (a, e, i, o)
Repeated vowels: 2(a), 2(e), 4(i), 3(o)
```

---

## Technologies Used
- **Java 8+**: For implementing the code.
- **Streams API**: For calculating total vowels and identifying repeated vowels efficiently.

---

## How to Run the Project
1. Clone or copy the code to your local environment.
2. Ensure Java 8 or higher is installed.
3. Compile and execute the `VowelCounter` and `Main` classes.
4. Test with different strings by modifying the input string in the `main` method.

---

## Possible Improvements
- Add support for accented vowels (e.g., `á`, `é`, `í`) for better compatibility with other languages.
- Implement interactive input collection via the console.
- Optimize vowel counting using alternative data structures for better performance.

