import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleEquipoComponent } from './detalle-equipo.component';

describe('DetalleEquipoComponent', () => {
  let component: DetalleEquipoComponent;
  let fixture: ComponentFixture<DetalleEquipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleEquipoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleEquipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
