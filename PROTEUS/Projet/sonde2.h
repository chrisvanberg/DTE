#include <18F458.h>
#device ADC=10
#use delay(crystal=20000000)
#use rs232(baud=9600,parity=N,xmit=0,rcv=0,bits=8,stream=PORT1)
