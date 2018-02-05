{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import qualified Language.Haskell.TH as TH
import qualified Language.Haskell.TH.Lib as TL

type Context = Stmt -> CompilationUnit

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

repe :: CompilationUnit -> (Exp -> Bool) -> [Exp]
repe prog pctnt = [ a | a <- universeBi prog, pctnt a]

reps :: CompilationUnit -> (Exp -> Bool) -> [Exp]
reps prog pctnt = [ a | a <- universeBi prog, pctnt a]

-- generate haskell code

matchs :: TH.Q [TH.Dec]
matchs = [d|f s = case s of {[jexp| `x + `_ |] -> True; _ -> False}|]


test :: [Exp]
test = repe prog1 pat
  where pat [jexp| `x + 9 |] = True
        pat [jexp| x + `y |] = False
        pat [jexp| `x + `y |] = False
        pat [jexp| `x |] = False

pstmt :: TH.Q TH.Pat
pstmt = [p| [java| w + 9 |] |]

pexp :: TH.Q TH.Pat
pexp = [p| [jexp| x + 9|] |]

w :: TH.Q TH.Pat
w = TL.wildP

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
