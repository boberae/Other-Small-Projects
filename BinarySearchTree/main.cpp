#include <iostream>
#include "BinarySearchTree.h"

using namespace std;

    // Test program
int main( )
{
    BinarySearchTree<int> t;

    int n;
    while(cin >> n) {
      t.insert(n);
    }

    t.printLevelOrder();

    return 0;
}
