import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaInfraestructuraComponent } from './nueva-infraestructura.component';

describe('NuevaInfraestructuraComponent', () => {
  let component: NuevaInfraestructuraComponent;
  let fixture: ComponentFixture<NuevaInfraestructuraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NuevaInfraestructuraComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NuevaInfraestructuraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
