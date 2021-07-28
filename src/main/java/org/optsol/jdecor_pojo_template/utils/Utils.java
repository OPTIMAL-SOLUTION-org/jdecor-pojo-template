package org.optsol.jdecor_pojo_template.utils;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.optsol.jdecor_pojo_template.model.constants.Constants;
import org.optsol.jdecor_pojo_template.solver.Solution;

@Slf4j
public final class Utils {
  private static final Map<Integer, String> coinNames =
      Map.of(
          1, "1 Cent",
          2, "2 Cents",
          3, "5 Cents",
          4, "10 Cents",
          5, "20 Cents",
          6, "50 Cents",
          7, "1 Euro",
          8, "2 Euros");

  private static final Map<Integer, String> metalNames =
      Map.of(
          1, "Copper (Cu)",
          2, "Iron (Fe)",
          3, "Nickel (Ni)",
          4, "Aluminium (Al)",
          5, "Zinc (Zn)",
          6, "Tin (Sn)");

  public static Constants generateConstants() {
    List<Integer> C = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    List<Integer> M = List.of(1, 2, 3, 4, 5, 6);
    Map<Integer, Double> Q = Map.of(
        1, 100.,
        2, 80.,
        3, 10.,
        4, 4.,
        5, 10.,
        6, 3.);
    Map<Integer, Double> P = Map.of(1, 0.01,
        2, 0.02,
        3, 0.05,
        4, 0.1,
        5, 0.2,
        6, 0.5,
        7, 1.,
        8, 2.);
    Map<Integer, Map<Integer, Double>> N = Map.of(
        1, Map.of(
            1, 0.102,
            2, 2.198),
        2, Map.of(
            1, 0.118,
            2, 2.942),
        3, Map.of(
            1, 0.134,
            2, 3.786),
        4, Map.of(
            1, 3.649,
            4, 0.205,
            5, 0.205,
            6, 0.041),
        5, Map.of(
            1, 5.1086,
            4, 0.287,
            5, 0.287,
            6, 0.0574),
        6, Map.of(
            1, 6.942,
            4, 0.39,
            5, 0.39,
            6, 0.078),
        7, Map.of(
            1, 5.625,
            3, 1.695,
            5, 0.18),
        8, Map.of(
            1, 6.375,
            3, 1.751,
            5, 0.374));

    return new Constants(C, M, Q, P, N);
  }


  public static void printSolution(
      Solution solution,
      Constants constants) {
    StringBuilder stringBuilder =
        new StringBuilder().append(System.getProperty("line.separator"));

    stringBuilder
        .append("Problem solved ")
        .append(solution.getSolutionState())
        .append(" in ")
        .append(solution.getSolutionTime().toSeconds())
        .append(" s: ")
        .append(solution.getObjectiveValue())
        .append("/").append(solution.getBestObjectiveBound())
        .append(" (best int/best bound)")
        .append(System.getProperty("line.separator"));

    Integer[] x = solution.get_x();
    stringBuilder.append("Coins: ");
    for (int c : constants.get_C()) {
      stringBuilder
          .append(coinNames.get(c))
          .append(": ")
          .append(x[c])
          .append("x | ");
    }
    stringBuilder.append(System.getProperty("line.separator"));

    log.info(stringBuilder.toString());
  }
}
