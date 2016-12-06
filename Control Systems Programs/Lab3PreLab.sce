//Start point, end point, number of steps
x_start = 1;
x_end = 10;
points = 1;

//Convergence criteria variables
integral = 0;
Good = 0

//While result is changing by more than 0.01%, keep increasing # of points
while Good < 10

points = points + 1;
integral_OLD = integral;
h = (x_end-x_start)/points;
X = linspace(x_start, x_end, points);
integral = 0;

// "F" represents a function vs. X
for i = 1:points
    F(i) = 5*(X(i)) + (sin(9*X(i)-5)/(X(i)) );
    integral = integral + F(i)*h;
end;

IntGraph(points) = integral;
PointsGraph(points) = points;

//% Difference between last result and this result
convergence = abs(integral - integral_OLD)/integral;
if convergence < 0.000001 then
    Good = Good+1;
end

//Print # of points and integral value
printf("%f\n", integral);

end;

plot2d(PointsGraph,IntGraph);

