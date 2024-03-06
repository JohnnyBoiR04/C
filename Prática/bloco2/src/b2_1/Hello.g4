grammar Hello;
main: (greetings | goodbye)+ ;
greetings: 'hello' ID;
goodbye: 'bye' ID;
ID : [a-z]+;
WS : [ \t\r\n]+ -> skip;
