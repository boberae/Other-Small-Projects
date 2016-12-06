// 2 Variable Runge Kutta


x1 = 0;       // initial conditions
v1 = 0;
Jm = 0.04407;
Ks = 6.800;



X = [x1, v1];

// Define the state matrix function
// The values returned are [x, v]
function foo=f(state,t)
    foo = [state($,2), state($,1)*(-Kx/Jm)]; 
endfunction

// Set the time length and step size for the integration
steps = 1000;
t_start = 0;
t_end = 4;
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
    //Print the Values
    printf("\nIntegration values: t=%f, x1=%f", t($,:), X($,1));
end


//Graph the Results
plot2d(t, X, [-7, -9], leg="position");
xtitle("Position vs. Time");


