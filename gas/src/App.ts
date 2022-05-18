function doGet() {
  const template = HtmlService.createTemplateFromFile('View');
  template.hello = 'Hello GAS!';
  return template.evaluate().setTitle('qrtl-aoc-2021/gas');
}
