package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}


%cup
%line
%column


%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"const"     { return new_symbol(sym.CONST, yytext()); }
"if"        { return new_symbol(sym.IF, yytext());}
"else"      { return new_symbol(sym.ELSE, yytext());}
"while"     { return new_symbol(sym.WHILE, yytext());}
"foreach"   { return new_symbol(sym.FOREACH, yytext());}
"break"     { return new_symbol(sym.BREAK, yytext()); }
"continue"  { return new_symbol(sym.CONTINUE, yytext()); }
"read"      { return new_symbol(sym.READ, yytext()); }
"new"       { return new_symbol(sym.NEW, yytext());}
"findAny" 	{ return new_symbol(sym.FINDANY, yytext());}
"findAndReplace" { return new_symbol(sym.FINDANDREPLACE, yytext());}
"count" { return new_symbol(sym.COUNT, yytext());}
"max" { return new_symbol(sym.MAX, yytext());}
"###" { return new_symbol(sym.BIN, yytext());}


"="   { return new_symbol(sym.IS, yytext()); }
"++"  { return new_symbol(sym.INCR, yytext()); }
"--"  { return new_symbol(sym.DECR, yytext()); }
"+"   { return new_symbol(sym.PLUS, yytext()); }
"-"   { return new_symbol(sym.MINUS, yytext()); }

"*"   { return new_symbol(sym.MUL, yytext()); }
"/"   { return new_symbol(sym.DIV, yytext()); }
"%"   { return new_symbol(sym.PERCENT, yytext()); }

"=="  { return new_symbol(sym.EQUAL, yytext()); }
"!="  { return new_symbol(sym.NOTEQUAL, yytext()); }
">"  { return new_symbol(sym.GRT, yytext()); }
">="  { return new_symbol(sym.GRTE, yytext()); }
"<"  { return new_symbol(sym.LT, yytext()); }
"<="  { return new_symbol(sym.LTE, yytext()); }

";"   { return new_symbol(sym.SEMI, yytext()); }
"."   { return new_symbol(sym.DOT, yytext()); }
","   { return new_symbol(sym.COMMA, yytext()); }

"&&"   { return new_symbol(sym.AND, yytext()); }
"||"   { return new_symbol(sym.OR, yytext()); }


"("   { return new_symbol(sym.LPAREN, yytext()); }
")"   { return new_symbol(sym.RPAREN, yytext()); }
"["   { return new_symbol(sym.LBRACKET, yytext()); }
"]"   { return new_symbol(sym.RBRACKET, yytext()); }
"{"   { return new_symbol(sym.LBRACE, yytext()); }
"}"   { return new_symbol(sym.RBRACE, yytext()); }
"=>"  { return new_symbol(sym.ARROW, yytext()); }


"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" {yybegin(YYINITIAL);}

[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
"true"      { return new_symbol(sym.BOOL, true); }
"false"     {return new_symbol(sym.BOOL, false); }
[a-zA-Z][a-zA-Z0-9_]* 	  {return new_symbol (sym.IDENT, yytext()); }
"'"."'" {return new_symbol (sym.CHAR, yytext().charAt(1)); }

. { System.err.println("Lexer error ("+yytext()+") on line "+(yyline+1)); }