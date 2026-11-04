
public class Ex04 {
    public static void printMovieFromNbr(int nbr){

        String result = switch(nbr){
            case 3 -> "The Three Brothers";
            case 6 -> "The Sixth Sense";
            case 23 -> "The Number 23";
            case 28 -> "28 Days Later";
            default -> "I don't know.";
        };
        System.out.println(result); 
    }
}
