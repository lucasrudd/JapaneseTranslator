package translation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class HelpDatabase {
	
	private static final File helpMapFile = new File("helpMapFile.txt");
	private static final File detailedHelpMapFile = new File("detailedHelpMapFile.txt");
	
	private static Map<String, String> helpMap;
	private static List helpList;
	private static Map<String, String> detailedHelpMap;
	
	private static int formatLength;
	
	HelpDatabase()
	{
		helpMap = new TreeMap<String, String>();
		helpList = new ArrayList<String>();
		detailedHelpMap = new TreeMap<String, String>();
		formatLength = 0;
		initalizeMaps();
	}
	
	private static void initalizeMaps()
	{
		read(helpMap, helpMapFile);
		read(detailedHelpMap, detailedHelpMapFile);
		//createHelpList(helpMap);
	}
	
	public void formatMaps()
	{
		for(Map.Entry<String,String> next : helpMap.entrySet())
		{
			formatLength = next.getKey().length() > formatLength ?
				next.getKey().length() : formatLength;
		}
		
	}	
	
	private static void createHelpList()
	{
		for(Map.Entry<String, String> next : helpMap.entrySet())
		{
			helpList.add(next.getKey().toUpperCase());
		}
	}
	
	/*
	 * private static boolean setIteratorType(genericMapOrListParamater gen)
	 * {
	 * 	Iterator it = new genericIteratorOfGenericType();
	 * 	if(it != null)
	 * {
	 * return true
	 * }
	 * 
	 * else
	 * 	return false;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public getIterator()
	 * {
	 * 	return genericIteratorType;
	 * }
	 */
	
	
	
	private static void read(Map<String, String> mp, File filename)
	{
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			Scanner fileScanner = new Scanner(fis);
			fileScanner.useDelimiter("~");
			
			while(fileScanner.hasNext())
			{
				String[] var = new String[2];
				for(int i = 0; i < 2; i++)
				{
					var[i] = fileScanner.next().trim();
				}
				
				mp.put(shouldBeUpperCase(var[0]), var[1]);
			}
			fileScanner.close();
		}
		catch(Exception ex)
		{
			System.err.println(ex);
			System.err.println("ERROR! Failed to load " + filename + " - See read()");
		}
	}
	
	/*private static void alphabatize()
	{
		it = helpMap.entrySet().iterator();
		int length = 0;
		String temp = null;
		int num = 0;
		while(it.hasNext())
		{
			if(num!=0)
			{
				Map.Entry<String, String> next = it.next();
				
				length = next.getKey().length() > length ?
						next.getKey().length() : length;
						
						for(int i = 0; i < length; i++)
						{
							if(next.getKey().charAt(i) < temp.charAt(i));
						}
			}
			else
			{
				temp = it.next().getKey();
				num++;
			}
		}
	}*/
	
	private static String shouldBeUpperCase(String key)
	{
		if(key.length() <= 2)
		{
			return key;
		}
		return key.toUpperCase();
	}
	
	protected boolean isSetup()
	{
		if((!helpMap.isEmpty()) /*&& (!helpList.isEmpty())*/ && (!detailedHelpMap.isEmpty()))
		{
			
			return true;
		}
		return false;
	}
	
	protected int getFormatLength()
	{
		return formatLength;
	}

	protected Map getHelpMap()
	{
		return helpMap;
	}
	
	protected static Map getDetailedHelpMap()
	{
		return detailedHelpMap;
	}
	
	protected static List getHelpType()
	{
		return helpList;
	}
	
	protected static String getConstruct(String construct)
	{
		if(detailedHelpMap.containsKey(construct))
		{
			return (String) detailedHelpMap.get(construct);
		}
		else
		{
			return "ERROR";
		}
	}

}
