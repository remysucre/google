{-# LANGUAGE QuasiQuotes #-}

module Lib where

import JQQ 
import Language.Java.Syntax

prog1 :: Language.Java.Syntax.Stmt
prog1 = [java| barInt1 = 1; |]

pull :: Language.Java.Syntax.Stmt -> Ident
pull [java| x = 1; |] = x
pull _ = Ident "noh"
