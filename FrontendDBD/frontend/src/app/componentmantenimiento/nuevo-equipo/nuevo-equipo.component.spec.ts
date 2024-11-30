import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoEquipoComponent } from './nuevo-equipo.component';

describe('NuevoEquipoComponent', () => {
  let component: NuevoEquipoComponent;
  let fixture: ComponentFixture<NuevoEquipoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NuevoEquipoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NuevoEquipoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
