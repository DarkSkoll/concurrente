public class HiloS6 extends Thread{
  private String name;
  private int d;
  private int e;
  private int f;

  public HiloS6(String name, int d, int e){
    this.name = name;
    this.d = d;
    this.e = e;
  }

  public void run(){
    f = d + e;
    System.out.println(name + ": " + d + " + " + e + " = " + d);
  }

  public int getF(){
    return f;
  }
}
