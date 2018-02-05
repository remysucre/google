{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import qualified Language.Haskell.TH as TH
import qualified Language.Haskell.TH.Lib as TL

--------------------
-- search engine ---
--------------------

grepe :: CompilationUnit -> (Exp -> Bool) -> [Exp]
grepe prog pctnt = [ a | a <- universeBi prog, pctnt a]

greps :: CompilationUnit -> (Stmt -> Bool) -> [Stmt]
greps prog pctnt = [ a | a <- universeBi prog, pctnt a]

grepj :: CompilationUnit -> (Pat -> Bool) -> [Stmt]
grepj = undefined

-- TODO transformation engine

--------------------
-- patterns here ---
--------------------

teste :: [Exp]
teste = grepe prog1 pat
  where pat [jexp| `x + 9 |] = True -- <- NOTE: add your patter here
        pat _ = False
        -- pat [jexp| x + `y |] = True
        -- pat [jexp| `x + `y |] = True
        -- pat [jexp| `x |] = True

tests :: [Stmt]
tests = greps prog1 pat
  where pat [jstmt| while (`x) { x = 9; } |] = True -- <- NOTE: add your patter here
        pat _ = False

-- TODO Pat test

--------------------
-- program source --
--------------------

prog1 :: CompilationUnit
prog1 = [jprog|
public class HelloWorld
{
        public static void main(String[] args) {
                System.out.println("Hello World!");
                while (1) { x = 9; };
                while (1) { y = x + 9; };
                while (1) { y = x + 10; };
                while (1) { x++; };
        }
}|]

--------------------
-- stuff dont look -
--------------------

pstmt :: TH.Q TH.Pat
pstmt = [p| [java| w + 9 |] |]

pexp :: TH.Q TH.Pat
pexp = [p| [jexp| x + 9|] |]

w :: TH.Q TH.Pat
w = TL.wildP

-- generate haskell code

matchs :: TH.Q [TH.Dec]
matchs = [d|f s = case s of {[jexp| `x + `_ |] -> True; _ -> False}|]

-- GREP
-- just like linux grep, this function takes a program (prog) and a pattern (match)
-- and returns a list of statements each of which matches the pattern

grep :: [Stmt]
grep = [r | r@[java| if (1) {}; |] <- universeBi prog1]

erep :: [Exp]
erep = [e | e@[java| 9 |] <- universeBi prog1]

srep :: CompilationUnit -> (Stmt -> Bool) -> ((Stmt -> CompilationUnit) -> Bool) -> [(Stmt, Stmt -> CompilationUnit)]
srep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

jrep :: CompilationUnit -> (Exp -> Bool) -> ((Exp -> CompilationUnit) -> Bool) -> [(Exp, Exp -> CompilationUnit)]
jrep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]



type Context = Stmt -> CompilationUnit
