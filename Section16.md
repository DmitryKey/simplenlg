# Generating a paragraph with strings, SPhraseSpecs and TextSpecs #

**This section has not yet been updated**

You can combine `strings`, `SPhraseSpecs` and `TextSpecs` in a single `TextSpec`. See the example below `[1]`.
```
        String str1 = "John is going to Tesco";
        String str2 = "Mary is going to Sainsburys";
        TextSpec t1 = new TextSpec();  // create a TextSpec
        t1.addSpec(str1);
        t1.addSpec(str2);
    	
        SPhraseSpec p1 = new SPhraseSpec("I", "go", "school");
        p1.setCuePhrase("however");
        p1.setProgressive(true);
    	
        TextSpec t2 = new TextSpec();
        t2.addSpec(t1);
        t2.addSpec(p1);
		
        String output = r.realiseDocument(t2);  //Realiser r created earlier
        System.out.println(output);
```

Output:
```
        John is going to Tesco and Mary is going to Sainsburys. However I am going to school.
```

In this example, we used the cue phrase ‘however’ to smooth the transition from the first sentence to the next one. Cue phrases often express how a sentence relates to the previous clause or sentence and help sentences in a paragraph flow together.

Notice that in this example we did not need to indicate that we want to generate a paragraph – simplenlg automatically did this for us i.e. we did not have to include the statement `t2.setParagraph`. Why not? Because if you add a `TextSpec` (`t1` in this example) to another `TextSpec` (`t2`), they will automatically be considered separate sentences.


---

`[1]` Note that simplenlg doesn’t always add all the punctuation that it should: there should be a comma after "However".