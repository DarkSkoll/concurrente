public class HiloS1 extends Thread{
  private String name;
  private int a;
  private int b;

  public HiloS1(String name, int a,int b){
    this.name = name;
    this.a = a;
    this.b = b;
  }

  public void run(){
    a *= 2;
    b *= 2;
    System.out.println(name + ": a * 2 = " + a);
    System.out.println(name + ": b * 2 = " + b);
  }

  public int getA(){
    return a;
  }

  public int getB(){
    return b;
  }
}
