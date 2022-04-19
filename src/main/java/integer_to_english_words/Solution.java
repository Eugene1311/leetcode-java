package integer_to_english_words;

import java.util.Map;

class Solution {
    private final Map<Integer, String> intsToWords = Map.ofEntries(
            Map.entry(1, "One"),
            Map.entry(2, "Two"),
            Map.entry(3, "Three"),
            Map.entry(4, "Four"),
            Map.entry(5, "Five"),
            Map.entry(6, "Six"),
            Map.entry(7, "Seven"),
            Map.entry(8, "Eight"),
            Map.entry(9, "Nine"),
            Map.entry(10, "Ten"),
            Map.entry(11, "Eleven"),
            Map.entry(12, "Twelve"),
            Map.entry(13, "Thirteen"),
            Map.entry(14, "Fourteen"),
            Map.entry(15, "Fifteen"),
            Map.entry(16, "Sixteen"),
            Map.entry(17, "Seventeen"),
            Map.entry(18, "Eighteen"),
            Map.entry(19, "Nineteen"),
            Map.entry(20, "Twenty"),
            Map.entry(30, "Thirty"),
            Map.entry(40, "Forty"),
            Map.entry(50, "Fifty"),
            Map.entry(60, "Sixty"),
            Map.entry(70, "Seventy"),
            Map.entry(80, "Eighty"),
            Map.entry(90, "Ninety"),
            Map.entry(100, "Hundred"),
            Map.entry(1000, "Thousand"),
            Map.entry(1_000_000, "Million"),
            Map.entry(1_000_000_000, "Billion")
    );
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int billions = num / 1_000_000_000;

        int millions = (num - (billions * 1_000_000_000)) / 1_000_000;

        int thousands = (num  - (billions * 1_000_000_000) - (millions * 1_000_000)) / 1000;

        int hundreds = (num - (billions * 1_000_000_000) - (millions * 1_000_000) - (thousands * 1000)) / 100;

        int tens = (num - (billions * 1_000_000_000) - (millions * 1_000_000) - (thousands * 1000) - (hundreds * 100)) / 10;

        int numbers = num - (billions * 1_000_000_000) - (millions * 1_000_000) - (thousands * 1000) - (hundreds * 100) - (tens * 10);

        if (tens < 2) {
            numbers += tens * 10;
            tens = 0;
        }

        String result = getStringFromNumber(billions, 1_000_000_000)
                + getStringFromNumber(millions, 1_000_000)
                + getStringFromNumber(thousands, 1000)
                + getStringFromNumber(hundreds, 100)
                + getStringFromNumber(tens * 10, 0)
                + getStringFromNumber(numbers, 0);

        return result.trim();
    }

    private String getStringFromNumber(int number, int degree) {
        if (number > 20 && degree >= 1000) {
            int hundreds = number / 100;
            int tens = (number - (hundreds * 100)) / 10;
            int numbers = number - (hundreds * 100) - (tens * 10);

            if (tens < 2) {
                numbers += tens * 10;
                tens = 0;
            }

            return getStringFromNumber(hundreds, 100)
                    + getStringFromNumber(tens * 10, 0)
                    + getStringFromNumber(numbers, 0)
                    + intsToWords.get(degree) + " ";
        }


        if (number != 0 && intsToWords.get(number) != null) {
            return intsToWords.get(number) + " " + (intsToWords.get(degree) == null ? "": intsToWords.get(degree) + " ");
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberToWords(25942));
    }
}
