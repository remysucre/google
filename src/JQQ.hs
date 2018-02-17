{-# Language ViewPatterns, TemplateHaskell #-}

module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Data.Generics (extQ)
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
            expand (EP e) = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` enot `extQ` snot) e
            expand (SP s) = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` enot `extQ` snot) s
        in expand c
    , quoteType = undefined
    , quoteDec  = undefined
    }

enot :: Language.Java.Syntax.Exp -> Maybe (Q Language.Haskell.TH.Pat)
enot (ENot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [e| False |]) []
        c2 = match wildP ( normalB [e| True |]) []
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` snot `extQ` enot) p
enot _ = Nothing


snot :: Language.Java.Syntax.Stmt -> Maybe (Q Language.Haskell.TH.Pat)
snot (SNot p) = Just (viewP (lamCaseE [c1, c2]) [p|True|])
  where c1 = match p_ ( normalB [e| False |]) []
        c2 = match wildP ( normalB [e| True |]) []
        p_ = dataToPatQ (const Nothing `extQ` antiExpPat `extQ` antiStmtPat `extQ` snot `extQ` enot) p
snot _ = Nothing

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
