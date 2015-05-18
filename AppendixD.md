# Appendix D: Using the NIH Specialist Lexicon #

Simplenlg comes with a default lexicon which covers the most common words in English.  If this is inadequate, then Simplenlg can be connected to the 300MB [NIH Specialist lexicon](http://lexsrv3.nlm.nih.gov/LexSysGroup/Home).  The Specialist lexicon contains quite good coverage of general English vocabulary, as well as very detailed coverage of medical terminology.

To use the Specialist lexicon:
  1. Download and unpack the latest version of lexAccess from http://lexsrv3.nlm.nih.gov/LexSysGroup/Projects/lexAccess/current/web/download.html. The "lite" version is fine, simplenlg does not need the full version.
  1. Add the lexAccess jar file (in the lib directory of the unpacked download) as a library in your application.
  1. In your code, create an NIHDBLexicon, specifying the lexical database in the constructor.  The DB is in data\HSqlDb  directory of the unpacked download. For example
```
	static String DB_FILENAME = "C:\\NIHDB\\lexAccess2011\\data\\HSqlDb\\lexAccess2011.data";
	Lexicon lexicon = new NIHDBLexicon(DB_FILENAME);
```


**Notes:**

You must use the lexAccess version of the lexicon.  Do **not** use
main version in http://lexsrv3.nlm.nih.gov/LexSysGroup/Projects/lexicon/current/web/release.
This will not work!

If you use an old version of the lexicon, you may also need the lexCheck jar file.  This can be obtained from
http://lexsrv3.nlm.nih.gov/LexSysGroup/Projects/lexCheck/current/web/download.html

The [copyright conditions](http://www.nlm.nih.gov/copyright.html) of the Specialist lexicon state that it is in the public domain, but ask that its use be acknowledged.