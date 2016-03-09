#include <math.h>
#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <limits.h>
#include <math.h>;


/* Mollie Burkle
Multithreading
Find the nth prime number using threads. */

void * thread_main(void *tid);

   /* Initialize the mutex and condition variables  */
pthread_mutex_t lock;
//pthread_mutex_t queued_mutex = PTHREAD_MUTEX_INITIALIZER;
//pthread_cond_t queued = PTHREAD_COND_INITIALIZER;
 
   /* GLOBAL VARIABLES  */
int count = 0;        // prime number counter
int N;                // targeted prime number
int*primes = 0;            // array holding found numbers
 
struct threadData {
   pthread_t ID;
   int startIndex;
   int lastIndex;
} threadData



void * thread_main(struct threadData data) {
   int start = datastartIndex;
   int last = data.lastIndex;
   int prime = 1;
   int halt;

   int i, j;
   for (i = start; i <= last; i++) {
      if (count == N) {
         break;
      }
      prime = 1;
      halt = (int) sqrt (i);
      for (j = 2; j <= halt; j++ {
         if (i % j == 0) {
            prime = 0;
            break;
         }
      }
      if (prime == 1) {
         pthread_mutex_lock(&lock);
         count++;
         primes[count] = i;
         pthread_mutex_unlock(&lock);
      }
   }
   return 0;
 }
      
      /* DEBUGGING
      printf ("\n: %d ", candidate);
      printf ("myCount: %d ", myCount);
      printf ("N: %d ", N);
      */

int main (int argc, char *argv[]) {

   N = atoi (argv[1]);
   int T = atoi (argv[2]);

   // Verify if input is valid or not
   if (argc != 3) {
    printf ("\nInvalid arg length ");  // debugging -remove
      return 0;
   }

   if (N < 0 && T < 0) {
    printf ("not above 0");   // debugging -remove
      return 0;   
   }
   if (N == 1 || N == 2) { // 
      printf ("\nThe %dth prime number is ", N);
      printf ("%d.\n", (N + 1));
      return 0;
   }


   float workload = ((round((log (N)) + 1)) * N);
   primes = malloc(sizeof(int) * (int)workload);
   int asgn = ((int) workload)/T;
   int t_count = 0;

   struct threadData data[T];
	int x;
	for (x = 0; x < T; x++) {
		thdData.ID = x;
      	thdData.startIndex = i;
      	thdData.lastIndex = i + (asgn-1);


   int i;
   for (i = 0; i <= workload;) {
      threadData *thdData = data[i];
      pthread_create(&data[t_count].ID, NULL, &thread_main, &data[t_count];
      t_count++;
      i = i+asgn;
   }

   // Gather threads and display results
   printf ("\nThe %dth prime number is ", N);
   for (i = 0; i < T; i++) {
      pthread_join (&data[i].ID, NULL);
   }
   
   printf ("%d.", primes[N-1]);   
   /*
   if (count == N) {
      printf d("%d. ", candidate);
   }
   else {
      printf ("not found. ");
   }
   */
   return 0;
}

   

      
