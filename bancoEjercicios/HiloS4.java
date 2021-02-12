public class HiloS4 extends Thread{
  private String name;
  private int a;
  private int b;
  private int d;
  private int e;

  public HiloS4(String name, int a, int b){
    this.name = name;
    this.a = a;
    this.b = b;
  }

  public void run(){
    d = a + b;
    System.out.println(name + ": "+ a + " + " + b + " = " + d);
    e = (a + b) * 2;
    System.out.println(name + ": ("+ a + " + " + b + ") x 2 = " + e);
  }

  public int getD(){
    return d;
  }

  public int getE(){
    return e;
  }
}
