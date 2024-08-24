import java.util.*;
class Task1 
{
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("YOU HAVE 3 ATTEMPTS");
        int totalScore = 0; 
        while (true) 
        {
            int c = 3, score = 0;
            Random random = new Random();
            int n = random.nextInt(100) + 1;
            while (c > 0) 
            {
                System.out.println("Enter your guess (between 1 and 100):");
                int g = sc.nextInt();
                c--;
                if (n == g) 
                {
                    score += c + 1; 
                    System.out.println("Correct guess");
                    break;
                } 
                else if (g > n) 
                {
                    System.out.println("Too high! Try Again");
                } 
                else 
                {
                    System.out.println("Too low! Try Again");
                }
                if (c == 0) 
                {
                    System.out.println("No attempts left. The correct number was: " + n);
                }
            }
            totalScore += score;
            System.out.println("Score for this round: " + score);
            System.out.println("Total Score: " + totalScore);
            System.out.println("ENTER 1 TO PLAY ANOTHER ROUND AND ANY OTHER KEY TO EXIT");
            int ch = sc.nextInt();
            if (ch != 1) 
            {
                break;
            } 
        }
        System.out.println("Final Total Score: " + totalScore);
        sc.close();
    }
}
