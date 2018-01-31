module Main where

import Lib
import Language.Java.Pretty

$(matchs)

main :: IO ()
main = do
  -- print "AST"
  -- print prog1
  -- print "pretty print"
  -- putStrLn $ prettyPrint prog1
  print . map prettyPrint $ reps prog1 f
