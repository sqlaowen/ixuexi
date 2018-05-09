package com.example.demo;

import java.util.LinkedList;

/**
 * @Description: ${todo}
 * @author: wenshiwei
 * @date: 2018-03-26
 */
public enum FlowEnum {

    STATEMENT_FLOW("结算单接入流程", "001", new LinkedList() {{
        add(AreaEnum.SHANGQIU.getValue());
        add(AreaEnum.BEIJING.getValue());
        add(AreaEnum.HENAN.getValue());
        addLast(AreaEnum.SHANGHAI.getValue());
    }}),

    WAIDAN_FLOW("外单接入流程", "002", new LinkedList() {{
        add(1);
        add(3);
    }});

    private String flowName;
    private String flowCode;
    private LinkedList flow;

    FlowEnum(String flowName, String flowCode, LinkedList flow) {
        this.flowName = flowName;
        this.flowCode = flowCode;
        this.flow = flow;
    }

    public static FlowEnum getEnum(String flowCode) {
        for (FlowEnum flowEnum : FlowEnum.values()) {
            if (flowCode == flowEnum.getFlowCode()) {
                for (Object o : flowEnum.getFlow()) {
                    
                }
                return flowEnum;
            }
        }
        return null;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public LinkedList getFlow() {
        return flow;
    }

    public void setFlow(LinkedList flow) {
        this.flow = flow;
    }
}
