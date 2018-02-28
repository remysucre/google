module Main where

import Lib
import Language.Java.Pretty
import Language.Java.Parser
import System.Environment

-- $(matchs)

main :: IO ()
main = do
  -- print . map prettyPrint $ teste
  [fn] <- getArgs
  fc <- readFile fn
  let Right java = parser compilationUnit fc
      res = testj java
  putStr . concatMap ((++ "haha \n") . prettyPrint) $ res
  -- print 
  print $ length res
  -- print . map prettyPrint $ tests
  -- print $ length tests
  -- print tests
  -- print prog1
  -- print "pretty print"
  -- putStrLn $ prettyPrint prog1
  -- print . map prettyPrint $ reps prog1 f
