{-# Language ViewPatterns, TemplateHaskell, FlexibleContexts #-}

module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Data.Generics (extQ)
import Data.Generics.Uniplate.Data
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
        let Right c = traceShowId $ parser pat str
        in case c of (EP e) -> dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) e
                     (SP s) -> dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` antiVar) (evalState (rename s) DS.empty)
    , quoteType = undefined
    , quoteDec  = undefined
    }


rename :: Language.Java.Syntax.Stmt -> State (DS.Set String) Language.Java.Syntax.Stmt
rename p = transformM rnvar p
  where rnvar (MetaStmt n) = do s <- get
                                let res = if DS.member n s then (SAssertEq n) else (MetaStmt n)
                                put (DS.insert n s)
                                return res
        rnvar x = return x

-- quoting java expressions

jexp :: QuasiQuoter
jexp = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser (Language.Java.Parser.exp <* eof) str
        in dataToPatQ (const Nothing `extQ` antiStmtPat `extQ` antiExpPat) c
    , quoteType = undefined
    , quoteDec  = undefined
    }

antiVar :: Language.Java.Syntax.Lhs -> Maybe (Q Language.Haskell.TH.Pat)
antiVar (MetaVar s) = Just $ varP (mkName s)
antiVar _ = Nothing

antiExpPat :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
antiExpPat (MetaExp s) = Just $ varP (mkName s)
antiExpPat (ENot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [e| False |]) []
        c2 = match wildP ( normalB [e| True |]) []
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
antiExpPat (EHasS p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
antiExpPat (EHasE p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
antiExpPat _ = Nothing

-- quoting java statements

jstmt :: QuasiQuoter
jstmt = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser (stmt <* eof) str
        in dataToPatQ (const Nothing `extQ` antiStmtPat `extQ` antiExpPat) c
    , quoteType = undefined
    , quoteDec  = undefined
    }

antiStmtPat :: Language.Java.Syntax.Stmt -> Maybe (Q Language.Haskell.TH.Pat)
antiStmtPat (MetaStmt s) = Just $ varP (mkName s)
antiStmtPat (SAssertEq s) = Just $ viewP [|(== $(varE . mkName $ s))|] [p|True|]
antiStmtPat (StmtBlock (Block [BlockStmt h, BlockStmt (Seq pseq)])) = Just p
  {- [p| StmtBlock (Block (p: (\ns -> all (\case {ps -> True; _ -> False}) ns)))|]-}
  where p = conP (mkName "StmtBlock") [conP (mkName "Block") [ infixP (conP (mkName "BlockStmt") [h_]) (mkName ":") ps_]]
        h_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) h
        ps_ = [p|((\ns -> all $(matchps) ns) -> True)|]
        matchps = lamCaseE [c1, c2]
        c1 = match (conP (mkName "BlockStmt") [pseq_]) (normalB [|True|]) []
        c2 = match wildP (normalB [|False|]) []
        pseq_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) pseq
antiStmtPat (SNot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [| False |]) []
        c2 = match wildP ( normalB [| True |]) []
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
antiStmtPat (SHasS p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
antiStmtPat (SHasE p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
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
