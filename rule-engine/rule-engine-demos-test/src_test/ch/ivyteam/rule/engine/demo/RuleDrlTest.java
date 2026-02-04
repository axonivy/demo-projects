package ch.ivyteam.rule.engine.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.rule.engine.api.Rules;
import rule.engine.demo.Member;
import rule.engine.demo.enums.MemberType;

@IvyTest
@SuppressWarnings({"deprecation", "removal"})
class RuleDrlTest
{
  @ParameterizedTest
  @CsvFileSource(resources = "test-cases.csv", numLinesToSkip = 1)
  void test(String memberType, String purchaseAmount, String discount)
  {
    var member = new Member();
    member.setPurchaseAmount(Integer.valueOf(purchaseAmount));
    member.setMemberType(MemberType.valueOf(memberType));

    Rules.create()
            .namespace("rule.engine.demo.drl")
            .executor()
            .execute(member);

    Assertions.assertEquals(Integer.valueOf(discount), member.getDiscount());
  }
}
