public class HiloS3 extends Thread{
  private String name;
  private int c;

  public HiloS3(String name, int c){
    this.name = name;
    this.c = c;
  }

  public void run(){
    c += 15;
    System.out.println(name + ": c = z + 15 = " + c);
  }

  public int getC(){
    return c;
  }
}
