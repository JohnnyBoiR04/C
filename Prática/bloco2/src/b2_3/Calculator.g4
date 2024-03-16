grammar Calculator; 

program: 
        stat* EOF
    ;

stat:
        expr? NEWLINE
    ;

expr:
        op=('+'|'-') expr               #ExprUnaryOp
    |   expr op=('*'|'/'|'%') expr      #ExprMulDivMod
    |   expr op=('+'|'-') expr          #ExprAddSub
    |   Integer                         #ExprInteger
    |   '(' expr ')'                    #ExprParent
    ;

Integer:[0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;