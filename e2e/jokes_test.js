Feature('jokes');

Scenario('/jokes requires authentication', (I) => {
  I.amOnPage('/jokes');
  I.seeInTitle('Login');
});

Scenario('/jokes is accessible after authentication', (I) => {
  const uniqueId = new Date().getTime();
  const uniqueUsername = 'oliechti-' + uniqueId;
  const uniqueEmail = 'olivier.liechti.' + uniqueId + '@heig-vd.ch';
  
  I.amOnPage('/login');
  I.seeInTitle('Login');
  I.fillField('form#register #tfUsername', uniqueUsername);
  I.fillField('form#register #tfFirstName', 'Olivier');
  I.fillField('form#register #tfLastName', 'Liechti');
  I.fillField('form#register #tfEmail', uniqueEmail);
  I.fillField('form#register #tfPassword', 'secret');
  I.click('form#register #bRegister');
  I.amOnPage('/jokes');
  I.seeInTitle('Jokes');

  const uniqueString = 'It\'s the story of a frog... ' + new Date().getTime();
  I.fillField('form#newJoke #tfText', uniqueString);
  I.click('form#newJoke #bSubmitJoke');
  I.see(uniqueString, '.jokes');
  pause();
});