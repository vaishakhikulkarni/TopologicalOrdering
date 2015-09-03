
/*Name:Vaishakhi Kulkarni
NetId:vpk140230
*/

Input:
Topological ordering of a DAG.
Implement the two algorithms for ordering the nodes of a DAG
topologically.
List<Vertex> toplogicalOrder1(Graph g) { /* Algorithm 1 */ }
Stack<Vertex> toplogicalOrder2(Graph g) { /* Algorithm 2 */ }

Step:
1.Compile the Assignment6a.java file
2.Run with command java Assignment6a C:\Users\Vaishakhi\workspace\Vaishakhi\input1.txt
       	   
Output:
Graph:
1: (1,2)(1,3)(1,4)
2: (2,4)(2,5)
3: (3,6)
4: (4,5)(4,6)(4,7)(4,6)
5: (5,7)
6: 
7: (7,6)
Topological Order using LinkedList:(Algorithm 1)
 [1, 2, 3, 4, 5, 7, 6]
Time is:1
Topological Order using Stack:(Algorithm 2)
 1  3  2  4  5  7  6 
Time is:0
