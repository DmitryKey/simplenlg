# Simplenlg has moved to Github! #

Because Googlecode is being wound down, we have moved simplenlg to Github, at https://github.com/simplenlg/simplenlg .  Releases from 4.4.3 onwards will only be available at the Github site.  Note that there a number of other GitHub projects which shadow or mirror simplenlg, we advise looking at https://github.com/simplenlg/simplenlg because it is the master site.

## Introduction ##

Simplenlg is a simple Java API designed to facilitate the generation of Natural Language. It was originally developed at the [University of Aberdeen's Department of Computing Science](http://www.csd.abdn.ac.uk).

Simplenlg is intended to function as a "[realisation engine](http://en.wikipedia.org/wiki/Realization_(linguistics))" for [Natural Language Generation](http://en.wikipedia.org/wiki/Natural_language_generation) architectures, and has been used successfully in a number of projects, both academic and commercial.   It handles the following

  * Lexicon/morphology system: The default lexicon computes inflected forms (morphological realisation). We believe this has fair coverage.  Better coverage can be obtained by using the [NIH Specialist Lexicon](http://lexsrv3.nlm.nih.gov/LexSysGroup/Projects/lexicon/current/web) (which is [supported by simplenlg](http://code.google.com/p/simplenlg/wiki/AppendixC)).
  * Realiser: Generates texts from a syntactic form. Grammatical coverage is limited compared to tools such as KPML and FUF/SURGE, but we believe it is adequate for many NLG tasks.
  * Microplanning: Currently just simple aggregation, hopefully will grow over time.

## Current release (English) ##

The current release of simplenlg is V4.4 ([API](http://simplenlg.googlecode.com/svn/wiki/javadoc/index.html)).  It can be downloaded from the downloads tab.   The "official" version of simplenlg only produces texts in English.  However, versions for other languages are under development, see the [Papers and Publications](http://code.google.com/p/simplenlg/wiki/Papers) page and [simplenlg discussion list](http://groups.google.com/group/simplenlg) for details.

Please note that earlier versions of simplenlg have different licensing, in particular versions before V4.0 cannot be used commercially.

## Getting started ##
For information on how to use simplenlg, please see the [tutorial](http://code.google.com/p/simplenlg/wiki/Tutorial)
and [API](http://simplenlg.googlecode.com/svn/trunk/docs/javadoc/index.html).

If you have a technical question about using simplenlg, please check the [simplenlg discussion list](http://groups.google.com/group/simplenlg).

If you wish to be informed about simplenlg updates and events, please subscribe to the [simplenlg announcement list](http://groups.google.com/group/simplenlg-announce).

If you wish to cite simplenlg in an academic publication, please cite the following paper

  * A Gatt and E Reiter (2009). [SimpleNLG: A realisation engine for practical applications](http://aclweb.org/anthology-new/W/W09/W09-0613.pdf). _Proceedings of ENLG-2009_

If you have other questions about simplenlg, please contact Ehud Reiter (University of Aberdeen); you can find his email via Google.

## Simplenlg for other languages ##
  * A version of SimpleNLG has now been developed for **French** by **Pierre-Luc Vaudry** at the Université de Montreal. This is a development based on the version 4 architecture. It is current being distributed separately [from this page](http://www-etud.iro.umontreal.ca/~vaudrypl/snlgbil/snlgEnFr_english.html).

  * **Marcel Bollman** has been working on an adaptation of SimpleNLG version 3 to **German**. This is available [from this page](http://www.linguistics.rub.de/~bollmann/simplenlg-ger.html). Please remember that SimpleNLG version 3 is not licensed for commercial use