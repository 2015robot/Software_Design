public final class DeathRole extends SquareRole{
  public DeathRole(Square s) {
    super(s);
  }

  @Override
  public boolean isDeath() {
    return true;
  }
}
