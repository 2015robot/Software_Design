public final class Player {
  private Square square = null ;
  private String name ;
  private boolean dead = false;

    public Square getSquare () {
      return square ;
    }

    public void setSquare ( Square square ) {
      this . square = square ;
    }

    public int position () {
      return square . getPosition ();
    }

    public String getName () {
     return name ;
    }

    public Player ( String strname ) {
     name = strname ;
    }

    @Override
    public String toString () {
     return name ;
    }

    public boolean wins () {
     return square . isLastSquare ();
    }

    public void moveForward ( int moves ) {
     assert moves >0 : "non - positive moves ";
     square . leave ( this );
     square = square . moveAndLand ( moves );
     if (square.isDeath()) {
       this.dead = true;
     } else {
       square.enter(this);
     }
    }

    public boolean getPlayerStatus() {return dead;}
}
