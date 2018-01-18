module JQQ where

import Language.Java.Parser
import Language.Java.Syntax
import Language.Haskell.TH
import Language.Haskell.TH.Quote
import Text.Parsec.Pos

someFunc :: IO ()
someFunc = putStrLn "someFunc"

java :: QuasiQuoter
java = QuasiQuoter {
      quoteExp = \str -> do
        l <- location'
        Left c <- parser (setPosition l *> topLevel stmt) str
        dataToExpQ (const Nothing) c
    , quotePat  = undefined
    , quoteType = undefined
    , quoteDec  = undefined
    }

location' :: Q SourcePos
location' = aux <$> location
  where
    aux :: Loc -> SourcePos
    aux loc = uncurry (newPos (loc_filename loc)) (loc_start loc)

