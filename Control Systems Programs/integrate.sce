//
// integrate.sce
// A simple program to integrate a function
//
// To run this in Scilab use 'File' then 'Exec'.
//

// Define the function
function foo=f(x)
    foo = 5 * x + 2 * log(sin(x) / x + 2);
endfunction

// Set the time length and step size
steps = 10;
x_start = 1;
x_end = 10;
x_delta = (x_end - x_start) / steps;


//
// Loop for rectangular integration
//
total = 0; // Set the initial sum to zero
for i=0:steps,
    x = x_start + i * x_delta;
    total = total + f(x);
end
total = total * x_delta;
printf("Rectangular integration value %f\n", total);

// Loop for trapezoidal integration
//
total = 0; // Set the initial sum to zero
for i=0:steps,
    x = x_start + i * x_delta;
    
    if i == 0 then
        total = total + f(x);       
    elseif i == steps then
        total = total + f(x);       
    else
        even = even + 1;
        
        if even > 1 then
            total = total + 4 * f(x);
            even = 0;            
        else
            total = total + 2 * f(x);
        end
        
    end
    
end

total = total * x_delta / 3;
printf("Simpsons rule integration value %f\n", total);
