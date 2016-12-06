# Matrix Chain Multiplication
Finds the least-costly way to multiply x number of matrices.

For instance, if you are going to multiply matrices Matrix1 and Matrix 2, 
the number of columns of Matrix1 should be the same as the number of rows of Matrix2. 
There is only one way of multiplying Matrix1 and Matrix2.

If you are going to multiply 3 matrices, let's call them Matrix1, Matrix2 and Matrix3, 
then the number of columns of Matrix1 should match the number of columns of Matrix2 
and the number of columns of Matrix2 should match the number of rows of Matrix3. 
There are two possible ways of multiplying Matrix1, Matrix2 and Matrix3: 
Either ((Matrix1*Matrix2)*Matrix3) or (Matrix1*(Matrix2*Matrix3)).

This program will find the matrix multiplication order requiring the fewest number of individual multiplication steps

To test this program, use command "java MatrixChainMultiplication (filename).txt"

Test files are in the following format:

numberOfMatrices
numberOfRowsMatrix1
numberOfRowsMatrix2
numberOfRowsMatrix3
etc.