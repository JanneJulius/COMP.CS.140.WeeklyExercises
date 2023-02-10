import java.util.Arrays;

public class Median {
    public static void main(String[] args) {
        double[] numbers = new double[args.length];

        // Convert command line parameters to double values
        for (int i = 0; i < args.length; i++) {
            numbers[i] = Double.parseDouble(args[i]);
        }

        // Sort the numbers
        Arrays.sort(numbers);

        // Calculate the median
        double median;
        if (numbers.length % 2 == 0) {
            median = (numbers[numbers.length / 2] + numbers[numbers.length / 2 - 1]) / 2;
        } else {
            median = numbers[numbers.length / 2];
        }

        // Print the median
        System.out.println("Median: " + median);
    }
}
