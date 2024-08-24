import java.util.Scanner;
class Task2 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter the number of subjects:");
        int numSubjects = sc.nextInt();
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) 
        {
            System.out.println("Enter marks obtained in subject " + (i + 1) + " (out of 100):");
            marks[i] = sc.nextInt();
            totalMarks += marks[i];
        }
        double averagePercentage = (double) totalMarks / numSubjects;
        char grade;
        if (averagePercentage >= 90) 
        {
            grade = 'A';
        } 
        else if (averagePercentage >= 80) 
        {
            grade = 'B';
        } 
        else if (averagePercentage >= 70) 
        {
            grade = 'C';
        } 
        else if (averagePercentage >= 60) 
        {
            grade = 'D';
        } 
        else 
        {
            grade = 'F';
        }
        System.out.println("\nResults for " + name + ":");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        sc.close();
    }
}