package daikon.inv;

import daikon.*;

import utilMDE.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.*;


// Note that this Invariant is used in a *very* different way from
// the same-named on in V2.  In V2, this is just for printing.  In V3,
// this does all the canonicalizing, etc.

// We don't need all these implementation details in the Javadoc that is
// reproduced in the Daikon user manual!
//
// During checking, Equality keeps track of variables that are
// comparable and equal, so we only need to instantiate (other)
// invariants for one member of each Equal set, the leader.
//
// During postProcessing, each instance of Equality instantiates into
// displaying several equality Comparison invariants ("x == y", "x ==
// z").  Equality invariants have leaders, which are the canonical
// forms of their variables.  In the previous example, x is the
// leader.  Equality invariants sort their variables by index ordering
// during checking.  During printing, however, equality invariants may
// "pivot" -- that is, switch leaders if the current leader wouldn't
// be printed because it was not an interesting variable.  Notice that
// when pivoting, all the other invariants based on this.leader also
// need to be pivoted.


/**
 * Keeps track of sets of variables that are equal.  Other invariants are
 * instantiated for only one member of the Equality set, the leader.  If
 * variables <samp>x</samp>, <samp>y</samp>, and <samp>z</samp> are members
 * of the Equality set and <samp>x</samp> is chosen as the leader, then
 * the Equality will internally convert into binary comparison invariants
 * that print as <samp>x == y</samp> and <samp>x == z</samp>.
 *
 **/
public final class Equality
  extends Invariant
{
   // We are Serializable, so we specify a version to allow changes to
   // method signatures without breaking serialization.  If you add or
   // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20021231L;

  public static final Logger debug =
    Logger.getLogger ("daikon.inv.Equality");

  public static final Logger debugPostProcess =
    Logger.getLogger ("daikon.inv.Equality.postProcess");

  /**
   * How many samples this has seen.
   **/
  private int numSamples;

  public void setSamples (int sample_cnt) {
    numSamples = sample_cnt;
  }

  public int numSamples() {
    return numSamples;
  }

  /**
   * The Set of VarInfos that this represents equality for.  Can
   * change over time as this invariant weakens.  Sorted by index
   * until pivoting.
   **/
  private TreeSet<VarInfo> vars;

  /** Returns the number of variables in the set. **/
  public int size() {
    return vars.size();
  }

  /**
   * Returns the variables in their index order.  Unmodifiable.
   **/
  public Set<VarInfo> getVars() {
    return Collections.unmodifiableSet (vars);
  }

  /**
   * @param variables Variables which are equivalent, with the canonical
   * one first.  Elements must be of type VarInfo.
   **/
  public Equality(Collection<VarInfo> variables, PptSlice ppt) {
    super(ppt);
    if (debug.isLoggable(Level.FINE)) {
      debug.fine ("Creating at " + ppt.parent.name() + " vars: ");
    }

    numSamples = 0;
    vars = new TreeSet<VarInfo>(VarInfo.IndexComparator.theInstance);
    vars.addAll (variables);
    VarInfo leader = leader();

    // ensure well-formedness and set equality slots
    Assert.assertTrue (variables.size() > 0);
    Assert.assertTrue (vars.size() == variables.size());
    for (Iterator<VarInfo> i = variables.iterator(); i.hasNext(); ) {
      VarInfo vi = i.next();
      if (debug.isLoggable(Level.FINE)) {
        debug.fine ("  " + vi.name.name() + " [" + vi.comparability + "]");
      }
      Assert.assertTrue(vi.ppt == leader.ppt);
      Assert.assertTrue(vi.comparableNWay (leader));
      Assert.assertTrue (VarComparability.comparable (leader, vi),
             "not comparable " + leader.name.name() + " " + vi.name.name()
            +" at ppt " + ppt.parent.name());
      Assert.assertTrue(vi.rep_type.isArray() == leader.rep_type.isArray());
      vi.equalitySet = this;
    }
  }

  ////////////////////////
  // Accessors


  private VarInfo leaderCache = null;
  /**
   * Return the canonical VarInfo of this.  Note that the leader never
   * changes.
   * @return the canonical VarInfo of this
   **/
  public VarInfo leader() {
    if (leaderCache == null) {
      leaderCache = vars.iterator().next();
    }
    return leaderCache;
  }

  public boolean hasNonCanonicalVariable() {
    throw new Error("Illegal operation on Equality invariant");
  }

  /**
   * Always return JUSTIFIED because we aggregate Comparison
   * invariants that are all justified to the confidence_limit
   * threshold.
   **/
  public double computeConfidence() {
    return Invariant.CONFIDENCE_JUSTIFIED;
  }


  ////////////////////////
  // Functions called during actual checking

  private void flow(Invariant flowed) {
    throw new UnsupportedOperationException("Equality invariants don't flow");
  }

  ////////////////////////
  // Printing

  public String repr() {
    return "Equality: size=" + size()
      + " leader: " + leader().name.name() + " with "
      + format_daikon() + " samples: " + numSamples();
  }

  public String format_using(OutputFormat format) {


    if (format.isJavaFamily()) return format_java_family(format);

    if (format == OutputFormat.DAIKON) return format_daikon();
    if (format == OutputFormat.IOA) return format_ioa();
    if (format == OutputFormat.ESCJAVA) return format_esc();
    // Commented out by MDE 7/27/2003.  I can't figure out whether
    // to just change JAVA_IDENTIFIER to IDENTIFIER, or whether other
    // changes are also necessary.
    // if (format == OutputFormat.JAVA_IDENTIFIER) return format_java();
    if (format == OutputFormat.SIMPLIFY) return format_simplify();
    return format_unimplemented(format);
  }

  public String format_daikon() {
    StringBuffer result = new StringBuffer();
    boolean start = true;
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo var = i.next();
      if (!start) {
        result.append(" == ");
      } else {
        start = false;
      }
      result.append(var.name.name());
      result.append ("[" + var.varinfo_index + "]");
      // result.append("[" + var.comparability + "]");
      if (var == leader())
        result.append ("L");
    }
    return result.toString();
  }


  // These format methods aren't called, because for output, we
  // convert to normal two-way IntEqual type invariants.  However,
  // they can be called if desired.
  public String format_java() {
    StringBuffer result = new StringBuffer ();
    VarInfo leader = leader();
    String leaderName = leader.name.name();
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo var = i.next();
      if (leader == var) continue;
      result.append("(").append(leaderName).append(" == "); // "interned"
      result.append(var.name.name()).append(")");
      if (i.hasNext()) result.append(" && ");
    }
    return result.toString();
  }

  public String format_ioa() {
    StringBuffer result = new StringBuffer();
    VarInfo leader = leader();
    String leaderName = leader.name.ioa_name();
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo var = i.next();
      if (leader == var) continue;
      result.append (var.name.ioa_name());
      result.append (" = ");
      result.append (leaderName);
      if (i.hasNext()) result.append (" /\\ ");
    }

    return result.toString();
  }


  public String format_esc() {
    String result = "";

    List<VarInfo> valid_equiv = new ArrayList<VarInfo>();
    List<VarInfo> invalid_equiv = new ArrayList<VarInfo>();

    List<VarInfo> equal_vars = new Vector<VarInfo>();

    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo other = i.next();
      if (other.isDerivedSequenceMinMaxSum()) {
        break;
      }
      if (other.isValidEscExpression()) {
        valid_equiv.add(other);
      } else {
        invalid_equiv.add(other);
      }
    }
    // Choose a leader, preferring the valid variables.
    VarInfo leader;
    if (valid_equiv.size() > 0) {
      leader = valid_equiv.get(0);
    } else {
      Assert.assertTrue(invalid_equiv.size() > 0);
      leader = invalid_equiv.get(0);
    }
    // Print the equality statements, stating expressible ones first.
    equal_vars.clear();
    equal_vars.addAll(valid_equiv);
    equal_vars.addAll(invalid_equiv);
    int numprinted = 0;
    for (int j=0; j<equal_vars.size(); j++) {
      VarInfo other = equal_vars.get(j);
      if (other == leader) continue;
      if (leader.name.applyPrestate().equals(other.name)) continue;
      if (other.name.applyPrestate().equals(leader.name)) continue;
      if (numprinted > 0) {
        result += Global.lineSep;
      }
      numprinted++;
      if (j >= valid_equiv.size()) {
        result = result + "warning: method Equality.format_esc() needs to be implemented: " + format();
      }
      if (leader.rep_type.isArray()) {
        String[] form =
          VarInfoName.QuantHelper.format_esc(new VarInfoName[]
            { leader.name, other.name }, true); // elementwise
        result = result + form[0] + "( " + form[1] + " == " + form[2] + " )" + form[3];
      } else {
        result = result + leader.name.esc_name() + " == " + other.name.esc_name();
      }

    }
    return result;

//      StringBuffer result = new StringBuffer();
//      if (leader().rep_type.isArray()) {
//        for (int i=1; i < vars.length; i++) {
//      if (i > 1) {
//        result.append(Global.lineSep);
//      }
//      String[] form =
//        VarInfoName.QuantHelper.format_esc(new VarInfoName[]
//          { leader().name, vars[i].name }, true); // elementwise
//      result.append(form[0] + "( " + form[1] + " == " + form[2] + " )" + form[3]);
//        }
//      } else {
//        for (int i=1; i < vars.length; i++) {
//      if (i > 1) {
//        result.append(" && ");
//      }
//      result.append("");   // formerly "("
//      result.append(leader().name.esc_name());
//      result.append(" == ");
//      result.append(vars[i].name.esc_name());
//      result.append("");  // formerly ")"
//        }
//      }
//      return result.toString();
  }

  // When A and B are pointers, don't say (EQ A B); instead say (EQ
  // (hash A) (hash B)).  If we said the former, Simplify would
  // presume that A and B were always interchangeable, which is not
  // the case when your programming language involves mutation.
  private String format_elt(String simname) {
    String result = simname;
    if (leader().is_reference()) {
      result = "(hash " + result + ")";
    }
    return result;
  }

  public String format_simplify() {
    StringBuffer result = new StringBuffer("(AND");
    VarInfo leader = leader();
    String leaderName = leader.name.simplify_name();
    if (leader.rep_type.isArray()) {
      for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
        VarInfo var = i.next();
        if (var == leader) continue;
        String[] form =
          VarInfoName.QuantHelper.format_simplify(new VarInfoName[]
            { leader.name, var.name }, true); // elementwise
        String a = format_elt(form[1]);
        String b = format_elt(form[2]);
        result.append(" " + form[0] + "(EQ " + a + " " + b + ")" + form[3]);
      }
    } else {
      for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
        VarInfo var = i.next();
        if (var == leader) continue;
        String a = format_elt(leaderName);
        String b = format_elt(var.name.simplify_name());
        result.append(" (EQ ");
        result.append(a);
        result.append(" ");
        result.append(b);
        result.append(")");
      }
    }
    result.append(")");
    return result.toString();
  }

  public String shortString() {
    return format_daikon();
  }

  public String format_java_family(OutputFormat format) {
    StringBuffer result = new StringBuffer ();
    VarInfo leader = leader();
    String leaderName = leader.name.name_using(format, leader);
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo var = i.next();
      if (leader == var) continue;
      if (leader.rep_type.isArray()) {
        result.append("(").append("daikon.Quant.pairwiseEqual(");
        result.append(leaderName).append(", ").append(var.name.name_using(format, var));
        result.append(")");
      } else {
        if (leader.type.isFloat()) {
          result
            .append("(")
            .append(Invariant.formatFuzzy("eq", leader, var, format))
            .append(")");
        } else {
          if ((leaderName.indexOf("daikon.Quant.collectObject") != -1)
              ||
              (var.name.name_using(format, var).indexOf("daikon.Quant.collectObject") != -1)) {
            result.append("(warning: it is meaningless to compare hashcodes for values "
                          + "obtained through daikon.Quant.collect... methods.");
          } else {
            result.append("(").append(leaderName).append(" == "); // "interned"
            result.append(var.name.name_using(format, var)).append(")");
          }
        }
      }
      if (i.hasNext()) result.append(" && ");
    }
    return result.toString();
  }

  public String toString() {
    return repr();
  }

  //////////////////////////////////////////////////////////////////////
  /// Processing of data

  /**
   * @return a List of VarInfos that do not fit into this set anymore
   *
   * Originally (8/14/2003), this did not check for the modified bits.
   * It seems however, quite wrong to leave variables in the same equality
   * set when one is missing and the other is not.  Its possible we should
   * go farther and break out of the equality set any variable that is
   * missingOutOfBounds (JHP)
   **/
  public List<VarInfo> add(ValueTuple vt, int count) {
    // Need to handle specially if leader is missing.
    VarInfo leader = leader();
    Object leaderValue = leader.getValue(vt);
    int leaderMod = leader.getModified(vt);
    boolean leaderOutOfBounds = leader.missingOutOfBounds();
    if (leaderMod == ValueTuple.MISSING_NONSENSICAL ||
        leaderMod == ValueTuple.MISSING_FLOW) {
    } else {
      numSamples += count;
    }

    List<VarInfo> result = new LinkedList<VarInfo>();
    if (debug.isLoggable(Level.FINE)) {
      debug.fine ("Doing add at " + this.ppt.parent.name() + " for " + this);
    }
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo vi = i.next();
      if (vi == leader)
        continue;
      Assert.assertTrue (vi.comparableNWay (leader));
      Object viValue = vi.getValue(vt);
      int viMod = vi.getModified(vt);
      // The following is possible because values are interned.  The
      // test also takes into account missing values, since they are
      // null.
      if ((leaderValue == viValue) && (leaderMod == viMod)
        && !leaderOutOfBounds && !vi.missingOutOfBounds()) continue;
      //       if (debug.isLoggable(Level.FINE)) {
      //         debug.fine ("  vi name: " + vi.name.name());
      //         debug.fine ("  vi value: " + viValue);
      //         debug.fine ("  le value: " + leaderValue);
      //       }
      if (Debug.logOn())
        Debug.log (getClass(), ppt.parent, Debug.vis (vi),
                   "Var " + vi.name.name()
                   + " [" + viValue + "," + viMod + "] split from leader "
                   + leader.name.name() + " [" + leaderValue + ","
                   + leaderMod + "]");

      result.add (vi);
      i.remove();
    }

    return result;
  }

  //  This method isn't going to be called, but it's declared abstract in Invariant.
  protected Invariant resurrect_done(int[] permutation) {
    throw new UnsupportedOperationException();
  }

  //  This method isn't going to be called, but it's declared abstract in Invariant.
  public boolean isSameFormula( Invariant other ) {
    throw new UnsupportedOperationException( "Equality.isSameFormula(): this method should not be called" );
  }

  /**
   * Convert Equality invariants into normal IntEqual type for
   * filtering, printing, etc.  Add these to parent.
   *
   * If the leader was changed to not be the first member of the group
   * adds leader == leader invariant as well since that invariant is
   * used in suppressions and obvious tests.
   **/
  public void postProcess () {
    if (this.numSamples() == 0) return; // All were missing or not present
    PptTopLevel parent = this.ppt.parent;
    VarInfo[] varArray = (VarInfo[]) this.vars.toArray(new VarInfo[0]);
    if (debugPostProcess.isLoggable(Level.FINE)) {
      debugPostProcess.fine ("Doing postProcess: " + this.format_daikon());
      debugPostProcess.fine ("  at: " + this.ppt.parent.name());
    }
    VarInfo leader = leader();

    if (debugPostProcess.isLoggable(Level.FINE)) {
      debugPostProcess.fine ("  var1: " + leader.name.name());
    }
    for (int i = 0; i < varArray.length; i++) {
      if (varArray[i] == leader) continue;
      if (debugPostProcess.isLoggable(Level.FINE)) {
        debugPostProcess.fine ("  var2: " + varArray[i].name.name());
      }

      parent.create_equality_inv (leader, varArray[i], numSamples());
    }
  }

  /**
   * Switch the leader of this invariant, if possible, to a more canonical
   * VarInfo:  a VarInfo that is not isDerived() is better than one that is;
   * one that is not isDerivedParamAndUninteresting() is better than one that
   * is; and other things being equal, choose the least complex name.
   * Call this only after postProcess has been called.
   * We do a pivot so that anything that's interesting to be printed
   * gets printed and not filtered out.  For example, if a == b and a
   * is the leader, but not interesting, we still want to print f(b)
   * as an invariant.  Thus we pivot b to be the leader.  Later on,
   * each relevant PptSlice gets pivoted.  But not here.
   **/
  public void pivot() {
    VarInfo newLeader = null;
    for (Iterator<VarInfo> iVars = vars.iterator(); iVars.hasNext(); ) {
      VarInfo var = iVars.next();
      if (newLeader == null) {
        newLeader = var;
      }
      else if (newLeader.isDerivedParamAndUninteresting() &&
	       !var.isDerivedParamAndUninteresting()) {
	newLeader = var;
      }
      else if (var.isDerivedParamAndUninteresting() &&
	       !newLeader.isDerivedParamAndUninteresting()) {
	// do nothing
      }
      else if (var.derivedDepth() < newLeader.derivedDepth()) {
	newLeader = var;
      }
      else if (var.derivedDepth() > newLeader.derivedDepth()) {
	// do nothing
      }
      // if we got here, this is the "all other things being equal" case
      else if (var.name.inOrderTraversal().size() <
	       newLeader.name.inOrderTraversal().size()) {
	newLeader = var;
      }
    }
    leaderCache = newLeader;
  }

  public void repCheck() {
    super.repCheck();
    VarInfo leader = leader();
    for (Iterator<VarInfo> i = vars.iterator(); i.hasNext(); ) {
      VarInfo var = i.next();
      Assert.assertTrue (VarComparability.comparable (leader, var),
                 "not comparable: " + leader.name.name() + " "
                + var.name.name() + " at ppt " + ppt.parent.name());
    }
  }

}
