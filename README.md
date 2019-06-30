# coteafs-error

## What is it all about?

This framework helps in wrapping Exceptions and re-throwing custom Error wrapped on top of `AssertionError` class. It can be used to wrap Excpetions and further catogorise the Error into Reasons, category and severity.

## Why created this project?

This project was created to solve the problem where we encounter exceptions from the library which we are using which are not descriptive enough to understand by just looking at it in the logs, however, we know while debugging what the exception is all about.

Now we can wrap those exceptions in coteafs-error and re-throw it with some descriptive message and descriptive Error name. This will help reduce the pain of debugging and help understand what went wrong by just looking in the logs.

## Examples

Use `ErrorUtil.fail` method to wrap and rethrow the Exception. See example below.

```java
import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;

. . .

// Throw new Custom Error with message.
fail (NotImplementedError.class, "Some Error message!");

. . .

// Throw new Custom Error with message and categorize it.
try {
  . . some code . .
}
catch (FileNotFoundException e) {
  fail (CoteafsError.class, "Test Error!", Reason.R2, Category.C1, Severity.CRITICAL);
}

. . .

// Throw wrapped Exception into a Custom Error with message.
try {
  . . some code . .
}
catch (FileNotFoundException e) {
  fail (OperationNotSupportedError.class, "Some Error message!", e);
}

. . .

// Categorise the wrapped Error.
try {
  . . some code . .
}
catch (FileNotFoundException e) {
  fail (CoteafsError.class, "Test Error!", e, Reason.R2, Category.C1, Severity.CRITICAL);
}

. . .
```

## Usage

In your `pom.xml`, add following block to use this POM,

```xml
<dependency>
  <groupId>com.github.wasiqb.coteafs</groupId>
  <artifactId>error</artifactId>
  <version>1.7.0</version>
</dependency>
```
