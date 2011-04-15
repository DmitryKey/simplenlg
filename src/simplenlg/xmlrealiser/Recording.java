package simplenlg.xmlrealiser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import simplenlg.xmlrealiser.wrapper.*;

public class Recording {
	boolean recordingOn = false;
	String recordingFolder;
	TestSet record = null;
	File recordingFile;
	
	public Recording(String directoryPath)
	{
		recordingFolder = directoryPath;
	}
	
	public boolean RecordingOn()
	{
		return recordingOn;
	}
	
	public String GetRecordingFile()
	{
		if (recordingOn)
			return recordingFile.getAbsolutePath();
		else
			return "";
	}
	
	public void start() throws IOException {
		
		if (recordingFolder.isEmpty() || recordingOn) {
			return;
		}
		
		File recordingDir = new File(recordingFolder);
		if (!recordingDir.exists())
		{
			boolean ok = recordingDir.mkdirs();
			if (!ok) {
				return;
			}
			
			recordingFile = File.createTempFile("xmlrealiser", ".xml", recordingDir);
			recordingOn = true;
			record = new TestSet();
		}
	}
	
	public void addRecord(simplenlg.xmlrealiser.wrapper.DocumentElement input, String output)
	{
		if (!recordingOn) {
			return;
		}
		Test t = new Test();
		Integer testNumber = record.getTest().size() + 1;
		String testName = "TEST_" + testNumber.toString();
		t.setName(testName);
		t.setData(input);
		t.setResult(output);
		record.getTest().add(t);
	}
	
	public void finish() throws FileNotFoundException, JAXBException {
		if (!recordingOn) {
			return;
		}
		
		recordingOn = false;
		JAXBContext jc;
		jc = JAXBContext.newInstance(simplenlg.xmlrealiser.wrapper.TestSet.class);
		Marshaller m = jc.createMarshaller();
		OutputStream os = new FileOutputStream(recordingFile);
		m.marshal(record, os);
	}
}
