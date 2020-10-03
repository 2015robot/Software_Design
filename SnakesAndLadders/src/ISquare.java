public class ISquare {

    public void moveForward ( int moves ) {
      assert moves >0 : "non - positive moves ";

      // the player asks to the square he/ she is in that it moves him/ her
      square.leave ( this );
      square = square.moveAndLand ( moves );
      square.enter ( this );
    }

  public ISquare moveAndLand ( int moves ) {
    int lastPosition = game.findLastSquare (). getPosition ();
    // the game object knows ( holds a list of) all the squares , and each
    // square has a reference to the game object in order to ask it which
    // is the number of the last square , get the square at ’moves ’ positions
    // ahead , and get the next square whatever it is ( ’ moves ’ ahead or home
    // square )
    if ( position + moves > lastPosition ) {
      System . out . println (" Should go to " + ( position + moves +1)
          + " beyond last square " + ( lastPosition +1)
          + " so don ’t move ");
      return this ;
      } else {
      System . out . println (" move from " + ( position +1) + " to "
          + ( findRelativeSquare ( moves ).getPosition()+1));
      return findRelativeSquare ( moves ).landHereOrGoHome ();
      }
    }

    private ISquare findRelativeSquare (int moves ) {
    // this is a forwarding method to make code more readable , making the code
    // tell ’’what ’’, not ’’how ’’
    // note : it returns some type of square , that is , an ISquare
    return game.findSquare ( moves + position );
    }

    public ISquare landHereOrGoHome () {
    // note : it returns some type of square , that is , an ISquare
    if ( isOccupied()) {
      System.out.println(" square " + ( position +1) + " is occupied ");
      } else {
      System.out.println(" land at " + ( position +1));
      }
    return isOccupied() ? findFirstSquare () : this ;
    }

    private ISquare findFirstSquare () {
    // another forwarding method to make code more readable
    return game.firstSquare ();
    }

}
