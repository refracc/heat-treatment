# Heat Treatment Optimisation Project
The Heat Treatment Optimisation Project contains a series of datasets which are currently being examined by [Dr Stathis Tingas](https://www.napier.ac.uk/people/stathis-tingas) at [Edinburgh Napier University](https://www.napier.ac.uk/) which explores what happens when certain processes are applied to materials. The project itself consists of five datasets ([Plastic Anisotropy](https://github.com/refracc/heat-treatment/blob/master/data/plastic-anisotropy.csv), [Tensile Strength](https://github.com/refracc/heat-treatment/blob/master/data/tensile-strength.csv), [Total Elongation](https://github.com/refracc/heat-treatment/blob/master/data/total-elongation.csv), [Vickers Hardness](https://github.com/refracc/heat-treatment/blob/master/data/vickers-hardness.csv) and [Yield Strength](https://github.com/refracc/heat-treatment/blob/master/data/yield-strength.csv)) where the data contained within them is represented in three dimensions. The first is [time](https://github.com/refracc/heat-treatment/blob/master/data/times.txt), the second is [temperature](https://github.com/refracc/heat-treatment/blob/master/data/temperatures.txt), and the third is a value that has been normalised between 1 and 0, detailed within the dataset.

The objective function is the euclidean distance of the time (t) and temperature (T), which is given as below.

![\Large E(t,T)=\sqrt{(T_2-T_1)^2+(t_2-t_1)^2}](https://latex.codecogs.com/svg.latex?\Large&space;E(t,T)=\sqrt{(T_2-T_1)^2+(t_2-t_1)^2})

## Installation
The [evolutionary algorithm](https://github.com/refracc/heat-treatment/tree/master/src/main/java/me/sanderson/ea) was developed in [Java 19](https://www.oracle.com/java/technologies/downloads/#java19) and the project can be compiled by using [Maven](https://maven.apache.org/) to have the code deployed on to a computer of higher specification. For ease, it is recommended that the project is opened in an Integrated Development Environment (IDE) such as [IntelliJ Idea](https://www.jetbrains.com/idea/download/) provided by JetBrains.

## Usage
Most notably, the project contains a rather handy `Parameters` file, which controls how the code will execute upon compilation. This is detailed below in the code block.
```java

```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)