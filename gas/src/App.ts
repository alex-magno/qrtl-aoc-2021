function doGet() {
  const template = HtmlService.createTemplateFromFile('View');
  template.solutionsFolderUrl = SOLUTIONS_FOLDER_URL;
  return template.evaluate().setTitle('qrtl-aoc-2021/gas');
}

interface Solution {
  day: string,
  partOneResult: string,
  partTwoResult: string
}

function day1Problem(): Solution {
  return new challenges.day1Problem(PROBLEM_1_DATA_URL).solve();
}

function day2Problem(): Solution {
  return new challenges.day2Problem(PROBLEM_2_DATA_URL).solve();
}

function day3Problem(): Solution {
  return new challenges.day3Problem(PROBLEM_3_DATA_URL).solve();
}
