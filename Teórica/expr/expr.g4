grammar expr;

main: stat* EOF;

stat: assignment | expression;

assignment: Identifier '=' expression;

expression: Interger | Identifier;

Identifier: [a-zA-Z_][a-zA-Z_0-9]*;
Interger: [0-9]+; 
WhiteSpace: [ \t\n\r]+ -> skip;