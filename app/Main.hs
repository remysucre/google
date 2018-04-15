module Main where

import Lib
import Language.Java.Pretty
import Language.Java.Syntax
import Language.Java.Parser
import System.Environment
import Debug.Trace

-- $(matchs)

main :: IO ()
main = do
  -- print . map prettyPrint $ teste
  [fn] <- getArgs
  fc <- readFile fn
  let java = case parser compilationUnit fc
               of Right pt -> pt
                  _ -> trace fn $ CompilationUnit Nothing [] []
      resb = testm java
      resl = testj java
      -- ms = concatMap (\x -> prettyPrint (fst x) ++ prettyPrint (snd x) ++ "haha \n" ) $ res
      bs = concatMap (\x -> prettyPrint x ++ "haha \n" ) $ resb
      ls = concatMap (\x -> prettyPrint (fst x) ++ prettyPrint (snd x) ++ "haha \n" ) $ resl
      out = fn ++ "\n" ++ ls ++ bs ++ "matches" ++ (show (length resb + length resl))++ "\n"
  if length resl + length resb > 0 then putStr out else return ()
  -- print $ length res
  -- print . map prettyPrint $ tests
  -- print $ length tests
  -- print tests
  -- print prog1
  -- print "pretty print"
  -- putStrLn $ prettyPrint prog1
  -- print . map prettyPrint $ reps prog1 f
