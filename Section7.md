# What are complements? #

As far as simplenlg is concerned, a complement is anything that comes after the verb.  When you label something as a complement and hand it to simplenlg to be realized, simplenlg will place it, no matter what it is, after the verb.`[1]`  If you've specified an object, it will place it after both the verb and the object.

Examples of complements are italicized in the sentences below:

  1. Mary is _happy_.
  1. Mary wrote the letter _quickly_.
  1. Mary just realized _that her holidays are over_.

The italicized words and phrases in the examples above are all different parts of speech. In example #1, it's an adjective phrase; in example #2, it's an adverb; and it’s a ‘that-clause’ (complementiser phrase) in example #3. But from simplenlg’s point of view, the underlined bits all have one thing in common:  they are complements and appear after the verb. Although it has an understanding of subjects, verbs, and objects, simplenlg has a very limited concept of adjective phrases that follow verbs, adverbial phrases, that-clauses, or other parts of speech that can appear after a verb. But it does understand the concept of a complement, and because of this, phrases that appear after a verb can be generated using the simplenlg library.  As can be seen in Table 2, complements encompass a variety of phrase types.  (For a full listing of Parts of Speech simplenlg can handle, see [Table 1](Section3.md).)

![http://simplenlg.googlecode.com/svn/wiki/simplenlg_table2.png](http://simplenlg.googlecode.com/svn/wiki/simplenlg_table2.png)


Complements can be added to sentences via the addComplement method.  For example, given our subject, object, and verb:

```
	p.setSubject("Mary");
	p.setVerb("chase");
	p.setObject("the monkey");

```

We can then add any kind of complement:

```
	p.addComplement("very quickly"); // Adverb phrase, passed as a string
	p.addComplement("despite her exhaustion"); // Prepositional phrase, string
```

Which will generate:

```
        Mary chases the monkey very quickly despite her exhaustion.
```


Note that only things of type `SPhraseSpec` can take a complement.
Nouns and verbs take modifiers, which we discuss next.


→ For more examples on complements, look at `testsrc/FPTest.java`.


---


`[1]` Even if you label a nonsense string like "shabadoo" as a complement, simplenlg will happily add it after the verb.