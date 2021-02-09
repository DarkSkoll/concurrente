public class Hilo extends Thread{
  private String name;
  private int numero;
  public int resultado;
  public static int contador = 0;

  public Hilo(int numero){
    this.name = "Hilo "+String.valueOf(contador);
    contador++;
    this.numero = numero;
  }

  public void run(){
    if(numero == 1 || numero == 0){
      resultado = 1;
      System.out.println(name + ": f(" + numero + ") = "+resultado);
    }else{
      try{
        Hilo h1 = new Hilo(numero-1);
        Hilo h2 = new Hilo(numero-2);
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        resultado = (5*h1.resultado)-(6*h2.resultado);
        System.out.println(name + ": f(" + numero + ") = "+resultado);
      }catch(InterruptedException ex){
        System.out.println("Error al sincronizar los hilos");
      }
    }
  }
}
