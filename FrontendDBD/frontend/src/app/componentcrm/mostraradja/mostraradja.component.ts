import { Component } from '@angular/core';
import {ListaclientesinvService} from '../../services/servicecrm/listaclientesinv.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-mostraradja',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './mostraradja.component.html',
  styleUrl: './mostraradja.component.css'
})
export class MostraradjaComponent {
  constructor(private servicio:ListaclientesinvService,private router: Router) {
  }
  mostrarNombre(){
    return this.servicio.getNombre();
  }
  backPage(){
    this.router.navigate(['menu']);
  }
}
