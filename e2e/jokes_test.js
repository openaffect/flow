Feature('jokes');

Scenario('test something', (I) => {
  const uniqueString = 'It\'s the story of a frog... ' + new Date().getTime();
  I.amOnPage('/jokes');
  I.seeInTitle('Jokes');
  I.fillField('form#newJoke #tfText', uniqueString);
  I.click('form#newJoke #bSubmitJoke');
  I.see(uniqueString, '.jokes');
  // pause();
});

