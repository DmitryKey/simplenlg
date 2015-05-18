# Generating a simple sentence #
In the sample Java class `TestMain` shown above, we have the statements:

```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;
```

These classes allow you to specify the parts of speech of a sentence and to perform various operations on them.  It’s important to note that simplenlg provides only a simple grammar: its notions of a sentence, noun phrase, etc., are very basic and are by no means representative of the incredibly varied and complicated grammar of the English language.

Let’s see how we would define and combine various parts of speech to generate a simple sentence such as "Mary chases the monkey". As discussed in [Section III](Section3.md), we’ll make use of the simplenlg construct `SPhraseSpec`, which allows us to define a sentence or a clause in terms of its syntactic constituents. This is useful because it allows us to hand different parts of a clause to simplenlg, in no particular order, and simplenlg will assemble those parts into the appropriate grammatical structure.
```
	SPhraseSpec p = nlgFactory.createClause();
	p.setSubject("Mary");
	p.setVerb("chase");
	p.setObject("the monkey");
```

The above set of calls to simplenlg defines the components of the sentence we wish to construct: we have specified a subject, a verb and an object for our sentence. Now, all that remains is to use the Realiser, which will take these different components of the sentence, combine them, and realise the text to make the result syntactically and morphologically correct:

```
        String output2 = realiser.realiseSentence(p); // Realiser created earlier.
        System.out.println(output2);
```

The resulting output is:
```
        Mary chases the monkey.
```

When parts of speech are defined and assembled into an instance of the `SPhraseSpec` class, methods associated with that class such as `setSubject`, `setVerb` and `setObject`, assemble the parts of speech by obeying the simple grammar embodied in simplenlg`[1]`.

→ For more examples on clauses, look at `testsrc/ClauseTest.java`.


---


`[1]` And, as we will see later, rules of grammar will have also been enforced in building up the smaller constituents of the sentence (such as NPPhraseSpec and PPPhraseSpec) to ensure they are well-formed. Thus, the rules of grammar which simplenlg implements are not defined within a single module of the simplenlg code but instead are spread throughout the various class definitions.