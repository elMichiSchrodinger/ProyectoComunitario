import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfraestructurasComponent } from './infraestructuras.component';

describe('InfraestructurasComponent', () => {
  let component: InfraestructurasComponent;
  let fixture: ComponentFixture<InfraestructurasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfraestructurasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfraestructurasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
