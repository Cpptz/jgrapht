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

The [documentation for the developpers](https://github.com/jgrapht/jgrapht/wiki#developer-pages) is really well written.

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
| [equals](./jgrapht-core/src/main/java/org/jgrapht/alg/isomorphism/IsomorphicGraphMapping.java)|2 if + 1 OR + 2 AND=|
| [simpleCycleToGraphPath](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/Cycles.java)|8 if + 1 for + 1 while  =|
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java) | 6 if + 5 for + 3 AND   -1  +2 =15 |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |

</center>

We have the same results when we calculated CCN by hand.

### 2.
As this a graph library with many algorithms, the functions can be both long and really complex.

### 3.
#### [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java)
This function return true if the graph contains an eulerian cycle.
That is finding if it is possible to construct a cycle, i.e. a path starting and ending on the same vertex, which
visits each vertex exactly once. It also means, the graph should only have one connected componenent with edges.

First, it checked that the graph contained vertices and node.

Then if it is a undirected graph, it checks that all vertices have an even degree
and that there is only connected component with edges.

Otherwise if it is a directed graph, it checks for all vertices that their in degree is equal to their out degree
and that there is only connected component with edges.

### [verify()](./jgraph-core/src/main/java/org/jgrapht/graph/GraphWalk.java)
This function checks that the path of the ``GraphWalk`` is feasible w.r.t. the graph of the ``GraphWalk``.

The path is described as a list of vertices, a list of edges or both.

First it checks that either the path or both the list of vertices and the list of edges

Then depending on the way the path is described, it will check that the vertices or edges are contained in the graph,
that each edges or vertices follow each other...

### [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java)
This function builds a graph dependant on the boolean variables allowingSelfLoops, allowingMultipleEdges, weighted and

directed. The function will return one of 16 possible graph structures that matches the correct boolean variables.

The function handles this by nesting if statements.

### [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)
This function computes a minimal-cost Hamiltonian tour, that is, the shortest way to visit every vertex exactly once.

The function will return a minimal cost tour if it can be found, it will return null otherwise.

The function starts by mapping all edges to integers and calculating the minimum weight between all vertices.

It then reconstructs the tour and finds the minimal cost hamiltonian tour by looping through and evaluating all the edges.


### 4.

The complexity does not change due to adding exceptions. When calculating complexity, the function evaluates the

exception essentially as a return statement. With regards to the complexity, it does not matter if execution ends on a

return statement or an exception.

When catching an exception using try{} catch{} will increase complexity by one due to the try statement as it holds

similar functionality as an if-statement.

### 5.

## Coverage

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
| [equals](./jgrapht-core/src/main/java/org/jgrapht/alg/isomorphism/IsomorphicGraphMapping.java)|11|
| [simpleCycleToGraphPath](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/Cycles.java)|13|
| [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java)|12|
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)|14|
|   |   |
|   |   |
|   |   |
|   |   |

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

git diff ... -->

<center>


|  Method | Branch number | Old coverage   |Number of new test cases | New coverage |
|---|---|---|---|---|
| [isEulerian](./jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2 | 21/22  | 1 | 22/22 |
| [verify](./jgrapht-core/src/main/java/org/jgrapht/graph/GraphWalk.java)   | 3 | 20/34 | 9 | 31/34 |
| [buildGraph](./jgrapht-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java) | 12 | 8/27 | 2 | 12/27 |
| [getTour](./jgrapht-core/src/main/java/org/jgrapht/alg/tour/HeldKarpTSP.java)| 14 | 17/18 | 1 | 18/18 |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
<center>


To find the new test added, one can run the following diff command
```bash
git diff master..iss_<Branch number> jgrapht-core/src/test/
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

## Effort spent

For each team member, how much time was spent in
Viktor:
    1. plenary discussions/meetings; 5h

    2. discussions within parts of the group; 5h

    3. reading documentation; 3h

    4. configuration; 3h

    5. analyzing code/output; 5h

    6. writing documentation; 2h

    7. writing code; 3h

    8. running code? 0.5h

## Overall experience

What are your main take-aways from this project? What did you learn?

Viktor: Finding the right functions were the key to this project.

Is there something special you want to mention here?
