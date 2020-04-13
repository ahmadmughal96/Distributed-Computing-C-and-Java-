#include <iostream>
#include "mpi.h"


int main(int argc, char* argv[])
{
//cout << "Hello World\n";
int nTasks, rank;
MPI_Init(&argc,&argv);
MPI_Comm_size(MPI_COMM_WORLD,&nTasks);
MPI_Comm_rank(MPI_COMM_WORLD,&rank);
printf ("Number of threads = %d, My rank = %d\n", nTasks, rank);
int a;
std::cin>>a;
MPI_Finalize();
return 0;
}
