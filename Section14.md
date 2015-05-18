# Beyond sentences: Generating paragraphs, sections, lists, documents, oh my! #
We've seen a lot of ways to create individual sentences.  But what do you do if you want to put those sentences together, for example, to create a larger paragraph?  Simplenlg can do this using the `DocumentElement` class. The `DocumentElement` class is used to define elements that form part of a larger textual structure (documents, sections, paragraphs, sentences, lists).

To create a paragraph, you would combine some `DocumentElement` instances using `createParagraph`. To create a section, you would combine `DocumentElement` instances using `createSection`. Similarly, to create a list, you could combine these elements using `createList`, and to create a document, you would use `createDocument`.  Below, we discuss the use of `createParagraph` and `createSection`.

**Creating a paragraph**

The `createParagraph` method takes either an array of sentences or individual sentences added via `addComponent`. These sentences are then placed together as a paragraph.

As a first step, let's add the following to the import statement at the beginning of the file:
```
	import java.util.Arrays;
```

This will let us pass an array to the createParagraph method later.
Okay, now we're ready to format a paragraph.  First, we define some sentences:
```
	SPhraseSpec p1 = nlgFactory.createClause("Mary", "chase", "the monkey");
	SPhraseSpec p2 = nlgFactory.createClause("The monkey", "fight back"); 
	SPhraseSpec p3 = nlgFactory.createClause("Mary", "be", "nervous");
```

Next, we define these sentences as being `DocumentElement` instances:
```
	DocumentElement s1 = nlgFactory.createSentence(p1);
	DocumentElement s2 = nlgFactory.createSentence(p2);
	DocumentElement s3 = nlgFactory.createSentence(p3);
```

We can then pass these elements as a list to the `createParagraph` method:
```
	DocumentElement par1 = nlgFactory.createParagraph(Arrays.asList(s1, s2, s3)); [1]
```

And then realise the paragraph:
```
	String output = realiser.realise(par1).getRealisation();
        System.out.println(output);
```

The resulting output is:
```
        Mary chases the monkey. The monkey fights back. Mary is nervous.
```

Note that in the last couple steps, we're using the realiser differently than in all of the previous examples: Instead of using `realiser.realiseSentence()`, as we did for individual sentences, we're now using the more general `realiser.realise().getRealisation()`.

**Creating a Section**

Let's say you want to have a bunch of paragraphs, organized together under one section header. To do this, you would use createSection().

With our earlier code in place, you can create a section with a header like this:
```
	DocumentElement section = nlgFactory.createSection("The Trials and Tribulations of Mary and the Monkey");
```

A paragraph can then be added to this section:
```
	section.addComponent(par1);
```

You can then realise this section as in the previous example:
```
    	String output = realiser.realise(section).getRealisation();
        System.out.println(output);
```

→ For more examples of `DocumentElements`, look at `testsrc/DocumentElementTest.java`.

**HTML Output**

By default, simplenlg produces plain text output.  If you want
output with HTML markups, add an HTMLFormatter to your realiser,
for example
```
        realiser.setFormatter(new HTMLFormatter());
```

→ For examples of simplenlg input and corresponding
HTML output, look at `HTMLFormatterTest.java`.


---


`[1]` You may also add individual sentences thus:
```
	DocumentElement par1 = nlgFactory.createParagraph();
	par1.addComponent(s1); // ...etc.
```