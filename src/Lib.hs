{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE ViewPatterns #-}

module Lib where

import JQQ
import Debug.Trace
import P1
import Data.List
import Data.Data
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

markb :: MethodBody
markb = MethodBody . Just $ Block [BlockStmt $ Labeled (Ident "slothmark") Empty]

mark :: Stmt
mark = Labeled (Ident "slothmark") Empty


grepm :: CompilationUnit ->  (MethodBody -> Bool) -> (CompilationUnit -> Bool) -> [MethodBody]
grepm prog pctnt pctxt = [ a | (a, b) <- contextsBi prog, pctnt a && pctxt (b markb)]

grepj :: CompilationUnit ->  (Stmt -> Bool) -> (CompilationUnit -> Bool) -> [(Stmt, MethodBody)]
grepj prog pctnt pctxt = [ (a, getBody b) | (a, b) <- contextsBi prog, pctnt a && pctxt (b mark)]

getBody :: (Stmt -> CompilationUnit) -> MethodBody
getBody ctxt = head [ m | m <- universeBi (ctxt mark), marked m]

--------------------
-- patterns here ---
--------------------

-- TODO invent special antiquote for bound patterns

p1 :: TH.Q TH.Pat
p1 = [p| [java|
if (this.#x != null) {
 int[] range = (int[]) this.#x.get(node);
 ``p2 }
|] |]

p2 :: TH.Q TH.Pat
p2 = [p| [java| `[ `_ `] |] |]

testm :: CompilationUnit -> [MethodBody]
testm prog = grepm prog pat ctxt
  where pat (MethodBody b) = (not $ labeled b) && nohash b && collects b && hof b && noloop b && notry b
        notry b = null [ undefined | Try _ _ _  <- universeBi b ]
        noloop b = null [ l | l@[java| while (`_) `_ |] <- universeBi b ]
        hof b = not $ null [ m :: MethodInvocation | m <- universeBi b, queries m ]
        queries m = not $ null [ undefined | Ident i <- universeBi m , "get" == i || "size" == i ]
        nohash m = null [ undefined | h <- universeBi m, h == "HashMap" || h == "Map" ]
        collects m = not $ null [ i | i <- universeBi m, any (== i) ["Collection", "List", "Set"] ]
        labeled p = not $ null [ undefined | Labeled (Ident l) _ <- universeBi p, "labeled" `isPrefixOf` l]
        ctxt _ = True

testj :: CompilationUnit -> [(Stmt, MethodBody)]
testj prog = grepj prog pat ctxt
  where pat [java| while (`_) `b |] = noInstance b && noMutate b && not (hash b) && noAddNew b && noArray b
        pat _ = False
        noArray b = null [undefined | ArrayLhs _ <- universeBi b]
        noAddNew x = null [ m :: MethodInvocation | m <- universeBi x, adds m && news m ]
        adds m = not $ null [undefined | "add" <- universeBi m]
        news m = not $ null [undefined | InstanceCreation _ _ _ _ <- universeBi m]
        noMutate b = null [ m :: MethodInvocation | m <- universeBi b, mutates m ]
        mutates m = not $ null [ undefined | Ident i <- universeBi m , "set" `isPrefixOf` i
                                                                       || "remove"`isPrefixOf` i
                                                                       || "put"`isPrefixOf` i
                                                                       || "append" `isPrefixOf` i
                                                                       || "save" `isPrefixOf` i ]
        noInstance b = null [ undefined | InstanceOf _ _ <- universeBi b ]
        ctxt p = (not (labeled p) && nonests p) && (hasCol p)
        nohashMap p = null [ m :: MethodBody | m <- universeBi p, hash m ]
        hash m = not $ null [ undefined | "HashMap" <- universeBi m ]
        hasCol p = not $ null [ m :: MethodBody | m <- universeBi p, nohashMap m && marked m && collects m && nonests m]
        nonests m = null [l | l@[java| while (`_) `s|] <- universeBi m, marked s]
        collects m = not $ null [ i | i <- universeBi m, any (== i) ["Collection", "List", "Set"] ]
        labeled p = not $ null [ undefined | Labeled (Ident l) s <- universeBi p, marked s && "labeled" `isPrefixOf` l]

marked :: Data.Data.Data from => from -> Bool
marked s = not $ null [ undefined | Labeled (Ident "slothmark") Empty <- universeBi s ]

-- /////////////////////
-- //     Patch 1     //
-- /////////////////////
-- //
-- // if (this.#x != null) {
-- //  int[] range = (int[]) this.#x.get(node);
-- //  `[ `_ `] }
-- //
-- /////////////////////
-- //   Patch 2 & 3   //
-- /////////////////////
-- //
-- // if (`*(
-- // (( this.currentCharacter =
-- // this.source [this.currentPosition++])
-- // == '\\')
-- // `)*) `*( (c1 = Character.getNumericValue(`_)) > 15 `)*
-- //
-- /////////////////////
-- //     Patch 4     //
-- /////////////////////
-- //
-- // switch (`_) {
-- //   case SWT.LINE_DOT:
-- //   case SWT.LINE_DASH:
-- //   case SWT.LINE_DASHDOT:
-- //   case SWT.LINE_DASHDOTDOT:
-- //     data.state &= ~LINE_STYLE;
-- // }
-- //
-- /////////////////////
-- //     Patch 5     // 
-- /////////////////////
-- //
-- // if (`*( focusIndex `)*) `*( redraw `)*
-- //
-- /////////////////////
-- //     Patch 6     // 
-- /////////////////////
-- //
-- // if (control instanceof Tree) { `*( TreeDragAndDropEffect `)* } else `_
-- //
-- /////////////////////
-- //     Patch 7     // 
-- /////////////////////
-- //
-- // for (int i = 0; i < digits; i++) adjustment.#x *= 10;
-- //
-- /////////////////////
-- //     Daikon      //
-- /////////////////////
-- //
-- // for (Iterator<`_> #i = `*( iterator `)*; #i.hasNext(); ) { // PATTERN HERE
-- //   `_ #_ = #i.next();
-- //   `[ `! `*( #i `)* `]
-- // }
-- // for (#_<#_> #i = `*( iterator() `)* ; #i.hasNext(); ) {
-- //   #_ #_ = #i.next();
-- //   `[ `! `*( #i `)* `]
-- // }
-- //

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
