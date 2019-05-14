package com.jyl.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.support.ActivationRuleGroup;
import org.jeasy.rules.support.UnitRuleGroup;


/**
 * @program: test
 * @description: 规则
 * @author: jiangyunlin
 * @create: 2019-05-09 16:25
 */
public class RuleClass {

    /**
    * priority 越小越先执行
    */
    @Rule(priority = 1)
    public static class FizzRule {

        /**
         * 规则判断方法，满足规则返回true, 不满足返回false，然后交由action方法进行后续处理
         * fact 是规则过滤的条件
         */
        @Condition
        public boolean isFizz(@Fact("number") Integer number) {
            //System.out.println(number);
            return number % 5 == 0;
        }

        @Action
        public void printFizz(Facts facts) {
            System.out.println(facts.get("number"));
            System.out.println("fizz");
        }
    }

    @Rule(priority = 2)
    public static class BuzzRule {
        @Condition
        public boolean isBuzz(@Fact("othernumber") Integer number, @Fact("result") StringBuffer result) {
            boolean b = number % 7 == 0;
            if (b)
                result.append("matched" + number);
            //System.out.println(number);
            return b;
        }

        @Action
        public void printBuzz(Facts facts) {
            System.out.println(facts.get("number"));
            System.out.println("buzz other");
            facts.put("reresult","this is action return");
        }
    }

    public static class FizzBuzzRule extends ActivationRuleGroup {

        public FizzBuzzRule(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }

        @Override
        public int getPriority() {
            return 0;
        }
    }

    public static class FizzBuzzRule2 extends ActivationRuleGroup {

        public FizzBuzzRule2(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }

    @Rule(priority = 3)
    public static class NonFizzBuzzRule {

        @Condition
        public boolean isNotFizzNorBuzz(@Fact("number") Integer number) {
            // can return true, because this is the latest rule to trigger according to
            // assigned priorities
            // and in which case, the number is not fizz nor buzz
            return number % 5 != 0 || number % 7 != 0;
        }

        @Action
        public void printInput(@Fact("number") Integer number) {
            System.out.println("jj:" + number);
        }
    }

}
