Exercise for "Java: Multi-threading and Concurrency Simplified" on Udemy

When we run Main we see an output that can look like this:
```
___FaultyTicketReservationSystem___
pool-1-thread-4 {a=100, b=100}
pool-1-thread-2 {a=100, b=100}
pool-1-thread-3 {a=100, b=100}
pool-1-thread-1 {a=100, b=100}
pool-1-thread-3 Reserved 51 tickets for train b
pool-1-thread-1 Reserved 51 tickets for train a
pool-1-thread-4 Reserved 51 tickets for train a
pool-1-thread-1 {a=-2, b=-2}
pool-1-thread-3 {a=-2, b=-2}
pool-1-thread-2 Reserved 51 tickets for train b
pool-1-thread-4 {a=-2, b=-2}
pool-1-thread-2 {a=-2, b=-2}
```
The system demonstrates a concurrency problem, the ticketing system is changing inbetween checking for the ticket count and reserving tickets. 
The race condition of the threads leads to a problem called the "Lost Update Problem".
Easy solution here is to `synchronize` the reserveTicket method. 
```
___GoodTicketReservationSystem___
pool-2-thread-1 {a=100, b=100}
pool-2-thread-2 {a=100, b=100}
pool-2-thread-3 {a=100, b=100}
pool-2-thread-4 {a=100, b=100}
pool-2-thread-1 Reserved 51 tickets for train a
pool-2-thread-1 {a=49, b=100}
pool-2-thread-4 Not enough tickets left, could not reserve a ticket to the train: a
pool-2-thread-4 {a=49, b=100}
pool-2-thread-3 Reserved 51 tickets for train b
pool-2-thread-3 {a=49, b=49}
pool-2-thread-2 Not enough tickets left, could not reserve a ticket to the train: b
pool-2-thread-2 {a=49, b=49}
```