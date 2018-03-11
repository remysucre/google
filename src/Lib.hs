{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE ViewPatterns #-}

module Lib where

import JQQ
import P1
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

grepj :: CompilationUnit -> (Stmt -> Bool) -> [Stmt]
grepj prog pctnt = [ a | a <- universeBi prog, pctnt a]

--------------------
-- patterns here ---
--------------------

testj :: CompilationUnit -> [Stmt]
testj prog = grepj prog pat
  where pat [java| 
/////////////////////
//     Patch 1     //
/////////////////////
//
// if (this.#x != null) {
//  int[] range = (int[]) this.#x.get(node);
//  `[ `_ `] }
//
/////////////////////
//   Patch 2 & 3   //
/////////////////////
//
// if (`*(
// (( this.currentCharacter =
// this.source [this.currentPosition++])
// == '\\')
// `)*) `*( (c1 = Character.getNumericValue(`_)) > 15 `)*
//
/////////////////////
//     Patch 4     //
/////////////////////
//
// switch (`_) {
//   case SWT.LINE_DOT:
//   case SWT.LINE_DASH:
//   case SWT.LINE_DASHDOT:
//   case SWT.LINE_DASHDOTDOT:
//     data.state &= ~LINE_STYLE;
// }
//
/////////////////////
//     Patch 5     // 
/////////////////////
//
// if (`*( focusIndex `)*) `*( redraw `)*
//
/////////////////////
//     Patch 6     // 
/////////////////////
//
// if (control instanceof Tree) { `*( TreeDragAndDropEffect `)* } else `_
//
/////////////////////
//     Patch 7     // 
/////////////////////
//
// for (int i = 0; i < digits; i++) adjustment.#x *= 10;
//
/////////////////////
//     Daikon      //
/////////////////////
//
for (Iterator<`_> #i = `*( iterator `)*; #i.hasNext(); ) { // PATTERN HERE
  `_ #_ = #i.next();
  `[ `! `*( #i `)* `]
}
// for (#_<#_> #i = `*( iterator() `)* ; #i.hasNext(); ) {
//   #_ #_ = #i.next();
//   `[ `! `*( #i `)* `]
// }
//
|] = True
        pat _ = False

teste :: [Exp]
teste = grepe prog1 pat
  where pat [jexp| `_ != null |] = True -- <- NOTE: add your patter here
        pat [jexp| x + `_ |] = True
        pat [jexp| `x + `_ |] = True
        pat [jexp| `x |] = True
        pat _ = False

{-
tests :: [Stmt]
tests = greps prog1 pat
  where pat [jstmt|
                if (this.leadingComments != null) {
                        int[] range = `x;
                        `y
                        `n } |] = True
        pat _ = False
-}
-- TODO Pat test

--------------------
-- program source --
--------------------


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
grep = [r | r@[java| if (2) {} |] <- universeBi prog1]

erep :: [Exp]
erep = [e | e@[java| 9 |] <- universeBi prog1]

srep :: CompilationUnit -> (Stmt -> Bool) -> ((Stmt -> CompilationUnit) -> Bool) -> [(Stmt, Stmt -> CompilationUnit)]
srep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

jrep :: CompilationUnit -> (Exp -> Bool) -> ((Exp -> CompilationUnit) -> Bool) -> [(Exp, Exp -> CompilationUnit)]
jrep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

g = $(do
  nm1 <- TH.newName "x"
  let nm2 = TH.mkName "x"
  return (TH.LamE [TH.VarP nm1] (TH.LamE [TH.VarP nm2] (TH.VarE nm1)))
 )

type Context = Stmt -> CompilationUnit
