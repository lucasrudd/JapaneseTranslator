package translation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Help {
	
	private static HelpDatabase data;
	
	private static boolean SETUP;
	private String helpString;
	private String userInput;
	private String construct;
	private boolean hasSpecification;
	private boolean needsHelp;
	

	
	Help(String input)
	{
		helpString = "/HELP";
		userInput = formatInput(input);
		
		needsHelp = (userInput.contains(helpString)) ?
				true : false;
		
		hasSpecification = (needsHelp && input.contains(" ") && userInput.length() > 5) ? 
				true : false;
		
		
		if(needsHelp)
		{
			SETUP = helpMapInitalization();
		}
		
	}
	
	private static String formatInput(String unformattedString)
	{
		String formattedString = "";
		String[] parsedString = unformattedString.split(" ");
		for(int i = 0; i < parsedString.length; i++)
		{
			formattedString += parsedString[i].length() <= 2 ?
								parsedString[i] : parsedString[i].toUpperCase();
		}
		
		return formattedString;
	}
	
	public boolean isConnected()
	{
		return SETUP;
	}
	
	public boolean doesNeedHelp()
	{
		if(needsHelp)
		{
			return true;
		}
		return false;
	}
	
	private void helpFormat(String unformattedString)
	{
		if(unformattedString.equals("ERROR"))
		{
			System.err.println("ERROR! Construct not found in database - see getConstruct()");
			helpMenu();
			return;
		}
		
		String[] formattedHelpInformation = unformattedString.split(":");
		for(int i = 0; i < formattedHelpInformation.length; i++)
		{
			System.out.println(formattedHelpInformation[i]+"\n");
		}
	}
	
	public void administerHelp()
	{
		if(hasSpecification) 
		{
			helpFormat(HelpDatabase.getConstruct(identifyHelpNeeded()));
		} 
		
		else 
		{
			helpMenu();
		}
	}
	
	private String identifyHelpNeeded()
	{
		
		if(!(userInput.contains(helpString)))
		{
				needsHelp = false;
				return "ERROR_HELP_NOT_NEEDED-see_identifyHelpNeeded()";
		}
		
		return formatInput(userInput.substring(helpString.length()));
		
		
	}
	
	private boolean helpMapInitalization()
	{
		data = new HelpDatabase();
		if(!data.isSetup())
		{
			System.err.println("ERROR! HelpDatabase failed setup.");
			return false;
		}
		
		data.formatMaps();
		return true;
	}
	
	
	private static String getFillerString(String s)
	{
		String filler = "";
		for(int tempLength = s.length(); tempLength!= data.getFormatLength(); tempLength++)
		{
			filler += " ";
		}
		
		filler += "\t";
		return filler;
	}
	
	private void readMap(Map<String, String> mp)
	{
		String fillerString;
		Iterator<Map.Entry<String, String>> it = mp.entrySet().iterator();
		
		
		while(it.hasNext())
		{
			Map.Entry<String, String> next = it.next();
			fillerString = getFillerString((String) next.getKey());
			System.out.println(next.getKey() + fillerString + next.getValue());
			it.remove();
		}
	}
	
	private void helpMenu()
	{
		System.out.println("For more information on a specific term, type /help term-name");
		readMap(data.getHelpMap());
	}
}
