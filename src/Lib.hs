{-# LANGUAGE QuasiQuotes #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data

prog1 :: CompilationUnit
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

grep :: CompilationUnit -> (Stmt -> Bool) -> ((Stmt -> CompilationUnit) -> Bool) -> [(Stmt, Stmt -> CompilationUnit)]
grep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

type Context = Stmt -> CompilationUnit

-- ![statement| x = 9 |]
pnot :: Stmt -> Bool
pnot n = case n
           of [java| x = 9; |] -> True
              _ -> False

-- in [statement| while ( 1 ) { @ } |]
-- TODO replace Empty with some crazy identifier
ctxt :: Context -> Bool
ctxt c = case [ x | x@[java| while (1) {} |] <- universeBi (c Empty)]
           of [] -> False
              _ -> True

-- [java| while ( 1 ) { _ } |] == [java| while ( 1 ) { $( X ) } |]
ctnt :: Stmt -> Bool
ctnt n = case n
           of [java| while ( 1 ) {`_} |] -> True
              _ -> False

-- ... [java| x = 9; |] ...
phas :: Stmt -> Bool
phas n = case [ x | x@[java| x = 9; |] <- universe n ]
           of [] -> False
              _ -> True

-- ![statement| x = 9 |] && in [statement| while ( 1 ) { @ } |]
pand :: Stmt -> Context -> Bool
pand a b = pnot a && ctxt b

-- ![statement| x = 9 |] || in [statement| while ( 1 ) { @ } |]
por :: Stmt -> Context -> Bool
por a b = pnot a && ctxt b

---- PATTERNS COMBINATIONS

-- NOT and friends

{-- ! ... [| x = 9 |] ...
nothas n = case [ x | x@[java| x = 9 |] <- universe n]
             of [] -> n
                _ -> Empty --}

{-- ... ! [| x = 9 |] ...
hasnot n = case [ case x of [java| x = 9 |] -> Empty; _ -> x | x <- universe n]
             of [] -> Empty
                _ -> n --}

{-- ! [| while (1) {_} |]
notctnt n = case n of [| while (1) {`x} |] -> Empty
                      _ -> n --}

{-- [| while (1) { $( ! q ) } |]
-- q = [| x = 9 |]
ctntnot n = case n
              of while (1) { `x } -> case x
                                       of [|x=9|] = Empty
                                          _ = x
                 _ -> Empty --}

{-- ! in [statement| while ( 1 ) { @ } |]
notin n = case n of [statement| while ( 1 ) { `x } |] -> Empty
                                                      -> n
--}

-- !([statement| x = 9 |] && [statement| while ( 1 ) { @ } |])

-- this is interesting, if a context is in a pattern we should match on it first

-- notand n = case n of [|x = 9|] -> case n of [||]

-- in [|while (@) { $( ) }|]

-- !([statement| x = 9 |] || in [statement| while ( 1 ) { @ } |])

-- IN and friends

-- in [statement| while (_) {@} |]

-- [statement| while (1) { $( in if (1) {@} ) } |]

-- [statement| while (1) { $( in while (1) {@} ) } |] NOTE: careful

-- in while (1) { ... @ ... }

-- ... in while (1) {@} ...

-- in while (1) { ... @ ... } and in if (1) {@}

-- in while (1) { ... @ ... } or in if (1) {@}

-- CTNT and friends

-- ... [| while ( 1 ) {_} |] ...

-- [| while ( 1 ) { ... _ ... } |]

-- while ( _ ) { 1 } && while ( 1 ) { _ }

-- while ( 1 ) { _ } || for ( 1 ) { _ }

-- HAS and friends

-- ... while ( 1 ) { _ } ... && ... if ( 1 ) { _ } ...

-- ... while ( _ ) { 1 } && while ( 1 ) { _ } ...

-- AND and friends

-- while (1) {_} && (while (_) {1} || while (_) {2})
