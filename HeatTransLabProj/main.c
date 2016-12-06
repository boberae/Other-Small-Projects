//Heat Transfer Lab Project
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main()
{
    double T[22] = { 1050 };        //Temps all initialized to ( T_inf + T_cooling_channel ) / 2 = 1050 K
    double newTemp[22] = { 1050 };
    double erMax;        //Convergence criterion: max delta Temp from last iteration to current iteration

    double deltaTemp = 100;               //Variable to find max difference from new to old temp values
    int i = 0;                            //Loop variable
    double Tinfo = 1700;                  //Tinfinity outer = 1700K
    double Tinfi = 400;                   //Tinfinity inner = 400K
    double outerConv = (0.001*1000)/25;   //(ho*deltaX)/k
    double innerConv = (0.001*200)/25;    //(hi*deltaX)/k

    //Ask user for convergence criterion
    printf("Please enter the convergence criterion\n");
    printf("(Max nodal temperature change from previous iteration to current iteration): ");
    scanf("%lf", &erMax);

    while (deltaTemp > erMax)
    {
        //FIND NEW NODAL TEMPS FOR CURRENT ITERATION
        newTemp[1] = (outerConv*Tinfo+T[2]+T[7])/(2+outerConv);
        newTemp[2] = (2*outerConv*Tinfo+newTemp[1]+T[3]+2*T[8])/(2*(outerConv+2));
        newTemp[3] = (2*outerConv*Tinfo+newTemp[2]+T[4]+2*T[9])/(2*(outerConv+2));
        newTemp[4] = (2*outerConv*Tinfo+newTemp[3]+T[5]+2*T[10])/(2*(outerConv+2));
        newTemp[5] = (2*outerConv*Tinfo+newTemp[4]+T[6]+2*T[11])/(2*(outerConv+2));
        newTemp[6] = (outerConv*Tinfo+newTemp[5]+T[12])/(2+outerConv);
        newTemp[7] = (newTemp[1]+2*T[8]+T[13])/4;
        newTemp[8] = (newTemp[2]+newTemp[7]+T[9]+T[14])/4;
        newTemp[9] = (newTemp[3]+newTemp[8]+T[10]+T[15])/4;
        newTemp[10] = (newTemp[4]+newTemp[9]+T[11]+T[16])/4;
        newTemp[11] = (newTemp[5]+newTemp[10]+T[12]+T[17])/4;
        newTemp[12] = (newTemp[6]+2*newTemp[11]+T[18])/4;
        newTemp[13] = (newTemp[7]+2*T[14]+T[19])/4;
        newTemp[14] = (newTemp[8]+newTemp[13]+T[15]+T[20])/4;
        newTemp[15] = (2*innerConv*Tinfi+2*newTemp[9]+2*newTemp[14]+T[16]+T[21])/(2*(3+innerConv));
        newTemp[16] = (2*innerConv*Tinfi+2*newTemp[10]+newTemp[15]+T[17])/(2*(innerConv+2));
        newTemp[17] = (2*innerConv*Tinfi+2*newTemp[11]+newTemp[16]+T[18])/(2*(innerConv+2));
        newTemp[18] = (innerConv*Tinfi+newTemp[12]+newTemp[17])/(2+innerConv);
        newTemp[19] = (newTemp[13]+T[20])/2;
        newTemp[20] = (newTemp[19]+2*newTemp[14]+T[21])/4;
        newTemp[21] = (innerConv*Tinfi+newTemp[15]+newTemp[20])/(2+innerConv);

        //Search for node with max temp change for this iteration
        deltaTemp = 0;
        for (i=1; i<=21; i++)
        {
            if( fabs(newTemp[i]-T[i]) > deltaTemp)
            {
                deltaTemp = fabs(newTemp[i]-T[i]);
            }
            T[i] = newTemp[i];
        }

        //Print max temp change for this iteration
        printf("\nMax temperature change for iteration (K): %lf", deltaTemp);
    }

    //Print Nodal Temperature Distribution
    printf("\n\nTemperature Distribution:");
    for(i=1; i<=21; i++)
    {
        printf("\nT%d: %lf K", i, T[i]);
    }
    printf("\n");
}


