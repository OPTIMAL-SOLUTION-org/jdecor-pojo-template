package org.optsol.jdecor_pojo_template.model.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.jdecor_pojo_template.model.constants.Constants;
import org.optsol.jdecor_pojo_template.model.variables.Variables;

public class AvailableMetalQuantity
    extends AbstractOrtoolsConstraintManager<Constants> {

  public AvailableMetalQuantity() {
    super("m");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(Constants constants) {
    //generate all indexes of constraint group:
    HashSet<ConstraintKey> indexes = new HashSet<>();
    for (int m : constants.get_M()) {
      indexes.add(new ConstraintKey(m));
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      Constants constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index) throws Exception {
    //configure constraint for index m:
    int m = index.get("m");

    //sum_c N_c_m * x_c <= Q_m
    constraint.setUb(constants.get_Q(m));

    for (int c : constants.get_C()) {
      constraint.setCoefficient(
          variables.getVar(Variables.x, c),
          constants.get_N(c, m));
    }
  }
}
