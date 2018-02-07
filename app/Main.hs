module Main where

import Lib
import Language.Java.Pretty

-- $(matchs)

main :: IO ()
main = do
  print . map prettyPrint $ teste
  -- print . map prettyPrint $ tests
  -- print $ length tests
  -- print tests
  -- print prog1 
  -- print "pretty print"
  -- putStrLn $ prettyPrint prog1
  -- print . map prettyPrint $ reps prog1 f
