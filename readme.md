# Heat Treatment Optimisation Project
The Heat Treatment Optimisation Project contains a series of datasets which are currently being examined by [Dr Stathis Tingas](https://www.napier.ac.uk/people/stathis-tingas) at [Edinburgh Napier University](https://www.napier.ac.uk/) which explores what happens when certain processes are applied to materials. The project itself consists of five datasets ([Plastic Anisotropy](https://github.com/refracc/heat-treatment/blob/master/data/plastic-anisotropy.csv), [Tensile Strength](https://github.com/refracc/heat-treatment/blob/master/data/tensile-strength.csv), [Total Elongation](https://github.com/refracc/heat-treatment/blob/master/data/total-elongation.csv), [Vickers Hardness](https://github.com/refracc/heat-treatment/blob/master/data/vickers-hardness.csv) and [Yield Strength](https://github.com/refracc/heat-treatment/blob/master/data/yield-strength.csv)) where the data contained within them is represented in three dimensions. The first is [time](https://github.com/refracc/heat-treatment/blob/master/data/times.txt), the second is [temperature](https://github.com/refracc/heat-treatment/blob/master/data/temperatures.txt), and the third is a value that has been normalised between 1 and 0, detailed within the dataset.

The objective function is the euclidean distance of the time (t) and temperature (T), which is given as below.

![(https://latex.codecogs.com/svg.image?E(t,&space;T)=\sqrt{\sum_{i=0}^{n_{\textrm{space}}}\left(t_i-T_i\right)^2})](https://latex.codecogs.com/svg.image?E(t,&space;T)=\sqrt{\sum_{i=0}^{n_{\textrm{space}}}\left(t_i-T_i\right)^2})


## Installation
The [evolutionary algorithm](https://github.com/refracc/heat-treatment/tree/master/src/main/java/me/sanderson/ea) was developed in [Java 19](https://www.oracle.com/java/technologies/downloads/#java19) and the project can be compiled by using [Maven](https://maven.apache.org/) to have the code deployed on to a computer of higher specification. For ease, it is recommended that the project is opened in an Integrated Development Environment (IDE) such as [IntelliJ Idea](https://www.jetbrains.com/idea/download/) provided by JetBrains.

## Usage
Most notably, the project contains a rather handy `Parameters` file, which controls how the code will execute upon compilation. There is an abridged version of this file below in the following code block.
```java
/* Control how the evolutionary algorithm initialises the population.
 * Possible values:
 *  - AUGMENTED
 *  - RANDOM
 */
public static Initialisation INITIALISATION = Initialisation.AUGMENTED;

/* Control how the evolutionary algorithm controls the selection process.
 * Possible values:
 *  - RANDOM
 *  - ROULETTE
 *  - ROUTE_RANK
 *  - TOURNAMENT
 */
public static Selection SELECTION = Selection.ROULETTE;

/* Control how the evolutionary algorithm controls the crossover process.
 * Possible values:
 *  - ARITHMETIC
 *  - ONE_POINT
 *  - TWO_POINT
 *  - UNIFORM
 */
public static Crossover CROSSOVER = Crossover.UNIFORM;

/* Control how the evolutionary algorithm controls the mutation process.
 * Possible values:
 *  - CONSTRAINED
 *  - STANDARD
 */
public static Mutation MUTATION = Mutation.CONSTRAINED;

/* Control how the evolutionary algorithm controls the replacement process.
 * Possible values:
 *  - TOURNAMENT
 *  - WORST
 */
public static Replacement REPLACEMENT = Replacement.WORST;

/* Control how the evolutionary algorithm evaluates the chromosomes.
 * Possible values:
 *  - MINIMISATION
 *  - MAXIMISATION
 */
public static ProblemType PROBLEM = ProblemType.MINIMISATION;

// The tournament size for the selection and replacement process.
public static int TOURNAMENT_SIZE = 10;

// The file that is to be read in by the evolutionary algorithm.
public static String FILE_NAME = "plastic-anisotropy.csv";

// The default population size.
public static int POPULATION_SIZE = 300;

// The augmented population size when using augmented initialisation.
public static int AUGMENTED_POPULATION_SIZE = 15750;

// The maximum number of evaluations/generations.
public static int MAXIMUM_EVALUATIONS = 20000;


// Parameters for mutation
// Rate = probability of changing a gene
// Change = the maximum +/- adjustment to the gene value
public static double MUTATION_RATE = 0.90; // mutation rate for mutation operator
public static double MUTATION_CHANGE = 1.50; // delta change for mutation operator
```

## Contributing
Pull requests are welcome! All we ask is, credit goes to the authors and contributors of this project:\
[Dr Stathis Tingas](https://www.napier.ac.uk/people/stathis-tingas): [e.tingas@napier.ac.uk](mailto:e.tingas@napier.ac.uk), Lecturer of Mathematics\
[Dr Neil Urquhart](https://www.napier.ac.uk/people/neil-urquhart): [n.urquhart@napier.ac.uk](mailto:n.urquhart@napier.ac.uk), Lecturer of Computer Science\
[Stewart Anderson](https://www.abdn.ac.uk/people/stewart.anderson): [stewart.anderson@abdn.ac.uk](mailto:stewart.anderson@abdn.ac.uk), PhD Candidate\
[Robert Galloway](https://www.github.com/Veonms): [r.galloway@napier.ac.uk](mailto:r.galloway@napier.ac.uk), Research Assistant
