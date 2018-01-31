{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import qualified Language.Haskell.TH as TH

prog1 :: CompilationUnit
prog1 = [jstmt|
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

jrep :: CompilationUnit -> (Exp -> Bool) -> ((Exp -> CompilationUnit) -> Bool) -> [(Exp, Exp -> CompilationUnit)]
jrep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

type Context = Stmt -> CompilationUnit

-- _
anyctnt :: Stmt -> Bool
anyctnt n = case n
            of [jstmt| `_ |] -> True

-- in _
anycntxt :: Context -> Bool
anycntxt c = case c Empty
             of _ -> True

-- [statement| x = 9 |]
ctnt :: Stmt -> Bool
ctnt n = case n
         of [jstmt| x = 9; |] -> True
            _ -> False

-- * [jstmt| x = 9] *
has :: Stmt -> Bool
has n = case [ x | x <- universeBi n, ctnt x]
          of [] -> False
             _ -> True

anyexp :: (Exp -> CompilationUnit) -> Bool
anyexp _ = True

exp :: Exp -> Bool
exp e = case e of [jexp| 9 |] -> True
                  _ -> False

-- * while (1) {* x = 9 *} *
hasnested :: Stmt -> Bool
hasnested n = case [x | x <- universeBi n, has x]
              of [] -> False
                 _ -> True

-- in while (1) {@}
hasit :: Stmt -> Bool
hasit n = case n
          of [jstmt| while (1) {}|] -> True
             _ -> False

inhas :: Context -> Bool
inhas c = case [ x | x <- universeBi $ c Empty, hasit x]
            of [] -> False
               _ -> True
-- in while (1) { * while (1) {@} * }
hasitstar :: Stmt -> Bool
hasitstar n =
  case n
  of [jstmt| while (1) { `m }|]
       -> case [x | x <- universeBi m, hasit x]
          of [] -> False
             _ -> True
     _ -> False

innested' :: Context -> Bool
innested' c = case [ x | x <- universeBi $ c Empty, hasitstar x]
            of [] -> False
               _ -> True
-- ![statement| x = 9 |]
pnot :: Stmt -> Bool
pnot n = not $ ctnt n

-- in !* [jstmt| while (1) {@} ] *
notin :: Context -> Bool
notin c = not $ inhas c

try :: TH.Q TH.Pat -> TH.Q [TH.Dec]
try p = TH.runQ [d|f s = case s of {$(p) -> True; _ -> False}|]

trypat :: TH.Q TH.Pat
trypat = [p| [jstmt| `x = 9|] |]

runpat :: TH.Q [TH.Dec]
runpat = try trypat

-- ![statement| x = 9 |] && in [statement| while ( 1 ) { @ } |]
pand :: Stmt -> Context -> Bool
pand a b = pnot a && inhas b

-- ![statement| x = 9 |] || in [statement| while ( 1 ) { @ } |]
por :: Stmt -> Context -> Bool
por a b = pnot a || inhas b

---- PATTERNS COMBINATIONS

-- NOT and friends

{-- ! ... [| x = 9 |] ...
nothas n = case [ x | x@[jstmt| x = 9 |] <- universe n]
             of [] -> n
                _ -> Empty --}

{-- ... ! [| x = 9 |] ...
hasnot n = case [ case x of [jstmt| x = 9 |] -> Empty; _ -> x | x <- universe n]
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
