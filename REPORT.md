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
|  Method | CCN | 
|---|---|
|  [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 9 if +6 for + 1 OR -1 return point + 2 = 17|
| [verify()](./jgraph-core/src/main/java/org/jgrapht/graph/GraphWalk.java)  | 18 if + 2 for + 1 while + 3 AND + 2 OR -1 return point +2 = 27 |
|[buildGraph()](./jgraph-core/src/main/java/org/jgrapht/graph/builder/GraphTypeBuilder.java) | 16 if + 3 AND -1  +2 =20 |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |

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
### 4. 

### 5. 

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### DYI

Show a patch that show the instrumented code in main (or the unit
test setup), and the ten methods where branch coverage is measured.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

These are the 10 functions we have 

|  Method | Branch number   | CCN | 
|---|---|---|
|  [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2  | 9 if +6 for + 1 OR -1 return point + 2 = 17|
| [verify()](./jgraph-core/src/main/java/org/jgrapht/graph/GraphWalk.java)  | 3  | 18 if + 2 for + 1 while + 3 AND + 2 OR -1 return point +2 = 27 |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |

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

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...


|  Method | Branch number | Old coverage   |Number of new test cases | New coverage | 
|---|---|---|---|---|
|  [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2 | 21/22  | 1 | 22/22 |
| [verify()](./jgraph-core/src/main/java/org/jgrapht/graph/GraphWalk.java)  | 3 | 20/34 | 9 | 31/34 |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |
|   |   |

To find the new test added, one can run the following diff command 
```bash
git diff master..iss_<Branch number> jgrapht-core/src/test/
```
## Refactoring

Plan for refactoring complex code:

Carried out refactoring (optional)

git diff ...

## Effort spent

For each team member, how much time was spent in

1. plenary discussions/meetings;

2. discussions within parts of the group;

3. reading documentation;

4. configuration;

5. analyzing code/output;

6. writing documentation;

7. writing code;

8. running code?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
