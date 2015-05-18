# Lexicon #
Like other natural language processing systems, simplenlg needs information about words; this is called a _`Lexicon`_. Simplenlg comes with a simple lexicon built into the system, which is accessed via:
```
        Lexicon lexicon = Lexicon.getDefaultLexicon();
```

The default lexicon is 700KB, and should be adequate for many purposes.

Simplenlg can also use the 300MB NIH Specialist lexicon, which has outstanding coverage of medical terminology as well as excellent coverage of everyday English.  For information on setting up this lexicon, please see [Appendix C](AppendixC.md).

You can also create your own lexicon if you wish.  The easiest way to do this is probably to edit `default-lexicon.xml` (the default lexicon file), this is in `res/default-lexicon.xml` in the simplenlg zip file. If your new lexicon is called `my-lexicon.xml`, and is saved in your current working directory, you can access it as follows:
```
        Lexicon lexicon = new XMLLexicon("my-lexicon.xml");
```

To access a lexicon outside of the current working directory, provide the full path name (e.g., “/home/staff/lexicons/my-lexicon.xml”, “C:\lexicons\my-lexicon.xml”).`[1]`

Once we have a lexicon, we can create an NLGFactory (object which creates simplenlg structures) and a realiser (object which transforms simplenlg structures into text), as follows:

```
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
```

→ For more examples, look at `testsrc/MultipleLexiconTest.java` and `testsrc/NIHDBLexiconTest.java`.


---

`[1]` Note that in simplenlg V4, there are no lexicon methods to directly get inflected variants of a word; in other words, there is no equivalent in V4 of the simplenlg V3 `getPlural(), getPastParticiple()`, etc. methods.  It is possible in V4 to compute inflected variants of words, but the process is more complicated: basically we need to create an InflectedWordElement around the base form, add appropriate features to this InflectedWordElement, and then realise it.