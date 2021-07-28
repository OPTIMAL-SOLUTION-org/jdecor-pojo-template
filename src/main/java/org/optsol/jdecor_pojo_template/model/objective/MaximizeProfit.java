package org.optsol.jdecor_pojo_template.model.objective;

import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsObjectiveManager;
import org.optsol.jdecor_pojo_template.model.constants.Constants;
import org.optsol.jdecor_pojo_template.model.variables.Variables;

public class MaximizeProfit extends AbstractOrtoolsObjectiveManager<Constants> {

  @Override
  protected void configureObjective(
      MPObjective objective,
      Constants constants,
      AbstractVariableManager<MPSolver, MPVariable> variables) throws Exception {
    //max sum_c:C[ P_c * x_c ]
    objective.setMaximization();

    for (int c : constants.get_C()) {
      objective.setCoefficient(
          variables.getVar(Variables.x, c),
          constants.get_P(c));
    }
  }
}
