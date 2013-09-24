import numpy
import scipy.signal

ideal = numpy.array([0.0,0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,
    2.0,2.2,2.4,2.6,2.8,3,3.2,3.4,3.6,3.8])
noisy = numpy.array([0.008976173,0.015300936,0.38730289,
    0.65415467,0.705971749,
    1.307427486,1.071969875,1.11358872,1.688798266,1.334709476,
    2.40411576,2.310886173,2.432582514,2.174252365,2.727890154,
    3.222288922,3.43265852,3.823261752,3.184157161,3.933609629])

filter1 = numpy.array([1.0, 1.0, 1.0])/3.0
filter2 = numpy.array([0.1, 0.2, 0.4, 0.2, 0.1])

N1 = (len(filter1)-1)
N2 = (len(filter2)-1)

out2 = scipy.signal.convolve(ideal, filter1)[N1:-N1]
out3 = scipy.signal.convolve(noisy, filter1)[N1:-N1]
out4 = scipy.signal.convolve(ideal, filter2)[N2:-N2]
out5 = scipy.signal.convolve(noisy, filter2)[N2:-N2]

def print_java_array(name, array):
    print "double %s[] = {" % name
    for value in array:
        print "    %f, " % value
    print "};"

print_java_array('out2', out2)
print_java_array('out3', out3)
print_java_array('out4', out4)
print_java_array('out5', out5)
