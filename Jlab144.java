import java.util.*;
import java.io.*;
public class Jlab144 
// Student Name : Oliwier Jakubiec
// Student Id Number : C00296807
// Date : 12/3/2024
// Purpose : Using Dictionary class and maintaining text files
{
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
        
        arrayLength = 0;
        try 
            {
                File openedFile = new File("WordLists\\englishWords.txt");
                Scanner fileReader = new Scanner(openedFile);
                index = 0;
                while(fileReader.hasNextLine())
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
        wordArray = new EFDictionary[arrayLength];
        try 
            {
                File openedFile = new File("WordLists\\englishWords.txt");
                File openedFile2 = new File("WordLists\\frenchWords.txt");
                Scanner fileReader = new Scanner(openedFile);
                Scanner fileReader2 = new Scanner(openedFile2);
                index = 0;
                while(fileReader.hasNextLine())
                    {
                        englishWords = fileReader.nextLine();
                        frenchWords = fileReader2.nextLine();
                        tempDict = new EFDictionary(englishWords, frenchWords);
                        wordArray[index] = tempDict;
                        index ++;
                    }
                fileReader.close();
                fileReader2.close();
            }
        catch (FileNotFoundException e)
            {
                System.out.println("An error has occured");
            }
        
        // for ( index = 0; index < 5; index++)
        //     {
        //         System.out.println(wordArray[index].toString());
        //     }
        System.out.print("Which language do you want to guess? (French/English): ");
        selectedLang = EasyIn.getString();
        
        randint = random.nextInt(wordArray.length-1);

        if (selectedLang.equalsIgnoreCase("French"))
            {
                unjumbledWord = wordArray[randint].getFWord();
                jumbleWord = jumble(unjumbledWord);
                System.out.println("French Word: " + jumbleWord);
                System.out.print("Enter English equivalent: ");
                guess = EasyIn.getString();

                if(findInArray(wordArray, guess, "English") == findInArray(wordArray, unjumbledWord, "French"))
                    {
                        System.out.println("Correct! The word was " + unjumbledWord);
                    }
                else
                    {
                        System.out.println("Incorrect! The word was " + unjumbledWord);
                    }
            }
        else if (selectedLang.equalsIgnoreCase("English"))
            {
                unjumbledWord = wordArray[randint].getEWord();
                jumbleWord = jumble(unjumbledWord);
                System.out.println("English Word: " + jumbleWord);
                System.out.print("Enter French equivalent: ");
                guess = EasyIn.getString();
                if(findInArray(wordArray, guess, "French") == findInArray(wordArray, unjumbledWord, "English"))

                    {
                        System.out.println("Correct! The word was " + unjumbledWord);
                    }
                else
                    {
                        System.out.println("Incorrect! The word was " + unjumbledWord);
                    }
            }
        else
            {
                System.out.println("Invalid Input");
            }
    }
}

