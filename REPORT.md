# Report for assignment 3

This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.

## Project

Name: JGraphT

URL: https://jgrapht.org/

Source : https://github.com/jgrapht/jgrapht

JGraphT is a library to work with graphs which implements lots of graph algorithms.

## Onboarding experience

Did it build as documented?
    
(See the assignment for details; if everything works out of the box,
there is no need to write much here.)


## Complexity

1. What are your results for the ten most complex functions? (If ranking
is not easily possible: ten complex functions)?
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

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
and would yield the following
```
diff --git a/jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java b/jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java
index 3b5ee5e22..9c6b8dfd3 100644
--- a/jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java
+++ b/jgrapht-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java
@@ -69,6 +69,8 @@ public class HierholzerEulerianCycle<V, E>
      */
     protected V startVertex;
 
+    static boolean[] branchCovered = new boolean[22];
+
     /**
      * Test whether a graph is Eulerian. An
      * <a href="http://mathworld.wolfram.com/EulerianGraph.html">Eulerian graph</a> is a graph
@@ -82,52 +84,83 @@ public class HierholzerEulerianCycle<V, E>
         GraphTests.requireDirectedOrUndirected(graph);
 
         if (graph.vertexSet().isEmpty()) {
+            branchCovered[0] = true;
+
             // null-graph return false
             return false;
         } else if (graph.edgeSet().isEmpty()) {
+            branchCovered[1] = true;
+
             // empty-graph with vertices
             return true;
         } else if (graph.getType().isUndirected()) {
+            branchCovered[2] = true;
+
             // check odd degrees
             for (V v : graph.vertexSet()) {
+                branchCovered[3] = true;
                 if (graph.degreeOf(v) % 2 == 1) {
+                    branchCovered[4] = true;
                     return false;
+                }else{
+                    branchCovered[5] = true;
                 }
             }
             // check that at most one connected component contains edges
             boolean foundComponentWithEdges = false;
             for (Set<V> component : new ConnectivityInspector<>(graph).connectedSets()) {
+                branchCovered[6] = true;
                 for (V v : component) {
+                    branchCovered[7] = true;
                     if (graph.degreeOf(v) > 0) {
+                        branchCovered[8] = true;
                         if (foundComponentWithEdges) {
+                            branchCovered[9] = true;
                             return false;
+                        }else{
+                            branchCovered[10] = true;
                         }
                         foundComponentWithEdges = true;
                         break;
+                    }else{
+                        branchCovered[11] = true;
                     }
                 }
             }
             return true;
         } else {
+            branchCovered[12] = true;
             // check same in and out degrees
             for (V v : graph.vertexSet()) {
+                branchCovered[13] = true;
                 if (graph.inDegreeOf(v) != graph.outDegreeOf(v)) {
+                    branchCovered[14] = true;
                     return false;
+                }else{
+                    branchCovered[15] = true;
                 }
             }
             // check that at most one strongly connected component contains
             // edges
             boolean foundComponentWithEdges = false;
             for (Set<V> component : new KosarajuStrongConnectivityInspector<>(graph)
-                .stronglyConnectedSets())
+                    .stronglyConnectedSets())
             {
+                branchCovered[16] = true;
                 for (V v : component) {
+                    branchCovered[17] = true;
                     if (graph.inDegreeOf(v) > 0 || graph.outDegreeOf(v) > 0) {
+                        branchCovered[18] = true;
                         if (foundComponentWithEdges) {
+                            branchCovered[19] = true;
                             return false;
+                        } else{
+                            branchCovered[20] = true;
                         }
                         foundComponentWithEdges = true;
                         break;
+                    }else{
+                        branchCovered[21] = true;
                     }
                 }
             }
@@ -135,6 +168,7 @@ public class HierholzerEulerianCycle<V, E>
         }
     }
```

### Evaluation

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

|  Method | Branch number | Old coverage   |Number of new test cases | New coverage | 
|---|---|---|---|---|
|  [isEulerian()](./jgraph-core/src/main/java/org/jgrapht/alg/cycle/HierholzerEulerianCycle.java) | 2 | 21/22  | 1 | 
22/22 |
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
