grammar CSV2HTML;

// Define the entry point
csvFile: hdr row+ ;

// Header line, assume separated by comma and a newline at the end
hdr: row ;

// Match a row of values
row: value (',' value)* '\r'? '\n' ;

// Match one value
value: TEXT | STRING | ;

// Match unquoted text
TEXT: ~[,\n\r"]+ ;

// Match quoted string which may include commas, newlines etc.
STRING: '"' ('""'|~'"')* '"' ;

// Skip whitespaces between tokens except newlines, since they are significant in CSV
WS: [ \t]+ -> skip ;
