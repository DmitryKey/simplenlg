# What is simplenlg? #
simplenlg can be used to help you write a program which generates grammatically correct English sentences. It’s a library (not an application), written in Java, which performs simple and useful tasks that are necessary for natural language generation (NLG).

simplenlg is distributed as a stand-alone jar file, available under the Downloads tab. The jar file contains all the classes you'll need, as well as a lexicon (discussed later).  If you are interested in the source, it is available in the Source tab on Google Code.

Because it’s a library, you will need to write your own Java program which makes use of simplenlg classes. These classes will allow you to specify the subject of a sentence (‘my dog’), the exact verb you want to appear in the sentence (‘chase’), the object (‘George’), and additional complements (‘because George looked funny’). You can also use simplenlg methods to indicate, for example, that you would like the verb to be in the past tense and expressed in the progressive form (‘was chasing’).  If this is already confusing, don't worry -- this tutorial will help you with all of that.

Once you have stipulated what the content of your sentence will be and expressed this information in simplenlg terms, simplenlg can assemble the parts of your sentence into a grammatical form and output the result. In our example, the resulting output would be "My dog was chasing George because George looked funny". Here, simplenlg has:

  * organized all the different parts into the correct order for English
  * capitalized the first letter of the sentence
  * added the auxiliary ‘was’ and made it agree with the subject
  * added ‘-ing’ to the end of the verb (because the progressive aspect of the verb is desired)
  * put all the words together in a grammatical form
  * inserted the appropriate whitespace between the words of the sentence
  * put a period at the end of the sentence

As you can see, simplenlg will not choose particular words for you: you will need to specify the words you want to appear in the output and their parts of speech. What simplenlg’s library of classes will do for you is create a grammatically correct sentence from the parts of speech you have provided it with. simplenlg automates some of the more mundane tasks that all natural language generation (NLG) systems need to perform. (For more information on NLG, see Appendix A). Tasks such as:

**orthography:**
  * inserting appropriate whitespace in sentences and paragraphs
  * absorbing punctuation – for example, generating the sentence `"He lives in Washington D.C."` instead of  `"He lives in Washington D.C.."` (i.e., the sentence ends with a single period rather than two)
  * pouring – that is, inserting line breaks between words (rather than in the middle of a word) in order to fit text into rows of, for example, 80 characters (or whatever length you choose)
  * formatting lists such as "apples, pears and oranges"

**morphology:**
  * handling inflected forms – that is, modifying/marking a word/lexeme to reflect grammatical information such as gender, tense, number or person.

**simple grammar:**
  * ensuring grammatical correctness by, among other things, enforcing noun-verb agreement `[1]`
  * creating well-formed verb groups (i.e., verb plus auxiliaries) such as "does not like"
  * allowing the user to define parts of a sentence or phrase and having simplenlg gather those parts together into an appropriate syntactic structure

For those familiar with the terminology of natural language generation (NLG), simplenlg is a realiser for a simple grammar. We hope that simplenlg will eventually provide simple algorithms for not only realization but all of microplanning as well. As its functionality expands over time, components such as microplanning will be added as self-contained modules: self-contained, in order to allow students and researchers use of parts of the library they want, with the freedom to extend or replace other modules with their own implementations.


---

`[1]` agreement describes how a word’s form sometimes depends on other words that appear with it in a sentence. For example you don’t say "I is" in English, because "is" cannot be used when the subject is "I". The word "is" is said not to agree with the word "I". The correct form is "I am", even though the verb still has the same function and basic meaning.