int stick1 = 1; // rice-eating example
int stick2 = 1;

active [2] proctype P1() {
  atomic { stick1 > 0;    // wait for a stick
           stick1--; }	   // take a stick
  atomic { stick2 > 0;    // wait for a stick
           stick2--; }	   // take a stick
  stick1++;   // eat and put the sticks back
  stick2++;
}
