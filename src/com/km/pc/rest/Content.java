package com.km.pc.rest;

import com.thoughtworks.xstream.XStream;

public class Content {
	private WorkloadType WorkloadType;
	private LGDistribution LGDistribution;
	private PcGroupResponse Group;
	private Scheduler Scheduler;

	public Content(com.km.pc.rest.WorkloadType workloadType,
			com.km.pc.rest.LGDistribution lGDistribution,
			com.km.pc.rest.PcGroupResponse group,
			com.km.pc.rest.Scheduler scheduler) {
		super();
		WorkloadType = workloadType;
		LGDistribution = lGDistribution;
		Group = group;
		Scheduler = scheduler;
	}

	public String objectToXML() {
		XStream obj = new XStream();
		obj.alias("Content", Content.class);
		return obj.toXML(this);
	}

	public WorkloadType getWorkloadType() {
		return WorkloadType;
	}

	public void setWorkloadType(WorkloadType workloadType) {
		WorkloadType = workloadType;
	}

	public LGDistribution getLGDistribution() {
		return LGDistribution;
	}

	public void setLGDistribution(LGDistribution lGDistribution) {
		LGDistribution = lGDistribution;
	}

	public PcGroupResponse getGroup() {
		return Group;
	}

	public void setGroup(PcGroupResponse group) {
		Group = group;
	}

	public Scheduler getScheduler() {
		return Scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		Scheduler = scheduler;
	}

}
