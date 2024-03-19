grammar Calculator; 

program: 
            stat* EOF
      ;

stat:
            expr? NEWLINE                       #PrintExpr
      |     ID '=' expr NEWLINE                 #Assign
      ;

expr:
            op=('+'|'-') expr                   #ExprUnaryOp
      |     expr op=('*'|'/'|'%') expr          #ExprMulDivMod
      |     expr op=('+'|'-') expr              #ExprAddSub
      |     Integer                             #ExprInteger
      |     ID                                  #ExprID
      |     '(' expr ')'                        #ExprParent
      ;

ID: [a-zA-Z]+;
Integer: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;