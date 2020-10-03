import java.util.LinkedList;
import java.util.Random;

public final class Game {
  private LinkedList<Player> players = new LinkedList<Player>();
  private Board board;
  private Player winner;
  private ISquare square = null;

  private final class Die {
    private static final int MINVALUE = 1;
    private static final int MAXVALUE = 6;

    public int roll () {
      return random ( MINVALUE , MAXVALUE );
      }

    private int random (int min , int max ) {
      assert min < max ;
      return ( int) ( min + Math . round (( max - min ) * Math . random ()));
      }
  }

  private void movePlayer (int roll ) {
    Player currentPlayer = players . remove (); // from the head
    currentPlayer . moveForward ( roll );
    players . add ( currentPlayer ); // to the tail
    if ( currentPlayer . wins ()) {
      winner = currentPlayer ;
    }
  }
  public Game ( String [] playerNames , int numSquares , int [][] snakes , int [][] ladders ) {
    makeBoard(numSquares, ladders, snakes);
    makePlayers(playerNames);
  }

  private void makeBoard (int numSquares , int [][] ladders , int [][] snakes ) {
    board = new Board ( numSquares , ladders , snakes );
    }

    private void makePlayers ( String [] playerNames ) {
    assert playerNames . length >0 : " There must be some player " ;
    System . out . println (" Players are : ");
    int i =1;
    for ( String str : playerNames ) {
      Player player = new Player ( str );
      players . add ( player ); // adds to the end
      System . out . println ( i + ". " + str );
      i ++;
      }
    }

    public void play () {
      assert ! players . isEmpty () : "No players to play ";
      assert board != null : "No scoreboard to play ";

      Die die = new Die ();
      startGame ();

      System . out . println (" Initial state : \n" + this );
      while (notOver()) {
        int roll = die.roll();
        System . out . println (" Current player is " + currentPlayer () + " and rolls " + roll );
        movePlayer(roll);
        System . out . println (" State : \n" + this );
      }
      System . out . println ( winner + " has won.");
    }

    private void startGame () {
      placePlayersAtFirstSquare ();
      winner = null ;
    }

    private void placePlayersAtFirstSquare () {
      for ( Player player : players ) {
        board . firstSquare (). enter ( player );
      }
    }

    private boolean notOver () {
      return winner == null ;
    }

    @Override
    public String toString () {
      String str = new String ();
      for ( Player player : players ) {
        str += player . getName () + " is at square " + ( player . position ()+1) + "\n";
      }
      return str ;
    }

  private void createGame (int numSquares , int [][] ladders , int [][] snakes ) {
    // make first , last and regular squares
    squares.add (new FirstSquare (0 , this ));
    for (int position = 1 ; position < numSquares -1 ; position ++) {
      Square square = new Square ( position , this );
      squares.add ( square );
      }
    squares.add (new LastSquare ( numSquares -1 , this ));

    // make snake squares which replace some already created square
    for (int i =0; i < snakes.length ; i ++) {
      assert snakes[i].length == 2;
      // snakes and ladders are defined by pairs of positions
      int fromPosition = snakes[i][0] -1;
      int toPosition = snakes[i][1] -1;
      int transport = toPosition - fromPosition ;
      squares.set ( fromPosition , new Snake ( fromPosition ,this , transport ));
      }

    for (int i =0; i < ladders . length ; i ++) {
      assert ladders [i]. length == 2;
      int fromPosition = ladders [i][0] -1;
      int toPosition = ladders [i][1] -1;
      int transport = toPosition - fromPosition ;
      squares.set ( fromPosition , new Ladder ( fromPosition ,this , transport ));
      }
    // TODO : redundant code , could snakes be the same as ladders but with a
    // negative transport ?
    }

    public void moveForward(int moves) {
      assert moves > 0 : "non-positive moves";

      square.leave(this);
      square = square.moveAndLand(moves);
      square.enter(this);
    }

    Player currentPlayer () {
     assert players . size () >0;
     return players . peek ();
    }
}
