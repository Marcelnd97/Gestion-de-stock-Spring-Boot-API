import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonneeArticleComponent } from './donnee-article.component';

describe('DonneeArticleComponent', () => {
  let component: DonneeArticleComponent;
  let fixture: ComponentFixture<DonneeArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonneeArticleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonneeArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
