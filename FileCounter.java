import java.io.*;
import java.util.*;

public class FileCounter {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int totalChars = 0;
        int palindromeCount = 0;
        int tokenCount = 0;
        int emoticonCount = 0;
        int newlineCount = 0;
        int totalTokenLength = 0;
        int maxTokenLength = 0;

        String emoticonPattern = "[:;][-~]?[)D]";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file path:");
        String filePath = scanner.nextLine();

        System.out.println("You entered: '" + filePath + "'");

        File inputFile = new File(filePath);

        if (!inputFile.exists()) {
            System.out.println("Error: File does not exist.");
            return;
        }

        if (!inputFile.isFile()) {
            System.out.println("Error: The path is not a file.");
            return;
        }

        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null) {
                newlineCount++;
                totalChars += line.length();
                String[] tokens = line.split("\\s+");
                tokenCount += tokens.length;

                for (String token : tokens) {
                    totalTokenLength += token.length();

                    if (token.length() > maxTokenLength) {
                        maxTokenLength = token.length();
                    }

                    if (isPalindrome(token)) {
                        palindromeCount++;
                    }

                    if (token.matches(emoticonPattern)) {
                        emoticonCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the reader: " + e.getMessage());
                }
            }
        }

        double averageTokenLength = 0;
        if (tokenCount != 0) {
            averageTokenLength = (double) totalTokenLength / tokenCount;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Program start:");
        System.out.println("\nTotal # Character count: " + totalChars);
        System.out.println("Total # Palindrome found: " + palindromeCount);
        System.out.println("Total Number of tokens: " + tokenCount);
        System.out.println("Total Number of emoticon: " + emoticonCount);
        System.out.println("Total # of New Lines: " + newlineCount);
        System.out.println("The longest and average token size token: " + maxTokenLength + " , " + + averageTokenLength);
        System.out.println("Execution Time: " + executionTime / 1000.0 + " seconds");
        System.out.println("\nProgram terminated properly!");
    }

    private static boolean isPalindrome(String word) {
        String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String reversed = new StringBuilder(cleanedWord).reverse().toString();
        return cleanedWord.equals(reversed);
    }
}
//The file path that I used: C:/Users/CAMT-STD/Documents/input1.txt
