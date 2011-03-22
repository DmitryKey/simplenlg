package simplenlg.xmlrealiser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import simplenlg.framework.*;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.realiser.english.*;
/**
 * @author Christopher Howell Agfa Healthcare Corporation
 *
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlFile = args[0];
		String result = new String("");
		String lexDB = (String)args[1];
		simplenlg.xmlrealiser.wrapper.DocumentElement wt = null;
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
			wt = UnWrapper.unmarshal(reader);
		} 
		catch (XMLRealiserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	
		if ( wt != null ) {
			try {
				NIHDBLexicon lexicon = new NIHDBLexicon(lexDB);
				UnWrapper w = new UnWrapper(lexicon);
				DocumentElement t = w.UnwrapDocumentElement(wt);
				if (t != null ) {
					Realiser r = new Realiser(lexicon);
					r.initialise();
					r.setDebugMode(true);
					System.out.println("--------realise----------");
					NLGElement tr = r.realise(t);
					
					result = tr.getRealisation();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		System.out.println(result);
	}

}
