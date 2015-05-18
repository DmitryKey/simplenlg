# NLG and simplenlg #

What is NLG and how much of it does simplenlg do? NLG aims to produce understandable text, typically from some nonlinguistic representation of info.

![http://simplenlg.googlecode.com/svn/wiki/NLG_Arch.png](http://simplenlg.googlecode.com/svn/wiki/NLG_Arch.png)

Many NLG systems consist of 3 components which are connected together in a pipeline. i.e. the output of document planning acts as input to microplanning and the output of the microplanner is the input to the surface realiser. The table below briefly outlines the different components of an NLG system and the shaded portion shows which tasks simplenlg performs.

![http://simplenlg.googlecode.com/svn/wiki/simplenlg_table5.png](http://simplenlg.googlecode.com/svn/wiki/simplenlg_table5.png)

## Document Planner ##
**content determination**
decides what information will appear in the output text. This depends on what your goal is, who the audience is, what sort of input information is available to you in the first place and other constraints such as allowed text length.

**document structuring**
decides how chunks of content should be grouped in a document, how to relate these groups to each other and in what order they should appear. For instance, when describing last month’s weather, you might talk first about temperature, then rainfall. Or you might start off generally talking about the weather and then provide specific weather events that occurred during the month.


## Microplanner ##
**lexicalization**
decides what specific words should be used to express the content. For example, the actual nouns, verbs, adjectives and adverbs to appear in the text are chosen from a lexicon. Particular syntactic structures are chosen as well. For example you can say ‘the car owned by Mary’ or you might prefer the phrase ‘Mary’s car’.

**referring expressions**
decides which expressions should be used to refer to entities (both concrete and abstract). The same entity can be referred to in many ways. For example March of last year can be referred to as:
  * `March 2006`
  * `March`
  * `March of the previous year`
  * `it`

**aggregation**
decides how the structures created by document planning should be mapped onto linguistic structures such as sentences and paragraphs. For instance, two ideas can be expressed in two sentences or in one:

```
          The month was cooler than average.The month was drier than average.
```

vs.

```
           The month was cooler and drier than average.
```


## Surface Realiser ##
**linguistic realisation**
uses rules of grammar (about morphology and syntax) to convert abstract representations of sentences into actual text.

**structure realization**
converts abstract structures such as paragraphs and sentences into mark-up symbols which are used to display the text.

simplenlg performs the last part, namely surface realisation.