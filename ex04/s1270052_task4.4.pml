int max = 0
int num = 0
int array[5] = {10,3,11,14,12 }
active [5] proctype Max_cmp() {
atomic{
if
:: max <= array[_pid]  -> max = array[_pid]; num++;
:: max > array[_pid]  -> skip; num++;
fi
}
}

init {
if
:: num > 4 -> printf("Max num is: %d\n",max);
fi
}