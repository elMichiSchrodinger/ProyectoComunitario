import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleInfraestructuraComponent } from './detalle-infraestructura.component';

describe('DetalleInfraestructuraComponent', () => {
  let component: DetalleInfraestructuraComponent;
  let fixture: ComponentFixture<DetalleInfraestructuraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleInfraestructuraComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleInfraestructuraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
