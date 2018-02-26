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
        -- pat [java| { `x while (1) { `x `x } } |] = True
        -- pat [java| `x = 9; |] = True
        pat [java| for (Iterator<Invariant> i = `_; i.hasNext(); ) {
                     Invariant mine = i.next();
                     if (`_2)
                       return `x;
                   } |] = True
        pat _ = False

teste :: [Exp]
teste = grepe prog1 pat
  where pat [jexp| `_ != null |] = True -- <- NOTE: add your patter here
        pat [jexp| x + `_ |] = True
        pat [jexp| `x + `_ |] = True
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

package daikon;

import daikon.inv.*;
import daikon.suppress.*;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.*;

import utilMDE.*;

/**
 * A Slice is a view of some of the variables for a program point.  A
 * program point (that is, PptTopLevel) does not directly contain
 * invariants.  Instead, slices contain the invariants that involve (all)
 * the Slice's variables.
 * <p>
 * Suppose a program point has variables A, B, C, and D.
 * There would be 4 unary slices -- one each for variables A, B, C, and D.
 * There would be 6 binary slices -- for {A,B}, {A,C}, {A,D}, {B,C}, {B,D},
 * and {C,D}.
 * There would be 4 ternary slices -- for {A,B,C}, {A,B,D}, {A,C,D}, and
 * {B,C,D}.
 **/

public abstract class PptSlice
  extends Ppt
{
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20040921L;

  // Permit subclasses to use it.
  protected static final String lineSep = Global.lineSep;

  /** Debug tracer. **/
  public static final Logger debug = Logger.getLogger("daikon.PptSlice");

  /** Debug tracer for debugging both this and PptSlices. **/
  public static final Logger debugGeneral = Logger.getLogger("daikon.PptSlice.general");
  public static final Logger debugFlow = Logger.getLogger("daikon.flow.flow");

  public static final Logger debugGuarding = Logger.getLogger("daikon.guard");

  // A better name would perhaps be "container", as this has nothing to do
  // with the program point hierarchy.
  /** This is a slice of the 'parent' ppt. */
  public PptTopLevel parent;
  public abstract int arity();

  /**
   * The invariants contained in this slice.
   * This should not be used directly, in general.  In particular,
   * subclasses such as PptSlice0 need to synchronize it with other values.
   * Therefore, it should be manipulated via addInvariant() and
   * removeInvariant().
   **/
  public Invariants invs;

  PptSlice(PptTopLevel parent, VarInfo[] var_infos) {
    this.parent = parent;
    this.var_infos = var_infos;
    // Ensure that the VarInfo objects are in order (and not duplicated).
    for (int i=0; i<var_infos.length-1; i++) {
      Assert.assertTrue(var_infos[i].varinfo_index <= var_infos[i+1].varinfo_index);
    }
    Assert.assertTrue(this instanceof PptSliceEquality || arity() == var_infos.length);
    invs = new Invariants();

    if (debugGeneral.isLoggable(Level.FINE)) {
      debugGeneral.fine (ArraysMDE.toString(var_infos));
    }
  }

  /** Trim the collections used in this PptSlice. **/
  public void trimToSize() {
    super.trimToSize();
    invs.trimToSize();
  }

  public final String name() {
    return parent.name + varNames(var_infos);
  }

  public boolean usesVar(VarInfo vi) {
    return (ArraysMDE.indexOfEq(var_infos, vi) != -1);
  }

  // This is only called from inv.filter.VariableFilter.
  public boolean usesVar(String name) {
    for (int i=0; i<var_infos.length; i++) {
      // mistere: I'm not sure is this is the right thing for
      // the gui, but it's probably close
      if (var_infos[i].name.name().equals(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return true if any of our variables is named NAME, or is derived
   * from a variable named NAME.
   **/
  // Only called right now from tools/ExtractConsequent
  public boolean usesVarDerived(String name) {
    for (int i=0; i<var_infos.length; i++) {
      if (var_infos[i].name.includesSimpleName(name))
        return true;
    }
    return false;
  }

  /** @return true if all of this slice's variables are orig() variables. */
  public boolean allPrestate() {
    for (int i = 0; i < var_infos.length; i++) {
      if (!var_infos[i].isPrestateDerived())
        return false;
    }
    return true;
  }

  public abstract void addInvariant(Invariant inv);

  /** This method actually removes the invariant from its PptSlice. **/
  // I don't just use ppt.invs.remove because I want to be able to defer
  // and to take action if the vector becomes void.
  public void removeInvariant(Invariant inv) {

    if (Debug.logDetail())
      log ("Removing invariant '" + inv.format() + "'");
    if (Debug.logOn())
      inv.log ("Removed from slice: " + inv.format());
    boolean removed = invs.remove(inv);
    if (Assert.enabled && !removed)
      Assert.assertTrue (removed, "inv " + inv + " not in ppt " + name());
    Global.falsified_invariants++;
    if (invs.size() == 0) {
      if (Debug.logDetail())
        log ("last invariant removed");
    }
  }

  // This can be called with very long lists by the conditionals code.
  // At least until that's fixed, it's important for it not to be
  // quadratic.
  public void removeInvariants(List<Invariant> to_remove) {
    if (to_remove.size() < 10) {
      for (int i=0; i<to_remove.size(); i++) {
        removeInvariant(to_remove.get(i));
      }
    } else {
      int removed = invs.removeMany(to_remove);
      if (Assert.enabled && removed < to_remove.size())
        Assert.assertTrue (false, "removed " + (to_remove.size() - removed)
            + " invs not in ppt " + name());
      Global.falsified_invariants += removed;
      if (invs.size() == 0) {
        if (Debug.logDetail())
          log ("last invariant removed");
      }
    }
  }

  /**
   * This procedure accepts a sample (a ValueTuple), extracts the values
   * from it, casts them to the proper types, and passes them along to the
   * invariants proper.  (The invariants accept typed values rather than a
   * ValueTuple that encapsulates objects of any type whatever.)
   * @return a List of Invariants that weakened due to the processing.
   **/
  abstract List<Invariant> add (ValueTuple full_vt, int count);

  /**
   * Removes any falsified invariants from our list.
   */
  protected void remove_falsified () {

    // Remove the dead invariants
    for (Iterator<Invariant> iFalsified = invs.iterator(); iFalsified.hasNext(); ) {
      Invariant inv = iFalsified.next();
      if (inv.is_false()) {
        iFalsified.remove();
        NIS.falsified (inv);
      }
    }
  }

  /**
   * Remove repeated entries in a permutation.  The repeats are a
   * consequence of equality optimization: a VarInfo may be a
   * destination more than once due to equality splitting.  The fix is
   * to, for each repeat, increment the value.  So 0, 0, 2 becomes 0,
   * 1, 2.
   **/
  private void fixPermutation (int[] permutation) {
    for (int i = 0; i < permutation.length; i++) {
      int count = 0;
      for (int j = 0; j < permutation.length; j++) {
        if (permutation[i] == permutation[j]) {
          permutation[j] += count;
          count++;
        }
      }
    }
    Assert.assertTrue(ArraysMDE.fn_is_permutation(permutation));
  }


  /** Return an approximation of the number of samples seen on this slice **/
  public abstract int num_samples();

  /**
   * Return an approximation of the number of distinct values seen on
   * this slice
   **/
  public abstract int num_values();

  /**
   * Instantiate invariants on the VarInfos this slice contains.
   **/
  abstract void instantiate_invariants();

  /**
   * This class is used for comparing PptSlice objects.
   * It orders by arity, then by variable names.
   * It's somewhat less efficient than ArityPptnameComparator.
   **/
  public static final class ArityVarnameComparator implements Comparator<PptSlice> {
    public int compare(PptSlice slice1, PptSlice slice2) {
      if (slice1 == slice2)
        return 0;
      // Don't do this assert, which prevents comparison across different Ppts.
      // (The assert check may be useful in some situations, though.)
      // Assert.assertTrue(slice1.parent == slice2.parent);
      if (slice1.arity() != slice2.arity()) {
        return slice2.arity() - slice1.arity();
      }
      return Ppt.varNames(slice1.var_infos)
        .compareTo(Ppt.varNames(slice2.var_infos));
    }
  }

  /**
   * This class is used for comparing PptSlice objects.
   * It orders by arity, then by name.
   * Because of the dependence on name, it should be used only for slices
   * on the same Ppt.
   **/
  public static final class ArityPptnameComparator implements Comparator<PptSlice> {
    public int compare(PptSlice slice1, PptSlice slice2) {
      if (slice1 == slice2)
        return 0;
      // Don't do this, to permit comparison across different Ppts.
      // (The check may be useful in some situations, though.)
      // Assert.assertTrue(slice1.parent == slice2.parent);
      if (slice1.arity() != slice2.arity()) {
        return slice2.arity() - slice1.arity();
      }
      return slice1.name().compareTo(slice2.name());
    }
  }

  //////////////////////////////////////////////////////////////////////////////
  //// Invariant guarding

//   /**
//    * This procedure guards all of the invariants in a given PptSlice by
//    * iterating over the contained invariants and replacing the invariants
//    * that require guarding with their guarded counterparts.  The guarded
//    * invariants are put into the joiner view of the PptTopLevel that
//    * contains the PptSlice where the invariant was originally located.
//    * The original (unguarded) invariants are removed.
//    * <p>
//    * This procedure changes what invariants exist, so the PptMap should
//    * not be saved, or used for anything except printing, after this is
//    * called.
//    */
//   public void guardInvariants() {
//     List<Invariant> guardedInvariants = new ArrayList<Invariant>();
//
//     if (debugGuarding.isLoggable(Level.FINE)) {
//       debugGuarding.fine ("PptSlice.guardInvariants init: " + this.parent.name());
//       debugGuarding.fine ("  I have " + invs.size() + " invariants");
//       for (Invariant inv : invs) {
//         debugGuarding.fine ("    " + inv);
//       }
//       debugGuarding.fine ("  var_infos in this slice:");
//       for (VarInfo vi : var_infos) {
//         try {
//           debugGuarding.fine ("    " + vi.name.name());
//         } catch (UnsupportedOperationException e) {
//           debugGuarding.fine ("  Part of PptSlice cannot be formatted.");
//         }
//       }
//       // debugGuarding.fine ("In guardInvariants, the VarInfos for the PptSlice: ");
//       // debugGuarding.fine (Arrays.asList(var_infos).toString());
//     }
//
//     // If this slice is to be deleted, then don't guard it
//     if (invs.size() == 0) return;
//
//     for (Iterator<Invariant> overInvs = invs.iterator(); overInvs.hasNext(); ) {
//       Invariant inv = overInvs.next();
/// The below can be replaced by a call to invariant.createGuardedInvariant().
// //       if (debugGuarding.isLoggable(Level.FINE)) {
// //         debugGuarding.fine ("  Trying to add guard for: " + inv + "     " + inv.repr());
// //       }
// //       if (inv.isGuardingPredicate) {
// //         debugGuarding.fine ("  Continuing: this is a guarding predicate");
// //         continue;
// //       }
// //       Invariant guardingPredicate = inv.createGuardingPredicate();
// //       if (debugGuarding.isLoggable(Level.FINE)) {
// //         if (guardingPredicate != null) {
// //           debugGuarding.fine ("  Predicate: " +
// //                               guardingPredicate.format());
// //           debugGuarding.fine ("  Consequent: " +
// //                               inv.format());
// //         } else {
// //           debugGuarding.fine ("  No implication needed");
// //         }
// //       }
//
//       if (guardingPredicate != null) {
//         Implication guardingImplication =
//           GuardingImplication.makeGuardingImplication(parent, guardingPredicate, inv, false);
//
//         if (! parent.joiner_view.hasImplication(guardingImplication)) {
//           parent.joiner_view.addInvariant(guardingImplication);
//           guardedInvariants.add(inv);
//
//           if (debugGuarding.isLoggable(Level.FINE)) {
//             debugGuarding.fine ("Adding " +
//                                 guardingImplication.format());
//             debugGuarding.fine ("Removing " +
//                                 inv.format());
//           }
//         }
//       }
//     }
//
//     removeInvariants(guardedInvariants);
//   }


  public boolean containsOnlyGuardingPredicates() {
    for (int i=0; i<invs.size(); i++) {
      if (!invs.get(i).isGuardingPredicate)
        return false;
    }
    return true;
  }

  /////////////////////////////////////////////////////////////////
  /// Miscellaneous

  /**
   * Remove the invariants noted in omitTypes
   */
  public void processOmissions(boolean[] omitTypes) {
    if (invs.size() == 0) return;
    List<Invariant> toRemove = new ArrayList<Invariant>();
    for (Iterator<Invariant> overInvs = invs.iterator(); overInvs.hasNext(); ) {
      Invariant inv = overInvs.next();
      if (omitTypes['r'] && inv.isReflexive())
        toRemove.add(inv);
    }
    removeInvariants(toRemove);
  }

  /**
   * Check the internals of this slice.  Each invariant in the slice
   * is checked for consistency and each inv.ppt must equal this
   */
  public void repCheck() {

    for (Iterator<Invariant> i = invs.iterator(); i.hasNext(); ) {
      Invariant inv = i.next();
      inv.repCheck();
      Assert.assertTrue (inv.ppt == this);
    }
  }

  /**
   * Clone self and replace this.var_infos with newVis.  Do the same
   * in all invariants that this holds.  Return a new PptSlice that's
   * like this except with the above replacement, along with correct
   * flow pointers for varInfos.  Invariants are also pivoted so that
   * any VarInfo index order swapping is handled correctly.
   *
   * @param newVis to replace this.var_infos.
   * @return a new PptSlice that satisfies the characteristics above.
   **/
  PptSlice cloneAndPivot(VarInfo[] newVis) {
    throw new Error("Shouldn't get called");
  }

  public PptSlice copy_new_invs (PptTopLevel ppt, VarInfo[] vis) {
    throw new Error("Shouldn't get called");
  }

  /**
   * For debugging only.
   **/
  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < var_infos.length; i++) {
      sb.append (" " + var_infos[i].name.name());
    }
    return this.getClass().getName() + ": " + parent.ppt_name + " "
           + sb + " samples: " + num_samples();
  }
  /**
   * Returns whether or not this slice already contains the specified
   * invariant.  Whether not invariants match is determine by Invariant.match()
   * This will return true for invariants of the same kind with different
   * formulas (eg, one_of, bound, linearbinary)
   */
  public boolean contains_inv (Invariant inv) {

    for (Iterator<Invariant> i = invs.iterator(); i.hasNext(); ) {
      Invariant mine = i.next();
      if (mine.match (inv))
        return (true);
    }
    return (false);
  }

  /**
   * Returns whether or not this slice contains an exact match
   * for the specified invariant.  An exact match requires that the
   * invariants be of the same class and have the same formula
   */
  public boolean contains_inv_exact (Invariant inv) {

    return (find_inv_exact(inv) != null);
  }

  /**
   * Returns the invariant that matches the specified invariant if it
   * exists.  Otherwise returns null.  An exact match requires that
   * the invariants be of the same class and have the same formula
   */
  public Invariant find_inv_exact (Invariant inv) {

    for (Iterator<Invariant> i = invs.iterator(); i.hasNext(); ) {
      Invariant mine = i.next();
      if ((mine.getClass() == inv.getClass()) && mine.isSameFormula(inv))
        return (mine);
    }
    return (null);
  }

  /**
   * Returns the invariant that matches the specified class if it
   * exists.  Otherwise returns null.
   */
  public Invariant find_inv_by_class (Class cls) {

    for (Iterator<Invariant> i = invs.iterator(); i.hasNext(); ) {
      Invariant inv = i.next();
      if ((inv.getClass() == cls))
        return (inv);
    }
    return (null);
  }

  /**
   * Returns true if the invariant is true in this slice.  This can
   * occur if the invariant exists in this slice, is suppressed,
   * or is obvious statically.
   */
  public boolean is_inv_true (Invariant inv) {

    if (contains_inv_exact (inv)) {
      if (Debug.logOn() && (Daikon.current_inv != null))
        Daikon.current_inv.log ("inv " + inv.format() + " exists");
      return (true);
    }

    // Check to see if the invariant is obvious statically over the leaders.
    // This check should be sufficient since if it isn't obvious statically
    // over the leaders, it should have been created.
    DiscardInfo di = inv.isObviousStatically (var_infos);
    if (di != null) {
      if (Debug.logOn() && (Daikon.current_inv != null))
        Daikon.current_inv.log ("inv " + inv.format() + " is obv statically");
      return (true);
    }

    boolean suppressed = inv.is_ni_suppressed();
    if (suppressed && Debug.logOn() && (Daikon.current_inv != null))
      Daikon.current_inv.log ("inv " + inv.format() + " is ni suppressed");
    return (suppressed);
  }

  /**
   * Output specified log information if the PtpSlice class, and this ppt
   * and variables are enabled for logging
   */
  public void log (String msg) {
    Debug.log (getClass(), this, msg);
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
