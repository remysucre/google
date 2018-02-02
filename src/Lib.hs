{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import qualified Language.Haskell.TH as TH

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

reps :: CompilationUnit -> (Stmt -> Bool) -> [Stmt]
reps prog pctnt = [ a | a@[jstmt| `_ = 9 |] <- universeBi prog, pctnt a]

-- generate haskell code

runp :: TH.Q TH.Pat -> TH.Q [TH.Dec]
runp p = [d|f s = case s of {$(p) -> True; _ -> False}|]

matchs :: TH.Q [TH.Dec]
matchs = runp pstmt

matche :: TH.Q [TH.Dec]
matche = runp pexp

pstmt :: TH.Q TH.Pat
pstmt = [p| [jstmt| `x = 9|] |]

pexp :: TH.Q TH.Pat
pexp = [p| [jexp| `_ + 9|] |]

prog1 :: CompilationUnit
prog1 = [jprog|
public class HelloWorld
{
        public static void main(String[] args) {
                System.out.println("Hello World!");
                while (1) { x = 9; };
                while (1) { y = x + 9; };
                while (1) { x++; };
        }
}|]
