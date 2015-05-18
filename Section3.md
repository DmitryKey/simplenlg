# Getting started #

It’s important to note that simplenlg is a library `[1]`, not an application! This means you cannot run it as a Java program – it does not have a "main" method. You need to create your own Java program which makes use of simplenlg classes and methods.

To enable your program to make use of the simplenlg library, you’ll need to:

  * Download the simplenlg zip file from the Download tab.
  * Extract then add simplenlg’s jar file to your classpath. (For instructions on how to do this in Eclipse, see [Appendix B](AppendixB.md)).

  * Create a new Java class which has the main method in it. In this example, let’s call the new class `TestMain`.

  * At the top of that class, put in the following import statements:
```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;
```

  * Create a simplenlg lexicon, NLGFactory, and realiser using the following statements
```
        Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
```

Following these steps, you should have code that looks like the following:
```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;


        public class TestMain {

	        public static void main(String[] args) {
		        Lexicon lexicon = Lexicon.getDefaultLexicon();
                        NLGFactory nlgFactory = new NLGFactory(lexicon);
                        Realiser realiser = new Realiser(lexicon);

	        }

        }

```
**Figure 1**: A Java class which is ready to make use of the simplenlg library.


You’re now ready to make use of simplenlg to generate sentences!

→ For further examples on ways to use simplenlg, take a look at the java files in `testsrc`.  A stand-alone example is provided in `testsrc/StandAloneExample.java`.

**Generating the simplest kind of phrase in simplenlg**

Let’s create the simplest kind of sentence allowed in simplenlg: canned text, i.e., a string which we’d like output to the screen as is. For instance, let’s say we are writing a program which takes input from users and generates a different paragraph depending on the various inputs. But let’s say that no matter what, we always want the first line of the paragraph to be “My dog is happy” because we feel everyone should share in the good news. The simplenlg code to do this is:

```
        NLGElement s1 = nlgFactory.createSentence("my dog is happy");
```

We now need to use the Realiser to output the string:

```
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
```

Not surprisingly, the resulting output is:
```
        My dog is happy.
```

It’s important to note that you only need to create the `Lexicon`, `NLGFactory`, and `Realiser` once within your program; you don’t need to create them for every sentence you generate. So a good idea is to create these at the start of your program and user them over the lifetime of the program run.


**The main steps involved in generating a more complicated sentence**

The last example was the simplest way to create a sentence, and as you can see, it requires you to do most of the work.  Now let's take a look at how to get simplenlg to do most of the work.  Keep in mind that simplenlg is pretty flexible in this way, and there are several other ways to generate sentences – many of the things that you think of will probably work as well.

At the most fine-grained level, simplenlg understands what a sentence is using a class called `SPhraseSpec`.  This is accessible through the `NLGFactory`, using the `createClause` method. The ideas of ‘verb phrase’, ‘noun phrase’, ‘prepositional phrase’, ‘adjective phrase’, and ‘adverb phrase’ are are also all accessible through `NLGFactory`, using `createVerbPhrase`, `createNounPhrase`, `createPrepositionPhrase`, etc. These return similarly named classes – `VPPhraseSpec`, `NPPhraseSpec`, `PPPhraseSpec`, `AdjPhraseSpec`, and `AdvPhraseSpec`.

In order to build a sentence using these simplenlg concepts or classes, you will normally follow these steps (all of this will be further explained in [Sections V](Section5.md) and on):

  * Create an instance of `NLGFactory`.

  * Create a placeholder for the sentence using `NLGFactory`'s `createClause` method. (This returns an `SPhraseSpec` instance.)

  * Create a verb, subject, and object using `NLGFactory`'s `createVerbPhrase` and `createNounPhrase` methods. (These return `VPPhraseSpec` and `NPPhraseSpec` instances.)

  * If you want, create prepositional phrases, adjective phrases, and adverb phrases using `NLGFactory`'s `createPrepositionPhrase`, `createAdjectivePhrase`, and `createAdverbPhrase` methods.  (These return `PPPhraseSpec`, `AdjPhraseSpec` and `AdvPhraseSpec` instances.)

  * Indicate what role these various parts of speech will play in the desired sentence. For example, specify that you want a particular noun phrase to be the subject of the sentence, and some other noun phrase to be the object, using `setSubject` and `setObject`. Specify the verb using `setVerb`, and the complement (e.g., the prepositional phrase) using `addComplement`.

  * Create a simplenlg object called the `Realiser`.

  * Ask the `Realiser` to ‘realise’ or transform the `SPhraseSpec` instance into a syntactically correct string.

You now have a string which is a grammatical English phrase or sentence and it can be treated like any other Java string. For instance, you can manipulate it further or print it out using the Java method `System.out.println`.

Note that this is the most detailed approach: You can actually just use `setSubject`, `setVerb`, etc., by passing these methods simple strings as arguments. Unless you're doing more complicated things, first specifying that a subject is an `NPPhraseSpec` or that a verb is a `VPPhraseSpec` is not even necessary!  See [Section V](Section5.md) for an example of actual Java code used to generate a sentence.

Below is quick breakdown of the main parts of speech that simplenlg can handle.  The rest of the tutorial will discuss each of these parts of speech in more detail.

![http://simplenlg.googlecode.com/svn/wiki/simplenlg_table1.png](http://simplenlg.googlecode.com/svn/wiki/simplenlg_table1.png)


---

`[1]` A library or API is a collection of methods/functions that people can make use of in their programs. This saves programmers from having to write that code themselves.

`[2]` This is actually a Complementiser Phrase, which is not currently implemented in simplenlg.  Instead, such phrases can just be written as strings, then specified as complements.

`[3]` This may also be added as a modifier to the full sentence, and will be interpreted as modifying the main verb.