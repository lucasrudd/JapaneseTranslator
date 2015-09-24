import translation.*;
public class JapaneseTranslator {
	
	
	
	public static void main(String[] args) {
		String translationString = "\nEnter, in romanji, the Japanese you wish to translate: ";
		
		translator translator = new RegularExpressions();
		
		while(true)
		{
			translator.beginRegex(translationString);
			identifyVerbs();
		}
	}
}
