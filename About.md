# Introduction #

Simplenlg is a simple Java API designed to facilitate the generation of Natural Language. It was originally developed at the [University of Aberdeen's Department of Computing Science](http://www.csd.abdn.ac.uk).

Simplenlg is intended to function as a "realisation engine" for Natural Language Generation architectures, and has been used successfully in a number of projects, including the [BabyTalk](http://www.abdn.ac.uk/ncs/computing/research/nlg/projects/previous/babytalk/) and [BabyTalk-Family](http://www.abdn.ac.uk/ncs/computing/research/nlg/projects/current/babytalk/) Projects.

For details of its design and examples of its use, please refer to the following publication:

  * A. Gatt and E. Reiter. (2009). [SimpleNLG: A realisation engine for practical applications](http://aclweb.org/anthology-new/W/W09/W09-0613.pdf). Proceedings of the 12th European Workshop on Natural Language Generation (ENLG-09).

The above paper is also the best academic citation for simplenlg.

For other papers and presentations about simplenlg, please see the [Papers and Presentations](Papers.md) page

# Version history and hosting #
SimpleNLG is currently hosted on Google Code.

Earlier versions of simplenlg can be found on
http://www.csd.abdn.ac.uk/research/simplenlg/; these are no longer being maintained.  Please note that earlier versions of simplenlg have different licensing, in particular versions before V4.0 cannot be used commercially.


# Summary of features #
SimpleNLG handles the following:

  * Lexicon/morphology system: The default lexicon computes inflected forms (morphological realisation). We believe this has fair coverage.  Better coverage can be obtained by using the [NIH Specialist Lexicon](http://lexsrv3.nlm.nih.gov/LexSysGroup/Projects/lexicon/current/web) (which is supported by simplenlg).
  * Realiser: Generates texts from a syntactic form. Grammatical coverage is very limited compared to tools such as KPML and FUF/SURGE, but we believe it is adequate for many NLG tasks.
  * Microplanning: Currently just simple aggregation, hopefully will grow over time.