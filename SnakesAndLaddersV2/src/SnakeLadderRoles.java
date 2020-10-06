public final class SnakeLadderRoles extends SquareRole {
  private int transport;
  private String role;

  public SnakeLadderRoles(Square s, int t, String role) {
    super(s);
    this.role = role;
    if (role.equals("snake")) {
      assert t < 0 : "A snake shift must be negative ";
    } else {
      assert t > 0: "A ladder shift must be positive ";
    }
    transport = t;
  }

  @Override public Square landHereOrGoHome () {
    if (role.equals("snake")) {
      System.out.println("snake from " + (square.getPosition() + 1) + " to " + (destination().getPosition() + 1));
    } else {
      System.out.println("ladder from " + (square.getPosition ()+1) + " to " + (destination().getPosition()+1));
    }
    return destination().landHereOrGoHome();
  }

  private Square destination () {
    return square.findRelativeSquare(transport);
  }
}
