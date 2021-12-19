bool c = true;
int num = 0
int process1 = 3
int process2 = 3
int process3 = 3


inline Coin_cmp(){
if
:: c -> return 0;
:: c -> return 1;
fi
}

active [3] proctype Coin() {
atomic{
if
:: _pid == 0 -> process1 = Coin_cmp(); num++;
:: _pid == 1 -> process2 = Coin_cmp(); num++;
:: _pid == 2 -> process3 = Coin_cmp(); num++;
fi
}
}

init{
if
:: num > 2 -> skip;
fi

if
::process1 == process2 -> printf("game is draw\n");
::process1 == process3 -> printf("process1 win\n");
::process2 == process3 -> printf("process2 win\n");
fi
}