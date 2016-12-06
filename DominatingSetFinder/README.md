# DominatingSetFinder

This code finds the smallest dominating set of nodes in a graph

To run this program, use command "java DomSetFinder (filename).txt"

The first value in the text files gives the number of nodes
The matrix contains a 1 if nodes are connected by an edge

i.e.
3
1 0 0
0 1 1
0 1 1
In this, there are three nodes, the first is not connected to anything,
the second and third are connected to each other.
The least dominating set (smallest set of nodes that contain each node or connection to each node)
is 2 for this example (either nodes 1 & 2, or 1 & 3)