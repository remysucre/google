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
      ms = concatMap ((++ "haha \n") . prettyPrint) $ res
      out = fn ++ "\n" ++ ms ++ (show $ length res) ++ "\n"
  -- print 
  if length res > 0 then putStr out else return ()
  -- print $ length res
  -- print . map prettyPrint $ tests
  -- print $ length tests
  -- print tests
  -- print prog1
  -- print "pretty print"
  -- putStrLn $ prettyPrint prog1
  -- print . map prettyPrint $ reps prog1 f
