public class Sudoku {
  
  private char[][] grid;
  //private char c;

  public Sudoku() {
    grid = new char[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        grid[i][j] = ' ';
      }
    }
  }

  public void set(int i, int j, char c) {
    if (i < 0 || i > 8 || j < 0 || j > 8) {
      System.out.println("Trying to access illegal cell (" + i + ", " + j + ")!");
      return;
    }
    if (c != ' ' && (c < '1' || c > '9')) {
      System.out.println("Trying to set illegal character " + c + " to (" + i + ", " + j + ")!");
      return;
    }
    grid[i][j] = c;
  }

  public boolean check() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = grid[i][j];
        if (c == ' ') {
          continue;
        }
        // Check row
        for (int k = j+1; k < 9; k++) {
          if (c == grid[i][k]) {
            System.out.println("Row " + i + " has multiple " + c + "'s!");
            return false;
          }
        }
      }
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = grid[i][j];
        if (c == ' ') {
          continue;
        }
        // Check column
        for (int k = i+1; k < 9; k++) {
          if (c == grid[k][j]) {
            System.out.println("Column " + j + " has multiple " + c + "'s!");
            return false;
          }
        }
      }
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = grid[i][j];
        if (c == ' ') {
          continue;
        }
       // Check 3x3 block
        int blockRow = i / 3 * 3;
        int blockCol = j / 3 * 3;
        for (int k = blockRow; k < blockRow + 3; k++) {
          for (int l = blockCol; l < blockCol + 3; l++) {
            if ((i != k || j != l) && c == grid[k][l]) {
              System.out.println("Block at (" + blockRow + ", " + blockCol + ") has multiple " + c + "'s!");
              return false;
            }
          }
        }
      }
    }
    return true;
}

  public void print() {

    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0){
        System.out.print("#####################################");
        System.out.println();
      }else{
        System.out.print("#---+---+---#---+---+---#---+---+---#");
        System.out.println();
      }
      for (int j = 0; j < 9; j++) {
        if (j == 0){
          System.out.print("#");
        }
        if (j == 2 || j == 5 || j == 8){
          System.out.print(" " + grid[i][j] + " #");
        }
        else{
          System.out.print(" " + grid[i][j] + " |");
        }      
      }
      System.out.println();
    }
    System.out.print("#####################################");
    System.out.println();
  } 
}




