package simplenlg.xmlrealiser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

import simplenlg.xmlrealiser.wrapper.*;

public class Recording {
	boolean recordingOn = false;
	String recordingFolder;
	RecordSet record = null;
	File recordingFile;

	public Recording(String directoryPath) {
		recordingFolder = directoryPath;
	}

	public boolean RecordingOn() {
		return recordingOn;
	}

	public String GetRecordingFile() {
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
		if (!recordingDir.exists()) {
			boolean ok = recordingDir.mkdirs();
			if (!ok) {
				return;
			}

			recordingFile = File.createTempFile("xmlrealiser", ".xml",
					recordingDir);
			recordingOn = true;
			record = new RecordSet();
		}
	}

	public void addRecord(simplenlg.xmlrealiser.wrapper.DocumentElement input,
			String output) {
		if (!recordingOn) {
			return;
		}
		DocumentRealisation t = new DocumentRealisation();
		Integer testNumber = record.getRecord().size() + 1;
		String testName = "TEST_" + testNumber.toString();
		t.setName(testName);
		t.setDocument(input);
		t.setRealisation(output);
		record.getRecord().add(t);
	}

	public void finish() throws JAXBException, IOException,
			TransformerException {
		if (!recordingOn) {
			return;
		}

		recordingOn = false;
		FileOutputStream os = new FileOutputStream(recordingFile);
		os.getChannel().truncate(0);
		writeRecording(record, os);
	}

	public static void writeRecording(RecordSet record, OutputStream os)
			throws JAXBException, IOException, TransformerException {
		JAXBContext jc;
		jc = JAXBContext
				.newInstance(simplenlg.xmlrealiser.wrapper.RecordSet.class);
		Marshaller m = jc.createMarshaller();

		// For the meaning of the next property, see the
		// Java Architecture for XML Binding JAXB RI Vendor Extensions Runtime
		// Properties
		// It was added so that the namespace declarations would be at the top
		// of the file, once,
		// instead of on the elements.
		m.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",
				new RecordingNamespacePrefixMapper());

		NLGSpec nlg = new NLGSpec();
		nlg.setRecording(record);

		StringWriter osTemp = new StringWriter();
		m.marshal(nlg, osTemp);

		// Prettify it.
		Source xmlInput = new StreamSource(new StringReader(osTemp.toString()));
		StreamResult xmlOutput = new StreamResult(new OutputStreamWriter(os,
				"UTF-8"));
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		if (transformer != null) {
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(xmlInput, xmlOutput);
		}
	}
}

/**
 * Coerces the JAXB marshaller to declare the "xsi" and "xsd" namespaces at the
 * root element instead of putting them inline on each element that uses one of
 * the namespaces.
 */
class RecordingNamespacePrefixMapper extends NamespacePrefixMapper {

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		return suggestion;
	}

	@Override
	public String[] getPreDeclaredNamespaceUris2() {
		return new String[] { "xsi",
				"http://www.w3.org/2001/XMLSchema-instance", "xsd",
				"http://www.w3.org/2001/XMLSchema" };

	}
}
