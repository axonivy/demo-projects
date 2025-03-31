package rule.engine.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.DateTime;
import ch.ivyteam.ivy.scripting.objects.File;
import rule.engine.demo.enums.MemberType;

public class RuleRepo {

  static String CSV_PATH = "resources/";

  public static List<Rule> loadRules(String filename) throws IOException {
    String csvFile = CSV_PATH + filename;
    List<String> lines;
    File ivyFile = new File(csvFile);

    if (ivyFile.exists()) {
      lines = FileUtils.readLines(new File(csvFile).getJavaFile(), StandardCharsets.UTF_8);
    } else {
      try (var input = RuleRepo.class.getClassLoader().getResourceAsStream(csvFile)) {
        lines = IOUtils.readLines(input, StandardCharsets.UTF_8);
      } catch (Exception ex) {
        Ivy.log().error("Couldn't read file", ex);
        lines = List.of();
      }
    }
    return lines.stream()
        .map(RuleRepo::convertLineToRule)
        .collect(Collectors.toList());
  }

  public static String saveRules(List<Rule> rules) throws IOException {
    String content = rules.stream()
        .map(RuleRepo::convertRulesToLines)
        .collect(Collectors.joining("\n"));
    String csvFile = (CSV_PATH + (new DateTime()) + ".csv").replace(':', '-');
    File ivyFile = new File(csvFile);
    ivyFile.createNewFile();
    ivyFile.write(content);
    return ivyFile.getName();
  }

  private static Rule convertLineToRule(String line) {
    String[] data = line.split(",");
    Rule rule = new Rule();
    rule.setName(data[0]);
    rule.setLowerLimit(Integer.parseInt(data[1]));
    rule.setUpperLimit(Integer.parseInt(data[2]));
    rule.setMemberType(MemberType.valueOf(data[3]));
    rule.setDiscount(Integer.parseInt(data[4]));
    return rule;
  }

  private static String convertRulesToLines(Rule rule) {
    return new StringJoiner(",")
        .add(rule.getName())
        .add(rule.getLowerLimit().toString())
        .add(rule.getUpperLimit().toString())
        .add(rule.getMemberType().name())
        .add(rule.getDiscount().toString())
        .toString();
  }

}
