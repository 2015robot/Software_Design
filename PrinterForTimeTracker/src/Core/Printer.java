package Core;

public class Printer {

  private Activity a = null;

  public Printer(Activity act) {
    this.a = act;
  }

  public void printTree() {
    a.acceptVisitor(new PrinterVisitor());
  }
}
