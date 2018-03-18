# Mower

This program implements the well known Xebia mower specification.

## Build

```
sbt assembly
```

It produces `target/scala-2.12/Mower-assembly-0.1.jar`

## Run

Do not forget to build first!

```
./mow.sh lawn.mow
```
or, if you want to take the program from `stdin`
```
./mow.sh < lawn.mow
```

## Command Line Interface
```
  -h, --help   Show help message

 trailing arguments:
  program (not required)   Mower program file (when omitted, the program is
                           taken from stdin)
```

## Roadmap
Improve the parser so that it produces nice error messages when the input program is malformed.