# Generating a sentence with multiple clauses #

**Phrases joined by a conjunction**
One way of generating a sentence with multiple clauses is to use the simplenlg class `CoordinatedPhraseElement`.
```
	SPhraseSpec s1 = nlgFactory.createClause("my cat", "like", "fish");
	SPhraseSpec s2 = nlgFactory.createClause("my dog", "like", "big bones");
	SPhraseSpec s3 = nlgFactory.createClause("my horse", "like", "grass");

	CoordinatedPhraseElement c = nlgFactory.createdCoordinatedPhrase();
        // may revert to nlgFactory.createCoordinatedPhrase( ) ;
	c.addCoordinate(s1);
	c.addCoordinate(s2);
	c.addCoordinate(s3);
```

The `CoordinatedPhraseElement c` can then be realised as a sentence:
```
	String output = realiser.realiseSentence(c);
	System.out.println(output);
```

If you do not supply a conjunction using the method `setConjunction`, the conjunction ‘and’ will automatically be used because it is the default. In this case, the resulting sentence would be:
```
        My cat likes fish, my dog likes big bones and my horse likes grass.
```


**Subordinate clauses**
Subordinate clauses can be added to the main clause using the `addComplement` method, where the kind of complementiser (“because”, “while”, etc.) to be used is set using the `setFeature` method.
```
	SPhraseSpec p = nlgFactory.createClause("I", "be", "happy");
	SPhraseSpec q = nlgFactory.createClause("I", "eat", "fish");
	
	q.setFeature(Feature.COMPLEMENTISER, "because");
	q.setFeature(Feature.TENSE, Tense.PAST);
	p.addComplement(q);
		
	String output4 = realiser.realiseSentence(p);  //Realiser created earlier
	System.out.println(output4);
```

The output is:
```
        I am happy because I ate fish.
```