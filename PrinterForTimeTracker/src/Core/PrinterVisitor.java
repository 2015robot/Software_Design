package Core;

public class PrinterVisitor {
  public void visitTask(Task t) {
    System.out.println("Task " + t.getName() + "   child of " + (t.getFather() == null?"null":t.getFather().getName()) + "   " + t.getInitialDate() + "   " + t.getFinalDate() + "   " + t.getDuration());
    for(Interval inter : t.getIntervals()) {
      inter.acceptVisitor(new PrinterVisitor());
    }
  }

  public void visitProject(Project p) {
    System.out.println("Project " + p.getName() + "   child of " + (p.getFather() == null?"null":p.getFather().getName()) + "   " + p.getInitialDate() + "   " + p.getFinalDate() + "   " + p.getDuration());
    for(Activity act : p.getChilds()) {
      act.acceptVisitor(new PrinterVisitor());
    }
  }

  public void visitInterval(Interval i) {
    System.out.println("Interval " + "   child of " + (i.getFather().getName()) + "   " + i.getInitialDate() + "   " + i.getFinalDate() + "   " + i.getDuration());
  }
}
