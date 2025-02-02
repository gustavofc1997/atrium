[![Download](https://api.bintray.com/packages/robstoll/tutteli-jars/atrium/images/download.svg)](https://bintray.com/robstoll/tutteli-jars/atrium/_latestVersion)
[![Slack](https://img.shields.io/badge/Slack-atrium@kotlinlang-blue.svg)](https://kotlinlang.slack.com/messages/C887ZKGCQ)
[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status Travis](https://travis-ci.org/robstoll/atrium.svg?branch=master)](https://travis-ci.org/robstoll/atrium/branches)
[![Build status AppVeyor](https://ci.appveyor.com/api/projects/status/j7o4odbtvpnb1th0/branch/master?svg=true)](https://ci.appveyor.com/project/robstoll/atrium/branch/master)
[![Coverage](https://codecov.io/gh/robstoll/atrium/branch/master/graph/badge.svg)](https://codecov.io/github/robstoll/atrium/branch/master)

# <img src="https://raw.githubusercontent.com/robstoll/atrium/gh-pages/logo.svg?sanitize=true" alt="Atrium" title="Atrium"/>
Atrium is an open-source multiplatform assertion library for Kotlin with support for JVM, JS and Android.
It is designed to support multiple [APIs](#apis), different error reporting styles and [Internationalization](#internationalization-1) (i18n). 
The project was inspired by AssertJ at first but moved on and provides now more 
flexibility, features and hints to its users (so to you :wink:).

Atrium is designed to be extensible as well as configurable 
and allows you to extend it with your own assertion functions, customise reporting 
or even replace core components with your own implementation in an easy way.

Atrium currently provides two [API](#apis) styles:
pure fluent and infix where both of them have their design focus on usability in conjunction with code completion functionality provided by your IDE.
See [Examples](#examples) below to get a feel for how you could benefit from Atrium.

----
:warning: You are taking a *sneak peek* at the next version. 
Please have a look at the README of the corresponding git tag in case you are looking for the documentation of a specific version.
For instance, the [README of v0.8.0](https://github.com/robstoll/atrium/tree/v0.8.0/README.md).

----

**Table of Content**
- [Installation](#installation)
  - [JVM](#jvm)
  - [JS](#js)
  - [Android](#android)
  - [Common](#common)
- [Examples](#examples)
  - [Your First Assertion](#your-first-assertion)
  - [Define Single Assertions or Assertion Groups](#define-single-assertions-or-assertion-groups)
  - [Expect an Exception](#expect-an-exception)
  - [Feature Assertions](#feature-assertions)
    - [Property and Method](#property-and-methods)
    - [Arbitrary Features](#arbitrary-features)
  - [Type Assertions](#type-assertions)
  - [Nullable Types](#nullable-types)
  - [Collection Assertions](#collection-assertions)
    - [Shortcut Functions](#shortcut-functions)
    - [Sophisticated Assertion Builders](#sophisticated-assertion-builders)
  - [Map Assertions](#map-assertions)    
  - [Data Driven Testing](#data-driven-testing)
  - [Further Examples](#further-examples)  
- [How is Atrium different from other Assertion Libraries](#how-is-atrium-different-from-other-assertion-libraries)
- [Write own Assertion Functions](#write-own-assertion-functions)
    - [Boolean based Assertions](#boolean-based-assertions)
    - [Compose Functions](#compose-assertion-functions)
    - [Enhanced Reporting](#enhanced-reporting)
    - [Own Sophisticated Assertion Builders](#own-sophisticated-assertion-builders)
- [Use own Assertion Verbs](#use-own-assertion-verbs)
  - [ReporterBuilder](#reporterbuilder)
- [Internationalization](#internationalization-1)
- [APIs](#apis)
- [Java Interoperability](#java-interoperability)
- [KDoc - Code Documentation](#kdoc---code-documentation)
- [Known Limitations](#known-limitations)
- [FAQ](#faq)
- [Kotlin Bugs](#kotlin-bugs)
- [Roadmap](#roadmap)
- [Contributors and contribute](#contributors-and-contribute)
- [License](#license)

# Installation

## JVM
Atrium is linked to [jcenter](https://bintray.com/bintray/jcenter?filterByPkgName=atrium)
but can also be retrieved directly from [bintray](https://bintray.com/robstoll/tutteli-jars/atrium). 

*gradle*: 
```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-fluent-en_GB:$atrium_version"
}
```
We have defined a dependency to the bundle `atrium-fluent-en_GB` in the above example 
which provides a pure fluent API (in en_GB) for the JVM platform.   

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-infix-en_GB:$atrium_version"
}
```
<hr/>
</details>

<details>
<summary>click to see how the setup for the fluent API in German looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-fluent-de_CH:$atrium_version"
}
```
<hr/>
</details>
<br/>

*maven*:  
Because maven is a bit more verbose than gradle, the example is not listed here but 
a [settings.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/settings.xml) 
is provided to set up the repository as well as an 
[example pom.xml](https://github.com/robstoll/atrium/tree/master/misc/maven/example-pom.xml)
which includes the necessary dependencies.

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.


## JS

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB-js:$atrium_version")
}
```

We have defined a dependency to the bundle `atrium-fluent-en_GB-robstoll` in the above example 
which provides a pure fluent API (in en_GB) for the JS platform.

You need to setup an explicit dependency on `atrium-fluent-en_GB-js` in your test code in order that you can use Atrium.
This is due to the loosely coupled design of Atrium and dead code elimination performed by the Kotlin compilerf or JS.
An example of how to setup Atrium in combination with the testing framework mocha is given in 
[samples/js/mocha](https://github.com/robstoll/atrium/tree/master/samples/js/mocha).
It also includes an automated way of establishing the dependency to Atrium.

Atrium itself is using mocha as well 
(see [build.gradle -> createJsTestTask](https://github.com/robstoll/atrium/tree/master/build.gradle#L290))
and has tests written in JS modules 
(see [AdjustStackTest](https://github.com/robstoll/atrium/tree/master/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/ch/tutteli/atrium/core/robstoll/lib/reporting/AdjustStackTest.kt))
as well as tests written in common modules (e.g. [SmokeTest](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB-common/src/test/kotlin/SmokeTest.kt))
which are executed on the JS platform as well 
(actually on all platforms -> JVM uses JUnit for this purpose, see 
[build.gradle -> useJupiter](https://github.com/robstoll/atrium/tree/master/build.gradle#L342)).

Further examples for other test frameworks can be found in the
[kotlin-examples repo](https://github.com/JetBrains/kotlin-examples/blob/master/gradle/js-tests).
Notice though, that they do not include the automated setup of a dependency to a bundle of Atrium.
Or in other words, you should at least create a gradle task similar to 
[establishDependencyToAtrium](https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L85)
or include a [testSetup.kt]((https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L80))
file in your test sources.

<details>
<summary>click to see how the setup for the infix API looks like</summary>

```
buildscript {
    ext { atrium_version='0.8.0' }
}
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/robstoll/tutteli-jars" }
}
dependencies {
    testImplementation "ch.tutteli.atrium:atrium-infix-en_GB-js:$atrium_version"
}
```
<hr/>
</details>

That is all, you are all set. Jump to [Examples](#examples) which shows how to use Atrium.

## Android

The setup for using Atrium in an Android project is basically the same as for the [JVM setup](#jvm), you only need to
suffix the dependency with `-android` in addition. 
For instance `atrium-fluent-en_GB-android` instead of `atrium-fluent-en_GB`.

## Common

The setup for using Atrium in a common module of a multiplatform project is basically the same as for the
[JVM setup](#jvm), you only need to suffix the dependency with `-common` in addition. 
For instance `atrium-fluent-en_GB-common` instead of `atrium-fluent-en_GB`.

Have a look at [JVM](#jvm), [JS](#js) or [Android](#android) to see how the setup of a specific platform has to be done.


# Examples
We are using the API provided by the bundle module 
[atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB/build.gradle)
in the following examples. 
It provides a pure fluent API for the JVM platform.
Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
to see how the infix API looks like, how they differ respectively.

## Your First Assertion
We start off with a simple example:
```kotlin
import ch.tutteli.atrium.api.verbs.expect
val x = 10
expect(x).toBe(9)
``` 
The statement can be read as "I expect, x to be nine" where an equality check is used (for an identity check, you have to use `isSameAs`). 
Since this is false, an `AssertionError` is thrown with the following message:
```text
expect: 10        (kotlin.Int <934275857>)
◆ to be: 9        (kotlin.Int <1364913072>)
```
where `◆ ...` represents a single assertion for the subject (`10` in the above example) of the assertion.
The examples in the following sections include the error message (the output) in the code example itself as comments.

We have used the predefined assertion verb `expect` in the above example which we had to import first. 
We will omit the `import` statement in the remaining examples for brevity. 

**You want to run the example yourself?**
Have a look at the [Installation](#installation) section which explains how to set up a dependency to Atrium.

:information_source: _&lt;- this icon signifies additional information, worth reading IMO but if you are only after code examples,
then you can skip now to the next section (otherwise click on the arrow to expand the section)._<br/> 

<details>
<summary>:information_source: further assertion verbs...</summary>

Atrium provides two further assertion verbs next to `expect` out of the box: `assert` and `assertThat`
which you can import with `import ch.tutteli.atrium.api.verbs.assert`, `import ch.tutteli.atrium.api.verbs.assertThat` respectively. 
Yet, you can also [define your own assertion verbs](#use-own-assertion-verbs) if another is your favourite.
<hr/>
</details> 

The next section shows how you can define multiple assertions for the same subject.

## Define Single Assertions or Assertion Groups

```kotlin
// two single assertions
 
expect(4 + 6).isLessThan(5).isGreaterThan(10)
    // expect: 10        (kotlin.Int <1841396611>)
    // ◆ is less than: 5        (kotlin.Int <1577592551>)
```

Atrium allows you to chain assertions or in other words
you only need to write the `expect(...)` part once and can make several single assertions for the same subject.
The expression which determines the subject of the assertion (`4 + 6` in the above example) is evaluated only once. 

In this sense we could have written it also as follows (which is only the same because `4 + 6` does not have side effects).
```kotlin
expect(4 + 6).isLessThan(5)
expect(4 + 6).isGreaterThan(10)
``` 

Correspondingly, the first `expect` statement (which does not hold) throws an `AssertionError`. 
In the above example, `isLessThan(5)` is already wrong and thus `isGreaterThan(10)` was not evaluated at all.

If you want that both assertions are evaluated together, then use the assertion group syntax as follows: 

```kotlin
// assertion group with two assertions

expect(4 + 6) {
    isLessThan(5)
    isGreaterThan(10)
}
    // expect: 10        (kotlin.Int <1841396611>)
    // ◆ is less than: 5        (kotlin.Int <1577592551>)
    // ◆ is greater than: 10        (kotlin.Int <1841396611>)
```

An assertion group throws an `AssertionError` at the end of its block; hence reports that both assertions do not hold.

You can use `and` as filling element between single assertions and assertion group blocks:
```kotlin
expect(4 + 6).isLessThan(5).and.isGreaterThan(10)

expect(4 + 6) { 
    // ... 
} and { // if the previous block fails, then this one is not evaluated
    // ...
}
```
 
## Expect an Exception
```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty")
}.toThrow<IllegalStateException>()

    // expect the thrown exception: java.lang.IllegalArgumentException: name is empty        (java.lang.IllegalArgumentException <1364913072>)
    // ◆ is a: IllegalStateException (java.lang.IllegalStateException)
```
You can define an `expect` block together with the function `toThrow` to make the assertion that the block throws a certain exception 
(`IllegalStateException` in the example above). 

As with all narrowing functions, there are two overloads:
- the first is parameterless and turns only the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.
- the second expects an `assertionCreator` lambda in which you can define sub-assertions. 
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups). 
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  showing some additional context in case of a failure.

Following an example using both overloads:
```kotlin
expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException>().message.startsWith("firstName")
    // expect the thrown exception: java.lang.IllegalArgumentException
    // ◆ ▶ message: null
    //     ◾ is instance of type: String (kotlin.String) -- Class: String (java.lang.String)

expect {
    throw IllegalArgumentException()
}.toThrow<IllegalArgumentException> {
    message { startsWith("firstName") }
}
    // expect the thrown exception: java.lang.IllegalArgumentException
    // ◆ ▶ message: null
    //     ◾ is instance of type: String (kotlin.String) -- Class: String (java.lang.String)
    //       » starts with: "firstName"        <184607701>
```

Notice `message` is a shortcut for `feature(Throwable::message).notToBeNull`, which creates a feature assertion (see next section) 
about `Throwable::message`.  

There is also the counterpart to `toThrow` named `notToThrow`:
```kotlin
expect {
    //this block does something but eventually...
    throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
}.notToThrow()

    //  expect the thrown exception: java.lang.IllegalArgumentException
    //  ◆ is: not thrown at all
    //    » Properties of the unexpected IllegalArgumentException
    //      » message: "name is empty"        <401424608>
    //      » stacktrace: 
    //        ⚬ TestKt$main$2.invoke(test.kt:23)
    //        ⚬ TestKt$main$2.invoke(test.kt)
    //        ⚬ TestKt.main(test.kt:24)
    //      » cause: java.lang.RuntimeException
    //          » message: "a cause"        <1348949648>
    //          » stacktrace: 
    //            ⚬ TestKt$main$2.invoke(test.kt:23)
    //  	at TestKt.main(test.kt:24)
```
Notice that stacks are filtered so that you only see what is of interest. 
Filtering can be configured via [`ReporterBuilder`](#reporterbuilder) by choosing an appropriate 
[AtriumErrorAdjuster](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-atrium-error-adjuster/index.html). 
Stack frames of Atrium and of test runners (Spek, Kotlintest and JUnit for JVM, mocha for JS) are excluded per default.
[Create a Feature Request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
in case you use a different runner, we can add yours to the list as well. 
 
<a name="property-assertions"></a>
<a name="method-assertions"></a>
## Feature Assertions
Many times you are only interested in certain features of the subject and want to make assertions about them. 

There are different use cases for feature assertions. 
We will start of with properties and method calls and go on with more complicated scenarios.

### Property and Methods
```kotlin
data class Person(val firstName: String, val lastName: String, val isStudent: Boolean) {
    fun fullName() = "$firstName $lastName"
    fun nickname(includeLastName: Boolean) = when(includeLastName){
        false -> "Mr. $firstName"
        true -> "$firstName aka. $lastName"
    }
}
val myPerson = Person("Robert", "Stoll", false)

expect(myPerson)
    .feature({ f(it::isStudent) }) { toBe(true) } // fails, subject still Person afterwards
    .feature { f(it::fullName) }                  // not evaluated anymore, subject String afterwards
        .startsWith("rob")                        // not evaluated anymore
  
    // expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (Person <1841396611>)
    // ◆ ▶ isStudent: false
    //     ◾ to be: true
```  
<sub>We are sorry that the syntax is not yet the nicest one. 
We admit that one has to get used to it first and that is a pity. 
Yet, it is due to many [Kotlin Bugs](#kotlin-bugs) standing in the way.</sub>

`feature` has several overloads, we are looking at the one expecting a lambda in which you have to provide a `MetaFeature`.
Creating a `MetaFeature` is done via the function `f` by passing in a 
[bounded reference](https://kotlinlang.org/docs/reference/reflection.html#bound-function-and-property-references-since-11) 
of the corresponding property or method (including arguments if required).
`it` within the `MetaFeature`-provider-lambda refers to the subject of the assertion (`myPerson` in the above example).
Have a look at [Ambiguity Problems](#ambiguity-problems) in case the compiler is not happy (it is most likely due to a Kotlin Bug).

In the above example we created two assertions, one for the property `isStudent` of `myPerson` 
and a second one for the return value of calling `fullName()` on `myPerson`.
A feature assertion is indicated as follows in reporting. 
It starts with a `▶` followed by the feature's name and its actual value.
So the above output can be read as "I expect, Person's property `isStudent` (which is actually `false`) to be `true`. 
The second feature is not shown in reporting as the first already failed and we have chosen to use [single assertions](#define-single-assertions-or-assertion-groups) 
which have fail-fast semantic.

Feature assertions follow the common pattern of having two overloads:
- the first expects only the `MetaFeature`-provider-lambda. 
  This overload narrows the subject to the feature, 
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
  
- the second expects an `assertionCreator` lambda in addition, in which you can define sub-assertions for the feature.
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) or in other words, not-fail fast.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  Moreover, the subject stays the same so that subsequent calls are still about the same subject.

    ```kotlin
    expect(myPerson) { // forms an assertion group block
  
        feature({ f(it::firstName) }) { // forms an assertion group block
            startsWith("Pe")            // fails
            endsWith("er")              // is evaluated nonetheless
        }                               // fails as a whole
  
        feature { f(it::lastName) }.toBe("Dummy") // still evaluated, as it is in outer assertion group block
    }   
        // expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (Person <1841396611>)
        // ◆ ▶ firstName: "Robert"        <1818544933>
        //     ◾ starts with: "Pe"        <1793436274>
        //     ◾ ends with: "er"        <572868060>
        // ◆ ▶ lastName: "Stoll"        <1818544933>
        //     ◾ to be: "Dummy"        <233436274>
    ```

<br/>

Atrium provides several shortcuts for commonly used properties so that you can use them instead of writing `feature(...)` all the time.
For instance, `message` for Throwable (see [Expect an Exception](#expect-an-exception)), `first` and `second` for `Pair` and others.
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) in case you miss a shortcut.

:interrobang: &lt;- _this icon signifies answers/input for advanced users, you might want to skip them if you are new to Atrium._<br/>

<details>
<summary>:interrobang: Wrap each property into an assertion function? </summary>

You might be asking yourself whether it is better to [write an own assertion function](#write-own-assertion-functions) or use `feature`. 

The only drawback of using an existing property is that a few more key strokes are required compared to 
[writing an own assertion function](#write-own-assertion-functions) once and then reuse it (as I did with `message`).
Yet, I do not recommend to write an own assertion function for every single property.
I think it makes sense to add one if you use it a lot and (preferably) it is a stable API. 
Why not always? Because one quickly forgets to rename the assertion function 
if the property as such is renamed (e.g., as part of an IDE refactoring). 
As you can see, you would need to keep the property name and the name of the assertion function in sync to be meaningful 
(otherwise one gets quickly confused or has to remember two names for the same thing). 

Writing assertion functions for methods is a different story though, especially due to [overload bugs in Kotlin](#kotlin-bugs).
Also, code completion is not yet as good as it should be when it comes to methods. 
Last but not least, in case it is not always safe to call a method (e.g. `List.get` => IndexOutOfBound) then it makes
sense to wrap it into an assertion function and use `ExpectImpl.feature.extractor` instead.

</details>
  
Last but not least, let us have a look at an example where a method with arguments is used as feature:
```kotlin

expect(myPerson)
    .feature { f(subject::nickname, false) } // subject narrowed to String
        .toBe("Robert aka. Stoll")  // fails
        .startsWith("llotS")         // not evaluated anymore

    // expect: Person(firstName=Robert, lastName=Stoll, isStudent=false)        (Person <168907708>)
    // ◆ ▶ nickname(false): "Mr. Robert"        <1325465767>
    //     ◾ to be: "Robert aka. Stoll"        <1021258849>
```

`f` supports methods with up to 5 arguments.

<details>
<summary>:interrobang: Why only overloads for up to 5 parameters</summary>
 
You might be asking yourself why I stopped at 5 Parameters.
You could go on and create further overloads for 6 and more parameters, but... uh... can you smell it :stuck_out_tongue_winking_eye:.
In case you have a function with 6 or more parameters and you do not want or cannot to get rid of it, 
then I suggest that you [write a specific assertion function](#write-own-assertion-functions) for it.

</details>


Atrium provides shortcuts for commonly used methods - so far only: `List.get` and `Map.getExisting` 
where both include some additional checking (index bound and existence of the key within the map)
Please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]) 
in case you miss a shortcut. 

<details>
<summary>:interrobang: Write own feature assertion functions with additional checks.</summary>

Atrium provides a feature extractor which allows to make feature assertions in a safe way in case they are only valid for certain input.
See `ExpectImpl.feature.extractor`. It is for instance used for [`List.get`](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt)

</details>

### Arbitrary Features
A feature does not necessarily have to be directly related to the subject as properties or method calls do.
You can use the overload which expects a feature description in form of a `String` as first argument instead.
Following an example:
```kotlin
data class Person(val firstName: String, val lastName: String)
data class Family(val members: List<Person>)

val myFamily = Family(listOf(Person("Robert", "Stoll")))

expect(myFamily)
    .feature("number of members", { members.size }) { toBe(1) }      // subject still Family afterwards
    .feature("first person's lastName") { members.first().lastName } // subject narrowed to Person
    .toBe("Robert")

    // expect: Family(members=[Person(firstName=Robert, lastName=Stoll)])        (Family <1384560357>)
    // ◆ ▶ first person's lastName: "Stoll"        <931268856>
    //     ◾ to be: "Robert"        <86342242>
```

Also this version of `feature` provides two different kind of overloads:
- the first expects a feature description and a feature-provider-lambda
  This overload narrows the subject to the feature, 
  meaning a subsequent call in the fluent chain is about the feature and not the previous subject.
  
- the second expects an `assertionCreator` lambda in addition, in which you can define sub-assertions for the feature.
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) or in other words, not-fail fast.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  Moreover, the subject stays the same so that subsequent calls are still about the same subject.

As you can see, Atrium provides a generic way to postulate assertions about features. 
Yet, if you use such feature assertion often or it gets more complicated, 
then it might be worth to [write an own assertion function](#write-own-assertion-functions).

### Within Assertion Functions

In case you write an own assertion function, then we discourage to use the `MetaFeature`-provider-lambda 
(as shown in [Property and Methods](#property-and-methods))
but encourage to pass a [class references](https://kotlinlang.org/docs/reference/reflection.html#class-references)
to `feature` instead.
This has the benefit, that we can always show the feature name, also in case a previous feature extraction or subject
transformation failed.
Following an example: 
```kotlin
fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBeDoneWrong(expected: F) =
    feature({ f(it::first) }) { toBe(expected) }

fun <F : Any, T : Pair<F, *>> Expect<T>.firstToBe(expected: F) =
    feature(Pair<F, *>::first) { toBe(expected) }

expect(listOf(1 to "a", 2 to "b")).get(10) {
    firstToBeDoneWrong(1)
    firstToBe(1)
}
    // assert: [(1, a), (2, b)]        (java.util.Arrays.ArrayList <61380653>)
    // ◆ ▶ get(10): ❗❗ index out of bounds
    //       » ▶ CANNOT show description as it is based on subject which is not defined: CANNOT evaluate representation as it is based on subject which is not defined.
    //             » to be: 1        (kotlin.Int <1786454499>)
    //       » ▶ first: CANNOT evaluate representation as it is based on subject which is not defined.
    //             » to be: 1        (kotlin.Int <1786454499>)
```

Also this version of `feature` provides to kind of overloads, one without and and with `assertionCreator`-lambda.
(see for instance [Arbitrary Features](#arbitrary-features) for more information).

### Ambiguity Problems
Unfortunately there are several Kotlin bugs when it comes to overloading, especially in conjunction with `KFunction`
(see [Kotlin Bugs](#kotlin-bugs) and upvote in case you run into one).
However, Atrium provides alternative functions next to `f` within the `MetaFeature`-provider-lambda to disambiguate the situation.
Use `p` for properties and `f0` to `f5` for methods. 
Likely you need to specify the type parameters manually as Kotlin is not able to infer them correctly.

```kotlin
class WorstCase {
    val propAndFun: Int = 1
    @JsName("propFun")
    fun propAndFun(): Int = 1

    fun overloaded(): Int = 1
    fun overloaded(b: Boolean): Int = 1
}

expect(WorstCase()) {
    feature { p<Int>(it::propAndFun) }
    feature { f0<Int>(it::propAndFun) }
    feature { f0<Int>(it::overloaded) }
    feature { f1<Boolean, Int>(it::overloaded, true) }
}
```

Notice, that you might run into the situation that Intellij is happy but the compiler is not.
For instance, Intellij will suggest that you can remove the type parameters in the above example. 
Yet, if you do so, then the compiler will fail, mentioning ambiguous overloads. 
Most of the time this problem stems from the reason that the new type inference algorithm is used per default within Intellij 
(see File | Settings | Build, Execution, Deployment | Compiler | Kotlin Compiler => Enable new type inference...)

Next to using the alternative functions, you could also use the syntax for [arbitrary features](#arbitrary-features) 
to circumvent the problem -- up to you.

### Property does not exist

In case you deal with Java code, then you might run into the problem that a property does not exist. 
This is due to the fact that Kotlin only provides syntactic sugar to access a getter via property syntax. 
In such a case, use the `get...` method instead. For instance:
```java
// java
class A { 
    public String getFoo() { return "bar"; } 
}
```
```kotlin
// kotlin
val a = A()
a.foo // syntactic sugar, accesses getFoo via property
expect(a)
    // feature{ f(it::foo) }    // would result in a compile error
    .feature { f(it::getFoo) }  // works
    .startsWith(...)
``` 

## Type Assertions
```kotlin
interface SuperType
data class SubType1(val number: Int): SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
expect(x).isA<SubType1>()
  .feature { f(it::number) }.toBe(2)

    // ex'ect: SubType2(word=hello, flag=true)        (ch.tutteli.atrium.api.fluent.en_GB.SubType2 <265580735>)
    // ◆ is instance of type: SubType1 (ch.tutteli.atrium.api.fluent.en_GB.SubType1)
    // ◆ ▶ CANNOT show description as it is based on subject which is not defined: CANNOT evaluate representation as it is based on subject which is not defined.
    //       » to be: 2        (kotlin.Int <211013631>)


expect(x).isA<SubType2> {
    feature { f(it::word) }.toBe("goodbye")
    feature { f(it::flag) }.toBe(false)
}

    // expect: SubType2(word=hello, flag=true)        (ch.tutteli.atrium.api.fluent.en_GB.SubType2 <225792809>)
    // ◆ ▶ word: "hello"        <1169583232>
    //     ◾ to be: "goodbye"        <1413258258>
    // ◆ ▶ flag: true
    //     ◾ to be: false
```
You can narrow a type with the `isA` function. 
On one hand it checks that the subject of the current assertion (`x` in the above example) is actually the expected type 
and on the other hand it turns the subject into this type. 
This way you can make specific assertions which are only possible for the corresponding type
-- for instance, considering the above example, `number` is not available on `SuperType` but only on `SubType1`.

There are two `isA` overloads: 
- the first is parameterless and turns only the subject into the expected type; 
  failing to do so cannot include additional information in error reporting though.
- the second expects an `assertionCreator` lambda in which you can define sub-assertions. 
  An `assertionCreator` lambda has always the semantic of an [assertion group block](#define-single-assertions-or-assertion-groups) 
  -- as a recapitulation, assertions in an assertion group block are all evaluated and failures are reported at the end of the block.
  It has also the benefit, that Atrium can provide those sub-assertions in error reporting, 
  showing some additional context in case of a failure.

<details>
<summary>:interrobang: How to make arbitrary type transformations?</summary>

Atrium provides the possibility to make arbitrary subject transformations 
as long as you can provide a checking function which can tell whether the transformation is safe or not 
and a transformation function which performs the transformation as such.
For an example, have a look at the [EitherSpec](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/test/kotlin/ch/tutteli/atrium/domain/builders/creating/EitherSpec.kt).

</details>

## Nullable Types
Let us look at the case where the subject of the assertion has a [nullable type](https://kotlinlang.org/docs/reference/null-safety.html).
```kotlin
val slogan1 : String? = "postulating assertions made easy"
expect(slogan1).toBe(null)
    // expect: "postulating assertions made easy"        <22600334>
    // ◆ to be: null
    
val slogan2 : String? = null    
expect(slogan2).toBe("postulating assertions made easy")
    // expect: null
    // ◆ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //   » to be: "postulating assertions made easy"        <461160828>
```
On one hand, you can use `toBe` and pass the same type (`String?` in the above example, so in other words either `null` or a `String`).
On the other hand, you can use `notToBeNull` to turn the subject into its non-null version.
This is a shortcut for `isA<Xy>` where `Xy` is the non-nullable type (see [Type Assertions](#type-assertions)).
Following an example:

```kotlin
expect(slogan2).notToBeNull().startsWith("atrium")
    // expect: null
    // ◆ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)

expect(slogan2).notToBeNull { startsWith("atrium") }    
    // expect: null
    // ◆ is type or sub-type of: String (kotlin.String) -- Class: String (java.lang.String)
    //   >> starts with: "atrium"        <222427158>    
```
Since `notToBeNull` delegates to `isA` it also provides two overloads, one without and one with `assertionCreator`-lambda; see  [Type Assertions](#type-assertions) for more information.

Atrium provides one additional function which is intended for [data driven testing](#data-driven-testing) 
involving nullable types and is explained in the corresponding section.

<details>
<summary>:information_source: dealing a lot with nullable types from Java...</summary>

... in this case I recommend to have a look at the [Java Interoperability](#java-interoperability) section.

</details>

## Collection Assertions

Atrium provides assertion builders which allow to make sophisticated `contains` assertions for `Iterable<T>`.
Such a building process allows you to define very specific assertions, where the process is guided by a fluent builder pattern.
You can either use such an 
[Assertion Builder](#sophisticated-assertion-builders)
to create a specific assertion or one of the 
[Shortcut Functions](#shortcut-functions) in case you have kind of a common case.
The following sub sections show both use cases by examples.

### Shortcut Functions
```kotlin
expect(listOf(1, 2, 2, 4)).contains(2, 3)        

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1448525331>) 
    // ◆ contains, in any order: 3        (kotlin.Int <1108924067>)
    //   ⚬ ▶ number of occurrences: 0
    //       ◾ is at least: 1
```
 
The assertion function `contains(2, 3)` is a shortcut for using a 
[Sophisticated Assertion Builder](#sophisticated-assertion-builders) -- it actually calls `contains.inAnyOrder.atLeast(1).values(2, 3)`. 
This is reflected in the output, which tells us that we expected that the `number of occurrences` of `3` (which is actually `0`) `is at least: 1`.

<details>
<summary>:information_source: and what about expected value 2?</summary>

Exactly, what about the expected value `2`, why do we not see anything about it in the output?
The output does not show anything about the expected value `2` because the predefined assertion verbs have configured [`ReporterBuilder`](#reporterbuilder) 
to use an [Only Failure Reporter](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.core/-core-factory/new-only-failure-reporter.html) 
which shows us only assertions (or sub assertions) which failed.

Back to the shortcut functions.
<hr/>
</details>
 
Next to expecting that certain values are contained in or rather returned by an `Iterable`, 
Atrium allows us to use an `assertionCreator`-lambda to identify an entry
(`assertionCreator`-lambda can also be thought of as matcher / predicate in this context).
An entry is considered as identified, if it holds all specified assertions.
Following an example:
```kotlin
expect(listOf(1, 2, 2, 4)).contains({ isLessThan(0) }, { isGreaterThan(2); isLessThan(4) })

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1144068272>) 
    // ◆ contains, in any order:   
    //   ⚬ an entry which:   
    //       » is less than: 0        (kotlin.Int <1985836631>) 
    //     ⚬ ▶ number of occurrences: 0 
    //         ◾ is at least: 1 
    //   ⚬ an entry which:    
    //       » is greater than: 2        (kotlin.Int <1948471365>)
    //       » is less than: 4        (kotlin.Int <1636506029>) 
    //     ⚬ ▶ number of occurrences: 0
    //         ◾ is at least: 1
```
In the above example, neither of the two lambdas matched any entries and thus both are reported as failing (sub) assertions.

Another `contains` shortcut function which Atrium provides for `Iterable<T>` is kind of 
the opposite of `inAnyOrder.atLeast(1)` and is named `containsExactly`.
Again, Atrium provides two overloads for it, one for values,
e.g. `containsExactly(1, 2)` which calls `contains.inOrder.only.values(1, 2)` 
and a second one which expects one or more `assertionCreator`-lambda or `null`, 
e.g. `containsExactly({ isLessThan(0) }, { isGreaterThan(5) })` 
and effectively calls `contains.inOrder.only.entries({ isLessThan(2) }, { isGreaterThan(5) })`.
We will spare the examples here and show them in the following sections.
Notice that passing `null` to `containsExactly` makes only sense if your `Iterable` contains nullable entries.

Atrium provides also a `containsNot` shortcut function. 
Furthermore, it provides aliases for `contains` and `containsNot` named `any` and `none`,  which might be a better 
choice if you think in terms of: expect a predicate holds. These two are completed with an `all` assertion function:
```kotlin
expect(listOf(1, 2, 3, 4)).any { isLessThan(0) }
expect(listOf(1, 2, 3, 4)).none { isGreaterThan(2) }
expect(listOf(1, 2, 3, 4)).all { isGreatherThan(2) }
```

### Sophisticated Assertion Builders

Sophisticated assertion builders implement a fluent builder pattern.
To use the assertion builder for sophisticated `Iterable<T>`-contains-assertions, you can type `contains` 
-- as you would when using the [Shortcut Functions](#shortcut-functions) `contains` -- 
but type `.` as next step (so that you are using the property `contains` instead of one of the shortcut functions). 
Currently, the builder provides two options, either `inAnyOrder` or `inOrder`. 
In case you are using an IDE, you do not really have to think too much -- use code completion; 
the fluent builders will guide you through your decision making :relaxed:

Following on the last section we will start with an `inOrder` example:
```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.entries({ isLessThan(3) }, { isLessThan(2) })
 
    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <817978763>)
    // ◆ contains only, in order:     
    //   ✔ ▶ entry 0: 1        (kotlin.Int <1578009262>)
    //       ◾ an entry which:    
    //         ⚬ is less than: 3        (kotlin.Int <1108924067>)
    //   ✘ ▶ entry 1: 2        (kotlin.Int <1948471365>)
    //       ◾ an entry which:    
    //         ⚬ is less than: 2        (kotlin.Int <1948471365>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 2
    //         ❗❗ additional entries detected:    
    //            ⚬ entry 2: 2        (kotlin.Int <1948471365>)
    //            ⚬ entry 3: 4        (kotlin.Int <1636506029>) 
```  

Since we have chosen the `only` option, Atrium shows us a summary where we see three things:
- Whether a specified `assertionCreator`-lambda matched (signified by `✔` or `✘`) 
  the corresponding entry or not (e.g. `✘ ▶ entry 1:` was `2` and we expected, it `is less than 2`)
- Whether the expected size was correct or not (`✘ ▶ size:` was `4`, we expected it, `to be: 2` -- see also [Property Assertions](#property-assertions))
- and last but not least, mismatches or additional entries as further clue (`❗❗ additional entries detected`).

:heart_eyes: I am pretty sure you are going to love this feature as well. 
Please star Atrium if you like using it.

<details>
<summary>:interrobang: too verbose?</summary>

As side notice, in case you are dealing with large `Iterable` and do not want such a verbose output, 
then let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]). 
So far the verbose output was always handy for me but you might have other test cases than me.
Also notice, that Atrium cannot yet deal with infinite `Iterable`s.
If you have to, then please open a feature request as well. In the meantime, you can of course `take(100)` or the like.
<hr/>
</details>

Following one more example for `inOrder` as well as a few examples for `inAnyOrder`. 
I think explanations are no longer required at this stage.
In case you have a question (no matter about which section), then please turn up in the 
[atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ) 
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet) 
and I happily answer your question there. 

```kotlin
expect(listOf(1, 2, 2, 4)).contains.inOrder.only.values(1, 2, 2, 3, 4)

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1362728240>)
    // ◆ contains only, in order:   
    //   ✔ ▶ entry 0: 1        (kotlin.Int <1578009262>)
    //       ◾ to be: 1        (kotlin.Int <1578009262>)
    //   ✔ ▶ entry 1: 2        (kotlin.Int <1948471365>)
    //       ◾ to be: 2        (kotlin.Int <1948471365>)
    //   ✔ ▶ entry 2: 2        (kotlin.Int <1948471365>)
    //       ◾ to be: 2        (kotlin.Int <1948471365>)
    //   ✘ ▶ entry 3: 4        (kotlin.Int <1636506029>)
    //       ◾ to be: 3        (kotlin.Int <1108924067>)
    //   ✘ ▶ entry 4: ❗❗ hasNext() returned false
    //       ◾ to be: 4        (kotlin.Int <1636506029>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 5

expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.atLeast(1).butAtMost(2).entries({ isLessThan(3) })

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <1092572064>)
    // ◆ contains, in any order:   
    //   ⚬ an entry which:   
    //       » is less than: 3        (kotlin.Int <1108924067>)
    //     ⚬ ▶ number of occurrences: 3
    //         ◾ is at most: 2


expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(1, 2, 3, 4)
            
    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <922511709>)
    // ◆ contains only, in any order:    
    //   ✔ an entry which is: 1        (kotlin.Int <1578009262>) 
    //   ✔ an entry which is: 2        (kotlin.Int <1948471365>) 
    //   ✘ an entry which is: 3        (kotlin.Int <1108924067>)  
    //   ✔ an entry which is: 4        (kotlin.Int <1636506029>) 
    //   ✔ ▶ size: 4  
    //       ◾ to be: 4
    //   ❗❗ following entries were mismatched:    
    //      ⚬ 2        (kotlin.Int <1948471365>)
    
expect(listOf(1, 2, 2, 4)).contains.inAnyOrder.only.values(4, 3, 2, 2, 1)

    // expect: [1, 2, 2, 4]        (java.util.Arrays$ArrayList <331994761>)
    // ◆ contains only, in any order:   
    //   ✔ an entry which is: 4        (kotlin.Int <1636506029>)
    //   ✘ an entry which is: 3        (kotlin.Int <1108924067>)
    //   ✔ an entry which is: 2        (kotlin.Int <1948471365>)
    //   ✔ an entry which is: 2        (kotlin.Int <1948471365>)
    //   ✔ an entry which is: 1        (kotlin.Int <1578009262>)
    //   ✘ ▶ size: 4
    //       ◾ to be: 5
```     

## Map Assertions

```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains("c" to 2, "a" to 1, "b" to 1)

    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains, in any order: 
    //   ⚬ ▶ entry "c": ❗❗ key does not exist
    //         » to be: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "b": 2        (kotlin.Int <970865974>)
    //       ◾ to be: 1        (kotlin.Int <1827171553>)
```
 
Map assertions are kind of very similar to [Collection Assertions](#collection-assertions), also regarding reporting. 
That is the reason why we are not going into too much detail here because we assume you are already familiar with it.

Next to making assertions based on key value pairs one can also define sub assertions for the value of an entry with 
the help of the parameter object `KeyValue`:
```kotlin
expect(mapOf("a" to 1, "b" to 2)).contains(
    KeyValue("c") { toBe(2) },
    KeyValue("a") { isGreaterThan(2) },
    KeyValue("b") { isLessThan(2) }
)   
 
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains, in any order: 
    //   ⚬ ▶ entry "c": ❗❗ key does not exist
    //         » to be: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "a": 1        (kotlin.Int <1827171553>)
    //       ◾ is greater than: 2        (kotlin.Int <970865974>)
    //   ⚬ ▶ entry "b": 2        (kotlin.Int <970865974>)
    //       ◾ is less than: 2        (kotlin.Int <970865974>)
```

In case you want to postulate an assertion about a value of one particular key, then you can use `getExisting`.
For instance:
```kotlin
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val children: Collection<Person>
    // ...  and others 
)
val bernstein = Person("Leonard", "Bernstein", 50, children=listOf(/*...*/))

expect(mapOf("bernstein" to bernstein))
    .getExisting("bernstein") { 
        feature { f(it::firstName) }.toBe("Leonard")
        feature { f(it::age) }.toBe(60) 
    }
    .getExisting("einstein") { 
        feature { f(it::firstName) }.toBe("Albert") 
    }
    
    // expect: {bernstein=Person(firstName=Leonard, lastName=Bernstein, age=50, children=[])}        (java.util.Collections.SingletonMap <1389647288>)
    // ◆ ▶ get("bernstein"): Person(firstName=Leonard, lastName=Bernstein, age=50, children=[])        (Person <12209492>)
    //     ◾ ▶ age: 50        (kotlin.Int <314337396>)
    //         ◾ to be: 60        (kotlin.Int <232824863>)
    // ◆ ▶ get("einstein"): ❗❗ key does not exist
    //         ❗❗ Could not evaluate the defined assertion(s) -- given key does not exist.
    // Visit the following site for an explanation: https://docs.atriumlib.org/could-not-evaluate-assertions
```


In case you want to make an assertion only about the keys or values of the `Map` then you can use `keys` or `values`:
```kotlin
expect(mapOf("a" to 1, "b" to 2)) {
    keys { all { startsWith("a") } }
    values { none { isGreaterThan(1) } }
}
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <1690101810>)
    // ◆ ▶ keys: [a, b]        (java.util.LinkedHashMap.LinkedKeySet <1502335674>)
    //     ◾ all entries: 
    //         » starts with: "a"        <1517640897>
    //         ❗❗ following entries were mismatched: 
    //            ⚬ index 1: "b"        <2061774051>
    // ◆ ▶ values: [1, 2]        (java.util.LinkedHashMap.LinkedValues <240630125>)
    //     ◾ does not contain: 
    //       ⚬ an entry which: 
    //           » is greater than: 1        (kotlin.Int <851912430>)
    //         ✘ ▶ number of occurrences: 1
    //             ◾ is: 0        (kotlin.Int <586358252>)
    //         ✔ ▶ has at least one element: true
    //             ◾ is: true
``` 

Last but not least, you can use the non-reporting `asEntries()` function which
turns `Expect<Map<K, V>>` into an `Expect<Set<Map.Entry<K, V>>` and thus allows that you can use all the assertion 
functions and sophisticated builders shown in [Collection Assertions](#collection-assertions).

For instance, say you have a `LinkedHashMap` and want to be sure that the order is correct:
```kotlin
expect(linkedMapOf("a" to 1, "b" to 2)).asEntries().contains.inOrder.only.entries(
    { isKeyValue("a", 1) },
    {
        key { startsWith("a") }
        value { isGreaterThan(2) }
    }
)
    // expect: {a=1, b=2}        (java.util.LinkedHashMap <503938393>)
    // ◆ contains only, in order: 
    //   ✔ ▶ entry 0: a=1        (java.util.LinkedHashMap.Entry <970865974>)
    //       ◾ an entry which: 
    //           » ▶ key: "a"        <1827171553>
    //               ◾ to be: "a"        <1827171553>
    //           » ▶ value: 1        (kotlin.Int <1424482154>)
    //               ◾ to be: 1        (kotlin.Int <1424482154>)
    //   ✘ ▶ entry 1: b=2        (java.util.LinkedHashMap.Entry <1072506992>)
    //       ◾ an entry which: 
    //           » ▶ key: "a"        <1827171553>
    //               ◾ starts with: "a"        <1827171553>
    //           » ▶ value: 1        (kotlin.Int <1424482154>)
    //               ◾ is greater than: 2        (kotlin.Int <1997702454>)
    //   ✔ ▶ size: 2        (kotlin.Int <1997702454>)
    //       ◾ to be: 2        (kotlin.Int <1997702454>)
```
`isKeyValue` as well as `key {}` and `value {}` are assertion functions defined for `Map.Entry<K, V>`.


Following a non-exhaustive list of further functions: `containsKey`/`containsNotKey`, `isEmpty`, `hasSize` ...  
More examples are given at [apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)

And in case you should miss an assertion function, then please [open a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
For instance, you might want to upvote [containsInAnyOrderOnly](https://github.com/robstoll/atrium/issues/68)
in case you want this shortcut function as well.

## Data Driven Testing

Atrium is not intended for data driven testing in the narrowed sense in terms that it cannot produce multiple tests.
This is the responsibility of your test runner.
However, Atrium let you define multiple assertions within one test and reports them all if you want.
In this sense it can be used for data driven testing.
This is especially helpful in case your test runner does not support data driven testing (or other mechanisms like hierarchical or dynamic tests).
As an example, Atrium can help you writing data driven tests in a common module of a multiplatform-project.

The trick is to wrap your assertions into an [assertion group block](#define-single-assertions-or-assertion-groups)
and create [Feature Assertions](#feature-assertions). Following an example:

```kotlin
fun myFun(i: Int) = (i + 97).toChar()

@Test
fun myFun_happyCases() {
    expect("calling myFun with...") {
        mapOf(
            1 to 'a',
            2 to 'c',
            3 to 'e'
        ).forEach { (arg, result) ->
            feature { f(::myFun, arg) }.toBe(result)
        }
    }
}

    // expect: "calling myFun with..."        <1608446010>
    // ◆ ▶ myFun(1): 'b'
    //     ◾ to be: 'a'
    // ◆ ▶ myFun(3): 'd'
    //     ◾ to be: 'e'
```
Depending on the chosen [reporting style](#reporterbuilder) it will only show the failing cases (default behaviour).
This is also the reason why the call of `myFun(2)` is not listed (as the result is `c` as expected).

Please [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
if you want to see a summary, meaning also successful assertions -- I happily add more functionality if it is of use for someone.

Following another example which involves an assertion creator lambda and not only a simple `toBe` check. 
We are going to reuse the `myFun` from above
```kotlin
import ch.tutteli.atrium.domain.builders.utils.subExpect

expect("calling myFun with ...") {
    mapOf(
        1 to subExpect<Char> { isLessThan('f') },
        2 to subExpect { toBe('c') } ,
        3 to subExpect { isGreaterThan('e') }
    ).forEach { (arg, assertionCreator) ->
        feature({ f(::myFun, arg) }, assertionCreator)
    }
}
    
    // expect: "calling myFun with ..."        <537548559>
    // ◆ ▶ myFun(3): 'd'
    //     ◾ is greater than: 'e'
```
The example should be self explanatory.
One detail to note though is the usage of `subExpect`. 
It is a helper function which circumvents certain [Kotlin type inference bugs](#kotlin-bugs) (upvote them please).
Writing the same as `mapOf<Int, Expect<Char>.() -> Unit>( 1 to { ... } )` would not work as the type for a lambda 
involved in a Pair is not (yet) inferred correctly.

There is one last function worth mentioning here which comes in handy in data-driven testing in case the subject has a 
[nullable type]((https://kotlinlang.org/docs/reference/null-safety.html).)

If you wish to make sub-assertions on the non-nullable type of the subject, then you can use
`toBeNullIfNullGivenElse` which accepts an assertion creator or `null`.
It is short for `if (assertionCreatorOrNull == null) toBe(null) else notToBeNull(assertionCreatorOrNull)`. 
Following another fictional example which illustrates `toBeNullIfNullGivenElse` (we are reusing `myFun` from above):
```kotlin
fun myNullableFun(i: Int) = if (i > 0) i.toString() else null

expect("calling myNullableFun with ...") {
    mapOf(
        Int.MIN_VALUE to subExpect<String> { contains("min") },
        -1 to null,
        0 to null,
        1 to subExpect { toBe("1") },
        2 to subExpect { endsWith("2") },
        Int.MAX_VALUE to subExpect { toBe("max") }
    ).forEach { (arg, assertionCreatorOrNull) ->
        feature { f(::myNullableFun, arg) }.toBeNullIfNullGivenElse(assertionCreatorOrNull)
    }
}

    // expect: "calling myNullableFun with ..."        <1151647522>
    // ◆ ▶ myNullableFun(-2147483648): null
    //     ◾ is instance of type: String (kotlin.String) -- Class: String (java.lang.String)
    //       » contains: 
    //         ⚬ value: "min"        <418730847>
    //           ⚬ ▶ number of occurrences: -1
    //               ◾ is at least: 1
    // ◆ ▶ myNullableFun(2147483647): "2147483647"        <1352112848>
    //     ◾ to be: "max"        <456846682>
``` 

## Further Examples

Atrium supports further assertion builders (e.g, for `CharSequence`) 
as well as assertion functions which have not been shown in the examples.
Have a look at [apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md) for a few more examples.
This site contains also a list of all APIs with links to their assertion function catalogs.

You can also have a look at the 
[specifications](https://github.com/robstoll/atrium/tree/master/misc/specs/atrium-specs-common/src/main/kotlin/ch/tutteli/atrium/specs) 
for more examples.

# How is Atrium different from other Assertion Libraries

The following subsections shall give you a quick overview how Atrium differ from other assertion libraries. 

- [Ready to Help](#ready-to-help)
  - [Fluent API with Code Documentation](#1-fluent-api-with-code-documentation)
  - [Additional Information in Failure Reporting](#2-additional-information-in-failure-reporting)
  - [Prevents you from Pitfalls](#3-prevents-you-from-pitfalls)
- [Flexibility](#flexibility)
- [Migration of Deprecated Functionality](#migration-of-deprecated-functionality)
- [Internationalization](#internationalization)

## Ready to Help
Atrium is designed to help you whenever possible.
I think this is the biggest difference to other assertion libraries and a very handy one indeed.

### 1. Fluent API with Code Documentation
Atrium provides a fluent API where the design focus was put on the interoperability (of the API) 
with the code completion functionality of your IDE. 
Or in other words, you can always use code completion to get direct help from your IDE.
This experience is improved by providing up-to-date [code documentation](#kdoc) (in form of KDoc) for all assertion functions, 
so that you get the extra help needed.

:poop: &lt;- _this icon signifies a bug in Kotlin which you might encounter as well. 
We try to provide a workaround whenever possible._

<details>
<summary>:poop: There is no KDoc for toBe</summary>

There is, but IntelliJ will not show it to you due to [this bug](https://youtrack.jetbrains.com/issue/KT-24836) (please upvote it).
You should be able to see the KDoc of other functions without problems. 
But in case, you can also browse the online documentation, e.g. [KDoc of toBe](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/to-be.html).

</details>

### 2. Additional Information in Failure Reporting
Atrium adds extra information to error messages so that you get quickly a better idea of what went wrong. 
For instance, for the following assertion (which fails) 
```kotlin
expect(listOf(1, 2, 3)).contains.inOrder.only.values(1, 3)
```
Atrium points out which `values` were found, makes an implicit assertion about the size and 
also states which entries were additionally contained in the list:

```text
expect: [1, 2, 3]        (java.util.Arrays$ArrayList <1287934450>)
◆ contains only, in order: 
  ✔ ▶ entry 0: 1        (kotlin.Int <6519275>)
      ◾ to be: 1        (kotlin.Int <6519275>)
  ✘ ▶ entry 1: 2        (kotlin.Int <692331943>)
      ◾ to be: 3        (kotlin.Int <692331943>)
  ✘ ▶ size: 3
      ◾ to be: 2
        ❗❗ additional entries detected: 
           ⚬ entry 2: 3        (kotlin.Int <1741979653>)
```    

Let us have a look at another example.
```kotlin
expect(9.99f).toBeWithErrorTolerance(10.0f, 0.01f)
```
The above assertion looks good at first sight but actually fails (at least on my machine). 
And without some extra information in the output we would believe that there is actually a bug in the assertion library itself.
But Atrium shows where it goes wrong and even gives a possible hint:
```text
expect: 9.99        (java.lang.Float <1287934450>)
◆ to be (error ± 0.01): 10.0        (java.lang.Float <6519275>)
    » failure might be due to using java.lang.Float, see exact check on the next line
    » exact check is |9.989999771118164 - 10.0| = 0.010000228881835938 ≤ 0.009999999776482582
```

One last example. This time about making an assertion that a certain `Throwable` is thrown but the assertion fails 
because it was the wrong one. 
Atrium comes with a very useful hint, it shows the actual exception. For instance, for:
```kotlin
expect {
  try {
      throw UnsupportedOperationException("not supported")
  } catch(t: Throwable) {
      throw IllegalArgumentException("no no no...", t)
  }
}.toThrow<IllegalStateException> { messageContains("no no no") }
```
the error reporting looks as follows:
```text
expect the thrown exception: java.lang.IllegalArgumentException
◆ is instance of type: IllegalStateException (java.lang.IllegalStateException)
  » ▶ message: CANNOT evaluate representation as it is based on subject which is not defined.
        » is instance of type: String (kotlin.String) -- Class: String (java.lang.String)
        » contains: 
          ⚬ value: "no no no"        <781182788>
            ⚬ ▶ number of occurrences: -1
                ◾ is at least: 1
  » Properties of the unexpected IllegalArgumentException
    » message: "no no no..."        <477902612>
    » stacktrace: 
      ⚬ TestKt$main$1.invoke(test.kt:12)
      ⚬ TestKt$main$1.invoke(test.kt)
      ⚬ TestKt.main(test.kt:72)
    » cause: java.lang.UnsupportedOperationException
        » message: "not supported"        <985934102>
        » stacktrace: 
          ⚬ TestKt$main$1.invoke(test.kt:10)
```

### 3. Prevents you from Pitfalls
But not enough. There are certain pitfalls when it comes to using an assertion library and Atrium tries to prevent you from those.

For instance, an overload of `toBe` and of `notToBe` for `BigDecimal` was introduced which are both deprecated and throw a `PleaseReplaceException`. 
The reason behind it?
It is very likely that a user actually wants to compare that a certain `BigDecimal` is numerically (not) equal to another `BigDecimal` 
rather than including `BigDecimal.scale` in the comparison.
Accordingly, the deprecation message of `toBe` (`notToBe` alike) explains the problem and suggests to either use `isNumericallyEqualTo` or `isEqualIncludingScale`.
And if the user should decide to use `isEqualIncludingScale` and at some point an assertion fails only due to the comparison of `BigDecimal.scale`
then Atrium reminds us of the possible pitfall. For instance:
```text
expect: 10        (java.math.BigDecimal <1287934450>)
◆ is equal (including scale): 10.0        (java.math.BigDecimal <6519275>)
    » notice, if you used isNumericallyEqualTo then the assertion would have hold.
```

another example are empty `assertionCreator`-lambdas. 
Getting distracted by a working colleague and taking up the work at the wrong position might be familiar to you. 
For instance:
```kotlin
expect(listOf(1)).get(0) {}

    // expect: [1]        (java.util.Collections.SingletonList <1695611955>)
    // ◆ ▶ get(0): 1
    //       » at least one assertion defined: false
    //           » You forgot to define assertions in the assertionCreator-lambda
    //           » Sometimes you can use an alternative to `{ }` For instance, instead of `toThrow<..> { }` you should use `toThrow<..>()`
```

## Flexibility
Another design goal of Atrium was to give you the flexibility needed but still adhere to a concise design. 
First and most importantly, Atrium does not enforce a certain style on your code base. 
Quite the contrary, it gives you the flexibility to [choose a desired name for the assertion verbs](#use-own-assertion-verbs), 
it continues by providing the possibility to configure the [reporting style](#reporterbuilder), 
goes on that you can chose from different [API styles](#apis) 
and ends that you can replace almost all components by other implementations and hook into existing.

So for instance, if you like to use an `infix` API, then use the bundle `atrium-infix-en_GB`. 
You prefer pure fluent and do not even want to see infix style in your code, 
then use `atrium-fluent-en_GB` which provides a pure fluent style API. 

You are free to choose what fits best without introducing ambiguity etc.
You could even mix up different API-Styles if needed (but not without losing conciseness I guess -- but hey, it is your decision :wink:). 

## Migration of Deprecated Functionality
Atrium follows [Semantic Versioning](https://semver.org/) and tries to be binary backward compatible within a major version (since 0.6.0).
Until 1.0.0 this is only true for the API level, we reserve the right to break things on the domain and core level until then.
Moreover, we follow the principle that a user of Atrium has enough time to migrate its code to new functionality before a next major version.
We provide this in form of `@Deprecated` annotations with a corresponding `ReplaceWith` 
as well as migration guides in the [Release Notes](https://github.com/robstoll/atrium/releases).
This way we hope that we provide a pleasant way to stay up-to-date without the need to migrate everything from one day to the other.

## Internationalization
The last difference is not yet fully-blown implemented but the design of Atrium has everything needed to go down the planed [Roadmap](#roadmap).
Might well be that this topic is not really a concern of yours; unless...  

- you are using domain-driven-design and would like to adopt the ubiquitous language also to your test code.
- you want to document the results of your defined assertions (in different languages) 

Atrium already supports APIs in two languages, and it is an easy task to translate an API to another language (hello DDD-people :wave: you are good to go).
Moreover, it is already possible to generate the output in a different language than the used API (e.g. code in English but report in German).

Together with the HTML-Report feature (currently missing but will follow) you will be able to generate reports in different languages.
Already the HTML-Report feature as such might be of your interest. 
You can use it to document your user stories etc (almost) for free.
In case you have clients who speak different languages then the HTML-Report together with the i18n feature will be especially helpful. 
I should not go on here, the HTML-Report feature is not yet implemented, but you can see what kind of road I plan to go down to.

# Write own Assertion Functions

Are you missing an assertion function for a specific type and the generic 
[Feature Assertions](#feature-assertions) are not good enough?

The following sub sections will show how you can write your own assertion functions. 
A pull request of your new assertion function is very much appreciated.

## Boolean based Assertions

This is kind of the simplest way of defining assertion functions. Following an example:

```kotlin
fun Expect<Int>.isMultipleOf(base: Int) =
    createAndAddAssertion("is multiple of", base) { it % base == 0 }
```
and its usage:

```kotlin
expect(12).isMultipleOf(5)
    // expect: 12        (kotlin.Int <934275857>)
    // ◆ is multiple of: 5        (kotlin.Int <1364913072>)
```

Let us see how we actually defined `isMultipleOf`. 
1. *Choose the extension point*: in our example we want to provide the assertion function for `Int`s. 
    Hence we define `isMultipleOf` as [extension function](https://kotlinlang.org/docs/reference/extensions.html) of `Expect<Int>`.

2. *Use the method `createAndAddAssertion`* (which is provided by `Expect`) to create the assertion 
    and add it to `Expect` (so that is also evaluated, creating alone is not enough). 
    The method `createAndAddAssertion` returns the `Expect` making it easy for you to provide a fluent API as well.
 
    The method [createAndAddAssertion](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.creating/-assertion-plant/create-and-add-assertion.html)
    expects:
    - a either a `String` or a [Translatable](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translatable/index.html)
      as description of your assertion.
    - the representation of the expected value.
    - and the actual check as lambda where you typically use the `it` which refers to the subject of the assertion.
     
We used a `String` as description in the above example because we are not bothered with internationalization at this point
(have a look at [Internationalization](#internationalization-1) if you are).

In most cases you probably use the expected value itself as its representation -- so you pass it as second argument.
And finally you specify the test as such in the lambda passed as third argument.

But not all assertion functions require a value which is somehow compared against the subject 
-- some make an assertion about a characteristic of the subject without comparing it against an expected value.
Consider the following assertion function:

```kotlin
fun Expect<Int>.isEven() = 
    createAndAddAssertion("is", RawString.create("an even number")) { it % 2 == 0 }
```
We are using a [RawString](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting/-raw-string/index.html)
here so that `"an even number"` is not treated as a `String` in reporting.
Its usage looks then as follows:

```kotlin
expect(13).isEven()
    // expect: 13        (kotlin.Int <1841396611>)
    // ◆ is: an even number
```

## Compose Assertion Functions

So far I ran quickly into the situation where I want to compose functions or 
reuse existing functions but with different arguments. 
We will show both use cases here, starting off by composing functions. 

Say you want to build a `isBetween` assertion function for `java.util.Date`, you could write it as follows:
```kotlin
fun <T: Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    isGreaterOrEquals(lowerBoundInclusive).and.isLessThan(upperBoundExclusive)
```
Pretty simply isn't it. 
Notice though, that this function fails fast, which means, the upper bound is not evaluated 
if the assertion about the lower bound already fails. 
You need to use an [assertion group block](#define-single-assertions-or-assertion-groups) 
if you want that both are evaluated:
```kotlin
fun <T: Date> Expect<T>.isBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
    addAssertionsCreatedBy {
        isGreaterOrEquals(lowerBoundInclusive)
        isLessThan(upperBoundExclusive)
    }
``` 
Still simple enough.

<details>
<summary>:interrobang: Why is a type parameter used in the above examples?</summary>

That is right, we used a type parameter `T: Date` and not `Expect<Date>` directly. 
You should always do this unless your type is final (not `open`) and does not have type parameters itself. 
This way the assertion function is also available for subtypes. This is because `Expect` is invariant.
Following an example:
```kotlin
interface A { val foo get() = 1 }
class B: A
val Expect<A>.foo get() = feature(A::foo)

expect(B()).foo // does not compile as foo is only available for `Expect<A>`
```
    
</details>


So lets move on to an example which is a bit more complicated. Assume the following data class `Person`

```kotlin
data class Person(
  val firstName: String, 
  val lastName: String, 
  val age: Int,
  val children: Collection<Person> 
  // ...  and others 
)
```

Say you want to make an assertion about the number of children a person has:
```kotlin
fun Expect<Person>.hasNumberOfChildren(number: Int) = apply {
    feature(Person::children) { hasSize(number) } 
}
```
Three things to notice here: 
1. we make use of a [feature assertion with class reference](#within-assertion-functions)
2. We use `apply` so that subsequent assertions are still made on `Person` and not on `children` 
   (you could also use `addAssertionsCreatedBy` or a block and `return this` instead of `apply`)
 
Its usage is then as follows:
```kotlin
expect(Person("Susanne", "Whitley", listOf()))
  .hasNumberOfChildren(2) 

    // expect: Person(firstName=Susanne, lastName=Whitley, children=[])        (Person <692342133>)
    // ◆ ▶ children: []        (kotlin.collections.EmptyList <654845766>)
    //     ◾ ▶ size: 0        (kotlin.Int <1712536284>)
    //         ◾ to be: 2        (kotlin.Int <2080166188>)    
```
Another example: assert the person has children which are all adults (assuming 18 is the age of majority).
```kotlin
fun Expect<Person>.hasAdultChildren() = apply {
    feature(Person::children) {
      all { property(Person::age).isGreaterOrEquals(18) }
    }
}
```
We also use `apply` here for the same reason as above.
We might be tempted to add an additional size check -- because a Person with 0 children does not have adult children --
but we don't have to, as `all` already checks that there is at least one element. 
```
expect: Person(firstName=Susanne, lastName=Whitley, children=[], age=30)        (Person <1870252780>)
◆ ▶ children: []        (kotlin.collections.EmptyList <761960786>)
    ◾ ▶ has at least one element: false
        ◾ is: true
```

If we keep adding assertion functions involving `children` it might be best to provide a shortcut property and function
(assuming the API of Person is stable enough).
```kotlin
val Expect<Person>.children get(): Expect<Collection<Person>> = feature(Person::children)
fun Expect<Person>.children(assertionCreator: Expect<Collection<Person>>.() -> Unit): Expect<Person> =
    apply { feature(Person::children, assertionCreator) }
```
With this, we can write things like:
```kotlin
expect(person)
    .children { // using the fun -> sub-assertions don't fail fast
        none { feature { f(it::firstName) }.startsWith("Ro") }
        all { feature { f(it::firstName) }.toBe("Stoll") }
    } // subject is still Person here
    .apply {
        children  // using the val -> subsequent assertions are about children and fail fast
            .hasSize(2)
            .any { feature { f(it::age) }.isGreaterThan(18) }
    } // subject is still Person here
    .children // using the val -> subsequent assertions are about children and fail fast
    .hasSize(2)
    .contains(...)
```

<hr/>

Enough of feature assertions. Let's move on to an example where we want to reuse an existing function but with different
arguments. Say we have a function which returns a list of first name / last name `Pair`s. 
We want to assert that the pairs contain only the first name / last name pairs of certain `Person` in any order.
[Collection Assertions](#collection-assertions) will help us with this. 
However, `contains.inAnyOrder.values` expects `Pair`s.
So we have to map from `Person` to `Pair` upfront.
As we have a variable length argument list and want to pass it to a variable length argument list, this cannot be done with a simple `map` from Kotlin. 
And it gets worse if we want to use `contains.inAnyOrder.entries` which expects at least one `assertionCreator`-lambda (`Expect<T>.() -> Unit`)
because Kotlin cannot infer the types automatically.

`mapArguments` to the rescue, you can write the assertion function as follows:
```kotlin
import ch.tutteli.atrium.domain.builders.utils.mapArguments

fun <T: List<Pair<String, String>>> Expect<T>.areNamesOf(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (pair, otherPairs) = mapArguments(person, otherPersons) { it.firstName to it.lastName }
    return contains.inAnyOrder.only.values(pair, *otherPairs)
}
```
As you can see we moved the mapping inside the function so that the consumer of our API can happily use it as follows:
```kotlin
expect(get...WhichReturnsPairs()).areNamesOf(fKafka, eBloch, kTucholsky)
```

Another fictional example, say we want to assert that the pairs have the same initials as the given persons and in the given order.
Which means, this time we need to use `assertionCreator`-lambdas. This can be written as follows:
```kotlin
fun <T : List<Pair<String, String>>> Expect<T>.sameInitialsAs(
    person: Person, vararg otherPersons: Person
): Expect<T> {
    val (first, others) = mapArguments(person, otherPersons).toExpect<Pair<String, String>> {
        first.startsWith(it.firstName[0].toString())
        second.startsWith(it.lastName[0].toString())
    }
    return contains.inOrder.only.entries(first, *others)
}
``` 

There are a few additional methods which you can call after `mapArguments`.
In case you want to provide your own implementation it suffices to create an 
extension function for [ArgumentMapperBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders.utils/-argument-mapper-builder/index.html). 

## Enhanced Reporting

[Composing assertion functions](#compose-assertion-functions) give already quite a bit of power to an assertion function writer.
Yet, sometimes we would like to create functions which have a better error reporting than the one we get 
when we compose assertion functions.

[`ExpectImpl`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders/-expect-impl/index.html) 
is the entry point in this case.
Its a builder and thus lets you find the functions you need via code completion.

Following a quick overview what it provides:
- all assertion functions on the domain level (what you have seen in [Compose-assertion-functions](#compose-assertion-functions) 
was the API level) so that you can reuse and compose them in other ways.
- `ExpectImpl.builder` to create different kinds of assertions (see [AssertionBuilder](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html) for more information).
- `ExpectImpl.changeSubject` which allows to change the subject either:
   - `unreported`; meaning it does not show up in reporting (e.g. `Expect<Array<out T>>.asIterable()` uses it, see [arrayAssertions](https://github.com/robstoll/atrium/tree/master/apis/fluent-en_GB/atrium-api-fluent-en_GB-common/src/main/kotlin/ch/tutteli/atrium/api/cc/en_GB/arrayAssertions.kt#L17))
   - reported, using `reportBuilder`; meaning a subject transformation which is shown in reporting as it incorporates a transformation (e.g. `isA` uses it, see [anyAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/anyAssertions.kt#L62))
- `ExpectImpl.collector` which allows to collect assertions - especially helpful in creating explanatory assertions (see [mapAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/mapAssertions.kt#L41))
- `ExpectImpl.feature.extractor` for feature assertions which are not always save to extract (see [`List.get`](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/listAssertions.kt))   

You can find an example in [floatingPointAssertions](https://github.com/robstoll/atrium/tree/master/domain/robstoll-lib/atrium-domain-robstoll-lib-common/src/main/kotlin/ch/tutteli/atrium/domain/robstoll/lib/creating/floatingPointAssertions.kt#L33)
which makes use of explanatory assertions as well as providing a failure hint.

Unfortunately I do not have the time to cover all cases, so let me know  if you want to know more
-- either by opening an issue or via the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](http://slack.kotlinlang.org/)).

## Own Sophisticated Assertion Builders

Do you want to write an own sophisticated assertion builder (or extend a current with more options) instead of an assertion function?
Great, I do not provide hands on documentation yet (had only one question about it so far). 
Therefore, please have a look at the implementation, for instance how the sophisticated assertion builders for `Iterable<T>` are defined:
[ch.tutteli.atrium.creating.iterable.contains](https://github.com/robstoll/atrium/tree/master/domain/api/atrium-domain-api-common/src/main/kotlin/ch/tutteli/atrium/domain/creating/iterable/contains).
Notice that the implementation supports [Internationalization](#internationalization-1).

I am willing to provide more documentation if you need it (please open an issue). 
In the meantime I might help you via slack, please post your questions in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ)
([Invite yourself](http://slack.kotlinlang.org/) in case you do not have an account yet).


# Use own Assertion Verbs

Atrium offers three assertion verbs out of the box: `expect`, `assert` and `assertThat`. 

But you can also define your own set of assertion verbs if they do not suite you or if you do not want that all of them are available in your classpath.
In order to create an own assertion verb it is sufficient to:
 1. Copy the file content of [atriumVerbs.kt](https://github.com/robstoll/atrium/tree/master/misc/verbs-internal/atrium-verbs-internal-common/src/main/kotlin/ch/tutteli/atrium/api/verbs/internal/atriumVerbs.kt)
 2. Create your own atriumVerbs.kt and paste the previously copied content.
 3. Adjust package name and `import`s and rename `expect` as desired (you can also leave it that way of course).
 4. exclude `atrium-verbs` from your dependencies. 
    Taking the setup shown in the [Installation](#installation) section, you would replace the `dependencies` block as follows:
    ```
    dependencies {
        testCompile("ch.tutteli.atrium:atrium-fluent-en_GB:$atrium_version") {
            exclude group: 'ch.tutteli.atrium', module: 'atrium-verbs'
        }
    }
    ```

You could also choose to have different verbs for the three functions.
For instance, you could use `expect` to postulate assertions about thrown `Throwable`s and `assert` for other assertions.

What are the benefits of creating own assertion verbs:
- you can limit the set of available assertion verbs. <br/>
  Say you want that everyone uses `expect` but not `assert` nor `assertThat`, removing them is surely a better option than using a linter.
- you can encapsulate the reporting style. <br/>
  This is especially useful if you have multiple projects and want to have a consistent reporting style.  
  For instance, you could change from same-line to multi-line reporting or report not only failing but also successful assertions, change the output language etc.
  
    <details>
    <summary>:interrobang: where should I put the atriumVerbs.kt?</summary>
    
    I suggest you create an adapter project for Atrium where you specify the assertion verbs. 
    And most likely you will accumulate them with assertion functions which are so common 
    that they appear in multiple projects -- please share them with us (get in touch with us via issue or slack) if they are not of an internal nature :wink:
    
    <hr/>
    </details>
 
 
What are the drawbacks:
- you have to maintain your assertion verbs. That should not be a big deal 
  -- you might have to replace deprecated options by their replacement when you upgrade to a newer Atrium version but that's about it.


## ReporterBuilder

The `ReporterBuilder` lets you choose among different options to configure the style of the reporting.
For instance, in case you are not happy with the predefined bullet points, then you can change them via the `ReporterBuilder`.
Have a look at [atriumVerbs.kt of atrium-api-fluent-de_CH](https://github.com/robstoll/atrium/tree/master/apis/fluent-de_CH/atrium-api-fluent-de_CH-jvm/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt)
where you can find an example.

Or if you prefer multi-line reporting over single-line reporting,
then you can configure `ReporterBuilder` as follows.
Instead of using `.withTextSameLineAssertionPairFormatter()` you choose `withTextNextLineAssertionPairFormatter()`.
The output looks then as follows:
```kotlin
expect(x).toBe(9)
```
Would then look as follows:
```text
expect: 
  10        (kotlin.Int <934275857>)
◆ to be: 
  9        (kotlin.Int <1364913072>)
```
instead of:
```
expect: 10        (kotlin.Int <934275857>)
◆ to be: 9        (kotlin.Int <1364913072>)
```

You prefer another reporting style but Atrium does not yet support it? 
Please let me know it by [writing a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).


There are more options to choose from. 
It does not matter if you use your [own assertion verb](#use-own-assertion-verbs) or a predefined one.
You can provide your custom configured `Reporter` by providing a `ReporterFactory`.
This is done via [ServiceLoader](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html) -mechanism on JVM 
and by calling `registerService` on JS where the call has to be before your tests run.  
An example for JVM is given in [atriumVerbs.kt of atrium-api-fluent-de_CH](https://github.com/robstoll/atrium/tree/master/apis/fluent-de_CH/atrium-api-fluent-de_CH-jvm/src/test/kotlin/ch/tutteli/atrium/atriumVerbs.kt).
An example of how you can make sure your code is called earlier than the tests run is given in [testSetup.kt of atrium-core-robstoll-lib](https://github.com/robstoll/atrium/tree/master/core/robstoll-lib/atrium-core-robstoll-lib-js/src/test/kotlin/testSetup.kt).

# Internationalization

We distinguish between two use cases. 
You might want to generate the [Report](#report) in a different language or/and you might want to use the [API in a different language](#api-in-a-different-language). 

## Report
Following on the example in [Write own Assertion Functions](#write-own-assertion-functions)
we show here how you need to write the `isMultipleOf` function, so that it supports i18n. 
This way the report could be generated in another language.

The difference lies in the first argument passed to `createAndAddAssertion`; 
we do no longer use a `String` but a proper `Translatable`. 

```kotlin
fun Expect<Int>.isMultipleOf(base: Int) = 
    createAndAddAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }
    
enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}    
```


Typically you would put `DescriptionIntAssertions` into an own module (jar) 
so that it could be replaced (with zero performance cost) by another language representation.
For instance,
[atrium-fluent-en_GB-common](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-fluent-en_GB-common/build.gradle)
uses `atrium-translations-en_GB-common` whereas 
[atrium-fluent-de_CH-common](https://github.com/robstoll/atrium/tree/master/bundles/fluent-de_CH/atrium-fluent-de_CH-common/build.gradle)
uses `atrium-translations-de_CH-common`.  

<details>
<summary>:interrobang: Using a TranslationSupplier</summary>

Next to providing translations via code you can also use a 
[TranslationSupplier](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translation-supplier/index.html)
based [Translator](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html)
by configuring the [`ReporterBuilder`](#reporterbuilder) accordingly (e.g. use `withDefaultTranslationSupplier` instead of `withoutTranslations`). 
Atrium supports a properties files based `TranslationSupplier` for JVM (a supplier for JS will follow) which is more or less what
[ResourceBundle](https://docs.oracle.com/javase/tutorial/i18n/resbundle/propfile.html)
provides out of the box. 
Yet, a `Translator` uses a more enhanced fallback mechanism compared to a `ResourceBundle`. 
For further technical information have a look at the KDoc of [Translator](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.reporting.translating/-translator/index.html).

Notice, Atrium does not yet support the generation of multiple reports in the same test run. 
This might become handy if you want to generate an HTML report in different languages.   
However, Atrium is designed to support this use case -- if you need this feature, then please let me know it by writing a 
[feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).

<hr/>
</details><br/>

Let us rewrite the `isEven` assertion function from the section [Write own Assertion Functions](#write-own-assertion-functions)
as second example:
```kotlin
fun Expect<Int>.isEven() = 
    createAndAddAssertion(DescriptionBasic.IS, RawString.create(DescriptionIntAssertions.EVEN)) { it % 2 == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
```
Once again we have to wrap the text which we want to be able to exchange with another language into a `Translatable`. 
Since we want that the translation as such is treated as a raw string in reporting, we wrap it into a `RawString` as we did before. 
Notice also, that we are reusing a `Translatable` from `DescriptionBasic`.

## API in a different Language

Following on the example in the previous section, 
we want to write `isMultipleOf` in such a way that one cannot only generate a report in a different language
but also that one can use the function itself in a different language. 
Or in other words, provide our API in a different language (the same applies if you want to provide another API style).

We split up the function in two parts: API and implementation 
-- whereas the implementation creates the assertion and the API provides a function for the user (the API as such) and
merely adds the assertion created by the implementation to the `Expect`.
 
Typically you put the API function in one module (jar) and the implementation in another (so that the API can be exchanged).
In the implementation module we define, what we will call hereafter an impl-function.
We follow the convention that impl-functions are prefixed with `_` 
-- this way the chance that it shows up in code completion, e.g. when a developer starts to type `is`, is very low):
```kotlin
fun _isMultipleOf(container: Expect<Int>, base: Int): Assertion 
    = ExpectImpl.builder.createDescriptive(container, DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }
```
Notice that the impl-function is not an extension function as before 
because we do not want to pollute the API of `Expect<Int>` with this function.
In the above example we created a simple [DescriptiveAssertion](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions/-descriptive-assertion/index.html)
(`createAndAddAssertion` does the same under the hood)
with a test which defines whether the assertion holds as well as a description (`IS_MULTIPLE_OF`) and a representation (`base`).

[`ExpectImpl`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.domain.builders/-expect-impl/index.html)
helps you in writing own assertion functions. 
I suggest you use it as entry point (rather than memorizing different class names), 
it guides you to existing assertion function implementations for different types 
as well as to other builders such as the [`AssertionBuilder`](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.assertions.builders/-assertion-builder/index.html)
which in turn helps you with creating assertions.

In the API module we define the extension function and call the impl-function:
```kotlin
fun Expect<Int>.isMultipleOf(base: Int)
    = addAssertion(_isMultipleOf(this, base))
```
We do no longer have to create the assertion as in the example of
[Write own Assertion Functions](#write-own-assertion-functions).
Therefore we use the `addAssertion` method and call the impl-function which will create the assertion for us.

You are ready to go, creating an API in a different language -- e.g. in German -- is now only a routine piece of work:
```kotlin
fun Expect<Int>.istVielfachesVon(base: Int)
    = addAssertion(_isMultipleOf(this, base))
```

<details>
<summary>:interrobang: Atrium has more layers</summary>

If you have a look at existing assertion functions and try to reach the impl-function from the API
then you will see that they use `ExpectImpl` and that a few more indirections were introduced into Atrium. 
An API call looks more or less as follows: <br/>
`API -> ExpectImpl -> ServiceLoader -> Service -> Implementation`

The reasons behind this are simple, you could exchange a `Service` with another service if you want.
A service could also reuse parts of the `Implementation` 
(that is why the `Service` delegates to the `Implementation` rather than implementing it itself).

</details>

# APIs
Atrium supports currently two API styles: pure `fluent` and `infix` 
where `fluent` exists in English and German; `infix` only in English.
All have their design focus on interoperability with code completion functionality of your IDE 
-- so that you can let your IDE do some of the work.

Atrium is 
[built up by different modules](https://docs.atriumlib.org/latest#/doc/) 
and it is your choice which implementation you want to use. 
However, this is more intended for advanced user with special requirements.
Atrium provides three modules which bundle API, translation, domain and core as well as predefined assertion verbs,
so that you just have to have a dependency on one of those bundles (kind a bit like a BOM pom in the maven world):

- [atrium-fluent-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/fluent-en_GB/atrium-cfluent-en_GB-common/build.gradle)
- [atrium-infix-en_GB](https://github.com/robstoll/atrium/tree/master/bundles/infix-en_GB/atrium-infix-en_GB-common/build.gradle)
- [atrium-fluent-de_CH](https://github.com/robstoll/atrium/tree/master/bundles/fluent-de_CH/atrium-fluent-de_CH-common/build.gradle)

Have a look at 
[apis/differences.md](https://github.com/robstoll/atrium/tree/master/apis/differences.md)
for more information and to see how the API styles differ.
 

# Java Interoperability
Atrium provides some helper functions in case you have to deal with Java Code where not all types are non-nullable. 
[Platform types](https://kotlinlang.org/docs/reference/java-interop.html#notation-for-platform-types)
are turned into a non-nullable version per default (if possible). 

Yet, that might not be what you want, especially if you know that certain functions return potentially `null` 
or in other words, the return type of those functions should be treated as nullable in Kotlin. 
Therefore you want to turn the platform type into the nullable version. 

You need to use a cast to do this. But depending on your return type this might be cumbersome especially if you deal with type parameters. 
Thus, Atrium provides the following functions to ease dealing with Java Code at least for some standard cases:
- [`nullable`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L19)
  turns a type into a nullable type.
- [`nullableContainer`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#40)
  turns an `Iterable` into an iterable with nullable element type, likewise it does the same for `Array`.
- [`nullableKeyMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L66)
  turns a `Map` into a map with a nullable key type.
- [`nullableValueMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L79)
  turns a `Map` into a map with a nullable value type.
- [`nullableKeyValueMap`](https://github.com/robstoll/atrium/tree/master/domain/builders/atrium-domain-builders-common/src/main/kotlin/ch/tutteli/atrium/domain/builders/utils/nullable.kt#L92)
  turns a `Map` into a map with a nullable key and nullable value type. 
    
 
# KDoc - Code Documentation
The code documentation is generated with dokka and is hosted on github-pages:
[KDoc of atrium](https://docs.atriumlib.org/)

# Known Limitations
According to the [YAGNI](https://en.wikipedia.org/wiki/You_aren%27t_gonna_need_it) principle this 
library does not yet offer a lot of out-of-the-box assertion functions. 
More functions will follow but only if they are used somewhere by someone. 
So, let me know if you miss something by creating a [feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature]).
Some assertion functions which I miss myself will follow in the next version. 
They are listed in the [Roadmap](#roadmap) below.

Atrium does especially not support (yet):
- specific JSON assertion functions (yet, everything is there as soon as you parse the JSON into a Map/Object)

# FAQ
You find frequently asked questions below.
If your question is not answered below, then please do not hesitate and ask your question in the [atrium Slack channel](https://kotlinlang.slack.com/messages/C887ZKGCQ).
In case you do not have an account for kotlinlang.slack.com yet, then please [Invite yourself](http://slack.kotlinlang.org/). 

## Are there contains/any/none/all assertions for `Sequence`/`Array`?

Atrium does not provide extension function applicable to `Expect<Sequence<E>>` (or `Array`) directly,
because they would basically duplicate the functions available for `Iterable<E>`.  
However, Atrium provides `asIterable` so that you can turn `Expect<Sequence<E>>` 
into `Expect<Iterable<E>>`. An example:
```kotlin
expect(sequenceOf(1, 2, 3)).asIterable().contains(2)
```
Likewise you can turn an `Expect<Array<E>>`, `Expect<DoubleArray>` etc. into an `Expect<Iterable<E>>`.

<details>
<summary>:interrobang: why do I not see anything aboutthe transformation in reporting?</summary>

`asIterable` uses `ExpectImpl.changeSubject.unreported` internally which is intended for not showing up in reporting.
If you would like that the transformation is reflected in reporting then you can use a regular feature assertion 
as follows:
```
expect(sequenceOf(1, 2, 3)).feature(Sequence::asIterable).contains(2)
```

</details>

## Where do I find a list of all available functions?

Atrium provides KDoc for all APIs - have a look at their KDoc:
- [atrium-api-fluent-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.fluent.en_-g-b/index.html)

Deprecated APIS:
- [atrium-api-cc-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/index.html)
- [atrium-api-cc-de_CH](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.de_-d-e/index.html)
- [atrium-api-cc-infix-en_GB](https://docs.atriumlib.org/latest#/doc/ch.tutteli.atrium.api.cc.infix.en_-g-b/index.html)

## Problems in conjunction with `feature`

See [Ambiguity Problems](#ambiguity-problems) and [Property does not exist](#property-does-not-exist).

# Kotlin Bugs
The following issues hinder Atrium to progress in certain areas or they are the reason that we cannot use Atrium as intended in all cases. 
Please upvote them (especially if you encounter them yourself):
- [Lower bounds](https://youtrack.jetbrains.com/issue/KT-209), i.a. that functions intended for nullable subject do not show up on non-nullable subjects.
- [CTRL+P shows extension functions of unrelated type](https://youtrack.jetbrains.com/issue/KT-29133)
- [Expose @OnlyInputTypes to restrict e.g. toBe](https://youtrack.jetbrains.com/issue/KT-13198)
- [Type inference KFunction overload bug 1](https://youtrack.jetbrains.com/issue/KT-17340)
- [Type inference KFunction overload bug 2](https://youtrack.jetbrains.com/issue/KT-19884)
- [Type inference KProperty/KFunction ambiguity bug](https://youtrack.jetbrains.com/issue/KT-17341)
- [Type inference fails to infer T of KFunction0 for most types](https://youtrack.jetbrains.com/issue/KT-29515)
- [Type inference type parameter bug](https://youtrack.jetbrains.com/issue/KT-12963)
- [Type inference return type bug](https://youtrack.jetbrains.com/issue/KT-24918)
- [Type inference out type parameter bug](https://youtrack.jetbrains.com/issue/KT-18401)
- [Type inference explicit type and overloads](https://youtrack.jetbrains.com/issue/KT-23791)
- [Type inference Pair with receiver type](https://youtrack.jetbrains.com/issue/KT-29129)
- [Type inference unable to infer primitive type](https://youtrack.jetbrains.com/issue/KT-33290)
- [Overload resolution null bug](https://youtrack.jetbrains.com/issue/KT-6591) (reason why you need to specify what type `null` is in the infix API when using `assert(listOf(...)) contains null`)
- [Extension resolution null as receiver bug](https://youtrack.jetbrains.com/issue/KT-30496) (reason why you need to define that `null to null` is a Pair in the infix API)
- [Overload resolution nullable bug](https://youtrack.jetbrains.com/issue/KT-23768)
- [Overload resolution primitive type bug](https://youtrack.jetbrains.com/issue/KT-24230)
- [Overload resolution function type bug](https://youtrack.jetbrains.com/issue/KT-23883)
- [Overload resolution generic upper bound bug](https://youtrack.jetbrains.com/issue/KT-30235)
- [Overload ambiguity between val and fun](https://youtrack.jetbrains.com/issue/KT-32958)
- [false positive: remove explicit type arguments](https://youtrack.jetbrains.com/issue/KT-32869)
- [navigate to source or show KDoc for overloaded extension function](https://youtrack.jetbrains.com/issue/KT-24836)
- [Wrong JS generated in case of name clash](https://youtrack.jetbrains.com/issue/KT-33294)
- [deprecated functions are no longer struck out in 2019.2.x](https://youtrack.jetbrains.com/issue/IDEA-216982)
- [forbid function types as substitute of reified types ](https://youtrack.jetbrains.com/issue/KT-27846)
- [forbid parameterised types as substitute of reified types](https://youtrack.jetbrains.com/issue/KT-27826)

And some features which would be handy
- [hide function with deprecation level error in code completion](https://youtrack.jetbrains.com/issue/KT-25263)
- [Method reference without `this`](https://youtrack.jetbrains.com/issue/KT-22920)
- [Infix function call with type parameters](https://youtrack.jetbrains.com/issue/KT-21593)
- [Extensibility for infix API](https://youtrack.jetbrains.com/issue/KT-27659)
- [Summarising overloads in code completion](https://youtrack.jetbrains.com/issue/KT-25079) 
- [vararg for lambdas](https://youtrack.jetbrains.com/issue/KT-24287)
- [delegate with inline modifier](https://youtrack.jetbrains.com/issue/KT-23241)


# Roadmap

I plan that Atrium is going to support certain features in the future. Following a rough plan (no guarantees).

## 0.9.0
- introduce `Expect<T>` with an invariant `T` (see [#56](https://github.com/robstoll/atrium/issues/56), the current solution with `Assert<out T>` will be deprecated and removed with 1.0.0) 
- introduce `feature` instead of `property` and `returnValueOf` (see [#40](https://github.com/robstoll/atrium/issues/40))
- optionally, introduce jdk8 specific assertion functions, e.g. for `Optional` or `Path`

## 0.10.0
- fix verbosity issues in conjunction with feature assertions and explanatory assertion groups.
- provide an easy way to create failure hints

## 0.11.0
- Json assertions (state your wishes in [#45](https://github.com/robstoll/atrium/issues/45))
  
## 0.12.0  
- see if we can further improve error reporting in the IDE with the help of opentest4j exceptions.
- Generating testing reports in html.
  - generate multiple reports in the same test run. 
  
## 0.13.0 (or 1.0.0)  
- extension for Spek so that reporting includes the `describe`, `it` etc.
- Inclusion of mockk's verify (so that it appears in the report as well).
    
Are you missing something else? 
[Feature Requests](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
are very welcome.

# Contributors and contribute

Our thanks go to [code contributors](https://github.com/robstoll/atrium/graphs/contributors) 
as well as other contributors (see acknowledgements in the [release notes](https://github.com/robstoll/atrium/releases)).

You are more than welcome to contribute as well:
- star Atrium if you like it
- [open a bug](https://github.com/robstoll/atrium/issues/new?template=bug_report.md) or [create a feature request](https://github.com/robstoll/atrium/issues/new?template=feature_request.md&title=[Feature])
- share your ideas via [issue](https://github.com/robstoll/atrium/issues/new) or [slack](https://kotlinlang.slack.com/messages/C887ZKGCQ)
- [ask a question](https://kotlinlang.slack.com/messages/C887ZKGCQ)
  so that I better understand where Atrium needs to improve.
- write a blog post about Atrium (e.g. about a feature you like) or a tutorial (let us know we happily link to your page)
- share your assertion functions with the rest of us by creating a pull request (no need for i18n support or the like, we can augment your pull request).
- have a look at the [help wanted issues](https://github.com/robstoll/atrium/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)
  if you would like to code (ping me on [Slack](https://kotlinlang.slack.com/messages/C887ZKGCQ) if there are not any).  

Please have a look at 
[CONTRIBUTING.md](https://github.com/robstoll/atrium/tree/master/.github/CONTRIBUTING.md)
for further suggestions and guidelines.

# License
Atrium is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Atrium is using:
- [KBox](https://github.com/robstoll/kbox) licensed under [Apache 2.0](http://opensource.org/licenses/Apache2.0)
- [Niok](https://github.com/robstoll/niok) licensed under [Apache 2.0](http://opensource.org/licenses/Apache2.0)
