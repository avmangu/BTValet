#NAME: Aditya Dixit
#EMAIL: adidixit@g.ucla.edu
#ID: 504631002

default:
	gcc -g -Wall -Wextra -pthread -lm -lmraa lab4c_tcp.c -o lab4c_tcp
	gcc -g -Wall -Wextra -pthread -lm -lmraa -lssl -lcrypto lab4c_tls.c -o lab4c_tls
clean:
	-rm -rf lab4c_tcp lab4c_tls lab4b-004843389.tar.gz logfile
dist:
	tar -czf lab4c-504631002.tar.gz lab4c_tcp.c lab4c_tls.c Makefile README
