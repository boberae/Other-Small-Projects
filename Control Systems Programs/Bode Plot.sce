steps_per_dec = 6;
decades = 6;
start_freq = 0.1;

// the transfer function
function foo=G(w)
    D = %i*w;
    foo = ((D+1000)/(D^2+5*D+100));
endfunction

// this section writes the values to a datafile that may be graphed in a spreadsheet
fd = mopen("data.txt", "w");
for step = 0:(steps_per_dec*decades),
    f = start_freq*10^(step/steps_per_dec); //calculate the next frequency
    w = f*2*%pi; //convert the frequency to radians
    [gain,phase] = polar(G(w)); //find the gain and convert it to mag and angle
    gaindb = 20*log10(gain); //convert magnitude to dB
    phasedeg = 180*phase/%pi; //convert to degrees
    //mfprint(fd, "%f, %f, %f \n", f, gaindb, phasedeg);
end

mclose(fd);

//to graph it directly the following is used
D = poly(0, 'D');
h = syslin('c', ((D+1000)/(D^2+5*D+100)));
bode(h, 0.1, 1000, 'Sample Transfer Function');
