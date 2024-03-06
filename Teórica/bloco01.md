# TP-3

### Introdução às gramáticas
 - Gramática é definir símbolos à custa de símbolos
 - Tal como em programação, muitas vezes descrições recursivas são bem mais simples, sem perda
 da objectividade e do rigor necessários.
 
#### Quádrupolo Gramatical
 - Formalmente, uma gramática é um quádruplo G = (T,N,S,P), onde:
    1. T é um conjunto finito não vazio designado por alfabeto terminal, onde cada elemento é designado por símbolo terminal;
    2. N é um conjunto finito não vazio, disjunto de T (N ∩ T != 0), cujos elementos são designados por símbolos não terminais;
    3. S ∈ N é um símbolo não terminal específico designado por símbolo inicial;
    4. P é um conjunto finito de regras da forma α -> β onde α ∈(T ∪ N)^* N(T ∪ N)^* e β ∈(T ∪ N)^*, isto é:
        - α é uma cadeia de símbolos terminais e não terminais contendo, pelo menos, um símbolo não terminal;
        - β é uma cadeia de símbolos, eventualmente vazia, terminais e não terminais.

