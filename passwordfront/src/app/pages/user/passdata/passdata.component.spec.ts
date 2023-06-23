import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassdataComponent } from './passdata.component';

describe('PassdataComponent', () => {
  let component: PassdataComponent;
  let fixture: ComponentFixture<PassdataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassdataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PassdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
