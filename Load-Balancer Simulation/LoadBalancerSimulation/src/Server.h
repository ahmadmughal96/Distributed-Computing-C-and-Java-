#ifndef SERVER_H_
#define SERVER_H_
#include <omnetpp.h>
using namespace omnetpp;
class Node: public cSimpleModule {
protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);
};
#endif    /* SERVER_H_ */
