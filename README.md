# Photon

Photon is a simple mathematical expression evaluator that supports variables. It is both an executable, and a library.

## Executable

It is an interactive CLI program. It uses variables from `context.json`, and emits information into `evaluator.json`.

## Library

```java
class Example {
    public static void main(String[] args) {
        var context = new EvaluationContext();
        context.setArgument(x, 5);
        var result = Photon.createEvaluator("x^2+1", context).evaluate();
    }
}
```
