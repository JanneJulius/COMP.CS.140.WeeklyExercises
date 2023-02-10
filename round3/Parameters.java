import java.util.Arrays;

public class Parameters {
    public static void main(String[] args) {
        // Sort the command line parameters in alphabetical order
        Arrays.sort(args);

        // Find the maximum width of the first column (row numbers)
        int firstColWidth = String.valueOf(args.length).length();

        // Find the maximum width of the second column (parameters)
        int secondColWidth = 0;
        for (String arg : args) {
            secondColWidth = Math.max(secondColWidth, arg.length());
        }

        // Add extra space on each side of the columns
        firstColWidth += 2;
        secondColWidth += 2;

        // Print the top border of the table
        System.out.print("#");
        for (int i = 0; i < firstColWidth + secondColWidth + 2; i++) {
            System.out.print("#");
        }
        System.out.println();

        // Print the rows of the table
        for (int i = 0; i < args.length; i++) {
            // Print the left border of the row
            System.out.print("#");

            // Print the first column (row number)
            String rowNum = String.valueOf(i + 1 + " ");
            int numSpaces = firstColWidth - rowNum.length();
            for (int j = 0; j < numSpaces; j++) {
                System.out.print(" ");
            }
            System.out.print(rowNum);

            // Print the vertical separator
            System.out.print("| ");

            // Print the second column (parameter)
            String arg = args[i];
            System.out.print(arg);
            numSpaces = secondColWidth - arg.length();
            for (int j = 0; j < numSpaces-1; j++) {
                System.out.print(" ");
            }

            // Print the right border of the row
            System.out.println("#");

            if(i < args.length-1) {
              // Print the horizontal separator
              System.out.print("#");
              for (int j = 0; j < firstColWidth; j++) {
                System.out.print("-");
              }
              System.out.print("+");
              for (int j = 0; j < secondColWidth; j++) {
                System.out.print("-");
              }
              System.out.println("#");
            }
            
        }

        // Print the bottom border of the table
        System.out.print("#");
        for (int i = 0; i < firstColWidth + secondColWidth + 2; i++) {
            System.out.print("#");
        }
    }
}

