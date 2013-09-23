import java.util.Scanner;

/*
 * Quick things,
 *  Seperate your user input processing from the logic of your program.
 *  This way you can check the logic of your program with out a user.
 *  You should include some tests so you dont have to spend all your
 *  time typing in the command line. Your main should just fire off the
 *  tests first thing. Then when everything is working comment out the
 *  calls to the tests and hand it in.
 *
 * Send me the assignment so I can check the functional side of your
 *  program.
 */

public class Main{
    /*
     * What does this method do?
     */
    public static void main(String[] args) {
        Scanner usrInputs = new Scanner(System.in);
        double absDif = 0;
        int size = 0;
        int buffer;
        double idealArray[] =
        {0.0,0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,
            2.0,2.2,2.4,2.6,2.8,3,3.2,3.4,3.6,3.8
        };
        double noisyArray[] =
        {0.008976173,0.015300936,0.38730289,
            0.65415467,0.705971749,
            1.307427486,1.071969875,1.11358872,1.688798266,1.334709476,
            2.40411576,2.310886173,2.432582514,2.174252365,2.727890154,
            3.222288922,3.43265852,3.823261752,3.184157161,3.933609629
        };

        // You could probably put the user input stuff in its own function
        System.out.println("Welcome to the Filter Program!");
        System.out.println("Please enter the size of  the filter array:");
        size = usrInputs.nextInt();
        if (size < 0)
            System.out.println("cannot have a negative size array");
        else if (size % 2 == 0)
            System.out.println("cannot have an even size array");

        buffer = ((size-1)/2);
        double filter[] = new double[size];
        System.out.println("Please enter the values of the filter(separated by spaces):");
        for (int i = 0 ; i <= filter.length-1; i++ ) 
            filter[i] = usrInputs.nextDouble();

        double[] filtered=filterSignal(noisyArray, filter);

        System.out.println("Values on the filtered result array are:");
        for(int i=0; i<filtered.length; i++)
        {
            if(filtered[i] != 0.0)
                System.out.println(filtered[i]);
            }
        }
        System.out.println("The average absolute difference from the idealSignal is");
        for (int i=0; i<filtered.length; i++)
        {
            if(i+(2*buffer) >= filtered.length)
                i = filtered.length;
            else
                absDif += Math.abs(filtered[i+buffer]-idealArray[i+buffer]);         	   
        }
        System.out.println(absDif/(idealArray.length-(2*buffer)));
    }

    public static double[] filterSignal(double values[], double filter[])
    {
        double[] newValues= new double[values.length];
        int buffer;
        for (int i=0; i<values.length; i++)
        {

            buffer = ((filter.length-1)/2);
            double x = 0;
            for(int j=0; j<filter.length; j++)
            {
                if (i-buffer < 0 || i+buffer > values.length-1)
                {
                    j = filter.length;
                }
                /*
                 * Always close your if/else statements in {}'s. Even a
                 * single line needs them. It convays your intent to
                 * people working on your code.
                 */
                else if (i-buffer >= 0) {
                    x = x + (values[i-buffer]*filter[j]);
                }
                buffer--;
            }
            newValues[i] = x;
        }

        return newValues;
    }
}
