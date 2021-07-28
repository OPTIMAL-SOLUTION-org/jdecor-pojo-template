package org.optsol.jdecor_pojo_template.model.constants;

import java.util.List;
import java.util.Map;
import lombok.Value;

@Value
public class Constants {
  //SETS
  List<Integer> _C;
  List<Integer> _M;
  //PARAMETERS
  Map<Integer, Double> _Q;
  Map<Integer, Double> _P;
  Map<Integer, Map<Integer, Double>> _N;

  public double get_Q(int m) {
    return get_Q().get(m);
  }

  public double get_P(int c) {
    return get_P().get(c);
  }

  public double get_N(
      int c,
      int m) {
    return get_N().get(c).getOrDefault(m, 0.);
  }
}
