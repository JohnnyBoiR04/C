grammar PrefixCalculator;

program:
        stat* EOF
    ;

stat:
        expr? NEWLINE
    ;

expr:
        op=('*'|'/'|'%') expr expr        #ExprMulDivMod
    |   op=('+'|'-') expr expr            #ExprAddSub
    |   op=('+'|'-') expr                 #ExprUnaryOp
    |   Integer                           #ExprInteger
    |   '(' expr ')'                      #ExprParent
    ;

Integer: [0-9]+;
NEWLINE: '\r' ? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;