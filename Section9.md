# Modifiers vs. complements #

As shown in [Table 1](Section3.md), there are several different kinds of phrase in simplenlg: noun phrases (which are represented by the Java class `NPPhraseSpec`), clauses or full sentences (which are represented by `SPhraseSpec`), prepositional phrases (represented by the class `PPPhraseSpec`), adjective phrases and adverb phrases. Adjective phrases and adverb phrases can be generated using the simplenlg concepts of modifier (when they modify a specific word/phrase) and complement (when they should occur after the verb).

Simplenlg in fact distinguishes between three types of modifiers: front modifiers (which go at the beginning of a phrase), pre-modifiers (which go immediately before the main noun or verb in a phrase), and post-modifiers (which go at  the end of a phrase).  You can directly specify where a modifier goes by using `addFrontModifier()`, `addPremodifier()`, or `addPostmodifier()`. If you use the more general `addModifier()`, then simplenlg will decide where to place your modifier.

Pre- and post- modifiers are allowed in all types of phrases. Front modifiers can only be specified for `SPhraseSpec`.