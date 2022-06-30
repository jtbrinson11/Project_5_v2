import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

public class Project_5_v2 {

        static Scanner scan = new Scanner(System.in);
        static String[] words = {"Joel", "Vectrex", "Luke", "Iron", "Rhododendron", "Stearman", "Eagle", "Taco", "Pizza", "Capacitor", "Blaze", "Scion", "Galaga", "Boeing", "Konami"};
        static String[] wrong = {"\n+----------+ \n" + "           | \n" + "           | \n" + "           | \n" + "            === \n", "\n+----------+ \n" + " O         | \n" + "           | \n" + "           | \n" + "            === \n", "\n+----------+ \n" + " O         | \n" + " |         | \n" + "           | \n" + "            === \n", "\n+----------+ \n" + " O         | \n" + " |         | \n" + " |         | \n" + "            === \n", "\n+----------+ \n" + " O         | \n" + " |         | \n" + " |         | \n" + "/           === \n", "\n+----------+ \n" + " O         | \n" + " |         | \n" + " |         | \n" + "/ \\         === \n", "\n+----------+ \n" + " O         | \n" + "/|         | \n" + " |         | \n" + "/ \\         === \n", "\n+----------+ \n" + " O         | \n" + "/|\\        | \n" + " |         | \n" + "/ \\         === \n"};
        static String[] word;
        static String userInput = "", wordPick = "", missedLetters = "", letUsed;
        static int wordLen, wordUse = 0, numWrong;
        static boolean done = false, complete = false;


        static void setInput(String input)
        {
            try {
                userInput = input;
            }
            catch (Exception e)
            {
                System.out.println("Oops, something went wrong. Exiting the game . . .");
                System.exit(-1);
            }
        }

        static String getInput()
        {
            return userInput;
        }

        static void computeOutcome(String input)
        {
            input = input.toLowerCase();
            char let = input.charAt(0);
            wordPick = wordPick.toLowerCase();
            int letNum = wordPick.indexOf(input);

            boolean found = false;
            if (letNum != -1)
            {
                found = true;
            }

            if (found == false)
            {
                System.out.println(wrong[++numWrong]);
                missedLetters += " " + input.toUpperCase();
                System.out.println(missedLetters);
                for (int i = 0; i < word.length; i++)
                {
                    System.out.print(word[i] + " ");
                }
            }
            else
            {
                for (int i = 0; i < wordLen; i++)
                {
                    if (wordPick.charAt(i) == let)
                    {
                        word[i] = input.toUpperCase();
                    }
                }
                System.out.println(wrong[numWrong]);
                System.out.println(missedLetters);
                for (int i = 0; i < word.length; i++)
                {
                    System.out.print(word[i] + " ");
                }
            }

            char done = ' ';
            for (int i = 0; i < word.length; i++)
            {
                String letter = word[i];
                char letCur = letter.charAt(0);
                if (done == letCur)
                {
                    complete = false;
                    break;
                }
                else
                {
                    complete = true;
                }
            }
        }

        static void playGame()
        {
            numWrong = 0;
            wordUse = (int)(Math.random() * (15 - 1) - 1);
            wordLen = words[wordUse].length();
            wordPick = words[wordUse];
            word = new String[wordLen];
            missedLetters = "Missed letters: ";
            letUsed = "";
            done = false;
            complete = false;
            System.out.println("\nH A N G M A N \n");
            System.out.println(wrong[0]);
            System.out.println(missedLetters);

            for (int i = 0; i < word.length; i++)
            {
                word[i] = " _ ";
            }
            for (int i = 0; i < word.length; i++)
            {
                System.out.print(word[i]);
            }
            while(numWrong != 7)
            {
                if (!complete)
                {
                    boolean validAnswer = false;
                    while (!validAnswer)
                    {
                        System.out.println("\n Guess a letter: ");
                        userInput = scan.nextLine();
                        int letIndex = letUsed.indexOf(userInput);
                        if (letIndex != -1)
                        {
                            System.out.println("Sorry, that letter is already used.\n");
                        }
                        else
                        {
                            letUsed = letUsed + userInput;
                            setInput(userInput);
                            validAnswer = true;
                        }
                    }

                    String guess = getInput();
                    computeOutcome(guess);
                }
                else
                {
                    break;
                }
            }
        }

        public static void main(String[] args)
        {
            while(!done)
            {
                playGame();
                if (complete)
                {
                    System.out.println("\n\nCongratulations, you guessed my word!");
                    System.out.println("Do you want to play again (y/n): ");
                    userInput = scan.nextLine();
                    switch (userInput)
                    {
                        case "Y":
                            System.out.println("Ok, I'll reset the game.");
                            break;
                        case "y":
                            System.out.println("Resetting the game, then.");
                            break;
                        case "N":
                            System.out.println("Ok, good-bye for now.");
                            done = true;
                            break;
                        case "n":
                            System.out.println("Good-bye for now.");
                            done = true;
                            break;
                        default:
                            System.out.println("Sorry, I didn't understand that. Exiting by default . . .");
                            done = true;
                            break;
                    }
                }
                else if (numWrong == 7)
                {
                    System.out.println("\n\nSorry, you ran out of guesses.");
                    System.out.println("The word was: " + words[wordUse] + ".");
                    System.out.println("Do you want to play again (y/n): ");
                    userInput = scan.nextLine();
                    switch (userInput)
                    {
                        case "Y":
                            System.out.println("Ok, I'll reset the game.");
                            break;
                        case "y":
                            System.out.println("Resetting the game, then.");
                            break;
                        case "N":
                            System.out.println("Ok, good-bye for now.");
                            done = true;
                            break;
                        case "n":
                            System.out.println("Good-bye for now.");
                            done = true;
                            break;
                        default:
                            System.out.println("Sorry, I didn't understand that. Exiting by default . . .");
                            done = true;
                            break;
                    }
                }
            }
        }
    }
