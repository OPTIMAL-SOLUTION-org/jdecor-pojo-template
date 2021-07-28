package org.optsol.jdecor_pojo_template.model;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.List;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.core.IConstraintManager;
import org.optsol.jdecor.core.IObjectiveManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsModelFactory;
import org.optsol.jdecor.ortools.OrtoolsVariableManager;
import org.optsol.jdecor.ortools.SolverEngine;
import org.optsol.jdecor_pojo_template.model.constants.Constants;
import org.optsol.jdecor_pojo_template.model.constraints.AvailableMetalQuantity;
import org.optsol.jdecor_pojo_template.model.objective.MaximizeProfit;
import org.optsol.jdecor_pojo_template.model.variables.Variables;

public class Model extends AbstractOrtoolsModelFactory<Constants> {

  public Model(SolverEngine solverEngine) {
    super(solverEngine);
  }

  @Override
  protected AbstractVariableManager<MPSolver, MPVariable> generateVarManager() {
    return
        new OrtoolsVariableManager.Builder()
            // x : int+
            .addIntVar(Variables.x)
            .addLowerBound(Variables.x, 0.)

            .build();
  }

  @Override
  protected IObjectiveManager<
      ? super Constants, MPVariable, MPSolver> generateObjective() {
    return new MaximizeProfit();
  }

  @Override
  protected List<
      IConstraintManager<
          ? super Constants,
          MPVariable,
          MPSolver>> generateConstraints() {
    return List.of(
        new AvailableMetalQuantity());
  }
}
