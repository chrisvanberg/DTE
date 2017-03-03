#include <18F458.h>
#device ADC=8
#use delay(crystal=20000000)
#use rs232(baud=9600,parity=N,xmit=None,rcv=None,bits=8,stream=PORT1)

