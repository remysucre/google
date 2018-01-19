module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Text.Parsec.Pos
import Data.Set (Set)
import qualified Data.Set as Set
import Data.Generics (extQ)

someFunc :: IO ()
someFunc = putStrLn "someFunc"

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = \str ->
        let Right c = parser stmt str
        in dataToExpQ (const Nothing) c
    , quotePat  = \str ->
        let Right c = parser stmt str
        in dataToPatQ (const Nothing `extQ` metaPat (fvStmt c)) c
    , quoteType = undefined
    , quoteDec  = undefined
    }

-- metaPat :: Set String -> Language.Java.Syntax.Stmt -> Maybe PatQ
-- metaPat fvs (ExpStmt (Assign (NameLhs (Name [Ident x])) EqualA (Lit (Int 1)))) | x `Set.member` fvs = Just (varP (mkName x))
metaPat :: Set String -> Ident -> Maybe PatQ
metaPat fvs (Ident x) | x `Set.member` fvs = Just (varP (mkName x))
metaPat _ _ = Nothing

fvStmt :: Language.Java.Syntax.Stmt -> Set String
fvStmt (ExpStmt (Assign (NameLhs (Name [Ident x])) EqualA (Lit (Int 1)))) = Set.singleton x
fvStmt _ = Set.empty

location' :: Q SourcePos
location' = aux <$> location
  where
    aux :: Loc -> SourcePos
    aux loc = uncurry (newPos (loc_filename loc)) (loc_start loc)

