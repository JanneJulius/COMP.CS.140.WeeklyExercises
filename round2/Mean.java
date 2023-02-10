
public class Mean {

  public static void main(String[] args){

    
    int length = args.length;
    if (length == 0){System.out.println("Mean: 0");}
    else{
      double sum = 0;
      for (int i = 0; i < length; i++){
        double asDouble = Double.parseDouble(args[i]);
        sum += asDouble;
      }
      double average = sum / length;
      System.out.println("Mean: "+ average);
    }
  }
}
