# coteafs-error

## What is it all about?

This framework helps in handling exceptions gracefully. You can re-throw an exception using message saved in YAML file
using corresponding Error code from the file. You can also handle an exception to filter out unwanted stack trace by
filtering desired package.

## Usage

In your `pom.xml`, add following block to use this POM,

```xml
<dependency>
    <groupId>com.github.wasiqb.coteafs</groupId>
    <artifactId>error</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Error file

An Error file containing Error codes and messages should be available in your `src/test/resources` folder with name 
`error-codes.yaml`. Following is a sample file content,

```yaml
# src/test/resources/error-codes.yaml
errors:
  404: "File {0} not found!"
  ge: "Generic Error."
```

## Examples

### `fail` method

Use `ErrorHandler.fail` method to wrap and rethrow the Exception. See example below.

```java
import static com.github.wasiqb.coteafs.error.ErrorHandler.fail;

. . .
// Throw new Custom Error with message.
fail (FileNotFoundException.class, "404", "file.yaml");
. . .

// Throw new Custom Error with message.
try {
    ..some code..
} catch (FileNotFoundException e) {
    fail (GenericError.class, e, "ge");
}
. . .
```

### `handleError` method

This method helps in collecting and filtering stacktrace for a particular package which can be helpful in logging of
errors.

```java
. . .
try {
    [code block]
} catch (final Exception e) {
    // Filter exception to show only exception occurred in only the given package.
    handleError ("com.github.wasiqb", e).forEach (System.err::println);

    // OR

    // This will show complete exception stack occurred.
    handleError (e).forEach (System.err::println);
}
. . .
```

Will output formatted exception with filtered stacktrace by package name like this,

```shell script
Error occurred: (com.github.wasiqb.coteafs.error.CoteafsError)
Message: Test Error!
  at com.github.wasiqb.coteafs.error.ErrorHandler: fail (147)
  at com.github.wasiqb.coteafs.error.ErrorHandler: fail (95)
  at com.github.wasiqb.coteafs.error.util.ErrorHandlerTest: testCoteafsErrorWithFiveArg (48)
Caused by: (class java.io.FileNotFoundException)
Message: null
  at com.github.wasiqb.coteafs.error.ErrorHandler: fail (147)
  at com.github.wasiqb.coteafs.error.ErrorHandler: fail (95)
  at com.github.wasiqb.coteafs.error.util.ErrorHandlerTest: testCoteafsErrorWithFiveArg (48)
```
