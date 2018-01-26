{-# LANGUAGE QuasiQuotes #-}

module Lib where

import JQQ 
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import Language.Java.Pretty

-- prog1 :: Language.Java.Syntax.Stmt
prog1 = [java|
public class HelloWorld
{
	public static void main(String[] args) {
		System.out.println("Hello World!");
		while (1) { x = 9; };
		while (1) { x++; };
	}
}|]

-- GREP
-- just like linux grep, this function takes a program (prog) and a pattern (match)
-- and returns a list of statements each of which matches the pattern

grep :: Language.Java.Syntax.CompilationUnit -> (Language.Java.Syntax.Stmt -> Language.Java.Syntax.Stmt) -> [Language.Java.Syntax.Stmt]
grep prog match = [ match x | x <- universeBi prog ]

---- PATTERNS

-- ![statement| x = 9 |]
pnot :: Language.Java.Syntax.Stmt -> Language.Java.Syntax.Stmt
pnot n = case n of [java| x = 9; |] -> Empty
                   _ -> n

-- in [statement| while ( 1 ) { @ } |]
pctxt [java| while ( 1 ) {`xx} |] = xx


-- [java| while ( 1 ) { _ } |]
hole n@[java| while ( 1 ) {`x} |] = n
hole _ = Empty

-- ... [java| x = 9; |] ...
pctnt n = case [ x | x@[java| x = 9; |] <- universe n ] of [] -> Empty
                                                           _ -> n
