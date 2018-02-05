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

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = undefined
    , quotePat  = \str ->
        let Right c = parser pat str
        in case c of EP e -> dataToPatQ (const Nothing `extQ` antiExpPat) e
                     SP s -> dataToPatQ (const Nothing `extQ` antiStmtPat) s
    , quoteType = undefined
    , quoteDec  = undefined
    }

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
