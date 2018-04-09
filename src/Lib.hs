{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE ViewPatterns #-}

module Lib where

import JQQ
import Debug.Trace
import P1
import Data.List
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

mark :: MethodBody
mark = MethodBody . Just $ Block [BlockStmt $ Labeled (Ident "slothmark") Empty]

grepj :: CompilationUnit ->  (MethodBody -> Bool) -> (CompilationUnit -> Bool) -> [MethodBody]
grepj prog pctnt pctxt = [ a | (a, b) <- contextsBi prog, pctnt a && pctxt (b mark)]

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

testj :: CompilationUnit -> [MethodBody]
testj prog = grepj prog pat ctxt
  where pat b = hasCol b && noUcf b && ({-hasHOF b ||-} hasLoop b) && (not $ labeled b)
        hasLoop b = not $ null [ s | s@[java| while ( `_ ) `_ |] <- universeBi b, mutates s ]
        hasHOF b = not $ null [ s::Stmt | s <- universeBi b, access s ]
        mutates s = (null [ m | MethodCall m _ <- universeBi s, bad m ])
        access s = (not $ null [ m | MethodCall m _ <- universeBi s, good m ])
                   && (null [ m | MethodCall m _ <- universeBi s, bad m ])
        good (Name m) = (elem (Ident "get") m
                           || elem (Ident "size") m)
        bad (Name m) = any (calls "set")  m
                       || any (calls "remove") m
                       || any (calls "save") m
        calls m (Ident i) = m `isPrefixOf` i
        calls _ _ = False
        hasCol b = not $ null [ c | c <- universeBi b, col c ]
        col (Ident i) = i == "Collection" || i == "Set" || i == "List"
        col _ = False
        noUcf s = null [ undefined | Try _ _ _ <- universeBi s]
        ctxt _ = True
        labeled b =  not $ null [ undefined | Labeled (Ident l) _ <- universeBi b, "labeled" `isPrefixOf` l]
        -- pat res@[java| while ( #i < `_ ) `*( #i++ `| #i += 1  `)* |] = Just res
        -- pat res@[java| while (((`_) #i).hasNext()) `*( ((`_) #i).next() `)* |] = Just res
        -- pat _ = Nothing
        -- f (Just [java| `*( (`_).println() `)* |]) = False
        -- f (Just [java| `*( System.out.#_(`_) `)* |]) = False
        -- f (Just [java| `*( printf(`_, `_) `)* |]) = False
        -- f (Just [java| `*( rand() `)* |]) = False
        -- f (Just [java| `*( Math.random() `)* |]) = False
        -- f (Just [java| `*( IOUtil.#_(`_) `)* |]) = False
        -- f (Just _) = True
        -- f Nothing = False
        -- ctxt p = case [undefined | [java| while (`_) `*( slothmark:; `)* |] <- universeBi p]
        --            of [] -> True
        --               _ -> False

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
