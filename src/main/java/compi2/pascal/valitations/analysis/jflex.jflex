/* codigo de usuario */
package compi2.pascal.valitations.analysis;

import java_cup.runtime.*;
import java.util.*;

%% //separador de area

/* opciones y declaraciones de jflex */

%public
%unicode
%class Lexer
%cup
%line
%column
%column
%ignorecase
%init{
    errorsList = new LinkedList<>();
    string = new StringBuilder();
%init}

%state STRING

LineTerminator = \r|\n|\r\n 

WhiteSpace = {LineTerminator} | [ \t\f]
Identifier = [:jletter:] ([:jletterdigit:]|_)*
Boolean = 0|1
DecIntegerLiteral = [0-9]+
DecFloatLiteral = {DecIntegerLiteral}\.{DecIntegerLiteral}

Comment = {SingleComment} | {MultilineComment}
SingleComment = "{" [^*] ~"\n" ~"}" | "{" "}"
MultilineComment   = "(*" [^*] ~"*)" | "(*" "*" + ")"

%{
    StringBuilder string;
  /*--------------------------------------------
    CODIGO PARA EL MANEJO DE ERRORES
  ----------------------------------------------*/
    private List<String> errorsList;

    public List<String> getErrors(){
        return this.errorsList;
    }

    /*--------------------------------------------
        CODIGO PARA EL PARSER
    ----------------------------------------------*/
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void error(String message, Object value) {
        errorsList.add("Error en la linea: " + (yyline+1) + ", columna: " + (yycolumn+1) + " : "+message);
    }

%}

%% // separador de areas

/* reglas lexicas */

    /*-----------------------------------------------------
                    simbolos reservados
    ------------------------------------------------------*/
    /*Operadores*/
    "+"             { return symbol(sym.PLUS); }
    "-"             { return symbol(sym.MINUS); }
    "*"             { return symbol(sym.TIMES); }
    "/"             { return symbol(sym.BARRA); }

    /*Delimitadores*/
    "["             { return symbol(sym.LLAVE_L); }
    "]"             { return symbol(sym.LLAVE_R); }
    "("             { return symbol(sym.PARENTESIS_L); }
    ")"             { return symbol(sym.PARENTESIS_R); }
    "{"             { return symbol(sym.CORCHETE_L); }
    "}"             { return symbol(sym.CORCHETE_R); }
    "`"             { return symbol(sym.ACENT); }

    /*comparators*/
    "="             { return symbol(sym.EQUALS); }
    "<>"            { return symbol(sym.DIFFERENT); }
    ">"             { return symbol(sym.GRATER); }
    "<"             { return symbol(sym.LESS); }
    "<="            { return symbol(sym.GRATER_EQUALS); }
    ">="            { return symbol(sym.LESS_EQUALS); }

    /*others*/
    ":="            { return symbol(sym.ASSIGNATION); }
    ","             { return symbol(sym.COMA); }
    "."             { return symbol(sym.DOT); }
    ";"             { return symbol(sym.SEMICOLON); }
    ":  "             { return symbol(sym.COLON); }


    /* keywords */
    <YYINITIAL> "and"       { return symbol(sym.AND);      }
    <YYINITIAL> "array"     { return symbol(sym.ARRAY);     }
    <YYINITIAL> "begin"     { return symbol(sym.BEGIN);       }
    <YYINITIAL> "case"      { return symbol(sym.CASE);   }
    <YYINITIAL> "const"     { return symbol(sym.CONST);    }
    <YYINITIAL> "div"       { return symbol(sym.DIV);   }
    <YYINITIAL> "do"        { return symbol(sym.DO);   }
    <YYINITIAL> "downto"    { return symbol(sym.DOWNTO);   }
    <YYINITIAL> "else"      { return symbol(sym.ELSE);   }
    <YYINITIAL> "end"      { return symbol(sym.ELSE);   }
    <YYINITIAL> "and"       { return symbol(sym.AND);   }
    <YYINITIAL> "file"      { return symbol(sym.FILE);   }
    <YYINITIAL> "for"       { return symbol(sym.FOR);   }
    <YYINITIAL> "function"  { return symbol(sym.FUNCTION);   }
    <YYINITIAL> "goto"      { return symbol(sym.GOTO);   }
    <YYINITIAL> "if"        { return symbol(sym.IF);   }
    <YYINITIAL> "in"        { return symbol(sym.IN);   }
    <YYINITIAL> "label"     { return symbol(sym.LABEL);   }
    <YYINITIAL> "mod"       { return symbol(sym.MOD);   }
    <YYINITIAL> "nil"       { return symbol(sym.NIL);   }
    <YYINITIAL> "not"       { return symbol(sym.NOT);   }
    <YYINITIAL> "of"        { return symbol(sym.OF);   }
    <YYINITIAL> "or"        { return symbol(sym.OR);   }
    <YYINITIAL> "packed"    { return symbol(sym.PACKED);   }
    <YYINITIAL> "procedure" { return symbol(sym.PROCEDURE);   }
    <YYINITIAL> "program"   { return symbol(sym.PROGRAM);   }
    <YYINITIAL> "record"    { return symbol(sym.RECORD);   }
    <YYINITIAL> "repeat"    { return symbol(sym.REPEAT);   }
    <YYINITIAL> "set"       { return symbol(sym.SET);   }
    <YYINITIAL> "then"      { return symbol(sym.THEN);   }
    <YYINITIAL> "to"        { return symbol(sym.TO);   }
    <YYINITIAL> "type"      { return symbol(sym.TYPE);   }
    <YYINITIAL> "until"     { return symbol(sym.UNTIL);   }
    <YYINITIAL> "var"       { return symbol(sym.VAR);   }
    <YYINITIAL> "while"     { return symbol(sym.WHILE);   }
    <YYINITIAL> "with"      { return symbol(sym.WITH);   }

    /* type of data */
    <YYINITIAL> "integer"   { return symbol(sym.INTEGER);   }
    <YYINITIAL> "real"      { return symbol(sym.REAL);   }
    <YYINITIAL> "boolean"   { return symbol(sym.BOOLEAN);   }
    <YYINITIAL> "char"      { return symbol(sym.CHAR);   }
    <YYINITIAL> "string"    { return symbol(sym.STRING);   }

    <YYINITIAL> {
        /* identifiers */ 
        {Identifier}                   { return symbol(sym.ID, yytext()); }
     
        /* literals */
        {Boolean}                      { return symbol(sym.BOOLEAN_LIT, Boolean.valueOf(yytext()));}
        {DecIntegerLiteral}            { return symbol(sym.INTEGER_LIT, Integer.valueOf(yytext())); }
        {DecFloatLiteral}              { return symbol(sym.REAL_LIT, Float.parseFloat(yytext()));}


        \"                             { string.setLength(0); yybegin(STRING); }
    }

    <STRING> {
        \"                             { yybegin(YYINITIAL);
                                       return symbol(sym.STRING_LIT, 
                                       string.toString()); }
        [^\n\r\"\\]+                   { string.append( yytext() ); }
        \\t                            { string.append('\t'); }
        \\n                            { string.append('\n'); }

        \\r                            { string.append('\r'); }
        \\\"                           { string.append('\"'); }
        \\                             { string.append('\\'); }
    }


    /*lo ignorado*/
    {WhiteSpace}     {/* ignoramos */}
    {Comment}        {/* ignoramos */}

    /* error fallback */
    .               { error("Simbolo invalido <"+ yytext()+">", yytext());}
    <<EOF>>         { return symbol(sym.EOF); }

