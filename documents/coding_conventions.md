# Coding Conventions

### Java Files

Packages shall come before imports.

Imports shall be seperated by their first delimiter using newlines.

Sections of code (Variables, Constructors, Methods, Returns, etc.) shall be seperated using newlines.

All opening curly brackets shall sit inline with a space before it, followed by a newline.

All closing curly brackets shall sit  under the last line in it's code block, with no newline above.

Java files shall be setup as shown below:

```java
pacakage package.app.logic;

import thing.partOfThing;
import thing.somethingElse;

import stuff.partOfStuff;
import stuff.thisIsStuff;

public class MyClass extends ExtendableClass {

    // Variables.
    private String variable1;
    private int variable2;
    private final boolean VARIABLE_THREE;

    // Constructors.
    public MyClass(String parameter1, int parameter2) {
        this.variable1 = parameter1;
        this.variable2 = paramter2;
        this.VARIABLE_THREE = true;
    }

    // Methods.
    public boolean myMethod(int parameter2) {

        // Variables.
        private boolean thing = true;

        // Logic.
        if(parameter2 > 1) {
            thing = false;
        }

        // Return.
        return thing;
    }

    @Override
    // Methods.
}
```

### Java Classes

Java classes shall be spaceless strings with each compound word capatalized (Pascal Case).

```java
MyClass
```

### Java Variables

Dynamic Java variables shall be spaceless strings with each compound word capatalized exluding the first (Camel Case).

```java
int myVariableInteger
String myVariableString
```

Static or Final java variables shall be spaced using underscores between words and capatalized (Screaming Snake Case).

```java
static MY_STATIC_VARIABLE
final MY_FINAL_VARIABLE
```

### Java Methods

Java methods shall be spaceless strings with each compound word capatalized exluding the first (Camel Case).

```java
public void myMethod() { doThing(); }
```

### Java Comments

Everything that cannot be easily understood (> 5s of thinking) shall be commented.

Java comments shall be placed directly above (without a newline) the section of code they are describing or inline with variables or conditionals that need further explanation.

Multi-line comments shall be stacked double forward slash comments.

Java comments shall be formated as shown below in all cases:

```java
// This is a comment describing something.
// This is a further explanation.
```

### XML Files

XML tag blocks shall be seperated using new lines.

XML tag closing brackets for singular tags shall be inline with the final line in the code block without a space.

```xml
<Button
    android:id="@+id/my_button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

XML tag closing brackets for nested tags shall be stacked on a new line after the final code block.

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <RelativeLayout
        android:layout_width="600dp"
        android:layout_height="match_parent">

        <Button
        android:id="@+id/my_button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    </RelativeLayout>
</LinearLayout>
```

XML files shall be underscored strings in all lower case (Snake Case).

```xml
my_file.xml
```

### XML Variables

XML variables shall be underscored strings in all lower case (Snake Case).

```xml
android:id="@+id/my_button"
```

### XML Comments

Everything that cannot be easily understood (> 5s of thinking) shall be commented.

XML comments shall be placed directly above (without a newline) the section of code they are describing or inline with sections that are hard to understand.

Multi-line comments shall be stacked singular comments.

XML comments shall be formated as shown below in all cases:

```xml
<!-- This is a comment describing something. -->
<!-- This is a further explanation. -->
```

### General Documents

Certain documents my require speical formating, such as our README file, for cases such as this use your best judgement.

All other document names shall be underscored strings in all lower case (Snake Case).

```txt
this_is_a_document.md
```

### Coding Principles:

- Keep your methods short.
- Comment everything that would require time spent thinking.
- Declare your variabels as close as possible to the place where you will use them.
- Methods which can be split apart should be.
- All classes should have a related interface, even singular pairs.
- Names for things should be descriptive and not require comments to explain (no iInterface, no cClass, no vVariable).

### Branching

Any code changes shall be pushed to a new branch and then requested to be merged (and deleted) to the develop branch.

Final versions for iteration releases shall be merged (and not deleted) to main from develop.

Existing code in main and develop shall not be edited directly and only merged in to.

Non-code files may be edited directly on main.

Non-code files shall not be present on develop or lesser branches.

### Modifying Existing Documents or Conventions

Changing conventions or rules we have put forth and decided on must be done as a group of 3 or more.