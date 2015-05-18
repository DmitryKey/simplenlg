# Verbs #
Verbs should be specified in infinitive ("to XXX") form. However, as a convenience, simplenlg will usually accept inflected forms of verbs as well. For example,
```
	p.setVerb("is");
```

is equivalent to
```
	p.setVerb("be");
```

You can specify particles (prepositions which accompany a verb) by writing the following:
```
	p.setVerb("pick up");
	p.setVerb("put down");
```

Verbs in simplenlg can have one of three different tenses: past, present and future. Let’s say we’ve written the following simplenlg code which yields the sentence "Mary chases the monkey.":

```
	SPhraseSpec p = nlgFactory.createClause();
	p.setSubject("Mary");
	p.setVerb("chase");
	p.setObject("the monkey");
```

In order to set this in the past, we would add the line:
```
	p.setFeature(Feature.TENSE, Tense.PAST);
```

thus rendering the sentence:
```
        Mary chased the monkey.
```

If Mary is instead busy with other things and forced to postpone her exercise, we could write:
```
        p.setFeature(Feature.TENSE, Tense.FUTURE);
```

yielding the sentence:
```
        Mary will chase the monkey.
```


**Negation**
If negated is set to true, the negative form of the sentence is produced. For example adding the following line to the previous:
```
	p.setFeature(Feature.NEGATED, true);
```

will change the resulting sentence to:
```
        Mary will not chase the monkey.
```


**Questions**
Simplenlg can generate simple yes/no questions.  For example:
```
	p.setSubject("Mary");
	p.setVerb("chase");
	p.setObject("the monkey");
	p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.YES_NO);
```
will generate:
```
        Does Mary chase the monkey?
```

Simplenlg can also generate simple WH questions.  For example:
```
	p.setSubject("Mary");
	p.setVerb("chase");
	p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.WHO_OBJECT);
```
will generate:
```
        Who does Mary chase?
```

→ For more examples on questions, look at `testsrc/InterrogativeTest.java`.

**Features**
TENSE, NEGATED, and INTERROGATIVE\_TYPE are examples of _features_ which can be set on a SPhraseSpec. Many other features are also allowed, including MODAL, PASSIVE, PERFECT, and PROGRESSIVE. Detailed information about allowable features are given in the simpleNLG API documentation.

→ For more examples on verbs, look at `testsrc/VerbPhraseTest.java`.