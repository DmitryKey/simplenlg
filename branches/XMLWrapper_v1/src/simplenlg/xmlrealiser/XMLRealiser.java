/**
 * 
 */
package simplenlg.xmlrealiser;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;

import java.io.*;

import javax.xml.bind.JAXBException;
/**
 * @author Christopher Howell Agfa Healthcare Corporation
 *
 */
public class XMLRealiser {

	static String lexDB = null;
	static Lexicon lexicon = null;
	static Recording record = null;
	/**
	 * @param args
	 */
	/*
	 * The arg[0] is the op code.
	 * op codes are "realise", "setLexicon", "startRecording", "stopRecording"
	 * Usage is:
	 * realize <xml string>
	 * 		returns realised string.
	 * setLexicon  (XML  | NIHDB) <path to lexicon>
	 * 		returns "OK" or not.
	 * startRecording <path to recording directory>
	 * 		returns "OK" or not.
	 * stopRecording
	 * 		returns name of file which contains recording.
	 */
	public enum OpCode {noop, realise, setLexicon, startRecording, stopRecording}
	public enum LexiconType {DEFAULT, XML, NIHDB}
	
	public static String main(Object[] args) throws XMLRealiserException {
	
		if (args == null || args.length == 0)
		{
			throw new XMLRealiserException("invalid args");
		}
		int argx = 0;
		String input = "";
		String output = "OK";
		String opCodeStr = (String)args[argx++];
		OpCode opCode;
		try {
			opCode = Enum.valueOf(OpCode.class, opCodeStr);
		}
		catch (IllegalArgumentException ex)
		{
			throw new XMLRealiserException("invalid args");
		}
		switch (opCode)
		{
		case realise:
			if (args.length <= argx)
			{
				throw new XMLRealiserException("invalid args");
			}
			input = (String)args[argx++];
			StringReader reader = new StringReader(input);
			output = realise(reader);
			break;
		case setLexicon:
		{
			if (args.length <= argx + 1)
			{
				throw new XMLRealiserException("invalid setLexicon args");
			}
			String lexTypeStr = (String)args[argx++];
			String lexFile = (String)args[argx++];
			LexiconType lexType;

			try {
				lexType = Enum.valueOf(LexiconType.class, lexTypeStr);
			}
			catch (IllegalArgumentException ex)
			{
				throw new XMLRealiserException("invalid args");
			}
			
			setLexicon(lexType, lexFile);
			break;
		}
		case startRecording:
		{
			if (args.length <= argx)
			{
				throw new XMLRealiserException("invalid args");
			}
			String path = (String)args[argx++];
			startRecording(path);
			break;
		}
		case stopRecording:
			if (record != null)
			{
				output = record.GetRecordingFile();
				try {
					record.finish();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case noop:
			break;
		default:
			throw new XMLRealiserException("invalid op code " + opCodeStr);
		}
		
		if (opCode == OpCode.realise)
		{
		}
		
		return output;
	}
	
	public static void setLexicon(LexiconType lexType, String lexFile)
	{		
		if (lexicon != null)
		{
			lexicon.close();
			lexicon = null;
		}
		
		if (lexType == LexiconType.XML){
			lexicon = new XMLLexicon(lexFile);
		}
		else if (lexType == LexiconType.NIHDB){
			lexicon = new NIHDBLexicon(lexFile);
		}
		else if (lexType == LexiconType.DEFAULT){
			lexicon = Lexicon.getDefaultLexicon();
		}
	}
	
	public static String realise(Reader input) throws XMLRealiserException
	{
		String output = "";
		simplenlg.xmlrealiser.wrapper.DocumentElement wt = UnWrapper.unmarshal(input);
	
		if ( wt != null ) {
			try {
				if (lexicon == null)
				{
					lexicon = Lexicon.getDefaultLexicon();
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
				throw new XMLRealiserException("NLG XMLRealiser Error", e); 				
			}
		}
		
		return output;
	}
	
	public static void startRecording(String path) throws XMLRealiserException
	{
		if (record != null)
		{
			try {
				record.finish();
			} catch (Exception e) {
				throw new XMLRealiserException("NLG XMLRealiser Error", e); 				
			}
		}
		record = new Recording(path);
		try {
			record.start();
		} catch (IOException e) {
			throw new XMLRealiserException("NLG XMLRealiser Error", e); 				
		}
	}
	
	public static String stopRecording() throws XMLRealiserException
	{
		String file = "";
		if (record != null)
		{
			file = record.GetRecordingFile();
			try {
				record.finish();
			} catch (Exception e) {
				throw new XMLRealiserException("NLG XMLRealiser Error", e); 				
			}
		}
		
		return file;
	}
}
