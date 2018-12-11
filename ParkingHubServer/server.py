import bluetooth
from bluetooth import *
import sys
from terminaltables import AsciiTable
import os, time
import threading

def currentTime():
    cur = time.time()
    os.environ["TZ"] = "US/Pacific"
    time.tzset()
    return time.strftime("%T %Z", time.localtime(cur))

# CREATING PARKING STRUCTURE
parkingSpots = {x:[] for x in range(1, 11)}
parkingSpots[1] = ['(B6:B8:C6:D8)', currentTime()]

def openSpots():
    spots = list()
    for key, value in parkingSpots.items():
        if(len(value) == 0):
            spots.append(key)
    return str(spots)

# ONLY FOR THE SERVER TO PRINT THE STRUCTURE
def parkingLot():
    table_data = [
        ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
        ['', '', '', '', '', '', '', '', '', ''],
    ]
    table = AsciiTable(table_data)
    for key, value in parkingSpots.items():
        if(len(value) > 0):
            table_data[1][key - 1] = str(value[0] + ": " + value[1])
        else:
            table_data[1][key - 1] = "EMPTY"

    return table.table + '\n'

print parkingLot()

# OPENING CONNECTION
port = 0
server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
server_sock.bind(("", port))
server_sock.listen(1)

uuid = "45cd98da-228d-4e75-8102-d428b8a12230" # THIS NEEDS TO BE IN THE APP
bluetooth.advertise_service(server_sock, "BTValet", uuid)

while True:
    checkedOut = False

    client_sock, address = server_sock.accept()
    print "Connected To: " + str(address)
    for key, value in parkingSpots.items():
        for i in value:
            # CHECKING OUT (SEEING IF HIS BT ADDY IS ALREADY THERE)
            if(str(i) == str(address)):
                print str(address) + " checked out."
                checkedOut = True
                client_sock.send("Checked Out. Thanks for using BTValet!")
                parkingSpots[int(key)] = []
                print parkingLot()
                break
        if(checkedOut):
            break
    if(checkedOut):
        continue

    client_sock.send(openSpots()) # SEND CLIENT OPEN SPOTS
    data = client_sock.recv(1024) # DATA RECEIVED
    # PARSING DATA
    while(len(parkingSpots[int(data)]) > 0):
        client_sock.send("That Parking Spot is already taken.")
        data = client_sock.recv(1024)

    # ADDING USER TO PARKING STRUCTURE
    parkingSpots[int(data)] = [str(address), currentTime()]
    client_sock.send(openSpots()) # SEND CLIENT OPEN SPOTS ONE MORE TIME
    print parkingLot()
    client_sock.close()
