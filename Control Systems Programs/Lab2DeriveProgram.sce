//Define X as array of 101 equally spaced data pts from 0 to 2*pi
x_start = 0;
x_end = 2*%pi;
points = 101;
X = linspace(x_start, x_end, points);

//Variable "F" represents a function vs. X
//In this case, F is set as array of sin(X)for every point of X
for i = 1:101
    F(i) = sin(X(i));
end;

//Variable Fdot represents first derivative of function F, vs. X
//Use Forward Difference Method to approximate first point of derivative
Fdot(1) = (F(2)-F(1))/(X(2)-X(1));

//Use Central Difference Method to approximate points 2-100 of derivative
for j = 2:points-1
    Fdot(j) = ((F(j+1)-F(j-1))/(X(j+1)-X(j-1)));
end;

//Use Backward Difference Method to approximate final point of derivative
Fdot(points) = ((F(points)-F(points-1))/(X(points)-X(points-1)));

//Plot both the original function, and the derivative of the original function, against X
plot2d(X,F);
plot2d(X,Fdot);


