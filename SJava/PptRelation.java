package daikon;

import daikon.inv.*;
import java.io.*;
import java.util.*;
import utilMDE.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Class that builds and describes relations in the ppt hierachy.
 * Building the relationship is specific to each type of parent/child
 * relationship (eg, method to object, exit to combined exit, etc).
 * The use of the relationship is general.
 *
 * The basic function of the class is to translate from a variable in
 * the parent to the equivalent variable in the child and vice-versa.
 * For example, in the ENTER -> EXIT relationship, the parent is the
 * ENTER ppt and the child is the EXIT ppt.  Each variable in the ENTER
 * ppt is connected to the corresponding orig variable in the EXIT ppt.
 */

public class PptRelation implements Serializable {

  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20030819L;

  // This should really be an enumerated type.
  // The arrows point from parent to child, which is the opposite of the
  // way that we usually draw them in diagrams.  (Consider changing this??)
  public static final String OBJECT_METHOD = "object -> method";
  public static final String CLASS_OBJECT = "class -> object";
  public static final String OBJECT_USER = "object -> user";
  public static final String ENTER_EXIT = "enter -> exit";
  public static final String EXIT_EXITNN = "exit -> exitNN";
  public static final String MERGE_CHILD = "merge -> child";
  public static final String PPT_PPTCOND = "ppt -> ppt_cond";

  private static final Logger debug = Logger.getLogger("daikon.PptRelation");

  /**
   * Description of type of parent-child relationship (debug output only).
   **/
  String relationship;

  /** Parent of relation. **/
  public PptTopLevel parent;

  /** Child of relation. **/
  public PptTopLevel child;

  /** Map from parent vars to matching child vars. */
  Map<VarInfo,VarInfo> parent_to_child_map;

  /** Map from child vars to matching parent vars. */
  Map<VarInfo,VarInfo> child_to_parent_map;

  /**
   * Boolean.  Controls whether the object-user relation is created in the
   * variable hierarchy.
   **/
  public static boolean dkconfig_enable_object_user = true;

  /**
   * Create a relation between the specified parent and child.  The actual
   * variable relations are filled in by the caller.  Note that this creates
   * the connection between this relation and the parent/child.
   */
  private PptRelation(PptTopLevel parent, PptTopLevel child, String rel_type) {

    this.parent = parent;
    this.child = child;
    parent_to_child_map = new LinkedHashMap<VarInfo,VarInfo>();
    child_to_parent_map = new LinkedHashMap<VarInfo,VarInfo>();
    // rel_type is one of the above relationship types because this is a
    // private constructor, called only within this file.
    relationship = rel_type;
    connect();
  }

  /**
   * Adds this relation to its child's parent list and its parent's
   * children list.
   */
  private void connect() {
    Assert.assertTrue(!child.parents.contains(this));
    Assert.assertTrue(!parent.children.contains(this));
    child.parents.add(this);
    parent.children.add(this);
  }

  /**
   * Returns the number of parent to child variable relations.
   */
  public int size() {
    return (parent_to_child_map.size());
  }

  public String toString() {
    return (parent.ppt_name + "->" + child.ppt_name + "(" + relationship + ")");
  }

  /**
   * Return a string containing all of the parent->child var relations.
   */
  public String parent_to_child_var_string() {

    StringBuffer var_str = new StringBuffer();
    for (VarInfo pv : parent_to_child_map.keySet()) {
      VarInfo cv = parent_to_child_map.get(pv);
      if (var_str.length() > 0)
        var_str.append(", ");
      var_str.append(pv.name.name() + "->" + cv.name.name());
    }

    return var_str.toString();
  }

  /**
   * Relates all of the variables with the same name in parent and child.
   * Returns true if each non-static parent variable was related to a
   * child variable
   */
  public boolean relate_same_name() {

    boolean relate_all = true;
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vp = parent.var_infos[i];
      boolean relate_var = relate(vp, vp.name);
      if (!relate_var && !vp.isStaticConstant()) {
        // System.out.printf ("no relation for %s%n", vp);
        relate_all = false;
      }
    }

    return (relate_all);

  }

  /**
   * Returns the variable in ppt that matches viname.  Returns null
   * if viname does not exist in ppt
   */
  private static VarInfo find_var(PptTopLevel ppt, VarInfoName viname) {

    for (int j = 0; j < ppt.var_infos.length; j++) {
      VarInfo v = ppt.var_infos[j];
      if (viname == v.name) {
        return (v);
      }
    }
    return (null);
  }

  /**
   * Prints a ppt hierarchy of all of the ppts of this child and below.
   */
  public void debug_print_tree(Logger l, int indent) {

    // Print the child tree including vars and class name
    child.debug_print_tree(l, indent, this);
  }

  /**
   * Returns whether or not this relation is a primary relation.  This
   * used to simplify debug prints of the PPt tree (so that extra relations
   * don't result in duplicative information).
   *
   * Somewhat arbitrarily, Object->User and Enter->Exit are not considered
   * primary while all others are.  The remaining relations (class->object,
   * object->method,and exit->exitNN) form a simple tree without duplication
   */

  public boolean is_primary() {
    return ((relationship != OBJECT_USER) && (relationship != ENTER_EXIT));
  }

  /** Returns a string describing the parent-child relationship. **/
  public String getRelationType() {
    return (relationship);
  }

  /**
   * Returns the parent variable that corresponds to childVar.  Returns
   * null if there is no corresponding variable.
   */

  public VarInfo parentVar(VarInfo childVar) {
    return child_to_parent_map.get(childVar);
  }


  /**
   * Like parentVar(VarInfo), but if no parent is found, tries every
   * variable in the equality set and returns null only if none of them has
   * a parent.
   **/
  public VarInfo parentVarAnyInEquality(VarInfo childVar) {
    VarInfo result = parentVar(childVar);
    if (result != null) {
      return result;
    }
    if (childVar.equalitySet == null) {
      return null;
    }
    for (VarInfo v : childVar.equalitySet.getVars()) {
      result = parentVar(v);
      if (result != null) {
        return result;
      }
    }
    return null;
  }



  /**
   * Returns the child variable that corresponds to parentVar.  Returns
   * null if there is no corresponding variable.
   */

  public VarInfo childVar(VarInfo parentVar) {
    return parent_to_child_map.get(parentVar);
  }

  /**
   * Returns whether or not this relation's child has children of its own.
   */
  public boolean hasChildren() {
    return (child.children.size() > 0);
  }

  /**
   * Returns a map of VarInfo.Pair with an entry for each pair of
   * equal variables in all of the equality sets of the child.  The
   * variables are the corresponding parent variables and not the
   * child variables themselves.  The map is from the pair to itself,
   * which allows the pair to be looked up (which is not possible with
   * a set).
   */
  public Map<VarInfo.Pair,VarInfo.Pair> get_child_equalities_as_parent() {

    debug.fine(
      "get_child_equalities for "
        + child.name()
        + " for parent "
        + parent.name()
        + " "
        + relationship);
    Map<VarInfo.Pair,VarInfo.Pair> emap = new LinkedHashMap<VarInfo.Pair,VarInfo.Pair>();

    if (child.equality_view == null)
      System.out.println(
        "equality_view.invs == null in child ppt: "
          + child.name()
          + " samples = "
          + child.num_samples());
    else if (child.equality_view.invs == null) {
      System.out.println(
        "equality_view.invs == null in child ppt: "
          + child.name()
          + " samples = "
          + child.num_samples());
      System.out.println("children = " + child.children);
    }

    // Loop through each equality set in the child
    for (int i = 0; i < child.equality_view.invs.size(); i++) {
      Equality e = (Equality) child.equality_view.invs.get(i);
      debug.fine("-- processing equality set " + e);
      Set<VarInfo> eqset = e.getVars();
      VarInfo[] varr = new VarInfo[eqset.size()];
      varr = (VarInfo[]) eqset.toArray(varr);

      // Build each combination of variables in the equality set and produce
      // a pair for each.  Skip any variables that do not have corresponding
      // variables in the parent.
      for (int j = 0; j < varr.length; j++) {
        VarInfo v1 = parentVar(varr[j]);
        if (v1 == null) {
          debug.fine("-- -- " + varr[j].name.name() + " not in parent (skip)");
          continue;
        }
        for (int k = j + 1; k < varr.length; k++) {
          VarInfo v2 = parentVar(varr[k]);
          if (v2 == null) {
            debug.fine(
              "-- -- " + varr[k].name.name() + " not in parent (skip)");
            continue;
          }
          VarInfo.Pair parent_pair = new VarInfo.Pair(v1, v2, e.numSamples());
          emap.put(parent_pair, parent_pair);
          if (debug.isLoggable(Level.FINE))
            debug.fine(
              "-- -- "
              + varr[j].name.name()
              + ", "
              + varr[k].name.name()
              + " in child yield "
              + parent_pair
              + " in parent");
        }
      }
    }
    return (emap);
  }

  /**
   * Relates parent_var to a variable in child that matches name.
   *
   * @param parent_var      The parent variable being matched
   * @param viname          The name to look for in child variables.
   *
   * @return true if there was a matching variable, false otherwise.
   */

  private boolean relate(VarInfo parent_var, VarInfoName viname) {

    for (int j = 0; j < child.var_infos.length; j++) {
      VarInfo vc = child.var_infos[j];
      if (viname == vc.name) {
        child_to_parent_map.put(vc, parent_var);
        parent_to_child_map.put(parent_var, vc);
        return (true);
      }
    }
    return (false);
  }

  /**
   * Returns a relation in the ppt hierarchy from an object (parent) to a
   * method (child) on that object.
   */
  public static PptRelation newObjectMethodRel(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, OBJECT_METHOD);

    debug.fine("parent vars = " + VarInfo.toString(parent.var_infos));
    debug.fine("child vars = " + VarInfo.toString(child.var_infos));

    // Connect each 'this' variable between parent and child.
    // Note that these should be the only variables whose names match and
    // that each parent variable should match one in the child.
    boolean relate_all = rel.relate_same_name();
    Assert.assertTrue(relate_all);
    return (rel);
  }

  /**
   * Returns a relation in the ppt hierarchy from a class (parent)
   * to an object (child) containing static members of that class.
   */

  public static PptRelation newClassObjectRel(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, CLASS_OBJECT);

    // Connect each static variable between parent and child
    // Note that these should be the only variables whose names match
    rel.relate_same_name();
    return (rel);
  }

  /**
   * Returns a relation in the ppt hierarchy from an object (parent)
   * to a user (child) of that objects (eg, from the object B to the method
   * A.foo (B arg))
   *
   * Note that on Nov 22 2005, jhp removed the exception noted below.
   * We now think it would be more regular to include this in the relation.
   * If the output is confusing, we can change the ParentFilter to not
   * filter out this particular child invariant.
   *
   *   Note that only the fields of the object (eg, this.x, this.y)
   *   and not the object itself (eg, this) are substituted in this
   *   fashion.  That is because the object and references to it are
   *   really not the same.
   *
   *   For example, assume that every reference to T at all ppts was not
   *   null.  This invariant would print as 'this != null.'  The
   *   invariant is both confusing (since in a normal context 'this' can
   *   never be null) and it is not obvious that it implies that all
   *   references to the object are not NULL.
   *
   * @param parent Ppt of the object definition
   * @param child Ppt of a user of parent's object
   * @param arg Variable of type object found in child
   */
  public static PptRelation newObjectUserRel(
    PptTopLevel parent,
    PptTopLevel child,
    VarInfo arg) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, OBJECT_USER);

    // Connect each each field in arg between parent and child.  Do this
    // by substituting args name for this in the parent and then looking
    // for a name match in the child
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vp = parent.var_infos[i];
      // // Don't make any relationship for variable "this".
      // if (vp.name.equals(VarInfoName.THIS))
      //  continue;
      VarInfoName parent_name = vp.name.replaceAll(VarInfoName.THIS, arg.name);
      rel.relate(vp, parent_name);
    }
    return (rel);
  }

  /**
   * Returns a relation in the ppt hierarchy from enter points to exit
   * points over orig variables.
   */
  public static PptRelation newEnterExitRel(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, ENTER_EXIT);

    // Look for orig versions of each non-derived parent variable in the child
    // Note that static constants don't have orig versions (since they are
    // known to be the same), so we connect to the post version instead.
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vp = parent.var_infos[i];
      if (vp.derived != null)
        continue;
      if (vp.isStaticConstant()) {
        boolean found = rel.relate(vp, vp.name);
        // Static constants are not always placed at each level in hierarchy
        // Assert.assertTrue(found);
      } else {
        VarInfoName orig_name = vp.name.applyPrestate().intern();
        boolean found = rel.relate(vp, orig_name);
        assert found : String.format ("vp %s orig_name %s parent %s child %s",
                                      vp, orig_name, parent, child);
      }
    }

    // Look for orig versions of derived variables in the child.  This is
    // done by finding the base of each derived variable and looking for
    // a child variable with the same bases and the same equation.  This
    // is necessary because derivations are done AFTER orig variables so
    // applying the prestate name (as done above) won't work (the resulting
    // variable is really the same but the name is constructed differently)

    // Loop through each derived parent (ENTER) variable
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vp = parent.var_infos[i];
      if (vp.derived == null)
        continue;

      // Get a child version of each of the bases of the derivation
      VarInfo[] vp_bases = vp.derived.getBases();
      VarInfo[] child_vp_bases = new VarInfo[vp_bases.length];
      for (int j = 0; j < vp_bases.length; j++)
        child_vp_bases[j] = rel.childVar(vp_bases[j]);

      // Loop through the child (exit) looking for a matching derived variable
      for (int j = 0; j < child.var_infos.length; j++) {
        VarInfo vc = child.var_infos[j];
        if (vc.derived == null)
          continue;
        if (vc.derived.isSameFormula(vp.derived)) {
          VarInfo[] vc_bases = vc.derived.getBases();
          if (Arrays.equals(child_vp_bases, vc_bases)) {
            rel.child_to_parent_map.put(vc, vp);
            rel.parent_to_child_map.put(vp, vc);
            break;
          }
        }
      }
    }

    // Make sure every non-static ENTER variable was found in the EXIT point
    boolean all_found = true;
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vp = parent.var_infos[i];
      if (vp.isStaticConstant())
        continue;
      if (!rel.parent_to_child_map.containsKey(vp)) {
        System.out.println(
          "No match for "
            + vp.name.name()
            + " from parent "
            + parent.name()
            + " in child "
            + child.name());
        all_found = false;
      }
    }
    if (!all_found) {
      for (int j = 0; j < child.var_infos.length; j++)
        System.out.println("    " + child.var_infos[j].name.name());
      //Assert.assertTrue (false, "Missing orig variable in EXIT");
    }
    return (rel);
  }

  /**
   * Returns a relation in the ppt hierarchy from combined
   * exit points (parent) to an individual exit point (child).  Individual
   * exit points are often referred to as exitNN where NN is the line
   * number of the exit point).
   */
  public static PptRelation newCombinedExitExitNNRel(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, EXIT_EXITNN);

    // Create the parent-child variable map.  This one is easy as the
    // variables should match exactly
    Assert.assertTrue(parent.var_infos.length == child.var_infos.length);
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vc = child.var_infos[i];
      VarInfo vp = parent.var_infos[i];
      Assert.assertTrue(vc.name.name().equals(vp.name.name()));
      rel.child_to_parent_map.put(vc, vp);
      rel.parent_to_child_map.put(vp, vc);
    }
    return (rel);
  }

  /**
   * Returns a relation in the ppt hierarchy from a ppt to a
   * PptConditional for that point.
   */
  public static PptRelation newPptPptConditional(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, PPT_PPTCOND);

    // Create the parent-child variable map.  This one is easy as the
    // variables should match exactly
    Assert.assertTrue(parent.var_infos.length == child.var_infos.length);
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vc = child.var_infos[i];
      VarInfo vp = parent.var_infos[i];
      Assert.assertTrue(vc.name.name().equals(vp.name.name()));
      rel.child_to_parent_map.put(vc, vp);
      rel.parent_to_child_map.put(vp, vc);
    }
    return (rel);
  }

  /**
   * Returns a an artificial relation in the Program point hierarchy
   * between the same ppt in two different PptMaps.  Used to merge
   * invariants between different data sets.  The parent and the
   * child should have exactly the same variables.
   */
  public static PptRelation newMergeChildRel(
    PptTopLevel parent,
    PptTopLevel child) {

    Assert.assertTrue((parent != null) && (child != null));

    PptRelation rel = new PptRelation(parent, child, MERGE_CHILD);

    // assert that parent vars match child vars
    if (parent.var_infos.length != child.var_infos.length) {
      System.out.println(
        "newMergeChildRel: in ppt " + parent.name() + " vars don't match");
      System.out.println("parent vars= " + VarInfo.toString(parent.var_infos));
      System.out.println("child vars=  " + VarInfo.toString(child.var_infos));
      Assert.assertTrue(parent.var_infos.length == child.var_infos.length);
    }

    // Create the parent-child variable map.  This one is easy as the
    // variables should match exactly
    for (int i = 0; i < parent.var_infos.length; i++) {
      VarInfo vc = child.var_infos[i];
      VarInfo vp = parent.var_infos[i];
      if (!vc.name.name().equals(vp.name.name())) {
        System.out.println(
          "newMergeChildRel: in ppt "
            + parent.name()
            + " var "
            + vc.name.name()
            + " doesn't match");
        System.out.println("par vars  = " + VarInfo.toString(parent.var_infos));
        System.out.println("child vars= " + VarInfo.toString(child.var_infos));
        Assert.assertTrue(vc.name.name().equals(vp.name.name()));
      }
      rel.child_to_parent_map.put(vc, vp);
      rel.parent_to_child_map.put(vp, vc);
    }
    return (rel);
  }

  /**
   * Copies the relation from its current ppts to the specified
   * ppts.  The new ppts must have the same variables in the same
   * order as do the original ones.
   */
  public PptRelation copy(PptTopLevel new_parent, PptTopLevel new_child) {

    PptRelation rel = new PptRelation(new_parent, new_child, relationship);
    for (VarInfo vc : child_to_parent_map.keySet()) {
      VarInfo vp = child_to_parent_map.get(vc);
      VarInfo new_vc = new_child.var_infos[vc.varinfo_index];
      VarInfo new_vp = new_parent.var_infos[vp.varinfo_index];
      Assert.assertTrue(new_vc.name.equals(vc.name));
      Assert.assertTrue(new_vp.name.equals(vp.name));
      rel.child_to_parent_map.put(new_vc, new_vp);
      rel.parent_to_child_map.put(new_vp, new_vc);
    }
    return (rel);
  }

  // used by init_hierarchy below
  private static class SplitChild {
    public PptRelation rel;
    public PptSplitter ppt_split;
    public SplitChild(PptRelation rel, PptSplitter ppt_split) {
      this.rel = rel;
      this.ppt_split = ppt_split;
    }
  }
  /**
   * Initialize the hierarchical relationship between ppts.  Specifically
   * process each ppt, find its parent(s) in the partial order, and fill
   * this point into the children field in the parent.  Note that children
   * contains only the immediate descendants of the ppt.
   */
  public static void init_hierarchy(PptMap all_ppts) {

    for (Iterator<PptTopLevel> i = all_ppts.pptIterator(); i.hasNext();) {
      PptTopLevel ppt = i.next();
      PptName pname = ppt.ppt_name;
      PptRelation rel = null;
      Daikon.debugProgress.fine ("Processing ppt " + pname);
      debug.fine("Processing ppt " + pname);

      // If this is an object ppt, parent is the class point
      if (pname.isObjectInstanceSynthetic()) {
        PptTopLevel parent = all_ppts.get(pname.makeClassStatic());
        if (parent != null)
          rel = newClassObjectRel(parent, ppt);

        // Else if it's a method and not a constructor, parent is
        // object or class static methods will relate to the class,
        // while non-static methods will relate to the object.
        // Whether or not a method is static is not in the decls file.
        // We infer this by looking to see if the variables match with
        // the object ppt or the class ppt.
      } else if ((pname.isEnterPoint() && !pname.isConstructor())
          || pname.isCombinedExitPoint()) {

        PptTopLevel parent = all_ppts.get(pname.makeObject());

        if (parent != null) {
          if (find_var(ppt, parent.var_infos[0].name) != null)
            rel = newObjectMethodRel(parent, ppt);
          else {
            parent = all_ppts.get(parent.ppt_name.makeClassStatic());
            if (parent != null)
              rel = newObjectMethodRel(parent, ppt);
          }
        }

        // Else if an exitNN point, parent is combined exit point
      } else if (pname.isExitPoint()) {
        PptTopLevel parent = all_ppts.get(pname.makeExit());
        if (parent != null)
          rel = newCombinedExitExitNNRel(parent, ppt);
      }

      // If a relation was created, connect it into its ppts
      if (rel != null) {
        debug.fine(
          "-- ppt parent is "
            + rel.parent.name()
            + " with connections ["
            + rel.parent_to_child_var_string()
            + "]");
      } else {
        debug.fine(" -- no ppt parent");
      }

      // Connect combined exit points to enter points over orig variables
      if (pname.isCombinedExitPoint()) {
        PptTopLevel enter = all_ppts.get(pname.makeEnter());
        if (enter != null) {
          rel = PptRelation.newEnterExitRel(enter, ppt);
          debug.fine(
            " -- exit to enter "
              + enter.name
              + " with connections ["
              + rel.parent_to_child_var_string()
              + "]");
        } else {
          debug.fine("-- No matching enter for exit");
        }
      }

      // For all points, look for vars of a declared type for which we have
      // a corresponding OBJECT ppt.  Essentially these are all of the
      // users of the object.  Don't match if the variable already has
      // a parent (since the parent will provide the link back to the
      // object) For each variable of this type that we find, setup a
      // parent-child relationship with its corresponding OBJECT
      // variables.
      //
      // For example, consider class A with fields x and y and method
      // B.foo (A arg1, A arg2).  We will setup two relations to this
      // ppt -- one from A to b.foo.arg1 and one from A to b.foo.arg2.
      // in each we will equate A.x with arg.x and A.y with arg.y.
      //
      // We skip variables named exactly 'this' so that we don't setup a
      // recursive relationship from the object to itself.

      // DaikonSimple can not see these relations, so don't create them
      // if we'll be comparing to DaikonSimple.
      if (dkconfig_enable_object_user) {

        debug.fine("-- Looking for variables with an OBJECT ppt");
        for (int j = 0; j < ppt.var_infos.length; j++) {
          VarInfo vc = ppt.var_infos[j];
          String dstr = "-- -- var '" + vc.name.name() + "' - ";
          if (ppt.has_parent(vc)) {
            debug.fine(dstr + " Skipping, already has a parent");
            continue;
          }
          if (vc.name.equals(VarInfoName.THIS)) {
            debug.fine(dstr + " skipping, name is 'this'");
            continue;
          }
          PptTopLevel object_ppt = vc.find_object_ppt(all_ppts);
          if (object_ppt != null) {
            if (object_ppt == ppt) {
              debug.fine(
                dstr
                  + " skipping, OBJECT ("
                  + object_ppt
                  + ") is the same as this");
              continue;
            }
            rel = PptRelation.newObjectUserRel(object_ppt, ppt, vc);
            debug.fine(
              dstr
                + " Connected to Object ppt "
                + object_ppt.name()
                + " with connections ["
                + rel.parent_to_child_var_string()
                + "]");
          } else
            debug.fine(dstr + " No object ppt");
        }
      }
      // Connect any conditional ppt variables.  Only connect to the
      // first splitter, since each splitter should yield the same
      // results at the parent (since each splitter sees the same
      // points)  This should only happen at the leaves (numbered
      // exit points) since all other points should be built from
      // their other children.  But since we need the relation
      // from the child's point of view when printing, we create
      // under all cases and then remove it from non-leaves children
      // list.  This doesn't seem like the best solution.
      if (ppt.has_splitters()) {
        PptSplitter ppt_split = ppt.splitters.get(0);
        for (int ii = 0; ii < ppt_split.ppts.length; ii++) {
          rel = newPptPptConditional(ppt, ppt_split.ppts[ii]);
          debug.fine(
            " -- Connected down to ppt conditional "
              + ppt_split.ppts[ii].name()
              + " with connections ["
              + rel.parent_to_child_var_string()
              + "]");
          if (!ppt.ppt_name.isNumberedExitPoint()) {
            ppt.children.remove(rel);
          }
        }
      }
    }

    // Create relations between conditional ppts and their children.
    // The relationship between conditional ppts matches exactly
    // the relationship between each their parents.  For example,
    // presume ppt A has a child ppt B.  A has two conditional
    // ppts (AC1, AC2) and B has two conditional ppts (BC1, BC2)
    // Then AC1 is the parent of BC1 and AC2 is the parent of BC2

    // Loop over each ppt and process each non-leaf with splitters
    for (Iterator<PptTopLevel> pi = all_ppts.pptIterator(); pi.hasNext();) {
      PptTopLevel ppt = pi.next();
      if (ppt.ppt_name.isNumberedExitPoint())
        continue;
      if (!ppt.has_splitters())
        continue;

      // Loop over each splitter
      splitter_loop : for (
        Iterator<PptSplitter> ii = ppt.splitters.iterator(); ii.hasNext();) {
        PptSplitter ppt_split = ii.next();

        // list of children that match this splitter
        List<SplitChild> split_children = new ArrayList<SplitChild>();

        // Create a list of children for this splitter
        child_loop : for (int jj = 0; jj < ppt.children.size(); jj++) {
          PptRelation rel = ppt.children.get(jj);
          if (!rel.child.has_splitters())
            break;
          for (Iterator<PptSplitter> kk = rel.child.splitters.iterator(); kk.hasNext();) {
            PptSplitter csplit = kk.next();
            if (ppt_split.splitter == csplit.splitter) {
              split_children.add(new SplitChild(rel, csplit));
              continue child_loop;
            }
          }
          break;
        }

        // If we didn't find a matching splitter at each child, can't merge
        // this point.  Just remove it from the list of splitters
        if (split_children.size() != ppt.children.size()) {
          ii.remove();
          continue;
        }

        // Build the PptRelations for each child.  The PptRelation from
        // the conditional point is of the same type as the original
        // relation from parent to child
        for (Iterator<SplitChild> jj = split_children.iterator(); jj.hasNext();) {
          SplitChild sc = jj.next();
          ppt_split.add_relation(sc.rel, sc.ppt_split);
        }
      }
    }

    // Debug print the hierarchy in a more readable manner
    if (debug.isLoggable(Level.FINE)) {
      debug.fine("PPT Hierarchy");
      for (Iterator<PptTopLevel> i = all_ppts.pptIterator(); i.hasNext();) {
        PptTopLevel ppt = i.next();
        if (ppt.parents.size() == 0)
          ppt.debug_print_tree(debug, 0, null);
      }
    }

    // Debug print the equality sets for each ppt
    if (debug.isLoggable(Level.FINE)) {
      for (Iterator<PptTopLevel> i = all_ppts.pptIterator(); i.hasNext();) {
        PptTopLevel ppt = i.next();
        debug.fine(ppt.name() + " equality sets: " + ppt.equality_sets_txt());
      }
    }
  }
}
