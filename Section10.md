# Adding multiple subjects, objects and complements #

An `SPhraseSpec` can have multiple subjects, objects and complements but not multiple verbs (although a future version of simplenlg might include this functionality). This is done with the `CoordinatedPhraseElement` class. Let’s say you also have a giraffe that wants to chase the poor monkey. To place your giraffe in the fray, you would write:
```
	NPPhraseSpec subject1 = nlgFactory.createNounPhrase("Mary");
	NPPhraseSpec subject2 = nlgFactory.createNounPhrase("your", "giraffe");

	CoordinatedPhraseElement subj = nlgFactory.createdCoordinatedPhrase(subject1, subject2); 
                  // may revert to nlgFactory.createCoordinatedPhrase( subject1, subject2 ) ;
	p.setSubject(subj);
```
The resulting output is:
```
        Mary and your giraffe chase the monkey.
```

simplenlg has automatically added the conjunction ‘and’ and has changed the ending of the verb so that it agrees with the multiple subjects of the sentence.

Similarly, you can have multiple objects and complements in an `SPhraseSpec`. Let’s suppose Mary and your giraffe have found more people to terrorize in what’s turning out to be a growing parade of horror.  Instead of `p.setObject(“the monkey”)`, you would write:
```
	NPPhraseSpec object1 = nlgFactory.createNounPhrase("the monkey");
	NPPhraseSpec object2 = nlgFactory.createNounPhrase("George");

	CoordinatedPhraseElement obj = nlgFactory.createdCoordinatedPhrase(object1, object2); 
                  // may revert to nlgFactory.createCoordinatedPhrase( subject1, subject2 ) ;
	obj.addCoordinate("Martha");
        p.setObject(obj);
```
The resulting output will be:
```
        Mary and your giraffe chase the monkey, George and Martha.
```

If Mary and the devious giraffe run by so quickly that you can't see who they're chasing, you can change the conjunction on the coordinated elements:
```
	obj.setFeature(Feature.CONJUNCTION, "or");
```
The resulting output will be:
```
        Mary and your giraffe chase the monkey, George or  Martha.
```

As with many methods in simplenlg, the `createCoordinatedPhrase` method can take all kinds of arguments – `NPPhraseSpec`, `PPPhraseSpec`, or even simple `string`s.

→  For more examples on coordination, look at `testsrc/ClauseAggregationTest.java`.