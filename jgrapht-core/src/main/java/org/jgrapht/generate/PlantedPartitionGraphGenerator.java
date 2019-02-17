/*
 * (C) Copyright 2018-2018, by Emilio Cruciani and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */

package org.jgrapht.generate;

import org.jgrapht.*;

import java.util.*;

/**
 * Create a random $l$-planted partition graph. An $l$-planted partition graph is a random graph on
 * $n = l \cdot k$ vertices subdivided in $l$ groups with $k$ vertices each. Vertices within the
 * same group are connected by an edge with probability $p$, while vertices belonging to different
 * groups are connected by an edge with probability $q$.
 *
 * <p>
 * The $l$-planted partition model is a special case of the
 * <a href="https://en.wikipedia.org/wiki/Stochastic_block_model">Stochastic Block Model</a>. If the
 * probability matrix is a constant, in the sense that $P_{ij}=p$ for all $i,j$, then the result is
 * the Erdős–Rényi model $\mathcal G(n,p)$. This case is degenerate—the partition into communities
 * becomes irrelevant— but it illustrates a close relationship to the Erdős–Rényi model.
 *
 * For more information on planted graphs, refer to:
 * <ol>
 * <li>Condon, A. Karp, R.M. Algorithms for graph partitioning on the planted partition model,
 * Random Structures and Algorithms, Volume 18, Issue 2, p.116-140, 2001</li>
 * <li>Fortunato, S. Community Detection in Graphs, Physical Reports Volume 486, Issue 3-5 p.
 * 75-174, 2010</li>
 * </ol>
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 *
 * @author Emilio Cruciani
 */
public class PlantedPartitionGraphGenerator<V, E>
    implements
    GraphGenerator<V, E, V>
{
    private static final boolean DEFAULT_ALLOW_SELFLOOPS = false;

    private final int l;
    private final int k;
    private final double p;
    private final double q;
    private final Random rng;
    private final boolean selfLoops;

    private boolean fired;
    private List<Set<V>> communities;

    /**
     * Construct a new PlantedPartitionGraphGenerator.
     *
     * @param l number of groups
     * @param k number of nodes in each group
     * @param p probability of connecting vertices within a group
     * @param q probability of connecting vertices between groups
     * @throws IllegalArgumentException if number of groups is negative
     * @throws IllegalArgumentException if number of nodes in each group is negative
     * @throws IllegalArgumentException if p is not in [0,1]
     * @throws IllegalArgumentException if q is not in [0,1]
     */
    public PlantedPartitionGraphGenerator(int l, int k, double p, double q)
    {
        this(l, k, p, q, new Random(), DEFAULT_ALLOW_SELFLOOPS);
    }

    /**
     * Construct a new PlantedPartitionGraphGenerator.
     *
     * @param l number of groups
     * @param k number of nodes in each group
     * @param p probability of connecting vertices within a group
     * @param q probability of connecting vertices between groups
     * @param selfLoops true if the graph allows self loops
     * @throws IllegalArgumentException if number of groups is negative
     * @throws IllegalArgumentException if number of nodes in each group is negative
     * @throws IllegalArgumentException if p is not in [0,1]
     * @throws IllegalArgumentException if q is not in [0,1]
     */
    public PlantedPartitionGraphGenerator(int l, int k, double p, double q, boolean selfLoops)
    {
        this(l, k, p, q, new Random(), selfLoops);
    }

    /**
     * Construct a new PlantedPartitionGraphGenerator.
     *
     * @param l number of groups
     * @param k number of nodes in each group
     * @param p probability of connecting vertices within a group
     * @param q probability of connecting vertices between groups
     * @param seed seed for the random number generator
     * @throws IllegalArgumentException if number of groups is negative
     * @throws IllegalArgumentException if number of nodes in each group is negative
     * @throws IllegalArgumentException if p is not in [0,1]
     * @throws IllegalArgumentException if q is not in [0,1]
     */
    public PlantedPartitionGraphGenerator(int l, int k, double p, double q, long seed)
    {
        this(l, k, p, q, new Random(seed), DEFAULT_ALLOW_SELFLOOPS);
    }

    /**
     * Construct a new PlantedPartitionGraphGenerator.
     *
     * @param l number of groups
     * @param k number of nodes in each group
     * @param p probability of connecting vertices within a group
     * @param q probability of connecting vertices between groups
     * @param seed seed for the random number generator
     * @param selfLoops true if the graph allows self loops
     * @throws IllegalArgumentException if number of groups is negative
     * @throws IllegalArgumentException if number of nodes in each group is negative
     * @throws IllegalArgumentException if p is not in [0,1]
     * @throws IllegalArgumentException if q is not in [0,1]
     */
    public PlantedPartitionGraphGenerator(
        int l, int k, double p, double q, long seed, boolean selfLoops)
    {
        this(l, k, p, q, new Random(seed), selfLoops);
    }

    /**
     * Construct a new PlantedPartitionGraphGenerator.
     *
     * @param l number of groups
     * @param k number of nodes in each group
     * @param p probability of connecting vertices within a group
     * @param q probability of connecting vertices between groups
     * @param rng random number generator
     * @param selfLoops true if the graph allows self loops
     * @throws IllegalArgumentException if number of groups is negative
     * @throws IllegalArgumentException if number of nodes in each group is negative
     * @throws IllegalArgumentException if p is not in [0,1]
     * @throws IllegalArgumentException if q is not in [0,1]
     */
    public PlantedPartitionGraphGenerator(
        int l, int k, double p, double q, Random rng, boolean selfLoops)
    {
        if (l < 0) {
            throw new IllegalArgumentException("number of groups must be non-negative");
        }
        if (k < 0) {
            throw new IllegalArgumentException(
                "number of nodes in each group must be non-negative");
        }
        if (p < 0 || p > 1) {
            throw new IllegalArgumentException("invalid probability p");
        }
        if (q < 0 || q > 1) {
            throw new IllegalArgumentException("invalid probability q");
        }
        this.l = l;
        this.k = k;
        this.p = p;
        this.q = q;
        this.rng = rng;
        this.selfLoops = selfLoops;

        this.fired = false;
    }

    /**
     * Generate an $l$-planted partition graph.
     *
     * Note that the method can be called only once. Must instantiate another
     * PlantedPartitionGraphGenerator object in order to generate another $l$-planted partition
     * graph.
     *
     * @param target target graph
     * @param resultMap result map
     * @throws IllegalArgumentException if target is directed
     * @throws IllegalArgumentException if self loops are requested but target does not allow them
     * @throws IllegalStateException if generateGraph() is called more than once
     */

    static boolean[] branchCovered = new boolean[25];

    @Override
    public void generateGraph(Graph<V, E> target, Map<String, V> resultMap)
    {
        if (fired) {
            branchCovered[0] = true;
            throw new IllegalStateException("generateGraph() can be only called once");
        }
        this.fired = true;

        // instantiate community structure
        communities = new ArrayList<>(this.l);
        for (int i = 0; i < this.l; i++) {
            branchCovered[1] = true;
            communities.add(new LinkedHashSet<>(this.k));
        }

        // empty graph case
        if (this.l == 0 || this.k == 0) {
            branchCovered[2] = true;
            return;
        }

        // number of nodes
        int n = this.k * this.l;
        // integer to vertices
        List<V> vertices = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            branchCovered[3] = true;
            V vertex = target.addVertex();
            vertices.add(vertex);

            // populate community structure
            int lv = i / this.k; // group of node v
            communities.get(lv).add(vertex);
        }

        // add self loops
        if (this.selfLoops) {
            branchCovered[4] = true;
            if (target.getType().isAllowingSelfLoops()) {
                branchCovered[5] = true;
                for (V v : vertices) {
                    branchCovered[6] = true;
                    if (this.rng.nextDouble() < this.p) {
                        branchCovered[7] = true;
                        target.addEdge(v, v);
                    }
                }
            } else {
                branchCovered[8] = true;
                throw new IllegalArgumentException("target graph must allow self-loops");
            }
        }

        // undirected edges
        if (target.getType().isUndirected()) {
            branchCovered[9] = true;
            for (int i = 0; i < n; i++) {
                branchCovered[10] = true;
                int li = i / this.k; // group of node i
                for (int j = i + 1; j < n; j++) {
                    branchCovered[11] = true;
                    int lj = j / this.k; // group of node j

                    // edge within partition
                    if (li == lj) {
                        branchCovered[12] = true;
                        if (this.rng.nextDouble() < this.p) {
                            branchCovered[13] = true;
                            target.addEdge(vertices.get(i), vertices.get(j));
                        }
                    }
                    // edge between partitions
                    else {
                        branchCovered[14] = true;
                        if (this.rng.nextDouble() < this.q) {
                            branchCovered[15] = true;
                            target.addEdge(vertices.get(i), vertices.get(j));
                        }
                    }
                }
            }
        }
        // directed edges
        else {
            branchCovered[16] = true;
            for (int i = 0; i < n; i++) {
                branchCovered[17] = true;
                int li = i / this.k; // group of node i
                for (int j = i + 1; j < n; j++) {
                    branchCovered[18] = true;
                    int lj = j / this.k; // group of node j

                    // edge within partition
                    if (li == lj) {
                        branchCovered[19] = true;
                        if (this.rng.nextDouble() < this.p) {
                            branchCovered[20] = true;
                            target.addEdge(vertices.get(i), vertices.get(j));
                        }
                        if (this.rng.nextDouble() < this.p) {
                            branchCovered[21] = true;
                            target.addEdge(vertices.get(j), vertices.get(i));
                        }
                    }
                    // edge between partitions
                    else {
                        branchCovered[22] = true;
                        if (this.rng.nextDouble() < this.q) {
                            branchCovered[23] = true;
                            target.addEdge(vertices.get(i), vertices.get(j));
                        }
                        if (this.rng.nextDouble() < this.q) {
                            branchCovered[24] = true;
                            target.addEdge(vertices.get(j), vertices.get(i));
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the community structure of the graph. The method returns a list of communities,
     * represented as sets of nodes.
     *
     * @throws IllegalStateException if getCommunities() is called before generating the graph
     * @return the community structure of the graph
     */
    public List<Set<V>> getCommunities()
    {
        if (communities == null)
            throw new IllegalStateException(
                "must generate graph before getting community structure");

        return communities;
    }

}
