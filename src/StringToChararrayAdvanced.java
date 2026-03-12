import java.util.*;

public class StringToCharArrayAdvanced {

    // Caesar cipher using char array
    static String encrypt(String text, int shift) {
        char[] arr = text.toCharArray();
        for (int i=0; i<arr.length; i++) {
            char c = arr[i];
            if (Character.isUpperCase(c)) arr[i] = (char)('A' + (c-'A'+shift)%26);
            else if (Character.isLowerCase(c)) arr[i] = (char)('a' + (c-'a'+shift)%26);
        }
        return new String(arr);
    }
    static String decrypt(String text, int shift) { return encrypt(text, 26-shift); }

    // Anagram check
    static boolean isAnagram(String a, String b) {
        char[] ca = a.toLowerCase().replaceAll("[^a-z]","").toCharArray();
        char[] cb = b.toLowerCase().replaceAll("[^a-z]","").toCharArray();
        Arrays.sort(ca); Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }

    // Most frequent character
    static char mostFrequent(String s) {
        int[] freq = new int[128];
        for (char c : s.toCharArray()) freq[c]++;
        char best = s.charAt(0);
        for (char c : s.toCharArray()) if (freq[c] > freq[best]) best = c;
        return best;
    }

    // Reverse words
    static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        for (int i=0; i<words.length; i++) {
            char[] arr = words[i].toCharArray();
            for (int l=0, r=arr.length-1; l<r; l++,r--) { char t=arr[l]; arr[l]=arr[r]; arr[r]=t; }
            words[i] = new String(arr);
        }
        return String.join(" ", words);
    }

    public static void main(String[] args) {
        System.out.println("=== Caesar Cipher ===");
        String msg = "Hello World";
        String enc = encrypt(msg, 3);
        System.out.println("Original: " + msg);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + decrypt(enc, 3));

        System.out.println("\n=== Anagram Checker ===");
        String[][] pairs = {{"listen","silent"},{"hello","world"},{"Astronomer","Moon starer"},{"Java","Vaja"}};
        for (String[] p : pairs)
            System.out.printf("%-15s %-15s → %s%n", p[0], p[1], isAnagram(p[0],p[1]) ? "ANAGRAM" : "not anagram");

        System.out.println("\n=== Most Frequent Char ===");
        String[] words = {"programming","mississippi","aabbccddee"};
        for (String w : words) System.out.printf("%-20s → '%c'%n", w, mostFrequent(w));

        System.out.println("\n=== Reverse Each Word ===");
        System.out.println(reverseWords("Hello World Java 21"));
    }
}
