package io.mycat.hbt4.logical;


import io.mycat.hbt4.Executor;
import io.mycat.hbt4.ExecutorImplementor;
import io.mycat.hbt4.ExplainWriter;
import io.mycat.hbt4.MycatRel;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptCost;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.core.Union;
import org.apache.calcite.rel.metadata.RelMetadataQuery;

import java.util.List;

/**
 * Union operator implemented in Mycat convention.
 */
public class MycatUnion extends Union implements MycatRel {
    public MycatUnion(
            RelOptCluster cluster,
            RelTraitSet traitSet,
            List<RelNode> inputs,
            boolean all) {
        super(cluster, traitSet, inputs, all);
    }

    public MycatUnion copy(
            RelTraitSet traitSet, List<RelNode> inputs, boolean all) {
        return new MycatUnion(getCluster(), traitSet, inputs, all);
    }

    @Override
    public RelOptCost computeSelfCost(RelOptPlanner planner,
                                      RelMetadataQuery mq) {
        return super.computeSelfCost(planner, mq).multiplyBy(.1);
    }


    @Override
    public ExplainWriter explain(ExplainWriter writer) {
        writer.name("MycatUnion").into();
        for (RelNode input : getInputs()) {
            MycatRel rel = (MycatRel) input;
            rel.explain(writer);
        }
        return writer.ret();
    }


    @Override
    public Executor implement(ExecutorImplementor implementor) {
        return implementor.implement(this);
    }
}