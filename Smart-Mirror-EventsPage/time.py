#this file gets the current date and time

import time;

localtime = time.asctime(time.localtime(time.time()));
print "Local current time :", localtime;