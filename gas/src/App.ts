function doGet() {
  const template = HtmlService.createTemplateFromFile('View');
  template.solutionsFolderUrl = SOLUTIONS_FOLDER_URL;
  return template.evaluate().setTitle('qrtl-aoc-2021/gas');
}

interface Solution {
  challenge: string,
  result: string
}

function day1Problem(): Solution {
  return new challenges.day1Problem().solve();
}
