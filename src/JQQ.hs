module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Text.Parsec.Pos
import Data.Set (Set)
import qualified Data.Set as Set
import Data.Generics (extQ)

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = \str ->
        let Right c = parser compilationUnit str
        in dataToExpQ (const Nothing) c
    , quotePat  = \str ->
        let Right c = parser stmt str
        in dataToPatQ (const Nothing `extQ` antiExprPat `extQ` antiStmtPat) c
    , quoteType = undefined
    , quoteDec  = undefined
    }

antiExprPat :: Ident -> Maybe (Q Pat)
antiExprPat (EMetaVar v) = Just $ varP (mkName v)
antiExprPat _ = Nothing

antiStmtPat :: Language.Java.Syntax.Stmt -> Maybe (Q Pat)
antiStmtPat (EMetaStmt s) = Just $ varP (mkName s)
antiStmtPat _ = Nothing

-- metaPat :: Set String -> Ident -> Maybe PatQ
-- metaPat fvs (Ident x) | x `Set.member` fvs = Just (varP (mkName x))
-- metaPat _ _ = Nothing
--
-- fvStmt :: Language.Java.Syntax.Stmt -> Set String
-- fvStmt (ExpStmt (Assign (NameLhs (Name [Ident x])) EqualA (Lit (Int 1)))) = Set.singleton x
-- fvStmt _ = Set.empty
