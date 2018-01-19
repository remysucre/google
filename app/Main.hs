module Main where

import Lib
import Language.Java.Pretty

main :: IO ()
main = do
  print "AST"
  print prog1
  print "pretty print"
  putStrLn $ prettyPrint prog1
