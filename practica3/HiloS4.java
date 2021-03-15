public class HiloS4 extends Thread{
  private String name;
  private int a;
  private int b;
  private int c;
  private int d;
  private int e;

  public HiloS4(String name, int a, int b, int c){
    this.name = name;
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public void run(){
    d = a + b;
    System.out.println(name + ": d = a + b = " + d);
    e = b + c;
    System.out.println(name + ": e = b + c = " + e);
  }

  public int getD(){
    return d;
  }

  public int getE(){
    return e;
  }
}
