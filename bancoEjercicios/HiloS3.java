public class HiloS3 extends Thread{
  private String name;
  private int a;

  public HiloS3(String name, int a){
    this.name = name;
    this.a = a;
  }

  public void run(){
    a += 15;
    System.out.println(name + ": a + 15 = " + a);
  }

  public int getA(){
    return a;
  }
}
