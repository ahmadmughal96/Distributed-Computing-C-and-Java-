#include "Server.h"
#include <string.h>
#include <omnetpp.h>
Define_Module(Server);
int d=1;
using namespace omnetpp;
/* Implementation of defined functions initialize() and handleMessage() in a sequential fashion.*/ 
void Server::initialize() 
{
/*If Message gets received from trafficGenerator then instance of Message is created and is delivered as well. */
    if (strcmp("A" , getName())==0)
    {
        cMessage *msg= new cMessage("class");
        send(msg, "out[0]");
    }
}
//If message gets received from LoadBalancer and checking bit is 1 then move this message to Sink Server C else Sink Server D receives this message. 
void Server:: handleMessage(cMessage *msg) {
    // TODO Auto-generated destructor stub
    if (strcmp("B", getName())==0){
        if (d==1){
            send(msg, "out", 4);
            d=0;
        }
        else
                send(msg, "out", 5);
            }
    else
        send(msg, "out", 3);
}
