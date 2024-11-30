import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ListaclientesinvService} from '../../services/servicecrm/listaclientesinv.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-mostraradjr',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './mostraradjr.component.html',
  styleUrl: './mostraradjr.component.css'
})
export class MostraradjrComponent {
  constructor(private servicio:ListaclientesinvService,private router: Router) {
  }
  mostrarNombre(){
    return this.servicio.getNombre();
  }
  backPage(){
    this.router.navigate(['menu']);
  }
}
