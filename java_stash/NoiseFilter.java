import java.util.Scanner;

public class NoiseFilter {
    public static void main(String[] args) {
        if (!tests()) {
            System.out.println("Tests failed. ABORT!");
            return;
        }
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
        {
            System.out.println("cannot have a negative size array");
        }
        else if (size % 2 == 0)
        {
            System.out.println("cannot have an even size array");
        }

        buffer = ((size-1)/2);
        double filter[] = new double[size];
        System.out.println("Please enter the values of the filter(separated by spaces):");
        for (int i = 0 ; i <= filter.length-1; i++)
        {
            filter[i] = usrInputs.nextDouble();
        }

        double[] filtered=filterSignal(noisyArray, filter);

        System.out.println("Values on the filtered result array are:");
        for (int i=0; i<filtered.length; i++)
        {
            if (filtered[i] != 0.0)
            {
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

    public static boolean checkFloats(double a, double b) {
        /*
         * Check for equality between two floats.
         *  Since floats are not perfect we have to add in some wiggle.
         */
        if (a*a-b*b <= Math.ulp(a)) {
            return true;
        }
        return false;
    }

    public static boolean test(int name, double[] data, double[] out, double[] filter) {
        System.out.println("Test " + name + " starting...");
        double[] filtered=filterSignal(data, filter);
        for (int i = 0; i < out.length; i++) {
            if (!checkFloats(out[i], filtered[i])) {
                System.out.println("Calculated value at index [" + i + "] did not equal test value.");
                System.out.println("    " + out[i] + " != " + filtered[i]);
                System.out.println("Test " + name + " FAILED!");
                return false;
            }
        }
        System.out.println("Test " + name + " Passed.");
        return true;
    }

    public static boolean tests()
    {
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
        double filter0Array[] = { 1.0 };
        double filter1Array[] = { 0.33333, 0.33333, 0.33333 };
        double filter2Array[] = { 0.1, 0.2, 0.4, 0.2, 0.1 };

        double out2[] = {
            0.199998,
            0.399996,
            0.599994,
            0.799992,
            0.99999,
            1.199988,
            1.399986,
            1.599984,
            1.799982,
            1.99998,
            2.199978,
            2.399976,
            2.599974,
            2.799972,
            2.999970,
            3.199968,
            3.399966,
            3.599964
        };

        double out3[] = {
            0.13719196106667,
            0.35224930947168004,
            0.5824706115689701,
            0.88917574315365,
            1.0284460854363,
            1.16431705037973,
            1.29143937247713,
            1.37901836367846,
            1.80918974192166,
            2.01655030396197,
            2.38250432371851,
            2.30588395826316,
            2.44488389524989,
            2.70811673222853,
            3.1275812558746803,
            3.49270147063602,
            3.47999101074189,
            3.64697304390486
        };

        double out4[] = {
            0.4,
            0.6,
            0.8,
            1.0,
            1.2,
            1.4,
            1.6,
            1.8,
            2.0,
            2.2,
            2.4,
            2.5999999999999996, // WTF! I hate floating point numbers!
            2.8,
            3.0,
            3.2,
            3.4
        };

        double out5[] = {
            0.3603070694,
            0.612589638,
            0.8206324073,
            1.0553336582,
            1.1524681927,
            1.2618028124,
            1.5127875091000003,
            1.6949140849,
            2.1029035117999997,
            2.2425903081,
            2.3832613046,
            2.4551129891,
            2.7569884224,
            3.120776715300001,
            3.3733782743000003,
            3.5682576921000004
        };

        boolean passed = true;
        passed = passed & test(0, idealArray, idealArray, filter0Array);
        passed = passed & test(1, noisyArray, noisyArray, filter0Array);
        passed = passed & test(2, idealArray, out2, filter1Array);
        passed = passed & test(3, noisyArray, out3, filter1Array);
        passed = passed & test(4, idealArray, out4, filter2Array);
        passed = passed & test(5, noisyArray, out5, filter2Array);

        return passed;
    }

    public static double[] filterSignal(double values[], double filter[])
    {
        int new_length = values.length - (filter.length - 1);
        double[] newValues = new double[new_length];
        for (int i=0; i<new_length; i++)
        {
            double x = 0;
            for(int j=0; j<filter.length; j++)
            {
                x += filter[j] * values[i+j];
            }
            newValues[i] = x;
        }

        return newValues;
    }
}
