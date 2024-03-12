//import needed libraries
import java.util.*;
import java.io.*;
public class main 
// Name : Oliwier Jakubiec
// Date : 12/3/2024
// Purpose : Using Dictionary class and maintaining text files
{
    // Method to delete a char from an array
    public static char[] deleteChar(char intArray[], int arrayLength, int location)
    {
        int index;
        for (index = location; index < arrayLength; index++ )
            {
                    if (index + 1 != arrayLength)
                        {
                            intArray[index] = intArray[index+1] ;
                        }         
            }
        char newArray[] = new char[arrayLength-1];
        for (index = 0; index < arrayLength-1; index++)
            {
                newArray[index] = intArray[index];
            }
        return newArray;
    }
    // Method to jumble a string
    public static String jumble(String word)
    {
        char letters[];
        int number1;
        int index;
        String jumbleWord;
        Random randomNumber = new Random();

        jumbleWord = "";
        letters = new char[word.length()];
        for (index = 0; index < word.length(); index++)
            {
                letters[index] = word.charAt(index);
            }
        for (index = 0; index < word.length(); index++)
            {
                number1 = randomNumber.nextInt(letters.length);
                jumbleWord = jumbleWord + letters[number1];
                letters = deleteChar(letters, letters.length, number1);
            }
        return jumbleWord;
    }
    // Finds the index position of the string in the array, if not found return -1
    public static int findInArray(EFDictionary theArray[], String theString, String version)
    {   
        int index;
        if (version.equalsIgnoreCase("English"))
            {
                for (index = 0; index < theArray.length; index++)
                    {
                        if (theString.equals(theArray[index].getEWord()))
                            {
                                return index;
                            }
                    }
            }
        else
            {
                for (index = 0; index < theArray.length; index++)
                    {
                        if (theString.equals(theArray[index].getFWord()))
                            {
                                return index;
                            }
                    }
            }
        return -1;
    }
    public static void main(String[] args) 
    {
        // Variable Decleration
        String englishWords;
        String frenchWords;
        EFDictionary wordArray[];
        EFDictionary tempDict;
        String selectedLang;
        Random random = new Random();
        String jumbleWord;
        String guess;
        String unjumbledWord;
        int randint;
        int arrayLength;
        int index;
        
        arrayLength = 0; // Initialise
        try // try catch loop incase an error with opening the file occurs
            {
                File openedFile = new File("WordLists\\englishWords.txt"); // Opens the file
                Scanner fileReader = new Scanner(openedFile);  // Makes a scanner object to read through the file
                index = 0;
                while(fileReader.hasNextLine())   //Loops through each line in the file and counts the total amount of lines
                    {
                        englishWords = fileReader.nextLine();
                        index ++;
                    }
                arrayLength = index;
                fileReader.close();
            }
        catch (FileNotFoundException e)
            {
                System.out.println("An error has occured");
            }
        // Initialise word array as the length of the amount of lines in the files
        wordArray = new EFDictionary[arrayLength];
        try 
            {
                // Open both files and make two scanners
                File openedFile = new File("WordLists\\englishWords.txt");
                File openedFile2 = new File("WordLists\\frenchWords.txt");
                Scanner fileReader = new Scanner(openedFile);
                Scanner fileReader2 = new Scanner(openedFile2);
                index = 0;
                while(fileReader.hasNextLine())
                    {
                        // Adds both the english and French word from each line to a new Dictionary Object in the array
                        englishWords = fileReader.nextLine();
                        frenchWords = fileReader2.nextLine();
                        tempDict = new EFDictionary(englishWords, frenchWords);
                        wordArray[index] = tempDict;
                        index ++;
                    }
                // Close both files
                fileReader.close();
                fileReader2.close();
            }
        catch (FileNotFoundException e)
            {
                System.out.println("An error has occured");
            }
        // Uncomment to print the dictionary Array!!
        // for ( index = 0; index < 5; index++)
        //     {
        //         System.out.println(wordArray[index].toString());
        //     }
        System.out.print("Which language do you want to guess? (French/English): ");
        selectedLang = EasyIn.getString();  // EasyIn for inputting text
        
        randint = random.nextInt(wordArray.length-1);  // Generate a random number between 0 and the length of the array 
        
        if (selectedLang.equalsIgnoreCase("French"))  // If guessing a French Word
            {
                unjumbledWord = wordArray[randint].getFWord();  // Picks a random word from the french word list
                jumbleWord = jumble(unjumbledWord);             // Jumbles up the word randomly
                System.out.println("French Word: " + jumbleWord);
                System.out.print("Enter English equivalent: ");
                guess = EasyIn.getString();

                // If the index position of the guessed word in the array is equal to index position of the french word in the array
                if(findInArray(wordArray, guess, "English") == findInArray(wordArray, unjumbledWord, "French"))
                    {
                        System.out.println("Correct! The word was " + unjumbledWord);
                    }
                else
                    {
                        System.out.println("Incorrect! The word was " + unjumbledWord);
                    }
            }
        else if (selectedLang.equalsIgnoreCase("English"))  // If guessing the english word has been selected
            {
                unjumbledWord = wordArray[randint].getEWord();  // Picks a random english word
                jumbleWord = jumble(unjumbledWord);             // Jumbles up the english word
                System.out.println("English Word: " + jumbleWord);
                System.out.print("Enter French equivalent: ");
                guess = EasyIn.getString();

                // If the index position of the guessed word in the array is equal to index position of the english word in the array
                if(findInArray(wordArray, guess, "French") == findInArray(wordArray, unjumbledWord, "English"))

                    {
                        System.out.println("Correct! The word was " + unjumbledWord);
                    }
                else
                    {
                        System.out.println("Incorrect! The word was " + unjumbledWord);
                    }
            }
        else  // If neither English or French is selected
            {
                System.out.println("Invalid Input");
            }
    }
}

