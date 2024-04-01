import java.util.*;
public class Player    //Player class declaration  
{
	private String name; //Class attributes, private to be only managed by the class functions
	private int bet; 
	private ArrayList<Cards> hand;
	private int score;
	private List<Cards> usedCards;
	public static List<Cards> deck;
    
	private List<Cards> shuffleDeck(List<Cards> deck) //method to shuffle the deck of cards
	{
        List<Cards> shuffledDeck = new ArrayList<>(deck);
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }

	private Cards dealWithoutReplacement(List<Cards> deck) //method to ensure no replacements in the game
	{
	        if (deck.isEmpty()) 
	        {
	            System.out.println("No cards left in the deck.");
	            return null; 
	        }
	        
	        Cards dealtCard = deck.remove(0);
	        usedCards.add(dealtCard);
	        return dealtCard;
	        
	}
	
	private List<Cards> createDeck() //method to create the card deck
	{
	    List<Cards> deck = new ArrayList<>();

	    String[] suits = {"s", "h", "d", "c"};
	    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	    for (String suit : suits) {
	        for (String rank : ranks) {
	            Cards card = new Cards(suit, rank);
	            deck.add(card);
	        }
	    }

	    return deck;
	}
	
	public Player()   //class default constructor to be called when creating a Player obj.
	{
		bet = 2;            
		score = 0;
		hand = new ArrayList<>();
		usedCards = new ArrayList<>(); //register the used cards
		deck = createDeck(); //create a deck for the game
        shuffleDeck(deck); //shuffle the card deck
	}
	
	public Player(String n) //Class constructor, specifically used to create the Dealer.
	{
		name = n;
		score = 0;
		hand = new ArrayList<>();
		usedCards = new ArrayList<>(); 
	}
	
	public int getBet() //get function used to get the bet value
	{
		return bet;
	}
	
	 public void resetHand() //function to reset the players hands
	{
	        hand = new ArrayList<>();
	        score  = 0;
	}
	 public boolean hitOrStand(List<Cards> deck) //function to hit or stand depending on the deck
	 {
		    if (this.hand.size() == 2 && this.score == 21) //checks if the hand contains a black Jack
		    {
		        System.out.println("***~~~~ Black Jack! ~~~~***");
		        this.addBet(); // this called function will be added on top of the normal bet adding making it double
		        return false;
		    } 
		    else if (score < 21) 
		    {
		        System.out.println("*****Do you want to Hit or Stand?*****");
		        Scanner scanner = new Scanner(System.in);
		        String hos = scanner.nextLine();
		        String lowerInput = hos.toLowerCase();
		        if (lowerInput.equals("hit")) {
		            System.out.println("Player chose: Hit!");
		            this.deal(deck); // Pass the deck to the modified deal method
		            return true;
		        } else if (lowerInput.equals("stand")) {
		            System.out.println("Player chose: Stand!");
		            return false;
		        } else {
		            System.out.println("Invalid action!");
		            return hitOrStand(deck);
		        }
		    } 
		    else 
		    {
		        return false;
		    }
	 }
	public void deal(List<Cards> availableCards) //method to deal the cards considering the deck
	{
	    if (availableCards.isEmpty()) { //consider the case no more cards are available in the deck
	        System.out.println("No cards left in the deck.");
	        return;
	    }

	    Cards card = dealWithoutReplacement(availableCards); //cards are created in a way that there are no replacements
	    hand.add(card);

	    if (card.getValue() == 11 && this.score > 10) {
	        score += 1;
	    } else {
	        score += card.getValue();
	    }

	    System.out.print("Card dealt to " + name + ": ");

	    if (this.name.equals("Dealer") && hand.size() == 1) {
	        card.getHand();
	        System.out.print("(Score: " + this.score + ")");
	    } else if (this.name.equals("Dealer")) {
	        System.out.print("xx");
	    } else {
	        card.getHand();
	        System.out.print("(Score: " + this.score + ")");
	    }

	    System.out.println();
	}
	
	public void setName() //method to set the name attribute 
	{
		System.out.println("Set player name: ");
		Scanner newName = new Scanner(System.in); //scan the player's input
		name = newName.nextLine(); //store the input in the name attribute
	}
	
	public void getName() //getter method to display the name
	{
		System.out.println("Player name: "+ name);
	}
	
	public void printScore() //method to display the obj. score
	{
		System.out.println("Score: " + score);
	}
	
	public void dealerBot(List<Cards> deck) //method for the dealer to pick up cards until his hand is greater than 16
	{
	    while (this.score < 16) 
	    {
	        Cards card = dealWithoutReplacement(deck);
	        if (card == null) 
	        {
	            System.out.println("No more cards in the deck.");
	            break; 
	        }
	        hand.add(card);
	        score += card.getValue();
	    }
	}
	
	public int getScore() //method to return the score as an int
	{
		return score;
	}
	
	public void addBet() //method to add bet
	{
		bet = bet+1;
	}
	
	public void subBet() //method to substract from the bet
	{
		bet = bet-1;
	}
	
	
	public static void main(String[] args) //main function
	{
		System.out.println("*******************************************");
		System.out.println("****** Enter Start to Play the game *******");
		Scanner start = new Scanner(System.in); //scan the user input to start the game
		String scan = start.nextLine(); //store the input
		String com = scan.toLowerCase(); //convert input to lower case
		if (com.equals("start")) //if the input equal start...
		{
			boolean cont = true; 
			Player player1 = new Player(); //instantiate a Player object using the default constructor
			Player dealer = new Player("Dealer"); //instantiate a Player object using the constructor that receives a string to create the Dealer
			player1.setName(); //call setName() method
			while (cont == true)
			{
				System.out.println("\n**************************************");
				System.out.println(" Your current bet is: " + player1.getBet());
				System.out.println("\n*** Player places one bet ***");
				System.out.println("Stake: 1\n");
				player1.deal(deck); //call the deal method four times (two cards for each player)
				dealer.deal(deck);
				player1.deal(deck);
				dealer.deal(deck);
				boolean x = true; //instantiate a boolean variable x to be true
				while (x == true) //while x is equal to true...
				{	
					System.out.println("\n**************************************");
					//System.out.println("\n");
					x = player1.hitOrStand(deck); //call hit or stand method and store the result into x
				}
				dealer.dealerBot(deck); //call the dealer method to follow the game logic
				int y = player1.getScore(); //store the player's score
				int z = dealer.getScore(); //store the dealer's score
				System.out.println("Your score is: " + player1.getScore()); //display player's score
				System.out.println("Dealer score is: " + dealer.getScore()); //display dealer's score
				if (y > z & y <= 21) //if player's score is greater than dealer's and less or equal to 21 then...
				{
					System.out.println("You won!"); //player wins
					player1.addBet();
				}
				else if (y == z) //else if the game ends in a draw
				{
					System.out.println("Push!"); //the result is push
				}
				else if (y > 21) //else if the player has busted...
				{
					System.out.println("Bust, Dealer wins!"); //Player loses (giving the player the reason)
					player1.subBet();
				}
				else if (z > 21) //else if the dealer has busted...
				{
					System.out.println("Bust, You win!"); //the player wins
					player1.addBet();
				}
				else //else if the dealer's score is greater than the player's...
				{
					System.out.println("Dealer wins!"); //the dealer wins
					player1.subBet();
				}
				player1.resetHand();
				dealer.resetHand();
				if (player1.getBet() > 0)
				{
					System.out.println("\n**************************************");
					System.out.println("Your current bet is: " + player1.getBet());
					System.out.println("Do you want to continue?");
					System.out.println("[Yes //  No]");
					System.out.println("**************************************");
					Scanner sc = new Scanner(System.in); //scan the user input to start the game
					String sc_cn = sc.nextLine(); //store the input
					String con = sc_cn.toLowerCase();
					if (con.equals("yes"))
					{
						cont = true;
					}
					else if(con.equals("no"))
					{
						cont = false;
						System.out.println("\n**************************************");
						System.out.println("GAME OVER !");
						System.out.println("**************************************");
					}
				}
				else
				{
					cont = false;
					System.out.println("**************************************");
					System.out.println("GAME OVER !");
					System.out.println("**************************************");
			    	}
				}	
		   }
		   else //if the input is not start...
		   {
			System.out.println("Invlaid start"); //give a warning and end the main function
			return;
		   }
	 }
}
