import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class Cards //Declare the class Cards
{
	private String suit; //private attributes of the card
	private String rank;
	private int card_num;
	private static List<Cards> availableCards = new ArrayList<>();

	static 
	{
	        initializeAvailableCards();
	}
	private static void initializeAvailableCards() //function to initialize all the possible cards combinations 
	{
        String[] suits = {"s", "h", "d", "c"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Cards card = new Cards(suit, rank);
                availableCards.add(card);
            }
        }
    }
	public Cards(String suit, String rank) { //cards constructor to create any card
	    this.suit = suit;
	    this.rank = rank;
        switch (rank) 
        {
	        case "Ace":
	            card_num = 11;
	            break;
	        case "2":
	        case "3":
	        case "4":
	        case "5":
	        case "6":
	        case "7":
	        case "8":
	        case "9":
	        case "10":
	            card_num = Integer.parseInt(rank);
	            break;
	        case "Jack":
	        case "Queen":
	        case "King":
	            card_num = 10;
	            break;
	        default:
	            break;
        }
	}

	public void getHand() //getter method to display the card
	{
		System.out.println(rank + suit);
	}
	public int getValue() //getter method to return the numeric value of the card as an int
	{
		return card_num; 
	}
	public String getRank() {
	    return rank;
	}
}
