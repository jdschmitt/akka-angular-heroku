import { SprayAngular2HerokuPage } from './app.po';

describe('spray-angular2-heroku App', () => {
  let page: SprayAngular2HerokuPage;

  beforeEach(() => {
    page = new SprayAngular2HerokuPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
