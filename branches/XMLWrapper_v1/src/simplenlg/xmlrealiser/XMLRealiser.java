/**
 * 
 */
package simplenlg.xmlrealiser;

import simplenlg.framework.*;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.realiser.english.*;

import java.io.*;
/**
 * @author Christopher Howell Agfa Healthcare Corporation
 *
 */
public class XMLRealiser {

	static String lexDB = null;
	static NIHDBLexicon lexicon = null;

	/**
	 * @param args
	 */
	public static String main(Object[] args) throws XMLRealiserException {
		String input = (String)args[0];
		String output = "";
		StringReader reader = new StringReader(input);
		simplenlg.xmlrealiser.wrapper.DocumentElement wt = UnWrapper.unmarshal(reader);
	
		if ( wt != null ) {
			try {
				if (lexicon == null)
				{
					lexDB = (String)args[1];
					lexicon = new NIHDBLexicon(lexDB);
				}
				UnWrapper w = new UnWrapper(lexicon);
				DocumentElement t = w.UnwrapDocumentElement(wt);
				if (t != null ) {
					Realiser r = new Realiser(lexicon);
					r.initialise();
		
					NLGElement tr = r.realise(t);
					
					output = tr.getRealisation();
				}
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new XMLRealiserException("NLG XMLRealiser Error", e); 				
			}
		}
		
		return output;
	}
}
