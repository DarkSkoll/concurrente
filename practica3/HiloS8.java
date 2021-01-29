public class HiloS8 extends Thread{
  private String name;
  private int f;
  private int g;
  private int total;

  public HiloS8(String name, int g, int f){
    this.name = name;
    this.f = f;
    this.g = g;
  }

  public void run(){
    total = g + f;
    System.out.println(name + ": " + g + " + " + f + " = " + total);
  }

  public int getTotal(){
    return total;
  }
}
