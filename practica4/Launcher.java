import java.util.Random;

public class Launcher{
  public static void main(String[] args) {
    if(args.length < 2){
      System.out.println("La forma de ejecutar el programa es:");
      System.out.println("java Launcher tamañoarray1 tamañoarray2");
      return;
    }
    Random random = new Random();
    int[] arrayA = new Int[args[0]];
    int[] arrayB = new Int[args[1]];
    for(int i = 0; i < arrayA.length; i++){
      arrayA[i] = random.nextInt(100);
    }
    for(int i = 0; i < arrayB.length; i++){
      arrayB[i] = random.nextInt(100);
    }
  }
}
