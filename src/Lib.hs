{-# LANGUAGE QuasiQuotes #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE ViewPatterns #-}

module Lib where

import JQQ
import Language.Java.Syntax
import Data.Generics.Uniplate.Data
import qualified Language.Haskell.TH as TH
import qualified Language.Haskell.TH.Lib as TL

--------------------
-- search engine ---
--------------------

grepe :: CompilationUnit -> (Exp -> Bool) -> [Exp]
grepe prog pctnt = [ a | a <- universeBi prog, pctnt a]

greps :: CompilationUnit -> (Stmt -> Bool) -> [Stmt]
greps prog pctnt = [ a | a <- universeBi prog, pctnt a]

grepj :: CompilationUnit -> (Stmt -> Bool) -> [Stmt]
grepj prog pctnt = [ a | a <- universeBi prog, pctnt a]

-- TODO transformation engine

--------------------
-- patterns here ---
--------------------
testj :: [Stmt]
testj = grepj prog1 pat
  where -- pat [java| while (1) { x = 9 + 9; `[ x = 9 + 9; `]} |] = True
        pat [java| { `x while (1) { `x `x } } |] = True
        pat [java| x = 9; |] = True
        pat _ = False

teste :: [Exp]
teste = grepe prog1 pat
  where pat [jexp| `_ != null |] = True -- <- NOTE: add your patter here
        pat [jexp| x + `y |] = True
        pat [jexp| `x + `y |] = True
        pat [jexp| `x |] = True
        pat _ = False

{-
tests :: [Stmt]
tests = greps prog1 pat
  where pat [jstmt|
                if (this.leadingComments != null) {
                        int[] range = `x;
                        `y
                        `n } |] = True
        pat _ = False
-}
-- TODO Pat test

--------------------
-- program source --
--------------------

prog1 :: CompilationUnit
prog1 = [jprog|
public class HelloWorld
{
        public static void main(String[] args) {
                System.out.println("Hello World!");
                while (1) { x = 9; };
                while (1) { x = 9 + 9; x = 9 + 8; };
                while (1) { x = 9 + 9; while (1) { x = 9 + 9; x = 9 + 9; } };
                while (1) { x++; };
        }
}|]

prog2 :: CompilationUnit
prog2 = [jprog|
/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.core.dom;

import java.util.HashMap;

import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.internal.compiler.parser.Scanner;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;

/**
 * Internal class for associating comments with AST nodes.
 *
 * @since 3.0
 */
class DefaultCommentMapper {
        Comment[] comments;
        HashMap leadingComments;
        HashMap trailingComments;

        Scanner scanner;

        /**
         * @param table the given table of comments
         */
        DefaultCommentMapper(Comment[] table) {
                this.comments = table;
        }

        boolean hasSameTable(Comment[] table) {
                return this.comments == table;
        }

        /**
         * Get comment of the list which includes a given position
         *
         * @param position The position belonging to the looked up comment
         * @return comment which includes the given position or null if none was found
         */
        Comment getComment(int position) {

                if (this.comments == null) {
                        return null;
                }
                int size = this.comments.length;
                if (size == 0) {
                        return null;
                }
                int index = getCommentIndex(0, position, 0);
                if (index<0) {
                        return null;
                }
                return this.comments[index];
        }

        /*
         * Get the index of comment which contains given position.
         * If there's no matching comment, then return depends on exact parameter:
         *		= 0: return -1
         *		< 0: return index of the comment before the given position
         *		> 0: return index of the comment after the given position
         */
        private int getCommentIndex(int start, int position, int exact) {
                if (position == 0) {
                        if (this.comments.length > 0 && this.comments[0].getStartPosition() == 0) {
                                return 0;
                        }
                        return -1;
                }
                int bottom = start, top = this.comments.length - 1;
                int i = 0, index = -1;
                Comment comment = null;
                while (bottom <= top) {
                        i = (bottom + top) /2;
                        comment = this.comments[i];
                        int commentStart = comment.getStartPosition();
                        if (position < commentStart) {
                                top = i-1;
                        } else if (position >=(commentStart+comment.getLength())) {
                                bottom = i+1;
                        } else {
                                index = i;
                                break;
                        }
                }
                if (index<0 && exact!=0) {
                        comment = this.comments[i];
                        if (position < comment.getStartPosition()) {
                                return exact<0 ? i-1 : i;
                        } else {
                                return exact<0 ? i : i+1;
                        }
                }
                return index;
        }

        /**
         * Return all leading comments of a given node.
         * @param node
         * @return an array of Comment or null if there's no leading comment
         */
        Comment[] getLeadingComments(ASTNode node) {
                if (this.leadingComments != null) {
                        int[] range = (int[]) this.leadingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] leadComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], leadComments, 0, length);
                                return  leadComments;
                        }
                }
                return null;
        }

        Comment[] getLeadingCommentsAnomaly2(ASTNode node) {
                if (this.leadingComments != null) {
                        int[] range = (int[]) this.leadingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] leadComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], leadComments, 0, length);
                                return  leadComments;
                        }
                }
                return null;
        }

        Comment[] getLeadingCommentsAnomaly3(ASTNode node) {
                if (this.leadingComments != null) {
                        int[] range = (int[]) this.leadingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] leadComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], leadComments, 0, length);
                                return  leadComments;
                        }
                }
                return null;
        }

        /**
         * Return all trailing comments of a given node.
         * @param node
         * @return an array of Comment or null if there's no trailing comment
         */
        Comment[] getTrailingComments(ASTNode node) {
                if (this.trailingComments != null) {
                        int[] range = (int[]) this.trailingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] trailComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], trailComments, 0, length);
                                return  trailComments;
                        }
                }
                return null;
        }

        /**
         * Returns the extended start position of the given node. Unlike
         * {@link ASTNode#getStartPosition()} and {@link ASTNode#getLength()},
         * the extended source range may include comments and whitespace
         * immediately before or after the normal source range for the node.
         *
         * @param node the node
         * @return the 0-based character index, or <code>-1</code>
         *    if no source position information is recorded for this node
         * @see #getExtendedLength(ASTNode)
         * @since 3.0
         */
        public int getExtendedStartPosition(ASTNode node) {
                if (this.leadingComments != null) {
                        int[] range = (int[]) this.leadingComments.get(node);
                        foo();
                        if (range != null) {
                                return  this.comments[range[0]].getStartPosition() ;
                        }
                }
                return node.getStartPosition();
        }

        /*
         * Returns the extended end position of the given node.
         */
        public int getExtendedEnd(ASTNode node) {
                int end = node.getStartPosition() + node.getLength();
                if (this.trailingComments != null) {
                        int[] range = (int[]) this.trailingComments.get(node);
                        foo();
                        if (range != null) {
                                if (range[0] == -1 && range[1] == -1) {
                                        ASTNode parent = node.getParent();
                                        if (parent != null && ((parent.getFlags() & ASTNode.ORIGINAL) != 0)) {
                                                return getExtendedEnd(parent);
                                        }
                                } else {
                                        Comment lastComment = this.comments[range[1]];
                                        end = lastComment.getStartPosition() + lastComment.getLength();
                                }
                        }
                }
                return end-1;
        }

        /**
         * Returns the extended source length of the given node. Unlike
         * {@link ASTNode#getStartPosition()} and {@link ASTNode#getLength()},
         * the extended source range may include comments and whitespace
         * immediately before or after the normal source range for the node.
         *
         * @param node the node
         * @return a (possibly 0) length, or <code>0</code>
         *    if no source position information is recorded for this node
         * @see #getExtendedStartPosition(ASTNode)
         * @see #getExtendedEnd(ASTNode)
         * @since 3.0
         */
        public int getExtendedLength(ASTNode node) {
                return getExtendedEnd(node) - getExtendedStartPosition(node) + 1;
        }

        /*
         * Initialize leading and trailing comments tables in whole nodes hierarchy of a compilation
         * unit.
         * Scanner is necessary to scan between nodes and comments and verify if there's
         * nothing else than white spaces.
         */
        void initialize(CompilationUnit unit, Scanner sc) {

                // Init comments
                this.comments = unit.optionalCommentTable;
                if (this.comments == null) {
                        return;
                }
                int size = this.comments.length;
                if (size == 0) {
                        return;
                }

                // Init tables
                this.leadingComments = new HashMap();
                this.trailingComments = new HashMap();

                // Init scanner and start ranges computing
                this.scanner = sc;
                this.scanner.tokenizeWhiteSpace = true;

                // Start unit visit
                DefaultASTVisitor commentVisitor = new CommentMapperVisitor();
                unit.accept(commentVisitor);
        }

        /**
         * Search and store node leading comments. Comments are searched in position range
         * from previous extended position to node start position. If one or several comment are found,
         * returns first comment start position, otherwise returns node start position.
         *
         * First look after first comment before node start position and return if none was found...
         *
         * When first comment was found before node, goes up in comment list until one of
         * following condition becomes true:
         *      1) comment end is before previous end
         *      2) comment start and previous end is on the same line but not on same line of node start
         *      3) there's other than white characters between current node and comment
         *      4) there's more than 1 line between current node and comment
         *
         * If at least one potential comment has been found, then no token should be on
         * on the same line before, so remove all comments which do not verify this assumption.
         *
         * If finally there is a subset of comments, then store start and end indexes
         * in leading comments table.
         */
        int storeLeadingComments(ASTNode node, int previousEnd) {
                // Init extended position
                int nodeStart = node.getStartPosition();
                int extended = nodeStart;

                // Get line of node start position
                int previousEndLine = this.scanner.getLineNumber(previousEnd);
                int nodeStartLine = this.scanner.getLineNumber(nodeStart);

                // Find first comment index
                int idx = getCommentIndex(0, nodeStart, -1);
                if (idx == -1) {
                        return nodeStart;
                }

                // Look after potential comments
                int startIdx = -1;
                int endIdx = idx;
                int previousStart = nodeStart;
                while (idx >= 0 && previousStart  >= previousEnd) {
                        // Verify for each comment that there's only white spaces between end and start of {following comment|node}
                        Comment comment = this.comments[idx];
                        int commentStart = comment.getStartPosition();
                        int end = commentStart+comment.getLength()-1;
                        int commentLine = this.scanner.getLineNumber(commentStart);
                        if (end <= previousEnd || (commentLine == previousEndLine && commentLine != nodeStartLine)) {
                                // stop search on condition 1) and 2)
                                break;
                        } else if ((end+1) < previousStart) { // may be equals => then no scan is necessary
                                this.scanner.resetTo(end+1, previousStart);
                                try {
                                        int token = this.scanner.getNextToken();
                                        if (token != TerminalTokens.TokenNameWHITESPACE || this.scanner.currentPosition != previousStart) {
                                                // stop search on condition 3)
                                                // if first comment fails, then there's no extended position in fact
                                                if (idx == endIdx) {
                                                        return nodeStart;
                                                }
                                                break;
                                        }
                                } catch (InvalidInputException e) {
                                        // Should not happen, but return no extended position...
                                        return nodeStart;
                                }
                                // verify that there's no more than one line between node/comments
                                char[] gap = this.scanner.getCurrentIdentifierSource();
                                int nbrLine = 0;
                                int pos = -1;
                                while ((pos=CharOperation.indexOf('\n', gap,pos+1)) >= 0) {
                                        nbrLine++;
                                }
                                if (nbrLine > 1) {
                                        // stop search on condition 4)
                                        break;
                                }
                        }
                        // Store previous infos
                        previousStart = commentStart;
                        startIdx = idx--;
                }
                if (startIdx != -1) {
                        // Verify that there's no token on the same line before first leading comment
                        int commentStart = this.comments[startIdx].getStartPosition();
                        if (previousEnd < commentStart && previousEndLine != nodeStartLine) {
                                int lastTokenEnd = previousEnd;
                                this.scanner.resetTo(previousEnd, commentStart);
                                try {
                                        while (this.scanner.currentPosition < commentStart) {
                                                if (this.scanner.getNextToken() != TerminalTokens.TokenNameWHITESPACE) {
                                                        lastTokenEnd =  this.scanner.getCurrentTokenEndPosition();
                                                }
                                        }
                                } catch (InvalidInputException e) {
                                        // do nothing
                                }
                                int lastTokenLine = this.scanner.getLineNumber(lastTokenEnd);
                                int length = this.comments.length;
                                while (startIdx<length && lastTokenLine == this.scanner.getLineNumber(this.comments[startIdx].getStartPosition()) && nodeStartLine != lastTokenLine) {
                                        startIdx++;
                                }
                        }
                        // Store leading comments indexes
                        if (startIdx <= endIdx) {
                                this.leadingComments.put(node, new int[] { startIdx, endIdx });
                                extended = this.comments[endIdx].getStartPosition();
                        }
                }
                return extended;
        }

        /**
         * Search and store node trailing comments. Comments are searched in position range
         * from node end position to specified next start. If one or several comment are found,
         * returns last comment end position, otherwise returns node end position.
         *
         * First look after first comment after node end position and return if none was found...
         *
         * When first comment was found after node, goes down in comment list until one of
         * following condition becomes true:
         *      1) comment start is after next start
         *      2) there's other than white characters between current node and comment
         *      3) there's more than 1 line between current node and comment
         *
         * If at least one potential comment has been found, then all of them has to be separated
         * from following node. So, remove all comments which do not verify this assumption.
         * Note that this verification is not applicable on last node.
         *
         * If finally there is a subset of comments, then store start and end indexes
         * in trailing comments table.
         */
        int storeTrailingComments(ASTNode node, int nextStart,  boolean lastChild) {

                // Init extended position
                int nodeEnd = node.getStartPosition()+node.getLength()-1;
                if (nodeEnd == nextStart) {
                        // special case for last child of its parent
                        this.trailingComments.put(node, new int[] { -1, -1 });
                        return nodeEnd;
                }
                int extended = nodeEnd;

                // Get line number
                int nodeEndLine = this.scanner.getLineNumber(nodeEnd);

                // Find comments range index
                int idx = getCommentIndex(0, nodeEnd, 1);
                if (idx == -1) {
                        return nodeEnd;
                }

                // Look after potential comments
                int startIdx = idx;
                int endIdx = -1;
                int length = this.comments.length;
                int commentStart = extended+1;
                int previousEnd = nodeEnd+1;
                int sameLineIdx = -1;
                while (idx<length && commentStart < nextStart) {
                        // get comment and leave if next starting position has been reached
                        Comment comment = this.comments[idx];
                        commentStart = comment.getStartPosition();
                        // verify that there's nothing else than white spaces between node/comments
                        if (commentStart >= nextStart) {
                                // stop search on condition 1)
                                break;
                        } else if (previousEnd < commentStart) {
                                this.scanner.resetTo(previousEnd, commentStart);
                                try {
                                        int token = this.scanner.getNextToken();
                                        if (token != TerminalTokens.TokenNameWHITESPACE || this.scanner.currentPosition != commentStart) {
                                                // stop search on condition 2)
                                                // if first index fails, then there's no extended position in fact...
                                                if (idx == startIdx) {
                                                        return nodeEnd;
                                                }
                                                // otherwise we get the last index of trailing comment => break
                                                break;
                                        }
                                } catch (InvalidInputException e) {
                                        // Should not happen, but return no extended position...
                                        return nodeEnd;
                                }
                                // verify that there's no more than one line between node/comments
                                char[] gap = this.scanner.getCurrentIdentifierSource();
                                int nbrLine = 0;
                                int pos = -1;
                                while ((pos=CharOperation.indexOf('\n', gap,pos+1)) >= 0) {
                                        nbrLine++;
                                }
                                if (nbrLine > 1) {
                                        // stop search on condition 3)
                                        break;
                                }
                        }
                        // Store index if we're on the same line than node end
                        int commentLine = this.scanner.getLineNumber(commentStart);
                        if (commentLine == nodeEndLine) {
                                sameLineIdx = idx;
                        }
                        // Store previous infos
                        previousEnd = commentStart+comment.getLength();
                        endIdx = idx++;
                }
                if (endIdx != -1) {
                        // Verify that following node start is separated
                        if (!lastChild) {
                                int nextLine = this.scanner.getLineNumber(nextStart);
                                int previousLine = this.scanner.getLineNumber(previousEnd);
                                if((nextLine - previousLine) <= 1) {
                                        if (sameLineIdx == -1) return nodeEnd;
                                        endIdx = sameLineIdx;
                                }
                        }
                        // Store trailing comments indexes
                        this.trailingComments.put(node, new int[] { startIdx, endIdx });
                        extended = this.comments[endIdx].getStartPosition()+this.comments[endIdx].getLength()-1;
                }
                return extended;
        }


        /**
         * Return a copy of the leading comment for ast node
         * @param node
         * @return
         */
        Comment[] getLeadingCommentsCopy(ASTNode node) {
                if (this.leadingComments != null) {
                        int[] range = (int[]) this.leadingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] leadComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], leadComments, 0, length);
                                return  (Comment[]) leadComments.clone();
                        }
                }
                return null;
        }

        /**
         * Return a copy of trailing comment for the ast node
         * @param node
         * @return
         */
        Comment[] getTrailingCommentsCopy(ASTNode node){
                if (this.trailingComments != null) {
                        int[] range = (int[]) this.trailingComments.get(node);
                        foo();
                        if (range != null) {
                                int length = range[1]-range[0]+1;
                                Comment[] trailComments = new Comment[length];
                                System.arraycopy(this.comments, range[0], trailComments, 0, length);
                                return  (Comment[]) trailComments.clone();
                        }
                }
                return null;
        }


        class CommentMapperVisitor extends DefaultASTVisitor {

                HashMap waitingSiblings = new HashMap(10);

                protected boolean visitNode(ASTNode node) {

                        // Get default previous end
                        ASTNode parent = node.getParent();
                        int previousEnd = parent.getStartPosition();

                        // Look for sibling node
                        ASTNode sibling = (ASTNode) this.waitingSiblings.get(parent);
                        if (sibling != null) {
                                // Found one previous sibling, so compute its trailing comments using current node start position
                                try {
                                        previousEnd = storeTrailingComments(sibling, node.getStartPosition(), false);
                                } catch (Exception ex) {
                                        // Give up extended ranges at this level if unexpected exception happens...
                                }
                        }

                        // Compute leading comments for current node
                        try {
                                storeLeadingComments(node, previousEnd);
                        } catch (Exception ex) {
                                // Give up extended ranges at this level if unexpected exception happens...
                        }

                        // Store current node as waiting sibling for its parent
                        this.waitingSiblings.put(parent, node);

                        // We're always ok to visit sub-levels
                        return true;
                }

                protected void endVisitNode(ASTNode node) {

                        // Look if a child node is waiting for trailing comments computing
                        ASTNode sibling = (ASTNode) this.waitingSiblings.get(node);
                        if (sibling != null) {
                                try {
                                        storeTrailingComments(sibling, node.getStartPosition()+node.getLength()-1, true);
                                } catch (Exception ex) {
                                        // Give up extended ranges at this level if unexpected exception happens...
                                }
                        }
                }

                public boolean visit ( CompilationUnit node) {
                        // do nothing special, just go down in sub-levels
                        return true;
                }
        }

        public void foo(){

        }

        public void bar(){

        }

        public void wrongMethod(){

        }
}

|]

--------------------
-- stuff dont look -
--------------------

pstmt :: TH.Q TH.Pat
pstmt = [p| [java| w + 9 |] |]

pexp :: TH.Q TH.Pat
pexp = [p| [jexp| x + 9|] |]

w :: TH.Q TH.Pat
w = TL.wildP

-- generate haskell code

matchs :: TH.Q [TH.Dec]
matchs = [d|f s = case s of {[jexp| `x + `_ |] -> True; _ -> False}|]

-- GREP
-- just like linux grep, this function takes a program (prog) and a pattern (match)
-- and returns a list of statements each of which matches the pattern

grep :: [Stmt]
grep = [r | r@[java| if (1) {}; |] <- universeBi prog1]

erep :: [Exp]
erep = [e | e@[java| 9 |] <- universeBi prog1]

srep :: CompilationUnit -> (Stmt -> Bool) -> ((Stmt -> CompilationUnit) -> Bool) -> [(Stmt, Stmt -> CompilationUnit)]
srep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

jrep :: CompilationUnit -> (Exp -> Bool) -> ((Exp -> CompilationUnit) -> Bool) -> [(Exp, Exp -> CompilationUnit)]
jrep prog pctnt pctxt = [ r | r@(a, b) <- contextsBi prog, pctnt a && pctxt b]

g = $(do
  nm1 <- TH.newName "x"
  let nm2 = TH.mkName "x"
  return (TH.LamE [TH.VarP nm1] (TH.LamE [TH.VarP nm2] (TH.VarE nm1)))
 )

type Context = Stmt -> CompilationUnit
