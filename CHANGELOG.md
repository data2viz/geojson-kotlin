# Change log for geojson-kotlin

## 0.6.5

> Published 18 Feb 2023
* Kotlin 1.8.10
* Fix a publication issue that prevented the proper resolution of Kotlin/Native artifacts because of a case mismatch.

## 0.6.5

> Published 17 Feb 2023
* Kotlin 1.8.0
* Remove unneeded `Typed` class introduced in version 0.6.4 that broke iOS consumers with the following error: `e: Compilation failed: external function Typed.<get-type> must have @TypedIntrinsic, @SymbolName, @GCUnsafeCall or @ObjCMethod annotation`.

## 0.6.2
> Published 01 Dec 2020
* Kotlin 1.4.20
* publish both IR and Legacy version for JS platform.


## 0.6.1-RC3

> Published 27 Jan 2020
* release on npm, new project structure and artifact name.


## 0.6.1-RC2

> Published 23 Jan 2020
* rename `intProp`, `booleanProp`, `stringProp` to
`intProperty`, `booleanProperty`, `stringProperty`

## 0.6.1-RC1

> Published 22 Jan 2020
* extract Feature properties (#5)
* the project uses now the new MPP structure
