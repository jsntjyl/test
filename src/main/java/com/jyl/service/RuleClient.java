package com.jyl.service;

import com.jyl.rule.RuleClass;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

/**
 * @program: test
 * @description: 规则测试客户端
 * @author: jiangyunlin
 * @create: 2019-05-09 16:42
 */
public class RuleClient {
    public static void main(String[] args) {
        // 创建一个规则引擎
        // 如果不设置 RulesEngineParameters ，则采用默认引擎，即会过滤每一条规则
        // skipOnFirstAppliedRule(true) 代表满足其中一条规则就返回，其余规则不执行
        // skipOnFirstNonTriggeredRule(true) 代表满足其中一条规则后，继续过滤后续规则，直到后续规则不再满足为止
        // rulePriorityThreshold(数值) 代表只过滤规则的 priority 属性小于等于 该数值的规则。
        //RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        //RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine();

        // 添加各种规则
        Rules rules = new Rules();
        //rules.register(new RuleClass.FizzRule());
        //rules.register(new RuleClass.BuzzRule());
        rules.register(new RuleClass.FizzBuzzRule(new RuleClass.FizzRule()));
        rules.register(new RuleClass.FizzBuzzRule2(new RuleClass.BuzzRule()));
        //rules.register(new RuleClass.NonFizzBuzzRule());

        // 进行规则过滤
        Facts facts = new Facts();
        StringBuffer result = new StringBuffer();
        facts.put("result", result);
        for (int i = 1; i <= 1; i++) {
            facts.put("number", 35);
            facts.put("othernumber", 35);
            fizzBuzzEngine.fire(rules, facts);
            //System.out.println(facts.get("reresult"));
        }
    }

}