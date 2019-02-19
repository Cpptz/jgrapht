# Report for assignment 3

<!--This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.-->

## Project

Name: JGraphT

URL: https://jgrapht.org/

Source : https://github.com/jgrapht/jgrapht

JGraphT is a library to work with graphs which implements lots of graph algorithms.

## Onboarding experience

<!--Did it build as documented?
    
(See the assignment for details; if everything works out of the box,
there is no need to write much here.)-->

As this is a maven project, installing dependencies was easy.

The [documentation for the developers](https://github.com/jgrapht/jgrapht/wiki#developer-pages) is really well written.

The build and the tests concluded without errors on our systems.


## Complexity

1. What are your results for the ten most complex functions? (If ranking
is not easily possible: ten complex functions)?
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?





### 1.
<center>

|  Method | CCN | 
|---|---|
| [isEulerian](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 9 if +6 for + 1 OR -1 return point + 2 = 17|
| [verify](./jgrapht-core/src/main/java/org/jgrapht/graph/GraphWalk.java)  | 18 if + 2 for + 1 while + 3 AND + 2 OR -1 return point +2 = 27 |
| [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java) | 16 if + 3 AND -1  +2 =20 |
| [containsJewel](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)|9 for + 10 if + 19 OR + 1 && -1 + 2 = 40|
| [containsCleanShortestOddHole](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)|3 for + 7 if + 8 OR -1 + 2 = 19|
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java) | 6 if + 5 for + 3 AND   -1  +2 =15 |
| [hasConfigurationType2](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 11 if + 7 for + 13 or + 2 AND = 33 -1 + 2 = 34 |
| [hasConfigurationType3](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 10 if + 9 for + 29 or + 4 and -1 +2  = 53 |
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/PlantedPartitionGraphGenerator.java)|14 if + 7 for + 1 OR - 1 + 2 = 23|
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/KleinbergSmallWorldGraphGenerator.java)|6 if + 11 for + 4 OR + 2 AND -1 + 2 =  24|

</center>

We have the same results when we calculated CCN by hand.

### 2.
As this a graph library with many algorithms, the functions can be both long and really complex.

### 3.
#### [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java)
This function returns true if the graph contains an eulerian cycle.
That is finding if it is possible to construct a cycle, i.e. a path starting and ending on the same vertex, which
visits each vertex exactly once. It also means, the graph should only have one connected componenent with edges.

First, it checked that the graph contained vertices and node.

Then if it is a undirected graph, it checks that all vertices have an even degree
and that there is only connected component with edges.

Otherwise if it is a directed graph, it checks for all vertices that their in degree is equal to their out degree
and that there is only connected component with edges.

#### [verify()](./jgraph-core/src/main/java/org/jgrapht/graph/GraphWalk.java)
This function checks that the path of the ``GraphWalk`` is feasible w.r.t. the graph of the ``GraphWalk``.

The path is described as a list of vertices, a list of edges or both, with optionnaly a start and an end vertex.

First it checks that either the path or both the list of vertices and the list of edges

Then depending on the way the path is described, it will check that the vertices or edges are contained in the graph,
that each edge or vertex follow each other...

#### [buildGraph()](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java)
This function builds a graph dependent on the boolean variables allowingSelfLoops, allowingMultipleEdges, weighted and

directed. The function will return one of 16 possible graph structures that matches the correct boolean variables.

The function handles this by nesting if statements.

#### [getTour()](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)
This function computes a minimal-cost Hamiltonian tour, that is, the shortest way to visit every vertex exactly once.

The function will return a minimal cost tour if it can be found, it will return null otherwise.

The function starts by mapping all edges to integers and calculating the minimum weight between all vertices.

It then reconstructs the tour and finds the minimal cost hamiltonian tour by looping through and evaluating all the edges.

#### [generateGraph()](./jgrapht-core/src/main/java/org/jgrapht/generate/PlantedPartitionGraphGenerator.java)
This function generates a planted partition graph from a PlantedPartitionGraphGenerator object.
The planted partition model is the special case that the values of the probability matrix P are a constant p on the
diagonal and another constant q off the diagonal.

This method can only be called once and that's why at the start of the function it checks if it has been run before on
the generator object.

The function then adds vertices from the number of nodes in the generator object.

Then it checks if the graph should have self loops and adds those.

The function then checks if the graph should have directed or undirected edges and adds those edges to the graph.

#### [generateGraph()](./jgrapht-core/src/main/java/org/jgrapht/generate/KleinbergSmallWorldGraphGenerator.java)
A small-world graph is a graph where in which most nodes are not neighbors of one another, but the neighbors of any
given node are likely to be neighbors of each other and most nodes can be reached from every other node by a
small number of hops or steps.

The function takes a target graph which is a kleinbergsmallworldgrapgenerator object and calculates a new graph which
is constrained by the small-world graph constraints.

The function checks if the edges are directed or undirected, creates vertices and adds edges in the near neighbourhood

Then the function adds the long range neighbours edges by using the inverse r power distribution

#### [containsCleanShortestOddHole()](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)
This is a boolean function which takes a graph with two generics as input and returns true if the graph contains a clean shortest odd hole, and returns false if it does not. The time complexity is O(|V(g)|^4).

The input graph is supposed to neither contain a pyramid nor a jewel. The function tests all vertex permutations of 3, by first finding the shortest path between the three vertices, then adding all the vertices from the three shortests paths to a new set, then creating a subgraph and finally making sure a lot of specific conditions are not true. The conditions are mainly about the sizes of the graph vertex and edge sets. If any of the specific conditions are true or the shortests paths are null the function returns false.

#### [containsJewel()](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)
This is a boolean function which takes a graph with two generics as input and returns true if the function contains a jewel, and false if it does not. The time complexity is O(|V(g)|^6). 

The function tests all vertex permutations of 4 and adds the fourth to a set if it is not connected to any of the first 3 vertices. Then all components of the set are found. After that two new sets are extracted depending on different conditions; such as which share an edge and which do not. Finally each vertex from the first set is compared which each vertex from the second set and in turn compared with each from the components set. If the two first vertices share neighbour the components set the graph contains a jewel.

#### [hasConfigurationType2()](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)
The function returns true if the graph g is of configuration type T2 which is a sequense v1,...,v4 (vertices) with some conditions.

First it enters for loops which iterates over the vertices in the graph. in each loop, there is a if condition that needs to be fullfilled, or the loop will go to the next iteration.

If the if conditions in the for loops are fulfilled. It will check the paths (edges) between the vertices to see if the configuration of the graph is of given specifications. If it is the method will return true, if not it will return false.

#### [hasConfigurationType3()](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)|
The function returns true if the graph g is of configuration type T3 which is a sequense v1,...,v6 (vertices) with some conditions.

It works similarly to the hasConfigurationType2 so that there are for loops which iterates over the vertices and in those forloops there are if condtiions that needs to be fullfilled or the loop will go to the next iteration. If all conditions are fulfilled, it will enter the last for loop, check the last condition and then start checking if the configuration is of type T3 which you can see conditions for in the documentation. If the configuration of the graph matches the specifications for T3, the method will return true and if the configuration does not match T3 it will return false.

### 4.

The complexity does not change due to adding exceptions. When calculating complexity, the function evaluates the

exception essentially as a return statement. With regards to the complexity, return statements increases complexity

by one, which is why we add +1 when calculating it by hand. The same holds true for exceptions, a function that does

nothing but throw an exception will have complexity 1.

When catching an exception using try{} catch{} will increase complexity by one due to the try statement as it holds

similar functionality as an if-statement.

### 5.

The code is documented with JavaDoc so we found that each function is pretty clear w.r.t. all the possible outcomes.

## Coverage

We did our coverage experiments in ``cov_<Branch number>`` branches and the new tests in ``iss_<Branch number>`` and
finally we merged every ``iss_<Branch number>`` in the ``all_new_test`` branch.

### Tools

<!--Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?-->

To check the test coverage for the project we have used the ``cobertura`` maven plugin.
The documentation is good and it easy to use.

One could see the lines added in the ``pom.xml`` file using the following diff command:
```bash
git diff master..code_coverage pom.xml
```


But we have noticed that for some classes, the coverage is not reported correctly.
### DYI

<!-- Show a patch that show the instrumented code in main (or the unit
test setup), and the ten methods where branch coverage is measured.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output? -->



These are the 10 functions we have tested
<center>

|  Method | Branch number |
|---|---|
| [isEulerian](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2  |
| [verify](./jgrapht-core/src/main/java/org/jgrapht/graph/GraphWalk.java)  | 3  |
| [getPaths](./jgrapht-core/src/main/java/org/jgrapht/alg/shortestpath/BellmanFordShortestPath.java)|15|
| [simpleCycleToGraphPath](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/Cycles.java)|13|
| [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java)|12|
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)|14|
| [hasConfigurationType2](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 4 |
| [hasConfigurationType3](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 5 |
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/PlantedPartitionGraphGenerator.java)| 6 |
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/KleinbergSmallWorldGraphGenerator.java)| 7 |

</center>

To test the branch coverage we put a boolean array as a static attribute in the class under test and add this code in
the JUnit test file corresponding to the class under test.
```java
@AfterClass
public static void tearDownAll() {
   int count = 0;
   for (int i = 0; i < HierholzerEulerianCycle.branchCovered.length; i++) {
       if (HierholzerEulerianCycle.branchCovered[i] == true) {
           count++;
       }
   }
   System.out.println(count);
   System.out.println(HierholzerEulerianCycle.branchCovered.length);
}
```

Each code for branch coverage, can be retrieved using the following command:
``` bash
git diff master..cov_<Branch number> jgrapht-core/src/main/
```

For instance , for ``isEulerian()`` method, this would be:
``` bash
git diff master..cov_2 jgrapht-core/src/main/
```

### Evaluation

<!-- Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ... 
-->

<center>


|  Method | Branch number | Old coverage   |Number of new test cases | New coverage |
|---|---|---|---|---|
| [isEulerian](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2 | 21/22  | 1 | 22/22 |
| [verify](./jgrapht-core/src/main/java/org/jgrapht/graph/GraphWalk.java)   | 3 | 20/34 | 9 | 31/34 |
| [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java) | 12 | 8/27 | 2 | 12/27 |
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)| 14 | 17/18 | 1 | 18/18 |
| [hasConfigurationType2](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 4 | 24/29 | 1 | 26/29 |
| [hasConfigurationType3](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)| 5 | 24/29 | 1 | 26/29 |
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/PlantedPartitionGraphGenerator.java)| 6 | 31/35 | 1 | 34/35 |
| [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/KleinbergSmallWorldGraphGenerator.java)| 7 | 19/21 | 2 | 21/21 |
|[getPaths](./jgrapht-core/src/main/java/org/jgrapht/alg/shortestpath/BellmanFordShortestPath.java)| 15 | 9/10 | 1 | 10/10 |
|[simpleCycleToGraphPath](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/Cycles.java)| 13 | 12/17 | 1 | 13/17 |
<center>


To find the new test added, one can run the following diff command
```bash
git diff master..iss_<Branch number> jgrapht-core/src/test/
```

To see the difference between before and after, one could look at the differences in the the cobertura report
on branch ``code_coverage`` (BEFORE) and branch ``all_new_test``.
One can run cobertura with :
```bash
mvn cobertura:cobertura
```

## Refactoring

<!-- Plan for refactoring complex code:

Carried out refactoring (optional) -->

#### [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java)
The way to check that there is only connected component with edges is redundant between the two main branches.
So we can put that code in a new private function. This way, the CCN of the function is reduced from 17 to 8.
Patch can be viewed using the following diff command
```bash
git diff master..refrac_2
```
#### [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java)
The function uses nesting if-else statements that could be refactored. The function uses the same if-else statements
for the graph being directed and undirected. We can branch by extraction by removing half of the if clauses by checking
weighted() in a help function.

#### [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/PlantedPartitionGraphGenerator.java)
This function uses duplicated code for most of directed and undirected graphs. This could be abstracted to another function.

#### [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)
We can refract this function by extracting the code that is maps each vertex to an integer as well as assigning values
 to a matrix which contains the minimum weight of edges in to a separate function.

#### [generateGraph](./jgrapht-core/src/main/java/org/jgrapht/generate/GnmRandomGraphGenerator.java)
This function has amongst other things an if-statement which will never be true. This could either be changed to an enabled assertion of just be removed.

#### [hasConfigurationType3](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/BergeGraphInspector.java)
As a start you could make a function that filter out the interesting vertices instead of using 3 nestled for loops. Then there's more filtering that could be done by a separate function. This would make the function a lot less complicated.
## Effort spent

<!-- For each team member, how much time was spent in-->


* Viktor
    1. plenary discussions/meetings: 5h

    2. discussions within parts of the group: 5h

    3. reading documentation: 3h

    4. configuration: 3h

    5. analyzing code/output: 5h

    6. writing documentation: 2h

    7. writing code: 3h

    8. running code: 0.5h
    
* Cyril 
    1. plenary discussions/meetings : 5h

    2. discussions within parts of the group: 5h
    
    3. reading documentation: 2h
    
    4. configuration: 2h
    
    5. analyzing code/output: 5h
    
    6. writing documentation: 3h
    
    7. writing code: 7h
    
    8. running code: 1h
    
* Robin
    1. plenary discussions/meetings : 5h

    2. discussions within parts of the group: 5h
    
    3. reading documentation: 1h
    
    4. configuration: 3h
    
    5. analyzing code/output: 5h
    
    6. writing documentation: 3h
    
    7. writing code: 7h
    
    8. running code: 1h
    
 * Sara
    1. plenary discussions/meetings: 5h

    2. discussions within parts of the group: 5h

    3. reading documentation: 3h

    4. configuration: 3h

    5. analyzing code/output: 5h

    6. writing documentation: 2h

    7. writing code: 6h

    8. running code: 1h

## Overall experience

What are your main take-aways from this project? What did you learn?

Viktor: Finding the right functions was the key to this project.

Sara:
I thought it was very hard to work with these functions since many of them are very nested in terms of new objects and data structures which are poorly documented. Also there were side effects going on and variables which were private so that certain branches could not ever be tested. There were also some dead code. Overall it was interesting but hard and took much effort to work with an open-source project like this.

Fredrik:
It was an interesting project in many ways. We experienced code that was written in a strange way, difficulties with understanding functions and what things actually does and many other obstacles that we had to deal with. Something important that I learned was that it is crucial to write good documentation on how things work so that other people could easier set themselves into the code and the program. I think this was the hardest part, just to understand what is happening and not. However I think it was really hard to chose project, and I think that maybe for the future, you should help more with choosing the project. It took a long time to get started.

Is there something special you want to mention here?
