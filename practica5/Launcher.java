public class Launcher{
  public static void main(String[] args){
    if(args.length < 1){
      System.out.println("La forma de ejecutar el programa es:");
      System.out.println("java Launcher numero");
      return;
    }
    try{
      Hilo h1 = new Hilo(Integer.parseInt(args[0]));
      h1.start();
      h1.join();
    }catch(InterruptedException e){  }
  }
}
