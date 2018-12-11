# DESIGNED TO SIMULATE ANDROID APP

import bluetooth
from bluetooth import *
import sys
import threading

uuid = "45cd98da-228d-4e75-8102-d428b8a12230" # Our Unique Key
addy = None

nearby_devices = discover_devices(lookup_names = True)
if(len(nearby_devices) > 0):
    for addr, name in nearby_devices:
        print name
        addy = addr

while True:
    service_matches = bluetooth.find_service(uuid = uuid,
                                             address = addy)
    if len(service_matches) == 0:
        print "No BTValet Devices Found."
        continue
    else:
        print "DEVICE MATCHED."
        break

first_match = service_matches[0]
port = first_match["port"]
name = first_match["name"]
host = first_match["host"]

print "Connecting to \"%s\" on %s" % (name, host)

sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
sock.connect((host, port))

class receiverThread(threading.Thread):
    def __init__ (self,sock):
        threading.Thread.__init__(self)
        self.sock = sock
    def run(self):
        while True:
            data = self.sock.recv(1024)
            if len(data) == 0: break
            print data

receiver = receiverThread(sock)
receiver.setDaemon(True)
receiver.start()
print "CONNECTED."
while True:
    data = raw_input()
    if len(data) == 0: break
    sock.send(data)
