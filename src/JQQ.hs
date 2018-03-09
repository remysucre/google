{-# Language ViewPatterns, TemplateHaskell, FlexibleContexts #-}

module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Data.Generics (extQ)
import Data.Generics.Uniplate.Data
import Data.Data
import Debug.Trace
import Text.Parsec.Combinator
import Control.Monad.State.Lazy
import qualified Data.Set as DS

-- general java patterns. in a minimal implementation, this is the only
-- necessary part

-- TODO probably wont work if pnot is nested

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser (pat <* eof) str
        in case c of (EP e) -> dataToPatQ exts e
                     (SP s) -> dataToPatQ exts (evalState ((rename s) >>= reid) DS.empty)
    , quoteType = undefined
    , quoteDec  = undefined
    }

exts :: Data b => b -> Maybe (Q Language.Haskell.TH.Pat)
exts = const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` antiId `extQ` antiVar `extQ` antiType

rename :: Language.Java.Syntax.Stmt -> State (DS.Set String) Language.Java.Syntax.Stmt
rename p = transformM rnvar p
  where rnvar x@(MetaStmt "_") = return x
        rnvar (MetaStmt n) =
          do s <- get
             let res = if DS.member n s then (SAssertEq n) else (MetaStmt n)
             put (DS.insert n s)
             return res
        rnvar x = return x

reid :: Language.Java.Syntax.Stmt -> State (DS.Set String) Language.Java.Syntax.Stmt
reid p = transformBiM rnvar p
  where rnvar x@(MetaId "_") = return x
        rnvar (MetaId n) =
          do s <- get
             let res = if DS.member n s then (IAssertEq n) else (MetaId n)
             put (DS.insert n s)
             return res
        rnvar x = return x

-- quoting java expressions

jexp :: QuasiQuoter
jexp = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser (Language.Java.Parser.exp <* eof) str
        in dataToPatQ exts c
    , quoteType = undefined
    , quoteDec  = undefined
    }

antiId :: Language.Java.Syntax.Ident -> Maybe (Q Language.Haskell.TH.Pat)
antiId (MetaId "_") = Just $ wildP
antiId (MetaId s) = Just $ varP (mkName s)
antiId (IAssertEq s) = Just $ viewP [|(== $(varE . mkName $ s))|] [p|True|]
antiId _ = Nothing

antiVar :: Language.Java.Syntax.Lhs -> Maybe (Q Language.Haskell.TH.Pat)
antiVar (MetaVar "_") = Just $ wildP
antiVar (MetaVar s) = Just $ varP (mkName s)
antiVar _ = Nothing

antiType :: Language.Java.Syntax.Type -> Maybe (Q Language.Haskell.TH.Pat)
antiType MetaType = Just $ wildP
antiType _ = Nothing

antiExpPat :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
antiExpPat (MetaExp "_") = Just $ wildP
antiExpPat (MetaExp s) = Just $ varP (mkName s)
antiExpPat (ENot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [e| False |]) []
        c2 = match wildP ( normalB [e| True |]) []
        p_ = dataToPatQ exts p
antiExpPat (EHasS p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiExpPat (EHasE p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiExpPat (EHasI p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universeBi n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiExpPat _ = Nothing

-- quoting java statements

jstmt :: QuasiQuoter
jstmt = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser (stmt <* eof) str
        in dataToPatQ exts c
    , quoteType = undefined
    , quoteDec  = undefined
    }

antiStmtPat :: Language.Java.Syntax.Stmt -> Maybe (Q Language.Haskell.TH.Pat)
antiStmtPat (MetaStmt "_") = Just $ wildP
antiStmtPat (MetaStmt s) = Just $ varP (mkName s)
antiStmtPat (SAssertEq s) = Just $ viewP [|(== $(varE . mkName $ s))|] [p|True|]
antiStmtPat (StmtBlock (Block [h, BlockStmt (Seq pseq)])) = Just p
  {- [p| StmtBlock (Block (p: (\ns -> all (\case {ps -> True; _ -> False}) ns)))|]-}
  where p = conP (mkName "StmtBlock") [conP (mkName "Block") [ infixP h_ (mkName ":") ps_]]
        h_ = dataToPatQ exts h
        ps_ = [p|((\ns -> all $(matchps) ns) -> True)|]
        matchps = lamCaseE [c1, c2]
        c1 = match (conP (mkName "BlockStmt") [pseq_]) (normalB [|True|]) [] -- TODO come here if things break
        c2 = match wildP (normalB [|False|]) []
        pseq_ = dataToPatQ exts pseq
antiStmtPat (SNot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [| False |]) []
        c2 = match wildP ( normalB [| True |]) []
        p_ = dataToPatQ exts p
antiStmtPat (SHasS p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiStmtPat (SHasE p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universeBi n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiStmtPat (SHasI p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universeBi n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ exts p
antiStmtPat _ = Nothing

-- this pattern is just for easily making programs

jprog :: QuasiQuoter
jprog = QuasiQuoter {
      quoteExp = \str ->
        let Right c = parser compilationUnit str
        in dataToExpQ (const Nothing) c
    , quotePat  = undefined
    , quoteType = undefined
    , quoteDec  = undefined
    }
