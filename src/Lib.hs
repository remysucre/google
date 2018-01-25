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

-- pullall :: Language.Java.Syntax.CompilationUnit -> [Language.Java.Syntax.Stmt]
pullall :: Language.Java.Syntax.CompilationUnit -> [String]
pullall p = [ prettyPrint (pull x) | x <- universeBi p ]
-- pullall p = [ pull x | x <- universeBi p ]

pull :: Language.Java.Syntax.Stmt -> Language.Java.Syntax.Stmt
-- pull [java| while ( 1 ) {`xx} |] = xx -- _ in while ( _ ) { @ }
-- pull n@[java| while ( 1 ) {`x} |] = case x of [java| x = 9; |] -> n
--                                               _ -> Empty
-- pull n = case [ x | x@[java| x = 9; |] <- universe n ] of [] -> Empty
--                                                           _ -> n
pull n = case n of [java| x = 9; |] -> Empty
                   _ -> n
pull x = Empty
