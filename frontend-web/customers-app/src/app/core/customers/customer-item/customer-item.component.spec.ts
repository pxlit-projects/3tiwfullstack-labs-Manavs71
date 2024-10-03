import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerItemComponent } from './customer-item.component';

describe('CustomerItemComponent', () => {
  let component: CustomerItemComponent;
  let fixture: ComponentFixture<CustomerItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
