// PreLab 3 Part 4

// System component values
F = 50;
M1 = 20;
M2 = 10;
Ks1 = 5;
Ks2 = 15;
Ks3 = 25;
Kd1 = 10;
Kd2 = 5;
Kd3 = 20;

x1 = 0;       // initial conditions
v1 = 0;
x2 = 0;
v2 = 0;
X = [x1,v1,x2,v2];

// Define the state matrix function
// The values returned are [x, v]
function foo=f(state,t)
    foo = [state($,2), -((Ks2+Ks1)/M1)*state($,1)-((Kd2+Kd1)/M1)*state($,2)+((Ks2)/M1)*state($,3)+((Kd2)/M1)*state($,4),
          state($,4), ((Ks2)/M2)*state($,1)+((Kd2)/M2)*state($,2)-((Ks3+Ks2)/M2)*state($,3)-((Kd3+Kd2)/M2)*state($,4)+F/M2]; 
endfunction

// Set the time length and step size for the integration
steps = 10000;
t_start = 1;
t_end = 10;
h = (t_end - t_start) / steps;
t = [t_start];

// Loop for integration
for i=1:steps,
    t = [t ; t($,:) + h];
    F1 = h * f(X($,:), t($,:));
    F2 = h * f(X($,:) + F1/2.0, t($,:) + h/2.0);
    F3 = h * f(X($,:) + F2/2.0 , t($,:)+h/2.0 );
    F4 = h * f(X($,:) + F3, t($,:) + h);
    X = [X ; X($,:) + (F1 + 2.0*F2 + 2.0*F3 + F4)/6.0];
end

//Print the Values
printf("The values at the end of the integration are x1=%f, v1=%f, x2=%f, v2=%f", X($,1), X($,2), X($,3), X($,4));

//Graph the Results
plot2d(t, X, [-2, -5, -7, -9], leg="velocity1@velocity2");
xtitle("Velocity 1 and 2 vs. Time");


