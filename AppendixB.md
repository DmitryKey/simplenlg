# simplenlg and Eclipse #
If you are using Eclipse to write your Java programs, you would

**Using jar distribution:**

  1. Create a new Project (File>New>Project).
  1. Add the simplenlg library to the project’s build path by doing the following:
    * go to the Eclipse menu and select Project>Properties>Java Build Path
    * click on the Libraries tab
    * click on the **Add External jars...** button
    * browse to the simplenlg jar file which you downloaded, and extracted from the ZIP file, from the web
    * select the simplenlg jar file and click **Open**
    * click **OK**
  1. In the project, create a new class which has the main method in it. In this example, let’s call the new class `TestMain`.
  1. At the top of the class, put in the following import statements:
```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;
```

Following these steps, you should have code that looks like the following:

```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;

        public class TestMain {

	        /**
        	 * @param args
	         */
        	public static void main(String[] args) {
	        	// TODO Auto-generated method stub

        	}        

        }
```

**Using source code:**

If you are using the simplenlg source code you need to set up several things.

  1. Create a new Project (File>New>Project).
  1. Add the additional libraries to the project’s build path by doing the following:
    * go to the Eclipse menu and select Project>Properties>Java Build Path
    * click on the Libraries tab
    * click on the **Add External jars** button
    * browse to the `simplenlg/lib` folder and select all jar files
    * click **Open**
    * click **OK**
  1. Add the simplenlg source code to your project.
  * open the project properties again and click on the **sources** tab.
  * click on the **Link Source...** button and add the path to the `simplenlg/src` folder; type `simplenlg` into the folder name.
  * click **finish** and then **OK**.
# In the project, create a new class which has the main method in it. In this example, let’s call the new class `TestMain`.
  1. At the top of the class, put in the following import statements:
```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;
```

Following these steps, you should have code that looks like the following:

```
        import simplenlg.framework.*;
        import simplenlg.lexicon.*;
        import simplenlg.realiser.english.*;
        import simplenlg.phrasespec.*;
        import simplenlg.features.*;

        public class TestMain {

	        /**
        	 * @param args
	         */
        	public static void main(String[] args) {
	        	// TODO Auto-generated method stub

        	}        

        }
```

There should be two folders in your project. The `src` folder, for your code, and the `simplenlg` folder, which contains the linked simplenlg source code.

You should be now ready to start implementing your own classes that use simplenlg.