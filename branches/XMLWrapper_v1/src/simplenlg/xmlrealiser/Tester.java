package simplenlg.xmlrealiser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import simplenlg.xmlrealiser.XMLRealiser;
import simplenlg.xmlrealiser.XMLRealiser.LexiconType;
import simplenlg.xmlrealiser.wrapper.DocumentRealisation;
import simplenlg.xmlrealiser.wrapper.RecordSet;
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
		lexDB = (String)args[ix];
		XMLRealiser.setLexicon(LexiconType.NIHDB, lexDB);
		
		if (processTestSets)
		{
			Collection<File> testFiles;
			FilenameFilter filter = new TestFilenameFilter();
			File path = new File(testsPath);
			if (path.isDirectory())
			{
				testFiles = listFiles(path, filter, true);
			}
			else
			{
				testFiles = new Vector<File>();
				testFiles.add(path);
			}
			
			for (File testFile : testFiles)
			{
				try {
					FileReader reader = new FileReader(testFile);
					RecordSet input = XMLRealiser.getRecording(reader);
					RecordSet output = new RecordSet();
					output.setName(input.getName());
					for (DocumentRealisation test : input.getRecord())
					{
						DocumentRealisation testOut = new DocumentRealisation();
						testOut.setName(test.getName());
						testOut.setDocument(test.getDocument());
						String realisation = XMLRealiser.realise(test.getDocument());
						testOut.setRealisation(realisation);
						output.getRecord().add(testOut);
					}
					
					String outFileName = testFile.getAbsolutePath();
					outFileName = outFileName.replaceFirst("\\.xml$", "Out.xml");
					FileOutputStream outFile = new FileOutputStream(outFileName);
					outFile.getChannel().truncate(0);
					Recording.writeRecording(output, outFile);		
					
				} catch (XMLRealiserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			
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
				result = XMLRealiser.realise(XMLRealiser.getRequest(reader).getDocument());
			} 
			catch (XMLRealiserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			System.out.println(result);
		}
	}
	
	///////////////// copied code ///////////////////////////////
	// Copied from http://snippets.dzone.com/posts/show/1875
	public static File[] listFilesAsArray(
			File directory,
			FilenameFilter filter,
			boolean recurse)
	{
		Collection<File> files = listFiles(directory,
				filter, recurse);
	//Java4: Collection files = listFiles(directory, filter, recurse);
		
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	public static Collection<File> listFiles(
	// Java4: public static Collection listFiles(
			File directory,
			FilenameFilter filter,
			boolean recurse)
	{
		// List of files / directories
		Vector<File> files = new Vector<File>();
	// Java4: Vector files = new Vector();
		
		// Get files / directories in the directory
		File[] entries = directory.listFiles();
		
		// Go over entries
		for (File entry : entries)
		{
	// Java4: for (int f = 0; f < files.length; f++) {
	// Java4: 	File entry = (File) files[f];

			// If there is no filter or the filter accepts the 
			// file / directory, add it to the list
			if (filter == null || filter.accept(directory, entry.getName()))
			{
				files.add(entry);
			}
			
			// If the file is a directory and the recurse flag
			// is set, recurse into the directory
			if (recurse && entry.isDirectory())
			{
				files.addAll(listFiles(entry, filter, recurse));
			}
		}
		
		// Return collection of files
		return files;		
	}
}
////////////////////////// end of copied code ////////////////////////

class TestFilenameFilter implements FilenameFilter
{
	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(".xml") && !name.endsWith("Out.xml"))
			return true;
		else
		return false;
	}
}
