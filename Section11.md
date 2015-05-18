# Prepositional phrases #

Our sentence is getting rather crowded with people and animals. So let’s return to the pristine simplicity of Mary chasing the monkey. But let’s give the heart-pounding action a setting:
`"Mary chases the monkey ` **`in the park`**".

The phrase "in the park" is a prepositional phrase and there are a number of ways we can create it using simplenlg. The simplest way would be to pass the string "in the park" to the addComplement method:
```
        SPhraseSpec p = nlgFactory.createClause("Mary", "chase", "the monkey");
	p.addComplement("in the park");
```

A more sophisticated way of creating this prepositional phrase, however, would be to specify the parts of the prepositional phrase – the preposition, determiner, noun phrase –  and combine them:
```
	NPPhraseSpec place = nlgFactory.createNounPhrase("park");
	place.setDeterminer("the");
	PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
	pp.addComplement(place);
	pp.setPreposition("in");
```
We then add the prepositional phrase as a complement to the ‘Mary chases the monkey’ sentence.
```
	p.addComplement(pp);
```
The table below shows three different ways of creating the prepositional phrase "in the park".  As you can start to see, there are often several ways to realise sentences using simplenlg.  The best way to do it depends on the needs of your system.

![http://simplenlg.googlecode.com/svn/wiki/simplenlg_table3.png](http://simplenlg.googlecode.com/svn/wiki/simplenlg_table3.png)

The third, sophisticated method requires much more code than the other two ways. So why then would we ever choose the third method?

The main reason is that the third method allows you to add pieces to a phrase or sentence with much greater ease. We have to remind ourselves that simplenlg will normally be used in a larger program which chooses the content of a sentence – and that content will likely be determined in a piecemeal fashion. It’s much easier to have simplenlg add a word or clause to a phrase which has been defined in a modular way (i.e., parts of the sentence are divided into chunks and labeled) rather than having to add new information to a monolithic string whose parts are not differentiated. For example, if we wanted to describe the park as ‘leafy’, and we had used the 3rd method to define our sentence, all we would need to do is write the following code:
```
	place.addPreModifier("leafy");
```

Had we chosen the first or second methods, however, adding the adjective ‘leafy’ to the string ‘park’ would be a major hassle. Among other things, you would have to write code which could:

  * find where to insert the new word in the string. In most cases this would require parsing the string, which is no simple task!
  * break that string into pieces to allow the insertion
  * insert the word
  * determine whether that insertion requires changing the other bits of string
  * put the pieces of string back together in a grammatical way

In other words, you would have to write a realiser like simplenlg!

So why, given the major drawback stated above, would we ever choose to define a sentence using methods #1 and #2? Because sometimes we simply want to generate canned text, i.e., text that we know won’t need to be enlarged or changed and which we simply want output as-is. If we know that we won’t be changing a phrase (such as ‘in the park’), then it makes sense to treat it as a uniform entity the way the first two methods do.

→  For more examples of prepositional phrases, look at `testsrc/PrepositionalPhraseTest.java`.