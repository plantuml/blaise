# Blaise
Work on potantiel next preprocessor evolution for PlantUML

Blaise must be compatible with actual PlantUML preprocessor.
It should provide new capabilities.

# Data type

The following basic type are supported:

- boolean
- integer (with no limit, like in Python)
- [double precision floating point](https://en.wikipedia.org/wiki/Double-precision_floating-point_format)
- String (with full unicode support)
- [JSON Data](https://en.wikipedia.org/wiki/JSON)
- [Tuples](https://www.w3schools.com/python/python_tuples.asp) using `<` and `>` as delimiter
- [Zero based indexing array](https://en.wikipedia.org/wiki/Array_(data_structure))
- [Dictionnary](https://en.wikipedia.org/wiki/Associative_array)

## Boolean

A boolean can by either `true` or `false`.
```
!$my_var = true
```

## Integer

You can use `_` as separator for long number.

```
!$my_var = 3_000_000_000_000_000
```

## Float

```
!$my_var = 3.14159
```


## String

String separator could be simple quote or double quote.

```
!$my_var1 = 'with simple quote'
!$my_var2 = """
This is a very
long string on several
lines"""
```


## JSON

```
!$my_var = {
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 27,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021-3100"
  },
  "phoneNumbers": [
    {
      "type": "home",
      "number": "212 555-1234"
    },
    {
      "type": "office",
      "number": "646 555-4567"
    }
  ],
  "children": [
    "Catherine",
    "Thomas",
    "Trevor"
  ],
  "spouse": null
}
```

## Tuple

A tuple is [immutable](https://en.wikipedia.org/wiki/Immutable_object).

```
!$my_var = <42, "Some String", {
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 27 } >
```

## Array

Elements of the array don't have to be the same type.
Arrays are mutable.

```
!$my_array = ["Some string", "another string", 42]
```



## Dictionnary

In dictionnary, keys could be either String or Integer.

```
!$my_dict = ["key1": 3, "key2": 40, "key3": "foo"]
```

