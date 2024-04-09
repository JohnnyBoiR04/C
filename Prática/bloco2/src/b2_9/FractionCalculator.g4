grammar FractionCalculator;

program:
            stat* EOF
        ;

stat:
            'print' expr+ ';'           #PrintExpr
        |   'print reduce' expr+ ';'    #ReduceExpr
        |   expr+ '->' ID ';'           #Assign
        ;

expr:
            op=('+'|'-') expr           #UnaryOp
        |   expr ('^') Integer          #ExprPower
        |   expr op=('*'|':') expr      #ExprMulDiv
        |   expr op=('+'|'-') expr      #ExprAddSub
        |   Integer                     #UnaryDenominator
        |   ID                          #ExprID
        |   Integer '/' Integer         #Fraction
        |   '(' expr ')'                #ExprParent
        ;

ID: [a-zA-Z]+;
Integer: [0-9]+;
WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;