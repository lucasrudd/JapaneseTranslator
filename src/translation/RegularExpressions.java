package translation;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class RegularExpressions {

	private static Pattern pattern;
	private static Matcher matcher;
	protected static Help helper;
	private static Scanner console;
	
	public RegularExpressions()
	{
		console = new Scanner(System.in);
		if(console == null)
		{
			System.err.println("ERROR! No console.");
			System.exit(1);
		}
	}
	
	public void beginRegex(String prompt)
	{
		System.out.print(prompt);
		String regex = console.nextLine();
		
		if(!isHelpString(regex))
		{
			searchRegex(regex);
		}
	}
	
	public static boolean isHelpString(String regex)
	{
		helper = new Help(regex);
		if(helper.doesNeedHelp() && helper.isConnected())
		{
			helper.administerHelp();
			return true;
		}
		return false;
	}
	
	public static void searchRegex(String regex)
	{
			pattern =
			Pattern.compile(regex);
			
			
			System.out.print("Enter input string to search: ");
			matcher =
			pattern.matcher(console.nextLine());
			if(!matcher.find())
			{
				System.out.print("No match found.\n");
			}
			else
			{
				matcher.reset();
				while(matcher.find())
				{
					System.out.print("\nI found the text '" + 
							matcher.group() + "' starting at index " + 
							matcher.start() + " and ending at index " + 
							matcher.end() + "\n");
				}
			}
	}

}
