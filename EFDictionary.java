class EFDictionary

{

	private String englishWord;
	private String frenchWord;

	

	public EFDictionary() // constructor
	
	
	{
	
	
	}
	
	
	public EFDictionary (String theEnglish, String theFrench) // constructor
	
	
	{
		englishWord = theEnglish;
		frenchWord = theFrench;
		
	}
	
	
	// Mutators (Setter Methods)
	
	
	public void setEWord (String theEWord)
	{
	
		englishWord = theEWord;
	}
	
	
	public void setFWord (String theFWord)
	{
	
		frenchWord = theFWord;
	}
	
	
	
	//Selectors (Getter Methods) no parameters
	
	
	public String getEWord ()
	{
	
		return englishWord;
	
	}
	
	
	public String getFWord ()
	
	
	{
	
		return frenchWord;
	
	}
	
	public int lengthDiff()
	{
		if(englishWord.length() > frenchWord.length())
			{
				return englishWord.length() - frenchWord.length();
			}
		else if(frenchWord.length() > englishWord.length())
			{
				return frenchWord.length() - englishWord.length();
			}
		else
			{
				return 0;
			}
	}
	
	public String toString ()
	
	
	{
		String myString;
		
		myString = "";
		myString += "\n English Version -> " + englishWord;
		myString += "\n French Version -> " + frenchWord;
		
		return myString;
	}	

}
