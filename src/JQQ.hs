{-# Language ViewPatterns, TemplateHaskell #-}

module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Syntax (showName)
import Language.Haskell.TH.Quote
import Data.Generics (extQ)
import Data.Generics.Uniplate.Data
import Debug.Trace
import Text.Parsec.Combinator

-- general java patterns. in a minimal implementation, this is the only
-- necessary part

-- TODO probably wont work if pnot is nested

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = traceShowId $ parser pat str
            expand (EP e) = do
              p0 <- dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) e
              let p1 = rename p0
              return p0
            expand (SP s) = do
              p0 <- dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) s
              let p1 = rename p0
              return p0
        in expand c
    , quoteType = undefined
    , quoteDec  = undefined
    }


rename :: Language.Haskell.TH.Pat -> Language.Haskell.TH.Pat
rename p = transformBi rnvar p
  where rnvar (VarP n) = (ViewP (AppE (VarE $ mkName "==") (VarE . mkName . showName $ n)) (ConP (mkName "True") []))-- [p|((== n) -> True)|]-- (viewP (== x) [p|True|])
        rnvar x = x

shass :: Language.Java.Syntax.Stmt -> Maybe (Q Language.Haskell.TH.Pat)
shass (SHasS p) = Just [p| ((\n -> $(body)) -> _:_) |] -- TODO watch out for n
  where body = compE [bindS p_ [|universe n|], noBindS [|undefined|]] -- TODO undefined is never evaluated
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat) p
shass _ = Nothing

shase :: Language.Java.Syntax.Stmt -> Maybe (Q Language.Haskell.TH.Pat)
shase = undefined

ehass :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
ehass = undefined

ehase :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
ehase = undefined

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

antiExpPat :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
antiExpPat (MetaExp s) = Just $ varP (mkName s)
antiExpPat (ENot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [e| False |]) []
        c2 = match wildP ( normalB [e| True |]) []
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
