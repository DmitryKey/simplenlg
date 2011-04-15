package simplenlg.xmlrealiser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import simplenlg.framework.*;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.realiser.english.*;
import simplenlg.xmlrealiser.XMLRealiser.*;
/**
 * @author Christopher Howell, Agfa Healthcare Corporation
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean processTestSets;
		String testsPath = "";
		String xmlFile = "";
		String lexDB;
		int ix=0;
		String usage = "usage: Tester [-tests <xml file based on TestSet schema or path to such files> | <xml file based on RealizerSchema>] <NIH db location>";
		if (args.length <2)
		{
			System.out.println(usage);
			return;
		}
		if (args[ix].matches("-tests"))
		{
			ix++;
			processTestSets = true;
			testsPath = args[ix++];
		}
		else
		{
			processTestSets = false;
			xmlFile = args[ix++];		
		}
		if (args.length < ix+1)
		{
			System.out.println(usage);
			return;			
		}
		
		if (processTestSets)
		{
			// TODO cch
		} else {
			lexDB = (String)args[ix];
			XMLRealiser.setLexicon(LexiconType.NIHDB, lexDB);
			
			String result = "";
			FileReader reader;
			try {
				reader = new FileReader(xmlFile);
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			try {
				result = XMLRealiser.realise(reader);
			} 
			catch (XMLRealiserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			System.out.println(result);
		}
	}
}
