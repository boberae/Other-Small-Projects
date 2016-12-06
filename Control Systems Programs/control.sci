// System component values
K = 0.036; // motor speed constant
R = 109; // motor resistance
J = .0000157837; // rotor inertia

// System state
theta0 = 2.45;// the initial position for the motor
omega0 = 0;
X=[theta0, omega0];
moving = 0;// the system is not in motion

// The controller definition
Cd = 252;// the setpoint
Kpot = 1.8277;		// the angle voltage ratio
Vzero = 0;		// the voltage when the pendulum is vertical
Vadmax = 5;		// the A/D voltage range
Vadmin = 0;
Cadmax = 255;		// the A/D converter output limits
Cadmin = 0;
Kp = 10;		// position feedback gain
Vpwmmax = .5;		// PWM output limitations in V
Cpwmmax = 255;		// PWM input range
Cdeadpos_static = 174;		// deadband limits for static torque
Cdeadneg_static = 179;
Cdeadpos_dynamic = 86;		// deadband limits for kinetic torque
Cdeadneg_dynamic = 94;
function foo=control(state, t)
	VL = Kpot * state($,1) - Vzero;		// estimate input voltage for pot.
	Cp = (VL - Vadmin) / (Vadmax - Vadmin) * (Cadmax - Cadmin);// estimate A/D input
	if Cp > Cadmax then Cp = Cadmax; end		// check for voltages over limits
	if Cp < Cadmin then Cp = Cadmin; end
	Ce = Cd - Cp;
	Cu = Kp * Ce;

	if abs(state($,2)) > 0.01 then
		Cdeadpos = Cdeadpos_dynamic;
		Cdeadneg = Cdeadneg_dynamic;
	else
		Cdeadpos = Cdeadpos_static;
		Cdeadneg = Cdeadneg_static;
	end
	Cpwm = 0;
	if Cu > 0.99 then// deadband compensation
		Cpwm = Cdeadpos + (Cu/Cpwmmax)*(Cpwmmax - Cdeadpos);
	end
	if Cu <= -0.99 then
		Cpwm = -Cdeadneg + (Cu/Cpwmmax)*(Cpwmmax - Cdeadneg);
	end

	foo = Vpwmmax * (Cpwm / Cpwmmax) ; // PWM output
	if foo > Vpwmmax then foo = Vpwmmax; end // clip voltage if too large
	if foo < -Vpwmmax then foo = -Vpwmmax; end
endfunction

// define the state matrix function for a simple DC motor
function foo=derivative(state,t)
	Vs=control(state, t);
	
	Tapplied = (Vs * K) / R;
	Tpkin = ((Vpwmmax * (Cdeadpos_dynamic / Cpwmmax)) * K) / R;
	Tnkin = -((Vpwmmax * (Cdeadneg_dynamic / Cpwmmax)) * K) / R;
	if (Tapplied > Tpkin) then
		Tf = Tpkin;
	else
	  if(Tapplied < Tnkin) then
		  Tf = Tnkin;
	  else
		  Tf = Tapplied;
		end
	end
	
	foo = [ state($,2), state($,2)*(-(K*K/(J*R))) + Vs*K/(J*R) - (Tf/J)];
endfunction

// Integration Set the time length and step size for the integration
steps = 1000;// The number of steps to use
t_start = 0;// the start time - normally use zero
t_end = 2;// the end time
h = (t_end - t_start) / steps;// the step size
t = [t_start];// an array to store time values
for i=1:steps,
	t = [t ; t($,:) + h];
	control(X($,:), t($,:));
	X = [X ; X($,:) + h * derivative(X($,:), t($,:))];// first order
end

// Graph the values
plot2d(t, X, [-2, -5], leg="angle(rad)@speed(rad/s)");
xtitle('Time (s)');

// printf the values over time
intervals = 20;
for time_count=1:intervals,
	i = int((time_count - 1)/intervals * steps + 1);
	printf("Point at t=%f theta=%f, omega=%f \n", i * h, X(i, 1), X(i, 2));
end

