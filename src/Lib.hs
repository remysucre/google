{-# LANGUAGE QuasiQuotes #-}

module Lib
    ( someFunc
    ) where

import JQQ 

prog1 :: Language.Java.Syntax.Stmt
prog1 = [java| barInt1 = 1; |]
